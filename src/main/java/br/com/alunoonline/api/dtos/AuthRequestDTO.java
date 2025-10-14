package br.com.alunoonline.api.dtos;

public record AuthRequestDTO(
        String username,
        String password
) {
}
