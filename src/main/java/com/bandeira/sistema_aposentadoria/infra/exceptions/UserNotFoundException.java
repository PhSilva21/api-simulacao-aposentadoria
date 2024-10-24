package com.bandeira.sistema_aposentadoria.infra.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Pessoa não cadastrada");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
