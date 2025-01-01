package com.lexiangmiao.crm.service.dto;

import com.lexiangmiao.crm.domain.Authority;
import com.lexiangmiao.crm.domain.User;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class PermissionAdminUserDto extends AdminUserDto {

    public PermissionAdminUserDto() {}

    public PermissionAdminUserDto(User user) {
        this.setId(user.getId());
        this.setLogin(user.getLogin());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setActivated(user.isActivated());
        this.setImageUrl(user.getImageUrl());
        this.setLangKey(user.getLangKey());
        this.setCreatedBy(user.getCreatedBy());
        this.setCreatedDate(user.getCreatedDate());
        this.setLastModifiedBy(user.getLastModifiedBy());
        this.setLastModifiedDate(user.getLastModifiedDate());
        this.setAuthorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }

    private Map<String, String> resources;

    public Map<String, String> getResources() {
        return resources;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }
}
