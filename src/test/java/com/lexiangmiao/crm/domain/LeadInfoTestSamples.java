package com.lexiangmiao.crm.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LeadInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LeadInfo getLeadInfoSample1() {
        return new LeadInfo()
            .id(1L)
            .salutation("salutation1")
            .firstName("firstName1")
            .lastName("lastName1")
            .title("title1")
            .department("department1")
            .phoneHome("phoneHome1")
            .phoneMobile("phoneMobile1")
            .phoneWork("phoneWork1")
            .phoneOther("phoneOther1")
            .phoneFax("phoneFax1")
            .lawfulBasis("lawfulBasis1")
            .lawfulBasisSource("lawfulBasisSource1")
            .primaryAddressStreet("primaryAddressStreet1")
            .primaryAddressCity("primaryAddressCity1")
            .primaryAddressState("primaryAddressState1")
            .primaryAddressPostalcode("primaryAddressPostalcode1")
            .primaryAddressCountry("primaryAddressCountry1")
            .altAddressStreet("altAddressStreet1")
            .altAddressCity("altAddressCity1")
            .altAddressState("altAddressState1")
            .altAddressPostalcode("altAddressPostalcode1")
            .altAddressCountry("altAddressCountry1")
            .assistant("assistant1")
            .assistantPhone("assistantPhone1")
            .referedBy("referedBy1")
            .leadSource("leadSource1")
            .leadSourceDescription("leadSourceDescription1")
            .status("status1")
            .statusDescription("statusDescription1");
    }

    public static LeadInfo getLeadInfoSample2() {
        return new LeadInfo()
            .id(2L)
            .salutation("salutation2")
            .firstName("firstName2")
            .lastName("lastName2")
            .title("title2")
            .department("department2")
            .phoneHome("phoneHome2")
            .phoneMobile("phoneMobile2")
            .phoneWork("phoneWork2")
            .phoneOther("phoneOther2")
            .phoneFax("phoneFax2")
            .lawfulBasis("lawfulBasis2")
            .lawfulBasisSource("lawfulBasisSource2")
            .primaryAddressStreet("primaryAddressStreet2")
            .primaryAddressCity("primaryAddressCity2")
            .primaryAddressState("primaryAddressState2")
            .primaryAddressPostalcode("primaryAddressPostalcode2")
            .primaryAddressCountry("primaryAddressCountry2")
            .altAddressStreet("altAddressStreet2")
            .altAddressCity("altAddressCity2")
            .altAddressState("altAddressState2")
            .altAddressPostalcode("altAddressPostalcode2")
            .altAddressCountry("altAddressCountry2")
            .assistant("assistant2")
            .assistantPhone("assistantPhone2")
            .referedBy("referedBy2")
            .leadSource("leadSource2")
            .leadSourceDescription("leadSourceDescription2")
            .status("status2")
            .statusDescription("statusDescription2");
    }

    public static LeadInfo getLeadInfoRandomSampleGenerator() {
        return new LeadInfo()
            .id(longCount.incrementAndGet())
            .salutation(UUID.randomUUID().toString())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .phoneHome(UUID.randomUUID().toString())
            .phoneMobile(UUID.randomUUID().toString())
            .phoneWork(UUID.randomUUID().toString())
            .phoneOther(UUID.randomUUID().toString())
            .phoneFax(UUID.randomUUID().toString())
            .lawfulBasis(UUID.randomUUID().toString())
            .lawfulBasisSource(UUID.randomUUID().toString())
            .primaryAddressStreet(UUID.randomUUID().toString())
            .primaryAddressCity(UUID.randomUUID().toString())
            .primaryAddressState(UUID.randomUUID().toString())
            .primaryAddressPostalcode(UUID.randomUUID().toString())
            .primaryAddressCountry(UUID.randomUUID().toString())
            .altAddressStreet(UUID.randomUUID().toString())
            .altAddressCity(UUID.randomUUID().toString())
            .altAddressState(UUID.randomUUID().toString())
            .altAddressPostalcode(UUID.randomUUID().toString())
            .altAddressCountry(UUID.randomUUID().toString())
            .assistant(UUID.randomUUID().toString())
            .assistantPhone(UUID.randomUUID().toString())
            .referedBy(UUID.randomUUID().toString())
            .leadSource(UUID.randomUUID().toString())
            .leadSourceDescription(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .statusDescription(UUID.randomUUID().toString());
    }
}
