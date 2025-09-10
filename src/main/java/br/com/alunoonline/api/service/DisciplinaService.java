package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dtos.DisciplinaResponseDTO;
import br.com.alunoonline.api.dtos.DisciplinaResquestDTO;

import java.util.List;

public interface DisciplinaService {

    DisciplinaResponseDTO criarDisciplina(DisciplinaResquestDTO dto);

    List<DisciplinaResponseDTO> listarDisciplinas();

    DisciplinaResponseDTO listarDisciplinaPorId(Long id);

    DisciplinaResponseDTO atualizarDisciplina(Long id, DisciplinaResquestDTO dto);

    void deletarDisciplina(Long id);
}
