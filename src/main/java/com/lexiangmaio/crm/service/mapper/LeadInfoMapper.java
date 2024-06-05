package com.lexiangmaio.crm.service.mapper;

import com.lexiangmaio.crm.domain.LeadInfo;
import com.lexiangmaio.crm.domain.User;
import com.lexiangmaio.crm.service.dto.LeadInfoDto;
import com.lexiangmaio.crm.service.dto.UserDto;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeadInfo} and its DTO {@link LeadInfoDto}.
 */
@Mapper(componentModel = "spring")
public interface LeadInfoMapper extends EntityMapper<LeadInfoDto, LeadInfo> {
    @Mapping(target = "reportsTo", source = "reportsTo", qualifiedByName = "leadInfoLastName")
    @Mapping(target = "assignedUser", source = "assignedUser", qualifiedByName = "userLogin")
    LeadInfoDto toDto(LeadInfo s);

    @Named("leadInfoLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    LeadInfoDto toDtoLeadInfoLastName(LeadInfo leadInfo);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDto toDtoUserLogin(User user);
}
