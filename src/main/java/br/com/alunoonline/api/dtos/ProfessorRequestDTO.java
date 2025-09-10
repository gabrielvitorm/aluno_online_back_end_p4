package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ProfessorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @CPF(message = "Cpf inválido")
        String cpf
) {
}
