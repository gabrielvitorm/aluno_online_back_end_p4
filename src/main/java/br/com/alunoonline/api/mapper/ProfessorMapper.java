package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dtos.ProfessorRequestDTO;
import br.com.alunoonline.api.dtos.ProfessorResponseDTO;
import br.com.alunoonline.api.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "id", ignore = true)
    Professor toEntity(ProfessorRequestDTO dto);

    ProfessorResponseDTO toDTO(Professor professor);
}
