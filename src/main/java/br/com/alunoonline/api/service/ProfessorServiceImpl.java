package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.ProfessorRequestDTO;
import br.com.alunoonline.api.dtos.ProfessorResponseDTO;
import br.com.alunoonline.api.mapper.ProfessorMapper;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;


    @Override
    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto) {
        Professor professor = professorMapper.toEntity(dto);

        professorRepository.save(professor);

        return professorMapper.toDTO(professor);
    }

    @Override
    public List<ProfessorResponseDTO> listarProfessores() {
        return professorRepository.findAll().stream()
                .map(professorMapper::toDTO)
                .toList();
    }

    @Override
    public ProfessorResponseDTO listarProfessorPorId(
            Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));

        return professorMapper.toDTO(professor);
    }

    @Transactional
    @Override
    public ProfessorResponseDTO atualizarProfessor(
            Long id,
            ProfessorRequestDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));

        professorMapper.toEntity(dto);

        return professorMapper.toDTO(professorRepository.save(professor));
    }

    @Transactional
    @Override
    public void deletarProfessor(Long id) {
        if (!professorRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Professor não encontrado");
        }

        professorRepository.deleteById(id);
    }
}
