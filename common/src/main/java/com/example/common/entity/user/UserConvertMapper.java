package com.example.common.entity.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConvertMapper {
    UserVO toVO(User user);
    List<UserVO> toVOs(List<User> user);
    User toUser(UserVO userVO);
}
