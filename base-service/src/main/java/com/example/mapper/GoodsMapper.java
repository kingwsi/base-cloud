package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.goods.Goods;
import com.example.common.entity.goods.GoodsInfoVO;
import com.example.common.entity.goods.GoodsListVO;
import com.example.common.entity.goods.GoodsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 商品 Mapper接口 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<GoodsVO> selectPage(Page<GoodsVO> page, @Param("vo") GoodsVO vo);

    List<GoodsListVO> selectGoodsList(Page<GoodsVO> page, @Param("vo") GoodsVO vo);

    GoodsInfoVO selectGoodsWithDetail(@Param("id") Integer id);
}
