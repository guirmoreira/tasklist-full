<template @list-update="list">
  <div class="dashboard">
    <v-row no-gutters>
      <v-col cols="3">
        <v-select
          :items="itemsOrder"
          label="Ordenar por"
          outlined
          item-value="value"
          item-text="name"
          v-model="selectedOrder"
          :options="update"
          return-object
        ></v-select>
      </v-col>
      <v-spacer />
      <v-col cols="2" align="right">
        <PopupNewTask />
      </v-col>
    </v-row>

    <v-card flat color="blue-grey lighten-5" class="rounded-lg mb-1">
      <v-row no-gutters>
        <v-col cols="7">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-title> Título </v-card-title>
            <v-card-subtitle> Descrição </v-card-subtitle>
          </v-card>
        </v-col>
        <v-col cols="2" class="ma-auto" align="center">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Status </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="2" class="ma-auto" align="center">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Data de Entrega </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="1" class="ma-auto" align="center">
          <v-card flat color="blue-grey lighten-5" class="rounded-pill">
            <v-card-text> Ações </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-card>

    <v-card flat v-for="task in tasks" :key="task.title">
      <v-row no-gutters class="">
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
        <v-col cols="2" class="ma-auto" align="center">
          <v-chip
            class="ma-2 white--text"
            :color="getStatusChipColor(task.status)"
          >
            {{ getStatusChipText(task.status) }}
          </v-chip>
        </v-col>
        <v-col cols="2" class="ma-auto" align="center">
          <v-card flat>
            <v-card-text class="font-weight-medium">
              <div>{{ formatDate(task.dateConclusion) }}</div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="1" class="ma-auto" align="center">
          <v-card flat>
            <v-card-actions>
              <PopupEditTask :parentTask="task" />
              <DeleteConfirmation :id="task.id" :title="task.title" />
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
            :items="itemsResults"
            v-model="selected"
            item-value="value"
            item-text="name"
            :options="update"
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
import PopupNewTask from "../components/popups/PopupNewTask.vue";
import PopupEditTask from "../components/popups/PopupEditTask.vue";
import DeleteConfirmation from "../components/bottom-sheets/DeleteConfirmation.vue";
import Tasks from "../services/tasks.js";
export default {
  components: { DeleteConfirmation, PopupNewTask, PopupEditTask },

  data() {
    return {
      tasks: [],
      page: 1,
      chipStatus: true,
      chipStatusColor: "grey",
      chipStatusText: "",
      itemsOrder: [
        { name: "Último adicionado", value: "id-DESC" },
        { name: "Primeiro adicionado", value: "id-ASC" },
        { name: "Data de Entrega crescente", value: "dateConclusion-ASC" },
        { name: "Data de Entrega decrescente", value: "dateConclusion-DESC" },
        { name: "Título crescente", value: "title-ASC" },
        { name: "Título descrescente", value: "title-DESC" },
        { name: "Status crescente", value: "status-ASC" },
        { name: "Status decrescente", value: "status-DESC" },
      ],
      itemsResults: [
        { name: "5 resultados/página", value: 5 },
        { name: "10 resultados/página", value: 10 },
        { name: "30 resultados/página", value: 30 },
        { name: "100 resultados/página", value: 100 },
      ],
      selected: { name: "5 resultados", value: 5 },
      selectedOrder: { name: "Último adicionado", value: "id-DESC" },
      totalPages: 10,
    };
  },

  computed: {
    update() {
      this.list();
      return [];
    },
  },

  mounted() {
    this.list();
  },

  events: {
    updateList() {
      this.list();
      this.$forceUpdate;
    },
  },

  methods: {
    list() {
      const [sort, dir] = this.selectedOrder.value.split("-");
      Tasks.listTasks(this.page - 1, this.selected.value, sort, dir).then(
        (response) => {
          this.tasks = response.data.content;
          this.totalPages = response.data.totalPages;
          this.$forceUpdate;
        }
      );
    },
    getStatusChipColor: function (status) {
      if (status == "ABERTA") {
        return "yellow darken-4";
      } else if (status == "EM_ANDAMENTO") {
        return "primary";
      } else if (status == "CONCLUÍDA") {
        return "green darken-2";
      } else {
        return "grey";
      }
    },
    getStatusChipText: function (status) {
      if (status == "ABERTA") {
        return "ABERTA";
      } else if (status == "EM_ANDAMENTO") {
        return "EM ANDAMENTO";
      } else if (status == "CONCLUÍDA") {
        return "CONCLUÍDA";
      } else {
        return "DESCONHECIDO";
      }
    },
    formatDate(date) {
      if (!date) return null;
      const [dayOfMonth] = date.split("T");
      const [year, month, day] = dayOfMonth.split("-");
      return `${day}/${month}/${year}`;
    },
  },
};
</script>

<style>
</style>