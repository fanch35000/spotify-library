<template>
  <v-container>

    <br/>

    <v-card elevation="2">
      <v-row>
        <v-col>
          <v-form ref="formArtist" v-model="validArtist" lazy-validation>
            <v-text-field
              prepend-icon="fa-user"
              v-model="artistName" :counter="20" :rules="artistRules" label="Artist" required
            >
              <template slot="append">
                <v-icon small style="padding:3px" @click="resetArtist()">fa-times-circle</v-icon>
                <v-icon small style="padding:3px" @click="searchByArtistName()">fa-search</v-icon>
              </template>
            </v-text-field>
          </v-form>
        </v-col>
        <v-col>
          <v-form ref="formAlbum" v-model="validAlbum" lazy-validation>
            <v-text-field 
              prepend-icon="fa-compact-disc"
              v-model="albumName" :counter="20" :rules="albumRules" label="Album" required
            >
              <template slot="append">
                <v-icon small style="padding:3px" @click="resetAlbum()">fa-times-circle</v-icon>
                <v-icon small style="padding:3px" @click="searchByAlbumName()">fa-search</v-icon>
              </template>
            </v-text-field>
          </v-form>
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
      validArtist: true,
      artistName: "",
      artistRules: [
        v => !!v || 'Artist is required',
        v => (v && v.length <= 20) || 'Artist must be less than 20 characters',
      ],
      validAlbum: true,
      albumName: "",
      albumRules: [
        v => !!v || 'Album is required',
        v => (v && v.length <= 20) || 'Album must be less than 20 characters',
      ],
    }),
    
    methods: {
      searchByArtistName() {
        if(this.$refs.formArtist.validate()){
          SpotifyApi.getAlbumsByArtistName(this.artistName);
        }
      },
      resetArtist() {
        this.artistName = "";
      },
      searchByAlbumName() {
        if(this.$refs.formAlbum.validate()){
          SpotifyApi.getAlbumsByAlbumName(this.albumName);
        }
      },
      resetAlbum() {
        this.albumName = "";
      },
    }
  }
</script>