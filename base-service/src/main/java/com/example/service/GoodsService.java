package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.brand.Brand;
import com.example.common.entity.category.Category;
import com.example.common.entity.favorites.Favorites;
import com.example.common.entity.goods.Goods;
import com.example.common.entity.goods.GoodsConvertMapper;
import com.example.common.entity.goods.GoodsInfoVO;
import com.example.common.entity.goods.GoodsListVO;
import com.example.common.entity.goods.GoodsVO;
import com.example.common.entity.goodsdetail.GoodsDetail;
import com.example.common.enumerate.GoodsStatus;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.BrandMapper;
import com.example.mapper.CategoryMapper;
import com.example.mapper.FavoritesMapper;
import com.example.mapper.GoodsDetailMapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.GoodsSkuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: 商品 Service <br>
 * date: 2021-07-15 <br>
 * author: ws <br>
 */
@Service
public class GoodsService {

    private final GoodsMapper goodsMapper;

    private final GoodsConvertMapper goodsConvertMapper;

    private final GoodsSkuMapper goodsSkuMapper;

    private final CategoryMapper categoryMapper;

    private final BrandMapper brandMapper;

    private final GoodsDetailMapper goodsDetailMapper;

    private final FavoritesMapper favoritesMapper;

    public GoodsService(GoodsMapper goodsMapper, GoodsConvertMapper goodsConvertMapper, GoodsSkuMapper goodsSkuMapper, CategoryMapper categoryMapper, BrandMapper brandMapper, GoodsDetailMapper goodsDetailMapper, FavoritesMapper favoritesMapper) {
        this.goodsMapper = goodsMapper;
        this.goodsConvertMapper = goodsConvertMapper;
        this.goodsSkuMapper = goodsSkuMapper;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
        this.goodsDetailMapper = goodsDetailMapper;
        this.favoritesMapper = favoritesMapper;
    }

    /**
     * 插入数据
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Goods create(GoodsVO vo) {
        Integer brandCount = brandMapper.selectCount(Wrappers.lambdaQuery(Brand.class)
                .eq(Brand::getId, vo.getBrandId())
                .eq(Brand::getEnable, true));
        if (brandCount == null || brandCount == 0) {
            throw new CustomException("无效的品牌");
        }

        Category category = categoryMapper.selectById(vo.getCategoryId());

        if (category == null) {
            throw new CustomException("无效的分类");
        }
        Goods goods = goodsConvertMapper.toGoods(vo);
        goods.setStatus(GoodsStatus.EDIT);
        if (goodsMapper.insert(goods) < 1) {
            throw new CustomException("新增失败！");
        }
        return goods;
    }

    /**
     * 新增商品明细
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean createDetail(Integer id, GoodsDetail goodsDetail) {
        Integer count = goodsMapper.selectCount(Wrappers.lambdaQuery(Goods.class)
                .eq(Goods::getId, id));
        if (count == null || count < 1) {
            throw new CustomException("无效商品");
        }
        goodsDetail.setId(id);
        return goodsDetailMapper.insert(goodsDetail) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return goodsMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<GoodsVO> listOfPage(Page<GoodsVO> page, GoodsVO vo) {
        return goodsMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param goodsVO
     * @return
     */
    public boolean updateById(GoodsVO goodsVO) {
        return goodsMapper.updateById(goodsConvertMapper.toGoods(goodsVO)) > 0;
    }

    public GoodsVO getById(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        return goodsConvertMapper.toGoodsVO(goods);
    }

    public List<GoodsListVO> page(Page<GoodsVO> page, GoodsVO vo) {
        return goodsMapper.selectGoodsList(page, vo);
    }

    public GoodsDetail getDetailById(Integer id) {
        return goodsDetailMapper.selectById(id);
    }

    public void getSpecs(Integer goodsId) {
    }

    public Boolean updateDetail(GoodsDetail detail) {
        return goodsDetailMapper.updateById(detail) > 0;
    }

    public GoodsInfoVO getGoodsWithDetailById(Integer goodsId) {
        GoodsInfoVO goodsInfoVO = goodsMapper.selectGoodsWithDetail(goodsId);
        Integer count = favoritesMapper.selectCount(
                Wrappers.lambdaQuery(Favorites.class)
                        .eq(Favorites::getMemberId, TokenUtils.getCurrentPrincipalId())
                        .eq(Favorites::getGoodsId, goodsId));
        if (count != null && count > 0) {
            goodsInfoVO.setFavorites(true);
        }
        return goodsInfoVO;
    }
}
