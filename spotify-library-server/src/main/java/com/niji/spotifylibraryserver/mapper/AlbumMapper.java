package com.niji.spotifylibraryserver.mapper;

import com.niji.spotifylibraryserver.entity.Album;
import com.niji.spotifylibraryserver.rest.bean.AlbumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="idSpotify", source="entity.idSpotify"),
            @Mapping(target="tag", source="entity.tag"),
            @Mapping(target="favorite", source="entity.favorite")
    })
    AlbumDto sourceToDestination(Album entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="idSpotify", source="dto.idSpotify"),
            @Mapping(target="tag", source="dto.tag"),
            @Mapping(target="favorite", source="dto.favorite")
    })
    Album destinationToSource(AlbumDto dto);
}