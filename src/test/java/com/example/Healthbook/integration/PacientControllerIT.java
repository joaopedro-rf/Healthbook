package com.example.Healthbook.integration;

import com.example.Healthbook.DTO.PacientDTO;
import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.repositories.PacientRepository;
import com.example.Healthbook.util.PacientCreator;
import com.example.Healthbook.util.PacientDTOCreator;
import com.example.Healthbook.wrapper.PageableResponse;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Log4j2
class PacientControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PacientRepository pacientRepository;

    @Test
    @DisplayName("list returns a list of Pacients inside a page object when successful")
    public void list_ReturnListOfPacientInsidePageObject_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());

        String expectedName = savedPacient.getName();
        int expectedAge = savedPacient.getAge();
        String expectedPhoneNumber = savedPacient.getPhoneNumber();
        String expectedAddress = savedPacient.getAddress();

        PageableResponse<Pacient> pacientPage = testRestTemplate.exchange("/pacients", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Pacient>>() {
                }).getBody();

        Assertions.assertThat(pacientPage).isNotNull();
        Assertions.assertThat(pacientPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(pacientPage.toList().get(0)
                .getName()).isEqualTo(expectedName);
        Assertions.assertThat(pacientPage.toList().get(0)
                .getAge()).isEqualTo(expectedAge);
        Assertions.assertThat(pacientPage.toList().get(0)
                .getAddress()).isEqualTo(expectedAddress);
        Assertions.assertThat(pacientPage.toList().get(0)
                .getPhoneNumber()).isEqualTo(expectedPhoneNumber);
    }

    @Test
    @DisplayName("listAll returns list of Pacients when successful")
    public void listAll_ReturnListOfPacient_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());

        String expectedName = savedPacient.getName();
        int expectedAge = savedPacient.getAge();
        String expectedPhoneNumber = savedPacient.getPhoneNumber();
        String expectedAddress = savedPacient.getAddress();

        List<Pacient> pacients = testRestTemplate.exchange("/pacients/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Pacient>>() {
                }).getBody();

         Assertions.assertThat(pacients).isNotNull();
        Assertions.assertThat(pacients)
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(pacients.get(0)
                .getName()).isEqualTo(expectedName);
        Assertions.assertThat(pacients.get(0)
                .getAge()).isEqualTo(expectedAge);
        Assertions.assertThat(pacients.get(0)
                .getAddress()).isEqualTo(expectedAddress);
        Assertions.assertThat(pacients.get(0)
                .getPhoneNumber()).isEqualTo(expectedPhoneNumber);
    }

    @Test
    @DisplayName("getById returns a Pacient when successful")
    void getById_ReturnPacient_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());
        Long expectedId = savedPacient.getId();

        Optional<Pacient> pacient = Optional.ofNullable(testRestTemplate.getForObject
                ("/pacients/{id}", Pacient.class, expectedId));

        Assertions.assertThat(pacient).isNotNull();
        Assertions.assertThat(pacient.get().getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("getByName returns a list of Pacient when successful")
    void getByName_ReturnListOfPacient_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());
        String expectedName = savedPacient.getName();


        String url = String.format("/pacients/find?name=%s" , expectedName);
        PageableResponse<Pacient> pacientPage = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Pacient>>() {
                }).getBody();

        Assertions.assertThat(pacientPage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pacientPage.getContent().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("getByName returns an empty list of Pacient when its not found")
    void getByName_ReturnEmptyListofPacient_WhenNotFound(){


        PageableResponse<Pacient> pacientPage = testRestTemplate.exchange("/pacients/find?name=%Ze", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Pacient>>() {
                }).getBody();

        Assertions.assertThat(pacientPage.toList())
                .isEmpty();
    }

    @Test
    @DisplayName("save returns a Pacient when successful")
    void save_ReturnPacient_WhenSuccessful(){
        PacientDTO pacientDTO = PacientDTOCreator.createValidPacientDTO();

        ResponseEntity<Pacient> pacientResponseEntity = testRestTemplate.postForEntity
                ("/pacients", pacientDTO, Pacient.class);
        Assertions.assertThat(pacientResponseEntity).isNotNull();
        Assertions.assertThat(pacientResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(pacientResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(pacientResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("update updates and returns a Pacient when successful")
    void update_ReturnPacient_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());
        savedPacient.setName("Novo nome");
        savedPacient.setAge(25);
        savedPacient.setPhoneNumber("(XX)XXXXX-XXXX");
        savedPacient.setAddress("Novo endereço");

        ResponseEntity<Void> pacientResponseEntity = testRestTemplate.exchange("/pacients/{id}", HttpMethod.PUT,
                new HttpEntity<>(savedPacient), Void.class, savedPacient.getId());

        Assertions.assertThat(pacientResponseEntity).isNotNull();
        Assertions.assertThat(pacientResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("delete removes a Pacient when successful")
    void delete_RemovePacient_WhenSuccessful(){
        Pacient savedPacient = pacientRepository.save(PacientCreator.createPacientToBeSaved());


        ResponseEntity<Void> pacientResponseEntity = testRestTemplate.exchange
                ("/pacients/{id}",HttpMethod.DELETE, null, Void.class, savedPacient.getId());

        Assertions.assertThat(pacientResponseEntity).isNotNull();
        Assertions.assertThat(pacientResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


}
