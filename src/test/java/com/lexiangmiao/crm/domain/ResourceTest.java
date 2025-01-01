package com.lexiangmiao.crm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.lexiangmiao.crm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resource.class);
        Resource resource1 = ResourceTestSamples.getResourceSample1();
        Resource resource2 = new Resource();
        assertThat(resource1).isNotEqualTo(resource2);

        resource2.setId(resource1.getId());
        assertThat(resource1).isEqualTo(resource2);

        resource2 = ResourceTestSamples.getResourceSample2();
        assertThat(resource1).isNotEqualTo(resource2);
    }
}
