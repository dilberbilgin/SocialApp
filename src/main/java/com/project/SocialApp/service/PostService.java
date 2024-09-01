package com.project.SocialApp.service;

import com.project.SocialApp.dao.PostRepository;
import com.project.SocialApp.dto.request.PostCreateRequest;
import com.project.SocialApp.dto.request.PostUpdateRequest;
import com.project.SocialApp.dto.response.PostResponseDto;
import com.project.SocialApp.entity.Post;
import com.project.SocialApp.entity.User;
import com.project.SocialApp.general.BaseService;
import com.project.SocialApp.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class PostService extends BaseService<Post, PostRepository> {

    private final UserService userService;

    protected PostService(PostRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = repository.findAll();
        return  PostMapper.INSTANCE.postsToPostResponseDtos(posts);
    }


    public PostResponseDto createPost(PostCreateRequest request) {
        User user = userService.findByIdWithControl(request.getUserId());
        Post post = PostMapper.INSTANCE.postCreateRequestToPost(request);
        post.setUser(user); //kullaniciyi posta ata
        post = save(post); // veri tabanina kaydet
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findByIdWithControl(id);
        return PostMapper.INSTANCE.postToPostResponseDto(post);

    }

    public PostResponseDto updatePost(Long id, PostUpdateRequest request) {
        Post post = findByIdWithControl(id);
        PostMapper.INSTANCE.updatePostFromRequest(request, post);
        post = save(post);
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }

    public void deleteOnePostById(Long id) {
        repository.deleteById(id);
    }
}
