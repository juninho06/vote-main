package br.com.voting.vote.services.impl;

import br.com.voting.vote.dto.AssociateDTO;
import br.com.voting.vote.exception.NotFoundException;
import br.com.voting.vote.model.Associate;
import br.com.voting.vote.repository.AssociateRepository;
import br.com.voting.vote.services.AssociateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository repository;

    @Autowired
    public AssociateServiceImpl(AssociateRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createAssociate(AssociateDTO associateDTO) {
        Associate associate = new Associate();
        associate.setName(associateDTO.getName());
        associate.setCpf(associateDTO.getCpf());

        repository.save(associate);
    }

    @Override
    public List<Associate> findAll() {
        return repository.findAll();
    }

    @Override
    public Associate findById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() ->
                new NotFoundException("Associado não encontrado"));
    }

    @Override
    public void deleteAssociate(String id) {
        Associate associate = findById(id);

        if (associate != null) {
            repository.delete(associate);
        }
    }

    @Transactional
    @Override
    public void updateAssociate(AssociateDTO associateDTO, String id) {
        Associate associate = findById(id);
        if (associate != null) {
            associate.setCpf(associateDTO.getCpf());
            associate.setName(associateDTO.getName());
            repository.save(associate);
        }
    }
}
