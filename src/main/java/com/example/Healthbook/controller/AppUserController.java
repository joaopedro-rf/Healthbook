package com.example.Healthbook.controller;

import com.example.Healthbook.DTO.AppUserDTO;
import com.example.Healthbook.DTO.AppUserRegisterDTO;
import com.example.Healthbook.model.AppUser;
import com.example.Healthbook.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://healthclinic.vercel.app")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AppUserDTO appUserDTO) {
        return appUserService.login(appUserDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AppUserRegisterDTO appUserRegisterDTO) {
        return appUserService.register(appUserRegisterDTO);
    }


    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserByName(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return new ResponseEntity<>(appUserService.findAllUsers(), HttpStatus.OK);
    }

}
