package com.example.Healthbook.repositories;

import com.example.Healthbook.model.HealthbookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthbookUserRepository extends JpaRepository<HealthbookUser, Long> {
    HealthbookUser findByUsername(String username);
}
