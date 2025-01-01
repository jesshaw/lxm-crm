package com.lexiangmiao.crm.service.dto;

import com.lexiangmiao.crm.domain.LeadInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link LeadInfo} entity.
 */
@Schema(description = "潜客")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeadInfoDto implements Serializable {

    private Long id;

    @Size(max = 255)
    private String salutation;

    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String department;

    private Boolean doNotCall;

    @Size(max = 100)
    private String phoneHome;

    @Size(max = 100)
    private String phoneMobile;

    @Size(max = 100)
    private String phoneWork;

    @Size(max = 100)
    private String phoneOther;

    @Size(max = 100)
    private String phoneFax;

    private LocalDate dateReviewed;

    @Size(max = 512)
    private String lawfulBasis;

    @Size(max = 100)
    private String lawfulBasisSource;

    @Size(max = 150)
    private String primaryAddressStreet;

    @Size(max = 100)
    private String primaryAddressCity;

    @Size(max = 100)
    private String primaryAddressState;

    @Size(max = 20)
    private String primaryAddressPostalcode;

    @Size(max = 255)
    private String primaryAddressCountry;

    @Size(max = 150)
    private String altAddressStreet;

    @Size(max = 100)
    private String altAddressCity;

    @Size(max = 100)
    private String altAddressState;

    @Size(max = 20)
    private String altAddressPostalcode;

    @Size(max = 255)
    private String altAddressCountry;

    @Size(max = 75)
    private String assistant;

    @Size(max = 100)
    private String assistantPhone;

    private Boolean converted;

    @Size(max = 100)
    private String referedBy;

    @Size(max = 100)
    private String leadSource;

    @Size(max = 512)
    private String leadSourceDescription;

    @Size(max = 100)
    private String status;

    @Size(max = 512)
    private String statusDescription;

    private LocalDate birthdate;

    @Lob
    private String description;

    private LeadInfoDto reportsTo;

    private UserDto assignedUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(Boolean doNotCall) {
        this.doNotCall = doNotCall;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public String getPhoneOther() {
        return phoneOther;
    }

    public void setPhoneOther(String phoneOther) {
        this.phoneOther = phoneOther;
    }

    public String getPhoneFax() {
        return phoneFax;
    }

    public void setPhoneFax(String phoneFax) {
        this.phoneFax = phoneFax;
    }

    public LocalDate getDateReviewed() {
        return dateReviewed;
    }

    public void setDateReviewed(LocalDate dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public String getLawfulBasis() {
        return lawfulBasis;
    }

    public void setLawfulBasis(String lawfulBasis) {
        this.lawfulBasis = lawfulBasis;
    }

    public String getLawfulBasisSource() {
        return lawfulBasisSource;
    }

    public void setLawfulBasisSource(String lawfulBasisSource) {
        this.lawfulBasisSource = lawfulBasisSource;
    }

    public String getPrimaryAddressStreet() {
        return primaryAddressStreet;
    }

    public void setPrimaryAddressStreet(String primaryAddressStreet) {
        this.primaryAddressStreet = primaryAddressStreet;
    }

    public String getPrimaryAddressCity() {
        return primaryAddressCity;
    }

    public void setPrimaryAddressCity(String primaryAddressCity) {
        this.primaryAddressCity = primaryAddressCity;
    }

    public String getPrimaryAddressState() {
        return primaryAddressState;
    }

    public void setPrimaryAddressState(String primaryAddressState) {
        this.primaryAddressState = primaryAddressState;
    }

    public String getPrimaryAddressPostalcode() {
        return primaryAddressPostalcode;
    }

    public void setPrimaryAddressPostalcode(String primaryAddressPostalcode) {
        this.primaryAddressPostalcode = primaryAddressPostalcode;
    }

    public String getPrimaryAddressCountry() {
        return primaryAddressCountry;
    }

    public void setPrimaryAddressCountry(String primaryAddressCountry) {
        this.primaryAddressCountry = primaryAddressCountry;
    }

    public String getAltAddressStreet() {
        return altAddressStreet;
    }

    public void setAltAddressStreet(String altAddressStreet) {
        this.altAddressStreet = altAddressStreet;
    }

    public String getAltAddressCity() {
        return altAddressCity;
    }

    public void setAltAddressCity(String altAddressCity) {
        this.altAddressCity = altAddressCity;
    }

    public String getAltAddressState() {
        return altAddressState;
    }

    public void setAltAddressState(String altAddressState) {
        this.altAddressState = altAddressState;
    }

    public String getAltAddressPostalcode() {
        return altAddressPostalcode;
    }

    public void setAltAddressPostalcode(String altAddressPostalcode) {
        this.altAddressPostalcode = altAddressPostalcode;
    }

    public String getAltAddressCountry() {
        return altAddressCountry;
    }

    public void setAltAddressCountry(String altAddressCountry) {
        this.altAddressCountry = altAddressCountry;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getAssistantPhone() {
        return assistantPhone;
    }

    public void setAssistantPhone(String assistantPhone) {
        this.assistantPhone = assistantPhone;
    }

    public Boolean getConverted() {
        return converted;
    }

    public void setConverted(Boolean converted) {
        this.converted = converted;
    }

    public String getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getLeadSourceDescription() {
        return leadSourceDescription;
    }

    public void setLeadSourceDescription(String leadSourceDescription) {
        this.leadSourceDescription = leadSourceDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LeadInfoDto getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(LeadInfoDto reportsTo) {
        this.reportsTo = reportsTo;
    }

    public UserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeadInfoDto)) {
            return false;
        }

        LeadInfoDto leadInfoDto = (LeadInfoDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, leadInfoDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeadInfoDto{" +
            "id=" + getId() +
            ", salutation='" + getSalutation() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", title='" + getTitle() + "'" +
            ", department='" + getDepartment() + "'" +
            ", doNotCall='" + getDoNotCall() + "'" +
            ", phoneHome='" + getPhoneHome() + "'" +
            ", phoneMobile='" + getPhoneMobile() + "'" +
            ", phoneWork='" + getPhoneWork() + "'" +
            ", phoneOther='" + getPhoneOther() + "'" +
            ", phoneFax='" + getPhoneFax() + "'" +
            ", dateReviewed='" + getDateReviewed() + "'" +
            ", lawfulBasis='" + getLawfulBasis() + "'" +
            ", lawfulBasisSource='" + getLawfulBasisSource() + "'" +
            ", primaryAddressStreet='" + getPrimaryAddressStreet() + "'" +
            ", primaryAddressCity='" + getPrimaryAddressCity() + "'" +
            ", primaryAddressState='" + getPrimaryAddressState() + "'" +
            ", primaryAddressPostalcode='" + getPrimaryAddressPostalcode() + "'" +
            ", primaryAddressCountry='" + getPrimaryAddressCountry() + "'" +
            ", altAddressStreet='" + getAltAddressStreet() + "'" +
            ", altAddressCity='" + getAltAddressCity() + "'" +
            ", altAddressState='" + getAltAddressState() + "'" +
            ", altAddressPostalcode='" + getAltAddressPostalcode() + "'" +
            ", altAddressCountry='" + getAltAddressCountry() + "'" +
            ", assistant='" + getAssistant() + "'" +
            ", assistantPhone='" + getAssistantPhone() + "'" +
            ", converted='" + getConverted() + "'" +
            ", referedBy='" + getReferedBy() + "'" +
            ", leadSource='" + getLeadSource() + "'" +
            ", leadSourceDescription='" + getLeadSourceDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusDescription='" + getStatusDescription() + "'" +
            ", birthdate='" + getBirthdate() + "'" +
            ", description='" + getDescription() + "'" +
            ", reportsTo=" + getReportsTo() +
            ", assignedUser=" + getAssignedUser() +
            "}";
    }
}
