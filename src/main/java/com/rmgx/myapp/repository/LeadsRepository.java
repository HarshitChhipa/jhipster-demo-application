package com.rmgx.myapp.repository;

import com.rmgx.myapp.domain.Leads;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Leads entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeadsRepository extends MongoRepository<Leads, String> {

}
