package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.favorites.Favorites;
import com.example.common.entity.favorites.FavoritesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description:  Mapper接口 <br>
* date: 2021-07-27 <br>
* author: ws <br>
*/
@Component
public interface FavoritesMapper extends BaseMapper<Favorites> {
    IPage<FavoritesVO> selectPage(Page<FavoritesVO> page, @Param("vo") FavoritesVO vo);

    void realDeleteById(@Param("id") Integer id);
}
