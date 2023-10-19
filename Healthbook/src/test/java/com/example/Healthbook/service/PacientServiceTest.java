package com.example.Healthbook.service;

import com.example.Healthbook.exception.BadRequestException;
import com.example.Healthbook.model.Pacient;
import com.example.Healthbook.repositories.PacientRepository;
import com.example.Healthbook.util.PacientCreator;
import com.example.Healthbook.util.PacientDTOCreator;
import jakarta.validation.ConstraintViolationException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class PacientServiceTest {

    @InjectMocks
    private PacientService pacientService;

    @Mock
    private PacientRepository pacientRepositoryMock;

    @BeforeEach
    void setup(){
        PageImpl<Pacient> pacientPage = new PageImpl<>(List.of(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientRepositoryMock.findAll(any(PageRequest.class)))
                .thenReturn(pacientPage);
        BDDMockito.when(pacientRepositoryMock.findAll())
                .thenReturn(List.of(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(PacientCreator.createValidPacient()));
        BDDMockito.when(pacientRepositoryMock.findByName(ArgumentMatchers.anyString(), any(Pageable.class)))
                .thenReturn(pacientPage);
        BDDMockito.when(pacientRepositoryMock.save(any(Pacient.class)))
                .thenReturn(PacientCreator.createValidPacient());
        BDDMockito.doNothing().when(pacientRepositoryMock).deleteById(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("listAll returns a list of Pacients inside a page object when successful")
    void listAll_ReturnListOfPacientInsidePageObject_WhenSuccessful(){
        String expectedName = PacientCreator.createValidPacient().getName();
        int expectedAge = PacientCreator.createValidPacient().getAge();
        String expectedPhoneNumber = PacientCreator.createValidPacient().getPhoneNumber();
        String expectedAddress = PacientCreator.createValidPacient().getAddress();
        Page<Pacient> pacientPage = pacientService.findAllPacients(PageRequest.of(1,1));

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
    @DisplayName("listAllNonPageable returns list of Pacients when successful")
    void listAllNonPageable_ReturnListOfPacient_WhenSuccessful(){
        String expectedName = PacientCreator.createValidPacient().getName();
        int expectedAge = PacientCreator.createValidPacient().getAge();
        String expectedPhoneNumber = PacientCreator.createValidPacient().getPhoneNumber();
        String expectedAddress = PacientCreator.createValidPacient().getAddress();

        List<Pacient> pacientList = pacientService.findAllPacientsNonPageable();


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
    @DisplayName("findPacientByIdOrThrowBadRequestException returns a Pacient when successful")
    void findPacientByIdOrThrowBadRequestException_ReturnPacient_WhenSuccessful(){
        Long expectedId = PacientCreator.createValidPacient().getId();

        Optional<Pacient> pacient = pacientService.findPacientByIdOrThrowBadRequestException(1L);

        Assertions.assertThat(pacient).isNotNull();
        Assertions.assertThat(pacient.get().getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findPacientByIdOrThrowBadRequestException throws BadRequestException when not successful")
    void findPacientByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenPacientIsNotFound(){
        BDDMockito.when(pacientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(()-> pacientService.findPacientByIdOrThrowBadRequestException(1L));

    }

    @Test
    @DisplayName("findByName returns a list of Pacient when successful")
    void findByName_ReturnListOfPacient_WhenSuccessful(){
        String expectedName = PacientCreator.createValidPacient().getName();

        Page<Pacient> pacients = pacientService.findPacientByName("Tester Pacient", Pageable.unpaged());

        Assertions.assertThat(pacients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pacients.getContent().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("getByName returns an empty list of Pacient when its not found")
    void findByName_ReturnEmptyListofPacient_WhenNotFound(){

        BDDMockito.when(pacientRepositoryMock.findByName(ArgumentMatchers.anyString(), any(Pageable.class)))
                .thenReturn(Page.empty());
        String expectedName = PacientCreator.createValidPacient().getName();

        Page<Pacient> pacients = pacientService.findPacientByName("pacient", null);

        Assertions.assertThat(pacients)
                .isNull();
    }

    @Test
    @DisplayName("save returns a Pacient when successful")
    void save_ReturnPacient_WhenSuccessful(){
        Pacient pacient = pacientService.createPacient(PacientDTOCreator.createToBeSavedPacientDTO());
        Assertions.assertThat(pacient).isNotNull().isEqualTo(PacientCreator.createValidPacient());
    }

    @Test
    @DisplayName("update updates and returns a Pacient when successful")
    void update_ReturnPacient_WhenSuccessful(){
        Pacient pacient = pacientService.updatePacient(PacientCreator.createValidPacient().getId(),
                PacientDTOCreator.createValidPacientDTO());
        Assertions.assertThat(pacient).isNotNull().isEqualTo(PacientCreator.createValidPacient());
    }

    @Test
    @DisplayName("delete removes a Pacient when successful")
    void delete_RemovePacient_WhenSuccessful(){

        Assertions.assertThatCode(() -> pacientService.deletePacientById(1L)).doesNotThrowAnyException();
    }

}