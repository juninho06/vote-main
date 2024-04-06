package br.com.voting.vote.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgendaItem {
    private UUID id;
    private String title;
    private Map<UUID, String> votes = new HashMap<>();
    private LocalDateTime voteSessionEndTime;


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Map<UUID, String> getVotes() {
        return votes;
    }
    public void setVotes(Map<UUID, String> votes) {
        this.votes = votes;
    }
    public LocalDateTime getVoteSessionEndTime() {
        return voteSessionEndTime;
    }
    public void setVoteSessionEndTime(LocalDateTime voteSessionEndTime) {
        this.voteSessionEndTime = voteSessionEndTime;
    }
    public void addVote(UUID associateId, String vote) {

        throw new UnsupportedOperationException("Unimplemented method 'addVote'");
    }

}