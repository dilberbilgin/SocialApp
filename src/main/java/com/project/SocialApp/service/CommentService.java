package com.project.SocialApp.service;

import com.project.SocialApp.dao.CommentRepository;
import com.project.SocialApp.dto.request.CommentCreateRequest;
import com.project.SocialApp.dto.request.CommentUpdateRequest;
import com.project.SocialApp.dto.response.CommentResponseDto;
import com.project.SocialApp.entity.Comment;
import com.project.SocialApp.entity.Post;
import com.project.SocialApp.entity.User;
import com.project.SocialApp.general.BaseService;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Service
public class CommentService extends BaseService<Comment, CommentRepository> {

    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository repository, UserService userService, PostService postService) {
        super(repository);
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = repository.findAll();
        return CommentMapper.INSTANCE.commentsToCommentResponseDtos(comments);
    }

    public CommentResponseDto createComment(CommentCreateRequest request) {
        User user = userService.findByIdWithControl(request.getUserId());
        Post post = postService.findByIdWithControl(request.getPostId());
        Comment comment = CommentMapper.INSTANCE.commentCreateRequestToComment(request);
        comment.setUser(user);
        comment.setPost(post);
        comment = save(comment);
        return CommentMapper.INSTANCE.commentToCommentResponsDto(comment);
    }

    public CommentResponseDto getCommentById(Long id) {
        Comment comment = findByIdWithControl(id);
        return CommentMapper.INSTANCE.commentToCommentResponsDto(comment);
    }

    public CommentResponseDto updateComment(Long id, CommentUpdateRequest request) {
        Comment comment = findByIdWithControl(id);
        CommentMapper.INSTANCE.updateCommentFromRequest(request, comment);
        comment = save(comment);
        return CommentMapper.INSTANCE.commentToCommentResponsDto(comment);
    }

    public void deleteOneCommentById(Long id) {
        repository.deleteById(id);
    }

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = repository.findByPostId(postId);
        return CommentMapper.INSTANCE.commentsToCommentResponseDtos(comments);
    }
}
