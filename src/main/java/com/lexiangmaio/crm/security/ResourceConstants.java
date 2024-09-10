package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.DictionaryEntry;
import java.util.Arrays;
import java.util.List;

public final class ResourceConstants {

    public static final String AUTHORITY = "AUTHORITY";
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String RESOURCE = "RESOURCE";
    public static final String LEAD_INFO = "LEAD_INFO";

    // jhipster-needle-add-entity-to-resource-constants - Jhipster will add entities to the resource constants here

    private ResourceConstants() {}

    protected enum Resource {
        AUTHORITY(ResourceConstants.AUTHORITY),
        EMPLOYEE(ResourceConstants.EMPLOYEE),
        RESOURCE(ResourceConstants.RESOURCE),
        LEAD_INFO(ResourceConstants.LEAD_INFO);

        // jhipster-needle-add-entity-to-resource-enum - Jhipster will add entities to the resource enum here

        private final String name;

        Resource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static List<DictionaryEntry> getAllResources() {
        return Arrays.stream(Resource.values()).map(o -> new DictionaryEntry(o.getName(), o.getName())).toList();
    }
}
