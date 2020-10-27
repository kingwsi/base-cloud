package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.utils.TokenUtils;
import com.example.common.enumerate.ResourceTypeEnum;
import com.example.common.exception.CustomException;
import com.example.common.entity.resource.Resource;
import com.example.common.entity.resource.ResourceConvertMapper;
import com.example.common.entity.resource.ResourceQuery;
import com.example.common.entity.resource.ResourceVO;
import com.example.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceMapper resourceMapper;

    private final ResourceConvertMapper resourceConvertMapper;

    public ResourceService(ResourceMapper resourceMapper, ResourceConvertMapper resourceConvertMapper) {
        this.resourceMapper = resourceMapper;
        this.resourceConvertMapper = resourceConvertMapper;
    }

    public void create(ResourceVO vo) {
        if (checkRepeat(vo)) {
            throw new CustomException("资源重复");
        }
        Resource resource = resourceConvertMapper.resourceVOToResource(vo);
        this.resourceMapper.insert(resource);
    }

    public int updateById(ResourceVO vo) {
        if (checkRepeat(vo)) {
            throw new CustomException("资源重复");
        }
        Resource resource = resourceConvertMapper.resourceVOToResource(vo);
        return this.resourceMapper.updateById(resource);
    }

    public List<String> listUrisByMethodAndUser(String method, String userId) {
        return resourceMapper.selectUrisByUser(method, userId);
    }

    public List<ResourceVO> listByType(ResourceTypeEnum route) {
        return resourceMapper.selectAllByType(route);
    }

    public IPage<ResourceVO> listOfPage(Page page, ResourceQuery resourceVO) {
        return resourceMapper.selectOfPage(page, resourceVO);
    }

    /**
     * 检查是否重复
     * 规则：uri唯一，MENU类型名称唯一
     *
     * @param resourceVO
     * @return true 重复 false 不重复
     */
    private boolean checkRepeat(ResourceVO resourceVO) {
        int repeatCount = resourceMapper.countRepeat(resourceVO);
        return repeatCount > 0;
    }

    public boolean deleteById(String id) {
        int rowCount = resourceMapper.deleteById(id);
        return rowCount > 0;
    }

    public List<ResourceVO> list() {
        List<Resource> resources = resourceMapper.selectList(Wrappers.emptyWrapper());
        return resourceConvertMapper.toResourceVOs(resources);
    }

    public List<ResourceVO> currentUserRouters() {
        List<Resource> resources = resourceMapper.selectRouteByUserId(TokenUtils.getCurrentUser().getId());
        return resourceConvertMapper.toResourceVOs(resources);
    }
}
