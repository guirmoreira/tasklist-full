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
        <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="date"
          transition="scale-transition"
          offset-y
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="date"
              label="Data de Entrega"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="picker" no-title scrollable>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false"> Cancelar </v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(date)">
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>
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
  props: ["updateList"],

  data: () => ({
    dialog: false,
    task: {
      title: "",
      description: "",
      status: "",
      dateConclusion: "",
    },
    items: ["ABERTA", "EM_ANDAMENTO", "CONCLUÍDA"],
    picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
    date: "",
    menu: false,
    modal: false,
    menu2: false,
  }),

  watch: {
    picker() {
      this.task.dateConclusion = this.picker;
      this.date = this.formatDate(this.picker);
    },
  },

  methods: {
    saveTask() {
      tasks.createTask(this.task).then(() => {
        this.$parent.list();
        this.$parent.$forceUpdate;
      });
      this.dialog = false;
    },
    formatDate(picker) {
      const [year, month, day] = picker.split("-");
      return `${day}/${month}/${year}`;
    }
  },
};
</script>
