package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.BenefitGateway;
import com.bandeira.sistema_aposentadoria.domain.User;

public class FindUserByCpf {

    private final BenefitGateway benefitGateway;

    public FindUserByCpf(BenefitGateway benefitGateway) {
        this.benefitGateway = benefitGateway;
    }

    public User findUserByCpf(String cpf){
        return benefitGateway.findUserByCpf(cpf);
    }

}
