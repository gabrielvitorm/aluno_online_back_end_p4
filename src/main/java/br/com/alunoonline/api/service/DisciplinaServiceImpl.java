package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.DisciplinaResponseDTO;
import br.com.alunoonline.api.dtos.DisciplinaResquestDTO;
import br.com.alunoonline.api.mapper.DisciplinaMapper;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaMapper disciplinaMapper;


    @Override
    public DisciplinaResponseDTO criarDisciplina(DisciplinaResquestDTO dto) {
        Disciplina disciplina = disciplinaMapper.toEntity(dto);

        disciplina.setProfessor(buscarProfessor(dto.professorId()));

        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    @Override
    public List<DisciplinaResponseDTO> listarDisciplinas() {
        return disciplinaRepository.findAll().stream()
                .map(disciplinaMapper::toDTO)
                .toList();
    }

    @Override
    public DisciplinaResponseDTO listarDisciplinaPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));

        return disciplinaMapper.toDTO(disciplina);
    }

    @Transactional
    @Override
    public DisciplinaResponseDTO atualizarDisciplina(Long id, DisciplinaResquestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
        disciplina.setProfessor(buscarProfessor(dto.professorId()));

        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    @Transactional
    @Override
    public void deletarDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Disciplina não encontrada");
        }

        disciplinaRepository.deleteById(id);
    }

    private Professor buscarProfessor(Long id){
         return professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));
    }
}
