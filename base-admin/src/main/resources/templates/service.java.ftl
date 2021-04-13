package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.${entity?lower_case}.${entity};
import com.example.common.entity.${entity?lower_case}.${entity}ConvertMapper;
import com.example.common.entity.${entity?lower_case}.${entity}VO;
import com.example.mapper.${entity}Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
* description: ${table.comment} Service <br>
* date: ${date} <br>
* author: ${author} <br>
*/
@Service
public class ${entity}Service {

    private final ${entity}Mapper ${entity?lower_case}Mapper;

    private final ${entity}ConvertMapper ${entity?lower_case}ConvertMapper;

    public ${entity}Service(${entity}Mapper ${entity?lower_case}Mapper, ${entity}ConvertMapper ${entity?lower_case}ConvertMapper) {
        this.${entity?lower_case}Mapper = ${entity?lower_case}Mapper;
        this.${entity?lower_case}ConvertMapper = ${entity?lower_case}ConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param ${entity?lower_case}VO
     * @return
     */
    public boolean create(${entity}VO ${entity?lower_case}VO) {
        return ${entity?lower_case}Mapper.insert(${entity?lower_case}ConvertMapper.to${entity}(${entity?lower_case}VO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return ${entity?lower_case}Mapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<${entity}VO> listOfPage(Page<${entity}VO> page, ${entity}VO vo) {
        return ${entity?lower_case}Mapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param ${entity?lower_case}VO
     * @return
     */
    public boolean updateById(${entity}VO ${entity?lower_case}VO) {
        Assert.isNull(${entity?lower_case}VO.getId(), "ID不可为空");
        return ${entity?lower_case}Mapper.updateById(${entity?lower_case}ConvertMapper.to${entity}(${entity?lower_case}VO)) > 0;
    }

    public ${entity}VO getById(Integer id) {
        ${entity} ${entity?lower_case} = ${entity?lower_case}Mapper.selectById(id);
        return ${entity?lower_case}ConvertMapper.to${entity}VO(${entity?lower_case});
    }
}
