package br.com.voting.vote.models;

public class VoteSessionRequest {
    private int durationMinutes = 1; // Valor padrão de 1 minuto



    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}


