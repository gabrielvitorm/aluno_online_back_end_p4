package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.dtos.NotasMatriculaAlunoDTO;
import br.com.alunoonline.api.service.MatriculaAlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "Gerenciamento das matrículas dos alunos")
public class MatriculaAlunoController {

    private final MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @Operation(summary = "Cadastrar Matrícula", description = "Cria uma nova matrícula para um aluno em uma disciplina")
    public ResponseEntity<MatriculaAlunoResponseDTO> criarMatricula(
            @Valid @RequestBody MatriculaAlunoRequestDTO dto) {
        return ResponseEntity.status(201).body(matriculaAlunoService.criarMatricula(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Matrículas", description = "Lista todas as matrículas cadastradas no sistema")
    public ResponseEntity<List<MatriculaAlunoResponseDTO>> listarMatriculas() {
        return ResponseEntity.ok(matriculaAlunoService.listarMatriculas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Matrícula por Id", description = "Busca uma matrícula pelo Id cadastrado no sistema")
    public ResponseEntity<MatriculaAlunoResponseDTO> listarMatriculaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaAlunoService.listarMatriculaPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Matrícula", description = "Atualiza os dados de uma matrícula existente")
    public ResponseEntity<MatriculaAlunoResponseDTO> atualizarMatriculaPorId(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaAlunoRequestDTO dto
    ) {
        return ResponseEntity.ok(matriculaAlunoService.atualizarMatricula(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Matrícula", description = "Remove uma matrícula cadastrada no sistema")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaAlunoService.deletarMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizar-status/{id}")
    @Operation(summary = "Atualizar Status da Matrícula", description = "Atualiza apenas o status de uma matrícula")
    public ResponseEntity<MatriculaAlunoResponseDTO> atualizarStatusMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaAlunoService.atualizarStatusMatrícula(id));
    }

    @PatchMapping("/atualizar-notas/{id}")
    @Operation(summary = "Atualizar Notas da Matrícula", description = "Atualiza as notas do Aluno e também atualiza o status para aprovado ou reprovado")
    public ResponseEntity<MatriculaAlunoResponseDTO> atualizarNotas(
            @PathVariable Long id,
            @Valid @RequestBody NotasMatriculaAlunoDTO dto
    ) {
        return ResponseEntity.ok(matriculaAlunoService.atualizarNotas(id, dto));
    }
}
