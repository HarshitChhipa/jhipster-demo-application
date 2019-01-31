package com.rmgx.myapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Auth_logs.
 */
@Document(collection = "auth_logs")
public class Auth_logs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("user_id")
    private Integer user_id;

    @NotNull
    @Field("ip_address")
    private String ip_address;

    @NotNull
    @Field("device")
    private String device;

    @NotNull
    @Field("location")
    private String location;

    @NotNull
    @Field("user_agent")
    private String user_agent;

    @NotNull
    @Field("auth_type")
    private String auth_type;

    @NotNull
    @Field("device_status")
    private String device_status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Auth_logs user_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public Auth_logs ip_address(String ip_address) {
        this.ip_address = ip_address;
        return this;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getDevice() {
        return device;
    }

    public Auth_logs device(String device) {
        this.device = device;
        return this;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLocation() {
        return location;
    }

    public Auth_logs location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public Auth_logs user_agent(String user_agent) {
        this.user_agent = user_agent;
        return this;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public Auth_logs auth_type(String auth_type) {
        this.auth_type = auth_type;
        return this;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getDevice_status() {
        return device_status;
    }

    public Auth_logs device_status(String device_status) {
        this.device_status = device_status;
        return this;
    }

    public void setDevice_status(String device_status) {
        this.device_status = device_status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auth_logs auth_logs = (Auth_logs) o;
        if (auth_logs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auth_logs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Auth_logs{" +
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
