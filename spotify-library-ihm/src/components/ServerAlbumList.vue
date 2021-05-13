<template>
  <v-container>

    <v-card elevation="2">
      <v-data-table :headers="headers" :items="albums">

      <template v-slot:[`item.imageUrl`]="{ item }">
        <v-img :src="item.imageUrl" :alt="item.name" contain max-height="50" max-width="50"></v-img>
      </template>

      <template v-slot:[`item.duration`]="{ item }">
        <span>{{ item.duration | formatDuration }}</span>
      </template>

      <template v-slot:[`item.favorite`]="{ item }">
        <v-icon v-if="item.favorite==true" small @click="setFavorite(item,false)" style="color:#FFBE33">fa-star</v-icon>
        <v-icon v-if="item.favorite==false" small @click="setFavorite(item,true)" style="opacity:0.5">fa-star</v-icon>
      </template>

      <template v-slot:[`item.tag`]="{ item }">
        <DisplayTag v-bind:item="item" @check-tag="checkTag" v-bind:selected="isSelected(item)"/>
      </template>

      <template v-slot:[`item.delete`]="{ item }">
        <v-icon small @click="removeAlbum(item)">fa-minus-circle</v-icon>
      </template>

      </v-data-table>
      
    </v-card>

    <v-row>
      <v-col cols="auto" class="mr-auto">
        <v-icon small @click="reload()" >fa-sync-alt</v-icon>
      </v-col>
      <v-col cols="auto">
        <div v-if="listIdsSelectedToTag.length>0" style="min-width:280px">
        
          <v-form ref="formTag" v-model="validTag" lazy-validation>
            <v-text-field v-model="tagName" :counter="10" :rules="tagRules" label="Tag" required>
              <template slot="prepend">
                <v-icon small @click="uncheckAll()">far fa-check-square</v-icon>
              </template>
              <template slot="append">
                <v-icon small style="padding:3px" @click="resetTag()">fa-times-circle</v-icon>
                <v-icon small style="padding:3px" @click="saveTagForAllIds()">far fa-save</v-icon>
              </template>
            </v-text-field>
          </v-form>
        
        </div>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import ServerApi from '../api/server-api'
import DisplayTag from '@/components/DisplayTag.vue'
import {_} from 'vue-underscore'

  export default {
    name: 'ServerAlbumList',

    components: {
      DisplayTag,
    },

    data: () => ({
        validTag: false,
        tagName: null,
        tagRules: [
          v => !!v || 'Tag is required',
          v => (v && v.length <= 10) || 'Tag must be less than 10 characters',
        ],
        
        listIdsSelectedToTag: [],

        headers: [
          { value: 'imageUrl', width: '100px', sortable: false },
          { value: 'releaseDate', width: '150px', text: 'Release date' },
          { value: 'artists', width: '200px', text: 'Artists' },
          { value: 'title', text: 'Title' },
          { value: 'tag', text: 'Tag', width: '150px', align:'center' },
          { value: 'duration', width: '100px', text: 'Duration' },
          { value: 'favorite', width: '10px' },
          { value: 'delete', width: '10px', sortable: false },
        ]
    }),
    methods: {
      removeAlbum(item) {
        ServerApi.removeAlbum(item);
      },
      setFavorite(item,value) {
        ServerApi.setFavorite(item,value);
      },
      reload() {
        ServerApi.getAlbums();
      },
      checkTag(checker){
        if(checker.selected){
          this.listIdsSelectedToTag.push(checker.id)
        } else {
          this.listIdsSelectedToTag = _.without(this.listIdsSelectedToTag, checker.id)
        }
      },
      uncheckAll(){
        this.resetTag()
        this.listIdsSelectedToTag = []
      },
      saveTagForAllIds(){
        if(this.$refs.formTag.validate()){
          let listIdsSelected = this.listIdsSelectedToTag;
          
          // Find all the albums affected by the tag
          let albumToUpdate = _.filter(this.albums, function(album){return _.contains(listIdsSelected, album.id)});
          
          // Update each album
          _.each(albumToUpdate, (album)=>{
            ServerApi.setTag(this.tagName, album)
          });

          this.listIdsSelectedToTag = []
          this.resetTag()
        }
      },
      resetTag() {
        this.tagName = "";
      },
      isSelected(item){
        return _.contains(this.listIdsSelectedToTag, item.id)
      }
    },
    mounted() {
      ServerApi.getAlbums();
    },
    computed : {
      albums(){
        return this.$store.state.serverAlbums;
      }
    }
    
  }

</script>