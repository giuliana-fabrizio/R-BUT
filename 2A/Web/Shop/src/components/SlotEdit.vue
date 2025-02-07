<template class="space-around">
  <div>
    <div v-if="slot.items.length > 0">
      {{ slot.label }} :
      <ul>
        <li v-for="(item, index) in slot.items" :key="index">
          <span>{{ item.nom }}</span>
          <v-btn color="#efb841" class="ms-1" type="submit" @click="enlever(slot, item)">
            ENLEVER
          </v-btn>
        </li>
      </ul>
    </div>

    <div v-if="peutEquiper.length > 0">
      <p>Possibilités d'assigner le(s) item(s) suivant(s) : </p>
      <ul>
        <li v-for="(item, index) in peutEquiper" :key="index">
          <span>{{ item.nom }}</span>
          <v-btn color="#efb841" class="ms-1" type="submit" @click="mettreItem(item)">
            METTRE
          </v-btn>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import {itemLimits} from "@/services/data.service";

export default {
  name: "SlotEdit",
  props: {
    slotName: String,
  },
  computed: {
    slot() {
      return this.$store.state.currentPerso.emplacements.find(slot => {
        return this.slotName === slot.nom
      });
    },
    peutEquiper() {
      let tab = [];
      for (let i = 0; i < this.$store.state.currentPerso.itemsAchetes.length; i += 1)
        if (itemLimits.find(s => this.slotName === s.slot).types.includes(
            this.$store.state.currentPerso.itemsAchetes[i].type
        ))
          tab.push(this.$store.state.currentPerso.itemsAchetes[i]);
      return tab;
    }
  },
  methods: {
    enlever(slot, item) {
      this.$store.commit("enlever", {slot: slot, item: item});
    },
    mettreItem(item) {
      if (this.slot.items.length >= itemLimits.find(s => s.slot === this.slotName).limit)
        alert("Trop d'éléments dans cette catégorie\
              \n\nSupprimer des éléments pour ajouter " + item.nom);
      else
        this.$store.commit("mettre", {pos: this.slot._id, item: item});
    }
  }
}
</script>

<style scoped>

</style>