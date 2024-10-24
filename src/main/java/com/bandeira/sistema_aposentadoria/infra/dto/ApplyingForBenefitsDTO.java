package com.bandeira.sistema_aposentadoria.infra.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplyingForBenefitsDTO(


        @NotNull(message = "o nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @NotNull(message = "o cpf não pode ser nulo")
        @NotBlank(message = "O cpf não pode ser vazio")
        String cpf,


        @NotNull(message = "o orgão responsavel não pode ser nulo")
        @NotBlank(message = "O preço não pode ser vazio")
        String organResponsible,

        @NotNull(message = "o orgão expedidor não pode ser nulo")
        @NotBlank(message = "O orgão expedidor não pode ser vazio")
        String organShipper

) {
}
