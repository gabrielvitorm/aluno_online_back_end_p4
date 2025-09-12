package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.SituacaoAlunoEnum;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        SituacaoAlunoEnum situacao
) {
}
