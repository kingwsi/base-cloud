package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.tradedetail.TradeDetailVO;
import com.example.service.TradeDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/trade-detail")
public class TradeDetailController {
    private final TradeDetailService tradeDetailService;

    public TradeDetailController(TradeDetailService tradeDetailService) {
        this.tradeDetailService = tradeDetailService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody TradeDetailVO tradeDetailVO) {
        if (StringUtils.isEmpty(tradeDetailVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = tradeDetailService.updateById(tradeDetailVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody TradeDetailVO tradeDetailVO) {
        boolean result = tradeDetailService.create(tradeDetailVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = tradeDetailService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<TradeDetailVO>> listOfPage(Page<TradeDetailVO> page, TradeDetailVO tradeDetailVO) {
        IPage<TradeDetailVO> pageInfo = tradeDetailService.listOfPage(page, tradeDetailVO);
        return ResponseData.OK(pageInfo);
    }
}
