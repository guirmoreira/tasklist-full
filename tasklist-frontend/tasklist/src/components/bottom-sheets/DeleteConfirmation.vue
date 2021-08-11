<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet" inset >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          icon
          color="red lighten-1"
          slot="activation"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon> mdi-delete </v-icon>
        </v-btn>
      </template>
      <v-sheet class="text-center pa-2" height="150px">
        <div class="my-4">Gostaria mesmo de remover <b>{{ title }}</b>?</div>
        <v-btn class="my-2" color="primary" elevation="2" raised large @click="this.delete">
          SIM
        </v-btn>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
  import tasks from "../../services/tasks";
  export default {
    props: {
      id: {
        type: Number,
        required: true,
      },
      title: {
        type: String,
        required: true,
      },
    },
    data: () => ({
      sheet: false,
    }),
    methods: {
    delete() {
      tasks.deleteTask(this.id).then(() => {
        this.$parent.$parent.$parent.list();
        this.$parent.$parent.$parent.$forceUpdate;
      });
      this.sheet = false;
    },
  },
  };
</script>