package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.BenefitGateway;

public class FindBenefitByCpf {

    private final BenefitGateway benefitGateway;

    public FindBenefitByCpf(BenefitGateway benefitGateway) {
        this.benefitGateway = benefitGateway;
    }

    public void findByCpf(String cpf){
        benefitGateway.findBenefitByCpf(cpf);
    }
}
