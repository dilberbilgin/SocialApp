package com.project.SocialApp.controller;
import com.project.SocialApp.dto.request.UserCreateRequest;
import com.project.SocialApp.dto.request.UserUpdateRequest;
import com.project.SocialApp.dto.response.UserResponseDto;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(allUsers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponseDto>> getuserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserResponseDto>> createUser(@RequestBody UserCreateRequest request) {
        UserResponseDto userResponseDto = userService.createUser(request);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        UserResponseDto userResponseDto = userService.updateUser(id, request);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }

    @DeleteMapping("/{id}")
    public void deleteOneUser(@PathVariable Long id) {
        userService.deleteOneUserById(id);
    }
//    public ResponseEntity<RestResponse<Void>> deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//        return ResponseEntity.ok(RestResponse.empty(null));
//    }






}
