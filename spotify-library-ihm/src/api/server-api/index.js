import Vue from 'vue'
import store from './../../store'

function Exception(status, message) {
  this.status = status;
  this.message = message;
}

function getAlbums() {

  const requestOptions = {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  };
  fetch(ServerApi.albumsUri, requestOptions)
    .then(response => {
      if (response.status === 200) {
        // Delete OK
        return response.json();
      } else {
        // Delete KO
        throw new Exception("KO", "Albums not loaded");
      }
    })
    .then(json => {
      store.commit('setServerAlbums',json)
      store.commit('setFeedback',
        {
          status : "OK",
          message : "Albums loaded",
        })
      }
    )
    .catch(e => store.commit('setFeedback', e));
}

function createAlbum(item) {

  item.id = 0;
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(
      item
    )
  };
  fetch(ServerApi.albumsUri, requestOptions)
    .then(response => {
      if (response.status === 201) {
        // Create OK
        return response.json();
      } else if (response.status === 200) {
        // Create WARNING
        throw new Exception("WARNING", "Album ever added: " + item.title);
      } else {
        // Create KO
        throw new Exception("KO", "Album not added: " + item.title);
      }
    })
    .then(json => {
      store.commit('addServerAlbums',json)
      store.commit('setFeedback',
        {
          status : "OK",
          message : "Album added: " + item.title,
        })
      }
    )
    .catch(e => store.commit('setFeedback', e));
}

function updateAlbum(item) {

  const requestOptions = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(
      item
    )
  };
  fetch(ServerApi.albumsUri+"/"+item.id, requestOptions)
    .then(response => {
      if (response.status === 200) {
        // Delete OK
        return () => response.json();
      } else if (response.status === 404) {
        // Delete WARNING
        throw new Exception("WARNING", "Album not found: " + item.title);
      } else {
        // Delete KO
        throw new Exception("KO", "Album not updated: " + item.title);
      }
    })
    .then(() => {
      store.commit('setFeedback',
        {
          status : "OK",
          message : "Album updated: " + item.title,
        })
      }
    )
    .catch(e => store.commit('setFeedback', e));
}

function deleteAlbum(item) {

  const requestOptions = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json"
    }
  };
  fetch(ServerApi.albumsUri+"/"+item.id, requestOptions)
    .then(response => {
      if (response.status === 200) {
        // Delete OK
        return () => response.json();
      } else if (response.status === 404) {
        // Delete WARNING
        throw new Exception("WARNING", "Album ever removed: " + item.title);
      } else {
        // Delete KO
        throw new Exception("KO", "Album not removed: " + item.title);
      }
    })
    .then(() => {
      store.commit('removeServerAlbums',item)
      store.commit('setFeedback',
        {
          status : "OK",
          message : "Album removed: " + item.title,
        })
      }
    )
    .catch(e => {
      if(e.status === "WARNING"){
        store.commit('removeServerAlbums',item)
      }
      store.commit('setFeedback', e)
    });
}

var ServerApi = new Vue({
  data : {
    albumsUri : "http://localhost:8080/albums",
  },
  methods: {
    getAlbums() {
      getAlbums();
    },
    addAlbum(item) {
      createAlbum(item);
    },
    removeAlbum(item) {
      deleteAlbum(item);
    },
    setFavorite(item, value) {
      item.favorite = value;
      updateAlbum(item);
    },
    setTag(tag, item) {
      item.tag = tag;
      updateAlbum(item);
    }
  }
});

export default ServerApi
