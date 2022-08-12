package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.goodsattribute.GoodsAttribute;
import com.example.common.entity.goodsattribute.GoodsAttributeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description:  Mapper接口 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
public interface GoodsAttributeMapper extends BaseMapper<GoodsAttribute> {
    IPage<GoodsAttributeVO> selectPage(Page<GoodsAttributeVO> page, @Param("vo") GoodsAttributeVO vo);
}
