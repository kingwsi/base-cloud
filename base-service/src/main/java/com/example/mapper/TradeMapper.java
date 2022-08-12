package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.trade.Trade;
import com.example.common.entity.trade.TradeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description:  Mapper接口 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Component
public interface TradeMapper extends BaseMapper<Trade> {
    IPage<TradeVO> selectPage(Page<TradeVO> page, @Param("vo") TradeVO vo);
}
