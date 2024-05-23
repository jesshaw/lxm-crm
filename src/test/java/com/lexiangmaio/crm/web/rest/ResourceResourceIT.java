package com.lexiangmaio.crm.web.rest;

import static com.lexiangmaio.crm.domain.ResourceAsserts.*;
import static com.lexiangmaio.crm.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lexiangmaio.crm.IntegrationTest;
import com.lexiangmaio.crm.domain.Resource;
import com.lexiangmaio.crm.repository.ResourceRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERMISSION = "AAAAAAAAAA";
    private static final String UPDATED_PERMISSION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/resources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceMockMvc;

    private Resource resource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resource createEntity(EntityManager em) {
        Resource resource = new Resource().name(DEFAULT_NAME).permission(DEFAULT_PERMISSION);
        return resource;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resource createUpdatedEntity(EntityManager em) {
        Resource resource = new Resource().name(UPDATED_NAME).permission(UPDATED_PERMISSION);
        return resource;
    }

    @BeforeEach
    public void initTest() {
        resource = createEntity(em);
    }

    @Test
    @Transactional
    void createResource() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Resource
        var returnedResource = om.readValue(
            restResourceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resource)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Resource.class
        );

        // Validate the Resource in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResourceUpdatableFieldsEquals(returnedResource, getPersistedResource(returnedResource));
    }

    @Test
    @Transactional
    void createResourceWithExistingId() throws Exception {
        // Create the Resource with an existing ID
        resource.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resource)))
            .andExpect(status().isBadRequest());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResources() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        // Get all the resourceList
        restResourceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resource.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].permission").value(hasItem(DEFAULT_PERMISSION)));
    }

    @Test
    @Transactional
    void getResource() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        // Get the resource
        restResourceMockMvc
            .perform(get(ENTITY_API_URL_ID, resource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resource.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.permission").value(DEFAULT_PERMISSION));
    }

    @Test
    @Transactional
    void getNonExistingResource() throws Exception {
        // Get the resource
        restResourceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResource() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resource
        Resource updatedResource = resourceRepository.findById(resource.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResource are not directly saved in db
        em.detach(updatedResource);
        updatedResource.name(UPDATED_NAME).permission(UPDATED_PERMISSION);

        restResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResource.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResource))
            )
            .andExpect(status().isOk());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResourceToMatchAllProperties(updatedResource);
    }

    @Test
    @Transactional
    void putNonExistingResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resource.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resource))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resource))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resource)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourceWithPatch() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resource using partial update
        Resource partialUpdatedResource = new Resource();
        partialUpdatedResource.setId(resource.getId());

        partialUpdatedResource.permission(UPDATED_PERMISSION);

        restResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResource))
            )
            .andExpect(status().isOk());

        // Validate the Resource in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourceUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedResource, resource), getPersistedResource(resource));
    }

    @Test
    @Transactional
    void fullUpdateResourceWithPatch() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resource using partial update
        Resource partialUpdatedResource = new Resource();
        partialUpdatedResource.setId(resource.getId());

        partialUpdatedResource.name(UPDATED_NAME).permission(UPDATED_PERMISSION);

        restResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResource.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResource))
            )
            .andExpect(status().isOk());

        // Validate the Resource in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourceUpdatableFieldsEquals(partialUpdatedResource, getPersistedResource(partialUpdatedResource));
    }

    @Test
    @Transactional
    void patchNonExistingResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resource.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resource))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resource))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResource() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resource.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resource)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resource in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResource() throws Exception {
        // Initialize the database
        resourceRepository.saveAndFlush(resource);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resource
        restResourceMockMvc
            .perform(delete(ENTITY_API_URL_ID, resource.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resourceRepository.count();
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

    protected Resource getPersistedResource(Resource resource) {
        return resourceRepository.findById(resource.getId()).orElseThrow();
    }

    protected void assertPersistedResourceToMatchAllProperties(Resource expectedResource) {
        assertResourceAllPropertiesEquals(expectedResource, getPersistedResource(expectedResource));
    }

    protected void assertPersistedResourceToMatchUpdatableProperties(Resource expectedResource) {
        assertResourceAllUpdatablePropertiesEquals(expectedResource, getPersistedResource(expectedResource));
    }
}
