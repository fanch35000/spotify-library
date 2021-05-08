package com.niji.spotifylibraryserver.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    // Server api specific data
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public String tag;
    public boolean favorite;

    // Spotify data
    public String idSpotify;
    public String title;
    public String artists;
    public Integer duration;
    public String releaseDate;
    public String imageUrl;

}
