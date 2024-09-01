package com.project.SocialApp.mapper;

import com.project.SocialApp.dto.request.LikeCreateRequest;
import com.project.SocialApp.dto.response.LikeResponseDto;
import com.project.SocialApp.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    List<LikeResponseDto> likesToLikeResponseDtos(List<Like> likes);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "post.id", target = "postId")
    LikeResponseDto likeToLikeResponseDto(Like like);

    Like likeCreateRequestToLike(LikeCreateRequest request);
}
