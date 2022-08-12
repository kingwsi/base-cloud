package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.brand.Brand;
import com.example.common.entity.brand.BrandConvertMapper;
import com.example.common.entity.brand.BrandVO;
import com.example.mapper.BrandMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* description: 品牌 Service <br>
* date: 2021-07-15 <br>
* author: ws <br>
*/
@Service
public class BrandService {

    private final BrandMapper brandMapper;

    private final BrandConvertMapper brandConvertMapper;

    public BrandService(BrandMapper brandMapper, BrandConvertMapper brandConvertMapper) {
        this.brandMapper = brandMapper;
        this.brandConvertMapper = brandConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param brandVO
     * @return
     */
    public boolean create(BrandVO brandVO) {
        return brandMapper.insert(brandConvertMapper.toBrand(brandVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return brandMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<BrandVO> listOfPage(Page<BrandVO> page, BrandVO vo) {
        return brandMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param brandVO
     * @return
     */
    public boolean updateById(BrandVO brandVO) {
        return brandMapper.updateById(brandConvertMapper.toBrand(brandVO)) > 0;
    }

    public BrandVO getById(Integer id) {
        Brand brand = brandMapper.selectById(id);
        return brandConvertMapper.toBrandVO(brand);
    }

    /**
     * 查询列表
     * @param name
     * @return
     */
    public List<BrandVO> listBrands(String name) {
        LambdaQueryWrapper<Brand> lambdaQueryWrapper = Wrappers.lambdaQuery(Brand.class).eq(Brand::getEnable, true);

        if (!StringUtils.isEmpty(name)) {
            lambdaQueryWrapper.like(Brand::getName, name);
        }
        List<Brand> brands = brandMapper.selectList(lambdaQueryWrapper.orderByAsc(Brand::getSort));
        return brandConvertMapper.toBrandVOList(brands);
    }
}
