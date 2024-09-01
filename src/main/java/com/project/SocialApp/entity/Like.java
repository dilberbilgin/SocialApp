package com.project.SocialApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.SocialApp.general.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LIKES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Like extends BaseEntity {

    @SequenceGenerator(name = "Like", sequenceName = "LIKE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Like")
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    User user;


}
