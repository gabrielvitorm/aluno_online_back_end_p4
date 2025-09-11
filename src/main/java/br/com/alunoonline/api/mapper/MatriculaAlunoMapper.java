package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dtos.MatriculaAlunoRequestDTO;
import br.com.alunoonline.api.dtos.MatriculaAlunoResponseDTO;
import br.com.alunoonline.api.model.MatriculaAluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaAlunoMapper {

    @Mapping(target = "id", ignore = true)
    MatriculaAluno toEntity(MatriculaAlunoRequestDTO dto);

    MatriculaAlunoResponseDTO toDTO(MatriculaAluno matriculaAluno);
}
