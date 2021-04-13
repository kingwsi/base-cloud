package com.example.common.entity.member;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: 会员 转换工具 <br>
* date: 2021-04-13 <br>
* author: ws <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface MemberConvertMapper {
    Member toMember(MemberVO vo);

    List<Member> toMemberList(List<MemberVO> vo);

    MemberVO toMemberVO(Member member);

    List<MemberVO> toMemberVOList(List<Member> vo);
}