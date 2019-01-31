package com.rmgx.myapp.service;

import com.rmgx.myapp.service.dto.Auth_logsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Auth_logs.
 */
public interface Auth_logsService {

    /**
     * Save a auth_logs.
     *
     * @param auth_logsDTO the entity to save
     * @return the persisted entity
     */
    Auth_logsDTO save(Auth_logsDTO auth_logsDTO);

    /**
     * Get all the auth_logs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Auth_logsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" auth_logs.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Auth_logsDTO> findOne(String id);

    /**
     * Delete the "id" auth_logs.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
