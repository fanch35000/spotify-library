package com.niji.spotifylibraryserver.service;

import com.google.common.collect.ImmutableList;
import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.repository.AlbumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@Slf4j
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    /**
     * List all albums of the database
     * @return
     */
    public Collection<Album> getAlbums() {
        log.info("getAlbums");
        return ImmutableList.copyOf(albumRepository.findAll());
    }

    /**
     * Save an album into the database
     * @param album Album to save
     * @return Album saved with a new ID
     */
    public Album saveAlbum(Album album) {
        log.info("saveAlbum : idSpotify={}", album.getIdSpotify());
        return albumRepository.save(album);
    }

    /**
     * Retrieves an album based on its database identifier
     * @param id Album identifier
     * @return Album if it exists
     */
    public Optional<Album> getAlbum(Long id) {
        log.info("getAlbum : id={}", id);
        return albumRepository.findById(id);
    }

    /**
     * Retrieves an album based on its spotify identifier
     * @param idSpotify Spotify idendifier
     * @return Album if it exists
     */
    public Optional<Album> getAlbumByIdSpotify(String idSpotify) {
        log.info("getAlbumByIdSpotify : idSpotify={}", idSpotify);
        return albumRepository.findByIdSpotify(idSpotify);
    }

    /**
     * Delete an album based on its database identifier
     * @param id Album identifier
     */
    public void deleteAlbum(Long id) {
        log.info("deleteAlbum : id={}", id);
        albumRepository.deleteById(id);
    }
}
