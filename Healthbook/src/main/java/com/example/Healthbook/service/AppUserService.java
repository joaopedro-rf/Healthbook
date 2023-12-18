package com.example.Healthbook.service;

import com.example.Healthbook.DTO.AppUserDTO;
import com.example.Healthbook.DTO.AppUserRegisterDTO;
import com.example.Healthbook.DTO.LoginResponseDTO;
import com.example.Healthbook.model.AppUser;
import com.example.Healthbook.repositories.AppUserRepository;
import com.example.Healthbook.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private final AppUserRepository appUserRepository;
    private final TokenService tokenService;
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, TokenService tokenService, TokenService tokenService1) {
        this.appUserRepository = appUserRepository;
        this.tokenService = tokenService1;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> login(@RequestBody @Valid AppUserDTO appUserDTO) {
        // Procurar usuário por e-mail
        AppUser appUser = appUserRepository.findByEmail(appUserDTO.email());
        if (appUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Verificar a senha
        if (new BCryptPasswordEncoder().matches(appUserDTO.password(), appUser.getPassword())) {
            // Gerar token e retornar
            String token = tokenService.generateToken(appUser);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Object> register(@RequestBody AppUserRegisterDTO appUserRegisterDTO) {
        if (appUserRepository.findByEmail(appUserRegisterDTO.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(appUserRegisterDTO.password());

        AppUser newUser = new AppUser(appUserRegisterDTO.email(), encryptedPassword, appUserRegisterDTO.username());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.appUserRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findUserForLogin(String email, String password) {
        return Optional.ofNullable(appUserRepository.findByEmailAndPassword(email, password));
    }
    public boolean authenticateUser(String email, String password) {
        AppUser user = appUserRepository.findByEmailAndPassword(email, password);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}

