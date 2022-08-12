package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.RespCodeEnum;
import com.example.common.entity.goods.Goods;
import com.example.common.entity.goodsattribute.GoodsAttribute;
import com.example.common.entity.goodssku.GoodsSku;
import com.example.common.entity.goodssku.GoodsSkuConvertMapper;
import com.example.common.entity.memberaddress.MemberAddress;
import com.example.common.entity.shoppingbag.ShoppingBag;
import com.example.common.entity.trade.CreateTradeVO;
import com.example.common.entity.trade.Trade;
import com.example.common.entity.trade.TradeConvertMapper;
import com.example.common.entity.trade.TradeVO;
import com.example.common.entity.tradedetail.TradeDetail;
import com.example.common.enumerate.GoodsStatus;
import com.example.common.exception.CustomException;
import com.example.common.utils.TokenUtils;
import com.example.mapper.GoodsAttributeMapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.GoodsSkuMapper;
import com.example.mapper.MemberAddressMapper;
import com.example.mapper.ShoppingBagMapper;
import com.example.mapper.TradeDetailMapper;
import com.example.mapper.TradeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * description:  Service <br>
 * date: 2021-08-05 <br>
 * author: ws <br>
 */
@Service
@AllArgsConstructor
public class TradeService {

    private final TradeMapper tradeMapper;

    private final TradeConvertMapper tradeConvertMapper;

    private final TradeDetailMapper tradeDetailMapper;

    private final MemberAddressMapper memberAddressMapper;

    private final GoodsSkuMapper goodsSkuMapper;

    private final GoodsMapper goodsMapper;

    private final GoodsAttributeMapper goodsAttributeMapper;

    private final GoodsSkuConvertMapper goodsSkuConvertMapper;

    private final ShoppingBagMapper shoppingBagMapper;

    /**
     * 插入数据
     *
     * @param tradeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateTradeVO tradeVO) {
        Integer currentPrincipalId = TokenUtils.getCurrentPrincipalId();
        MemberAddress address = this.getAddress(currentPrincipalId, tradeVO.getAddressId());
        GoodsSku sku = this.getSku(tradeVO.getSkuId());
        if (sku.getStock() < tradeVO.getNum()) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        // 限购
        if (sku.getPurchaseLimit() > 0 && tradeVO.getNum() > sku.getStock()) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }

        Trade trade = new Trade();
        trade.setConsignee(address.getAddresseeName());
        trade.setConsigneeAddress(address.getFullAddress());
        trade.setConsigneeMobile(address.getAddresseeMobile());
        trade.setMemberId(currentPrincipalId);
        trade.setTotalAmount(sku.getPrice() * tradeVO.getNum());
        tradeMapper.insert(trade);
        TradeDetail tradeDetail = new TradeDetail();
        tradeDetail.setGoodsId(sku.getGoodsId());
        tradeDetail.setNum(tradeVO.getNum());
        tradeDetail.setSkuId(sku.getId());
        tradeDetail.setPrice(sku.getPrice());
        tradeDetail.setSkuImage(sku.getThumbnail());
        tradeDetail.setTradeId(trade.getId());
        tradeDetail.setPayAmount(trade.getPayAmount());
        tradeDetail.setSkuProperties(sku.getPropertyDescription());
        tradeDetailMapper.insert(tradeDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createByBag(CreateTradeVO tradeVO) {
        if (tradeVO.getShoppingBagIds() == null || tradeVO.getShoppingBagIds().isEmpty()) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        Integer currentPrincipalId = TokenUtils.getCurrentPrincipalId();
        MemberAddress address = this.getAddress(currentPrincipalId, tradeVO.getAddressId());

        List<ShoppingBag> shoppingBags = shoppingBagMapper.selectBatchIds(tradeVO.getShoppingBagIds());
        List<GoodsSku> skuArrayList = new ArrayList<>();
        shoppingBags.forEach(shoppingBag -> {
            GoodsSku sku = this.getSku(shoppingBag.getSkuId());
            if (sku.getStock() < shoppingBag.getNum()) {
                throw new CustomException(RespCodeEnum.FORBIDDEN);
            }
            // 限购
            if (sku.getPurchaseLimit() > 0 && shoppingBag.getNum() > sku.getStock()) {
                throw new CustomException(RespCodeEnum.FORBIDDEN);
            }
            skuArrayList.add(sku);
        });

        int total = skuArrayList.stream().mapToInt(GoodsSku::getPrice).sum();
        Trade trade = new Trade();
        trade.setConsignee(address.getAddresseeName());
        trade.setConsigneeAddress(address.getFullAddress());
        trade.setConsigneeMobile(address.getAddresseeMobile());
        trade.setMemberId(currentPrincipalId);
        trade.setTotalAmount(total);
        tradeMapper.insert(trade);
        skuArrayList.forEach(sku->{
            TradeDetail tradeDetail = new TradeDetail();
            tradeDetail.setGoodsId(sku.getGoodsId());
            tradeDetail.setNum(tradeVO.getNum());
            tradeDetail.setSkuId(sku.getId());
            tradeDetail.setPrice(sku.getPrice());
            tradeDetail.setSkuImage(sku.getThumbnail());
            tradeDetail.setTradeId(trade.getId());
            tradeDetail.setPayAmount(trade.getPayAmount());
            tradeDetail.setSkuProperties(sku.getPropertyDescription());
            tradeDetailMapper.insert(tradeDetail);
        });
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return tradeMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<TradeVO> listOfPage(Page<TradeVO> page, TradeVO vo) {
        return tradeMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     *
     * @param tradeVO
     * @return
     */
    public boolean updateById(TradeVO tradeVO) {
        return tradeMapper.updateById(tradeConvertMapper.toTrade(tradeVO)) > 0;
    }

    public TradeVO getById(Integer id) {
        Trade trade = tradeMapper.selectById(id);
        return tradeConvertMapper.toTradeVO(trade);
    }

    private MemberAddress getAddress(Integer memberId, Integer addressId) {
        MemberAddress memberAddress = memberAddressMapper.selectOne(Wrappers.lambdaQuery(MemberAddress.class)
                .eq(MemberAddress::getMemberId, memberId)
                .eq(MemberAddress::getId, addressId));
        if (memberAddress == null) {
            throw new CustomException("无效的地址！");
        }
        return memberAddress;
    }

    private GoodsSku getSku(Integer skuId) {
        GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
        if (goodsSku == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        Goods goods = goodsMapper.selectById(goodsSku.getGoodsId());
        if (goods == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        if (goods.getStatus() != GoodsStatus.ON_SELL) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectList(Wrappers.lambdaQuery(GoodsAttribute.class).eq(GoodsAttribute::getSkuId, skuId));
        if (goodsAttributes == null) {
            throw new CustomException(RespCodeEnum.FORBIDDEN);
        }
        return goodsSku;
    }
}
