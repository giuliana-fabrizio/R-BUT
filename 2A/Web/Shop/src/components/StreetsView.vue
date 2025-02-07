<template>
  <div>
    <div style="text-align: left; width: 30%">
      <h2 class="mt-5">Les rues</h2>

      <select v-model="currentStreet" @change="changeStreet(currentStreet)" class="select">
        <option disabled value="">Sélectionner une rue</option>
        <option v-for="(rue, index) in villes[idTown-1].rues" :key="index" :value="index">{{ rue.nom }}</option>
      </select>
    </div>
    <router-view name="shops"/>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "TownDetails",
  data: () => ({
    currentStreet: null
  }),
  computed: {
    ...mapState(['villes'])
  },
  props: {
    idTown: String,
  },
  methods: {
    changeStreet(id) {
      this.$router.push('/towns/' + this.idTown + '/street/' + id);
      this.$store.commit('setCurrentShop', null);
    }
  },
  watch: {
    idTown() {
      this.currentStreet = null;
    }
  }
}
</script>

<style scoped>

</style>