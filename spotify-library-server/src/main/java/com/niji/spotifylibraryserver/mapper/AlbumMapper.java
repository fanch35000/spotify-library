package com.niji.spotifylibraryserver.mapper;

import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.rest.bean.AlbumDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDto sourceToDestination(Album entity);

    Album destinationToSource(AlbumDto dto);
}