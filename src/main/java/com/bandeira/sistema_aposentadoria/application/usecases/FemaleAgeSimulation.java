package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;

public class FemaleAgeSimulation {

    private final PensionSimulationGateway pensionSimulationGateway;

    public FemaleAgeSimulation(PensionSimulationGateway pensionSimulationGateway) {
        this.pensionSimulationGateway = pensionSimulationGateway;
    }

    public String doFemaleAgeSimulation(String cpf){
        return pensionSimulationGateway.doFemaleAgeSimulation(cpf);
    }
}
