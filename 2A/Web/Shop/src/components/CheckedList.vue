<template>
  <div>
    <p v-for="(item, indexRow) in data" :key="indexRow">
      <input type="checkbox"
             v-if="itemCheck"
             :checked="checked[indexRow]"
             @click="$emit('checked-changed',indexRow)"
      >
      <slot name="item" :item="item" :indexRow="indexRow"/>
      <span v-if="itemButton" color="grey">
        <slot name="item-button" :item="item" :indexRow="indexRow"/>
      </span>
    </p>
    <span v-if="listButton" color="green"> <!-- @click="$emit('list-button-clicked')" -->
      <slot name="list-button"/>
    </span>
  </div>
</template>

<script>
export default {
  name: "CheckedList",
  props: {
    data: Array,
    itemCheck: Boolean,
    checked: Array,
    itemButton: Boolean,
    listButton: Boolean,
  }
}
</script>
