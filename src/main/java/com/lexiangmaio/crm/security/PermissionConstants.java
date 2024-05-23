package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.DictionaryEntry;
import java.util.Arrays;
import java.util.List;

public final class PermissionConstants {

    public static final String ACCESS = "ACCESS";
    public static final String DELETE = "DELETE";
    public static final String EDIT = "EDIT";
    public static final String EXPORT = "EXPORT";
    public static final String IMPORT = "IMPORT";
    public static final String LIST = "LIST";
    public static final String MASSUPDATE = "MASSUPDATE";
    public static final String VIEW = "VIEW";

    private PermissionConstants() {}

    protected enum Permission {
        ACCESS(PermissionConstants.ACCESS),
        DELETE(PermissionConstants.DELETE),
        EDIT(PermissionConstants.EDIT),
        EXPORT(PermissionConstants.EXPORT),
        IMPORT(PermissionConstants.IMPORT),
        LIST(PermissionConstants.LIST),
        MASSUPDATE(PermissionConstants.MASSUPDATE),
        VIEW(PermissionConstants.VIEW);

        private final String name;

        Permission(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static List<DictionaryEntry> getAllPermissions() {
        return Arrays.stream(Permission.values()).map(o -> new DictionaryEntry(o.getName(), o.getName())).toList();
    }
}
