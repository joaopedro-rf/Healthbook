package com.example.Healthbook.repositories;

import com.example.Healthbook.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByEmailAndPassword(String email, String password);
    AppUser findByEmail(String email);
}
