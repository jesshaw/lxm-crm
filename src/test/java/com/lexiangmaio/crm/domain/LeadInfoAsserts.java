package com.lexiangmaio.crm.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class LeadInfoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLeadInfoAllPropertiesEquals(LeadInfo expected, LeadInfo actual) {
        assertLeadInfoAutoGeneratedPropertiesEquals(expected, actual);
        assertLeadInfoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLeadInfoAllUpdatablePropertiesEquals(LeadInfo expected, LeadInfo actual) {
        assertLeadInfoUpdatableFieldsEquals(expected, actual);
        assertLeadInfoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLeadInfoAutoGeneratedPropertiesEquals(LeadInfo expected, LeadInfo actual) {
        assertThat(expected)
            .as("Verify LeadInfo auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLeadInfoUpdatableFieldsEquals(LeadInfo expected, LeadInfo actual) {
        assertThat(expected)
            .as("Verify LeadInfo relevant properties")
            .satisfies(e -> assertThat(e.getSalutation()).as("check salutation").isEqualTo(actual.getSalutation()))
            .satisfies(e -> assertThat(e.getFirstName()).as("check firstName").isEqualTo(actual.getFirstName()))
            .satisfies(e -> assertThat(e.getLastName()).as("check lastName").isEqualTo(actual.getLastName()))
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getDepartment()).as("check department").isEqualTo(actual.getDepartment()))
            .satisfies(e -> assertThat(e.getDoNotCall()).as("check doNotCall").isEqualTo(actual.getDoNotCall()))
            .satisfies(e -> assertThat(e.getPhoneHome()).as("check phoneHome").isEqualTo(actual.getPhoneHome()))
            .satisfies(e -> assertThat(e.getPhoneMobile()).as("check phoneMobile").isEqualTo(actual.getPhoneMobile()))
            .satisfies(e -> assertThat(e.getPhoneWork()).as("check phoneWork").isEqualTo(actual.getPhoneWork()))
            .satisfies(e -> assertThat(e.getPhoneOther()).as("check phoneOther").isEqualTo(actual.getPhoneOther()))
            .satisfies(e -> assertThat(e.getPhoneFax()).as("check phoneFax").isEqualTo(actual.getPhoneFax()))
            .satisfies(e -> assertThat(e.getDateReviewed()).as("check dateReviewed").isEqualTo(actual.getDateReviewed()))
            .satisfies(e -> assertThat(e.getLawfulBasis()).as("check lawfulBasis").isEqualTo(actual.getLawfulBasis()))
            .satisfies(e -> assertThat(e.getLawfulBasisSource()).as("check lawfulBasisSource").isEqualTo(actual.getLawfulBasisSource()))
            .satisfies(
                e -> assertThat(e.getPrimaryAddressStreet()).as("check primaryAddressStreet").isEqualTo(actual.getPrimaryAddressStreet())
            )
            .satisfies(e -> assertThat(e.getPrimaryAddressCity()).as("check primaryAddressCity").isEqualTo(actual.getPrimaryAddressCity()))
            .satisfies(
                e -> assertThat(e.getPrimaryAddressState()).as("check primaryAddressState").isEqualTo(actual.getPrimaryAddressState())
            )
            .satisfies(
                e ->
                    assertThat(e.getPrimaryAddressPostalcode())
                        .as("check primaryAddressPostalcode")
                        .isEqualTo(actual.getPrimaryAddressPostalcode())
            )
            .satisfies(
                e -> assertThat(e.getPrimaryAddressCountry()).as("check primaryAddressCountry").isEqualTo(actual.getPrimaryAddressCountry())
            )
            .satisfies(e -> assertThat(e.getAltAddressStreet()).as("check altAddressStreet").isEqualTo(actual.getAltAddressStreet()))
            .satisfies(e -> assertThat(e.getAltAddressCity()).as("check altAddressCity").isEqualTo(actual.getAltAddressCity()))
            .satisfies(e -> assertThat(e.getAltAddressState()).as("check altAddressState").isEqualTo(actual.getAltAddressState()))
            .satisfies(
                e -> assertThat(e.getAltAddressPostalcode()).as("check altAddressPostalcode").isEqualTo(actual.getAltAddressPostalcode())
            )
            .satisfies(e -> assertThat(e.getAltAddressCountry()).as("check altAddressCountry").isEqualTo(actual.getAltAddressCountry()))
            .satisfies(e -> assertThat(e.getAssistant()).as("check assistant").isEqualTo(actual.getAssistant()))
            .satisfies(e -> assertThat(e.getAssistantPhone()).as("check assistantPhone").isEqualTo(actual.getAssistantPhone()))
            .satisfies(e -> assertThat(e.getConverted()).as("check converted").isEqualTo(actual.getConverted()))
            .satisfies(e -> assertThat(e.getReferedBy()).as("check referedBy").isEqualTo(actual.getReferedBy()))
            .satisfies(e -> assertThat(e.getLeadSource()).as("check leadSource").isEqualTo(actual.getLeadSource()))
            .satisfies(
                e -> assertThat(e.getLeadSourceDescription()).as("check leadSourceDescription").isEqualTo(actual.getLeadSourceDescription())
            )
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getStatusDescription()).as("check statusDescription").isEqualTo(actual.getStatusDescription()))
            .satisfies(e -> assertThat(e.getBirthdate()).as("check birthdate").isEqualTo(actual.getBirthdate()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLeadInfoUpdatableRelationshipsEquals(LeadInfo expected, LeadInfo actual) {
        assertThat(expected)
            .as("Verify LeadInfo relationships")
            .satisfies(e -> assertThat(e.getReportsTo()).as("check reportsTo").isEqualTo(actual.getReportsTo()));
    }
}
