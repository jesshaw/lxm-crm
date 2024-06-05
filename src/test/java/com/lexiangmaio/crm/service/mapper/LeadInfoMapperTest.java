package com.lexiangmaio.crm.service.mapper;

import static com.lexiangmaio.crm.domain.LeadInfoAsserts.*;
import static com.lexiangmaio.crm.domain.LeadInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeadInfoMapperTest {

    private LeadInfoMapper leadInfoMapper;

    @BeforeEach
    void setUp() {
        leadInfoMapper = new LeadInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLeadInfoSample1();
        var actual = leadInfoMapper.toEntity(leadInfoMapper.toDto(expected));
        assertLeadInfoAllPropertiesEquals(expected, actual);
    }
}
