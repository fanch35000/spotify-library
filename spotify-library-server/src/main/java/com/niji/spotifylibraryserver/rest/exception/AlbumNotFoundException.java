package com.niji.spotifylibraryserver.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Album")
public class AlbumNotFoundException extends RuntimeException {}