package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.dtos.ProfessorRequestDTO;
import br.com.alunoonline.api.dtos.ProfessorResponseDTO;

import java.util.List;

public interface ProfessorService {
    ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto);
    List<ProfessorResponseDTO> listarProfessores();
    ProfessorResponseDTO listarProfessorPorId(Long id);
    ProfessorResponseDTO atualizarAluno(Long id, ProfessorRequestDTO dto);
    void deletarProfessor(Long id);
}
