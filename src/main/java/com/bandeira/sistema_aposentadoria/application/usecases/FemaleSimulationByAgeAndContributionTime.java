package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;

public class FemaleSimulationByAgeAndContributionTime {

    private final PensionSimulationGateway pensionSimulationGateway;

    public FemaleSimulationByAgeAndContributionTime(PensionSimulationGateway pensionSimulationGateway) {
        this.pensionSimulationGateway = pensionSimulationGateway;
    }

    public String doFemaleSimulationByAgeAndContributionTime(String cpf){
        return pensionSimulationGateway.doFemaleSimulationByAgeAndContributionTime(cpf);
    }
}


