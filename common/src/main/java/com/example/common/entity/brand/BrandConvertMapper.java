package com.example.common.entity.brand;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 品牌 转换工具 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface BrandConvertMapper {
    Brand toBrand(BrandVO vo);

    List<Brand> toBrandList(List<BrandVO> vo);

    BrandVO toBrandVO(Brand brand);

    List<BrandVO> toBrandVOList(List<Brand> vo);
}