package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.apiwhitelist.ApiWhitelist;
import com.example.common.entity.apiwhitelist.ApiWhitelistConvertMapper;
import com.example.common.entity.apiwhitelist.ApiWhitelistVO;
import com.example.mapper.ApiWhitelistMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* description: api白名单 Service <br>
* date: 2021-06-10 <br>
* author: ws <br>
*/
@Service
public class ApiWhitelistService {

    private final ApiWhitelistMapper apiwhitelistMapper;

    private final ApiWhitelistConvertMapper apiwhitelistConvertMapper;

    public ApiWhitelistService(ApiWhitelistMapper apiwhitelistMapper, ApiWhitelistConvertMapper apiwhitelistConvertMapper) {
        this.apiwhitelistMapper = apiwhitelistMapper;
        this.apiwhitelistConvertMapper = apiwhitelistConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param apiwhitelistVO
     * @return
     */
    public boolean create(ApiWhitelistVO apiwhitelistVO) {
        return apiwhitelistMapper.insert(apiwhitelistConvertMapper.toApiWhitelist(apiwhitelistVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return apiwhitelistMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<ApiWhitelistVO> listOfPage(Page<ApiWhitelistVO> page, ApiWhitelistVO vo) {
        return apiwhitelistMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param apiwhitelistVO
     * @return
     */
    public boolean updateById(ApiWhitelistVO apiwhitelistVO) {
        Assert.isNull(apiwhitelistVO.getId(), "ID不可为空");
        return apiwhitelistMapper.updateById(apiwhitelistConvertMapper.toApiWhitelist(apiwhitelistVO)) > 0;
    }

    public ApiWhitelistVO getById(Integer id) {
        ApiWhitelist apiwhitelist = apiwhitelistMapper.selectById(id);
        return apiwhitelistConvertMapper.toApiWhitelistVO(apiwhitelist);
    }

    public List<ApiWhitelist> listAll() {
        return apiwhitelistMapper.selectList(new QueryWrapper<ApiWhitelist>());
    }

    public List<String> listAllPath(String method) {
        return apiwhitelistMapper.selectListPathByMethod(method);
    }
}
