package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.bannergroup.BannerGroup;
import com.example.common.entity.bannergroup.BannerGroupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: banner分组 Mapper接口 <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Component
public interface BannerGroupMapper extends BaseMapper<BannerGroup> {
    IPage<BannerGroupVO> selectPage(Page<BannerGroupVO> page, @Param("vo") BannerGroupVO vo);
}
