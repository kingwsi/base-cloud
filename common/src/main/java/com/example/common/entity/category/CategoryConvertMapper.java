package com.example.common.entity.category;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 分类 转换工具 <br>
* date: 2021-07-12 <br>
* author:  <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface CategoryConvertMapper {
    Category toCategory(CategoryVO vo);

    List<Category> toCategoryList(List<CategoryVO> vo);

    CategoryVO toCategoryVO(Category category);

    List<CategoryVO> toCategoryVOList(List<Category> vo);
}