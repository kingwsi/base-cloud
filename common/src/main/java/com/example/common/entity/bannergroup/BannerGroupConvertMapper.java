package com.example.common.entity.bannergroup;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: banner分组 转换工具 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface BannerGroupConvertMapper {
    BannerGroup toBannerGroup(BannerGroupVO vo);

    List<BannerGroup> toBannerGroupList(List<BannerGroupVO> vo);

    BannerGroupVO toBannerGroupVO(BannerGroup bannergroup);

    List<BannerGroupVO> toBannerGroupVOList(List<BannerGroup> vo);
}