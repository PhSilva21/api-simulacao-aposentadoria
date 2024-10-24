package com.bandeira.sistema_aposentadoria.infra.gateways;

import com.bandeira.sistema_aposentadoria.domain.User;
import com.bandeira.sistema_aposentadoria.domain.unums.Sex;
import com.bandeira.sistema_aposentadoria.infra.exceptions.UserNotFoundException;
import com.bandeira.sistema_aposentadoria.infra.feign.UserFeign;
import com.bandeira.sistema_aposentadoria.infra.persistence.Benefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PensionRepositoryGatewayTest {

    @InjectMocks
    PensionRepositoryGateway pensionRepositoryGateway;

    @Mock
    UserFeign userFeign;

    @Captor
    ArgumentCaptor<Benefit> benefitArgumentCaptor;

    @Nested
    @DisplayName("Do Male Age Simulation")
    class DoMaleAgeSimulation {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.MAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.MAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADO for eligible male user")
        void shouldReturnHabilitadoForEligibleMaleUser() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway.doMaleAgeSimulation(user.getCpf());

            assertEquals(response, "HABILITADO");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADO for non-eligible male user")
        void shouldReturnNaoHabilitadoForNonEligibleMaleUser() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway.doMaleAgeSimulation(user2.getCpf());

            assertEquals(response, "NÃO HABILITADO");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class,
                    () -> pensionRepositoryGateway.doMaleAgeSimulation(user.getCpf()));
        }
    }

    @Nested
    @DisplayName("Do Male Simulation for Contribution Time and Points")
    class DoMaleSimulationForContributionTimeAndPoints {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.MAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.WOMAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADO for male user with sufficient contribution")
        void shouldReturnHabilitadoForMaleUserWithSufficientContribution() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doMaleSimulationForContributionTimeAndPoints(user.getCpf());

            assertEquals(response, "HABILITADO");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADO for male user without sufficient contribution")
        void shouldReturnNaoHabilitadoForMaleUserWithoutSufficientContribution() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doMaleSimulationForContributionTimeAndPoints(user2.getCpf());

            assertEquals(response, "NÃO HABILITADO");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class, () -> pensionRepositoryGateway
                    .doMaleSimulationForContributionTimeAndPoints(user.getCpf()));
        }
    }

    @Nested
    @DisplayName("Do Male Simulation by Age and Contribution Time")
    class DoMaleSimulationByAgeAndContributionTime {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.MAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.WOMAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADO for male user with sufficient age and contribution time")
        void shouldReturnHabilitadoForMaleUserWithSufficientAgeAndContributionTime() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doMaleSimulationByAgeAndContributionTime(user.getCpf());

            assertEquals(response, "HABILITADO");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADO for male user without sufficient age and contribution time")
        void shouldReturnNaoHabilitadoForMaleUserWithoutSufficientAgeAndContributionTime() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doMaleSimulationByAgeAndContributionTime(user2.getCpf());

            assertEquals(response, "NÃO HABILITADO");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class, () -> pensionRepositoryGateway
                    .doMaleSimulationByAgeAndContributionTime(user.getCpf()));
        }
    }

    @Nested
    @DisplayName("Do Female Age Simulation")
    class DoFemaleAgeSimulation {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.WOMAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.MAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADA for eligible female user")
        void shouldReturnHabilitadaForEligibleFemaleUser() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleAgeSimulation(user.getCpf());

            assertEquals(response, "HABILITADA");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADA for non-eligible female user")
        void shouldReturnNaoHabilitadaForNonEligibleFemaleUser() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleAgeSimulation(user2.getCpf());

            assertEquals(response, "NÃO HABILITADA");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class, () -> pensionRepositoryGateway
                    .doFemaleAgeSimulation(user.getCpf()));
        }
    }

    @Nested
    @DisplayName("Do Female Simulation for Contribution Time and Points")
    class DoFemaleSimulationForContributionTimeAndPoints {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.WOMAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.MAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADA for female user with sufficient contribution")
        void shouldReturnHabilitadaForFemaleUserWithSufficientContribution() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleSimulationForContributionTimeAndPoints(user.getCpf());

            assertEquals(response, "HABILITADA");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADA for female user without sufficient contribution")
        void shouldReturnNaoHabilitadaForFemaleUserWithoutSufficientContribution() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleSimulationForContributionTimeAndPoints(user2.getCpf());

            assertEquals(response, "NÃO HABILITADA");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class, () -> pensionRepositoryGateway
                    .doFemaleSimulationForContributionTimeAndPoints(user.getCpf()));
        }
    }

    @Nested
    @DisplayName("Do Female Simulation by Age and Contribution Time")
    class DoFemaleSimulationByAgeAndContributionTime {

        User user = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 67, Sex.WOMAN,
                195, 41, 3, 15, 150);

        User user2 = new User(4L, "123.456.789-00", "João da Silva",
                "joao.silva@example.com", "senhaSegura123", 61, Sex.MAN,
                145, 28, 3, 15, 150);

        @Test
        @DisplayName("Should return HABILITADA for female user with sufficient age and contribution time")
        void shouldReturnHabilitadaForFemaleUserWithSufficientAgeAndContributionTime() {
            doReturn(user).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleSimulationByAgeAndContributionTime(user.getCpf());

            assertEquals(response, "HABILITADA");

            verify(userFeign, times(1)).findByCpf(user.getCpf());
        }

        @Test
        @DisplayName("Should return NÃO HABILITADA for female user without sufficient age and contribution time")
        void shouldReturnNaoHabilitadaForFemaleUserWithoutSufficientAgeAndContributionTime() {
            doReturn(user2).when(userFeign)
                    .findByCpf(user.getCpf());

            var response = pensionRepositoryGateway
                    .doFemaleSimulationByAgeAndContributionTime(user2.getCpf());

            assertEquals(response, "NÃO HABILITADA");

            verify(userFeign, times(1)).findByCpf(user2.getCpf());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user is not found")
        void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() {
            doReturn(null)
                    .when(userFeign).findByCpf(user.getCpf());

            assertThrows(UserNotFoundException.class, () -> pensionRepositoryGateway
                    .doFemaleSimulationByAgeAndContributionTime(user.getCpf()));
        }
    }
}
