package com.example.mapper;

import com.example.common.entity.dictionary.Dictionary;
import com.example.common.entity.dictionary.DictionaryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 字典数据 Mapper接口 <br>
* date: 2021-06-14 <br>
* author:  <br>
*/
@Component
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    IPage<DictionaryVO> selectPage(Page<DictionaryVO> page, @Param("vo") DictionaryVO vo);
}
