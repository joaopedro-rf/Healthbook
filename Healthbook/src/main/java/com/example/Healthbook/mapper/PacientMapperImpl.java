package com.example.Healthbook.mapper;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PacientMapperImpl implements PacientMapper {

    @Override
    public Pacient fromPacient(PacientDTO pacientDTO) {
        if ( pacientDTO == null ) {
            return null;
        }

        Pacient pacient = new Pacient();


        pacient.setName( pacientDTO.getName() );
        pacient.setAge( pacientDTO.getAge() );
        pacient.setPhoneNumber( pacientDTO.getPhoneNumber() );
        pacient.setAddress( pacientDTO.getAddress() );

        return pacient;
    }

    @Override
    public PacientDTO toPacient(Pacient pacient) {
        if ( pacient == null ) {
            return null;
        }

        PacientDTO pacientDTO = new PacientDTO();


        pacientDTO.setName( pacient.getName() );
        pacientDTO.setAge( pacient.getAge() );
        pacientDTO.setPhoneNumber( pacient.getPhoneNumber() );
        pacientDTO.setAddress( pacient.getAddress() );

        return pacientDTO;
    }

    public List<PacientDTO> toPacients(List<Pacient> sourceList) {
        return sourceList.stream()
                .map(this::toPacient)
                .collect(Collectors.toList());
    }

    public List<Pacient> fromPacients(List<PacientDTO> targetList) {
        return targetList.stream()
                .map(this::fromPacient)
                .collect(Collectors.toList());
    }
}