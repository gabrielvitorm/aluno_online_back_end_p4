package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.service.AlunoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Alunos", description = "Gerenciamento de alunos")
public class AlunoController {
    private final AlunoServiceImpl alunoService;

    @PostMapping
    @Operation(summary = "Cadastrar Aluno", description = "Cria um novo aluno no sistema")
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO dto){
        return ResponseEntity.status(201).body(alunoService.criarAluno(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Alunos", description = "Lista todos os alunos do sistema")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos(){
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @Operation(summary = "Lista Aluno por Id", description = "Lista o aluno pelo Id cadastrado no sistema")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> listarAlunoPorId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.listarAlunoPorId(id));
    }

    @Operation(summary = "Atualizar Aluno", description = "Atualiza um aluno cadastrado no sistema")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @RequestBody AlunoRequestDTO dto
    ){
        return ResponseEntity.ok(alunoService.atualizarAluno(id, dto));
    }

    @Operation(summary = "Deletar Aluno", description = "Deleta uma aluno cadastrado no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
