import Vue from 'vue'
import VueRouter from 'vue-router'
import store from './../store'
import SpotifyApi from './../api/spotify-api'


Vue.use(VueRouter)

const routes = [
  {
    path: "/loginSpotify",
    name: 'loginSpotify',
    beforeEnter() { //to, from, next
      let url = SpotifyApi.authorizeUri+"?";
      url += "client_id="+SpotifyApi.clientId;
      url += "&" + "redirect_uri="+SpotifyApi.redirectUri;
      url += "&" + "scope="+"";
      url += "&" + "response_type="+"token";
      url += "&" + "show_dialog="+"true";
      // Redirect to spotify-auth
      window.location.href = url;
    }
  },
  {
    path: '/callback',
    name: 'callback',
    beforeEnter() {
      let hash = window.location.hash;
      // hash format : "#name1=value1&name2=value2& ..."
      // convert to : {name1:value1,name2:value2 ...}
      hash = hash.substring(1)
        .split("&")
        .reduce(function(initial, item) {
          if (item) {
            var parts = item.split("=");
            initial[parts[0]] = decodeURIComponent(parts[1]);
          }
          return initial;
        }, {});
      store.commit('spotify_token',hash);

      window.location.hash = "";
    }
    
  }

]



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
