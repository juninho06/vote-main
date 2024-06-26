package br.com.voting.vote.repository;

import br.com.voting.vote.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
}
