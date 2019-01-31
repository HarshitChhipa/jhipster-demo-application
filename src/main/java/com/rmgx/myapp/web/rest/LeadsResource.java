package com.rmgx.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rmgx.myapp.domain.Leads;
import com.rmgx.myapp.service.LeadsService;
import com.rmgx.myapp.web.rest.errors.BadRequestAlertException;
import com.rmgx.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Leads.
 */
@RestController
@RequestMapping("/api")
public class LeadsResource {

    private final Logger log = LoggerFactory.getLogger(LeadsResource.class);

    private static final String ENTITY_NAME = "leads";

    private final LeadsService leadsService;

    public LeadsResource(LeadsService leadsService) {
        this.leadsService = leadsService;
    }

    /**
     * POST  /leads : Create a new leads.
     *
     * @param leads the leads to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leads, or with status 400 (Bad Request) if the leads has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leads")
    @Timed
    public ResponseEntity<Leads> createLeads(@Valid @RequestBody Leads leads) throws URISyntaxException {
        log.debug("REST request to save Leads : {}", leads);
        if (leads.getId() != null) {
            throw new BadRequestAlertException("A new leads cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Leads result = leadsService.save(leads);
        return ResponseEntity.created(new URI("/api/leads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leads : Updates an existing leads.
     *
     * @param leads the leads to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leads,
     * or with status 400 (Bad Request) if the leads is not valid,
     * or with status 500 (Internal Server Error) if the leads couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leads")
    @Timed
    public ResponseEntity<Leads> updateLeads(@Valid @RequestBody Leads leads) throws URISyntaxException {
        log.debug("REST request to update Leads : {}", leads);
        if (leads.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Leads result = leadsService.save(leads);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leads.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leads : get all the leads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leads in body
     */
    @GetMapping("/leads")
    @Timed
    public List<Leads> getAllLeads() {
        log.debug("REST request to get all Leads");
        return leadsService.findAll();
    }

    /**
     * GET  /leads/:id : get the "id" leads.
     *
     * @param id the id of the leads to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leads, or with status 404 (Not Found)
     */
    @GetMapping("/leads/{id}")
    @Timed
    public ResponseEntity<Leads> getLeads(@PathVariable String id) {
        log.debug("REST request to get Leads : {}", id);
        Optional<Leads> leads = leadsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leads);
    }

    /**
     * DELETE  /leads/:id : delete the "id" leads.
     *
     * @param id the id of the leads to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leads/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeads(@PathVariable String id) {
        log.debug("REST request to delete Leads : {}", id);
        leadsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
