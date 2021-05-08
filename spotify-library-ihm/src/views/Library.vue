<template>
  <v-card elevation="2">
    <v-tabs>
      <v-tab href="#spotify">Spotify</v-tab>
      <v-tab href="#bdd">BDD</v-tab>

      <v-tab-item value="spotify">
        <div v-if="logged" style="height:770px">
          <AlbumFilter/>
          <AlbumList/>
        </div>
        <div v-if="!logged" style="height:770px">
            <v-container class="text-center">
              <br/>
              <v-btn elevation="2" v-on:click="loginSpotify()">
                <v-img max-height="25" max-width="25" src="spotify_logo.png"/>
                <span>Connect to Spotify</span>
              </v-btn>
            </v-container>
        </div>
      </v-tab-item>
      <v-tab-item value="bdd">
        <div style="height:770px">
          <ServerAlbumList/>
        </div>
      </v-tab-item>

    </v-tabs>

  </v-card>

</template>

<script>
// @ is an alias to /src
import AlbumFilter from '@/components/AlbumFilter.vue'
import AlbumList from '@/components/AlbumList.vue'
import ServerAlbumList from '@/components/ServerAlbumList.vue'

  export default {
    name: "App",
    components: {
      AlbumFilter,
      AlbumList,
      ServerAlbumList,
    },
    data() {
      return {
      tab: null,
        title: "Spotify library"
      };
    },
    methods: {
      loginSpotify() {
        this.$router.push('loginSpotify')
      }
    },
    computed : {
      logged(){
        return this.$store.state.user.spotify_token.access_token!=null;
      }
    }

  };

</script>