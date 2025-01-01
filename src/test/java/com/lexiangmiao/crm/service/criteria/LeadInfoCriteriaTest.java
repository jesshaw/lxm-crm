package com.lexiangmiao.crm.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class LeadInfoCriteriaTest {

    @Test
    void newLeadInfoCriteriaHasAllFiltersNullTest() {
        var leadInfoCriteria = new LeadInfoCriteria();
        assertThat(leadInfoCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void leadInfoCriteriaFluentMethodsCreatesFiltersTest() {
        var leadInfoCriteria = new LeadInfoCriteria();

        setAllFilters(leadInfoCriteria);

        assertThat(leadInfoCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void leadInfoCriteriaCopyCreatesNullFilterTest() {
        var leadInfoCriteria = new LeadInfoCriteria();
        var copy = leadInfoCriteria.copy();

        assertThat(leadInfoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(leadInfoCriteria)
        );
    }

    @Test
    void leadInfoCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var leadInfoCriteria = new LeadInfoCriteria();
        setAllFilters(leadInfoCriteria);

        var copy = leadInfoCriteria.copy();

        assertThat(leadInfoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(leadInfoCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var leadInfoCriteria = new LeadInfoCriteria();

        assertThat(leadInfoCriteria).hasToString("LeadInfoCriteria{}");
    }

    private static void setAllFilters(LeadInfoCriteria leadInfoCriteria) {
        leadInfoCriteria.id();
        leadInfoCriteria.salutation();
        leadInfoCriteria.firstName();
        leadInfoCriteria.lastName();
        leadInfoCriteria.title();
        leadInfoCriteria.department();
        leadInfoCriteria.doNotCall();
        leadInfoCriteria.phoneHome();
        leadInfoCriteria.phoneMobile();
        leadInfoCriteria.phoneWork();
        leadInfoCriteria.phoneOther();
        leadInfoCriteria.phoneFax();
        leadInfoCriteria.dateReviewed();
        leadInfoCriteria.lawfulBasis();
        leadInfoCriteria.lawfulBasisSource();
        leadInfoCriteria.primaryAddressStreet();
        leadInfoCriteria.primaryAddressCity();
        leadInfoCriteria.primaryAddressState();
        leadInfoCriteria.primaryAddressPostalcode();
        leadInfoCriteria.primaryAddressCountry();
        leadInfoCriteria.altAddressStreet();
        leadInfoCriteria.altAddressCity();
        leadInfoCriteria.altAddressState();
        leadInfoCriteria.altAddressPostalcode();
        leadInfoCriteria.altAddressCountry();
        leadInfoCriteria.assistant();
        leadInfoCriteria.assistantPhone();
        leadInfoCriteria.converted();
        leadInfoCriteria.referedBy();
        leadInfoCriteria.leadSource();
        leadInfoCriteria.leadSourceDescription();
        leadInfoCriteria.status();
        leadInfoCriteria.statusDescription();
        leadInfoCriteria.birthdate();
        leadInfoCriteria.reportsToId();
        leadInfoCriteria.assignedUserId();
        leadInfoCriteria.distinct();
    }

    private static Condition<LeadInfoCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getSalutation()) &&
                condition.apply(criteria.getFirstName()) &&
                condition.apply(criteria.getLastName()) &&
                condition.apply(criteria.getTitle()) &&
                condition.apply(criteria.getDepartment()) &&
                condition.apply(criteria.getDoNotCall()) &&
                condition.apply(criteria.getPhoneHome()) &&
                condition.apply(criteria.getPhoneMobile()) &&
                condition.apply(criteria.getPhoneWork()) &&
                condition.apply(criteria.getPhoneOther()) &&
                condition.apply(criteria.getPhoneFax()) &&
                condition.apply(criteria.getDateReviewed()) &&
                condition.apply(criteria.getLawfulBasis()) &&
                condition.apply(criteria.getLawfulBasisSource()) &&
                condition.apply(criteria.getPrimaryAddressStreet()) &&
                condition.apply(criteria.getPrimaryAddressCity()) &&
                condition.apply(criteria.getPrimaryAddressState()) &&
                condition.apply(criteria.getPrimaryAddressPostalcode()) &&
                condition.apply(criteria.getPrimaryAddressCountry()) &&
                condition.apply(criteria.getAltAddressStreet()) &&
                condition.apply(criteria.getAltAddressCity()) &&
                condition.apply(criteria.getAltAddressState()) &&
                condition.apply(criteria.getAltAddressPostalcode()) &&
                condition.apply(criteria.getAltAddressCountry()) &&
                condition.apply(criteria.getAssistant()) &&
                condition.apply(criteria.getAssistantPhone()) &&
                condition.apply(criteria.getConverted()) &&
                condition.apply(criteria.getReferedBy()) &&
                condition.apply(criteria.getLeadSource()) &&
                condition.apply(criteria.getLeadSourceDescription()) &&
                condition.apply(criteria.getStatus()) &&
                condition.apply(criteria.getStatusDescription()) &&
                condition.apply(criteria.getBirthdate()) &&
                condition.apply(criteria.getReportsToId()) &&
                condition.apply(criteria.getAssignedUserId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<LeadInfoCriteria> copyFiltersAre(LeadInfoCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getSalutation(), copy.getSalutation()) &&
                condition.apply(criteria.getFirstName(), copy.getFirstName()) &&
                condition.apply(criteria.getLastName(), copy.getLastName()) &&
                condition.apply(criteria.getTitle(), copy.getTitle()) &&
                condition.apply(criteria.getDepartment(), copy.getDepartment()) &&
                condition.apply(criteria.getDoNotCall(), copy.getDoNotCall()) &&
                condition.apply(criteria.getPhoneHome(), copy.getPhoneHome()) &&
                condition.apply(criteria.getPhoneMobile(), copy.getPhoneMobile()) &&
                condition.apply(criteria.getPhoneWork(), copy.getPhoneWork()) &&
                condition.apply(criteria.getPhoneOther(), copy.getPhoneOther()) &&
                condition.apply(criteria.getPhoneFax(), copy.getPhoneFax()) &&
                condition.apply(criteria.getDateReviewed(), copy.getDateReviewed()) &&
                condition.apply(criteria.getLawfulBasis(), copy.getLawfulBasis()) &&
                condition.apply(criteria.getLawfulBasisSource(), copy.getLawfulBasisSource()) &&
                condition.apply(criteria.getPrimaryAddressStreet(), copy.getPrimaryAddressStreet()) &&
                condition.apply(criteria.getPrimaryAddressCity(), copy.getPrimaryAddressCity()) &&
                condition.apply(criteria.getPrimaryAddressState(), copy.getPrimaryAddressState()) &&
                condition.apply(criteria.getPrimaryAddressPostalcode(), copy.getPrimaryAddressPostalcode()) &&
                condition.apply(criteria.getPrimaryAddressCountry(), copy.getPrimaryAddressCountry()) &&
                condition.apply(criteria.getAltAddressStreet(), copy.getAltAddressStreet()) &&
                condition.apply(criteria.getAltAddressCity(), copy.getAltAddressCity()) &&
                condition.apply(criteria.getAltAddressState(), copy.getAltAddressState()) &&
                condition.apply(criteria.getAltAddressPostalcode(), copy.getAltAddressPostalcode()) &&
                condition.apply(criteria.getAltAddressCountry(), copy.getAltAddressCountry()) &&
                condition.apply(criteria.getAssistant(), copy.getAssistant()) &&
                condition.apply(criteria.getAssistantPhone(), copy.getAssistantPhone()) &&
                condition.apply(criteria.getConverted(), copy.getConverted()) &&
                condition.apply(criteria.getReferedBy(), copy.getReferedBy()) &&
                condition.apply(criteria.getLeadSource(), copy.getLeadSource()) &&
                condition.apply(criteria.getLeadSourceDescription(), copy.getLeadSourceDescription()) &&
                condition.apply(criteria.getStatus(), copy.getStatus()) &&
                condition.apply(criteria.getStatusDescription(), copy.getStatusDescription()) &&
                condition.apply(criteria.getBirthdate(), copy.getBirthdate()) &&
                condition.apply(criteria.getReportsToId(), copy.getReportsToId()) &&
                condition.apply(criteria.getAssignedUserId(), copy.getAssignedUserId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
