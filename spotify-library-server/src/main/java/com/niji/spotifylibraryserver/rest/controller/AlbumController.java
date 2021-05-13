package com.niji.spotifylibraryserver.rest.controller;

import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.mapper.AlbumMapper;
import com.niji.spotifylibraryserver.rest.bean.AlbumDto;
import com.niji.spotifylibraryserver.rest.exception.AlbumNotFoundException;
import com.niji.spotifylibraryserver.service.AlbumService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(value = "album", tags = { "Album management" })
@RequestMapping(value = AbstractController.REST_PREFIX + "/albums", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AlbumController extends AbstractController {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private AlbumService albumService;

    @CrossOrigin
    @ApiOperation(value = "List all albums")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Albums listed")})
    @GetMapping
    public Collection<AlbumDto> getAlbums() {
        return albumService.getAlbums().stream().map(album->albumMapper.sourceToDestination(album)).collect(Collectors.toList());
    }

    @CrossOrigin
    @ApiOperation(value = "Create an album if there is no album with the same idSpotify already created")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album ever created"),
            @ApiResponse(code = 201, message = "Album created"),
            @ApiResponse(code = 404, message = "Album not found")})
    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(
            @ApiParam(value = "Album that needs to be updated", required = true) @RequestBody AlbumDto albumDto
    ) {
        Optional<Album> albumByIdSpotify = albumService.getAlbumByIdSpotify(albumDto.getIdSpotify());
        if(!albumByIdSpotify.isPresent()) {
            // Conversion Rest to Entity
            Album album = albumMapper.destinationToSource(albumDto);
            album.setId(null);
            return new ResponseEntity(albumMapper.sourceToDestination(albumService.saveAlbum(album)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(albumByIdSpotify.get(), HttpStatus.OK);
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Get an album if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album found"),
            @ApiResponse(code = 404, message = "Album not found")})
    @GetMapping("/{id:.+}")
    public AlbumDto getAlbum(
            @ApiParam(value = "ID of album", required = true) @PathVariable long id
    ) {
        Optional<Album> album = albumService.getAlbum(id);
        if (album.isPresent()) {
            return albumMapper.sourceToDestination(album.get());
        } else {
            throw new AlbumNotFoundException();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Update an album if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album updated"),
            @ApiResponse(code = 404, message = "Album not found")})
    @PutMapping("/{id:.+}")
    public AlbumDto updateAlbum(
            @ApiParam(value = "ID of album that needs to be updated", required = true) @PathVariable long id,
            @ApiParam(value = "Updated data of the album", required = true) @RequestBody AlbumDto albumDto
    ) {
        Optional<Album> albumTest = albumService.getAlbum(id);
        if (albumTest.isPresent()) {
            // Conversion Rest to Entity
            Album album = albumMapper.destinationToSource(albumDto);
            album.setId(id);
            return albumMapper.sourceToDestination(albumService.saveAlbum(album));
        } else {
            throw new AlbumNotFoundException();
        }
    }

    @CrossOrigin
    @ApiOperation(value = "Delete an album based on its database identifier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album deleted"),
            @ApiResponse(code = 404, message = "Album not found")})
    @DeleteMapping("/{id:.+}")
    public void deleteAlbum(
            @ApiParam(value = "ID of album that needs to be deleted", required = true) @PathVariable long id
    ) {
        try {
            albumService.deleteAlbum(id);
        } catch (Exception e) {
            throw new AlbumNotFoundException();
        }
    }

}