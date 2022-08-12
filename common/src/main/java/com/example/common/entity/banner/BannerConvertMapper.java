package com.example.common.entity.banner;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: banner 转换工具 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface BannerConvertMapper {
    Banner toBanner(BannerVO vo);

    List<Banner> toBannerList(List<BannerVO> vo);

    BannerVO toBannerVO(Banner banner);

    List<BannerVO> toBannerVOList(List<Banner> vo);
}