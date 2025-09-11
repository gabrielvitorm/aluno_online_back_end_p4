package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.DisciplinaResponseDTO;
import br.com.alunoonline.api.dtos.DisciplinaResquestDTO;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.service.DisciplinaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaServiceImpl disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@Valid @RequestBody DisciplinaResquestDTO dto){
        return ResponseEntity.status(201).body(disciplinaService.criarDisciplina(dto));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas(){
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }
}
