package com.example.service.entity.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConvertMapper {
    UserVO toVO(User user);

    User toUser(UserVO userVO);
}
