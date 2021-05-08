<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title dense>
        <v-row class="text-center">
          <v-col>
            {{ title }}
          </v-col>
        </v-row>
      </v-toolbar-title>
    </v-app-bar>

    <v-main>

      <Library/>
      <Feedback/>

    </v-main>

  </v-app>
</template>

<script>

// @ is an alias to /src
import Library from '@/views/Library.vue'
import Feedback from '@/components/Feedback.vue'

  export default {
    name: "App",
    components: {
      Library,
      Feedback,
    },
    data() {
      return {
        title: "Spotify library"
      };
    }
  }

</script>
