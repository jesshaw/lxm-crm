package com.lexiangmiao.crm.web.rest;

import com.lexiangmiao.crm.domain.DictionaryEntry;
import com.lexiangmiao.crm.domain.Employee;
import com.lexiangmiao.crm.security.PermissionConstants;
import com.lexiangmiao.crm.security.ResourceConstants;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
