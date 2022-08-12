package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.RespCodeEnum;
import com.example.common.entity.goods.Goods;
import com.example.common.entity.goodssku.GoodsSku;
import com.example.common.entity.shoppingbag.ShoppingBag;
import com.example.common.entity.shoppingbag.ShoppingBagConvertMapper;
import com.example.common.entity.shoppingbag.ShoppingBagVO;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.GoodsMapper;
import com.example.mapper.GoodsSkuMapper;
import com.example.mapper.ShoppingBagMapper;
import org.springframework.stereotype.Service;

/**
 * description:  Service <br>
 * date: 2021-08-03 <br>
 * author: ws <br>
 */
@Service
public class ShoppingBagService {

    private final ShoppingBagMapper shoppingbagMapper;

    private final GoodsSkuMapper goodsSkuMapper;

    private final ShoppingBagConvertMapper shoppingbagConvertMapper;

    private final GoodsMapper goodsMapper;

    public ShoppingBagService(ShoppingBagMapper shoppingbagMapper, GoodsSkuMapper goodsSkuMapper, ShoppingBagConvertMapper shoppingbagConvertMapper, GoodsMapper goodsMapper) {
        this.shoppingbagMapper = shoppingbagMapper;
        this.goodsSkuMapper = goodsSkuMapper;
        this.shoppingbagConvertMapper = shoppingbagConvertMapper;
        this.goodsMapper = goodsMapper;
    }

    /**
     * 插入数据
     *
     * @param shoppingBagVO
     * @return
     */
    public void create(ShoppingBagVO shoppingBagVO) {
        GoodsSku goodsSku = goodsSkuMapper.selectOne(Wrappers.lambdaQuery(GoodsSku.class).eq(GoodsSku::getId, shoppingBagVO.getSkuId()));
        if (goodsSku == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        Goods goods = goodsMapper.selectById(goodsSku.getGoodsId());
        if (goods == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        Integer currentPrincipalId = TokenUtils.getCurrentPrincipalId();
        shoppingBagVO.setMemberId(currentPrincipalId);
        ShoppingBag shoppingbag = shoppingbagMapper.selectOne(Wrappers.lambdaQuery(ShoppingBag.class)
                .eq(ShoppingBag::getMemberId, currentPrincipalId)
                .eq(ShoppingBag::getSkuId, shoppingBagVO.getSkuId()));
        if (shoppingbag != null) {
            shoppingbag.setNum(shoppingbag.getNum() + 1);
            shoppingbagMapper.updateById(shoppingbag);
        } else {
            shoppingBagVO.setNum(1);
            shoppingBagVO.setGoodsId(goods.getId());
            shoppingbagMapper.insert(shoppingbagConvertMapper.toShoppingbag(shoppingBagVO));
        }
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return shoppingbagMapper.realDeleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<ShoppingBagVO> listOfPage(Page<ShoppingBagVO> page, ShoppingBagVO vo) {
        vo.setMemberId(TokenUtils.getCurrentPrincipalId());
        return shoppingbagMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param shoppingbagVO
     * @return
     */
    public boolean updateById(ShoppingBagVO shoppingbagVO) {
        return shoppingbagMapper.updateById(shoppingbagConvertMapper.toShoppingbag(shoppingbagVO)) > 0;
    }

    public ShoppingBagVO getById(Integer id) {
        ShoppingBag shoppingbag = shoppingbagMapper.selectById(id);
        return shoppingbagConvertMapper.toShoppingbagVO(shoppingbag);
    }

    /**
     * 更新数量，返回累加后的数量
     *
     * @param id
     * @param num
     * @return
     */
    public Integer updateNum(Integer id, Integer num) {
        Integer currentPrincipalId = TokenUtils.getCurrentPrincipalId();
        ShoppingBag shoppingBag = shoppingbagMapper.selectOne(Wrappers.lambdaQuery(ShoppingBag.class)
                .eq(ShoppingBag::getMemberId, currentPrincipalId)
                .eq(ShoppingBag::getId, id));
        if (shoppingBag == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        } else {
            shoppingBag.setNum(shoppingBag.getNum() + num);
            if (num > 0) {
                shoppingbagMapper.updateById(shoppingBag);
            }
        }
        return shoppingBag.getNum();
    }
}
