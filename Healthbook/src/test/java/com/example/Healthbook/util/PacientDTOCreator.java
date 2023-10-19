package com.example.Healthbook.util;

import com.example.Healthbook.DTO.PacientDTO;

public class PacientDTOCreator {
    public static PacientDTO createToBeSavedPacientDTO(){
        return PacientDTO.builder()
                .name(PacientCreator.createPacientToBeSaved().getName())
                .age(PacientCreator.createPacientToBeSaved().getAge())
                .address(PacientCreator.createPacientToBeSaved().getAddress())
                .phoneNumber(PacientCreator.createPacientToBeSaved().getPhoneNumber())
                .build();
    }
    public static PacientDTO createValidPacientDTO(){
        return PacientDTO.builder()
                .name(PacientCreator.createValidPacient().getName())
                .age(PacientCreator.createValidPacient().getAge())
                .address(PacientCreator.createValidPacient().getAddress())
                .phoneNumber(PacientCreator.createValidPacient().getPhoneNumber())
                .build();
    }


}
