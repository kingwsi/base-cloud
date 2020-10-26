package com.example.service.entity.resource;

import com.example.service.common.enumerate.ResourceTypeEnum;
import lombok.Data;

@Data
public class ResourceQuery {
    private String name;
    private ResourceTypeEnum type;
}
