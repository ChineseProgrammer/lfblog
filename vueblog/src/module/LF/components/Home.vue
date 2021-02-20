<template>
  <div class="g-doc">
    <div class="g-hd">
      <router-view name="layout_header"></router-view>
    </div>
    <div class="g-mn">

    </div>

    <div class="g-ft">
      <div class="f-fl"><span title="Copyright">©</span><a href="https://liufei885.lofter.com/">liu_fei</a> | Powered by <a href="//www.lofter.com">LOFTER</a></div>
      <div class="f-fr" id="gtotop" style="visibility: hidden;"><a href="#">返回顶部</a></div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
  .g-doc {
    width: 650px;
    margin: 0 auto;
    padding: 40px 80px 20px;
    background: #fff;
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    box-shadow: 1px 0 20px #ddd;
  }
</style>
