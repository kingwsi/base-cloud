package com.example.service.entity.resource;

import com.example.service.common.enumerate.ResourceTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class ResourceVO {
    private String id;
    private String name;
    private ResourceTypeEnum type;
    private String description;
    private String uri;
    private String methods;
    private String parentId;
    private String sort;
    private String component;
    private String icon;
    private String remark;
    private List<String> resourceIds;

}
