package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.ProfessorRequestDTO;
import br.com.alunoonline.api.dtos.ProfessorResponseDTO;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.service.ProfessorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorServiceImpl professorService;

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@Valid @RequestBody ProfessorRequestDTO dto){
        return ResponseEntity.status(201).body(professorService.criarProfessor(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores(){
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> listarProfessorPorId(@PathVariable Long id){
        return ResponseEntity.ok(professorService.listarProfessorPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDTO dto){

        return ResponseEntity.ok(professorService.atualizarAluno(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id){
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
