<template>
  <v-container>
    <div v-if="!selected" @click="selectTag(item,true)">
      <v-chip v-if="item.tag!=null" close close-icon="mdi-delete" filter link outlined @click:close="deleteTag(item)">
          {{item.tag}}
      </v-chip>
      <div v-if="item.tag==null">
        <v-icon small >far fa-square</v-icon>
      </div>
    </div>
    <div v-if="selected" @click="selectTag(item,false)">
      <v-icon small >far fa-check-square</v-icon>
    </div>
  </v-container>
</template>

<script>
import ServerApi from '../api/server-api'

  export default {
    name: 'DisplayTag',
    props: ['item','selected'],
    methods: {
      selectTag(item,value) {
        this.$emit('check-tag', { id: item.id, selected: value })
      },
      deleteTag(item) {
        ServerApi.setTag(null,item);
      },
    },
  }
</script>