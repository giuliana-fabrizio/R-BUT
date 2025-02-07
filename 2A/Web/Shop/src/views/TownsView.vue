<template>
  <v-container style="display: flex">

    <div style="width: 50%">
      <div style="text-align: left; width: 30%">
        <h2 class="mt-5">Les villes</h2>

        <select v-model="currentTown" @change="changeTown(currentTown._id)" class="select">
          <option disabled value="">Sélectionner une ville</option>
          <option v-for="(ville, index) in villes" :key="index" :value="ville">{{ ville.nom }}</option>
        </select>
      </div>
      <router-view name="streets"/>
    </div>

    <div v-if="this.$store.getters.currentShop">
      <ShopDetails :shop="this.$store.getters.currentShop"></ShopDetails>
    </div>
  </v-container>
</template>

<script>
import {mapState} from 'vuex'
import ShopDetails from "@/components/ShopDetails.vue";

export default {
  name: 'TownsView',
  components: {ShopDetails},
  computed: {
    ...mapState(['villes'])
  },
  data: () => ({
    currentTown: null
  }),
  methods: {
    changeTown(id) {
      this.$router.push('/towns/' + id);
      this.$store.commit('setCurrentShop', null);
    }
  }
}
</script>

<style>

</style>
