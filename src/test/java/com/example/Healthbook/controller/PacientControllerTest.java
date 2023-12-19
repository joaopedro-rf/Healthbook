package com.example.Healthbook.controller;

import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.service.PacientService;
import com.example.Healthbook.util.PacientCreator;
import com.example.Healthbook.util.PacientDTOCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(SpringExtension.class)
class PacientControllerTest {
    @InjectMocks
    private PacientController pacientController;

    @Mock
    private PacientService pacientServiceMock;

    @BeforeEach
    void setup(){
        PageImpl<Pacient> pacientPage = new PageImpl<>(List.of(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientServiceMock.findAllPacients(any()))
                .thenReturn(pacientPage);
        BDDMockito.when(pacientServiceMock.findAllPacientsNonPageable())
                .thenReturn(List.of(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientServiceMock.findPacientByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientServiceMock.findPacientByName(ArgumentMatchers.anyString(), any(Pageable.class)))
                .thenReturn(pacientPage);
        BDDMockito.when(pacientServiceMock.createPacient(any()))
                .thenReturn(PacientCreator.createValidPacient());
        BDDMockito.when(pacientServiceMock.updatePacient(ArgumentMatchers.anyLong(), any()))
                .thenReturn(PacientCreator.createValidPacient());

        BDDMockito.doNothing().when(pacientServiceMock).deletePacientById(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("list returns a list of Pacients inside a page object when successful")
    void list_ReturnListOfPacientInsidePageObject_WhenSuccessful(){
        String expectedName = PacientCreator.createValidPacient().getName();
        int expectedAge = PacientCreator.createValidPacient().getAge();
        String expectedPhoneNumber = PacientCreator.createValidPacient().getPhoneNumber();
        String expectedAddress = PacientCreator.createValidPacient().getAddress();


        Page<Pacient> pacientPage = pacientController.getAllPacients(null).getBody();

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
    void listAll_ReturnListOfPacient_WhenSuccessful(){
        String expectedName = PacientCreator.createValidPacient().getName();
        int expectedAge = PacientCreator.createValidPacient().getAge();
        String expectedPhoneNumber = PacientCreator.createValidPacient().getPhoneNumber();
        String expectedAddress = PacientCreator.createValidPacient().getAddress();

        List<Pacient> pacientList = pacientController.getAllPacients().getBody();


        Assertions.assertThat(pacientList).isNotNull();
        Assertions.assertThat(pacientList)
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(pacientList.get(0)
                .getName()).isEqualTo(expectedName);
        Assertions.assertThat(pacientList.get(0)
                .getAge()).isEqualTo(expectedAge);
        Assertions.assertThat(pacientList.get(0)
                .getAddress()).isEqualTo(expectedAddress);
        Assertions.assertThat(pacientList.get(0)
                .getPhoneNumber()).isEqualTo(expectedPhoneNumber);
    }

    @Test
    @DisplayName("getById returns a Pacient when successful")
    void getById_ReturnPacient_WhenSuccessful(){
        Long expectedId = PacientCreator.createValidPacient().getId();

        Optional<Pacient> pacient = pacientController.getPacientById(1L).getBody();

        Assertions.assertThat(pacient).isNotNull();
        Assertions.assertThat(pacient.get().getId()).isNotNull().isEqualTo(expectedId);
    }

        @Test
        @DisplayName("getByName returns a list of Pacient when successful")
        void getByName_ReturnListOfPacient_WhenSuccessful(){
            String expectedName = PacientCreator.createValidPacient().getName();

            Page<Pacient> pacients = pacientController.getPacientByName("Tester Pacient", Pageable.unpaged()).getBody();

            Assertions.assertThat(pacients)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(1);

            Assertions.assertThat(pacients.getContent().get(0).getName()).isEqualTo(expectedName);

        }

    @Test
    @DisplayName("getByName returns an empty list of Pacient when its not found")
    void getByName_ReturnEmptyListofPacient_WhenNotFound(){

        BDDMockito.when(pacientServiceMock.findPacientByName(ArgumentMatchers.anyString(), any(Pageable.class)))
                .thenReturn(Page.empty());
        String expectedName = PacientCreator.createValidPacient().getName();

        Page<Pacient> pacients = pacientController.getPacientByName("pacient", null).getBody();

        Assertions.assertThat(pacients)
                .isNull();
    }

    @Test
    @DisplayName("save returns a Pacient when successful")
    void save_ReturnPacient_WhenSuccessful(){
        Pacient pacient = pacientController.savePacient(PacientDTOCreator.createToBeSavedPacientDTO()).getBody();
        Assertions.assertThat(pacient).isNotNull().isEqualTo(PacientCreator.createValidPacient());
    }

    @Test
    @DisplayName("update updates and returns a Pacient when successful")
    void update_ReturnPacient_WhenSuccessful(){
        Pacient pacient = pacientController.updatePacient(PacientCreator.createValidPacient().getId(),
                PacientDTOCreator.createValidPacientDTO()).getBody();
        Assertions.assertThat(pacient).isNotNull().isEqualTo(PacientCreator.createValidPacient());
    }

    @Test
    @DisplayName("delete removes a Pacient when successful")
    void delete_RemovePacient_WhenSuccessful(){

        Assertions.assertThatCode(() -> pacientController.deletePacient(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = pacientController.deletePacient(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}