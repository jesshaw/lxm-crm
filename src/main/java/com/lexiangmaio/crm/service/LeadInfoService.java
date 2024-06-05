package com.lexiangmaio.crm.service;

import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.lexiangmaio.crm.domain.LeadInfo}.
 */
public interface LeadInfoService {
    /**
     * Save a leadInfo.
     *
     * @param leadInfoDto the entity to save.
     * @return the persisted entity.
     */
    LeadInfoDto save(LeadInfoDto leadInfoDto);

    /**
     * Updates a leadInfo.
     *
     * @param leadInfoDto the entity to update.
     * @return the persisted entity.
     */
    LeadInfoDto update(LeadInfoDto leadInfoDto);

    /**
     * Partially updates a leadInfo.
     *
     * @param leadInfoDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LeadInfoDto> partialUpdate(LeadInfoDto leadInfoDto);

    /**
     * Get all the leadInfos with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LeadInfoDto> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" leadInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LeadInfoDto> findOne(Long id);

    /**
     * Delete the "id" leadInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
