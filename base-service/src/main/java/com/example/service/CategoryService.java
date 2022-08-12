package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.category.Category;
import com.example.common.entity.category.CategoryConvertMapper;
import com.example.common.entity.category.CategoryVO;
import com.example.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* description: 分类 Service <br>
* date: 2021-07-12 <br>
* author:  <br>
*/
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryConvertMapper categoryConvertMapper;

    public CategoryService(CategoryMapper categoryMapper, CategoryConvertMapper categoryConvertMapper) {
        this.categoryMapper = categoryMapper;
        this.categoryConvertMapper = categoryConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param categoryVO
     * @return
     */
    public boolean create(CategoryVO categoryVO) {
        return categoryMapper.insert(categoryConvertMapper.toCategory(categoryVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return categoryMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<CategoryVO> listOfPage(Page<CategoryVO> page, CategoryVO vo) {
        return categoryMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param categoryVO
     * @return
     */
    public boolean updateById(CategoryVO categoryVO) {
        return categoryMapper.updateById(categoryConvertMapper.toCategory(categoryVO)) > 0;
    }

    public CategoryVO getById(Integer id) {
        Category category = categoryMapper.selectById(id);
        return categoryConvertMapper.toCategoryVO(category);
    }

    public List<CategoryVO> listAll(CategoryVO vo) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = Wrappers.lambdaQuery(Category.class);
        if (!StringUtils.isEmpty(vo.getName())) {
            lambdaQueryWrapper.like(Category::getName, vo.getName());
        }
        List<Category> list = categoryMapper.selectList(lambdaQueryWrapper);
        return categoryConvertMapper.toCategoryVOList(list);
    }

    public List<CategoryVO> listChildren() {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = Wrappers.lambdaQuery(Category.class);
        lambdaQueryWrapper.notIn(Category::getParentId, -1);
        List<Category> list = categoryMapper.selectList(lambdaQueryWrapper);
        return categoryConvertMapper.toCategoryVOList(list);
    }
}
