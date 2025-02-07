<template>
  <v-app>
    <v-app-bar
      app
      color="black"
      dark
    >
      <NavBar :titles="titles" :path="path" @menu-clicked="goTo">
        <template v-slot:link-to="{link}">
          <v-btn :color="link.color">{{link.text}}</v-btn>
        </template>
      </NavBar>
    </v-app-bar>

    <v-main class="background-image">
      <router-view name="central"/>
    </v-main>
  </v-app>
</template>

<script>

import {mapActions} from 'vuex'
import NavBar from "@/components/NavBar";

export default {
  name: 'App',
  components: {NavBar},
  data: () => ({
    titles: [ {text:'Personnages', color: 'purple'},
      {text:'Villes', color: 'pink'},
    ],
    path: ['/persos', '/towns'],
    currentIndex: -1
  }),
  methods: {
    ...mapActions(['getAllTowns', 'getAllCharacs']),
    goTo(index) {
      if (index !== this.currentIndex) {
        this.currentIndex = index;
        if (index === 0) this.$router.push('/persos');
        else this.$router.push('/towns');
      }
    }
  },
  mounted() {
    this.getAllTowns()
    this.getAllCharacs()
  }
};
</script>

<style>

  .background-image {
    background: url('./assets/Castle.jpg') fixed;
    background-size: cover;
  }

  @font-face {
    font-family: Seagram;
    src: url("./assets/Seagram.ttf") format("truetype");
  }

  h1, h2, div, span, select, table {
    font-family: Seagram;
  }

  h1, h2 {
    color: #4f2800;
  }

  table, th, td {
    border: 4px #4f2800 solid;
    background-color: #fee9ad;
    border-radius: 10px;
  }

</style>