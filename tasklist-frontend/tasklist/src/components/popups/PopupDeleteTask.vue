<template>
    <v-dialog
        max-width="600px"
    >
        <template v-slot:activator="{ on, attrs }">
            <v-btn icon color="red lighten-1" slot="activation" v-bind="attrs" v-on="on">
                <v-icon> mdi-delete </v-icon>
            </v-btn>
        </template>

        <v-card>
            <v-card-title class="pb-8">
                <h5 class="text--subtitle">Deseja remover esta tarefa?</h5>
            </v-card-title>

            <v-card-text>
               
            </v-card-text>

            <v-card-actions>
                <v-btn
                    color="red darken-1"
                    text
                    @click="dialog = false"
                >
                    Cancelar
                </v-btn>
                
                <v-spacer></v-spacer>
                <v-btn
                    color="green darken-1"
                    text
                    @click="dialog = false"
                >
                    SIM
                </v-btn>
                </v-card-actions>

        </v-card>
    </v-dialog>
</template>

<script>
export default {
    methods: {
      
  },
    
}
</script>
