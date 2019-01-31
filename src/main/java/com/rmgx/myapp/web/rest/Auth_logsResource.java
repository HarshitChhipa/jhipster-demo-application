package com.rmgx.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rmgx.myapp.service.Auth_logsService;
import com.rmgx.myapp.web.rest.errors.BadRequestAlertException;
import com.rmgx.myapp.web.rest.util.HeaderUtil;
import com.rmgx.myapp.web.rest.util.PaginationUtil;
import com.rmgx.myapp.service.dto.Auth_logsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Auth_logs.
 */
@RestController
@RequestMapping("/api")
public class Auth_logsResource {

    private final Logger log = LoggerFactory.getLogger(Auth_logsResource.class);

    private static final String ENTITY_NAME = "auth_logs";

    private final Auth_logsService auth_logsService;

    public Auth_logsResource(Auth_logsService auth_logsService) {
        this.auth_logsService = auth_logsService;
    }

    /**
     * POST  /auth-logs : Create a new auth_logs.
     *
     * @param auth_logsDTO the auth_logsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auth_logsDTO, or with status 400 (Bad Request) if the auth_logs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/auth-logs")
    @Timed
    public ResponseEntity<Auth_logsDTO> createAuth_logs(@Valid @RequestBody Auth_logsDTO auth_logsDTO) throws URISyntaxException {
        log.debug("REST request to save Auth_logs : {}", auth_logsDTO);
        if (auth_logsDTO.getId() != null) {
            throw new BadRequestAlertException("A new auth_logs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Auth_logsDTO result = auth_logsService.save(auth_logsDTO);
        return ResponseEntity.created(new URI("/api/auth-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /auth-logs : Updates an existing auth_logs.
     *
     * @param auth_logsDTO the auth_logsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auth_logsDTO,
     * or with status 400 (Bad Request) if the auth_logsDTO is not valid,
     * or with status 500 (Internal Server Error) if the auth_logsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/auth-logs")
    @Timed
    public ResponseEntity<Auth_logsDTO> updateAuth_logs(@Valid @RequestBody Auth_logsDTO auth_logsDTO) throws URISyntaxException {
        log.debug("REST request to update Auth_logs : {}", auth_logsDTO);
        if (auth_logsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Auth_logsDTO result = auth_logsService.save(auth_logsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auth_logsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /auth-logs : get all the auth_logs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auth_logs in body
     */
    @GetMapping("/auth-logs")
    @Timed
    public ResponseEntity<List<Auth_logsDTO>> getAllAuth_logs(Pageable pageable) {
        log.debug("REST request to get a page of Auth_logs");
        Page<Auth_logsDTO> page = auth_logsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/auth-logs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /auth-logs/:id : get the "id" auth_logs.
     *
     * @param id the id of the auth_logsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auth_logsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/auth-logs/{id}")
    @Timed
    public ResponseEntity<Auth_logsDTO> getAuth_logs(@PathVariable String id) {
        log.debug("REST request to get Auth_logs : {}", id);
        Optional<Auth_logsDTO> auth_logsDTO = auth_logsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auth_logsDTO);
    }

    /**
     * DELETE  /auth-logs/:id : delete the "id" auth_logs.
     *
     * @param id the id of the auth_logsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/auth-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuth_logs(@PathVariable String id) {
        log.debug("REST request to delete Auth_logs : {}", id);
        auth_logsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
