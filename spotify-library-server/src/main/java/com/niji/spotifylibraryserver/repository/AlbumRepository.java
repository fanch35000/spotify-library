package com.niji.spotifylibraryserver.repository;

import com.niji.spotifylibraryserver.entity.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    Optional<Album> findByIdSpotify(String idSpotify);

}