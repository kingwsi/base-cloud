package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.enumerate.ResourceTypeEnum;
import com.example.common.entity.resource.Resource;
import com.example.common.entity.resource.ResourceQuery;
import com.example.common.entity.resource.ResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    @Select("SELECT * FROM sys_resources WHERE type = #{route} AND deleted = 0")
    List<ResourceVO> selectAllByType(@Param("route") ResourceTypeEnum route);

    List<Resource> selectRouteByUserId(@Param("userId") String userId);

    List<Resource> selectByUserId(@Param("userId") String userId);

    List<Resource> selectByMethodAndUserIdAndUri(@Param("method") String method, @Param("userId") String userId);

    List<String> selectUrisByUser(@Param("userId") String userId, @Param("method") String method);

    List<Resource> selectByUser(@Param("userId") String userId);

    List<Resource> selectByUserAndMethod(@Param("userId") String userId, @Param("method") String method);

    IPage<ResourceVO> selectOfPage(Page page, @Param("query") ResourceQuery query);

    int countRepeat(ResourceVO resourceVO);
}
