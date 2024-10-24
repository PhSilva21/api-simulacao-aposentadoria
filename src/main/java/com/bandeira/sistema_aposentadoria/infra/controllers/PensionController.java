package com.bandeira.sistema_aposentadoria.infra.controllers;

import com.bandeira.sistema_aposentadoria.application.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/pension")
public class PensionController {

    private final MaleAgeSimulation maleAgeSimulation;

    private final MaleSimulationByAgeAndContributionTime maleSimulationByAgeAndContributionTime;

    private final MaleSimulationForContributionTimeAndPoints maleSimulationForContributionTimeAndPoints;

    private final FemaleAgeSimulation femaleAgeSimulation;

    private final FemaleSimulationByAgeAndContributionTime femaleSimulationByAgeAndContributionTime;

    private final FemaleSimulationForContributionTimeAndPoints femaleSimulationForContributionTimeAndPoints;


    public PensionController(MaleAgeSimulation maleAgeSimulation, MaleSimulationByAgeAndContributionTime
            maleSimulationByAgeAndContributionTime, MaleSimulationForContributionTimeAndPoints
            maleSimulationForContributionTimeAndPoints, FemaleAgeSimulation femaleAgeSimulation
            , FemaleSimulationByAgeAndContributionTime femaleSimulationByAgeAndContributionTime
            , FemaleSimulationForContributionTimeAndPoints femaleSimulationForContributionTimeAndPoints) {
        this.maleAgeSimulation = maleAgeSimulation;
        this.maleSimulationByAgeAndContributionTime = maleSimulationByAgeAndContributionTime;
        this.maleSimulationForContributionTimeAndPoints = maleSimulationForContributionTimeAndPoints;
        this.femaleAgeSimulation = femaleAgeSimulation;
        this.femaleSimulationByAgeAndContributionTime = femaleSimulationByAgeAndContributionTime;
        this.femaleSimulationForContributionTimeAndPoints = femaleSimulationForContributionTimeAndPoints;
    }

    @Operation(description = "Operação para simular aposentadoria por idade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/male/age-simulation")
    public ResponseEntity<String> doMaleAgeSimulation(
                            @RequestParam @Param("cpf") String cpf) {
        var response = maleAgeSimulation.doMaleAgeSimulation(cpf);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para simular aposentadoria por tempo de contribuição e pontos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/male/contribution-points-simulation")
    public ResponseEntity<String> doMaleSimulationForContributionTimeAndPoints(
                            @RequestParam @Param("cpf") String cpf) {
        var response = maleSimulationForContributionTimeAndPoints
                .doMaleSimulationForContributionTimeAndPoints(cpf);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para simular aposentadoria por idade e tempo de contribuição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/male/age-contribution-simulation")
    public ResponseEntity<String> doMaleSimulationByAgeAndContributionTime(
                            @RequestParam @Param("cpf") String cpf) {
        var response = maleSimulationByAgeAndContributionTime
                .doMaleSimulationByAgeAndContributionTime(cpf);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para simular aposentadoria por idade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/female/age-simulation")
    public ResponseEntity<String> doFemaleAgeSimulation(
                            @RequestParam @Param("cpf") String cpf) {
        var response = femaleAgeSimulation.doFemaleAgeSimulation(cpf);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para simular aposentadoria por tempo de contribuição e pontos")    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/female/contribution-points-simulation")
    public ResponseEntity<String> doFemaleSimulationForContributionTimeAndPoints(
            @RequestParam @Param("cpf") String cpf) {
        var response = femaleSimulationForContributionTimeAndPoints
                .doFemaleSimulationForContributionTimeAndPoints(cpf);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para simular aposentadoria por idade e tempo de contribuição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aposentadoria simulada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/female/age-contribution-simulation")
    public ResponseEntity<String> doFemaleSimulationByAgeAndContributionTime(
            @RequestParam @Param("cpf") String cpf) {
        var response = femaleSimulationByAgeAndContributionTime.doFemaleSimulationByAgeAndContributionTime(cpf);
        return ResponseEntity.ok().body(response);
    }

}
