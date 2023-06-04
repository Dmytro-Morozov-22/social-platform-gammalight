package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="like_avatar")
public class LikeAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    @JsonIgnore
    private Avatar avatar;

    public LikeAvatar() {}

    public LikeAvatar(User user, Avatar avatar) {
        this.user = user;
        this.avatar = avatar;
    }
}
