<template>
  <div id="LF" style="background-color: rgba(235, 235, 235, 0.08)">
    <router-view name = "first"/>
  </div>
</template>

<script>
export default {
  name: 'LF'
}
</script>

<style>
#LF {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
