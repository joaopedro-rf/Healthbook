package com.example.Healthbook.service;


import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.exception.BadRequestException;
import com.example.Healthbook.mapper.PacientMapper;
import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.repositories.PacientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PacientService{
    private final PacientRepository pacientRepository;


    @Autowired
    public PacientService(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    public Page<Pacient> findAllPacients(Pageable pageable){


        //List<PacientDTO> pacientDTO = PacientMapper.INSTANCE.toPacients(pacientRepository.findAll());
        //Quando adicionar roles ADMIN usar a linha acima
        return pacientRepository.findAll(pageable);
    }

    public Optional<Pacient> findPacientByIdOrThrowBadRequestException(Long id){

        return Optional.ofNullable(pacientRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Pacient not found")));
    }

    public Page<Pacient> findPacientByName(String name, Pageable pageable){

        return pacientRepository.findByName(name, pageable);
    }

    @Transactional
    public Pacient createPacient(PacientDTO pacientDTO) {
        /*
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new BadRequestException(errors.toString());
        }
        */

        return pacientRepository.save(PacientMapper.INSTANCE.fromPacient(pacientDTO));
    }

    public void deletePacientById(Long id){
        pacientRepository.deleteById(id);
    }

    public Pacient updatePacient(Long id, PacientDTO pacientDTO ){
        findPacientByIdOrThrowBadRequestException(id);
        Pacient pacient = PacientMapper.INSTANCE.fromPacient(pacientDTO);
        pacient.setId(id);

        /*
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new BadRequestException(errors.toString());
        }
        */

        return pacientRepository.save(PacientMapper.INSTANCE.fromPacient(pacientDTO));


    }

}
