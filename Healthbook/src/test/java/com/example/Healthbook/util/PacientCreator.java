package com.example.Healthbook.util;

import com.example.Healthbook.model.Pacient;

public class PacientCreator {

    public static Pacient createPacientToBeSaved(){
        return Pacient.builder()
                .name("Tester Pacient")
                .age(50)
                .address("Rua ali do lado")
                .phoneNumber("(11)11111-1111")
                .build();
    }
    public static Pacient createValidPacient(){
        return Pacient.builder()
                .id(1L)
                .name("Tester Pacient")
                .age(11)
                .address("Rua ali do lado")
                .phoneNumber("(11)11111-1111")
                .build();
    }
    public static Pacient createValidUpdatedPacient(){
        return Pacient.builder()
                .name("Pacient test")
                .age(25)
                .address("Aquela rua ali")
                .phoneNumber("(22)22222-2222")
                .build();
    }
}
