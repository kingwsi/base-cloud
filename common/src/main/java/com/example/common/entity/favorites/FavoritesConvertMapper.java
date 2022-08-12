package com.example.common.entity.favorites;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description:  转换工具 <br>
* date: 2021-07-27 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface FavoritesConvertMapper {
    Favorites toFavorites(FavoritesVO vo);

    List<Favorites> toFavoritesList(List<FavoritesVO> vo);

    FavoritesVO toFavoritesVO(Favorites favorites);

    List<FavoritesVO> toFavoritesVOList(List<Favorites> vo);
}