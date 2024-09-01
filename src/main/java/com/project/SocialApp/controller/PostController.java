package com.project.SocialApp.controller;

import com.project.SocialApp.dto.request.PostCreateRequest;
import com.project.SocialApp.dto.request.PostUpdateRequest;
import com.project.SocialApp.dto.request.UserUpdateRequest;
import com.project.SocialApp.dto.response.PostResponseDto;
import com.project.SocialApp.dto.response.UserResponseDto;
import com.project.SocialApp.entity.Post;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<RestResponse<List<PostResponseDto>>> getAllPosts() {
        List<PostResponseDto> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(RestResponse.of(allPosts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<PostResponseDto>> getPostId(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.getPostById(id);
        return ResponseEntity.ok(RestResponse.of(postResponseDto));
    }

    @PostMapping
    public ResponseEntity<RestResponse<PostResponseDto>> createPost(@RequestBody PostCreateRequest request) {
        PostResponseDto postResponseDto = postService.createPost(request);
        return ResponseEntity.ok(RestResponse.of(postResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<PostResponseDto>> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        PostResponseDto postResponseDto = postService.updatePost(id, request);
        return ResponseEntity.ok(RestResponse.of(postResponseDto));
    }

    @DeleteMapping("/{id}")
    public void deleteOnePost(@PathVariable Long id) {
        postService.deleteOnePostById(id);
    }
}
