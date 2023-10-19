package com.example.Healthbook.mapper;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


public interface PacientMapper {

    PacientMapper INSTANCE = Mappers.getMapper(PacientMapper.class);
    Pacient fromPacient(PacientDTO pacientDTO);
    PacientDTO toPacient(Pacient pacient);
    List<PacientDTO> toPacients(List<Pacient> pacient);
    List<Pacient> fromPacients(List<PacientDTO> pacient);

}

