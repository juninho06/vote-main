package br.com.voting.vote.external;

public interface CPFValidationClient {

    boolean isCPFValid(String cpf);

    boolean isUserAbleToVote(String cpf);
}
