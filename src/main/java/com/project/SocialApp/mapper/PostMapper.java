package com.project.SocialApp.mapper;

import com.project.SocialApp.dto.request.LikeCreateRequest;
import com.project.SocialApp.dto.request.PostCreateRequest;
import com.project.SocialApp.dto.request.PostUpdateRequest;
import com.project.SocialApp.dto.response.PostResponseDto;
import com.project.SocialApp.entity.Like;
import com.project.SocialApp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LikeMapper.class})
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "likes", target = "likes")
    PostResponseDto postToPostResponseDto(Post post);

    List<PostResponseDto> postsToPostResponseDtos(List<Post> posts);

    @Mapping(target = "user", ignore = true)
    Post postCreateRequestToPost(PostCreateRequest request);

    @Mapping(target = "user", ignore = true) // Kullanıcıyı güncellemeye dahil etme
    void updatePostFromRequest(PostUpdateRequest request, @MappingTarget Post post);


    @Mapping(target = "user", ignore = true)
    Post postUpdateRequestToPost(PostUpdateRequest request);


    Like likeCreateRequestToLike(LikeCreateRequest request);

}
