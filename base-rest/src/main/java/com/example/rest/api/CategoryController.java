package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.common.entity.category.CategoryVO;
import com.example.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 授权登陆 <br>
 * date: 2021/7/01 19:14 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "分类")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseData<?> listCategory(CategoryVO vo){
        return ResponseData.OK(categoryService.listAll(vo));
    }
}
