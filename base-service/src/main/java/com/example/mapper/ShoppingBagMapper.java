package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.shoppingbag.ShoppingBag;
import com.example.common.entity.shoppingbag.ShoppingBagVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description:  Mapper接口 <br>
* date: 2021-08-03 <br>
* author: ws <br>
*/
@Component
public interface ShoppingBagMapper extends BaseMapper<ShoppingBag> {
    IPage<ShoppingBagVO> selectPage(Page<ShoppingBagVO> page, @Param("vo") ShoppingBagVO vo);

    int realDeleteById(@Param("id") Integer id);
}
