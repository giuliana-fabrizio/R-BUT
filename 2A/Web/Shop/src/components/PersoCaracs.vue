<template class="space-around">
  <div v-if="selected" style="text-align: left;">
    <div style="display: flex; margin-top: 70px; align-items: center;">
      <h1 style="margin-right: 10px">{{ selected.nom }}</h1>
      <v-btn @click="update = true">Modifier</v-btn>
    </div>
    <div v-if="update">
      <UpdatePerso :selected="selected">
        <template v-slot:confirm-update="{nom, or, niveau, vie, force, armure}">
          <v-btn color="green" @click="updatePerso(nom, or, niveau, vie, force, armure)">Valider</v-btn>
        </template>
      </UpdatePerso>
    </div>
    <table class="table">
      <tr style="text-align: center;">
        <th style="width: 50%">Attributs et articles</th>
        <th style="width: 50%">Emplacements</th>
      </tr>
      <tr>
        <td>
          <ul>
            <slot name="attribut" :niveau="selected.niveau" :vie="selected.attributs.vie"
                  :vitalite="selected.attributs.vitalite" :or="selected.or"/>
            <li>force : {{ selected.attributs.force }}</li>
            <li>armure : {{ selected.attributs.protection }}</li>
          </ul>
        </td>
        <td>
          <ul>
            <li v-for="(slot, index) in slots" :key="index">
              <v-btn color="#efb841" class="ms-1" @click="selectedSlot = slot,
                                                            $router.push({name: 'slot', params: {name: slot.nom}})">
                {{ slot.label }} [{{ slot.items.length }}]
              </v-btn>
              <ul v-if="selectedSlot && peutEquiper().length > 0 && selectedSlot === slot">
                <li v-for="(item, index) in peutEquiper()" :key="index" style="">
                  <span>{{ item.nom }}</span>
                  <v-btn color="#efb841" class="ms-1" type="submit" @click="mettreItem(item)">
                    METTRE
                  </v-btn>
                </li>
              </ul>
            </li>
          </ul>
        </td>
      </tr>
      <tr>
        <td>
          <CheckedList
              :data="selected.itemsAchetes"
              :checked="checkedBoughtItems"
              item-check
              :item-button=true
              :list-button=true
              @checked-changed="toggleItem"
          >
            <template v-slot:item={item}>
              {{ item.nom }} {{ item.type }}
            </template>
            <template v-slot:item-button="{indexRow}">
              <v-btn color="red" @click="showItemPrice(indexRow)">Price</v-btn>
            </template>
            <template v-if="selected.itemsAchetes.length > 0" v-slot:list-button={}>
              <v-btn color="light" @click="showItemsInfo()">Infos</v-btn>
            </template>
          </CheckedList>
        </td>
        <td>
          <router-view name="slot"/>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>

import CheckedList from "@/components/CheckedList";
import {itemLimits, Perso} from "@/services/data.service";
import UpdatePerso from "@/components/UpdatePerso.vue";

import Vue from 'vue'; /* IMPORT À CHANGER */

export default {
  name: "PersoCaracs",
  components: {UpdatePerso, CheckedList},
  data: () => ({
    idSelectedBoughtItems: [], // ce tableau ne contient que les ids des items achetés sélectionnés.
    selectedSlot: null,
    update: false
  }),
  props: {
    selected: Perso
  },
  computed: {
    checkedBoughtItems() {
      if (this.selected === null) return []
      let tab = []
      for (let i = 0; i < this.selected.itemsAchetes.length; i++) {
        if (this.idSelectedBoughtItems.includes(i)) tab.push(true)
        else tab.push(false)
      }
      return tab
    },
    slots() {
      if (this.selected) {
        let tab = [];
        let slot = this.selected.emplacements.find(s => s.nom === 'head')
        slot.label = 'tête'
        tab.push(slot)
        slot = this.selected.emplacements.find(s => s.nom === 'body')
        slot.label = 'corps'
        tab.push(slot)
        slot = this.selected.emplacements.find(s => s.nom === 'hands')
        slot.label = 'mains'
        tab.push(slot)
        slot = this.selected.emplacements.find(s => s.nom === 'belt')
        slot.label = 'ceinture'
        tab.push(slot)
        slot = this.selected.emplacements.find(s => s.nom === 'bag')
        slot.label = 'sac à dos'
        tab.push(slot)
        return tab
      }
      return []
    }
  },
  methods: {
    showItemPrice(index) {
      alert(this.selected.itemsAchetes[index].nom + ' : ' + this.selected.itemsAchetes[index].prix);
      const prix = Math.floor(Math.random() / 2 + 0.4 * this.selected.itemsAchetes[index].prix);
      if (window.confirm("Souhaitez vous vendre l'article " +
          this.selected.itemsAchetes[index].nom + " au prix de " + prix + " ?"))
        this.$store.commit("resell", {item: this.selected.itemsAchetes[index], gold: prix});
    },
    showItemsInfo() {
      let nomItems = "", items = [], prixVente = 0;
      this.idSelectedBoughtItems.forEach(index => {
        nomItems += this.selected.itemsAchetes[index].nom + ' ; ';
        items.push({
          item: this.selected.itemsAchetes[index],
          prix: Math.random() / 2 + 0.4 * this.selected.itemsAchetes[index].prix
        });
        prixVente += items[items.length - 1].prix;
      });
      alert(nomItems);
      if (window.confirm("Souhaitez vous vendre les articles " +
          nomItems + " au prix de " + prixVente + " ?"))
        for (let it of items) this.$store.commit("resell", {item: it['item'], gold: it['prix']});
    },
    toggleItem(index) {
      let id = this.idSelectedBoughtItems.indexOf(index)
      if (id === -1) {
        // ajoute index
        this.idSelectedBoughtItems.push(index)
      } else {
        // enleve index
        this.idSelectedBoughtItems.splice(id, 1)
      }
    },
    mettreItem(item) {
      if (this.selectedSlot.items.length >= itemLimits.find(s => s.slot === this.selectedSlot.nom).limit)
        alert("Trop d'éléments dans cette catégorie\
              \n\nSupprimer des éléments pour ajouter " + item.nom);
      else
        this.$store.commit("mettre", {pos: this.selectedSlot._id, item: item});
    },
    peutEquiper() {
      let tab = [];
      for (let i = 0; i < this.$store.state.currentPerso.itemsAchetes.length; i += 1)
        if (itemLimits.find(s => this.selectedSlot.nom === s.slot).types.includes(
            this.selected.itemsAchetes[i].type
        ))
          tab.push(this.selected.itemsAchetes[i]);
      return tab;
    },
    updatePerso(nom, or, niveau, vie, force, armure) {
      /*
      this.selected.nom = nom;
      this.selected.or = parseInt(or);
      this.selected.niveau = parseInt(niveau);
      this.selected.attributs.vie = parseInt(vie);
      this.selected.attributs.force = parseInt(force);
      this.selected.attributs.protection = parseInt(armure);
      */
      Vue.set(this.selected, 'nom', nom);
      Vue.set(this.selected, 'or', parseInt(or));
      Vue.set(this.selected, 'niveau', parseInt(niveau));
      Vue.set(this.selected.attributs, 'vie', parseInt(vie));
      Vue.set(this.selected.attributs, 'force', parseInt(force));
      Vue.set(this.selected.attributs, 'protection', parseInt(armure));
      this.update = false;
    }
  },
  watch: {
    selected() {
      this.update = false;
    }
  }
}
</script>

<style>

</style>