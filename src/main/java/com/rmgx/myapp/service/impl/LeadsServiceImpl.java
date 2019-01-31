package com.rmgx.myapp.service.impl;

import com.rmgx.myapp.service.LeadsService;
import com.rmgx.myapp.domain.Leads;
import com.rmgx.myapp.repository.LeadsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Leads.
 */
@Service
public class LeadsServiceImpl implements LeadsService {

    private final Logger log = LoggerFactory.getLogger(LeadsServiceImpl.class);

    private final LeadsRepository leadsRepository;

    public LeadsServiceImpl(LeadsRepository leadsRepository) {
        this.leadsRepository = leadsRepository;
    }

    /**
     * Save a leads.
     *
     * @param leads the entity to save
     * @return the persisted entity
     */
    @Override
    public Leads save(Leads leads) {
        log.debug("Request to save Leads : {}", leads);
        return leadsRepository.save(leads);
    }

    /**
     * Get all the leads.
     *
     * @return the list of entities
     */
    @Override
    public List<Leads> findAll() {
        log.debug("Request to get all Leads");
        return leadsRepository.findAll();
    }


    /**
     * Get one leads by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Leads> findOne(String id) {
        log.debug("Request to get Leads : {}", id);
        return leadsRepository.findById(id);
    }

    /**
     * Delete the leads by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Leads : {}", id);
        leadsRepository.deleteById(id);
    }
}
