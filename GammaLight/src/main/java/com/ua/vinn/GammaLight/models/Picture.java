package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_link")
    private String pictureLink;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @JsonIgnore
    private Post post;

    public Picture() {}

    public Picture(String pictureLink, Post post) {
        this.pictureLink = pictureLink;
        this.post = post;
    }

}
