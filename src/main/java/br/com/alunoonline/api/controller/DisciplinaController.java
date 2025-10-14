package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.DisciplinaResponseDTO;
import br.com.alunoonline.api.dtos.DisciplinaResquestDTO;
import br.com.alunoonline.api.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;


    @PostMapping
    @Operation(summary = "Cadastrar Disciplina", description = "Cria uma nova disciplina no sistema")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@Valid @RequestBody DisciplinaResquestDTO dto) {
        return ResponseEntity.status(201).body(disciplinaService.criarDisciplina(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Disciplinas", description = "Lista todas as disciplinas cadastradas")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar Disciplina por Id", description = "Busca uma disciplina pelo Id cadastrado no sistema")
    @PreAuthorize("hasAnyRole('COORDENADOR','PROFESSOR')")
    public ResponseEntity<DisciplinaResponseDTO> listarDisciplinaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.listarDisciplinaPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Disciplina", description = "Atualiza os dados de uma disciplina cadastrada")
    @PreAuthorize("hasAnyRole('COORDENADOR','PROFESSOR')")
    public ResponseEntity<DisciplinaResponseDTO> atualizarDisciplina(
            @PathVariable Long id,
            @Valid @RequestBody DisciplinaResquestDTO dto
    ) {
        return ResponseEntity.ok(disciplinaService.atualizarDisciplina(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Disciplina", description = "Remove uma disciplina cadastrada no sistema")
    @PreAuthorize("hasRole('COORDENADOR')")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
