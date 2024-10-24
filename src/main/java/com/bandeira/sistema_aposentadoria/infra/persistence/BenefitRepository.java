package com.bandeira.sistema_aposentadoria.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BenefitRepository extends JpaRepository<Benefit, UUID> {


    Benefit findByCpf(String cpf);
}
