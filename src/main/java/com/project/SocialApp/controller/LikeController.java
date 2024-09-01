package com.project.SocialApp.controller;

import com.project.SocialApp.dto.request.LikeCreateRequest;
import com.project.SocialApp.dto.response.LikeResponseDto;
import com.project.SocialApp.entity.Like;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<RestResponse<List<LikeResponseDto>>> getAllLikes() {
        List<LikeResponseDto> likeResponseDtos = likeService.getAllLikes();
        return ResponseEntity.ok(RestResponse.of(likeResponseDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<LikeResponseDto>> getLikeById(@PathVariable Long id) {
        LikeResponseDto likeResponseDto = likeService.getLikeById(id);
        return ResponseEntity.ok(RestResponse.of(likeResponseDto));
    }

    @PostMapping
    public ResponseEntity<RestResponse<LikeResponseDto>> createOneLike(@RequestBody LikeCreateRequest request) {
        LikeResponseDto likeResponseDto = likeService.createOneLike(request);
        return ResponseEntity.ok(RestResponse.of(likeResponseDto));
    }
}
