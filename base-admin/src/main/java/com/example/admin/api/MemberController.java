package com.example.admin.api;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.entity.member.MemberVO;
import com.example.service.MemberService;
import com.example.common.bean.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* description: 会员 controller <br>
* date: 2021-04-13 <br>
* author: ws <br>
*/

@Api(tags = "会员")
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody MemberVO memberVO) {
        if (StringUtils.isEmpty(memberVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = memberService.updateById(memberVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody MemberVO memberVO) {
        boolean result = memberService.create(memberVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = memberService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<MemberVO>> listOfPage(Page<MemberVO> page, MemberVO memberVO) {
        IPage<MemberVO> pageInfo = memberService.listOfPage(page, memberVO);
        return ResponseData.OK(pageInfo);
    }
}
