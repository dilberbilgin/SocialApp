package com.project.SocialApp.controller;

import com.project.SocialApp.dto.request.CommentCreateRequest;
import com.project.SocialApp.dto.request.CommentUpdateRequest;
import com.project.SocialApp.dto.response.CommentResponseDto;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<RestResponse<List<CommentResponseDto>>> getAllComments(@RequestParam Optional<Long> postId ) {
        List<CommentResponseDto> allComments = commentService.getAllComments(postId);
        return ResponseEntity.ok(RestResponse.of(allComments));
    }

//    @GetMapping("/posts/{postId}/comments")
//    public ResponseEntity<RestResponse<List<CommentResponseDto>>> getCommentsByPostId(@PathVariable Long postId) {
//        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);
//        return ResponseEntity.ok(RestResponse.of(comments));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CommentResponseDto>> getCommentById(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.getCommentById(id);
        return ResponseEntity.ok(RestResponse.of(commentResponseDto));
    }

    @PostMapping
    public ResponseEntity<RestResponse<CommentResponseDto>> createComment(@RequestBody CommentCreateRequest request) {
        CommentResponseDto commentResponseDto = commentService.createComment(request);
        return ResponseEntity.ok(RestResponse.of(commentResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<CommentResponseDto>> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        CommentResponseDto commentResponseDto = commentService.updateComment(id, request);
        return ResponseEntity.ok(RestResponse.of(commentResponseDto));
    }

    @DeleteMapping("/{id}")
    public void deleteOneComment(@PathVariable Long id) {
        commentService.deleteOneCommentById(id);
    }
}
