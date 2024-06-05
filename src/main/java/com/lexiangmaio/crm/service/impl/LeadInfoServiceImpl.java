package com.lexiangmaio.crm.service.impl;

import com.lexiangmaio.crm.domain.LeadInfo;
import com.lexiangmaio.crm.repository.LeadInfoRepository;
import com.lexiangmaio.crm.service.LeadInfoService;
import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import com.lexiangmaio.crm.service.mapper.LeadInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.lexiangmaio.crm.domain.LeadInfo}.
 */
@Service
@Transactional
public class LeadInfoServiceImpl implements LeadInfoService {

    private final Logger log = LoggerFactory.getLogger(LeadInfoServiceImpl.class);

    private final LeadInfoRepository leadInfoRepository;

    private final LeadInfoMapper leadInfoMapper;

    public LeadInfoServiceImpl(LeadInfoRepository leadInfoRepository, LeadInfoMapper leadInfoMapper) {
        this.leadInfoRepository = leadInfoRepository;
        this.leadInfoMapper = leadInfoMapper;
    }

    @Override
    public LeadInfoDto save(LeadInfoDto leadInfoDto) {
        log.debug("Request to save LeadInfo : {}", leadInfoDto);
        LeadInfo leadInfo = leadInfoMapper.toEntity(leadInfoDto);
        leadInfo = leadInfoRepository.save(leadInfo);
        return leadInfoMapper.toDto(leadInfo);
    }

    @Override
    public LeadInfoDto update(LeadInfoDto leadInfoDto) {
        log.debug("Request to update LeadInfo : {}", leadInfoDto);
        LeadInfo leadInfo = leadInfoMapper.toEntity(leadInfoDto);
        leadInfo = leadInfoRepository.save(leadInfo);
        return leadInfoMapper.toDto(leadInfo);
    }

    @Override
    public Optional<LeadInfoDto> partialUpdate(LeadInfoDto leadInfoDto) {
        log.debug("Request to partially update LeadInfo : {}", leadInfoDto);

        return leadInfoRepository
            .findById(leadInfoDto.getId())
            .map(existingLeadInfo -> {
                leadInfoMapper.partialUpdate(existingLeadInfo, leadInfoDto);

                return existingLeadInfo;
            })
            .map(leadInfoRepository::save)
            .map(leadInfoMapper::toDto);
    }

    public Page<LeadInfoDto> findAllWithEagerRelationships(Pageable pageable) {
        return leadInfoRepository.findAllWithEagerRelationships(pageable).map(leadInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LeadInfoDto> findOne(Long id) {
        log.debug("Request to get LeadInfo : {}", id);
        return leadInfoRepository.findOneWithEagerRelationships(id).map(leadInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LeadInfo : {}", id);
        leadInfoRepository.deleteById(id);
    }
}
