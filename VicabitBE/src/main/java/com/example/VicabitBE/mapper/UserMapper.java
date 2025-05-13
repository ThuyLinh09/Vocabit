package com.example.VicabitBE.mapper;

import com.example.VicabitBE.dto.request.UserCreationRequest;
import com.example.VicabitBE.dto.request.UserUpdateRequest;
import com.example.VicabitBE.dto.response.UserResponse;
import com.example.VicabitBE.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget  User user ,UserUpdateRequest request);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponse(List<User> users);

}
