package com.lexiangmiao.crm.domain;

import static com.lexiangmiao.crm.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.lexiangmiao.crm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = getEmployeeSample1();
        Employee employee2 = new Employee();
        assertThat(employee1).isNotEqualTo(employee2);

        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);

        employee2 = getEmployeeSample2();
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    void reportsToTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        employee.setReportsTo(employeeBack);
        assertThat(employee.getReportsTo()).isEqualTo(employeeBack);

        employee.reportsTo(null);
        assertThat(employee.getReportsTo()).isNull();
    }
}
