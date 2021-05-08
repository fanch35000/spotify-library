<template>
  <v-footer white padless>
    
    <v-card class="flex" style="background-color: rgb(230, 230, 230)">

      <v-card-text class="black--text text-rigth" >
    <v-row>
      <v-col cols="9" align="left">
          <div v-if="feedback.status=='OK'" style="color:green;">{{feedback.status}} - {{feedback.message}}</div>
          <div v-if="feedback.status=='WARNING'" style="color:orange;">{{feedback.status}} - {{feedback.message}}</div>
          <div v-if="feedback.status=='KO'" style="color:red;">{{feedback.status}} - {{feedback.message}}</div>
      </v-col>
      <v-col cols="3" align="right">
          {{ new Date().getFullYear() }} — <strong>Spotify library</strong>
      </v-col>
    </v-row>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>

  export default {
    name: 'Feedback',
    computed : {
      feedback(){
        return this.$store.state.feedback;
      }
    },
  }
</script>