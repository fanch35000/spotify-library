<template>
  <v-container>

    <v-card elevation="2">
      <v-data-table
        :headers="headers"
        :items="albums"
      >

      <template v-slot:[`item.imageUrl`]="{ item }">
        <v-img :src="item.imageUrl" :alt="item.name" contain max-height="50" max-width="50"></v-img>
      </template>

      <template v-slot:[`item.duration`]="{ item }">
        <span>{{ item.duration | formatDuration }}</span>
      </template>

      <template v-slot:[`item.actions`]="{ item }">
        <v-icon small @click="addAlbum(item)">
          fa-plus-circle
        </v-icon>
      </template>

      </v-data-table>
    </v-card>

  </v-container>
</template>

<script>
import ServerApi from './../api/server-api'

  export default {
    name: 'AlbumList',

    data: () => ({
        headers: [
          { value: 'imageUrl', width: '100px', sortable: false },
          { value: 'releaseDate', width: '150px', text: 'Release date' },
          { value: 'artists', width: '200px', text: 'Artists' },
          { value: 'title', text: 'Title' },
          { value: 'duration', width: '100px', text: 'Duration' },
          { value: 'actions', width: '10px', sortable: false },
        ]
    }),
    
    methods: {
      addAlbum(item) {
        ServerApi.addAlbum(item);
      }
    },
    computed : {
      albums(){
        return this.$store.state.spotifyAlbums;
      }
    }
    
  }

</script>