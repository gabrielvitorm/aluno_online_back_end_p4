package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.dtos.NotasMatriculaAlunoDTO;
import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.mapper.MatriculaAlunoMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaAlunoServiceImpl implements MatriculaAlunoService {

    private final MatriculaAlunoRepository matriculaAlunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final MatriculaAlunoMapper matriculaAlunoMapper;

    @Override
    public MatriculaAlunoResponseDTO criarMatricula(MatriculaAlunoRequestDTO dto) {
        MatriculaAluno matriculaAluno = matriculaAlunoMapper.toEntity(dto);

        matriculaAluno.setDisciplina(buscarDisciplina(dto.disciplinaId()));
        matriculaAluno.setAluno(buscarAluno(dto.alunoId()));
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);

        return matriculaAlunoMapper.toDTO(matriculaAlunoRepository.save(matriculaAluno));
    }

    @Override
    public List<MatriculaAlunoResponseDTO> listarMatriculas() {
        return matriculaAlunoRepository.findAll().stream()
                .map(matriculaAlunoMapper::toDTO)
                .toList();
    }

    @Override
    public MatriculaAlunoResponseDTO listarMatriculaPorId(Long id) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada"));

        return matriculaAlunoMapper.toDTO(matriculaAluno);
    }

    @Transactional
    @Override
    public MatriculaAlunoResponseDTO atualizarMatricula(Long id, MatriculaAlunoRequestDTO dto) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada"));

        matriculaAlunoMapper.updateFromDto(dto, matriculaAluno);

        matriculaAluno.setDisciplina(buscarDisciplina(dto.disciplinaId()));
        matriculaAluno.setAluno(buscarAluno(dto.alunoId()));

        return matriculaAlunoMapper.toDTO(matriculaAlunoRepository.save(matriculaAluno));
    }

    @Transactional
    @Override
    public void deletarMatricula(Long id) {
        if (!matriculaAlunoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Matrícula não encontrada");
        }

        matriculaAlunoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public MatriculaAlunoResponseDTO atualizarStatusMatrícula(Long id) {
        MatriculaAluno entity = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada"));

        if (entity.getStatus() != MatriculaAlunoStatusEnum.MATRICULADO) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Só é possível trancar matrículas com status MATRICULADO");
        }

        entity.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
        return matriculaAlunoMapper.toDTO(matriculaAlunoRepository.save(entity));
    }

    @Override
    public MatriculaAlunoResponseDTO atualizarNotas(Long id, NotasMatriculaAlunoDTO dto) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada"));

        if (dto.nota1() == null || dto.nota2() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "As duas notas são obrigatórias!");
        }

        matriculaAluno.setNota1(dto.nota1());
        matriculaAluno.setNota2(dto.nota2());

        double media = (dto.nota1() + dto.nota2()) / 2.0;
        matriculaAluno.setStatus(media >= 7.0 ? MatriculaAlunoStatusEnum.APROVADO
                : MatriculaAlunoStatusEnum.REPROVADO);

        matriculaAlunoRepository.save(matriculaAluno);

        return matriculaAlunoMapper.toDTO(matriculaAluno);
    }

    private Disciplina buscarDisciplina(Long id){
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));
    }

    private Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));
    }
}
