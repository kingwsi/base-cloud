package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.tradedetail.TradeDetail;
import com.example.common.entity.tradedetail.TradeDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description:  Mapper接口 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Component
public interface TradeDetailMapper extends BaseMapper<TradeDetail> {
    IPage<TradeDetailVO> selectPage(Page<TradeDetailVO> page, @Param("vo") TradeDetailVO vo);
}
