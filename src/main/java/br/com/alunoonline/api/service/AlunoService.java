package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;

import java.util.List;

public interface AlunoService {

    AlunoResponseDTO criarAluno(AlunoRequestDTO dto);
    List<AlunoResponseDTO> listarAlunos();
    AlunoResponseDTO listarAlunoPorId(Long id);
    AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto);
    AlunoResponseDTO deletarAluno(Long id);
}
