package com.project.SocialApp.mapper;

import com.project.SocialApp.dto.request.UserCreateRequest;
import com.project.SocialApp.dto.request.UserUpdateRequest;
import com.project.SocialApp.dto.response.UserResponseDto;
import com.project.SocialApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto userToUserDto(User user);

    List<UserResponseDto> usersToUserResponseDtos(List<User> users);

    User userCreateRequestToUser(UserCreateRequest request);

    @Mapping(target = "id", ignore = true)
    void updateUserFields(UserUpdateRequest request, @MappingTarget User user);


}
