package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.goodssku.GoodsSku;
import com.example.common.entity.goodssku.GoodsSkuVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: SKU Mapper接口 <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Component
public interface GoodsSkuMapper extends BaseMapper<GoodsSku> {
    IPage<GoodsSkuVO> selectPage(Page<GoodsSkuVO> page, @Param("vo") GoodsSkuVO vo);

    List<GoodsSkuVO> selectSKUByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 删除不在集合中的sku
     * @param goodsId
     * @param ids
     * @return
     */
    int deleteByGoodsId(@Param("goodsId") Integer goodsId, @Param("ids") List<Integer> ids);
}
