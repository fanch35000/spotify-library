import Vue from 'vue'
import Vuex from 'vuex'
import {_} from 'vue-underscore'

Vue.use(Vuex)

export default new Vuex.Store({

    state : {
        user : {
            spotify_token : {
                access_token : null
            }
        },
        feedback : {
            status : null,
            message : null,
        },
        spotifyAlbums : [],
        serverAlbums : []
    },
    mutations : {
        spotify_token(state, value){
            state.user.spotify_token = value
        },
        setSpotifyAlbums(state, value){
            state.spotifyAlbums = value
        },
        setServerAlbums(state, value){
            state.serverAlbums = value
        },
        addServerAlbums(state, value){
            state.serverAlbums.push(value)
        },
        removeServerAlbums(state, value){
            state.serverAlbums = _.filter(state.serverAlbums, function(album){ return album.id != value.id; });
        },
        setFeedback(state, value){
            state.feedback = value
        },
    },
    actions : {
    },
    modules: {
    }

})