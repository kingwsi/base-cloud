package com.example.admin.api;


import com.example.common.bean.ResponseData;
import com.example.common.entity.category.CategoryVO;
import com.example.service.CategoryService;
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

import java.util.List;

/**
* description: 分类 controller <br>
* date: 2021-07-12 <br>
* author:  <br>
*/

@Api(tags = "分类")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody CategoryVO categoryVO) {
        if (StringUtils.isEmpty(categoryVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = categoryService.updateById(categoryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody CategoryVO categoryVO) {
        boolean result = categoryService.create(categoryVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = categoryService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/list")
    @ApiOperation("获取list")
    public ResponseData<List<CategoryVO>> listAll(CategoryVO categoryVO) {
        List<CategoryVO> categoryVOS = categoryService.listAll(categoryVO);
        return ResponseData.OK(categoryVOS);
    }

    @GetMapping("/children")
    @ApiOperation("获取 children list")
    public ResponseData<List<CategoryVO>> listChildren() {
        List<CategoryVO> categoryVOS = categoryService.listChildren();
        return ResponseData.OK(categoryVOS);
    }
}
