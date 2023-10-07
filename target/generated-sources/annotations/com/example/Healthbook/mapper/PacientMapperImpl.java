package com.example.Healthbook.mapper;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-04T18:02:56-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class PacientMapperImpl extends PacientMapper {

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

    @Override
    public List<PacientDTO> toPacients(List<Pacient> pacient) {
        if ( pacient == null ) {
            return null;
        }

        List<PacientDTO> list = new ArrayList<PacientDTO>( pacient.size() );
        for ( Pacient pacient1 : pacient ) {
            list.add( toPacient( pacient1 ) );
        }

        return list;
    }
}
