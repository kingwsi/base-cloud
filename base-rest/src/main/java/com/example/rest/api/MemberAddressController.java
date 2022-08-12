package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.common.entity.memberaddress.MemberAddressVO;
import com.example.common.utils.TokenUtils;
import com.example.service.MemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: TODO <br>
 * date: 2021/07/09 14:24 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "地址管理")
@RestController
@RequestMapping("/api/member-address")
public class MemberAddressController {
    private final MemberAddressService memberAddressService;

    public MemberAddressController(MemberAddressService memberAddressService) {
        this.memberAddressService = memberAddressService;
    }

    @ApiOperation("获取地址列表")
    @GetMapping("/list")
    public ResponseData<?> listAddress(){
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        List<MemberAddressVO> list = memberAddressService.listByCurrentMember(memberId);
        return ResponseData.OK(list);
    }


    @ApiOperation("将地址设为默认地址")
    @PutMapping("/default/{id}")
    public ResponseData<?> updateDefault(@PathVariable Integer id) {
        memberAddressService.setDefault(id);
        return ResponseData.OK();
    }

    @ApiOperation("获取默认地址")
    @GetMapping("/default")
    public ResponseData<?> getDefault() {
        Integer memberId = TokenUtils.getCurrentPrincipalId();
        return ResponseData.OK(memberAddressService.getDefaultByMember(memberId));
    }

    @ApiOperation("新增地址")
    @PostMapping()
    public ResponseData<?> getDefault(@RequestBody @Validated MemberAddressVO vo) {
        memberAddressService.create(vo);
        return ResponseData.OK();
    }

    @ApiOperation("删除地址")
    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable Integer id) {
        memberAddressService.removeById(id);
        return ResponseData.OK();
    }

    @ApiOperation("获取收货地址")
    @GetMapping("/{id}")
    public ResponseData<?> getById(@PathVariable Integer id) {
        return ResponseData.OK(memberAddressService.getById(id));
    }

    @ApiOperation("更新收货地址")
    @PutMapping
    public ResponseData<?> updateById(@RequestBody @Validated MemberAddressVO memberAddressVO) {
        return ResponseData.OK(memberAddressService.updateById(memberAddressVO));
    }
}
