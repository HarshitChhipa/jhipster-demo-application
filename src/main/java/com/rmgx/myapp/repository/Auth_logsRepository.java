package com.rmgx.myapp.repository;

import com.rmgx.myapp.domain.Auth_logs;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Auth_logs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Auth_logsRepository extends MongoRepository<Auth_logs, String> {

}
