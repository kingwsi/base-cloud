package com.example.rest.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.trade.CreateTradeVO;
import com.example.common.entity.trade.TradeVO;
import com.example.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* description:  controller <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/

@Api(tags = "")
@RestController
@RequestMapping("/api/trade")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    
    @PostMapping
    @ApiOperation("创建订单")
    public ResponseData<String> create(@RequestBody CreateTradeVO tradeVO) {
        tradeService.create(tradeVO);
        return ResponseData.OK();
    }

    @PostMapping("/shopping-bag")
    @ApiOperation("创建订单")
    public ResponseData<String> createByShoppingBag(@RequestBody CreateTradeVO tradeVO) {
        tradeService.createByBag(tradeVO);
        return ResponseData.OK();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        return ResponseData.OK();
    }

    @GetMapping("/info")
    @ApiOperation("获取信息")
    public ResponseData<String> getById(@PathVariable Integer id) {
        return ResponseData.OK();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<TradeVO>> listOfPage(Page<TradeVO> page, TradeVO tradeVO) {
        IPage<TradeVO> pageInfo = tradeService.listOfPage(page, tradeVO);
        return ResponseData.OK(pageInfo);
    }
}
