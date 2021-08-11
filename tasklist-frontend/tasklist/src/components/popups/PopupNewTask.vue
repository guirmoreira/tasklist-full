<template>
  <v-dialog max-width="900px" v-model="dialog">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        flat
        class="green darken-1 white--text success"
        slot="activation"
        v-bind="attrs"
        v-on="on"
      >
        Nova Tarefa
      </v-btn>
    </template>

    <v-card>
      <v-card-title class="pb-8">
        <h5 class="text--subtitle">CRIAR NOVA TAREFA</h5>
      </v-card-title>

      <v-card-text>
        <v-row>
          <v-col cols="8">
            <v-text-field
              label="Título"
              v-model="task.title"
              filled
              dense
              clearable
              required
              maxlength="64"
            ></v-text-field>
            <v-text-field
              label="Descrição"
              v-model="task.description"
              filled
              dense
              clearable
              required
              maxlength="256"
            >
              <template v-slot:label>
                <div>Descrição <small>(opcional)</small></div>
              </template>
            </v-text-field>
            <v-select
              label="Status"
              v-model="task.status"
              :items="items"
              filled
            ></v-select>
          </v-col>
          <v-col cols="4">
            <v-date-picker v-model="picker"></v-date-picker>
          </v-col>
        </v-row>
      </v-card-text>

      <v-card-actions>
        <v-btn color="red darken-1" text @click="dialog = false">
          Cancelar
        </v-btn>

        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="saveTask"> Salvar </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import tasks from "../../services/tasks";
export default {
  data: () => ({
    dialog: false,
    task: {
      title: "",
      description: "",
      status: "",
      dateConclusion: "",
    },
    items: ["ABERTA", "EM_ANDAMENTO", "CONCLUIDA"],
    picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
  }),

  watch: {
    picker() {
      this.task.dateConclusion = this.picker;
    },
  },

  methods: {
    saveTask() {
      tasks.createTask(this.task).then((response) => {
        alert(response);
      }),
        (this.dialog = false), 
        this.$forceUpdate;
    },
  },
};
</script>
