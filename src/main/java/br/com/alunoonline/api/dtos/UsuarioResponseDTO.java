package br.com.alunoonline.api.dtos;

import br.com.alunoonline.api.enums.Role;

public record UsuarioResponseDTO(
        String username,
        String email,
        Role role
) {
}
