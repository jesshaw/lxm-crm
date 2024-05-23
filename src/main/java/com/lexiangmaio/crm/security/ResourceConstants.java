package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.DictionaryEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ResourceConstants {

    public static final String EMPLOYEE = "EMPLOYEE";

    private ResourceConstants() {}

    protected enum Resource {
        EMPLOYEE(ResourceConstants.EMPLOYEE);

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
