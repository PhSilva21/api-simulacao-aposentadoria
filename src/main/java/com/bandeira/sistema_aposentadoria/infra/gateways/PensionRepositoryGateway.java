package com.bandeira.sistema_aposentadoria.infra.gateways;

import com.bandeira.sistema_aposentadoria.application.gateways.PensionSimulationGateway;
import com.bandeira.sistema_aposentadoria.domain.User;
import com.bandeira.sistema_aposentadoria.domain.unums.Sex;
import com.bandeira.sistema_aposentadoria.infra.exceptions.UserNotFoundException;
import com.bandeira.sistema_aposentadoria.infra.feign.UserFeign;
import org.springframework.stereotype.Service;


public class PensionRepositoryGateway implements PensionSimulationGateway {

    private final UserFeign userFeign;

    public PensionRepositoryGateway(UserFeign userFeign) {
        this.userFeign = userFeign;
    }

    @Override
    public String doMaleAgeSimulation(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.MAN)) {
            if (user.getAge() >= 65 && user.getShortage() > 180
                    && user.getYearsContribution() >= 15) {
                return "HABILITADO";
            }
        }
        return "NÃO HABILITADO";
    }

    @Override
    public String doMaleSimulationForContributionTimeAndPoints(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.MAN)) {
            if (user.getAge() >= 65 && user.getShortage() > 180
                    && user.getYearsContribution() >= 35 && user.getPoints() >= 101) {
                return "HABILITADO";
            }
        }
        return "NÃO HABILITADO";
    }

    @Override
    public String doMaleSimulationByAgeAndContributionTime(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.MAN)) {
            if (user.getAge() >= 63 && user.getShortage() > 180
                    && user.getYearsContribution() >= 35) {
                return "HABILITADO";
            }
        }
        return "NÃO HABILITADO";
    }

    @Override
    public String doFemaleAgeSimulation(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.WOMAN)) {
            if (user.getAge() >= 62 && user.getShortage() > 180
                    && user.getYearsContribution() >= 15) {
                return "HABILITADA";
            }
        }
        return "NÃO HABILITADA";
    }

    @Override
    public String doFemaleSimulationForContributionTimeAndPoints(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.WOMAN)) {
            if (user.getShortage() > 180 && user.getYearsContribution() >= 30
                    && user.getPoints() >= 91) {
                return "HABILITADA";
            }
        }
        return "NÃO HABILITADA";
    }

    @Override
    public String doFemaleSimulationByAgeAndContributionTime(String cpf) {
        var user = findUserByCpf(cpf);

        if (user.getSex().equals(Sex.WOMAN)) {
            if (user.getShortage() > 180 && user.getYearsContribution() >= 30
                    && user.getAge() >= 58 && user.getPoints() >= 91) {
                return "HABILITADA";
            }
        }
        return "NÃO HABILITADA";
    }

    @Override
    public User findUserByCpf(String cpf){
        var user = userFeign.findByCpf(cpf);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
