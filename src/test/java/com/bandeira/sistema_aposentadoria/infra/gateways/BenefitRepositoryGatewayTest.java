package com.bandeira.sistema_aposentadoria.infra.gateways;


import com.bandeira.sistema_aposentadoria.application.usecases.SendRequestConfirmationEmail;
import com.bandeira.sistema_aposentadoria.domain.User;
import com.bandeira.sistema_aposentadoria.domain.unums.Sex;

import com.bandeira.sistema_aposentadoria.domain.unums.StatusBenefits;
import com.bandeira.sistema_aposentadoria.infra.controllers.BenefitMapper;
import com.bandeira.sistema_aposentadoria.infra.dto.ApplyingForBenefitsDTO;
import com.bandeira.sistema_aposentadoria.infra.exceptions.BenefitAlreadyExists;
import com.bandeira.sistema_aposentadoria.infra.exceptions.UserNotFoundException;
import com.bandeira.sistema_aposentadoria.infra.feign.UserFeign;
import com.bandeira.sistema_aposentadoria.infra.persistence.Benefit;
import com.bandeira.sistema_aposentadoria.infra.persistence.BenefitRepository;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BenefitRepositoryGatewayTest {

    @InjectMocks
    BenefitRepositoryGateway benefitRepositoryGateway;

    @Mock
    SendRequestConfirmationEmail sendRequestConfirmationEmail;

    @Mock
    BenefitRepository benefitRepository;

    @Mock
    UserFeign userFeign;

    @Mock
    BenefitMapper benefitMapper;

    @Captor
    ArgumentCaptor<Benefit> benefitArgumentCaptor;

    @Nested
    @DisplayName("Applying For Benefits")
    class ApplyingForBenefits {

        User user = new User(19L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 30, Sex.MAN,
                2, 5, 3, 15, 150);

        ApplyingForBenefitsDTO applyingForBenefitsDTO = new ApplyingForBenefitsDTO(
                "João da Silva", "123.456.789-00", "IJST",
                "IJEM");

        Benefit benefit = new Benefit(UUID.randomUUID(), "Maria Oliveira", "987.654.321-00",
                "Ministério da Saúde", "Secretaria de Saúde",
                LocalDate.now(), StatusBenefits.UNDER_REVIEW);

        @Test
        @DisplayName("Should apply for benefits successfully")
        void applyingForBenefits() throws MessagingException, UnsupportedEncodingException {
            doReturn(user).when(userFeign)
                    .findByCpf(applyingForBenefitsDTO.cpf());
            doReturn(null)
                    .when(benefitRepository).findByCpf(applyingForBenefitsDTO.cpf());
            doReturn(benefit)
                    .when(benefitMapper).tuBenefitEntity(applyingForBenefitsDTO);
            doReturn(benefit)
                    .when(benefitRepository).save(benefitArgumentCaptor.capture());
            doNothing().when(sendRequestConfirmationEmail)
                    .sendRequestConfirmationEmail(user);

            benefitRepositoryGateway.applyingForBenefits(applyingForBenefitsDTO);

            var benefitCaptured = benefitArgumentCaptor.getValue();

            assertEquals(applyingForBenefitsDTO.name(), benefitCaptured.getName());
            assertEquals(applyingForBenefitsDTO.cpf(), benefitCaptured.getCpf());
            assertEquals(applyingForBenefitsDTO.organResponsible(), benefitCaptured.getOrganResponsible());
            assertEquals(applyingForBenefitsDTO.organShipper(), benefitCaptured.getOrganShipper());

            verify(userFeign, times(1))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(1))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(1))
                    .save(benefitArgumentCaptor.capture());
            verify(sendRequestConfirmationEmail, times(1))
                    .sendRequestConfirmationEmail(user);
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserNotFound() throws MessagingException, UnsupportedEncodingException {
            doReturn(null).when(userFeign)
                    .findByCpf(applyingForBenefitsDTO.cpf());

            assertThrows(UserNotFoundException.class,
                    () -> benefitRepositoryGateway.applyingForBenefits(applyingForBenefitsDTO));

            verify(userFeign, times(1))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(0))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(0))
                    .save(benefit);
            verify(sendRequestConfirmationEmail, times(0))
                    .sendRequestConfirmationEmail(user);
        }

        @Test
        @DisplayName("Should throw BenefitAlreadyExistsException when benefit already exists")
        void shouldThrowBenefitAlreadyExistsExceptionWhenBenefitExists() throws MessagingException, UnsupportedEncodingException {
            doReturn(user).when(userFeign)
                    .findByCpf(applyingForBenefitsDTO.cpf());
            doReturn(benefit)
                    .when(benefitRepository).findByCpf(applyingForBenefitsDTO.cpf());

            assertThrows(BenefitAlreadyExists.class,
                    () -> benefitRepositoryGateway.applyingForBenefits(applyingForBenefitsDTO));

            verify(userFeign, times(1))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(1))
                    .findByCpf(applyingForBenefitsDTO.cpf());
            verify(benefitRepository, times(0))
                    .save(benefit);
            verify(sendRequestConfirmationEmail, times(0))
                    .sendRequestConfirmationEmail(user);
        }
    }

    @Nested
    @DisplayName("Find Benefit By CPF")
    class FindBenefitByCpf {

        User user = new User(3L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 30, Sex.MAN,
                2, 5, 3, 15, 150);

        Benefit benefit = new Benefit(UUID.randomUUID(), "Maria Oliveira", "987.654.321-00",
                "Ministério da Saúde", "Secretaria de Saúde",
                LocalDate.now(), StatusBenefits.UNDER_REVIEW);

        @Test
        @DisplayName("Should find benefit by CPF successfully")
        void findBenefitByCpf() {
            doReturn(null)
                    .when(benefitRepository).findByCpf(user.getCpf());

            benefitRepositoryGateway.findBenefitByCpf(user.getCpf());

            verify(benefitRepository, times(1))
                    .findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should throw BenefitAlreadyExistsException when benefit is found")
        void shouldThrowBenefitAlreadyExistsExceptionWhenBenefitExists() {
            doReturn(benefit)
                    .when(benefitRepository).findByCpf(user.getCpf());

            assertThrows(BenefitAlreadyExists.class,
                    () -> benefitRepositoryGateway.findBenefitByCpf(user.getCpf()));

            verify(benefitRepository, times(1))
                    .findByCpf(user.getCpf());
        }
    }

    @Nested
    @DisplayName("Find User By CPF")
    class FindUserByCpf {

        User user = new User(2L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 30, Sex.MAN,
                2, 5, 3, 15, 150);

        @Test
        @DisplayName("Should find user by CPF successfully")
        void findUserSuccess() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = benefitRepositoryGateway.findUserByCpf(user.getCpf());

            assertNotNull(response);
            verify(userFeign, times(1))
                    .findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserNotFound() {
            doReturn(null).when(userFeign)
                    .findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class,
                    () -> benefitRepositoryGateway.findUserByCpf(user.getCpf()));

            verify(userFeign, times(1))
                    .findByCpf(user.getCpf());
        }
    }
}
