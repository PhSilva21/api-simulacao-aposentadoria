package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;

public class FemaleSimulationForContributionTimeAndPoints {

    private final PensionSimulationGateway pensionSimulationGateway;

    public FemaleSimulationForContributionTimeAndPoints(PensionSimulationGateway pensionSimulationGateway) {
        this.pensionSimulationGateway = pensionSimulationGateway;
    }

    public String doFemaleSimulationForContributionTimeAndPoints(String cpf){
        return pensionSimulationGateway.doFemaleSimulationForContributionTimeAndPoints(cpf);
    }

}
