package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private LocalDateTime published;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<LikeComment> likeComment;

    public Comment() {}

    public Comment(String text, LocalDateTime published, User user, Post post) {
        this.text = text;
        this.published = published;
        this.user = user;
        this.post = post;
    }
}
