# spotify-library-ihm

# Table of contents
1. [Installation](#installation)
2. [Configuration](#configuration)
3. [Démarrage](#démarrage)
4. [URLs de l'application](#urls)
5. [Frameworks utilisés](#frameworks-utilisés)

## Installation

```bash
$ git clone https://github.com/fanch35000/spotify-library.git
$ cd spotify-library-ihm
$ npm install
```

## Configuration

### Dans le fichier `spotify-library-ihm/src/api/server-api`  
Modifier les propriétés suivantes au besoin:  
```
{
  data : {
    albumsUri : "http://localhost:8080/albums",
  }
}
```

### Dans le fichier `spotify-library-ihm/src/api/spotify-api`  
Modifier les propriétés suivantes au besoin:  
```
{
  data : {
    maxAlbum : 20,
    clientId : "9356dbb5ddb046979447e2b2e84d6d04",
    secret : "c445b6a4413d401d9d9e098911d4bf12",
    authorizeUri : "https://accounts.spotify.com/authorize",
    redirectUri : "http://localhost:8888/callback",
    requestSearchUri : "https://api.spotify.com/v1/search",
    requestArtistsUri : "https://api.spotify.com/v1/artists",
    requestAlbumsUri : "https://api.spotify.com/v1/albums",
  }
}
```

## Démarrage
```bash
$ npm run serve -- --port 8888
```

## URLs
http://localhost:8888/

## Frameworks utilisés  
| Nom          | Besoin                  | Lien |
| ------------ | ----------------------- | ---- |
| Vue          |                         | https://fr.vuejs.org/v2/guide/ |
| Vuex         | Gestion des mutations   | https://vuex.vuejs.org/ |
| Vue router   | Redirection             | https://router.vuejs.org/ |
| Vuetify      | Librairy composants IHM | https://vuetifyjs.com/en/getting-started/installation/ |
| UnderscoreJS | Parcours d'objet JSON   | https://underscorejs.org/ |
| I18n         | Intertionalisation      | |
| Moment       | Manipulation de date    | https://momentjs.com/ |
