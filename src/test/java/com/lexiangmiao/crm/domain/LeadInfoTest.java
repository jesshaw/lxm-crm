package com.lexiangmiao.crm.domain;

import static com.lexiangmiao.crm.domain.LeadInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.lexiangmiao.crm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeadInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeadInfo.class);
        LeadInfo leadInfo1 = getLeadInfoSample1();
        LeadInfo leadInfo2 = new LeadInfo();
        assertThat(leadInfo1).isNotEqualTo(leadInfo2);

        leadInfo2.setId(leadInfo1.getId());
        assertThat(leadInfo1).isEqualTo(leadInfo2);

        leadInfo2 = getLeadInfoSample2();
        assertThat(leadInfo1).isNotEqualTo(leadInfo2);
    }

    @Test
    void reportsToTest() throws Exception {
        LeadInfo leadInfo = getLeadInfoRandomSampleGenerator();
        LeadInfo leadInfoBack = getLeadInfoRandomSampleGenerator();

        leadInfo.setReportsTo(leadInfoBack);
        assertThat(leadInfo.getReportsTo()).isEqualTo(leadInfoBack);

        leadInfo.reportsTo(null);
        assertThat(leadInfo.getReportsTo()).isNull();
    }
}
