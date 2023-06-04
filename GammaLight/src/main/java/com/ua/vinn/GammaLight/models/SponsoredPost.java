package com.ua.vinn.GammaLight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="sponsored_post")
public class SponsoredPost  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sponsor_id")
    private long sponsorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "posts_id")
    @JsonIgnore
    private Post post;

    public SponsoredPost() {}

    public SponsoredPost(Post post, long sponsorId) {
        this.sponsorId = sponsorId;
        this.post = post;
    }

}
