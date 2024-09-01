package com.project.SocialApp.dto.response;

import com.project.SocialApp.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {
    Long id;
    Long userId;
    Long postId;

}
