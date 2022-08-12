package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.favorites.Favorites;
import com.example.common.entity.favorites.FavoritesConvertMapper;
import com.example.common.entity.favorites.FavoritesVO;
import com.example.common.utils.TokenUtils;
import com.example.mapper.FavoritesMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * description:  Service <br>
 * date: 2021-07-27 <br>
 * author: ws <br>
 */
@Service
public class FavoritesService {

    private final FavoritesMapper favoritesMapper;

    private final FavoritesConvertMapper favoritesConvertMapper;

    public FavoritesService(FavoritesMapper favoritesMapper, FavoritesConvertMapper favoritesConvertMapper) {
        this.favoritesMapper = favoritesMapper;
        this.favoritesConvertMapper = favoritesConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param favoritesVO
     * @return
     */
    public boolean create(FavoritesVO favoritesVO) {
        return favoritesMapper.insert(favoritesConvertMapper.toFavorites(favoritesVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return favoritesMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<FavoritesVO> listOfPage(Page<FavoritesVO> page, FavoritesVO vo) {
        vo.setMemberId(TokenUtils.getCurrentPrincipalId());
        return favoritesMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param favoritesVO
     * @return
     */
    public boolean updateById(FavoritesVO favoritesVO) {
        return favoritesMapper.updateById(favoritesConvertMapper.toFavorites(favoritesVO)) > 0;
    }

    public FavoritesVO getById(Integer id) {
        Favorites favorites = favoritesMapper.selectById(id);
        return favoritesConvertMapper.toFavoritesVO(favorites);
    }

    public boolean createOrRemove(Integer goodsId) {
        LambdaQueryWrapper<Favorites> queryWrapper = Wrappers.lambdaQuery(Favorites.class)
                .eq(Favorites::getMemberId, TokenUtils.getCurrentPrincipalId())
                .eq(Favorites::getGoodsId, goodsId);
        Favorites favorites = favoritesMapper.selectOne(queryWrapper);
        if (favorites == null) {
            favorites = new Favorites();
            favorites.setGoodsId(goodsId);
            favorites.setCreatedDate(LocalDateTime.now());
            favorites.setMemberId(TokenUtils.getCurrentPrincipalId());
            favoritesMapper.insert(favorites);
            return true;
        } else {
            favoritesMapper.realDeleteById(favorites.getId());
        }
        return false;
    }

    public void removeFavorites(Integer goodsId) {
        LambdaQueryWrapper<Favorites> queryWrapper = Wrappers.lambdaQuery(Favorites.class)
                .eq(Favorites::getMemberId, TokenUtils.getCurrentPrincipalId())
                .eq(Favorites::getGoodsId, goodsId);

    }
}
