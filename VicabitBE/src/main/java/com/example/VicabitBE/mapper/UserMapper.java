package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.request.UserCreationRequest;
import com.example.VicabitBE.dto.request.UserUpdateRequest;
import com.example.VicabitBE.dto.response.UserResponse;
import com.example.VicabitBE.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget  User user ,UserUpdateRequest request);

//    @Mapping(source = "name", target = "username")
//    @Mapping(target = "name", ignore = true)
    UserResponse toUserResponse(User user);


}
