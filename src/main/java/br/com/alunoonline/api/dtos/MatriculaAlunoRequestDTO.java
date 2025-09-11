package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatriculaAlunoRequestDTO(
        @NotNull(message = "Aluno é obrigatório")
        Long alunoId,

        @NotNull(message = "Disciplina é obrigatório")
        Long disciplinaId,

        Double nota1,

        Double nota2,

        @NotBlank(message = "Status da matrícula é obrigatório")
        MatriculaAlunoStatusEnum matriculaAlunoStatus
) {
}
