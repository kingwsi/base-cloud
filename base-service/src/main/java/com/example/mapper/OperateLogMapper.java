package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.operatelog.OperateLog;
import com.example.common.entity.operatelog.OperateLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 操作记录 Mapper接口 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Component
public interface OperateLogMapper extends BaseMapper<OperateLog> {
    IPage<OperateLogVO> selectPage(Page<OperateLogVO> page, @Param("vo") OperateLogVO vo);
}
