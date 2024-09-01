package com.project.SocialApp.service;

import com.project.SocialApp.dao.UserRepository;
import com.project.SocialApp.dto.request.UserCreateRequest;
import com.project.SocialApp.dto.request.UserUpdateRequest;
import com.project.SocialApp.dto.response.UserResponseDto;
import com.project.SocialApp.entity.User;
import com.project.SocialApp.general.BaseService;
import com.project.SocialApp.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class UserService extends BaseService<User, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = repository.findAll();
        return UserMapper.INSTANCE.usersToUserResponseDtos(users);
    }

    public UserResponseDto createUser(UserCreateRequest request) {
        User user = UserMapper.INSTANCE.userCreateRequestToUser(request); // userCreaterequesti User'a donusturme
        user = save(user);// user'i veri tabanina kaydetme
        return UserMapper.INSTANCE.userToUserDto(user); //kaydedilen user userResponceDtoya donusturme

    }

    public UserResponseDto updateUser(Long id, UserUpdateRequest request) {
        User user = findByIdWithControl(id);
        UserMapper.INSTANCE.updateUserFields(request,user);
        user = save(user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public UserResponseDto getUserById(Long id) {
        User user = findByIdWithControl(id);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public void deleteOneUserById(Long id) {
        repository.deleteById(id);

        //    public void delete(Long id) {
//        User user = findByIdWithControl(id);
//        repository.delete(user);
//    }
    }
}
