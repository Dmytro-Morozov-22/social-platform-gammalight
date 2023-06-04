package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.vinn.GammaLight.models.securityElements.Role;
import com.ua.vinn.GammaLight.models.securityElements.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

//@OneToMany(mappedBy = "avatar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

//user -> avatar
//@JsonManagedReference//використовується на батьківському класі, щоб уникнути кругового посилання
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Avatar> avatar;

//user -> post
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> post;

//user -> comment
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comment;

//user -> likeAvatar
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<LikeAvatar> likeAvatar;

//user -> likeComment
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<LikeComment> likeComment;

//user -> likePost
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<LikePost> likePost;

//user -> notification
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notification;

//user -> follower
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Follower> followers;

//user -> friend
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Friend> friends;

    public User() {}

    public User(String first_name, String email, String password, Role role, Status status) {
        this.firstName = first_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

}
