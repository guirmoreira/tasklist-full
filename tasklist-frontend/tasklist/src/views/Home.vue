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
      elevation="2"
      class="rounded-b-lg pl-2 pt-8"
      color="blue-grey darken-4">
      <v-spacer></v-spacer>
      <v-toolbar-title class="white--text text-h4 font-weight-medium">
        <p class="text-xs-center">Lista de Tarefas</p>
      </v-toolbar-title>

      <v-spacer></v-spacer>

    </v-toolbar>

    <v-card flat class="pa-2 mt-4 mx-1" >
      <Dashboard />
    </v-card>
  </v-card>

</template>

<script>
  import Dashboard from './Dashboard.vue'

  export default {
    components: { Dashboard },
  }
</script>
