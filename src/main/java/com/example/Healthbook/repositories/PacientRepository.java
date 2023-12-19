package com.example.Healthbook.repositories;

import com.example.Healthbook.model.Pacient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Page<Pacient> findByName(String username, Pageable pageable);

}
