package com.project.SocialApp.service;

import com.project.SocialApp.dao.LikeRepository;
import com.project.SocialApp.dto.request.LikeCreateRequest;
import com.project.SocialApp.dto.response.LikeResponseDto;
import com.project.SocialApp.entity.Like;
import com.project.SocialApp.entity.Post;
import com.project.SocialApp.entity.User;
import com.project.SocialApp.exception.BusinessException;
import com.project.SocialApp.general.BaseService;
import com.project.SocialApp.general.RestResponse;
import com.project.SocialApp.mapper.LikeMapper;
import com.project.SocialApp.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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


    public List<LikeResponseDto> getAllLikes(Optional<Long> postId, Optional<Long> userId) {

        List<Like> likes;
        if (postId.isPresent() && userId.isPresent()) {
            likes = repository.findAllByPostIdAndUserId(postId.get(), userId.get());
        } else if (postId.isPresent()) {
            likes = repository.findAllByPostId(postId.get());
        } else if (userId.isPresent()) {
            likes = repository.findAllByUserId(userId.get());
        } else {
            likes = repository.findAll();
        }
        return  LikeMapper.INSTANCE.likesToLikeResponseDtos(likes);
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

    public List<LikeResponseDto> getAllLikesByUserId(Long userId) {
        List<Like> likes = repository.findAllByUserId(userId);
        return LikeMapper.INSTANCE.likesToLikeResponseDtos(likes);
    }

    @Transactional
    public void deleteOneLikeById(Long likeId) {
        repository.deleteById(likeId);
    }

//    public void deleteOneLike(Long postId, Long userId) {
//        Like like = repository.findByPostIdAndUserId(postId, userId);
//        if (like != null) {
//            repository.deleteById(like.getId());
//        } else {
//            throw new BusinessException("Like not found for the user and post");
//        }
//    }
}
