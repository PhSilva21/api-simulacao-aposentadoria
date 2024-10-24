package com.bandeira.sistema_aposentadoria.infra.controllers;


import com.bandeira.sistema_aposentadoria.application.usecases.ApplyingForBenefits;
import com.bandeira.sistema_aposentadoria.infra.dto.ApplyingForBenefitsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/v1/benefits")
public class BenefitController {

    private final ApplyingForBenefits applyingForBenefitsUseCase;


    public BenefitController(ApplyingForBenefits applyingForBenefitsUseCase) {
        this.applyingForBenefitsUseCase = applyingForBenefitsUseCase;
    }

    @Operation(description = "Operação para solicitar um benefício")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Benefício solicitado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Void> applyingForBenefits(@RequestBody @Valid ApplyingForBenefitsDTO request) throws MessagingException, UnsupportedEncodingException {
        applyingForBenefitsUseCase.execute(request);
        return ResponseEntity.ok().build();
    }


}
