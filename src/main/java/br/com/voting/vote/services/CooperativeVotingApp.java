package br.com.voting.vote.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.voting.vote.models.AgendaItem;
import br.com.voting.vote.models.VoteSessionRequest;
import br.com.voting.vote.models.VoteRequest;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CooperativeVotingApp {


    private Map<UUID, AgendaItem> agendaItems = new HashMap<>();


    @PostMapping("/agenda")
    public ResponseEntity<Object> createAgendaItem(@RequestBody AgendaItem agendaItem) {
        UUID itemId = UUID.randomUUID();
        agendaItem.setId(itemId);
        agendaItems.put(itemId, agendaItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemId);
    }


    @PostMapping("/agenda/{itemId}/vote-session")
    public ResponseEntity<Object> openVoteSession(@PathVariable UUID itemId, @RequestBody VoteSessionRequest request) {
        AgendaItem agendaItem = agendaItems.get(itemId);
        if (agendaItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada");
        }


        LocalDateTime endTime = LocalDateTime.now().plusMinutes(request.getDurationMinutes());
        agendaItem.setVoteSessionEndTime(endTime);

        return ResponseEntity.ok("Sessão de votação aberta com sucesso");
    }


    @PostMapping("/agenda/{itemId}/vote")
    public ResponseEntity<Object> vote(@PathVariable UUID itemId, @RequestBody VoteRequest request) {
        AgendaItem agendaItem = agendaItems.get(itemId);
        if (agendaItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada");
        }

        if (agendaItem.getVoteSessionEndTime() == null || LocalDateTime.now().isAfter(agendaItem.getVoteSessionEndTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sessão de votação não está aberta ou expirou");
        }

        if (!request.getVote().equalsIgnoreCase("Sim") && !request.getVote().equalsIgnoreCase("Não")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voto inválido");
        }

        agendaItem.addVote(request.getAssociateId(), request.getVote());
        return ResponseEntity.ok("Voto registrado com sucesso");
    }


    @GetMapping("/agenda/{itemId}/result")
    public ResponseEntity<Object> getVoteResult(@PathVariable UUID itemId) {
        AgendaItem agendaItem = agendaItems.get(itemId);
        if (agendaItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada");
        }

        if (agendaItem.getVoteSessionEndTime() == null || LocalDateTime.now().isBefore(agendaItem.getVoteSessionEndTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sessão de votação ainda não terminou");
        }

        int yesVotes = 0;
        int noVotes = 0;

        for (String vote : agendaItem.getVotes().values()) {
            if (vote.equalsIgnoreCase("Sim")) {
                yesVotes++;
            } else {
                noVotes++;
            }
        }

        String result = yesVotes > noVotes ? "Aprovado" : "Reprovado";
        return ResponseEntity.ok("Resultado da votação: " + result);
    }

    public static void main(String[] args) {
        SpringApplication.run(CooperativeVotingApp.class, args);
    }
}
