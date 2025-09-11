package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ProfessorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 250, message = "Nome deve ter no máximo 250 caracteres")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @CPF(message = "CPF inválido")
        String cpf
) {
    public ProfessorRequestDTO {
        if (cpf != null) {
            cpf = cpf.replaceAll("\\D", "");
        }
    }
}
