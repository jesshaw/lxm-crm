package com.lexiangmaio.crm.service;

import com.lexiangmaio.crm.domain.*; // for static metamodels
import com.lexiangmaio.crm.domain.LeadInfo;
import com.lexiangmaio.crm.repository.LeadInfoRepository;
import com.lexiangmaio.crm.service.criteria.LeadInfoCriteria;
import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import com.lexiangmaio.crm.service.mapper.LeadInfoMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link LeadInfo} entities in the database.
 * The main input is a {@link LeadInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link LeadInfoDto} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LeadInfoQueryService extends QueryService<LeadInfo> {

    private final Logger log = LoggerFactory.getLogger(LeadInfoQueryService.class);

    private final LeadInfoRepository leadInfoRepository;

    private final LeadInfoMapper leadInfoMapper;

    public LeadInfoQueryService(LeadInfoRepository leadInfoRepository, LeadInfoMapper leadInfoMapper) {
        this.leadInfoRepository = leadInfoRepository;
        this.leadInfoMapper = leadInfoMapper;
    }

    /**
     * Return a {@link Page} of {@link LeadInfoDto} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LeadInfoDto> findByCriteria(LeadInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LeadInfo> specification = createSpecification(criteria);
        return leadInfoRepository.findAll(specification, page).map(leadInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LeadInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LeadInfo> specification = createSpecification(criteria);
        return leadInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link LeadInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LeadInfo> createSpecification(LeadInfoCriteria criteria) {
        Specification<LeadInfo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LeadInfo_.id));
            }
            if (criteria.getSalutation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSalutation(), LeadInfo_.salutation));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), LeadInfo_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), LeadInfo_.lastName));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LeadInfo_.title));
            }
            if (criteria.getDepartment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepartment(), LeadInfo_.department));
            }
            if (criteria.getDoNotCall() != null) {
                specification = specification.and(buildSpecification(criteria.getDoNotCall(), LeadInfo_.doNotCall));
            }
            if (criteria.getPhoneHome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneHome(), LeadInfo_.phoneHome));
            }
            if (criteria.getPhoneMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneMobile(), LeadInfo_.phoneMobile));
            }
            if (criteria.getPhoneWork() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneWork(), LeadInfo_.phoneWork));
            }
            if (criteria.getPhoneOther() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneOther(), LeadInfo_.phoneOther));
            }
            if (criteria.getPhoneFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneFax(), LeadInfo_.phoneFax));
            }
            if (criteria.getDateReviewed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateReviewed(), LeadInfo_.dateReviewed));
            }
            if (criteria.getLawfulBasis() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLawfulBasis(), LeadInfo_.lawfulBasis));
            }
            if (criteria.getLawfulBasisSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLawfulBasisSource(), LeadInfo_.lawfulBasisSource));
            }
            if (criteria.getPrimaryAddressStreet() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPrimaryAddressStreet(), LeadInfo_.primaryAddressStreet)
                );
            }
            if (criteria.getPrimaryAddressCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrimaryAddressCity(), LeadInfo_.primaryAddressCity));
            }
            if (criteria.getPrimaryAddressState() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPrimaryAddressState(), LeadInfo_.primaryAddressState)
                );
            }
            if (criteria.getPrimaryAddressPostalcode() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPrimaryAddressPostalcode(), LeadInfo_.primaryAddressPostalcode)
                );
            }
            if (criteria.getPrimaryAddressCountry() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPrimaryAddressCountry(), LeadInfo_.primaryAddressCountry)
                );
            }
            if (criteria.getAltAddressStreet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltAddressStreet(), LeadInfo_.altAddressStreet));
            }
            if (criteria.getAltAddressCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltAddressCity(), LeadInfo_.altAddressCity));
            }
            if (criteria.getAltAddressState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltAddressState(), LeadInfo_.altAddressState));
            }
            if (criteria.getAltAddressPostalcode() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAltAddressPostalcode(), LeadInfo_.altAddressPostalcode)
                );
            }
            if (criteria.getAltAddressCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltAddressCountry(), LeadInfo_.altAddressCountry));
            }
            if (criteria.getAssistant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssistant(), LeadInfo_.assistant));
            }
            if (criteria.getAssistantPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssistantPhone(), LeadInfo_.assistantPhone));
            }
            if (criteria.getConverted() != null) {
                specification = specification.and(buildSpecification(criteria.getConverted(), LeadInfo_.converted));
            }
            if (criteria.getReferedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferedBy(), LeadInfo_.referedBy));
            }
            if (criteria.getLeadSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeadSource(), LeadInfo_.leadSource));
            }
            if (criteria.getLeadSourceDescription() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getLeadSourceDescription(), LeadInfo_.leadSourceDescription)
                );
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), LeadInfo_.status));
            }
            if (criteria.getStatusDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusDescription(), LeadInfo_.statusDescription));
            }
            if (criteria.getBirthdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthdate(), LeadInfo_.birthdate));
            }
            if (criteria.getReportsToId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getReportsToId(), root -> root.join(LeadInfo_.reportsTo, JoinType.LEFT).get(LeadInfo_.id))
                );
            }
            if (criteria.getAssignedUserId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getAssignedUserId(), root -> root.join(LeadInfo_.assignedUser, JoinType.LEFT).get(User_.id))
                );
            }
        }
        return specification;
    }
}
