package com.example.mapper;

import com.example.common.entity.member.Member;
import com.example.common.entity.member.MemberVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: 会员 Mapper接口 <br>
* date: 2021-04-13 <br>
* author: ws <br>
*/
@Component
public interface MemberMapper extends BaseMapper<Member> {
    IPage<MemberVO> selectPage(Page<MemberVO> page, @Param("vo") MemberVO vo);
}
