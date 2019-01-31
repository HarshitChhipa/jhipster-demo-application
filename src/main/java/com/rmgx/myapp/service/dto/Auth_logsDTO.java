package com.rmgx.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Auth_logs entity.
 */
public class Auth_logsDTO implements Serializable {

    private String id;

    @NotNull
    private Integer user_id;

    @NotNull
    private String ip_address;

    @NotNull
    private String device;

    @NotNull
    private String location;

    @NotNull
    private String user_agent;

    @NotNull
    private String auth_type;

    @NotNull
    private String device_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getDevice_status() {
        return device_status;
    }

    public void setDevice_status(String device_status) {
        this.device_status = device_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Auth_logsDTO auth_logsDTO = (Auth_logsDTO) o;
        if (auth_logsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auth_logsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Auth_logsDTO{" +
            "id=" + getId() +
            ", user_id=" + getUser_id() +
            ", ip_address='" + getIp_address() + "'" +
            ", device='" + getDevice() + "'" +
            ", location='" + getLocation() + "'" +
            ", user_agent='" + getUser_agent() + "'" +
            ", auth_type='" + getAuth_type() + "'" +
            ", device_status='" + getDevice_status() + "'" +
            "}";
    }
}
