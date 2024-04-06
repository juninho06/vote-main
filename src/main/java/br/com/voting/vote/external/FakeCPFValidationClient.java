package br.com.voting.vote.external;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FakeCPFValidationClient implements CPFValidationClient {

    private static final Random random = new Random();

    @Override
    public boolean isCPFValid(String cpf) {

        return random.nextBoolean();
    }

    @Override
    public boolean isUserAbleToVote(String cpf) {

        return random.nextBoolean();
    }
}
