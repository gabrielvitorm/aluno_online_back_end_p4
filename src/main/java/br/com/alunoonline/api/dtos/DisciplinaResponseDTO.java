package br.com.alunoonline.api.dtos;

public record DisciplinaResponseDTO(
        Long id,
        String nome,
        Integer cargarHoraria,
        Long professorId,
        String professorNome
) {
}
