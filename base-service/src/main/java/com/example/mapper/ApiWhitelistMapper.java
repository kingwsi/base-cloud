package com.example.mapper;

import com.example.common.entity.apiwhitelist.ApiWhitelist;
import com.example.common.entity.apiwhitelist.ApiWhitelistVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: api白名单 Mapper接口 <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/
@Component
public interface ApiWhitelistMapper extends BaseMapper<ApiWhitelist> {
    IPage<ApiWhitelistVO> selectPage(Page<ApiWhitelistVO> page, @Param("vo") ApiWhitelistVO vo);
}
