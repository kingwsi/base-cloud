package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.category.Category;
import com.example.common.entity.category.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 分类 Mapper接口 <br>
* date: 2021-07-12 <br>
* author:  <br>
*/
@Component
public interface CategoryMapper extends BaseMapper<Category> {
    IPage<CategoryVO> selectPage(Page<CategoryVO> page, @Param("vo") CategoryVO vo);
}
