package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.vinn.GammaLight.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name="posts")
//@MappedSuperclass
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Picture> picture;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<LikePost> likePost;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private SponsoredPost sponsoredPost;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    @PrimaryKeyJoinColumn
//    private List<SponsoredPost>  sponsoredPost;




    public Post() {}

    public Post(String postText, LocalDateTime publicationDate, User user) {
        this.postText = postText;
        this.publicationDate = publicationDate;
        this.user = user;
    }

}
