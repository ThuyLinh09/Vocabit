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
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", source = "newPassword")
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

//    @Mapping(source = "name", target = "username")
//    @Mapping(target = "name", ignore = true)
    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponse(List<User> users);

}
