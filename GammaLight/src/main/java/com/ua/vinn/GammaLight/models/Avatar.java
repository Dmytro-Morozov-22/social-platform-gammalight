package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avatarLink")
    private String avatarLink;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created")
    private LocalDate created;

//    @JsonBackReference //використовується на дочірньому класі, щоб уникнути кругового посилання
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)  nullable = false,
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "avatar")
    @JsonIgnore
    private List<LikeAvatar> likeAvatar;

    public Avatar() {}

    public Avatar(String avatarLink, boolean active, LocalDate created, User user) {
        this.avatarLink = avatarLink;
        this.active = active;
        this.created = created;
        this.user = user;
    }

}
