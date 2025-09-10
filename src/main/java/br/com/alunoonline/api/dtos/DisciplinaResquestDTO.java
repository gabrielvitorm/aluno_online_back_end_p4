package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaResquestDTO(
        @NotBlank(message = "Nome da disciplina é obrigatório")
        String nome,

        @NotBlank(message = "Carga Horária é obirgatório")
        Integer cargaHoraria,

        @NotNull(message = "Id do professor é obirgatório")
        Long professorId
) {
}
