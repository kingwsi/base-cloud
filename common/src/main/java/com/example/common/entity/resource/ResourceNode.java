package com.example.common.entity.resource;

import com.example.common.enumerate.ResourceTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class ResourceNode {
    private Integer id;
    private String name;
    private ResourceTypeEnum type;
    private String uri;
    private String methods;
    private Integer parentId;
    private Integer sort;
    List<ResourceNode> children;
}
