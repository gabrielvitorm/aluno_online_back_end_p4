package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.ProfessorRequestDTO;
import br.com.alunoonline.api.dtos.ProfessorResponseDTO;
import br.com.alunoonline.api.service.ProfessorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
@Tag(name = "Professores", description = "Gerenciamento de professores")
public class ProfessorController {

    private final ProfessorServiceImpl professorService;

    @PostMapping
    @Operation(summary = "Cadastrar Professor", description = "Cria um novo professor no sistema")
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@Valid @RequestBody ProfessorRequestDTO dto){
        return ResponseEntity.status(201).body(professorService.criarProfessor(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Professores", description = "Lista todos os professores cadastrados")
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores(){
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Professor por Id", description = "Busca um professor pelo Id cadastrado no sistema")
    public ResponseEntity<ProfessorResponseDTO> listarProfessorPorId(@PathVariable Long id){
        return ResponseEntity.ok(professorService.listarProfessorPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Professor", description = "Atualiza os dados de um professor cadastrado")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDTO dto){
        return ResponseEntity.ok(professorService.atualizarProfessor(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Professor", description = "Remove um professor cadastrado no sistema")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id){
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
