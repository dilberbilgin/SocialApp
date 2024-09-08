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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<RestResponse<List<LikeResponseDto>>> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        List<LikeResponseDto> likeResponseDtos = likeService.getAllLikes(postId, userId);
        return ResponseEntity.ok(RestResponse.of(likeResponseDtos));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResponse<List<LikeResponseDto>>> getAllLikesByUserId(@PathVariable Long userId) {
        List<LikeResponseDto> likeResponseDtos = likeService.getAllLikesByUserId(userId);
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

    @DeleteMapping({"/{likeId}"})
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }

//    @DeleteMapping("/{postId}/user/{userId}")
//    public ResponseEntity<RestResponse<String>> deleteOneLike(@PathVariable Long postId, @PathVariable Long userId) {
//        likeService.deleteOneLike(postId, userId);
//        return ResponseEntity.ok(RestResponse.of("Like deleted successfully"));
//    }
}


//Belirli bir post ve kullanıcı için beğenileri getirme:
//GET /api/v1/likes?postId=1&userId=2

//Yalnızca post ID'ye göre beğenileri getirme:
//GET /api/v1/likes?postId=1

// Yalnızca kullanıcı ID'ye göre beğenileri getirme:
//GET /api/v1/likes?userId=123