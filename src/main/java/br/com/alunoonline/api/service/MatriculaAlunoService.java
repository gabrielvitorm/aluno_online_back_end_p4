package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.dtos.NotasMatriculaAlunoDTO;

import java.util.List;

public interface MatriculaAlunoService {

    MatriculaAlunoResponseDTO criarMatricula(MatriculaAlunoRequestDTO dto);

    List<MatriculaAlunoResponseDTO> listarMatriculas();

    MatriculaAlunoResponseDTO listarMatriculaPorId(Long id);

    MatriculaAlunoResponseDTO atualizarMatricula(Long id, MatriculaAlunoRequestDTO dto);

    void deletarMatricula(Long id);

    MatriculaAlunoResponseDTO atualizarStatusMatrícula(Long id);

    MatriculaAlunoResponseDTO atualizarNotas(Long id, NotasMatriculaAlunoDTO dto);
}
