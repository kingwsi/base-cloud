package com.example.service.entity.organization;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationConvertMapper {
    Organization toOrganization(OrganizationVO organizationVO);
}
