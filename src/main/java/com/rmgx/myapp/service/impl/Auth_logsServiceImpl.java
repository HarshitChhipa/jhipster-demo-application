package com.rmgx.myapp.service.impl;

import com.rmgx.myapp.service.Auth_logsService;
import com.rmgx.myapp.domain.Auth_logs;
import com.rmgx.myapp.repository.Auth_logsRepository;
import com.rmgx.myapp.service.dto.Auth_logsDTO;
import com.rmgx.myapp.service.mapper.Auth_logsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Auth_logs.
 */
@Service
public class Auth_logsServiceImpl implements Auth_logsService {

    private final Logger log = LoggerFactory.getLogger(Auth_logsServiceImpl.class);

    private final Auth_logsRepository auth_logsRepository;

    private final Auth_logsMapper auth_logsMapper;

    public Auth_logsServiceImpl(Auth_logsRepository auth_logsRepository, Auth_logsMapper auth_logsMapper) {
        this.auth_logsRepository = auth_logsRepository;
        this.auth_logsMapper = auth_logsMapper;
    }

    /**
     * Save a auth_logs.
     *
     * @param auth_logsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Auth_logsDTO save(Auth_logsDTO auth_logsDTO) {
        log.debug("Request to save Auth_logs : {}", auth_logsDTO);

        Auth_logs auth_logs = auth_logsMapper.toEntity(auth_logsDTO);
        auth_logs = auth_logsRepository.save(auth_logs);
        return auth_logsMapper.toDto(auth_logs);
    }

    /**
     * Get all the auth_logs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Auth_logsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Auth_logs");
        return auth_logsRepository.findAll(pageable)
            .map(auth_logsMapper::toDto);
    }


    /**
     * Get one auth_logs by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Auth_logsDTO> findOne(String id) {
        log.debug("Request to get Auth_logs : {}", id);
        return auth_logsRepository.findById(id)
            .map(auth_logsMapper::toDto);
    }

    /**
     * Delete the auth_logs by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Auth_logs : {}", id);
        auth_logsRepository.deleteById(id);
    }
}
