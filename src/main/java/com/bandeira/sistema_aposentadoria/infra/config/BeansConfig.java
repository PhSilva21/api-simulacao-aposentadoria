package com.bandeira.sistema_aposentadoria.infra.config;

import com.bandeira.sistema_aposentadoria.application.gateways.BenefitGateway;
import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;
import com.bandeira.sistema_aposentadoria.application.gateways.SendingEmailsGateway;
import com.bandeira.sistema_aposentadoria.application.usecases.*;
import com.bandeira.sistema_aposentadoria.infra.controllers.BenefitMapper;
import com.bandeira.sistema_aposentadoria.infra.gateways.BenefitRepositoryGateway;
import com.bandeira.sistema_aposentadoria.infra.gateways.PensionRepositoryGateway;
import com.bandeira.sistema_aposentadoria.infra.gateways.SendingEmailService;
import com.bandeira.sistema_aposentadoria.infra.feign.UserFeign;
import com.bandeira.sistema_aposentadoria.infra.persistence.BenefitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class BeansConfig {

    @Bean
    public BenefitGateway benefitGateway(
            SendRequestConfirmationEmail sendRequestConfirmationEmail
            , BenefitRepository benefitRepository, UserFeign userFeign
            , BenefitMapper benefitMapper) {
        return new BenefitRepositoryGateway(sendRequestConfirmationEmail, benefitRepository
                , userFeign, benefitMapper);
    }

    @Bean
    public PensionSimulationGateway pensionSimulationGateway(UserFeign userFeign) {
        return new PensionRepositoryGateway(userFeign);
    }

    @Bean
    public SendingEmailsGateway sendingEmailsGateway(JavaMailSender emailSender) {
        return new SendingEmailService(emailSender);
    }

    @Bean
    public SendRequestConfirmationEmail sendRequestConfirmationEmail(SendingEmailsGateway sendingEmailsGateway) {
        return new SendRequestConfirmationEmail(sendingEmailsGateway);
    }

    @Bean
    public ApplyingForBenefits applyingForBenefits(BenefitGateway benefitGateway) {
        return new ApplyingForBenefits(benefitGateway);
    }

    @Bean
    public MaleAgeSimulation maleAgeSimulation(PensionSimulationGateway pensionSimulationGateway) {
        return new MaleAgeSimulation(pensionSimulationGateway);
    }

    @Bean
    public MaleSimulationByAgeAndContributionTime maleSimulationByAgeAndContributionTime(PensionSimulationGateway pensionSimulationGateway) {
        return new MaleSimulationByAgeAndContributionTime(pensionSimulationGateway);
    }

    @Bean
    public MaleSimulationForContributionTimeAndPoints maleSimulationForContributionTimeAndPoints(PensionSimulationGateway pensionSimulationGateway) {
        return new MaleSimulationForContributionTimeAndPoints(pensionSimulationGateway);
    }

    @Bean
    public FemaleAgeSimulation femaleAgeSimulation(PensionSimulationGateway pensionSimulationGateway) {
        return new FemaleAgeSimulation(pensionSimulationGateway);
    }

    @Bean
    public FemaleSimulationByAgeAndContributionTime femaleSimulationByAgeAndContributionTime(PensionSimulationGateway pensionSimulationGateway) {
        return new FemaleSimulationByAgeAndContributionTime(pensionSimulationGateway);
    }

    @Bean
    public FemaleSimulationForContributionTimeAndPoints femaleSimulationForContributionTimeAndPoints(PensionSimulationGateway pensionSimulationGateway) {
        return new FemaleSimulationForContributionTimeAndPoints(pensionSimulationGateway);
    }
}
