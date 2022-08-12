package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.brand.Brand;
import com.example.common.entity.brand.BrandVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 品牌 Mapper接口 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
public interface BrandMapper extends BaseMapper<Brand> {
    IPage<BrandVO> selectPage(Page<BrandVO> page, @Param("vo") BrandVO vo);
}
