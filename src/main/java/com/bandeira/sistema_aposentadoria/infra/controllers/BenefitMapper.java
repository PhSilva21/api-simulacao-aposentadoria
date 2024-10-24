package com.bandeira.sistema_aposentadoria.infra.controllers;

import com.bandeira.sistema_aposentadoria.domain.unums.StatusBenefits;
import com.bandeira.sistema_aposentadoria.infra.dto.ApplyingForBenefitsDTO;
import com.bandeira.sistema_aposentadoria.infra.persistence.Benefit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class BenefitMapper {

    public Benefit tuBenefitEntity(ApplyingForBenefitsDTO applyingForBenefitsDTO){
        return new Benefit(UUID.randomUUID(), applyingForBenefitsDTO.name(),
                applyingForBenefitsDTO.cpf(), applyingForBenefitsDTO.organResponsible(),
                applyingForBenefitsDTO.organShipper(), LocalDate.now(),
                StatusBenefits.UNDER_REVIEW);
    }
}
