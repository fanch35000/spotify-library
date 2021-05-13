package com.niji.spotifylibraryserver.rest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {

    // Server api specific data
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