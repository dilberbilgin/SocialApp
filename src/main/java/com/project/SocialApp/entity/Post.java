package com.project.SocialApp.entity;

import com.project.SocialApp.general.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POSTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {

    @SequenceGenerator(name = "Post", sequenceName = "POST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Post")
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "POST_TEXT", nullable = false, length = 250)
    private String postText;


}
