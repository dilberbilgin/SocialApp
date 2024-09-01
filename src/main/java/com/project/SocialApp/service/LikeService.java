package com.project.SocialApp.service;

import com.project.SocialApp.dao.LikeRepository;
import com.project.SocialApp.dto.request.LikeCreateRequest;
import com.project.SocialApp.dto.response.LikeResponseDto;
import com.project.SocialApp.entity.Like;
import com.project.SocialApp.entity.Post;
import com.project.SocialApp.entity.User;
import com.project.SocialApp.exception.BusinessException;
import com.project.SocialApp.general.BaseService;
import com.project.SocialApp.mapper.LikeMapper;
import com.project.SocialApp.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LikeService extends BaseService<Like, LikeRepository> {
    private final PostService postService;
    private final UserService userService;

    protected LikeService(LikeRepository repository, PostService postService, UserService userService) {
        super(repository);
        this.postService = postService;
        this.userService = userService;
    }


    public List<LikeResponseDto> getAllLikes() {
        List<Like> likes = repository.findAll();
        return LikeMapper.INSTANCE.likesToLikeResponseDtos(likes);
    }

    public LikeResponseDto getLikeById(Long id) {
        Like like = findByIdWithControl(id);
        return LikeMapper.INSTANCE.likeToLikeResponseDto(like);
    }

    public LikeResponseDto createOneLike(LikeCreateRequest request) {

        boolean exists = repository.existsByUserIdAndPostId(request.getUserId(), request.getPostId());
        if (exists) {
            throw new BusinessException("You have already liked this post.");
        }

        User user = userService.findByIdWithControl(request.getUserId());
        Post post = postService.findByIdWithControl(request.getPostId());

        Like like = LikeMapper.INSTANCE.likeCreateRequestToLike(request);
        like.setUser(user);
        like.setPost(post);

        like = save(like);
        return LikeMapper.INSTANCE.likeToLikeResponseDto(like);
    }
}
