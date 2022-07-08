package com.example.admin.api;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.entity.dictionary.DictionaryVO;
import com.example.service.DictionaryService;
import com.example.common.bean.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* description: 字典数据 controller <br>
* date: 2021-06-14 <br>
* author:  <br>
*/

@Api(tags = "字典数据")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody DictionaryVO dictionaryVO) {
        if (StringUtils.isEmpty(dictionaryVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = dictionaryService.updateById(dictionaryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody DictionaryVO dictionaryVO) {
        boolean result = dictionaryService.create(dictionaryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = dictionaryService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<DictionaryVO>> listOfPage(Page<DictionaryVO> page, DictionaryVO dictionaryVO) {
        IPage<DictionaryVO> pageInfo = dictionaryService.listOfPage(page, dictionaryVO);
        return ResponseData.OK(pageInfo);
    }
}
