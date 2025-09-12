package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.model.MatriculaAluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MatriculaAlunoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "disciplina", ignore = true)
    MatriculaAluno toEntity(MatriculaAlunoRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "alunoNome", source = "aluno.nome")
    @Mapping(target = "disciplinaId", source = "disciplina.id")
    @Mapping(target = "disciplinaNome", source = "disciplina.nome")
    @Mapping(target = "matriculaAlunoStatus", source = "status")
    @Mapping(target = "media",
            expression = "java( (entity.getNota1() != null && entity.getNota2() != null) ? " +
                    "(entity.getNota1() + entity.getNota2()) / 2 : null )")
    MatriculaAlunoResponseDTO toDTO(MatriculaAluno entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "disciplina", ignore = true)
    void updateFromDto(MatriculaAlunoRequestDTO dto, @MappingTarget MatriculaAluno matriculaAluno);
}
