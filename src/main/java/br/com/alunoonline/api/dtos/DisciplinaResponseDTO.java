package br.com.alunoonline.api.dtos;

public record DisciplinaResponseDTO(
        Long id,
        String nome,
        Integer cargaHoraria,
        Long professorId,
        String professorNome
) {
}
