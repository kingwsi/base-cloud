package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.memberaddress.MemberAddressVO;
import com.example.service.MemberAddressService;
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
* description: 会员地址 controller <br>
* date: 2021-07-09 <br>
* author: ws <br>
*/

@Api(tags = "会员地址")
@RestController
@RequestMapping("/api/member-address")
public class MemberAddressController {
    private final MemberAddressService memberAddressService;

    public MemberAddressController(MemberAddressService memberAddressService) {
        this.memberAddressService = memberAddressService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody MemberAddressVO memberAddressVO) {
        if (StringUtils.isEmpty(memberAddressVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = memberAddressService.updateById(memberAddressVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody MemberAddressVO memberAddressVO) {
        boolean result = memberAddressService.create(memberAddressVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = memberAddressService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<MemberAddressVO>> listOfPage(Page<MemberAddressVO> page, MemberAddressVO memberAddressVO) {
        IPage<MemberAddressVO> pageInfo = memberAddressService.listOfPage(page, memberAddressVO);
        return ResponseData.OK(pageInfo);
    }
}
