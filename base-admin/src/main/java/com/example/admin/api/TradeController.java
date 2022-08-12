package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.trade.TradeVO;
import com.example.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody TradeVO tradeVO) {
        if (StringUtils.isEmpty(tradeVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = tradeService.updateById(tradeVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

//    @PostMapping
//    @ApiOperation("新增")
//    public ResponseData<String> create(@RequestBody TradeVO tradeVO) {
//        boolean result = tradeService.create(tradeVO);
//        return result ? ResponseData.OK() : ResponseData.FAIL();
//    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = tradeService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<TradeVO>> listOfPage(Page<TradeVO> page, TradeVO tradeVO) {
        IPage<TradeVO> pageInfo = tradeService.listOfPage(page, tradeVO);
        return ResponseData.OK(pageInfo);
    }
}
