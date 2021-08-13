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
        <v-form v-model="valid">
          <v-text-field
            label="Título"
            v-model="task.title"
            filled
            dense
            clearable
            required
            maxlength="64"
            :rules="inputTitleRules"
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
            :rules="inputStatusRules"
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
                :rules="inputDateRules"
              ></v-text-field>
            </template>
            <v-date-picker v-model="picker" no-title scrollable :min="today">
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="menu = false">
                Cancelar
              </v-btn>
              <v-btn text color="primary" @click="$refs.menu.save(date)">
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-btn color="red darken-1" text @click="dialog = false">
          Cancelar
        </v-btn>

        <v-spacer></v-spacer>
        <v-btn color="green darken-1" text @click="submit"> Salvar </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import tasks from "../../services/tasks";
export default {
  props: ["updateList"],

  data: () => ({
    today: new Date(Date.now() - new Date().getTimezoneOffset() * 60000).toISOString().substr(0, 10),
    valid: false,
    dialog: false,
    task: {
      title: "",
      description: "",
      status: "",
      dateConclusion: "",
    },
    items: ["ABERTA", "EM_ANDAMENTO", "CONCLUÍDA"],
    picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    date: "",
    menu: false,
    modal: false,
    menu2: false,
    inputTitleRules: [
      v => !!v || 'O título é obrigatório',
      v =>  v.length >= 8 || 'Tamanho mínimo de 8 caracteres'
    ],
     inputStatusRules: [
      v => !!v || 'O status é obrigatório'
    ],
     inputDateRules: [
      v => !!v || 'A data de conclusão é obrigatória'
    ],
  }),

  watch: {
    picker() {
      this.task.dateConclusion = this.picker;
      this.date = this.formatDate(this.picker);
    },
  },

  methods: {
    submit() {
      if (this.valid) {
        tasks.createTask(this.task).then(() => {
          this.$parent.list();
          this.$parent.$forceUpdate;
        });
        this.dialog = false;
      }
    },
    formatDate(picker) {
      const [year, month, day] = picker.split("-");
      return `${day}/${month}/${year}`;
    },
  },
};
</script>
