package br.com.alunoonline.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record AlunoRequestDTO(
        @NotBlank(message = "Nome é obrgatório")
        @Size(max = 250)
        String nome,

        @CPF(message = "Cpf inválido")
        String cpf,

        @Email(message = "Email inválido")
        String email
) {
}
