package com.lexiangmaio.crm.web.rest;

import com.lexiangmaio.crm.repository.LeadInfoRepository;
import com.lexiangmaio.crm.security.PermissionConstants;
import com.lexiangmaio.crm.security.ResourceConstants;
import com.lexiangmaio.crm.service.LeadInfoQueryService;
import com.lexiangmaio.crm.service.LeadInfoService;
import com.lexiangmaio.crm.service.criteria.LeadInfoCriteria;
import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import com.lexiangmaio.crm.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.lexiangmaio.crm.domain.LeadInfo}.
 */
@RestController
@RequestMapping("/api/lead-infos")
public class LeadInfoResource {

    private static final Logger LOG = LoggerFactory.getLogger(LeadInfoResource.class);

    private static final String ENTITY_NAME = "leadInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeadInfoService leadInfoService;

    private final LeadInfoRepository leadInfoRepository;

    private final LeadInfoQueryService leadInfoQueryService;

    public LeadInfoResource(
        LeadInfoService leadInfoService,
        LeadInfoRepository leadInfoRepository,
        LeadInfoQueryService leadInfoQueryService
    ) {
        this.leadInfoService = leadInfoService;
        this.leadInfoRepository = leadInfoRepository;
        this.leadInfoQueryService = leadInfoQueryService;
    }

    /**
     * {@code POST  /lead-infos} : Create a new leadInfo.
     *
     * @param leadInfoDto the leadInfoDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leadInfoDto, or with status {@code 400 (Bad Request)} if the leadInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.EDIT + "\")")
    public ResponseEntity<LeadInfoDto> createLeadInfo(@Valid @RequestBody LeadInfoDto leadInfoDto) throws URISyntaxException {
        LOG.debug("REST request to save LeadInfo : {}", leadInfoDto);
        if (leadInfoDto.getId() != null) {
            throw new BadRequestAlertException("A new leadInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        leadInfoDto = leadInfoService.save(leadInfoDto);
        return ResponseEntity.created(new URI("/api/lead-infos/" + leadInfoDto.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, leadInfoDto.getId().toString()))
            .body(leadInfoDto);
    }

    /**
     * {@code PUT  /lead-infos/:id} : Updates an existing leadInfo.
     *
     * @param id the id of the leadInfoDto to save.
     * @param leadInfoDto the leadInfoDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leadInfoDto,
     * or with status {@code 400 (Bad Request)} if the leadInfoDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leadInfoDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.EDIT + "\")")
    public ResponseEntity<LeadInfoDto> updateLeadInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LeadInfoDto leadInfoDto
    ) throws URISyntaxException {
        LOG.debug("REST request to update LeadInfo : {}, {}", id, leadInfoDto);
        if (leadInfoDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leadInfoDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leadInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        leadInfoDto = leadInfoService.update(leadInfoDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leadInfoDto.getId().toString()))
            .body(leadInfoDto);
    }

    /**
     * {@code PATCH  /lead-infos/:id} : Partial updates given fields of an existing leadInfo, field will ignore if it is null
     *
     * @param id the id of the leadInfoDto to save.
     * @param leadInfoDto the leadInfoDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leadInfoDto,
     * or with status {@code 400 (Bad Request)} if the leadInfoDto is not valid,
     * or with status {@code 404 (Not Found)} if the leadInfoDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the leadInfoDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.EDIT + "\")")
    public ResponseEntity<LeadInfoDto> partialUpdateLeadInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LeadInfoDto leadInfoDto
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update LeadInfo partially : {}, {}", id, leadInfoDto);
        if (leadInfoDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leadInfoDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leadInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeadInfoDto> result = leadInfoService.partialUpdate(leadInfoDto);
        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leadInfoDto.getId().toString())
        );
    }

    /**
     * {@code GET  /lead-infos} : get all the leadInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leadInfos in body.
     */
    @GetMapping("")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.LIST + "\")")
    public ResponseEntity<List<LeadInfoDto>> getAllLeadInfos(
        LeadInfoCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get LeadInfos by criteria: {}", criteria);

        Page<LeadInfoDto> page = leadInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lead-infos/count} : count all the leadInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.LIST + "\")")
    public ResponseEntity<Long> countLeadInfos(LeadInfoCriteria criteria) {
        LOG.debug("REST request to count LeadInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(leadInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /lead-infos/:id} : get the "id" leadInfo.
     *
     * @param id the id of the leadInfoDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leadInfoDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.VIEW + "\")")
    public ResponseEntity<LeadInfoDto> getLeadInfo(@PathVariable("id") Long id) {
        LOG.debug("REST request to get LeadInfo : {}", id);
        Optional<LeadInfoDto> leadInfoDto = leadInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leadInfoDto);
    }

    /**
     * {@code DELETE  /lead-infos/:id} : delete the "id" leadInfo.
     *
     * @param id the id of the leadInfoDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@lxmAuth.hasPermission(\"" + ResourceConstants.LEAD_INFO + "\",\"" + PermissionConstants.DELETE + "\")")
    public ResponseEntity<Void> deleteLeadInfo(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete LeadInfo : {}", id);
        leadInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
