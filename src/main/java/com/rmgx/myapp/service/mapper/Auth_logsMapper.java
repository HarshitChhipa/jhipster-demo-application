package com.rmgx.myapp.service.mapper;

import com.rmgx.myapp.domain.*;
import com.rmgx.myapp.service.dto.Auth_logsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Auth_logs and its DTO Auth_logsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Auth_logsMapper extends EntityMapper<Auth_logsDTO, Auth_logs> {



    default Auth_logs fromId(String id) {
        if (id == null) {
            return null;
        }
        Auth_logs auth_logs = new Auth_logs();
        auth_logs.setId(id);
        return auth_logs;
    }
}
