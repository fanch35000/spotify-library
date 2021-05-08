package com.niji.spotifylibraryserver.rest.controller;

import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.mapper.AlbumMapper;
import com.niji.spotifylibraryserver.rest.bean.AlbumDto;
import com.niji.spotifylibraryserver.rest.exception.AlbumNotFoundException;
import com.niji.spotifylibraryserver.service.AlbumService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AlbumController {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private AlbumService albumService;

    @CrossOrigin(origins = "http://localhost:8888")
    @ApiOperation(value = "List all albums")
    @GetMapping("/albums")
    public Collection<AlbumDto> getAlbums() {
        return albumService.getAlbums().stream().map(album->albumMapper.sourceToDestination(album)).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8888")
    @ApiOperation(value = "Create an album if there is no album with the same idSpotify already created")
    @PostMapping("/albums")
    public ResponseEntity<AlbumDto> createAlbum(@RequestBody AlbumDto albumDto) {
        Optional<Album> albumByIdSpotify = albumService.getAlbumByIdSpotify(albumDto.idSpotify);
        if(!albumByIdSpotify.isPresent()) {
            // Conversion Rest to Entity
            Album album = albumMapper.destinationToSource(albumDto);
            album.id = null;
            return new ResponseEntity(albumMapper.sourceToDestination(albumService.saveAlbum(album)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(albumByIdSpotify.get(), HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8888")
    @ApiOperation(value = "Get an album if it exists")
    @GetMapping("/albums/{id}")
    public AlbumDto getAlbum(@PathVariable long id) {
        Optional<Album> album = albumService.getAlbum(id);
        if (album.isPresent()) {
            return albumMapper.sourceToDestination(album.get());
        } else {
            throw new AlbumNotFoundException();
        }
    }

    @CrossOrigin(origins = "http://localhost:8888")
    @ApiOperation(value = "Update an album if it exists")
    @PutMapping("/albums/{id}")
    public AlbumDto updateAlbum(@PathVariable long id, @RequestBody AlbumDto albumDto) {
        Optional<Album> albumTest = albumService.getAlbum(id);
        if (albumTest.isPresent()) {
            // Conversion Rest to Entity
            Album album = albumMapper.destinationToSource(albumDto);
            album.id = id;
            return albumMapper.sourceToDestination(albumService.saveAlbum(album));
        } else {
            throw new AlbumNotFoundException();
        }
    }

    @CrossOrigin(origins = "http://localhost:8888")
    @ApiOperation(value = "Delete an album if it exists")
    @DeleteMapping("/albums/{id}")
    public void deleteAlbum(@PathVariable long id) {
        try {
            albumService.deleteAlbum(id);
        } catch (Exception e) {
            throw new AlbumNotFoundException();
        }
    }

}