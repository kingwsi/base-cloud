package com.example.admin.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.dictionary.DictionaryVO;
import com.example.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * description: DictionaryController <br>
 * date: 2020/8/6 11:04 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PutMapping
    @ApiOperation("更新字典")
    public ResponseData<String> updateById(@RequestBody DictionaryVO dictionaryVO) {
        if (StringUtils.isEmpty(dictionaryVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = dictionaryService.updateById(dictionaryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增字典")
    public ResponseData<String> create(@RequestBody DictionaryVO dictionaryVO) {
        boolean result = dictionaryService.create(dictionaryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除字典")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = dictionaryService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取字典分页")
    public ResponseData<IPage<DictionaryVO>> listOfPage(Page<DictionaryVO> page, DictionaryVO vo) {
        IPage<DictionaryVO> pageInfo = dictionaryService.listOfPage(page, vo);
        return ResponseData.OK(pageInfo);
    }
}
