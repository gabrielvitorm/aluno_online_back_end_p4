package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
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

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));

        matriculaAluno.setDisciplina(disciplina);
        matriculaAluno.setAluno(aluno);
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

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));

        matriculaAlunoMapper.updateFromDto(dto, matriculaAluno);

        matriculaAluno.setDisciplina(disciplina);
        matriculaAluno.setAluno(aluno);

        return matriculaAlunoMapper.toDTO(matriculaAlunoRepository.save(matriculaAluno));
    }

    @Transactional
    @Override
    public void deletarMatricula(Long id) {
        if (!matriculaAlunoRepository.existsById(id)){
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
}
