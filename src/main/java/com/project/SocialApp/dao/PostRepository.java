package com.project.SocialApp.dao;

import com.project.SocialApp.entity.Post;
import com.project.SocialApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
