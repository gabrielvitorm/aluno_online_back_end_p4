package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dtos.DisciplinaResponseDTO;
import br.com.alunoonline.api.dtos.DisciplinaResquestDTO;
import br.com.alunoonline.api.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "professor", ignore = true)
    Disciplina toEntity(DisciplinaResquestDTO dto);

    DisciplinaResponseDTO toDTO(Disciplina disciplina);


}
