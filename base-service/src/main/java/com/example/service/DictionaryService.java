package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.common.entity.dictionary.Dictionary;
import com.example.mapper.DictionaryMapper;
import com.example.common.entity.dictionary.DictionaryConvertMapper;
import com.example.common.entity.dictionary.DictionaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: DictionaryService <br>
 * date: 2020/8/6 10:29 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private DictionaryConvertMapper dictionaryConvertMapper;

    public boolean create(DictionaryVO dictionaryVO) {
        Dictionary dictionary = dictionaryConvertMapper.toDictionary(dictionaryVO);
        return dictionaryMapper.insert(dictionary) > 0;
    }

    public boolean updateById(DictionaryVO dictionaryVO) {
        Dictionary dictionary = dictionaryConvertMapper.toDictionary(dictionaryVO);
        return this.dictionaryMapper.updateById(dictionary) > 0;
    }

    public boolean removeById(Integer id) {
        return this.dictionaryMapper.deleteById(id) > 0;
    }

    public IPage<DictionaryVO> listOfPage(Page<DictionaryVO> page, DictionaryVO dictionaryVO) {
        return dictionaryMapper.selectPage(page, dictionaryVO);
    }
}
