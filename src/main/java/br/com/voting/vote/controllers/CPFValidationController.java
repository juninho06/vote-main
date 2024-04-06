package br.com.voting.vote.controllers;

import br.com.voting.vote.external.CPFValidationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cpf")
public class CPFValidationController {

    private final CPFValidationClient cpfValidationClient;

    @Autowired
    public CPFValidationController(CPFValidationClient cpfValidationClient) {
        this.cpfValidationClient = cpfValidationClient;
    }

    @GetMapping("/{cpf}/validate")
    public ResponseEntity<String> validateCPF(@PathVariable String cpf) {
        if (!cpfValidationClient.isCPFValid(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF inválido");
        }

        boolean ableToVote = cpfValidationClient.isUserAbleToVote(cpf);
        String result = ableToVote ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE";

        return ResponseEntity.ok(result);
    }
}
