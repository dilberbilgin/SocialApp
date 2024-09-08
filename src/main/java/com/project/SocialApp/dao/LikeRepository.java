package com.project.SocialApp.dao;

import com.project.SocialApp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long > {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    List<Like> findAllByPostId(Long aLong);

    List<Like> findAllByUserId(Long userId);

    List<Like> findAllByPostIdAndUserId(Long postId, Long userId);

//    Like findByPostIdAndUserId(Long postId, Long userId);
}
