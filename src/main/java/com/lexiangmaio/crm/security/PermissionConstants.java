package com.lexiangmaio.crm.security;

import com.lexiangmaio.crm.domain.DictionaryEntry;
import java.util.*;

public final class PermissionConstants {

    // 允许用户访问模块或记录，即进入到模块的页面或查看记录的详细信息
    public static final String ACCESS = "ACCESS";
    // 允许用户删除模块中的记录
    public static final String DELETE = "DELETE";
    // 允许用户编辑模块中的记录，即修改记录的内容
    public static final String EDIT = "EDIT";
    // 允许用户将模块中的记录导出到外部文件，如 CSV 或 Excel 格式
    public static final String EXPORT = "EXPORT";
    // 允许用户从外部文件导入数据到模块中
    public static final String IMPORT = "IMPORT";
    // 允许用户查看模块中的记录列表
    public static final String LIST = "LIST";
    // 允许用户对模块中的多条记录进行批量更新操作
    public static final String MASSUPDATE = "MASSUPDATE";
    // 允许用户查看模块中的单条记录的详细信息
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
