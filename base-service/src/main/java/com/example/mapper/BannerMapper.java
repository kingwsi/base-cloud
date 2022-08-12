package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.banner.Banner;
import com.example.common.entity.banner.BannerVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: banner Mapper接口 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Component
public interface BannerMapper extends BaseMapper<Banner> {
    IPage<BannerVO> selectPage(Page<BannerVO> page, @Param("vo") BannerVO vo);
}
