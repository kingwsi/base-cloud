package com.example.common.entity.resource;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ResourceConvertMapper {
    Resource routeToResource(RouteVO vo);

    Resource resourceVOToResource(ResourceVO vo);

    List<ResourceVO> toResourceVOs(List<Resource> resources);
}
