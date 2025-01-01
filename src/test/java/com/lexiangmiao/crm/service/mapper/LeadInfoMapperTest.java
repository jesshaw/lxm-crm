package com.lexiangmiao.crm.service.mapper;

import com.lexiangmiao.crm.domain.LeadInfoAsserts;
import com.lexiangmiao.crm.domain.LeadInfoTestSamples;
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
        var expected = LeadInfoTestSamples.getLeadInfoSample1();
        var actual = leadInfoMapper.toEntity(leadInfoMapper.toDto(expected));
        LeadInfoAsserts.assertLeadInfoAllPropertiesEquals(expected, actual);
    }
}
