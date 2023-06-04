package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "followed_user_id")
    Long followedUserId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Notification() {}

    public Notification(Long followedUserId, User user) {
        this.followedUserId = followedUserId;
        this.user = user;
    }
}
