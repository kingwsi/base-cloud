package com.example.common.entity.resource;

import com.example.common.enumerate.ResourceTypeEnum;
import lombok.Data;

@Data
public class ResourceQuery {
    private String name;
    private ResourceTypeEnum type;
}
