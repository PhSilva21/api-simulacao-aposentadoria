package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;

public class MaleSimulationForContributionTimeAndPoints {

    private final PensionSimulationGateway pensionSimulationGateway;

    public MaleSimulationForContributionTimeAndPoints(PensionSimulationGateway pensionSimulationGateway) {
        this.pensionSimulationGateway = pensionSimulationGateway;
    }

    public String doMaleSimulationForContributionTimeAndPoints(String cpf){
        return pensionSimulationGateway.doMaleSimulationForContributionTimeAndPoints(cpf);
    }
}
