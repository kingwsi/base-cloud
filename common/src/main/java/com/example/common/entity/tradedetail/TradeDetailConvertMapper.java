package com.example.common.entity.tradedetail;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description:  转换工具 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface TradeDetailConvertMapper {
    TradeDetail toTradeDetail(TradeDetailVO vo);

    List<TradeDetail> toTradeDetailList(List<TradeDetailVO> vo);

    TradeDetailVO toTradeDetailVO(TradeDetail tradedetail);

    List<TradeDetailVO> toTradeDetailVOList(List<TradeDetail> vo);
}