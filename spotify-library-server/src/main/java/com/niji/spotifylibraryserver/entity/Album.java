package com.niji.spotifylibraryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    // Server api specific data
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String tag;
    private boolean favorite;

    // Spotify data
    private String idSpotify;
    private String title;
    private String artists;
    private Integer duration;
    private String releaseDate;
    private String imageUrl;

}
