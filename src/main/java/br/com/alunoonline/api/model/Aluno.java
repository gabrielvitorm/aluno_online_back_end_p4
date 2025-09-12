package br.com.alunoonline.api.model;

import br.com.alunoonline.api.enums.SituacaoAlunoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "aluno")
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 250)
    private String nome;

    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;

    @Column(name = "email", length = 250, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", length = 30)
    private SituacaoAlunoEnum situacaoAlunoEnum;
}
