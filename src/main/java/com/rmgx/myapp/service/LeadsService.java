package com.rmgx.myapp.service;

import com.rmgx.myapp.domain.Leads;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Leads.
 */
public interface LeadsService {

    /**
     * Save a leads.
     *
     * @param leads the entity to save
     * @return the persisted entity
     */
    Leads save(Leads leads);

    /**
     * Get all the leads.
     *
     * @return the list of entities
     */
    List<Leads> findAll();


    /**
     * Get the "id" leads.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Leads> findOne(String id);

    /**
     * Delete the "id" leads.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
