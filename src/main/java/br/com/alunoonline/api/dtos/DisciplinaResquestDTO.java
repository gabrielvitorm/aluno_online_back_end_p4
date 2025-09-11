package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DisciplinaResquestDTO(
        @NotBlank(message = "Nome da disciplina é obrigatório")
        String nome,

        @NotNull(message = "Carga horária é obrigatória")
        @Positive(message = "Carga horária deve ser maior que zero")
        Integer cargaHoraria,

        @NotNull(message = "Id do professor é obrigatório")
        Long professorId
) {
}
