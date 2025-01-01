package com.lexiangmiao.crm.service.criteria;

import com.lexiangmiao.crm.domain.LeadInfo;
import com.lexiangmiao.crm.web.rest.LeadInfoResource;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link LeadInfo} entity. This class is used
 * in {@link LeadInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lead-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeadInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter salutation;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter title;

    private StringFilter department;

    private BooleanFilter doNotCall;

    private StringFilter phoneHome;

    private StringFilter phoneMobile;

    private StringFilter phoneWork;

    private StringFilter phoneOther;

    private StringFilter phoneFax;

    private LocalDateFilter dateReviewed;

    private StringFilter lawfulBasis;

    private StringFilter lawfulBasisSource;

    private StringFilter primaryAddressStreet;

    private StringFilter primaryAddressCity;

    private StringFilter primaryAddressState;

    private StringFilter primaryAddressPostalcode;

    private StringFilter primaryAddressCountry;

    private StringFilter altAddressStreet;

    private StringFilter altAddressCity;

    private StringFilter altAddressState;

    private StringFilter altAddressPostalcode;

    private StringFilter altAddressCountry;

    private StringFilter assistant;

    private StringFilter assistantPhone;

    private BooleanFilter converted;

    private StringFilter referedBy;

    private StringFilter leadSource;

    private StringFilter leadSourceDescription;

    private StringFilter status;

    private StringFilter statusDescription;

    private LocalDateFilter birthdate;

    private LongFilter reportsToId;

    private LongFilter assignedUserId;

    private Boolean distinct;

    public LeadInfoCriteria() {}

    public LeadInfoCriteria(LeadInfoCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.salutation = other.optionalSalutation().map(StringFilter::copy).orElse(null);
        this.firstName = other.optionalFirstName().map(StringFilter::copy).orElse(null);
        this.lastName = other.optionalLastName().map(StringFilter::copy).orElse(null);
        this.title = other.optionalTitle().map(StringFilter::copy).orElse(null);
        this.department = other.optionalDepartment().map(StringFilter::copy).orElse(null);
        this.doNotCall = other.optionalDoNotCall().map(BooleanFilter::copy).orElse(null);
        this.phoneHome = other.optionalPhoneHome().map(StringFilter::copy).orElse(null);
        this.phoneMobile = other.optionalPhoneMobile().map(StringFilter::copy).orElse(null);
        this.phoneWork = other.optionalPhoneWork().map(StringFilter::copy).orElse(null);
        this.phoneOther = other.optionalPhoneOther().map(StringFilter::copy).orElse(null);
        this.phoneFax = other.optionalPhoneFax().map(StringFilter::copy).orElse(null);
        this.dateReviewed = other.optionalDateReviewed().map(LocalDateFilter::copy).orElse(null);
        this.lawfulBasis = other.optionalLawfulBasis().map(StringFilter::copy).orElse(null);
        this.lawfulBasisSource = other.optionalLawfulBasisSource().map(StringFilter::copy).orElse(null);
        this.primaryAddressStreet = other.optionalPrimaryAddressStreet().map(StringFilter::copy).orElse(null);
        this.primaryAddressCity = other.optionalPrimaryAddressCity().map(StringFilter::copy).orElse(null);
        this.primaryAddressState = other.optionalPrimaryAddressState().map(StringFilter::copy).orElse(null);
        this.primaryAddressPostalcode = other.optionalPrimaryAddressPostalcode().map(StringFilter::copy).orElse(null);
        this.primaryAddressCountry = other.optionalPrimaryAddressCountry().map(StringFilter::copy).orElse(null);
        this.altAddressStreet = other.optionalAltAddressStreet().map(StringFilter::copy).orElse(null);
        this.altAddressCity = other.optionalAltAddressCity().map(StringFilter::copy).orElse(null);
        this.altAddressState = other.optionalAltAddressState().map(StringFilter::copy).orElse(null);
        this.altAddressPostalcode = other.optionalAltAddressPostalcode().map(StringFilter::copy).orElse(null);
        this.altAddressCountry = other.optionalAltAddressCountry().map(StringFilter::copy).orElse(null);
        this.assistant = other.optionalAssistant().map(StringFilter::copy).orElse(null);
        this.assistantPhone = other.optionalAssistantPhone().map(StringFilter::copy).orElse(null);
        this.converted = other.optionalConverted().map(BooleanFilter::copy).orElse(null);
        this.referedBy = other.optionalReferedBy().map(StringFilter::copy).orElse(null);
        this.leadSource = other.optionalLeadSource().map(StringFilter::copy).orElse(null);
        this.leadSourceDescription = other.optionalLeadSourceDescription().map(StringFilter::copy).orElse(null);
        this.status = other.optionalStatus().map(StringFilter::copy).orElse(null);
        this.statusDescription = other.optionalStatusDescription().map(StringFilter::copy).orElse(null);
        this.birthdate = other.optionalBirthdate().map(LocalDateFilter::copy).orElse(null);
        this.reportsToId = other.optionalReportsToId().map(LongFilter::copy).orElse(null);
        this.assignedUserId = other.optionalAssignedUserId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public LeadInfoCriteria copy() {
        return new LeadInfoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSalutation() {
        return salutation;
    }

    public Optional<StringFilter> optionalSalutation() {
        return Optional.ofNullable(salutation);
    }

    public StringFilter salutation() {
        if (salutation == null) {
            setSalutation(new StringFilter());
        }
        return salutation;
    }

    public void setSalutation(StringFilter salutation) {
        this.salutation = salutation;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public Optional<StringFilter> optionalFirstName() {
        return Optional.ofNullable(firstName);
    }

    public StringFilter firstName() {
        if (firstName == null) {
            setFirstName(new StringFilter());
        }
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public Optional<StringFilter> optionalLastName() {
        return Optional.ofNullable(lastName);
    }

    public StringFilter lastName() {
        if (lastName == null) {
            setLastName(new StringFilter());
        }
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getTitle() {
        return title;
    }

    public Optional<StringFilter> optionalTitle() {
        return Optional.ofNullable(title);
    }

    public StringFilter title() {
        if (title == null) {
            setTitle(new StringFilter());
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getDepartment() {
        return department;
    }

    public Optional<StringFilter> optionalDepartment() {
        return Optional.ofNullable(department);
    }

    public StringFilter department() {
        if (department == null) {
            setDepartment(new StringFilter());
        }
        return department;
    }

    public void setDepartment(StringFilter department) {
        this.department = department;
    }

    public BooleanFilter getDoNotCall() {
        return doNotCall;
    }

    public Optional<BooleanFilter> optionalDoNotCall() {
        return Optional.ofNullable(doNotCall);
    }

    public BooleanFilter doNotCall() {
        if (doNotCall == null) {
            setDoNotCall(new BooleanFilter());
        }
        return doNotCall;
    }

    public void setDoNotCall(BooleanFilter doNotCall) {
        this.doNotCall = doNotCall;
    }

    public StringFilter getPhoneHome() {
        return phoneHome;
    }

    public Optional<StringFilter> optionalPhoneHome() {
        return Optional.ofNullable(phoneHome);
    }

    public StringFilter phoneHome() {
        if (phoneHome == null) {
            setPhoneHome(new StringFilter());
        }
        return phoneHome;
    }

    public void setPhoneHome(StringFilter phoneHome) {
        this.phoneHome = phoneHome;
    }

    public StringFilter getPhoneMobile() {
        return phoneMobile;
    }

    public Optional<StringFilter> optionalPhoneMobile() {
        return Optional.ofNullable(phoneMobile);
    }

    public StringFilter phoneMobile() {
        if (phoneMobile == null) {
            setPhoneMobile(new StringFilter());
        }
        return phoneMobile;
    }

    public void setPhoneMobile(StringFilter phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public StringFilter getPhoneWork() {
        return phoneWork;
    }

    public Optional<StringFilter> optionalPhoneWork() {
        return Optional.ofNullable(phoneWork);
    }

    public StringFilter phoneWork() {
        if (phoneWork == null) {
            setPhoneWork(new StringFilter());
        }
        return phoneWork;
    }

    public void setPhoneWork(StringFilter phoneWork) {
        this.phoneWork = phoneWork;
    }

    public StringFilter getPhoneOther() {
        return phoneOther;
    }

    public Optional<StringFilter> optionalPhoneOther() {
        return Optional.ofNullable(phoneOther);
    }

    public StringFilter phoneOther() {
        if (phoneOther == null) {
            setPhoneOther(new StringFilter());
        }
        return phoneOther;
    }

    public void setPhoneOther(StringFilter phoneOther) {
        this.phoneOther = phoneOther;
    }

    public StringFilter getPhoneFax() {
        return phoneFax;
    }

    public Optional<StringFilter> optionalPhoneFax() {
        return Optional.ofNullable(phoneFax);
    }

    public StringFilter phoneFax() {
        if (phoneFax == null) {
            setPhoneFax(new StringFilter());
        }
        return phoneFax;
    }

    public void setPhoneFax(StringFilter phoneFax) {
        this.phoneFax = phoneFax;
    }

    public LocalDateFilter getDateReviewed() {
        return dateReviewed;
    }

    public Optional<LocalDateFilter> optionalDateReviewed() {
        return Optional.ofNullable(dateReviewed);
    }

    public LocalDateFilter dateReviewed() {
        if (dateReviewed == null) {
            setDateReviewed(new LocalDateFilter());
        }
        return dateReviewed;
    }

    public void setDateReviewed(LocalDateFilter dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public StringFilter getLawfulBasis() {
        return lawfulBasis;
    }

    public Optional<StringFilter> optionalLawfulBasis() {
        return Optional.ofNullable(lawfulBasis);
    }

    public StringFilter lawfulBasis() {
        if (lawfulBasis == null) {
            setLawfulBasis(new StringFilter());
        }
        return lawfulBasis;
    }

    public void setLawfulBasis(StringFilter lawfulBasis) {
        this.lawfulBasis = lawfulBasis;
    }

    public StringFilter getLawfulBasisSource() {
        return lawfulBasisSource;
    }

    public Optional<StringFilter> optionalLawfulBasisSource() {
        return Optional.ofNullable(lawfulBasisSource);
    }

    public StringFilter lawfulBasisSource() {
        if (lawfulBasisSource == null) {
            setLawfulBasisSource(new StringFilter());
        }
        return lawfulBasisSource;
    }

    public void setLawfulBasisSource(StringFilter lawfulBasisSource) {
        this.lawfulBasisSource = lawfulBasisSource;
    }

    public StringFilter getPrimaryAddressStreet() {
        return primaryAddressStreet;
    }

    public Optional<StringFilter> optionalPrimaryAddressStreet() {
        return Optional.ofNullable(primaryAddressStreet);
    }

    public StringFilter primaryAddressStreet() {
        if (primaryAddressStreet == null) {
            setPrimaryAddressStreet(new StringFilter());
        }
        return primaryAddressStreet;
    }

    public void setPrimaryAddressStreet(StringFilter primaryAddressStreet) {
        this.primaryAddressStreet = primaryAddressStreet;
    }

    public StringFilter getPrimaryAddressCity() {
        return primaryAddressCity;
    }

    public Optional<StringFilter> optionalPrimaryAddressCity() {
        return Optional.ofNullable(primaryAddressCity);
    }

    public StringFilter primaryAddressCity() {
        if (primaryAddressCity == null) {
            setPrimaryAddressCity(new StringFilter());
        }
        return primaryAddressCity;
    }

    public void setPrimaryAddressCity(StringFilter primaryAddressCity) {
        this.primaryAddressCity = primaryAddressCity;
    }

    public StringFilter getPrimaryAddressState() {
        return primaryAddressState;
    }

    public Optional<StringFilter> optionalPrimaryAddressState() {
        return Optional.ofNullable(primaryAddressState);
    }

    public StringFilter primaryAddressState() {
        if (primaryAddressState == null) {
            setPrimaryAddressState(new StringFilter());
        }
        return primaryAddressState;
    }

    public void setPrimaryAddressState(StringFilter primaryAddressState) {
        this.primaryAddressState = primaryAddressState;
    }

    public StringFilter getPrimaryAddressPostalcode() {
        return primaryAddressPostalcode;
    }

    public Optional<StringFilter> optionalPrimaryAddressPostalcode() {
        return Optional.ofNullable(primaryAddressPostalcode);
    }

    public StringFilter primaryAddressPostalcode() {
        if (primaryAddressPostalcode == null) {
            setPrimaryAddressPostalcode(new StringFilter());
        }
        return primaryAddressPostalcode;
    }

    public void setPrimaryAddressPostalcode(StringFilter primaryAddressPostalcode) {
        this.primaryAddressPostalcode = primaryAddressPostalcode;
    }

    public StringFilter getPrimaryAddressCountry() {
        return primaryAddressCountry;
    }

    public Optional<StringFilter> optionalPrimaryAddressCountry() {
        return Optional.ofNullable(primaryAddressCountry);
    }

    public StringFilter primaryAddressCountry() {
        if (primaryAddressCountry == null) {
            setPrimaryAddressCountry(new StringFilter());
        }
        return primaryAddressCountry;
    }

    public void setPrimaryAddressCountry(StringFilter primaryAddressCountry) {
        this.primaryAddressCountry = primaryAddressCountry;
    }

    public StringFilter getAltAddressStreet() {
        return altAddressStreet;
    }

    public Optional<StringFilter> optionalAltAddressStreet() {
        return Optional.ofNullable(altAddressStreet);
    }

    public StringFilter altAddressStreet() {
        if (altAddressStreet == null) {
            setAltAddressStreet(new StringFilter());
        }
        return altAddressStreet;
    }

    public void setAltAddressStreet(StringFilter altAddressStreet) {
        this.altAddressStreet = altAddressStreet;
    }

    public StringFilter getAltAddressCity() {
        return altAddressCity;
    }

    public Optional<StringFilter> optionalAltAddressCity() {
        return Optional.ofNullable(altAddressCity);
    }

    public StringFilter altAddressCity() {
        if (altAddressCity == null) {
            setAltAddressCity(new StringFilter());
        }
        return altAddressCity;
    }

    public void setAltAddressCity(StringFilter altAddressCity) {
        this.altAddressCity = altAddressCity;
    }

    public StringFilter getAltAddressState() {
        return altAddressState;
    }

    public Optional<StringFilter> optionalAltAddressState() {
        return Optional.ofNullable(altAddressState);
    }

    public StringFilter altAddressState() {
        if (altAddressState == null) {
            setAltAddressState(new StringFilter());
        }
        return altAddressState;
    }

    public void setAltAddressState(StringFilter altAddressState) {
        this.altAddressState = altAddressState;
    }

    public StringFilter getAltAddressPostalcode() {
        return altAddressPostalcode;
    }

    public Optional<StringFilter> optionalAltAddressPostalcode() {
        return Optional.ofNullable(altAddressPostalcode);
    }

    public StringFilter altAddressPostalcode() {
        if (altAddressPostalcode == null) {
            setAltAddressPostalcode(new StringFilter());
        }
        return altAddressPostalcode;
    }

    public void setAltAddressPostalcode(StringFilter altAddressPostalcode) {
        this.altAddressPostalcode = altAddressPostalcode;
    }

    public StringFilter getAltAddressCountry() {
        return altAddressCountry;
    }

    public Optional<StringFilter> optionalAltAddressCountry() {
        return Optional.ofNullable(altAddressCountry);
    }

    public StringFilter altAddressCountry() {
        if (altAddressCountry == null) {
            setAltAddressCountry(new StringFilter());
        }
        return altAddressCountry;
    }

    public void setAltAddressCountry(StringFilter altAddressCountry) {
        this.altAddressCountry = altAddressCountry;
    }

    public StringFilter getAssistant() {
        return assistant;
    }

    public Optional<StringFilter> optionalAssistant() {
        return Optional.ofNullable(assistant);
    }

    public StringFilter assistant() {
        if (assistant == null) {
            setAssistant(new StringFilter());
        }
        return assistant;
    }

    public void setAssistant(StringFilter assistant) {
        this.assistant = assistant;
    }

    public StringFilter getAssistantPhone() {
        return assistantPhone;
    }

    public Optional<StringFilter> optionalAssistantPhone() {
        return Optional.ofNullable(assistantPhone);
    }

    public StringFilter assistantPhone() {
        if (assistantPhone == null) {
            setAssistantPhone(new StringFilter());
        }
        return assistantPhone;
    }

    public void setAssistantPhone(StringFilter assistantPhone) {
        this.assistantPhone = assistantPhone;
    }

    public BooleanFilter getConverted() {
        return converted;
    }

    public Optional<BooleanFilter> optionalConverted() {
        return Optional.ofNullable(converted);
    }

    public BooleanFilter converted() {
        if (converted == null) {
            setConverted(new BooleanFilter());
        }
        return converted;
    }

    public void setConverted(BooleanFilter converted) {
        this.converted = converted;
    }

    public StringFilter getReferedBy() {
        return referedBy;
    }

    public Optional<StringFilter> optionalReferedBy() {
        return Optional.ofNullable(referedBy);
    }

    public StringFilter referedBy() {
        if (referedBy == null) {
            setReferedBy(new StringFilter());
        }
        return referedBy;
    }

    public void setReferedBy(StringFilter referedBy) {
        this.referedBy = referedBy;
    }

    public StringFilter getLeadSource() {
        return leadSource;
    }

    public Optional<StringFilter> optionalLeadSource() {
        return Optional.ofNullable(leadSource);
    }

    public StringFilter leadSource() {
        if (leadSource == null) {
            setLeadSource(new StringFilter());
        }
        return leadSource;
    }

    public void setLeadSource(StringFilter leadSource) {
        this.leadSource = leadSource;
    }

    public StringFilter getLeadSourceDescription() {
        return leadSourceDescription;
    }

    public Optional<StringFilter> optionalLeadSourceDescription() {
        return Optional.ofNullable(leadSourceDescription);
    }

    public StringFilter leadSourceDescription() {
        if (leadSourceDescription == null) {
            setLeadSourceDescription(new StringFilter());
        }
        return leadSourceDescription;
    }

    public void setLeadSourceDescription(StringFilter leadSourceDescription) {
        this.leadSourceDescription = leadSourceDescription;
    }

    public StringFilter getStatus() {
        return status;
    }

    public Optional<StringFilter> optionalStatus() {
        return Optional.ofNullable(status);
    }

    public StringFilter status() {
        if (status == null) {
            setStatus(new StringFilter());
        }
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getStatusDescription() {
        return statusDescription;
    }

    public Optional<StringFilter> optionalStatusDescription() {
        return Optional.ofNullable(statusDescription);
    }

    public StringFilter statusDescription() {
        if (statusDescription == null) {
            setStatusDescription(new StringFilter());
        }
        return statusDescription;
    }

    public void setStatusDescription(StringFilter statusDescription) {
        this.statusDescription = statusDescription;
    }

    public LocalDateFilter getBirthdate() {
        return birthdate;
    }

    public Optional<LocalDateFilter> optionalBirthdate() {
        return Optional.ofNullable(birthdate);
    }

    public LocalDateFilter birthdate() {
        if (birthdate == null) {
            setBirthdate(new LocalDateFilter());
        }
        return birthdate;
    }

    public void setBirthdate(LocalDateFilter birthdate) {
        this.birthdate = birthdate;
    }

    public LongFilter getReportsToId() {
        return reportsToId;
    }

    public Optional<LongFilter> optionalReportsToId() {
        return Optional.ofNullable(reportsToId);
    }

    public LongFilter reportsToId() {
        if (reportsToId == null) {
            setReportsToId(new LongFilter());
        }
        return reportsToId;
    }

    public void setReportsToId(LongFilter reportsToId) {
        this.reportsToId = reportsToId;
    }

    public LongFilter getAssignedUserId() {
        return assignedUserId;
    }

    public Optional<LongFilter> optionalAssignedUserId() {
        return Optional.ofNullable(assignedUserId);
    }

    public LongFilter assignedUserId() {
        if (assignedUserId == null) {
            setAssignedUserId(new LongFilter());
        }
        return assignedUserId;
    }

    public void setAssignedUserId(LongFilter assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LeadInfoCriteria that = (LeadInfoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(salutation, that.salutation) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(title, that.title) &&
            Objects.equals(department, that.department) &&
            Objects.equals(doNotCall, that.doNotCall) &&
            Objects.equals(phoneHome, that.phoneHome) &&
            Objects.equals(phoneMobile, that.phoneMobile) &&
            Objects.equals(phoneWork, that.phoneWork) &&
            Objects.equals(phoneOther, that.phoneOther) &&
            Objects.equals(phoneFax, that.phoneFax) &&
            Objects.equals(dateReviewed, that.dateReviewed) &&
            Objects.equals(lawfulBasis, that.lawfulBasis) &&
            Objects.equals(lawfulBasisSource, that.lawfulBasisSource) &&
            Objects.equals(primaryAddressStreet, that.primaryAddressStreet) &&
            Objects.equals(primaryAddressCity, that.primaryAddressCity) &&
            Objects.equals(primaryAddressState, that.primaryAddressState) &&
            Objects.equals(primaryAddressPostalcode, that.primaryAddressPostalcode) &&
            Objects.equals(primaryAddressCountry, that.primaryAddressCountry) &&
            Objects.equals(altAddressStreet, that.altAddressStreet) &&
            Objects.equals(altAddressCity, that.altAddressCity) &&
            Objects.equals(altAddressState, that.altAddressState) &&
            Objects.equals(altAddressPostalcode, that.altAddressPostalcode) &&
            Objects.equals(altAddressCountry, that.altAddressCountry) &&
            Objects.equals(assistant, that.assistant) &&
            Objects.equals(assistantPhone, that.assistantPhone) &&
            Objects.equals(converted, that.converted) &&
            Objects.equals(referedBy, that.referedBy) &&
            Objects.equals(leadSource, that.leadSource) &&
            Objects.equals(leadSourceDescription, that.leadSourceDescription) &&
            Objects.equals(status, that.status) &&
            Objects.equals(statusDescription, that.statusDescription) &&
            Objects.equals(birthdate, that.birthdate) &&
            Objects.equals(reportsToId, that.reportsToId) &&
            Objects.equals(assignedUserId, that.assignedUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            salutation,
            firstName,
            lastName,
            title,
            department,
            doNotCall,
            phoneHome,
            phoneMobile,
            phoneWork,
            phoneOther,
            phoneFax,
            dateReviewed,
            lawfulBasis,
            lawfulBasisSource,
            primaryAddressStreet,
            primaryAddressCity,
            primaryAddressState,
            primaryAddressPostalcode,
            primaryAddressCountry,
            altAddressStreet,
            altAddressCity,
            altAddressState,
            altAddressPostalcode,
            altAddressCountry,
            assistant,
            assistantPhone,
            converted,
            referedBy,
            leadSource,
            leadSourceDescription,
            status,
            statusDescription,
            birthdate,
            reportsToId,
            assignedUserId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeadInfoCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalSalutation().map(f -> "salutation=" + f + ", ").orElse("") +
            optionalFirstName().map(f -> "firstName=" + f + ", ").orElse("") +
            optionalLastName().map(f -> "lastName=" + f + ", ").orElse("") +
            optionalTitle().map(f -> "title=" + f + ", ").orElse("") +
            optionalDepartment().map(f -> "department=" + f + ", ").orElse("") +
            optionalDoNotCall().map(f -> "doNotCall=" + f + ", ").orElse("") +
            optionalPhoneHome().map(f -> "phoneHome=" + f + ", ").orElse("") +
            optionalPhoneMobile().map(f -> "phoneMobile=" + f + ", ").orElse("") +
            optionalPhoneWork().map(f -> "phoneWork=" + f + ", ").orElse("") +
            optionalPhoneOther().map(f -> "phoneOther=" + f + ", ").orElse("") +
            optionalPhoneFax().map(f -> "phoneFax=" + f + ", ").orElse("") +
            optionalDateReviewed().map(f -> "dateReviewed=" + f + ", ").orElse("") +
            optionalLawfulBasis().map(f -> "lawfulBasis=" + f + ", ").orElse("") +
            optionalLawfulBasisSource().map(f -> "lawfulBasisSource=" + f + ", ").orElse("") +
            optionalPrimaryAddressStreet().map(f -> "primaryAddressStreet=" + f + ", ").orElse("") +
            optionalPrimaryAddressCity().map(f -> "primaryAddressCity=" + f + ", ").orElse("") +
            optionalPrimaryAddressState().map(f -> "primaryAddressState=" + f + ", ").orElse("") +
            optionalPrimaryAddressPostalcode().map(f -> "primaryAddressPostalcode=" + f + ", ").orElse("") +
            optionalPrimaryAddressCountry().map(f -> "primaryAddressCountry=" + f + ", ").orElse("") +
            optionalAltAddressStreet().map(f -> "altAddressStreet=" + f + ", ").orElse("") +
            optionalAltAddressCity().map(f -> "altAddressCity=" + f + ", ").orElse("") +
            optionalAltAddressState().map(f -> "altAddressState=" + f + ", ").orElse("") +
            optionalAltAddressPostalcode().map(f -> "altAddressPostalcode=" + f + ", ").orElse("") +
            optionalAltAddressCountry().map(f -> "altAddressCountry=" + f + ", ").orElse("") +
            optionalAssistant().map(f -> "assistant=" + f + ", ").orElse("") +
            optionalAssistantPhone().map(f -> "assistantPhone=" + f + ", ").orElse("") +
            optionalConverted().map(f -> "converted=" + f + ", ").orElse("") +
            optionalReferedBy().map(f -> "referedBy=" + f + ", ").orElse("") +
            optionalLeadSource().map(f -> "leadSource=" + f + ", ").orElse("") +
            optionalLeadSourceDescription().map(f -> "leadSourceDescription=" + f + ", ").orElse("") +
            optionalStatus().map(f -> "status=" + f + ", ").orElse("") +
            optionalStatusDescription().map(f -> "statusDescription=" + f + ", ").orElse("") +
            optionalBirthdate().map(f -> "birthdate=" + f + ", ").orElse("") +
            optionalReportsToId().map(f -> "reportsToId=" + f + ", ").orElse("") +
            optionalAssignedUserId().map(f -> "assignedUserId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
