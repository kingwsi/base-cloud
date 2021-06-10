package com.example.common.entity.apiwhitelist;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: api白名单 转换工具 <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface ApiWhitelistConvertMapper {
    ApiWhitelist toApiWhitelist(ApiWhitelistVO vo);

    List<ApiWhitelist> toApiWhitelistList(List<ApiWhitelistVO> vo);

    ApiWhitelistVO toApiWhitelistVO(ApiWhitelist apiwhitelist);

    List<ApiWhitelistVO> toApiWhitelistVOList(List<ApiWhitelist> vo);
}