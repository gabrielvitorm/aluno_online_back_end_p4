package br.com.alunoonline.api.dtos;

public record ProfessorResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {
}
