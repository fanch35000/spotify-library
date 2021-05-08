package com.niji.spotifylibraryserver.service;

import com.google.common.collect.ImmutableList;
import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Collection<Album> getAlbums() {
        return ImmutableList.copyOf(albumRepository.findAll());
    }

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Optional<Album> getAlbum(Long id) {
        return albumRepository.findById(id);
    }

    public Optional<Album> getAlbumByIdSpotify(String idSpotify) {
        return albumRepository.findByIdSpotify(idSpotify);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
