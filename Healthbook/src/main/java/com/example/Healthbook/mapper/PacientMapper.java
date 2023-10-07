package com.example.Healthbook.mapper;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PacientMapper {
    public static final PacientMapper INSTANCE = Mappers.getMapper(PacientMapper.class);
    public abstract Pacient fromPacient(PacientDTO pacientDTO);
    public abstract PacientDTO toPacient(Pacient pacient);
    public abstract List<PacientDTO> toPacients(List<Pacient> pacient);

}

