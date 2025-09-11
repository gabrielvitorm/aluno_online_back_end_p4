package br.com.alunoonline.api.dtos;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email
) {
}
