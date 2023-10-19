package com.example.Healthbook.repositories;

import com.example.Healthbook.model.Pacient;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import static com.example.Healthbook.util.PacientCreator.createPacientToBeSaved;

@DisplayName("Tests Pacient Repository")
@DataJpaTest
@Log4j2
class PacientRepositoryTest {

    @Autowired
    private PacientRepository pacientRepository;
    private Pageable pageable;

    @DisplayName("Creates and saves Pacient when successful")
    @Test
    public void createAndPersistPacient_WhenSuccessful()
    {
        Pacient pacientToBeSaved = createPacientToBeSaved();
        Pacient savedPacient = this.pacientRepository.save(pacientToBeSaved);
        Assertions.assertThat(savedPacient).isNotNull();
        Assertions.assertThat(savedPacient.getId()).isNotNull();
        Assertions.assertThat(savedPacient.getName()).isEqualTo(pacientToBeSaved.getName());
    }

    @DisplayName("Updates and saves Pacient when successful")
    @Test
    public void createAndUpdatePacient_WhenSuccessful()
    {
        Pacient pacientToBeSaved = createPacientToBeSaved();
        Pacient savedPacient = this.pacientRepository.save(pacientToBeSaved);
        savedPacient.setName("Pacient test");
        savedPacient.setAge(25);
        savedPacient.setAddress("Aquela rua ali");
        savedPacient.setPhoneNumber("(XX)XXXXX-XXXX");

        Pacient updatedPacient = this.pacientRepository.save(savedPacient);

        Assertions.assertThat(updatedPacient).isNotNull();
        Assertions.assertThat(updatedPacient.getId()).isNotNull();
        Assertions.assertThat(updatedPacient.getName()).isEqualTo(savedPacient.getName());
    }

    @DisplayName("Deletes and removes Pacient when successful")
    @Test
    public void deleteAndRemovePacient_WhenSuccessful()
    {
        Pacient pacientToBeSaved = createPacientToBeSaved();
        Pacient savedPacient = this.pacientRepository.save(pacientToBeSaved);

        this.pacientRepository.delete(savedPacient);

        Optional<Pacient> pacientOptional = this.pacientRepository.findById(savedPacient.getId());

        Assertions.assertThat(pacientOptional.isEmpty());

    }

    @DisplayName("Finds a Pacient by name and return a list of pacients when successful")
    @Test
    public void findByName_ReturnListOfPacient_WhenSuccessful()
    {
        Pacient pacientToBeSaved = createPacientToBeSaved();
        Pacient savedPacient = this.pacientRepository.save(pacientToBeSaved);

        String namePacient = savedPacient.getName();

        Page<Pacient> pacients = this.pacientRepository.findByName(namePacient,pageable);

        Assertions.assertThat(pacients)
                .isNotEmpty()
                .contains(savedPacient);

    }

    @DisplayName("Finds a Pacient by name returns empty if its not found")
    @Test
    public void findByName_ReturnEmptyList_WhenPacientNotFound()
    {
        Page<Pacient> pacients = this.pacientRepository.findByName("Should not find", pageable);
        Assertions.assertThat(pacients).isEmpty();

    }

    @DisplayName("Creates and throws ConstraintViolationException when a field is empty")
    @Test
    public void create_ThrowsConstraintViolationException_WhenFieldIsEmpty()
    {
        Pacient pacient = new Pacient();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> this.pacientRepository.save(pacient))
                .withMessageContaining("Pacient's name must not be empty");

    }

}