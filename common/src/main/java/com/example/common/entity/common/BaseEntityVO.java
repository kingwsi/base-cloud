package com.example.common.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * description: BaseEntityVO <br>
 * date: 2020/8/7 9:50 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@ToString
public class BaseEntityVO {
    private Integer id;
    private String creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;
    private String lastUpdater;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastUpdateDate;
    private Boolean deleted;
}
