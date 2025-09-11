package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;

public record MatriculaAlunoResponseDTO(
        Long id,
        Long alunoId,
        Long disciplinaId,
        Double nota1,
        Double nota2,
        MatriculaAlunoStatusEnum matriculaAlunoStatus
) {
}
