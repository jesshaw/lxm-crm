package com.lexiangmaio.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 潜客
 */
@Entity
@Table(name = "lead_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 称呼
     */
    @Size(max = 255)
    @Column(name = "salutation", length = 255)
    private String salutation;

    /**
     * 名
     */
    @Size(max = 100)
    @Column(name = "first_name", length = 100)
    private String firstName;

    /**
     * 姓
     */
    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    private String lastName;

    /**
     * 职位
     */
    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    /**
     * 部门
     */
    @Size(max = 100)
    @Column(name = "department", length = 100)
    private String department;

    /**
     * 不要打电话
     */
    @Column(name = "do_not_call")
    private Boolean doNotCall;

    /**
     * 家庭电话
     */
    @Size(max = 100)
    @Column(name = "phone_home", length = 100)
    private String phoneHome;

    /**
     * 移动电话
     */
    @Size(max = 100)
    @Column(name = "phone_mobile", length = 100)
    private String phoneMobile;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @Column(name = "phone_work", length = 100)
    private String phoneWork;

    /**
     * 其它电话
     */
    @Size(max = 100)
    @Column(name = "phone_other", length = 100)
    private String phoneOther;

    /**
     * 传真
     */
    @Size(max = 100)
    @Column(name = "phone_fax", length = 100)
    private String phoneFax;

    /**
     * 审核日期
     */
    @Column(name = "date_reviewed")
    private LocalDate dateReviewed;

    /**
     * 合法依据
     */
    @Size(max = 512)
    @Column(name = "lawful_basis", length = 512)
    private String lawfulBasis;

    /**
     * 合法依据来源
     */
    @Size(max = 100)
    @Column(name = "lawful_basis_source", length = 100)
    private String lawfulBasisSource;

    /**
     * 街道
     */
    @Size(max = 150)
    @Column(name = "primary_address_street", length = 150)
    private String primaryAddressStreet;

    /**
     * 城市
     */
    @Size(max = 100)
    @Column(name = "primary_address_city", length = 100)
    private String primaryAddressCity;

    /**
     * 省/州
     */
    @Size(max = 100)
    @Column(name = "primary_address_state", length = 100)
    private String primaryAddressState;

    /**
     * 邮编
     */
    @Size(max = 20)
    @Column(name = "primary_address_postalcode", length = 20)
    private String primaryAddressPostalcode;

    /**
     * 国家/地区
     */
    @Size(max = 255)
    @Column(name = "primary_address_country", length = 255)
    private String primaryAddressCountry;

    /**
     * 备用街道
     */
    @Size(max = 150)
    @Column(name = "alt_address_street", length = 150)
    private String altAddressStreet;

    /**
     * 备用城市
     */
    @Size(max = 100)
    @Column(name = "alt_address_city", length = 100)
    private String altAddressCity;

    /**
     * 备用省/州
     */
    @Size(max = 100)
    @Column(name = "alt_address_state", length = 100)
    private String altAddressState;

    /**
     * 备用邮编
     */
    @Size(max = 20)
    @Column(name = "alt_address_postalcode", length = 20)
    private String altAddressPostalcode;

    /**
     * 备用国家/地区
     */
    @Size(max = 255)
    @Column(name = "alt_address_country", length = 255)
    private String altAddressCountry;

    /**
     * 助手
     */
    @Size(max = 75)
    @Column(name = "assistant", length = 75)
    private String assistant;

    /**
     * 助手电话
     */
    @Size(max = 100)
    @Column(name = "assistant_phone", length = 100)
    private String assistantPhone;

    /**
     * 已转化
     */
    @Column(name = "converted")
    private Boolean converted;

    /**
     * 推荐人
     */
    @Size(max = 100)
    @Column(name = "refered_by", length = 100)
    private String referedBy;

    /**
     * 来源
     */
    @Size(max = 100)
    @Column(name = "lead_source", length = 100)
    private String leadSource;

    /**
     * 来源描述
     */
    @Size(max = 512)
    @Column(name = "lead_source_description", length = 512)
    private String leadSourceDescription;

    /**
     * 状态
     */
    @Size(max = 100)
    @Column(name = "status", length = 100)
    private String status;

    /**
     * 状态描述
     */
    @Size(max = 512)
    @Column(name = "status_description", length = 512)
    private String statusDescription;

    /**
     * 生日
     */
    @Column(name = "birthdate")
    private LocalDate birthdate;

    /**
     * 描述
     */
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * 汇报人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "reportsTo", "assignedUser" }, allowSetters = true)
    private LeadInfo reportsTo;

    /**
     * 分派给
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeadInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return this.salutation;
    }

    public LeadInfo salutation(String salutation) {
        this.setSalutation(salutation);
        return this;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public LeadInfo firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LeadInfo lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return this.title;
    }

    public LeadInfo title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return this.department;
    }

    public LeadInfo department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getDoNotCall() {
        return this.doNotCall;
    }

    public LeadInfo doNotCall(Boolean doNotCall) {
        this.setDoNotCall(doNotCall);
        return this;
    }

    public void setDoNotCall(Boolean doNotCall) {
        this.doNotCall = doNotCall;
    }

    public String getPhoneHome() {
        return this.phoneHome;
    }

    public LeadInfo phoneHome(String phoneHome) {
        this.setPhoneHome(phoneHome);
        return this;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneMobile() {
        return this.phoneMobile;
    }

    public LeadInfo phoneMobile(String phoneMobile) {
        this.setPhoneMobile(phoneMobile);
        return this;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneWork() {
        return this.phoneWork;
    }

    public LeadInfo phoneWork(String phoneWork) {
        this.setPhoneWork(phoneWork);
        return this;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public String getPhoneOther() {
        return this.phoneOther;
    }

    public LeadInfo phoneOther(String phoneOther) {
        this.setPhoneOther(phoneOther);
        return this;
    }

    public void setPhoneOther(String phoneOther) {
        this.phoneOther = phoneOther;
    }

    public String getPhoneFax() {
        return this.phoneFax;
    }

    public LeadInfo phoneFax(String phoneFax) {
        this.setPhoneFax(phoneFax);
        return this;
    }

    public void setPhoneFax(String phoneFax) {
        this.phoneFax = phoneFax;
    }

    public LocalDate getDateReviewed() {
        return this.dateReviewed;
    }

    public LeadInfo dateReviewed(LocalDate dateReviewed) {
        this.setDateReviewed(dateReviewed);
        return this;
    }

    public void setDateReviewed(LocalDate dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public String getLawfulBasis() {
        return this.lawfulBasis;
    }

    public LeadInfo lawfulBasis(String lawfulBasis) {
        this.setLawfulBasis(lawfulBasis);
        return this;
    }

    public void setLawfulBasis(String lawfulBasis) {
        this.lawfulBasis = lawfulBasis;
    }

    public String getLawfulBasisSource() {
        return this.lawfulBasisSource;
    }

    public LeadInfo lawfulBasisSource(String lawfulBasisSource) {
        this.setLawfulBasisSource(lawfulBasisSource);
        return this;
    }

    public void setLawfulBasisSource(String lawfulBasisSource) {
        this.lawfulBasisSource = lawfulBasisSource;
    }

    public String getPrimaryAddressStreet() {
        return this.primaryAddressStreet;
    }

    public LeadInfo primaryAddressStreet(String primaryAddressStreet) {
        this.setPrimaryAddressStreet(primaryAddressStreet);
        return this;
    }

    public void setPrimaryAddressStreet(String primaryAddressStreet) {
        this.primaryAddressStreet = primaryAddressStreet;
    }

    public String getPrimaryAddressCity() {
        return this.primaryAddressCity;
    }

    public LeadInfo primaryAddressCity(String primaryAddressCity) {
        this.setPrimaryAddressCity(primaryAddressCity);
        return this;
    }

    public void setPrimaryAddressCity(String primaryAddressCity) {
        this.primaryAddressCity = primaryAddressCity;
    }

    public String getPrimaryAddressState() {
        return this.primaryAddressState;
    }

    public LeadInfo primaryAddressState(String primaryAddressState) {
        this.setPrimaryAddressState(primaryAddressState);
        return this;
    }

    public void setPrimaryAddressState(String primaryAddressState) {
        this.primaryAddressState = primaryAddressState;
    }

    public String getPrimaryAddressPostalcode() {
        return this.primaryAddressPostalcode;
    }

    public LeadInfo primaryAddressPostalcode(String primaryAddressPostalcode) {
        this.setPrimaryAddressPostalcode(primaryAddressPostalcode);
        return this;
    }

    public void setPrimaryAddressPostalcode(String primaryAddressPostalcode) {
        this.primaryAddressPostalcode = primaryAddressPostalcode;
    }

    public String getPrimaryAddressCountry() {
        return this.primaryAddressCountry;
    }

    public LeadInfo primaryAddressCountry(String primaryAddressCountry) {
        this.setPrimaryAddressCountry(primaryAddressCountry);
        return this;
    }

    public void setPrimaryAddressCountry(String primaryAddressCountry) {
        this.primaryAddressCountry = primaryAddressCountry;
    }

    public String getAltAddressStreet() {
        return this.altAddressStreet;
    }

    public LeadInfo altAddressStreet(String altAddressStreet) {
        this.setAltAddressStreet(altAddressStreet);
        return this;
    }

    public void setAltAddressStreet(String altAddressStreet) {
        this.altAddressStreet = altAddressStreet;
    }

    public String getAltAddressCity() {
        return this.altAddressCity;
    }

    public LeadInfo altAddressCity(String altAddressCity) {
        this.setAltAddressCity(altAddressCity);
        return this;
    }

    public void setAltAddressCity(String altAddressCity) {
        this.altAddressCity = altAddressCity;
    }

    public String getAltAddressState() {
        return this.altAddressState;
    }

    public LeadInfo altAddressState(String altAddressState) {
        this.setAltAddressState(altAddressState);
        return this;
    }

    public void setAltAddressState(String altAddressState) {
        this.altAddressState = altAddressState;
    }

    public String getAltAddressPostalcode() {
        return this.altAddressPostalcode;
    }

    public LeadInfo altAddressPostalcode(String altAddressPostalcode) {
        this.setAltAddressPostalcode(altAddressPostalcode);
        return this;
    }

    public void setAltAddressPostalcode(String altAddressPostalcode) {
        this.altAddressPostalcode = altAddressPostalcode;
    }

    public String getAltAddressCountry() {
        return this.altAddressCountry;
    }

    public LeadInfo altAddressCountry(String altAddressCountry) {
        this.setAltAddressCountry(altAddressCountry);
        return this;
    }

    public void setAltAddressCountry(String altAddressCountry) {
        this.altAddressCountry = altAddressCountry;
    }

    public String getAssistant() {
        return this.assistant;
    }

    public LeadInfo assistant(String assistant) {
        this.setAssistant(assistant);
        return this;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getAssistantPhone() {
        return this.assistantPhone;
    }

    public LeadInfo assistantPhone(String assistantPhone) {
        this.setAssistantPhone(assistantPhone);
        return this;
    }

    public void setAssistantPhone(String assistantPhone) {
        this.assistantPhone = assistantPhone;
    }

    public Boolean getConverted() {
        return this.converted;
    }

    public LeadInfo converted(Boolean converted) {
        this.setConverted(converted);
        return this;
    }

    public void setConverted(Boolean converted) {
        this.converted = converted;
    }

    public String getReferedBy() {
        return this.referedBy;
    }

    public LeadInfo referedBy(String referedBy) {
        this.setReferedBy(referedBy);
        return this;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    public String getLeadSource() {
        return this.leadSource;
    }

    public LeadInfo leadSource(String leadSource) {
        this.setLeadSource(leadSource);
        return this;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getLeadSourceDescription() {
        return this.leadSourceDescription;
    }

    public LeadInfo leadSourceDescription(String leadSourceDescription) {
        this.setLeadSourceDescription(leadSourceDescription);
        return this;
    }

    public void setLeadSourceDescription(String leadSourceDescription) {
        this.leadSourceDescription = leadSourceDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public LeadInfo status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    public LeadInfo statusDescription(String statusDescription) {
        this.setStatusDescription(statusDescription);
        return this;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public LeadInfo birthdate(LocalDate birthdate) {
        this.setBirthdate(birthdate);
        return this;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return this.description;
    }

    public LeadInfo description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LeadInfo getReportsTo() {
        return this.reportsTo;
    }

    public void setReportsTo(LeadInfo leadInfo) {
        this.reportsTo = leadInfo;
    }

    public LeadInfo reportsTo(LeadInfo leadInfo) {
        this.setReportsTo(leadInfo);
        return this;
    }

    public User getAssignedUser() {
        return this.assignedUser;
    }

    public void setAssignedUser(User user) {
        this.assignedUser = user;
    }

    public LeadInfo assignedUser(User user) {
        this.setAssignedUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeadInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((LeadInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeadInfo{" +
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
            "}";
    }
}
