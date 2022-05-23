package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.dictionary.Dictionary;
import com.example.common.entity.dictionary.DictionaryConvertMapper;
import com.example.common.entity.dictionary.DictionaryVO;
import com.example.mapper.DictionaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
* description: 字典数据 Service <br>
* date: 2021-06-14 <br>
* author:  <br>
*/
@Service
public class DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    private final DictionaryConvertMapper dictionaryConvertMapper;

    public DictionaryService(DictionaryMapper dictionaryMapper, DictionaryConvertMapper dictionaryConvertMapper) {
        this.dictionaryMapper = dictionaryMapper;
        this.dictionaryConvertMapper = dictionaryConvertMapper;
    }

    /**
     * 插入数据
     *
     * @param dictionaryVO
     * @return
     */
    public boolean create(DictionaryVO dictionaryVO) {
        return dictionaryMapper.insert(dictionaryConvertMapper.toDictionary(dictionaryVO)) > 0;
    }

    /**
     * 删除一条记录
     *
     * @param id
     * @return
     */
    public boolean removeById(Integer id) {
        return dictionaryMapper.deleteById(id) > 0;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param vo
     * @return
     */
    public IPage<DictionaryVO> listOfPage(Page<DictionaryVO> page, DictionaryVO vo) {
        return dictionaryMapper.selectPage(page, vo);
    }

    /**
     * 更新数据 id不能为空
     * @param dictionaryVO
     * @return
     */
    public boolean updateById(DictionaryVO dictionaryVO) {
        return dictionaryMapper.updateById(dictionaryConvertMapper.toDictionary(dictionaryVO)) > 0;
    }

    public DictionaryVO getById(Integer id) {
        Dictionary dictionary = dictionaryMapper.selectById(id);
        return dictionaryConvertMapper.toDictionaryVO(dictionary);
    }
}
