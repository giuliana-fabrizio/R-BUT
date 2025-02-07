<template>
  <div>
    <table v-if="shop">
      <tr>
        <td colspan="2">{{ shop.nom }}</td>
      </tr>
      <tr>
        <td>Stock : {{ shop.itemStock.length }} items</td>
        <td>Sur commande : {{ shop.itemCommande.length }} items</td>
      </tr>
      <tr>
        <td>
          <CheckedList
              :data="itemsStock"
              :checked="checkedItemsStock"
              item-check
              :item-button=true
              :list-button=true
              @checked-changed="toggleItemStock"
          >
            <template v-slot:item={item}>
              {{ item.text }}
            </template>
            <template v-slot:item-button="{indexRow}">
              <v-btn v-if="indexRow%2 == 0" color="red" @click="buyOneItem(indexRow)">Buy</v-btn>
              <v-btn v-else color="blue" @click="buyOneItem(indexRow)">Buy</v-btn>
            </template>
            <template v-slot:list-button={}>
              <v-btn color="green" @click="buySelectedItems()">Buy all</v-btn>
            </template>
          </CheckedList>
        </td>
        <td>
          <CheckedList
              :data="itemsCommande"
              :item-button=true
              @item-button-clicked="orderOneItem"
          >
            <template v-slot:item={item}>
              {{ item.text }} : {{ Math.floor(item.time / 1000) }} sec
            </template>
            <template v-slot:item-button="{item, indexRow}">
              <v-btn :color=donneCouleur(item) @click="orderOneItem(item.time, indexRow)">Order</v-btn>
            </template>
          </CheckedList>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import CheckedList from "@/components/CheckedList";
import {itemCats} from "@/datasource/model";

export default {
  name: "ShopDetails",
  components: {CheckedList},
  props: {
    shop: Object
  },
  data: () => ({
    idSelectedItemsStock: []
  }),
  computed: {
    checkedItemsStock() {
      let tab = []
      for (let i = 0; i < this.shop.itemStock.length; i++) {
        if (this.idSelectedItemsStock.includes(i)) tab.push(true);
        else tab.push(false);
      }
      return tab;
    },
    itemsStock() {
      return this.shop.itemStock.map(e => ({text: e.nom + ' : ' + e.prix + ' po'}))
    },
    itemsCommande() {
      return this.shop.itemCommande.map(e => ({
        text: e.nom + ' (' + e.prix + ' po)', time: Math.random() * 8000 + 2000,
        type: e.type, id: e._id
      }));
    }
  },
  methods: {
    toggleItemStock(index) {
      let id = this.idSelectedItemsStock.indexOf(index)
      if (id === -1) {
        // ajoute index
        this.idSelectedItemsStock.push(index)
      } else {
        // enleve index
        this.idSelectedItemsStock.splice(id, 1)
      }
    },
    buyOneItem(index) {
      console.log('achat de ' + this.shop.itemStock[index].nom);
      if (this.$store.getters.currentOr >= this.shop.itemStock[index].prix)
        this.$store.commit("sell", this.shop.itemStock[index]);
      else alert("Quantité d'or insuffisante");
    },
    buySelectedItems() {
      console.log('achat des items d\'indice ' + this.idSelectedItemsStock);
      let items = [], prixTot = 0;
      for (let index of this.idSelectedItemsStock) {
        items.push(this.shop.itemStock[index]);
        prixTot += this.shop.itemStock[index].prix;
      }
      if (this.$store.getters.currentOr >= prixTot) {
        for (let item of items) this.$store.commit("sell", item);
        this.idSelectedItemsStock.splice(0);
      } else alert("Quantité d'or insuffisante");
    },
    orderOneItem(time, index) {
      console.log('commande de ' + this.shop.itemCommande[index].nom);
      this.$store.dispatch("order", {time: time, item: this.shop.itemCommande[index].clone()});
    },
    donneCouleur(item) {
      const color = ["blue", "green", "pink", "red", "indigo", "yellow", "brown", "orange", "beige", "grey", "purple"]
      return color[itemCats.indexOf(item.type)];
    }
  },
  watch: {
    shop() {
      this.idSelectedItemsStock.splice(0)
    }
  }
}
</script>

<style scoped>

</style>