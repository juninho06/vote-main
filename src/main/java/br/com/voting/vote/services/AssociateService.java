package br.com.voting.vote.services;

import br.com.voting.vote.dto.AssociateDTO;
import br.com.voting.vote.model.Associate;

import java.util.List;

public interface AssociateService {
    void createAssociate(AssociateDTO associateDTO);

    List<Associate> findAll();

    Associate findById(String id);

    void deleteAssociate(String id);

    void updateAssociate(AssociateDTO associateDTO, String id);
}
