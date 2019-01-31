package com.rmgx.myapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Leads.
 */
@Document(collection = "leads")
public class Leads implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("salutation")
    private String salutation;

    @NotNull
    @Field("full_name")
    private String fullName;

    @NotNull
    @Field("first_name")
    private String firstName;

    @NotNull
    @Field("last_name")
    private String lastName;

    @NotNull
    @Field("date_of_birth")
    private String dateOfBirth;

    @NotNull
    @Field("email_address")
    private String emailAddress;

    @NotNull
    @Field("home_phone")
    private String homePhone;

    @NotNull
    @Field("work_phone")
    private String workPhone;

    @NotNull
    @Field("cell_phone")
    private String cellPhone;

    @NotNull
    @Field("gender")
    private String gender;

    @NotNull
    @Field("type")
    private String type;

    @NotNull
    @Field("preferred_comms_mode")
    private String preferredCommsMode;

    @NotNull
    @Field("stage")
    private String stage;

    @NotNull
    @Field("status")
    private String status;

    @NotNull
    @Field("lead_interest")
    private String leadInterest;

    @NotNull
    @Field("lead_quality_score")
    private String leadQualityScore;

    @NotNull
    @Field("assigned_to")
    private String assignedTo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public Leads salutation(String salutation) {
        this.salutation = salutation;
        return this;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFullName() {
        return fullName;
    }

    public Leads fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Leads firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Leads lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Leads dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Leads emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public Leads homePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public Leads workPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public Leads cellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getGender() {
        return gender;
    }

    public Leads gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public Leads type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPreferredCommsMode() {
        return preferredCommsMode;
    }

    public Leads preferredCommsMode(String preferredCommsMode) {
        this.preferredCommsMode = preferredCommsMode;
        return this;
    }

    public void setPreferredCommsMode(String preferredCommsMode) {
        this.preferredCommsMode = preferredCommsMode;
    }

    public String getStage() {
        return stage;
    }

    public Leads stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public Leads status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeadInterest() {
        return leadInterest;
    }

    public Leads leadInterest(String leadInterest) {
        this.leadInterest = leadInterest;
        return this;
    }

    public void setLeadInterest(String leadInterest) {
        this.leadInterest = leadInterest;
    }

    public String getLeadQualityScore() {
        return leadQualityScore;
    }

    public Leads leadQualityScore(String leadQualityScore) {
        this.leadQualityScore = leadQualityScore;
        return this;
    }

    public void setLeadQualityScore(String leadQualityScore) {
        this.leadQualityScore = leadQualityScore;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Leads assignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
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
        Leads leads = (Leads) o;
        if (leads.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), leads.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Leads{" +
            "id=" + getId() +
            ", salutation='" + getSalutation() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", homePhone='" + getHomePhone() + "'" +
            ", workPhone='" + getWorkPhone() + "'" +
            ", cellPhone='" + getCellPhone() + "'" +
            ", gender='" + getGender() + "'" +
            ", type='" + getType() + "'" +
            ", preferredCommsMode='" + getPreferredCommsMode() + "'" +
            ", stage='" + getStage() + "'" +
            ", status='" + getStatus() + "'" +
            ", leadInterest='" + getLeadInterest() + "'" +
            ", leadQualityScore='" + getLeadQualityScore() + "'" +
            ", assignedTo='" + getAssignedTo() + "'" +
            "}";
    }
}
