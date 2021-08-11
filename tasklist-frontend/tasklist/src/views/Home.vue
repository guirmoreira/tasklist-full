<template>

  <v-card
    color="white lighten-4"
    flat
    tile
    class="mx-auto"
    width="1200px"
  >
    <v-toolbar
      flat
      tile
      extended
      extension-height="36"
      class="rounded-b-lg pl-2 pt-8"
      color="blue-grey darken-2">
      <v-toolbar-title class="white--text headline font-weight-light">
        Lista de Tarefas
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <PopupNewTask />
 
    </v-toolbar>

    <v-card flat class="pa-2 mt-4 mx-1" >
      <Dashboard />
    </v-card>
  </v-card>


</template>

<script>
  import PopupNewTask from '../components/popups/PopupNewTask.vue'
  import Dashboard from './Dashboard.vue'

  export default {
    components: { PopupNewTask , Dashboard },

  }
</script>
