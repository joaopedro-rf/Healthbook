package com.example.Healthbook.controller;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.service.PacientService;
import com.example.Healthbook.util.DateUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacients")
@CrossOrigin(origins = "https://healthclinic.vercel.app")
public class PacientController {
    @Autowired
    private PacientService pacientService;

    @GetMapping
    public ResponseEntity<Page<Pacient>> getAllPacients(Pageable pageable){
        return new ResponseEntity<>(pacientService.findAllPacients(pageable), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<Pacient>> getPacientByName(@RequestParam String name, Pageable pageable){
        Page<Pacient> pacient = pacientService.findPacientByName(name, pageable);
        return new ResponseEntity<>(pacient, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pacient>> getAllPacients(){
        return new ResponseEntity<>(pacientService.findAllPacientsNonPageable(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pacient>> getPacientById(@PathVariable Long id){
        return ResponseEntity.ok(pacientService.findPacientByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Pacient> savePacient(@Valid @RequestBody PacientDTO pacientDTO){
        Pacient newPacient = pacientService.createPacient(pacientDTO);
        return new ResponseEntity<>(newPacient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long id){
        pacientService.deletePacientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacient> updatePacient(@PathVariable Long id,@Valid @RequestBody PacientDTO pacientDTO)
    {
        Pacient pacient = pacientService.updatePacient(id,pacientDTO);

        return new ResponseEntity<>(pacient, HttpStatus.OK);
    }


}
