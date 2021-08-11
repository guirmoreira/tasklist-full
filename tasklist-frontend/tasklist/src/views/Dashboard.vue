<template>
  <div class="dashboard">
    <v-card flat color="blue-grey lighten-5" class="rounded-lg mb-2">
      <v-row no-gutters>
        <v-col cols="7">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-title> Título </v-card-title>
            <v-card-subtitle> Descrição </v-card-subtitle>
          </v-card>
        </v-col>
        <v-col cols="2" class="ma-auto">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Status </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="2" class="ma-auto">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Data de Entrega </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="1" class="ma-auto">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Ações </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-card>

    <v-card flat v-for="task in tasks" :key="task.title">
      <v-row no-gutters class="pr-2">
        <v-col cols="7">
          <v-card flat>
            <v-card-title>
              <div>{{ task.title }}</div>
            </v-card-title>
            <v-card-subtitle>
              <div>{{ task.description }}</div>
            </v-card-subtitle>
          </v-card>
        </v-col>
        <v-col cols="2" class="ma-auto">
          <v-chip
            v-if="chip3"
            class="ma-2"
            close
            color="green"
            outlined
            @click:close="chip3 = false"
          >
            Success
          </v-chip>
        </v-col>
        <v-col cols="2" class="ma-auto">
          <v-card flat>
            <v-card-text>
              <div>{{ task.dateConclusion }}</div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="1" class="ma-auto pr-2">
          <v-card flat class="mr-4">
            <v-card-actions>
              <v-btn icon color="indigo lighten-1">
                <v-icon> mdi-pencil </v-icon>
              </v-btn>
              <PopupDeleteTask />
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
      <v-divider class=""></v-divider>
    </v-card>

    <div class="text-center mt-6">
      <v-pagination v-model="page" :length="totalPages"></v-pagination>
    </div>

    <v-row no-gutters style="height: 50px">
      <v-col cols="5" class="ma-auto" />
      <v-col cols="2" class="ma-auto">
        <div class="text-center mt-3 text-caption">
          <v-select
            class="text-caption"
            :items="items"
            v-model="selected"
            item-value="value"
            item-text="name"
            :options="startAmtPage"
            return-object
            solo
          >
          </v-select>
        </div>
      </v-col>
      <v-col cols="5" class="ma-auto" />
    </v-row>
  </div>
</template>

<script>
import PopupDeleteTask from "../components/popups/PopupDeleteTask.vue";
import Tasks from "../services/tasks.js";
export default {
  components: { PopupDeleteTask },

  data() {
    return {
      tasks: [],
      page: 1,
      chip1: true,
      chip2: true,
      chip3: true,
      chip4: true,
      items: [
        { name: "5 resultados/página", value: 5},
        { name: "10 resultados/página", value: 10},
        { name: "30 resultados/página", value: 30},
        { name: "100 resultados/página", value: 100},
      ],
      selected: { name: "5 resultados", value: 5},
      totalPages: 10,
    };
  },

  computed: {
    startAmtPage() {
        this.list();
      return []
    },
  },

  mounted() {
    this.list()
  },

  methods: {
      list(){
          Tasks.listTasks(this.page-1, this.selected.value).then((response) => {
            this.tasks = response.data.content;
            this.totalPages = response.data.totalPages;
            });
      },

  },
};
</script>

<style>
.project.complete {
  border-left: 4px solid #3cd1c2;
}
.project.ongoing {
  border-left: 4px solid #ffaa2c;
}
.project.overdue {
  border-left: 4px solid #f83e70;
}
.v-chip.complete {
  background: #3cd1c2;
}
.v-chip.ongoing {
  background: #ffaa2c;
}
.v-chip.overdue {
  background: #f83e70;
}
</style>