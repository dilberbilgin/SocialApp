package com.project.SocialApp.mapper;

import com.project.SocialApp.dto.request.CommentCreateRequest;
import com.project.SocialApp.dto.request.CommentUpdateRequest;
import com.project.SocialApp.dto.response.CommentResponseDto;
import com.project.SocialApp.dto.response.PostResponseDto;
import com.project.SocialApp.entity.Comment;
import com.project.SocialApp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);

    Comment commentCreateRequestToComment(CommentCreateRequest request);

    CommentResponseDto commentToCommentResponsDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    Comment updateCommentFromRequest(CommentUpdateRequest request, @MappingTarget Comment comment);
}
