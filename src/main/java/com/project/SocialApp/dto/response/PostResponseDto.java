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
public class PostResponseDto {
    Long id;
    Long userId;
    String username;
    String title;
    String postText;
//    List<Like> postLikes;
    // TODO: 1.09.2024 postLikes potresponse icin bakilacak. 
}
