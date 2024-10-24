package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;

public class MaleAgeSimulation {

    private final PensionSimulationGateway pensionSimulationGateway;

    public MaleAgeSimulation(PensionSimulationGateway pensionSimulationGateway) {
        this.pensionSimulationGateway = pensionSimulationGateway;
    }

    public String doMaleAgeSimulation(String cpf){
        return pensionSimulationGateway.doMaleAgeSimulation(cpf);
    }
}
