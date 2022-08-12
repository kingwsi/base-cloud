package com.example.admin.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.bean.ResponseData;
import com.example.common.entity.operatelog.OperateLogVO;
import com.example.service.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* description: 操作记录 controller <br>
* date: 2021-08-05 <br>
* author: ws <br>
*/

@Api(tags = "操作记录")
@RestController
@RequestMapping("/api/operate-log")
public class OperateLogController {
    private final OperateLogService operateLogService;

    public OperateLogController(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<String> updateById(@RequestBody OperateLogVO operateLogVO) {
        if (StringUtils.isEmpty(operateLogVO.getId())) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = operateLogService.updateById(operateLogVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseData<String> create(@RequestBody OperateLogVO operateLogVO) {
        boolean result = operateLogService.create(operateLogVO);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public ResponseData<String> deleteById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseData.FAIL("ID不能为空");
        }
        boolean result = operateLogService.removeById(id);
        return result ? ResponseData.OK() : ResponseData.FAIL();
    }

    @GetMapping("/page")
    @ApiOperation("获取分页")
    public ResponseData<IPage<OperateLogVO>> listOfPage(Page<OperateLogVO> page, OperateLogVO operateLogVO) {
        IPage<OperateLogVO> pageInfo = operateLogService.listOfPage(page, operateLogVO);
        return ResponseData.OK(pageInfo);
    }
}
