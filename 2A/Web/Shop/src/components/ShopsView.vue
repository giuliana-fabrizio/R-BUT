<template>
  <div style="text-align: left; width: 30%">
    <h2 class="mt-5">Les boutiques</h2>
    <select v-model="currentShop" @change="shopSelected(currentShop)" class="select">
      <option disabled value="">Sélectionner une boutique</option>
      <option v-for="(shop, index) in villes[this.idTown-1].rues[idStreet].boutiques" :key="index" :value="shop">
        {{ shop.nom }}
      </option>
    </select>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "ShopsView",
  data: () => ({
    currentShop: null,
    boutiques: null,
  }),
  computed: {
    ...mapState(['villes'])
  },
  props: {
    idTown: String,
    idStreet: String,
  },
  methods: {
    shopSelected(shop) {
      this.$store.commit("setCurrentShop", shop)
    }
  }
}
</script>

<style scoped>

</style>