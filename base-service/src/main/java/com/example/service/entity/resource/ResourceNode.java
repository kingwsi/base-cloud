package com.example.service.entity.resource;

import com.example.service.common.enumerate.ResourceTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class ResourceNode {
    private String id;
    private String name;
    private ResourceTypeEnum type;
    private String uri;
    private String methods;
    private String parentId;
    private Integer sort;
    List<ResourceNode> children;
}
