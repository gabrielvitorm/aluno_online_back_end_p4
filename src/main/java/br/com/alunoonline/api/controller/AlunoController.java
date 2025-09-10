package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.service.AlunoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoServiceImpl alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO dto){
        return ResponseEntity.status(201).body(alunoService.criarAluno(dto));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos(){
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> listarAlunoPorId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.listarAlunoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @RequestBody AlunoRequestDTO dto
    ){
        return ResponseEntity.ok(alunoService.atualizarAluno(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
