package com.example.Healthbook.repositories;

import com.example.Healthbook.model.Pacient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Page<Pacient> findByName(String name, Pageable pageable);

}
