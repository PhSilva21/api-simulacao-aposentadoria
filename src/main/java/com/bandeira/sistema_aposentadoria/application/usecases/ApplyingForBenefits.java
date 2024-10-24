package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.BenefitGateway;
import com.bandeira.sistema_aposentadoria.infra.dto.ApplyingForBenefitsDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

public class ApplyingForBenefits {

    private BenefitGateway benefitGateway;

    @Autowired
    public ApplyingForBenefits(BenefitGateway execute) {
        this.benefitGateway = execute;
    }

    public void execute(ApplyingForBenefitsDTO applyingForBenefits) throws MessagingException, UnsupportedEncodingException {
        benefitGateway.applyingForBenefits(applyingForBenefits);
    }
}
