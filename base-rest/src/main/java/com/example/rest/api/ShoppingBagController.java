package com.example.rest.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.shoppingbag.ShoppingBagVO;
import com.example.service.ShoppingBagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
* date: 2021-08-03 <br>
* author: ws <br>
*/

@Api(tags = "购物袋")
@RestController
@RequestMapping("/api/shopping-bag")
public class ShoppingBagController {
    private final ShoppingBagService shoppingbagService;

    public ShoppingBagController(ShoppingBagService shoppingbagService) {
        this.shoppingbagService = shoppingbagService;
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody ShoppingBagVO shoppingbagVO) {
        shoppingbagService.create(shoppingbagVO);
        return ResponseData.OK();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        boolean result = shoppingbagService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PutMapping("/num/{id}/{num}")
    @ApiOperation("更新数量")
    public ResponseData<String> deleteById(@PathVariable Integer id,@PathVariable Integer num) {
        Integer total = shoppingbagService.updateNum(id, num);
        return ResponseData.OK(String.valueOf(total));
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<ShoppingBagVO>> listOfPage(Page<ShoppingBagVO> page, ShoppingBagVO shoppingbagVO) {
        IPage<ShoppingBagVO> pageInfo = shoppingbagService.listOfPage(page, shoppingbagVO);
        return ResponseData.OK(pageInfo);
    }
}
