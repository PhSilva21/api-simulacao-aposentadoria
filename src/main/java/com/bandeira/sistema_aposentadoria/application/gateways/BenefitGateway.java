package com.bandeira.sistema_aposentadoria.application.gateways;


import com.bandeira.sistema_aposentadoria.domain.User;
import com.bandeira.sistema_aposentadoria.infra.dto.ApplyingForBenefitsDTO;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface BenefitGateway {

    void applyingForBenefits(ApplyingForBenefitsDTO applyingForBenefitsDTO) throws MessagingException, UnsupportedEncodingException;

    void findBenefitByCpf(String cpf);

    User findUserByCpf(String cpf);
}
