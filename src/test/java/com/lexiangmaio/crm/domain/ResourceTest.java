package com.lexiangmaio.crm.domain;

import static com.lexiangmaio.crm.domain.ResourceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.lexiangmaio.crm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resource.class);
        Resource resource1 = getResourceSample1();
        Resource resource2 = new Resource();
        assertThat(resource1).isNotEqualTo(resource2);

        resource2.setId(resource1.getId());
        assertThat(resource1).isEqualTo(resource2);

        resource2 = getResourceSample2();
        assertThat(resource1).isNotEqualTo(resource2);
    }
}
