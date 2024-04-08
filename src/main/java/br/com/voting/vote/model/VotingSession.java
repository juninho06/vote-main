package br.com.voting.vote.model;

import br.com.voting.vote.enums.StatusVotingSession;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voting_session")
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_session")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startSession;

    @Column(name = "end_session")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endSession;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVotingSession status;


    public VotingSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public StatusVotingSession getStatus() {
        return status;
    }

    public void setStatus(StatusVotingSession status) {
        this.status = status;
    }
}
