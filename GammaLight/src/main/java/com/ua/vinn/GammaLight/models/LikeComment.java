package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="like_comment")
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @JsonIgnore
    private Comment comment;

    public LikeComment() {}

    public LikeComment(Comment comment, User user) {
        this.user = user;
        this.comment = comment;
    }
}
