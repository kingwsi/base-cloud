package com.example.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.service.entity.dictionary.Dictionary;
import com.example.service.entity.dictionary.DictionaryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * description: DictionaryMapper <br>
 * date: 2020/8/6 10:35 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Component
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    IPage<DictionaryVO> selectPage(Page<DictionaryVO> page, @Param("vo") DictionaryVO vo);
}
