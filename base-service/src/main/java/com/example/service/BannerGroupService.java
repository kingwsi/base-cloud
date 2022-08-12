package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.bannergroup.BannerGroup;
import com.example.common.entity.bannergroup.BannerGroupConvertMapper;
import com.example.common.entity.bannergroup.BannerGroupVO;
import com.example.mapper.BannerGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* description: banner分组 Service <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Service
public class BannerGroupService {

    private final BannerGroupMapper bannergroupMapper;

    private final BannerGroupConvertMapper bannergroupConvertMapper;

    public BannerGroupService(BannerGroupMapper bannergroupMapper, BannerGroupConvertMapper bannergroupConvertMapper) {
        this.bannergroupMapper = bannergroupMapper;
        this.bannergroupConvertMapper = bannergroupConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param bannergroupVO
     * @return
     */
    public boolean create(BannerGroupVO bannergroupVO) {
        return bannergroupMapper.insert(bannergroupConvertMapper.toBannerGroup(bannergroupVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return bannergroupMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<BannerGroupVO> listOfPage(Page<BannerGroupVO> page, BannerGroupVO vo) {
        return bannergroupMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param bannergroupVO
     * @return
     */
    public boolean updateById(BannerGroupVO bannergroupVO) {
        return bannergroupMapper.updateById(bannergroupConvertMapper.toBannerGroup(bannergroupVO)) > 0;
    }

    public BannerGroupVO getById(Integer id) {
        BannerGroup bannergroup = bannergroupMapper.selectById(id);
        return bannergroupConvertMapper.toBannerGroupVO(bannergroup);
    }

    public List<BannerGroupVO> list() {
        List<BannerGroup> bannerGroups = bannergroupMapper.selectList(Wrappers.emptyWrapper());
        return bannergroupConvertMapper.toBannerGroupVOList(bannerGroups);
    }
}
