package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dtos.AlunoRequestDTO;
import br.com.alunoonline.api.dtos.AlunoResponseDTO;
import br.com.alunoonline.api.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "id", ignore = true)
    Aluno toEntity(AlunoRequestDTO dto);

    AlunoResponseDTO toDTO(Aluno aluno);
}
