package com.example.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.entity.goods.Goods;
import com.example.common.entity.goodsattribute.GoodsAttribute;
import com.example.common.entity.goodsattribute.GoodsAttributeVO;
import com.example.common.entity.goodssku.GoodsSku;
import com.example.common.entity.goodssku.GoodsSkuVO;
import com.example.common.enumerate.GoodsStatus;
import com.example.common.exception.CustomException;
import com.example.mapper.GoodsAttributeMapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.GoodsSkuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 商品规格 Service <br>
 * date: 2021-07-15 <br>
 * author: ws <br>
 */
@Service
public class GoodsSkuService {
    private final GoodsSkuMapper goodsSkuMapper;

    private final GoodsMapper goodsMapper;

    private final GoodsAttributeMapper goodsAttributeMapper;

    public GoodsSkuService(GoodsSkuMapper goodsSkuMapper, GoodsMapper goodsMapper, GoodsAttributeMapper goodsAttributeMapper) {
        this.goodsSkuMapper = goodsSkuMapper;
        this.goodsMapper = goodsMapper;
        this.goodsAttributeMapper = goodsAttributeMapper;
    }

    /**
     * 新增规格list
     *
     * @param goodsId
     * @param list
     */
    @Transactional(rollbackFor = Exception.class)
    public void create(Integer goodsId, List<GoodsSkuVO> list) {
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods == null) {
            throw new CustomException("商品不存在");
        }
        HashSet<String> set = new HashSet<>();
        list.forEach(sku -> {
                    List<String> collect = sku.getAttrs()
                            .stream()
                            .map(GoodsAttributeVO::getAttributeValue)
                            .collect(Collectors.toList());
                    if (collect.size() > 3) {
                        throw new CustomException("规格超出3个");
                    }
                    set.add(String.join("_", collect));
                }

        );
        if (list.size() != set.size()) {
            throw new CustomException("规格重复");
        }

        List<Integer> priceList = list.stream().map(GoodsSkuVO::getPrice).sorted().collect(Collectors.toList());
        list.forEach(o -> {
            GoodsSku goodsSku = o.toGoodsSku();
            goodsSku.setGoodsId(goods.getId());
            goodsSkuMapper.insert(goodsSku);
            o.getAttrs().forEach(attr ->
                    goodsAttributeMapper.insert(new GoodsAttribute(goodsSku.getId(), goodsId, attr.getAttributeName(), attr.getAttributeValue())));
        });
        goods.setMaxPrice(priceList.get(0));
        goods.setMaxPrice(priceList.get(priceList.size() - 1));
        goods.setStatus(GoodsStatus.EDIT);
        goodsMapper.updateById(goods);

    }

    @Transactional(rollbackFor = Exception.class)
    public void create(GoodsSkuVO skuVO) {
        Goods goods = goodsMapper.selectById(skuVO.getGoodsId());
        if (goods == null) {
            throw new CustomException("商品不存在");
        }
        // TODO 重复检测
        GoodsSku goodsSku = skuVO.toGoodsSku();
        goodsSku.setGoodsId(goods.getId());
        goodsSkuMapper.insert(goodsSku);
        skuVO.getAttrs().forEach(o -> goodsAttributeMapper.insert(new GoodsAttribute(goods.getId(), skuVO.getGoodsId(), o.getAttributeName(), o.getAttributeValue())));
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean updateById(GoodsSkuVO skuVO) {
        GoodsSku goodsSku = goodsSkuMapper.selectById(skuVO.getId());
        Assert.notNull(goodsSku, "对象不存在，请检查id");
        goodsSkuMapper.updateById(skuVO.toGoodsSku());
        goodsAttributeMapper.delete(Wrappers.lambdaQuery(GoodsAttribute.class)
                .eq(GoodsAttribute::getSkuId, goodsSku.getId()));
        skuVO.getAttrs().forEach(o -> goodsAttributeMapper.insert(new GoodsAttribute(goodsSku.getId(), skuVO.getGoodsId(), o.getAttributeName(), o.getAttributeValue())));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSkuAndSpec(Integer skuId) {
        goodsAttributeMapper.delete(Wrappers.lambdaQuery(GoodsAttribute.class)
                .eq(GoodsAttribute::getSkuId, skuId));
        return goodsSkuMapper.deleteById(skuId) > 0;
    }

    public List<GoodsSkuVO> listSpecs(Integer goodsId) {
        return goodsSkuMapper.selectSKUByGoodsId(goodsId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateList(Integer goodsId, List<GoodsSkuVO> list) {
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods == null) {
            throw new CustomException("商品不存在");
        }
        // 删除非更新的sku
        List<Integer> updateIds = list.stream().filter(item -> item.getId() != null).map(GoodsSkuVO::getId).collect(Collectors.toList());
        goodsSkuMapper.deleteByGoodsId(goodsId, updateIds);

        // 删除sku属性
        goodsAttributeMapper.delete(Wrappers.lambdaQuery(GoodsAttribute.class).eq(GoodsAttribute::getGoodsId, goodsId));

        List<Integer> priceList = list.stream().map(GoodsSkuVO::getPrice).sorted().collect(Collectors.toList());
        list.forEach(o -> {
            GoodsSku goodsSku = o.toGoodsSku();
            goodsSku.setGoodsId(goods.getId());
            String description = o.getAttrs().stream().map(attr->{
                goodsAttributeMapper.insert(new GoodsAttribute(goodsSku.getId(), goodsId, attr.getAttributeName(), attr.getAttributeValue()));
                return attr.getAttributeValue();
            }).collect(Collectors.joining(";"));
            goodsSku.setPropertyDescription(description);
            if (o.getId() != null) {
                goodsSkuMapper.updateById(goodsSku);
            } else {
                goodsSkuMapper.insert(goodsSku);
            }
        });
        goods.setMaxPrice(priceList.get(0));
        goods.setMaxPrice(priceList.get(priceList.size() - 1));
        goods.setStatus(GoodsStatus.EDIT);
        goodsMapper.updateById(goods);
    }
}
