package com.lexiangmaio.crm.web.rest;

import static com.lexiangmaio.crm.domain.LeadInfoAsserts.*;
import static com.lexiangmaio.crm.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexiangmaio.crm.IntegrationTest;
import com.lexiangmaio.crm.domain.LeadInfo;
import com.lexiangmaio.crm.domain.LeadInfo;
import com.lexiangmaio.crm.domain.User;
import com.lexiangmaio.crm.repository.LeadInfoRepository;
import com.lexiangmaio.crm.service.LeadInfoService;
import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import com.lexiangmaio.crm.service.mapper.LeadInfoMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LeadInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class LeadInfoResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SALUTATION = "AAAAAAAAAA";
    private static final String UPDATED_SALUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DO_NOT_CALL = false;
    private static final Boolean UPDATED_DO_NOT_CALL = true;

    private static final String DEFAULT_PHONE_HOME = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_HOME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_WORK = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_WORK = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_FAX = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_FAX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_REVIEWED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REVIEWED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_REVIEWED = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_LAWFUL_BASIS = "AAAAAAAAAA";
    private static final String UPDATED_LAWFUL_BASIS = "BBBBBBBBBB";

    private static final String DEFAULT_LAWFUL_BASIS_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_LAWFUL_BASIS_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS_STREET = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_ADDRESS_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_ADDRESS_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ALT_ADDRESS_STREET = "AAAAAAAAAA";
    private static final String UPDATED_ALT_ADDRESS_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_ALT_ADDRESS_CITY = "AAAAAAAAAA";
    private static final String UPDATED_ALT_ADDRESS_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ALT_ADDRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_ALT_ADDRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ALT_ADDRESS_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_ALT_ADDRESS_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_ALT_ADDRESS_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ALT_ADDRESS_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ASSISTANT = "AAAAAAAAAA";
    private static final String UPDATED_ASSISTANT = "BBBBBBBBBB";

    private static final String DEFAULT_ASSISTANT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_ASSISTANT_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONVERTED = false;
    private static final Boolean UPDATED_CONVERTED = true;

    private static final String DEFAULT_REFERED_BY = "AAAAAAAAAA";
    private static final String UPDATED_REFERED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_SOURCE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_SOURCE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BIRTHDATE = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/lead-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LeadInfoRepository leadInfoRepository;

    @Mock
    private LeadInfoRepository leadInfoRepositoryMock;

    @Autowired
    private LeadInfoMapper leadInfoMapper;

    @Mock
    private LeadInfoService leadInfoServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLeadInfoMockMvc;

    private LeadInfo leadInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeadInfo createEntity(EntityManager em) {
        LeadInfo leadInfo = new LeadInfo()
            .description(DEFAULT_DESCRIPTION)
            .salutation(DEFAULT_SALUTATION)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .title(DEFAULT_TITLE)
            .department(DEFAULT_DEPARTMENT)
            .doNotCall(DEFAULT_DO_NOT_CALL)
            .phoneHome(DEFAULT_PHONE_HOME)
            .phoneMobile(DEFAULT_PHONE_MOBILE)
            .phoneWork(DEFAULT_PHONE_WORK)
            .phoneOther(DEFAULT_PHONE_OTHER)
            .phoneFax(DEFAULT_PHONE_FAX)
            .dateReviewed(DEFAULT_DATE_REVIEWED)
            .lawfulBasis(DEFAULT_LAWFUL_BASIS)
            .lawfulBasisSource(DEFAULT_LAWFUL_BASIS_SOURCE)
            .primaryAddressStreet(DEFAULT_PRIMARY_ADDRESS_STREET)
            .primaryAddressCity(DEFAULT_PRIMARY_ADDRESS_CITY)
            .primaryAddressState(DEFAULT_PRIMARY_ADDRESS_STATE)
            .primaryAddressPostalcode(DEFAULT_PRIMARY_ADDRESS_POSTALCODE)
            .primaryAddressCountry(DEFAULT_PRIMARY_ADDRESS_COUNTRY)
            .altAddressStreet(DEFAULT_ALT_ADDRESS_STREET)
            .altAddressCity(DEFAULT_ALT_ADDRESS_CITY)
            .altAddressState(DEFAULT_ALT_ADDRESS_STATE)
            .altAddressPostalcode(DEFAULT_ALT_ADDRESS_POSTALCODE)
            .altAddressCountry(DEFAULT_ALT_ADDRESS_COUNTRY)
            .assistant(DEFAULT_ASSISTANT)
            .assistantPhone(DEFAULT_ASSISTANT_PHONE)
            .converted(DEFAULT_CONVERTED)
            .referedBy(DEFAULT_REFERED_BY)
            .leadSource(DEFAULT_LEAD_SOURCE)
            .leadSourceDescription(DEFAULT_LEAD_SOURCE_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .statusDescription(DEFAULT_STATUS_DESCRIPTION)
            .birthdate(DEFAULT_BIRTHDATE);
        return leadInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeadInfo createUpdatedEntity(EntityManager em) {
        LeadInfo leadInfo = new LeadInfo()
            .description(UPDATED_DESCRIPTION)
            .salutation(UPDATED_SALUTATION)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .department(UPDATED_DEPARTMENT)
            .doNotCall(UPDATED_DO_NOT_CALL)
            .phoneHome(UPDATED_PHONE_HOME)
            .phoneMobile(UPDATED_PHONE_MOBILE)
            .phoneWork(UPDATED_PHONE_WORK)
            .phoneOther(UPDATED_PHONE_OTHER)
            .phoneFax(UPDATED_PHONE_FAX)
            .dateReviewed(UPDATED_DATE_REVIEWED)
            .lawfulBasis(UPDATED_LAWFUL_BASIS)
            .lawfulBasisSource(UPDATED_LAWFUL_BASIS_SOURCE)
            .primaryAddressStreet(UPDATED_PRIMARY_ADDRESS_STREET)
            .primaryAddressCity(UPDATED_PRIMARY_ADDRESS_CITY)
            .primaryAddressState(UPDATED_PRIMARY_ADDRESS_STATE)
            .primaryAddressPostalcode(UPDATED_PRIMARY_ADDRESS_POSTALCODE)
            .primaryAddressCountry(UPDATED_PRIMARY_ADDRESS_COUNTRY)
            .altAddressStreet(UPDATED_ALT_ADDRESS_STREET)
            .altAddressCity(UPDATED_ALT_ADDRESS_CITY)
            .altAddressState(UPDATED_ALT_ADDRESS_STATE)
            .altAddressPostalcode(UPDATED_ALT_ADDRESS_POSTALCODE)
            .altAddressCountry(UPDATED_ALT_ADDRESS_COUNTRY)
            .assistant(UPDATED_ASSISTANT)
            .assistantPhone(UPDATED_ASSISTANT_PHONE)
            .converted(UPDATED_CONVERTED)
            .referedBy(UPDATED_REFERED_BY)
            .leadSource(UPDATED_LEAD_SOURCE)
            .leadSourceDescription(UPDATED_LEAD_SOURCE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .statusDescription(UPDATED_STATUS_DESCRIPTION)
            .birthdate(UPDATED_BIRTHDATE);
        return leadInfo;
    }

    @BeforeEach
    public void initTest() {
        leadInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createLeadInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);
        var returnedLeadInfoDto = om.readValue(
            restLeadInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leadInfoDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LeadInfoDto.class
        );

        // Validate the LeadInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLeadInfo = leadInfoMapper.toEntity(returnedLeadInfoDto);
        assertLeadInfoUpdatableFieldsEquals(returnedLeadInfo, getPersistedLeadInfo(returnedLeadInfo));
    }

    @Test
    @Transactional
    void createLeadInfoWithExistingId() throws Exception {
        // Create the LeadInfo with an existing ID
        leadInfo.setId(1L);
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeadInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leadInfoDto)))
            .andExpect(status().isBadRequest());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLeadInfos() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leadInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].doNotCall").value(hasItem(DEFAULT_DO_NOT_CALL.booleanValue())))
            .andExpect(jsonPath("$.[*].phoneHome").value(hasItem(DEFAULT_PHONE_HOME)))
            .andExpect(jsonPath("$.[*].phoneMobile").value(hasItem(DEFAULT_PHONE_MOBILE)))
            .andExpect(jsonPath("$.[*].phoneWork").value(hasItem(DEFAULT_PHONE_WORK)))
            .andExpect(jsonPath("$.[*].phoneOther").value(hasItem(DEFAULT_PHONE_OTHER)))
            .andExpect(jsonPath("$.[*].phoneFax").value(hasItem(DEFAULT_PHONE_FAX)))
            .andExpect(jsonPath("$.[*].dateReviewed").value(hasItem(DEFAULT_DATE_REVIEWED.toString())))
            .andExpect(jsonPath("$.[*].lawfulBasis").value(hasItem(DEFAULT_LAWFUL_BASIS)))
            .andExpect(jsonPath("$.[*].lawfulBasisSource").value(hasItem(DEFAULT_LAWFUL_BASIS_SOURCE)))
            .andExpect(jsonPath("$.[*].primaryAddressStreet").value(hasItem(DEFAULT_PRIMARY_ADDRESS_STREET)))
            .andExpect(jsonPath("$.[*].primaryAddressCity").value(hasItem(DEFAULT_PRIMARY_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].primaryAddressState").value(hasItem(DEFAULT_PRIMARY_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].primaryAddressPostalcode").value(hasItem(DEFAULT_PRIMARY_ADDRESS_POSTALCODE)))
            .andExpect(jsonPath("$.[*].primaryAddressCountry").value(hasItem(DEFAULT_PRIMARY_ADDRESS_COUNTRY)))
            .andExpect(jsonPath("$.[*].altAddressStreet").value(hasItem(DEFAULT_ALT_ADDRESS_STREET)))
            .andExpect(jsonPath("$.[*].altAddressCity").value(hasItem(DEFAULT_ALT_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].altAddressState").value(hasItem(DEFAULT_ALT_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].altAddressPostalcode").value(hasItem(DEFAULT_ALT_ADDRESS_POSTALCODE)))
            .andExpect(jsonPath("$.[*].altAddressCountry").value(hasItem(DEFAULT_ALT_ADDRESS_COUNTRY)))
            .andExpect(jsonPath("$.[*].assistant").value(hasItem(DEFAULT_ASSISTANT)))
            .andExpect(jsonPath("$.[*].assistantPhone").value(hasItem(DEFAULT_ASSISTANT_PHONE)))
            .andExpect(jsonPath("$.[*].converted").value(hasItem(DEFAULT_CONVERTED.booleanValue())))
            .andExpect(jsonPath("$.[*].referedBy").value(hasItem(DEFAULT_REFERED_BY)))
            .andExpect(jsonPath("$.[*].leadSource").value(hasItem(DEFAULT_LEAD_SOURCE)))
            .andExpect(jsonPath("$.[*].leadSourceDescription").value(hasItem(DEFAULT_LEAD_SOURCE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllLeadInfosWithEagerRelationshipsIsEnabled() throws Exception {
        when(leadInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLeadInfoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(leadInfoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllLeadInfosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(leadInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLeadInfoMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(leadInfoRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getLeadInfo() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get the leadInfo
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, leadInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(leadInfo.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.doNotCall").value(DEFAULT_DO_NOT_CALL.booleanValue()))
            .andExpect(jsonPath("$.phoneHome").value(DEFAULT_PHONE_HOME))
            .andExpect(jsonPath("$.phoneMobile").value(DEFAULT_PHONE_MOBILE))
            .andExpect(jsonPath("$.phoneWork").value(DEFAULT_PHONE_WORK))
            .andExpect(jsonPath("$.phoneOther").value(DEFAULT_PHONE_OTHER))
            .andExpect(jsonPath("$.phoneFax").value(DEFAULT_PHONE_FAX))
            .andExpect(jsonPath("$.dateReviewed").value(DEFAULT_DATE_REVIEWED.toString()))
            .andExpect(jsonPath("$.lawfulBasis").value(DEFAULT_LAWFUL_BASIS))
            .andExpect(jsonPath("$.lawfulBasisSource").value(DEFAULT_LAWFUL_BASIS_SOURCE))
            .andExpect(jsonPath("$.primaryAddressStreet").value(DEFAULT_PRIMARY_ADDRESS_STREET))
            .andExpect(jsonPath("$.primaryAddressCity").value(DEFAULT_PRIMARY_ADDRESS_CITY))
            .andExpect(jsonPath("$.primaryAddressState").value(DEFAULT_PRIMARY_ADDRESS_STATE))
            .andExpect(jsonPath("$.primaryAddressPostalcode").value(DEFAULT_PRIMARY_ADDRESS_POSTALCODE))
            .andExpect(jsonPath("$.primaryAddressCountry").value(DEFAULT_PRIMARY_ADDRESS_COUNTRY))
            .andExpect(jsonPath("$.altAddressStreet").value(DEFAULT_ALT_ADDRESS_STREET))
            .andExpect(jsonPath("$.altAddressCity").value(DEFAULT_ALT_ADDRESS_CITY))
            .andExpect(jsonPath("$.altAddressState").value(DEFAULT_ALT_ADDRESS_STATE))
            .andExpect(jsonPath("$.altAddressPostalcode").value(DEFAULT_ALT_ADDRESS_POSTALCODE))
            .andExpect(jsonPath("$.altAddressCountry").value(DEFAULT_ALT_ADDRESS_COUNTRY))
            .andExpect(jsonPath("$.assistant").value(DEFAULT_ASSISTANT))
            .andExpect(jsonPath("$.assistantPhone").value(DEFAULT_ASSISTANT_PHONE))
            .andExpect(jsonPath("$.converted").value(DEFAULT_CONVERTED.booleanValue()))
            .andExpect(jsonPath("$.referedBy").value(DEFAULT_REFERED_BY))
            .andExpect(jsonPath("$.leadSource").value(DEFAULT_LEAD_SOURCE))
            .andExpect(jsonPath("$.leadSourceDescription").value(DEFAULT_LEAD_SOURCE_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusDescription").value(DEFAULT_STATUS_DESCRIPTION))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()));
    }

    @Test
    @Transactional
    void getLeadInfosByIdFiltering() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        Long id = leadInfo.getId();

        defaultLeadInfoFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultLeadInfoFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultLeadInfoFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllLeadInfosBySalutationIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where salutation equals to
        defaultLeadInfoFiltering("salutation.equals=" + DEFAULT_SALUTATION, "salutation.equals=" + UPDATED_SALUTATION);
    }

    @Test
    @Transactional
    void getAllLeadInfosBySalutationIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where salutation in
        defaultLeadInfoFiltering("salutation.in=" + DEFAULT_SALUTATION + "," + UPDATED_SALUTATION, "salutation.in=" + UPDATED_SALUTATION);
    }

    @Test
    @Transactional
    void getAllLeadInfosBySalutationIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where salutation is not null
        defaultLeadInfoFiltering("salutation.specified=true", "salutation.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosBySalutationContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where salutation contains
        defaultLeadInfoFiltering("salutation.contains=" + DEFAULT_SALUTATION, "salutation.contains=" + UPDATED_SALUTATION);
    }

    @Test
    @Transactional
    void getAllLeadInfosBySalutationNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where salutation does not contain
        defaultLeadInfoFiltering("salutation.doesNotContain=" + UPDATED_SALUTATION, "salutation.doesNotContain=" + DEFAULT_SALUTATION);
    }

    @Test
    @Transactional
    void getAllLeadInfosByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where firstName equals to
        defaultLeadInfoFiltering("firstName.equals=" + DEFAULT_FIRST_NAME, "firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where firstName in
        defaultLeadInfoFiltering("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME, "firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where firstName is not null
        defaultLeadInfoFiltering("firstName.specified=true", "firstName.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where firstName contains
        defaultLeadInfoFiltering("firstName.contains=" + DEFAULT_FIRST_NAME, "firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where firstName does not contain
        defaultLeadInfoFiltering("firstName.doesNotContain=" + UPDATED_FIRST_NAME, "firstName.doesNotContain=" + DEFAULT_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lastName equals to
        defaultLeadInfoFiltering("lastName.equals=" + DEFAULT_LAST_NAME, "lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lastName in
        defaultLeadInfoFiltering("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME, "lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lastName is not null
        defaultLeadInfoFiltering("lastName.specified=true", "lastName.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByLastNameContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lastName contains
        defaultLeadInfoFiltering("lastName.contains=" + DEFAULT_LAST_NAME, "lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lastName does not contain
        defaultLeadInfoFiltering("lastName.doesNotContain=" + UPDATED_LAST_NAME, "lastName.doesNotContain=" + DEFAULT_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where title equals to
        defaultLeadInfoFiltering("title.equals=" + DEFAULT_TITLE, "title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where title in
        defaultLeadInfoFiltering("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE, "title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where title is not null
        defaultLeadInfoFiltering("title.specified=true", "title.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByTitleContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where title contains
        defaultLeadInfoFiltering("title.contains=" + DEFAULT_TITLE, "title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where title does not contain
        defaultLeadInfoFiltering("title.doesNotContain=" + UPDATED_TITLE, "title.doesNotContain=" + DEFAULT_TITLE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDepartmentIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where department equals to
        defaultLeadInfoFiltering("department.equals=" + DEFAULT_DEPARTMENT, "department.equals=" + UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDepartmentIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where department in
        defaultLeadInfoFiltering("department.in=" + DEFAULT_DEPARTMENT + "," + UPDATED_DEPARTMENT, "department.in=" + UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDepartmentIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where department is not null
        defaultLeadInfoFiltering("department.specified=true", "department.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByDepartmentContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where department contains
        defaultLeadInfoFiltering("department.contains=" + DEFAULT_DEPARTMENT, "department.contains=" + UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDepartmentNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where department does not contain
        defaultLeadInfoFiltering("department.doesNotContain=" + UPDATED_DEPARTMENT, "department.doesNotContain=" + DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDoNotCallIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where doNotCall equals to
        defaultLeadInfoFiltering("doNotCall.equals=" + DEFAULT_DO_NOT_CALL, "doNotCall.equals=" + UPDATED_DO_NOT_CALL);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDoNotCallIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where doNotCall in
        defaultLeadInfoFiltering("doNotCall.in=" + DEFAULT_DO_NOT_CALL + "," + UPDATED_DO_NOT_CALL, "doNotCall.in=" + UPDATED_DO_NOT_CALL);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDoNotCallIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where doNotCall is not null
        defaultLeadInfoFiltering("doNotCall.specified=true", "doNotCall.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneHomeIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneHome equals to
        defaultLeadInfoFiltering("phoneHome.equals=" + DEFAULT_PHONE_HOME, "phoneHome.equals=" + UPDATED_PHONE_HOME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneHomeIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneHome in
        defaultLeadInfoFiltering("phoneHome.in=" + DEFAULT_PHONE_HOME + "," + UPDATED_PHONE_HOME, "phoneHome.in=" + UPDATED_PHONE_HOME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneHomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneHome is not null
        defaultLeadInfoFiltering("phoneHome.specified=true", "phoneHome.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneHomeContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneHome contains
        defaultLeadInfoFiltering("phoneHome.contains=" + DEFAULT_PHONE_HOME, "phoneHome.contains=" + UPDATED_PHONE_HOME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneHomeNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneHome does not contain
        defaultLeadInfoFiltering("phoneHome.doesNotContain=" + UPDATED_PHONE_HOME, "phoneHome.doesNotContain=" + DEFAULT_PHONE_HOME);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneMobileIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneMobile equals to
        defaultLeadInfoFiltering("phoneMobile.equals=" + DEFAULT_PHONE_MOBILE, "phoneMobile.equals=" + UPDATED_PHONE_MOBILE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneMobileIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneMobile in
        defaultLeadInfoFiltering(
            "phoneMobile.in=" + DEFAULT_PHONE_MOBILE + "," + UPDATED_PHONE_MOBILE,
            "phoneMobile.in=" + UPDATED_PHONE_MOBILE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneMobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneMobile is not null
        defaultLeadInfoFiltering("phoneMobile.specified=true", "phoneMobile.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneMobileContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneMobile contains
        defaultLeadInfoFiltering("phoneMobile.contains=" + DEFAULT_PHONE_MOBILE, "phoneMobile.contains=" + UPDATED_PHONE_MOBILE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneMobileNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneMobile does not contain
        defaultLeadInfoFiltering(
            "phoneMobile.doesNotContain=" + UPDATED_PHONE_MOBILE,
            "phoneMobile.doesNotContain=" + DEFAULT_PHONE_MOBILE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneWorkIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneWork equals to
        defaultLeadInfoFiltering("phoneWork.equals=" + DEFAULT_PHONE_WORK, "phoneWork.equals=" + UPDATED_PHONE_WORK);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneWorkIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneWork in
        defaultLeadInfoFiltering("phoneWork.in=" + DEFAULT_PHONE_WORK + "," + UPDATED_PHONE_WORK, "phoneWork.in=" + UPDATED_PHONE_WORK);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneWorkIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneWork is not null
        defaultLeadInfoFiltering("phoneWork.specified=true", "phoneWork.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneWorkContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneWork contains
        defaultLeadInfoFiltering("phoneWork.contains=" + DEFAULT_PHONE_WORK, "phoneWork.contains=" + UPDATED_PHONE_WORK);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneWorkNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneWork does not contain
        defaultLeadInfoFiltering("phoneWork.doesNotContain=" + UPDATED_PHONE_WORK, "phoneWork.doesNotContain=" + DEFAULT_PHONE_WORK);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneOtherIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneOther equals to
        defaultLeadInfoFiltering("phoneOther.equals=" + DEFAULT_PHONE_OTHER, "phoneOther.equals=" + UPDATED_PHONE_OTHER);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneOtherIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneOther in
        defaultLeadInfoFiltering(
            "phoneOther.in=" + DEFAULT_PHONE_OTHER + "," + UPDATED_PHONE_OTHER,
            "phoneOther.in=" + UPDATED_PHONE_OTHER
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneOtherIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneOther is not null
        defaultLeadInfoFiltering("phoneOther.specified=true", "phoneOther.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneOtherContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneOther contains
        defaultLeadInfoFiltering("phoneOther.contains=" + DEFAULT_PHONE_OTHER, "phoneOther.contains=" + UPDATED_PHONE_OTHER);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneOtherNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneOther does not contain
        defaultLeadInfoFiltering("phoneOther.doesNotContain=" + UPDATED_PHONE_OTHER, "phoneOther.doesNotContain=" + DEFAULT_PHONE_OTHER);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneFaxIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneFax equals to
        defaultLeadInfoFiltering("phoneFax.equals=" + DEFAULT_PHONE_FAX, "phoneFax.equals=" + UPDATED_PHONE_FAX);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneFaxIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneFax in
        defaultLeadInfoFiltering("phoneFax.in=" + DEFAULT_PHONE_FAX + "," + UPDATED_PHONE_FAX, "phoneFax.in=" + UPDATED_PHONE_FAX);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneFaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneFax is not null
        defaultLeadInfoFiltering("phoneFax.specified=true", "phoneFax.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneFaxContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneFax contains
        defaultLeadInfoFiltering("phoneFax.contains=" + DEFAULT_PHONE_FAX, "phoneFax.contains=" + UPDATED_PHONE_FAX);
    }

    @Test
    @Transactional
    void getAllLeadInfosByPhoneFaxNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where phoneFax does not contain
        defaultLeadInfoFiltering("phoneFax.doesNotContain=" + UPDATED_PHONE_FAX, "phoneFax.doesNotContain=" + DEFAULT_PHONE_FAX);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed equals to
        defaultLeadInfoFiltering("dateReviewed.equals=" + DEFAULT_DATE_REVIEWED, "dateReviewed.equals=" + UPDATED_DATE_REVIEWED);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed in
        defaultLeadInfoFiltering(
            "dateReviewed.in=" + DEFAULT_DATE_REVIEWED + "," + UPDATED_DATE_REVIEWED,
            "dateReviewed.in=" + UPDATED_DATE_REVIEWED
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed is not null
        defaultLeadInfoFiltering("dateReviewed.specified=true", "dateReviewed.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed is greater than or equal to
        defaultLeadInfoFiltering(
            "dateReviewed.greaterThanOrEqual=" + DEFAULT_DATE_REVIEWED,
            "dateReviewed.greaterThanOrEqual=" + UPDATED_DATE_REVIEWED
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed is less than or equal to
        defaultLeadInfoFiltering(
            "dateReviewed.lessThanOrEqual=" + DEFAULT_DATE_REVIEWED,
            "dateReviewed.lessThanOrEqual=" + SMALLER_DATE_REVIEWED
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsLessThanSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed is less than
        defaultLeadInfoFiltering("dateReviewed.lessThan=" + UPDATED_DATE_REVIEWED, "dateReviewed.lessThan=" + DEFAULT_DATE_REVIEWED);
    }

    @Test
    @Transactional
    void getAllLeadInfosByDateReviewedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where dateReviewed is greater than
        defaultLeadInfoFiltering("dateReviewed.greaterThan=" + SMALLER_DATE_REVIEWED, "dateReviewed.greaterThan=" + DEFAULT_DATE_REVIEWED);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasis equals to
        defaultLeadInfoFiltering("lawfulBasis.equals=" + DEFAULT_LAWFUL_BASIS, "lawfulBasis.equals=" + UPDATED_LAWFUL_BASIS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasis in
        defaultLeadInfoFiltering(
            "lawfulBasis.in=" + DEFAULT_LAWFUL_BASIS + "," + UPDATED_LAWFUL_BASIS,
            "lawfulBasis.in=" + UPDATED_LAWFUL_BASIS
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasis is not null
        defaultLeadInfoFiltering("lawfulBasis.specified=true", "lawfulBasis.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasis contains
        defaultLeadInfoFiltering("lawfulBasis.contains=" + DEFAULT_LAWFUL_BASIS, "lawfulBasis.contains=" + UPDATED_LAWFUL_BASIS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasis does not contain
        defaultLeadInfoFiltering(
            "lawfulBasis.doesNotContain=" + UPDATED_LAWFUL_BASIS,
            "lawfulBasis.doesNotContain=" + DEFAULT_LAWFUL_BASIS
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisSourceIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasisSource equals to
        defaultLeadInfoFiltering(
            "lawfulBasisSource.equals=" + DEFAULT_LAWFUL_BASIS_SOURCE,
            "lawfulBasisSource.equals=" + UPDATED_LAWFUL_BASIS_SOURCE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisSourceIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasisSource in
        defaultLeadInfoFiltering(
            "lawfulBasisSource.in=" + DEFAULT_LAWFUL_BASIS_SOURCE + "," + UPDATED_LAWFUL_BASIS_SOURCE,
            "lawfulBasisSource.in=" + UPDATED_LAWFUL_BASIS_SOURCE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisSourceIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasisSource is not null
        defaultLeadInfoFiltering("lawfulBasisSource.specified=true", "lawfulBasisSource.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisSourceContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasisSource contains
        defaultLeadInfoFiltering(
            "lawfulBasisSource.contains=" + DEFAULT_LAWFUL_BASIS_SOURCE,
            "lawfulBasisSource.contains=" + UPDATED_LAWFUL_BASIS_SOURCE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLawfulBasisSourceNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where lawfulBasisSource does not contain
        defaultLeadInfoFiltering(
            "lawfulBasisSource.doesNotContain=" + UPDATED_LAWFUL_BASIS_SOURCE,
            "lawfulBasisSource.doesNotContain=" + DEFAULT_LAWFUL_BASIS_SOURCE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStreetIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressStreet equals to
        defaultLeadInfoFiltering(
            "primaryAddressStreet.equals=" + DEFAULT_PRIMARY_ADDRESS_STREET,
            "primaryAddressStreet.equals=" + UPDATED_PRIMARY_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStreetIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressStreet in
        defaultLeadInfoFiltering(
            "primaryAddressStreet.in=" + DEFAULT_PRIMARY_ADDRESS_STREET + "," + UPDATED_PRIMARY_ADDRESS_STREET,
            "primaryAddressStreet.in=" + UPDATED_PRIMARY_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStreetIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressStreet is not null
        defaultLeadInfoFiltering("primaryAddressStreet.specified=true", "primaryAddressStreet.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStreetContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressStreet contains
        defaultLeadInfoFiltering(
            "primaryAddressStreet.contains=" + DEFAULT_PRIMARY_ADDRESS_STREET,
            "primaryAddressStreet.contains=" + UPDATED_PRIMARY_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStreetNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressStreet does not contain
        defaultLeadInfoFiltering(
            "primaryAddressStreet.doesNotContain=" + UPDATED_PRIMARY_ADDRESS_STREET,
            "primaryAddressStreet.doesNotContain=" + DEFAULT_PRIMARY_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCityIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCity equals to
        defaultLeadInfoFiltering(
            "primaryAddressCity.equals=" + DEFAULT_PRIMARY_ADDRESS_CITY,
            "primaryAddressCity.equals=" + UPDATED_PRIMARY_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCityIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCity in
        defaultLeadInfoFiltering(
            "primaryAddressCity.in=" + DEFAULT_PRIMARY_ADDRESS_CITY + "," + UPDATED_PRIMARY_ADDRESS_CITY,
            "primaryAddressCity.in=" + UPDATED_PRIMARY_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCity is not null
        defaultLeadInfoFiltering("primaryAddressCity.specified=true", "primaryAddressCity.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCityContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCity contains
        defaultLeadInfoFiltering(
            "primaryAddressCity.contains=" + DEFAULT_PRIMARY_ADDRESS_CITY,
            "primaryAddressCity.contains=" + UPDATED_PRIMARY_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCityNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCity does not contain
        defaultLeadInfoFiltering(
            "primaryAddressCity.doesNotContain=" + UPDATED_PRIMARY_ADDRESS_CITY,
            "primaryAddressCity.doesNotContain=" + DEFAULT_PRIMARY_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStateIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressState equals to
        defaultLeadInfoFiltering(
            "primaryAddressState.equals=" + DEFAULT_PRIMARY_ADDRESS_STATE,
            "primaryAddressState.equals=" + UPDATED_PRIMARY_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStateIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressState in
        defaultLeadInfoFiltering(
            "primaryAddressState.in=" + DEFAULT_PRIMARY_ADDRESS_STATE + "," + UPDATED_PRIMARY_ADDRESS_STATE,
            "primaryAddressState.in=" + UPDATED_PRIMARY_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressState is not null
        defaultLeadInfoFiltering("primaryAddressState.specified=true", "primaryAddressState.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStateContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressState contains
        defaultLeadInfoFiltering(
            "primaryAddressState.contains=" + DEFAULT_PRIMARY_ADDRESS_STATE,
            "primaryAddressState.contains=" + UPDATED_PRIMARY_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressStateNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressState does not contain
        defaultLeadInfoFiltering(
            "primaryAddressState.doesNotContain=" + UPDATED_PRIMARY_ADDRESS_STATE,
            "primaryAddressState.doesNotContain=" + DEFAULT_PRIMARY_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressPostalcodeIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressPostalcode equals to
        defaultLeadInfoFiltering(
            "primaryAddressPostalcode.equals=" + DEFAULT_PRIMARY_ADDRESS_POSTALCODE,
            "primaryAddressPostalcode.equals=" + UPDATED_PRIMARY_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressPostalcodeIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressPostalcode in
        defaultLeadInfoFiltering(
            "primaryAddressPostalcode.in=" + DEFAULT_PRIMARY_ADDRESS_POSTALCODE + "," + UPDATED_PRIMARY_ADDRESS_POSTALCODE,
            "primaryAddressPostalcode.in=" + UPDATED_PRIMARY_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressPostalcodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressPostalcode is not null
        defaultLeadInfoFiltering("primaryAddressPostalcode.specified=true", "primaryAddressPostalcode.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressPostalcodeContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressPostalcode contains
        defaultLeadInfoFiltering(
            "primaryAddressPostalcode.contains=" + DEFAULT_PRIMARY_ADDRESS_POSTALCODE,
            "primaryAddressPostalcode.contains=" + UPDATED_PRIMARY_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressPostalcodeNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressPostalcode does not contain
        defaultLeadInfoFiltering(
            "primaryAddressPostalcode.doesNotContain=" + UPDATED_PRIMARY_ADDRESS_POSTALCODE,
            "primaryAddressPostalcode.doesNotContain=" + DEFAULT_PRIMARY_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCountry equals to
        defaultLeadInfoFiltering(
            "primaryAddressCountry.equals=" + DEFAULT_PRIMARY_ADDRESS_COUNTRY,
            "primaryAddressCountry.equals=" + UPDATED_PRIMARY_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCountryIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCountry in
        defaultLeadInfoFiltering(
            "primaryAddressCountry.in=" + DEFAULT_PRIMARY_ADDRESS_COUNTRY + "," + UPDATED_PRIMARY_ADDRESS_COUNTRY,
            "primaryAddressCountry.in=" + UPDATED_PRIMARY_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCountry is not null
        defaultLeadInfoFiltering("primaryAddressCountry.specified=true", "primaryAddressCountry.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCountryContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCountry contains
        defaultLeadInfoFiltering(
            "primaryAddressCountry.contains=" + DEFAULT_PRIMARY_ADDRESS_COUNTRY,
            "primaryAddressCountry.contains=" + UPDATED_PRIMARY_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByPrimaryAddressCountryNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where primaryAddressCountry does not contain
        defaultLeadInfoFiltering(
            "primaryAddressCountry.doesNotContain=" + UPDATED_PRIMARY_ADDRESS_COUNTRY,
            "primaryAddressCountry.doesNotContain=" + DEFAULT_PRIMARY_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStreetIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressStreet equals to
        defaultLeadInfoFiltering(
            "altAddressStreet.equals=" + DEFAULT_ALT_ADDRESS_STREET,
            "altAddressStreet.equals=" + UPDATED_ALT_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStreetIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressStreet in
        defaultLeadInfoFiltering(
            "altAddressStreet.in=" + DEFAULT_ALT_ADDRESS_STREET + "," + UPDATED_ALT_ADDRESS_STREET,
            "altAddressStreet.in=" + UPDATED_ALT_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStreetIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressStreet is not null
        defaultLeadInfoFiltering("altAddressStreet.specified=true", "altAddressStreet.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStreetContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressStreet contains
        defaultLeadInfoFiltering(
            "altAddressStreet.contains=" + DEFAULT_ALT_ADDRESS_STREET,
            "altAddressStreet.contains=" + UPDATED_ALT_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStreetNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressStreet does not contain
        defaultLeadInfoFiltering(
            "altAddressStreet.doesNotContain=" + UPDATED_ALT_ADDRESS_STREET,
            "altAddressStreet.doesNotContain=" + DEFAULT_ALT_ADDRESS_STREET
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCityIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCity equals to
        defaultLeadInfoFiltering("altAddressCity.equals=" + DEFAULT_ALT_ADDRESS_CITY, "altAddressCity.equals=" + UPDATED_ALT_ADDRESS_CITY);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCityIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCity in
        defaultLeadInfoFiltering(
            "altAddressCity.in=" + DEFAULT_ALT_ADDRESS_CITY + "," + UPDATED_ALT_ADDRESS_CITY,
            "altAddressCity.in=" + UPDATED_ALT_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCity is not null
        defaultLeadInfoFiltering("altAddressCity.specified=true", "altAddressCity.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCityContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCity contains
        defaultLeadInfoFiltering(
            "altAddressCity.contains=" + DEFAULT_ALT_ADDRESS_CITY,
            "altAddressCity.contains=" + UPDATED_ALT_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCityNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCity does not contain
        defaultLeadInfoFiltering(
            "altAddressCity.doesNotContain=" + UPDATED_ALT_ADDRESS_CITY,
            "altAddressCity.doesNotContain=" + DEFAULT_ALT_ADDRESS_CITY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStateIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressState equals to
        defaultLeadInfoFiltering(
            "altAddressState.equals=" + DEFAULT_ALT_ADDRESS_STATE,
            "altAddressState.equals=" + UPDATED_ALT_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStateIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressState in
        defaultLeadInfoFiltering(
            "altAddressState.in=" + DEFAULT_ALT_ADDRESS_STATE + "," + UPDATED_ALT_ADDRESS_STATE,
            "altAddressState.in=" + UPDATED_ALT_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressState is not null
        defaultLeadInfoFiltering("altAddressState.specified=true", "altAddressState.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStateContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressState contains
        defaultLeadInfoFiltering(
            "altAddressState.contains=" + DEFAULT_ALT_ADDRESS_STATE,
            "altAddressState.contains=" + UPDATED_ALT_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressStateNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressState does not contain
        defaultLeadInfoFiltering(
            "altAddressState.doesNotContain=" + UPDATED_ALT_ADDRESS_STATE,
            "altAddressState.doesNotContain=" + DEFAULT_ALT_ADDRESS_STATE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressPostalcodeIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressPostalcode equals to
        defaultLeadInfoFiltering(
            "altAddressPostalcode.equals=" + DEFAULT_ALT_ADDRESS_POSTALCODE,
            "altAddressPostalcode.equals=" + UPDATED_ALT_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressPostalcodeIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressPostalcode in
        defaultLeadInfoFiltering(
            "altAddressPostalcode.in=" + DEFAULT_ALT_ADDRESS_POSTALCODE + "," + UPDATED_ALT_ADDRESS_POSTALCODE,
            "altAddressPostalcode.in=" + UPDATED_ALT_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressPostalcodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressPostalcode is not null
        defaultLeadInfoFiltering("altAddressPostalcode.specified=true", "altAddressPostalcode.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressPostalcodeContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressPostalcode contains
        defaultLeadInfoFiltering(
            "altAddressPostalcode.contains=" + DEFAULT_ALT_ADDRESS_POSTALCODE,
            "altAddressPostalcode.contains=" + UPDATED_ALT_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressPostalcodeNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressPostalcode does not contain
        defaultLeadInfoFiltering(
            "altAddressPostalcode.doesNotContain=" + UPDATED_ALT_ADDRESS_POSTALCODE,
            "altAddressPostalcode.doesNotContain=" + DEFAULT_ALT_ADDRESS_POSTALCODE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCountry equals to
        defaultLeadInfoFiltering(
            "altAddressCountry.equals=" + DEFAULT_ALT_ADDRESS_COUNTRY,
            "altAddressCountry.equals=" + UPDATED_ALT_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCountryIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCountry in
        defaultLeadInfoFiltering(
            "altAddressCountry.in=" + DEFAULT_ALT_ADDRESS_COUNTRY + "," + UPDATED_ALT_ADDRESS_COUNTRY,
            "altAddressCountry.in=" + UPDATED_ALT_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCountry is not null
        defaultLeadInfoFiltering("altAddressCountry.specified=true", "altAddressCountry.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCountryContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCountry contains
        defaultLeadInfoFiltering(
            "altAddressCountry.contains=" + DEFAULT_ALT_ADDRESS_COUNTRY,
            "altAddressCountry.contains=" + UPDATED_ALT_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAltAddressCountryNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where altAddressCountry does not contain
        defaultLeadInfoFiltering(
            "altAddressCountry.doesNotContain=" + UPDATED_ALT_ADDRESS_COUNTRY,
            "altAddressCountry.doesNotContain=" + DEFAULT_ALT_ADDRESS_COUNTRY
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistant equals to
        defaultLeadInfoFiltering("assistant.equals=" + DEFAULT_ASSISTANT, "assistant.equals=" + UPDATED_ASSISTANT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistant in
        defaultLeadInfoFiltering("assistant.in=" + DEFAULT_ASSISTANT + "," + UPDATED_ASSISTANT, "assistant.in=" + UPDATED_ASSISTANT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistant is not null
        defaultLeadInfoFiltering("assistant.specified=true", "assistant.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistant contains
        defaultLeadInfoFiltering("assistant.contains=" + DEFAULT_ASSISTANT, "assistant.contains=" + UPDATED_ASSISTANT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistant does not contain
        defaultLeadInfoFiltering("assistant.doesNotContain=" + UPDATED_ASSISTANT, "assistant.doesNotContain=" + DEFAULT_ASSISTANT);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistantPhone equals to
        defaultLeadInfoFiltering("assistantPhone.equals=" + DEFAULT_ASSISTANT_PHONE, "assistantPhone.equals=" + UPDATED_ASSISTANT_PHONE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistantPhone in
        defaultLeadInfoFiltering(
            "assistantPhone.in=" + DEFAULT_ASSISTANT_PHONE + "," + UPDATED_ASSISTANT_PHONE,
            "assistantPhone.in=" + UPDATED_ASSISTANT_PHONE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistantPhone is not null
        defaultLeadInfoFiltering("assistantPhone.specified=true", "assistantPhone.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantPhoneContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistantPhone contains
        defaultLeadInfoFiltering(
            "assistantPhone.contains=" + DEFAULT_ASSISTANT_PHONE,
            "assistantPhone.contains=" + UPDATED_ASSISTANT_PHONE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssistantPhoneNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where assistantPhone does not contain
        defaultLeadInfoFiltering(
            "assistantPhone.doesNotContain=" + UPDATED_ASSISTANT_PHONE,
            "assistantPhone.doesNotContain=" + DEFAULT_ASSISTANT_PHONE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByConvertedIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where converted equals to
        defaultLeadInfoFiltering("converted.equals=" + DEFAULT_CONVERTED, "converted.equals=" + UPDATED_CONVERTED);
    }

    @Test
    @Transactional
    void getAllLeadInfosByConvertedIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where converted in
        defaultLeadInfoFiltering("converted.in=" + DEFAULT_CONVERTED + "," + UPDATED_CONVERTED, "converted.in=" + UPDATED_CONVERTED);
    }

    @Test
    @Transactional
    void getAllLeadInfosByConvertedIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where converted is not null
        defaultLeadInfoFiltering("converted.specified=true", "converted.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByReferedByIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where referedBy equals to
        defaultLeadInfoFiltering("referedBy.equals=" + DEFAULT_REFERED_BY, "referedBy.equals=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    void getAllLeadInfosByReferedByIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where referedBy in
        defaultLeadInfoFiltering("referedBy.in=" + DEFAULT_REFERED_BY + "," + UPDATED_REFERED_BY, "referedBy.in=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    void getAllLeadInfosByReferedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where referedBy is not null
        defaultLeadInfoFiltering("referedBy.specified=true", "referedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByReferedByContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where referedBy contains
        defaultLeadInfoFiltering("referedBy.contains=" + DEFAULT_REFERED_BY, "referedBy.contains=" + UPDATED_REFERED_BY);
    }

    @Test
    @Transactional
    void getAllLeadInfosByReferedByNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where referedBy does not contain
        defaultLeadInfoFiltering("referedBy.doesNotContain=" + UPDATED_REFERED_BY, "referedBy.doesNotContain=" + DEFAULT_REFERED_BY);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSource equals to
        defaultLeadInfoFiltering("leadSource.equals=" + DEFAULT_LEAD_SOURCE, "leadSource.equals=" + UPDATED_LEAD_SOURCE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSource in
        defaultLeadInfoFiltering(
            "leadSource.in=" + DEFAULT_LEAD_SOURCE + "," + UPDATED_LEAD_SOURCE,
            "leadSource.in=" + UPDATED_LEAD_SOURCE
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSource is not null
        defaultLeadInfoFiltering("leadSource.specified=true", "leadSource.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSource contains
        defaultLeadInfoFiltering("leadSource.contains=" + DEFAULT_LEAD_SOURCE, "leadSource.contains=" + UPDATED_LEAD_SOURCE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSource does not contain
        defaultLeadInfoFiltering("leadSource.doesNotContain=" + UPDATED_LEAD_SOURCE, "leadSource.doesNotContain=" + DEFAULT_LEAD_SOURCE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSourceDescription equals to
        defaultLeadInfoFiltering(
            "leadSourceDescription.equals=" + DEFAULT_LEAD_SOURCE_DESCRIPTION,
            "leadSourceDescription.equals=" + UPDATED_LEAD_SOURCE_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSourceDescription in
        defaultLeadInfoFiltering(
            "leadSourceDescription.in=" + DEFAULT_LEAD_SOURCE_DESCRIPTION + "," + UPDATED_LEAD_SOURCE_DESCRIPTION,
            "leadSourceDescription.in=" + UPDATED_LEAD_SOURCE_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSourceDescription is not null
        defaultLeadInfoFiltering("leadSourceDescription.specified=true", "leadSourceDescription.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceDescriptionContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSourceDescription contains
        defaultLeadInfoFiltering(
            "leadSourceDescription.contains=" + DEFAULT_LEAD_SOURCE_DESCRIPTION,
            "leadSourceDescription.contains=" + UPDATED_LEAD_SOURCE_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByLeadSourceDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where leadSourceDescription does not contain
        defaultLeadInfoFiltering(
            "leadSourceDescription.doesNotContain=" + UPDATED_LEAD_SOURCE_DESCRIPTION,
            "leadSourceDescription.doesNotContain=" + DEFAULT_LEAD_SOURCE_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where status equals to
        defaultLeadInfoFiltering("status.equals=" + DEFAULT_STATUS, "status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where status in
        defaultLeadInfoFiltering("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS, "status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where status is not null
        defaultLeadInfoFiltering("status.specified=true", "status.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where status contains
        defaultLeadInfoFiltering("status.contains=" + DEFAULT_STATUS, "status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where status does not contain
        defaultLeadInfoFiltering("status.doesNotContain=" + UPDATED_STATUS, "status.doesNotContain=" + DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where statusDescription equals to
        defaultLeadInfoFiltering(
            "statusDescription.equals=" + DEFAULT_STATUS_DESCRIPTION,
            "statusDescription.equals=" + UPDATED_STATUS_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where statusDescription in
        defaultLeadInfoFiltering(
            "statusDescription.in=" + DEFAULT_STATUS_DESCRIPTION + "," + UPDATED_STATUS_DESCRIPTION,
            "statusDescription.in=" + UPDATED_STATUS_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where statusDescription is not null
        defaultLeadInfoFiltering("statusDescription.specified=true", "statusDescription.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusDescriptionContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where statusDescription contains
        defaultLeadInfoFiltering(
            "statusDescription.contains=" + DEFAULT_STATUS_DESCRIPTION,
            "statusDescription.contains=" + UPDATED_STATUS_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByStatusDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where statusDescription does not contain
        defaultLeadInfoFiltering(
            "statusDescription.doesNotContain=" + UPDATED_STATUS_DESCRIPTION,
            "statusDescription.doesNotContain=" + DEFAULT_STATUS_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate equals to
        defaultLeadInfoFiltering("birthdate.equals=" + DEFAULT_BIRTHDATE, "birthdate.equals=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsInShouldWork() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate in
        defaultLeadInfoFiltering("birthdate.in=" + DEFAULT_BIRTHDATE + "," + UPDATED_BIRTHDATE, "birthdate.in=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate is not null
        defaultLeadInfoFiltering("birthdate.specified=true", "birthdate.specified=false");
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate is greater than or equal to
        defaultLeadInfoFiltering("birthdate.greaterThanOrEqual=" + DEFAULT_BIRTHDATE, "birthdate.greaterThanOrEqual=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate is less than or equal to
        defaultLeadInfoFiltering("birthdate.lessThanOrEqual=" + DEFAULT_BIRTHDATE, "birthdate.lessThanOrEqual=" + SMALLER_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsLessThanSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate is less than
        defaultLeadInfoFiltering("birthdate.lessThan=" + UPDATED_BIRTHDATE, "birthdate.lessThan=" + DEFAULT_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByBirthdateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        // Get all the leadInfoList where birthdate is greater than
        defaultLeadInfoFiltering("birthdate.greaterThan=" + SMALLER_BIRTHDATE, "birthdate.greaterThan=" + DEFAULT_BIRTHDATE);
    }

    @Test
    @Transactional
    void getAllLeadInfosByReportsToIsEqualToSomething() throws Exception {
        LeadInfo reportsTo;
        if (TestUtil.findAll(em, LeadInfo.class).isEmpty()) {
            leadInfoRepository.saveAndFlush(leadInfo);
            reportsTo = LeadInfoResourceIT.createEntity(em);
        } else {
            reportsTo = TestUtil.findAll(em, LeadInfo.class).get(0);
        }
        em.persist(reportsTo);
        em.flush();
        leadInfo.setReportsTo(reportsTo);
        leadInfoRepository.saveAndFlush(leadInfo);
        Long reportsToId = reportsTo.getId();
        // Get all the leadInfoList where reportsTo equals to reportsToId
        defaultLeadInfoShouldBeFound("reportsToId.equals=" + reportsToId);

        // Get all the leadInfoList where reportsTo equals to (reportsToId + 1)
        defaultLeadInfoShouldNotBeFound("reportsToId.equals=" + (reportsToId + 1));
    }

    @Test
    @Transactional
    void getAllLeadInfosByAssignedUserIsEqualToSomething() throws Exception {
        User assignedUser;
        if (TestUtil.findAll(em, User.class).isEmpty()) {
            leadInfoRepository.saveAndFlush(leadInfo);
            assignedUser = UserResourceIT.createEntity(em);
        } else {
            assignedUser = TestUtil.findAll(em, User.class).get(0);
        }
        em.persist(assignedUser);
        em.flush();
        leadInfo.setAssignedUser(assignedUser);
        leadInfoRepository.saveAndFlush(leadInfo);
        Long assignedUserId = assignedUser.getId();
        // Get all the leadInfoList where assignedUser equals to assignedUserId
        defaultLeadInfoShouldBeFound("assignedUserId.equals=" + assignedUserId);

        // Get all the leadInfoList where assignedUser equals to (assignedUserId + 1)
        defaultLeadInfoShouldNotBeFound("assignedUserId.equals=" + (assignedUserId + 1));
    }

    private void defaultLeadInfoFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultLeadInfoShouldBeFound(shouldBeFound);
        defaultLeadInfoShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLeadInfoShouldBeFound(String filter) throws Exception {
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leadInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].doNotCall").value(hasItem(DEFAULT_DO_NOT_CALL.booleanValue())))
            .andExpect(jsonPath("$.[*].phoneHome").value(hasItem(DEFAULT_PHONE_HOME)))
            .andExpect(jsonPath("$.[*].phoneMobile").value(hasItem(DEFAULT_PHONE_MOBILE)))
            .andExpect(jsonPath("$.[*].phoneWork").value(hasItem(DEFAULT_PHONE_WORK)))
            .andExpect(jsonPath("$.[*].phoneOther").value(hasItem(DEFAULT_PHONE_OTHER)))
            .andExpect(jsonPath("$.[*].phoneFax").value(hasItem(DEFAULT_PHONE_FAX)))
            .andExpect(jsonPath("$.[*].dateReviewed").value(hasItem(DEFAULT_DATE_REVIEWED.toString())))
            .andExpect(jsonPath("$.[*].lawfulBasis").value(hasItem(DEFAULT_LAWFUL_BASIS)))
            .andExpect(jsonPath("$.[*].lawfulBasisSource").value(hasItem(DEFAULT_LAWFUL_BASIS_SOURCE)))
            .andExpect(jsonPath("$.[*].primaryAddressStreet").value(hasItem(DEFAULT_PRIMARY_ADDRESS_STREET)))
            .andExpect(jsonPath("$.[*].primaryAddressCity").value(hasItem(DEFAULT_PRIMARY_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].primaryAddressState").value(hasItem(DEFAULT_PRIMARY_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].primaryAddressPostalcode").value(hasItem(DEFAULT_PRIMARY_ADDRESS_POSTALCODE)))
            .andExpect(jsonPath("$.[*].primaryAddressCountry").value(hasItem(DEFAULT_PRIMARY_ADDRESS_COUNTRY)))
            .andExpect(jsonPath("$.[*].altAddressStreet").value(hasItem(DEFAULT_ALT_ADDRESS_STREET)))
            .andExpect(jsonPath("$.[*].altAddressCity").value(hasItem(DEFAULT_ALT_ADDRESS_CITY)))
            .andExpect(jsonPath("$.[*].altAddressState").value(hasItem(DEFAULT_ALT_ADDRESS_STATE)))
            .andExpect(jsonPath("$.[*].altAddressPostalcode").value(hasItem(DEFAULT_ALT_ADDRESS_POSTALCODE)))
            .andExpect(jsonPath("$.[*].altAddressCountry").value(hasItem(DEFAULT_ALT_ADDRESS_COUNTRY)))
            .andExpect(jsonPath("$.[*].assistant").value(hasItem(DEFAULT_ASSISTANT)))
            .andExpect(jsonPath("$.[*].assistantPhone").value(hasItem(DEFAULT_ASSISTANT_PHONE)))
            .andExpect(jsonPath("$.[*].converted").value(hasItem(DEFAULT_CONVERTED.booleanValue())))
            .andExpect(jsonPath("$.[*].referedBy").value(hasItem(DEFAULT_REFERED_BY)))
            .andExpect(jsonPath("$.[*].leadSource").value(hasItem(DEFAULT_LEAD_SOURCE)))
            .andExpect(jsonPath("$.[*].leadSourceDescription").value(hasItem(DEFAULT_LEAD_SOURCE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())));

        // Check, that the count call also returns 1
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLeadInfoShouldNotBeFound(String filter) throws Exception {
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLeadInfoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingLeadInfo() throws Exception {
        // Get the leadInfo
        restLeadInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLeadInfo() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leadInfo
        LeadInfo updatedLeadInfo = leadInfoRepository.findById(leadInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLeadInfo are not directly saved in db
        em.detach(updatedLeadInfo);
        updatedLeadInfo
            .description(UPDATED_DESCRIPTION)
            .salutation(UPDATED_SALUTATION)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .department(UPDATED_DEPARTMENT)
            .doNotCall(UPDATED_DO_NOT_CALL)
            .phoneHome(UPDATED_PHONE_HOME)
            .phoneMobile(UPDATED_PHONE_MOBILE)
            .phoneWork(UPDATED_PHONE_WORK)
            .phoneOther(UPDATED_PHONE_OTHER)
            .phoneFax(UPDATED_PHONE_FAX)
            .dateReviewed(UPDATED_DATE_REVIEWED)
            .lawfulBasis(UPDATED_LAWFUL_BASIS)
            .lawfulBasisSource(UPDATED_LAWFUL_BASIS_SOURCE)
            .primaryAddressStreet(UPDATED_PRIMARY_ADDRESS_STREET)
            .primaryAddressCity(UPDATED_PRIMARY_ADDRESS_CITY)
            .primaryAddressState(UPDATED_PRIMARY_ADDRESS_STATE)
            .primaryAddressPostalcode(UPDATED_PRIMARY_ADDRESS_POSTALCODE)
            .primaryAddressCountry(UPDATED_PRIMARY_ADDRESS_COUNTRY)
            .altAddressStreet(UPDATED_ALT_ADDRESS_STREET)
            .altAddressCity(UPDATED_ALT_ADDRESS_CITY)
            .altAddressState(UPDATED_ALT_ADDRESS_STATE)
            .altAddressPostalcode(UPDATED_ALT_ADDRESS_POSTALCODE)
            .altAddressCountry(UPDATED_ALT_ADDRESS_COUNTRY)
            .assistant(UPDATED_ASSISTANT)
            .assistantPhone(UPDATED_ASSISTANT_PHONE)
            .converted(UPDATED_CONVERTED)
            .referedBy(UPDATED_REFERED_BY)
            .leadSource(UPDATED_LEAD_SOURCE)
            .leadSourceDescription(UPDATED_LEAD_SOURCE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .statusDescription(UPDATED_STATUS_DESCRIPTION)
            .birthdate(UPDATED_BIRTHDATE);
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(updatedLeadInfo);

        restLeadInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leadInfoDto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(leadInfoDto))
            )
            .andExpect(status().isOk());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLeadInfoToMatchAllProperties(updatedLeadInfo);
    }

    @Test
    @Transactional
    void putNonExistingLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, leadInfoDto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(leadInfoDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(leadInfoDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(leadInfoDto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLeadInfoWithPatch() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leadInfo using partial update
        LeadInfo partialUpdatedLeadInfo = new LeadInfo();
        partialUpdatedLeadInfo.setId(leadInfo.getId());

        partialUpdatedLeadInfo
            .description(UPDATED_DESCRIPTION)
            .salutation(UPDATED_SALUTATION)
            .firstName(UPDATED_FIRST_NAME)
            .phoneMobile(UPDATED_PHONE_MOBILE)
            .phoneWork(UPDATED_PHONE_WORK)
            .lawfulBasis(UPDATED_LAWFUL_BASIS)
            .lawfulBasisSource(UPDATED_LAWFUL_BASIS_SOURCE)
            .primaryAddressStreet(UPDATED_PRIMARY_ADDRESS_STREET)
            .primaryAddressPostalcode(UPDATED_PRIMARY_ADDRESS_POSTALCODE)
            .converted(UPDATED_CONVERTED)
            .referedBy(UPDATED_REFERED_BY)
            .leadSource(UPDATED_LEAD_SOURCE)
            .leadSourceDescription(UPDATED_LEAD_SOURCE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .birthdate(UPDATED_BIRTHDATE);

        restLeadInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeadInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLeadInfo))
            )
            .andExpect(status().isOk());

        // Validate the LeadInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeadInfoUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedLeadInfo, leadInfo), getPersistedLeadInfo(leadInfo));
    }

    @Test
    @Transactional
    void fullUpdateLeadInfoWithPatch() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the leadInfo using partial update
        LeadInfo partialUpdatedLeadInfo = new LeadInfo();
        partialUpdatedLeadInfo.setId(leadInfo.getId());

        partialUpdatedLeadInfo
            .description(UPDATED_DESCRIPTION)
            .salutation(UPDATED_SALUTATION)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .department(UPDATED_DEPARTMENT)
            .doNotCall(UPDATED_DO_NOT_CALL)
            .phoneHome(UPDATED_PHONE_HOME)
            .phoneMobile(UPDATED_PHONE_MOBILE)
            .phoneWork(UPDATED_PHONE_WORK)
            .phoneOther(UPDATED_PHONE_OTHER)
            .phoneFax(UPDATED_PHONE_FAX)
            .dateReviewed(UPDATED_DATE_REVIEWED)
            .lawfulBasis(UPDATED_LAWFUL_BASIS)
            .lawfulBasisSource(UPDATED_LAWFUL_BASIS_SOURCE)
            .primaryAddressStreet(UPDATED_PRIMARY_ADDRESS_STREET)
            .primaryAddressCity(UPDATED_PRIMARY_ADDRESS_CITY)
            .primaryAddressState(UPDATED_PRIMARY_ADDRESS_STATE)
            .primaryAddressPostalcode(UPDATED_PRIMARY_ADDRESS_POSTALCODE)
            .primaryAddressCountry(UPDATED_PRIMARY_ADDRESS_COUNTRY)
            .altAddressStreet(UPDATED_ALT_ADDRESS_STREET)
            .altAddressCity(UPDATED_ALT_ADDRESS_CITY)
            .altAddressState(UPDATED_ALT_ADDRESS_STATE)
            .altAddressPostalcode(UPDATED_ALT_ADDRESS_POSTALCODE)
            .altAddressCountry(UPDATED_ALT_ADDRESS_COUNTRY)
            .assistant(UPDATED_ASSISTANT)
            .assistantPhone(UPDATED_ASSISTANT_PHONE)
            .converted(UPDATED_CONVERTED)
            .referedBy(UPDATED_REFERED_BY)
            .leadSource(UPDATED_LEAD_SOURCE)
            .leadSourceDescription(UPDATED_LEAD_SOURCE_DESCRIPTION)
            .status(UPDATED_STATUS)
            .statusDescription(UPDATED_STATUS_DESCRIPTION)
            .birthdate(UPDATED_BIRTHDATE);

        restLeadInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLeadInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLeadInfo))
            )
            .andExpect(status().isOk());

        // Validate the LeadInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeadInfoUpdatableFieldsEquals(partialUpdatedLeadInfo, getPersistedLeadInfo(partialUpdatedLeadInfo));
    }

    @Test
    @Transactional
    void patchNonExistingLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, leadInfoDto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(leadInfoDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(leadInfoDto))
            )
            .andExpect(status().isBadRequest());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLeadInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        leadInfo.setId(longCount.incrementAndGet());

        // Create the LeadInfo
        LeadInfoDto leadInfoDto = leadInfoMapper.toDto(leadInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLeadInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(leadInfoDto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LeadInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLeadInfo() throws Exception {
        // Initialize the database
        leadInfoRepository.saveAndFlush(leadInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the leadInfo
        restLeadInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, leadInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return leadInfoRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected LeadInfo getPersistedLeadInfo(LeadInfo leadInfo) {
        return leadInfoRepository.findById(leadInfo.getId()).orElseThrow();
    }

    protected void assertPersistedLeadInfoToMatchAllProperties(LeadInfo expectedLeadInfo) {
        assertLeadInfoAllPropertiesEquals(expectedLeadInfo, getPersistedLeadInfo(expectedLeadInfo));
    }

    protected void assertPersistedLeadInfoToMatchUpdatableProperties(LeadInfo expectedLeadInfo) {
        assertLeadInfoAllUpdatablePropertiesEquals(expectedLeadInfo, getPersistedLeadInfo(expectedLeadInfo));
    }
}
