import Vue from 'vue'
import store from '../../store'
import {_} from 'vue-underscore'

const headers = {
  'Accept': 'application/json',
  'Content-Type': 'application/json',
  Authorization: null
};

function convert(albumItem) {

  // find artists
  let listArtistName = _.map(albumItem.artists, function(artist){ return artist.name; });
  let listArtistNameString = _.reduce(listArtistName, function(memo, artist){ return memo + ', ' + artist; });

  // choose an image
  let listImageUrl = _.map(albumItem.images, function(image){ return image.url; });
  let imageUrl = _.first(listImageUrl);

  return {
    idSpotify : albumItem.id,
    imageUrl : imageUrl,
    title: albumItem.name,
    artists: listArtistNameString,
    duration: 0,
    releaseDate: albumItem.release_date
  }
}

function evaluateDuration(trackItems){

  // evaluate duration
  let listDurationByTrack = _.map(trackItems, function(track){ return track.duration_ms; });
  let duration_ms = _.reduce(listDurationByTrack, function(memo, num){ return memo + num; }, 0);

  return duration_ms;
}

function getArtistsByName(artistName, token, cb) {
  headers.Authorization = `Bearer ${token}`;
  fetch(
    SpotifyApi.requestSearchUri+'?q='+artistName+'&type=artist&limit=20',
    {
      headers,
    }
    ).then(response => 
      response.json().then(data => ({
        data: data,
        status: response.status
      })
    ).then(res => cb(res.data.artists.items))
  )
}

function getAlbumsByArtistId(artistId, token, cb) {
  headers.Authorization = `Bearer ${token}`;
  fetch(
    SpotifyApi.requestArtistsUri+'/'+artistId+'/albums?market=FR&limit=20',
    {
      headers,
    }
    ).then(response => 
      response.json().then(data => ({
        data: data,
        status: response.status
      })
    ).then(res => cb(res.data.items))
  )
}

function getAlbumsByName(albumName, token, cb) {
  headers.Authorization = `Bearer ${token}`;
  fetch(
    SpotifyApi.requestSearchUri+'?q='+albumName+'&market=FR&type=album&limit=20',
    {
      headers,
    }
    ).then(response => 
      response.json().then(data => ({
        data: data,
        status: response.status
      })
    ).then(res => cb(res.data.albums.items))
  )
}

function getTracksByAlbumId(id, token, cb) {
  headers.Authorization = `Bearer ${token}`;
  fetch(
    SpotifyApi.requestAlbumsUri + '/'+id+'?market=FR',
    {
      headers,
    }
    ).then(response => 
      response.json().then(data => ({
        data: data,
        status: response.status
      })
    ).then(res => cb(res.data.tracks.items))
  )
}

var SpotifyApi = new Vue({
  data : {
    maxAlbum : 20,
    clientId : "9356dbb5ddb046979447e2b2e84d6d04",
    secret : "c445b6a4413d401d9d9e098911d4bf12",
    authorizeUri : "https://accounts.spotify.com/authorize",
    redirectUri : "http://localhost:8888/callback",
    requestSearchUri : 'https://api.spotify.com/v1/search',
    requestArtistsUri : 'https://api.spotify.com/v1/artists',
    requestAlbumsUri : 'https://api.spotify.com/v1/albums',
  },
  methods: {
    getAlbumsByArtistName(artistName) {
      let token = store.state.user.spotify_token.access_token;
      store.commit('setSpotifyAlbums',[]);
      let albums = [];
      getArtistsByName(artistName, token, (artistItems) => artistItems.forEach(artistItem => {
        getAlbumsByArtistId(artistItem.id, token, (albumItems) => albumItems.forEach(albumItem => {
          if(_.size(albums)<this.maxAlbum){
            let album = convert(albumItem);
            albums.push(album);
            getTracksByAlbumId(albumItem.id, token, (trackItems) => {
              album.duration = evaluateDuration(trackItems);
            })
          }
        }))
      }));
      store.commit('setSpotifyAlbums', albums);
    },
    getAlbumsByAlbumName(albumName) {
      let token = store.state.user.spotify_token.access_token;
      store.commit('setSpotifyAlbums',[]);
      let albums = [];
      getAlbumsByName(albumName, token, (albumItems) => albumItems.forEach(albumItem => {
        if(_.size(albums)<this.maxAlbum){
          let album = convert(albumItem);
          albums.push(album);
          getTracksByAlbumId(albumItem.id, token, (trackItems) => {
            album.duration = evaluateDuration(trackItems);
          })
        }
      }));
      store.commit('setSpotifyAlbums', albums);
    }
  }
});

export default SpotifyApi
