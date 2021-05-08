<template>
  <v-container>

    <br/>

    <v-card elevation="2">

      <v-row class="text-center">
        <v-col>
          <v-icon small>fa-user</v-icon> <input class="form-control" v-model="artistName" placeholder="artist"><v-icon small v-on:click="searchByArtistName()">fa-search</v-icon>
        </v-col>
      </v-row>
      <v-row class="text-center">
        <v-col>
          <v-icon small>fa-compact-disc</v-icon> <input class="form-control" v-model="albumName" placeholder="album"><v-icon small v-on:click="searchByAlbumName()">fa-search</v-icon>
        </v-col>
      </v-row>
    
    </v-card>
    
  </v-container>
</template>

<script>

import SpotifyApi from './../api/spotify-api'

  export default {
    name: 'AlbumFilter',

    data: () => ({
        artistName: "",
        albumName: "",
    }),
    
    methods: {
      searchByArtistName() {
        if(this.artistName!=""){
          SpotifyApi.getAlbumsByArtistName(this.artistName);
        }
      },
      searchByAlbumName() {
        if(this.albumName!=""){
          SpotifyApi.getAlbumsByAlbumName(this.albumName);
        }
      }
    }
  }
</script>