package com.example.common.entity.operatelog;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 操作记录 转换工具 <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface OperateLogConvertMapper {
    OperateLog toOperateLog(OperateLogVO vo);

    List<OperateLog> toOperateLogList(List<OperateLogVO> vo);

    OperateLogVO toOperateLogVO(OperateLog operatelog);

    List<OperateLogVO> toOperateLogVOList(List<OperateLog> vo);
}