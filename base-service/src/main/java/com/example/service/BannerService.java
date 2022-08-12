package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.banner.Banner;
import com.example.common.entity.banner.BannerConvertMapper;
import com.example.common.entity.banner.BannerVO;
import com.example.mapper.BannerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* description: banner Service <br>
* date: 2021-07-12 <br>
* author: ws <br>
*/
@Service
public class BannerService {

    private final BannerMapper bannerMapper;

    private final BannerConvertMapper bannerConvertMapper;

    public BannerService(BannerMapper bannerMapper, BannerConvertMapper bannerConvertMapper) {
        this.bannerMapper = bannerMapper;
        this.bannerConvertMapper = bannerConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param bannerVO
     * @return
     */
    public boolean create(BannerVO bannerVO) {
        return bannerMapper.insert(bannerConvertMapper.toBanner(bannerVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return bannerMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<BannerVO> listOfPage(Page<BannerVO> page, BannerVO vo) {
        return bannerMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param bannerVO
     * @return
     */
    public boolean updateById(BannerVO bannerVO) {
        return bannerMapper.updateById(bannerConvertMapper.toBanner(bannerVO)) > 0;
    }

    public BannerVO getById(Integer id) {
        Banner banner = bannerMapper.selectById(id);
        return bannerConvertMapper.toBannerVO(banner);
    }

    public List<BannerVO> listByCode(String code) {
        List<Banner> banners = bannerMapper.selectList(Wrappers.lambdaQuery(Banner.class).eq(Banner::getGroupCode, code));
        return bannerConvertMapper.toBannerVOList(banners);
    }
}
