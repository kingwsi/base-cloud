package com.example.common.entity.dictionary;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 字典数据 转换工具 <br>
* date: 2021-06-14 <br>
* author:  <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface DictionaryConvertMapper {
    Dictionary toDictionary(DictionaryVO vo);

    List<Dictionary> toDictionaryList(List<DictionaryVO> vo);

    DictionaryVO toDictionaryVO(Dictionary dictionary);

    List<DictionaryVO> toDictionaryVOList(List<Dictionary> vo);
}