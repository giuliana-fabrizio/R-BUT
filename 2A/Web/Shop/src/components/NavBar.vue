<template>
  <div>
    <span v-for="(link, index) in titles" :key="index" :color="link.color" @click="goTo(index)">
      <slot name="link-to" :link="link">{{ link.text }}</slot>
    </span>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  props: {titles: Array, path: Array},
  methods: {
    goTo(index) {
      this.$emit('menu-clicked', index);
      this.$router.push(this.titles[index].path);
    }
  }
}
</script>

<style scoped>

span {
  margin-right: 10px;
}

</style>