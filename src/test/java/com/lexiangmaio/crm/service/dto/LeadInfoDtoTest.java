package com.lexiangmaio.crm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.lexiangmaio.crm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeadInfoDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeadInfoDto.class);
        LeadInfoDto leadInfoDto1 = new LeadInfoDto();
        leadInfoDto1.setId(1L);
        LeadInfoDto leadInfoDto2 = new LeadInfoDto();
        assertThat(leadInfoDto1).isNotEqualTo(leadInfoDto2);
        leadInfoDto2.setId(leadInfoDto1.getId());
        assertThat(leadInfoDto1).isEqualTo(leadInfoDto2);
        leadInfoDto2.setId(2L);
        assertThat(leadInfoDto1).isNotEqualTo(leadInfoDto2);
        leadInfoDto1.setId(null);
        assertThat(leadInfoDto1).isNotEqualTo(leadInfoDto2);
    }
}
