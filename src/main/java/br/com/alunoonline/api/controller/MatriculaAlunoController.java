package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.service.MatriculaAlunoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaAlunoController {

    private final MatriculaAlunoServiceImpl matriculaAlunoService;

    @PostMapping
    public ResponseEntity<MatriculaAlunoResponseDTO> criarMatricula(
            @Valid
            @RequestBody MatriculaAlunoRequestDTO dto) {
        return ResponseEntity.status(201).body(matriculaAlunoService.criarMatricula(dto));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaAlunoResponseDTO>> listarMatriculas() {
        return ResponseEntity.ok(matriculaAlunoService.listarMatriculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaAlunoResponseDTO> listarMatriculaPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(matriculaAlunoService.listarMatriculaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaAlunoResponseDTO> atualizarMatriculaPorId(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaAlunoRequestDTO dto
    ) {
        return ResponseEntity.ok(matriculaAlunoService.atualizarMatricula(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(Long id) {
        matriculaAlunoService.deletarMatricula(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizar-status/{id}")
    public ResponseEntity<MatriculaAlunoResponseDTO> atualizarStatusMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaAlunoService.atualizarStatusMatrícula(id));
    }

}
