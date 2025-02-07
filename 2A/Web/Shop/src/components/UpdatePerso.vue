<template>
  <v-container>
    <form>
      <p style="font-size: x-large">Modifier {{ selected.nom }}</p>

      <label for="nom">Saisir le nom : </label>
      <input v-model="nom" class="input" id="nom" type="text">
      <br>
      <label for="or">Saisir la quantité d'or : </label>
      <input v-model="or" class="input" id="or" type="number" min="0">
      <br>
      <label for="or">Saisir le niveau : </label>
      <input v-model="niveau" class="input" id="niveau" type="number" min="0">
      <br>
      <label for="or">Saisir le nombre de vie : </label>
      <input v-model="vie" class="input" id="vie" type="number" min="0">
      <br>
      <label for="or">Saisir la force : </label>
      <input v-model="force" class="input" id="fore" type="number" min="0">
      <br>
      <label for="or">Saisir le nombre d'armure : </label>
      <input style="margin-bottom: 10px;" v-model="armure" class="input" id="armure" type="number" min="0">
      <br>
      <slot name="confirm-update" :nom="nom" :or="or" :niveau="niveau" :vie="vie" :force="force" :armure="armure"/>
    </form>
  </v-container>
</template>

<script>
export default {
  name: "UpdatePerso",
  data() {
    return {
      nom: null,
      or: null,
      niveau: null,
      vie: null,
      force: null,
      armure: null
    }
  },
  props: {
    selected: null
  },
  mounted() {
    this.nom = this.selected.nom;
    this.or = this.selected.or;
    this.niveau = this.selected.niveau;
    this.vie = this.selected.attributs.vie;
    this.force = this.selected.attributs.force;
    this.armure = this.selected.attributs.protection;
  }
}
</script>

<style scoped>

form {
  border: 4px #4f2800 solid;
  background-color: #fee9ad;
  border-radius: 10px;
  text-align: center;
  padding: 10px;
}

.input {
  border: 4px #4f2800 solid;
  border-radius: 10px;
  text-align: center;
  padding: 5px;
  margin-top: 10px;
}

</style>