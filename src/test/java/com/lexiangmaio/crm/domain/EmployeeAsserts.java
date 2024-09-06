package com.lexiangmaio.crm.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmployeeAllPropertiesEquals(Employee expected, Employee actual) {
        assertEmployeeAutoGeneratedPropertiesEquals(expected, actual);
        assertEmployeeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmployeeAllUpdatablePropertiesEquals(Employee expected, Employee actual) {
        assertEmployeeUpdatableFieldsEquals(expected, actual);
        assertEmployeeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the derived primary key is set correctly.
     *
     * @param entityToPersist the entity used to persist
     * @param persisted the persisted entity
     */
    public static void assertEmployeeMapsIdRelationshipPersistedValue(Employee entityToPersist, Employee persisted) {
        // Validate the id for MapsId, the ids must be same
        assertThat(entityToPersist.getUser().getId()).isEqualTo(persisted.getId());
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmployeeAutoGeneratedPropertiesEquals(Employee expected, Employee actual) {
        assertThat(expected)
            .as("Verify Employee auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmployeeUpdatableFieldsEquals(Employee expected, Employee actual) {
        assertThat(expected)
            .as("Verify Employee relevant properties")
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getNickName()).as("check nickName").isEqualTo(actual.getNickName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertEmployeeUpdatableRelationshipsEquals(Employee expected, Employee actual) {
        assertThat(expected)
            .as("Verify Employee relationships")
            .satisfies(e -> assertThat(e.getReportsTo()).as("check reportsTo").isEqualTo(actual.getReportsTo()));
    }
}
