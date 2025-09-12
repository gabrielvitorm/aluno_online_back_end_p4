package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;

public record MatriculaAlunoResponseDTO(
        Long id,
        Long alunoId,
        String alunoNome,
        Long disciplinaId,
        String disciplinaNome,
        Double nota1,
        Double nota2,
        String matriculaAlunoStatus,
        Double media
) {
}
