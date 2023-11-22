package com.example.Healthbook.controller;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.HealthbookUser;
import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.service.HealthbookUserDetailsService;
import com.example.Healthbook.service.PacientService;
import com.example.Healthbook.util.DateUtil;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class HealthbookUserController {

    @Autowired
    private HealthbookUserDetailsService healthbookUserDetailsService;

    @GetMapping("/info")
    public ResponseEntity<String> getUserInfo(Authentication authentication) {
        String username = authentication.getClass().getName();
        return ResponseEntity.ok("Info about user: " + username);
    }

    @PostMapping
    public ResponseEntity<HealthbookUser> saveUser(@Valid @RequestBody HealthbookUser healthbookUser){
        return new ResponseEntity<>(healthbookUserDetailsService.createUser(healthbookUser), HttpStatus.CREATED);
    }

}
