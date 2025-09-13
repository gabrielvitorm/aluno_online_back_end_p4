package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.enums.SituacaoAlunoEnum;
import br.com.alunoonline.api.mapper.AlunoMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    @Override
    public AlunoResponseDTO criarAluno(AlunoRequestDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);

        aluno.setSituacaoAlunoEnum(SituacaoAlunoEnum.ATIVO);

        alunoRepository.save(aluno);

        return alunoMapper.toDTO(aluno);
    }

    @Override
    public List<AlunoResponseDTO> listarAlunos() {
        return alunoRepository.findAll().stream()
                .map(alunoMapper::toDTO)
                .toList();
    }

    @Override
    public AlunoResponseDTO listarAlunoPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client not found"));

        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    @Override
    public AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setCpf(dto.cpf());

        alunoRepository.save(aluno);

        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    @Override
    public AlunoResponseDTO deletarAluno(Long id) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));

        aluno.setSituacaoAlunoEnum(SituacaoAlunoEnum.DESATIVADO);

        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }
}
