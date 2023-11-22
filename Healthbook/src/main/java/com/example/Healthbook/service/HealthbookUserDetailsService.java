package com.example.Healthbook.service;

//import com.example.Healthbook.repositories.HealthbookUserRepository;
import com.example.Healthbook.model.HealthbookUser;
import com.example.Healthbook.repositories.HealthbookUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthbookUserDetailsService implements UserDetailsService {
    private HealthbookUserRepository healthbookUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void UserService(HealthbookUserRepository healthbookUserRepository, PasswordEncoder passwordEncoder) {
        this.healthbookUserRepository = healthbookUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return Optional.ofNullable(healthbookUserRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Healthbook user not found"));
    }

    public List<HealthbookUser> findAllUsers(){
        return healthbookUserRepository.findAll();
    }

    @Transactional
    public HealthbookUser createUser(HealthbookUser healthbookUser) {
        healthbookUser.setPassword(passwordEncoder.encode(healthbookUser.getPassword()));
        return healthbookUserRepository.save(healthbookUser);
    }


}
