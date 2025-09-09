package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.mapper.AlunoMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    @Override
    public AlunoResponseDTO criarAluno(AlunoRequestDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);

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

    @Override
    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno não encontrado");
        }

        alunoRepository.deleteById(id);
    }
}
