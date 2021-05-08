package com.niji.spotifylibraryserver.rest.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {

    // Server api specific data
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