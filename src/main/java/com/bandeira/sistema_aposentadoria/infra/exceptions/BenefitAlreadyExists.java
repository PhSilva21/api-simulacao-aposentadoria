package com.bandeira.sistema_aposentadoria.infra.exceptions;

public class BenefitAlreadyExists extends RuntimeException{


    public BenefitAlreadyExists(){
        super("Já existe um benefício associado a este cpf");
    }

    public BenefitAlreadyExists(String message){
        super(message);
    }
}
