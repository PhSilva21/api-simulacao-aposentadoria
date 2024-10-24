package com.bandeira.sistema_aposentadoria.domain;

import com.bandeira.sistema_aposentadoria.domain.unums.Sex;
import com.bandeira.sistema_aposentadoria.infra.persistence.Benefit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;

    private String cpf;

    private String name;

    private String email;

    private String password;

    private Integer age;

    private Sex sex;

    private Integer shortage;

    private Integer yearsContribution;

    private Integer monthsContribution;

    private Integer daysContribution;

    private Integer points;

    private List<Benefit> benefits = new ArrayList<>();

    public User(Long id, String cpf, String name, String email, String password, Integer age
            , Sex sex, Integer shortage, Integer yearsContribution, Integer monthsContribution
            , Integer daysContribution, Integer points) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.shortage = shortage;
        this.yearsContribution = yearsContribution;
        this.monthsContribution = monthsContribution;
        this.daysContribution = daysContribution;
        this.points = points;
    }
}
