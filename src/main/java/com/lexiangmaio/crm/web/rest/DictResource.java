package com.lexiangmaio.crm.web.rest;

import com.lexiangmaio.crm.domain.DictionaryEntry;
import com.lexiangmaio.crm.domain.Employee;
import com.lexiangmaio.crm.repository.EmployeeRepository;
import com.lexiangmaio.crm.repository.UserRepository;
import com.lexiangmaio.crm.security.PermissionConstants;
import com.lexiangmaio.crm.security.ResourceConstants;
import com.lexiangmaio.crm.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link Employee}.
 */
@RestController
@RequestMapping("/api/dicts")
@Transactional
public class DictResource {

    private final Logger log = LoggerFactory.getLogger(DictResource.class);

    /**
     * {@code GET  /dicts} : get the dict.
     *
     * @param types the one or multi type data pu to dict.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @GetMapping("")
    @Transactional(readOnly = true)
    public Map<String, List<DictionaryEntry>> getAllDicts(
        @RequestParam(name = "types", required = false, defaultValue = "") Set<String> types
    ) {
        log.debug("REST request to get all Employees");
        Map<String, List<DictionaryEntry>> results = new HashMap<>();
        if (types.contains("resource")) {
            results.put("resource", ResourceConstants.getAllResources());
        }
        if (types.contains("permission")) {
            results.put("permission", PermissionConstants.getAllPermissions());
        }
        return results;
    }
}
