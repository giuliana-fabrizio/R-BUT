<template class="space-around">
  <v-container style="display: flex" class="mt-5">
    <div style="text-align: left; width: 20%">
      <h1 class="mb-5">Les personnages</h1>
      <select v-model="selected" @change="updateCurrentPerso(selected)" class="select">
        <option disabled value="">Sélectionner un personnage</option>
        <option v-for="(perso, index) in persos" :key="index" :value="perso">{{ perso.nom }}</option>
      </select>
    </div>
    <div v-if="selected" style="text-align: left; width: 80%">
      <PersoCaracs :selected="selected">
        <template v-slot:attribut="{niveau, vie, vitalite, or}">
          <li v-if="or < 100000">or : {{ orEnRome(or) }}
            <img src="../assets/piece.png" style="width: 10%; position: relative; top: 12px;">
          </li>
          <li v-else>or : {{ or }}
            <img src="../assets/piece.png" style="width: 10%; position: relative; top: 12px;">
          </li>
          <li>niveau :
            <img src="../assets/etoile.png" style="width: 10%; position: relative; top: 16px;" v-for="index in niveau"
                 :key="index">
          </li>
          <li>vitalite : {{ vitalite }}
            <br>
            vie : {{ vie }}
            <progress :max="vitalite" :value="vie"/>
          </li>
        </template>
      </PersoCaracs>
    </div>
  </v-container>
</template>

<script>
import {mapState} from 'vuex'
import PersoCaracs from "@/components/PersoCaracs.vue";

export default {
  name: 'PersosView',
  components: {PersoCaracs},
  data: () => ({
    selected: null,
    centaines: ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"],
    vingtaines: ["", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"],
    unites: ["", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"]
  }),
  computed: {
    ...mapState(['persos'])
  },
  methods: {
    updateCurrentPerso(selected) {
      this.$store.commit("setCurrentPerso", selected);
    },
    orEnRome(or) {
      let monnaie = "";
      while (or >= 1000) {
        monnaie += "M";
        or -= 1000;
      }
      return monnaie + this.centaines[Math.floor(or / 100)] + this.vingtaines[Math.floor(or / 10 % 10)] +
          this.unites[Math.floor(or % 10)];
    }
  }
}
</script>

<style>

space-around {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

.select {
  border: 4px #4f2800 solid;
  background-color: #fee9ad;
  border-radius: 10px;
  text-align: center;
  padding: 5px;
}

tr * {
  padding: 5px;
}

progress {
  margin-top: 5px;
  height: 20px;
  background-color: #af0606;
  padding: 0px;
}

progress::-moz-progress-bar {
  background-color: #7bad7b;
}

</style>
