package com.example.admin.api;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.entity.customer.CustomerVO;
import com.example.service.CustomerService;
import com.example.common.bean.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * customer
 * </p>
 *
 * @author ws
 * @since 2020-12-29
 */
@Api(tags = "客户")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData updateById(@RequestBody CustomerVO customerVO) {
        if (StringUtils.isEmpty(customerVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = customerService.updateById(customerVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData create(@RequestBody CustomerVO customerVO) {
        boolean result = customerService.create(customerVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            ResponseData.FAIL("ID不能为空");
        }
        boolean result = customerService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData listOfPage(Page<CustomerVO> page, CustomerVO customerVO) {
        IPage<CustomerVO> pageInfo = customerService.listOfPage(page, customerVO);
        return ResponseData.OK(pageInfo);
    }
}
