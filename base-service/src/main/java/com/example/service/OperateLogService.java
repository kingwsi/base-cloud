package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.operatelog.OperateLog;
import com.example.common.entity.operatelog.OperateLogConvertMapper;
import com.example.common.entity.operatelog.OperateLogVO;
import com.example.mapper.OperateLogMapper;
import org.springframework.stereotype.Service;

/**
* description: 操作记录 Service <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/
@Service
public class OperateLogService {

    private final OperateLogMapper operatelogMapper;

    private final OperateLogConvertMapper operatelogConvertMapper;

    public OperateLogService(OperateLogMapper operatelogMapper, OperateLogConvertMapper operatelogConvertMapper) {
        this.operatelogMapper = operatelogMapper;
        this.operatelogConvertMapper = operatelogConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param operatelogVO
     * @return
     */
    public boolean create(OperateLogVO operatelogVO) {
        return operatelogMapper.insert(operatelogConvertMapper.toOperateLog(operatelogVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return operatelogMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<OperateLogVO> listOfPage(Page<OperateLogVO> page, OperateLogVO vo) {
        return operatelogMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param operatelogVO
     * @return
     */
    public boolean updateById(OperateLogVO operatelogVO) {
        return operatelogMapper.updateById(operatelogConvertMapper.toOperateLog(operatelogVO)) > 0;
    }

    public OperateLogVO getById(Integer id) {
        OperateLog operatelog = operatelogMapper.selectById(id);
        return operatelogConvertMapper.toOperateLogVO(operatelog);
    }
}
