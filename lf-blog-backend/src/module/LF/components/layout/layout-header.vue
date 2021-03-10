<template>
  <div id="layout-header">
      <h1 class="title">
        <a href="/">
          liu_fei
        </a>
      </h1>
      <div class="menu">
        <div class="f-fl nav">
          <a href="https://liufei885.lofter.com/mylofteruapp" class="customlink" target="_blank">UAPP</a>
          <a href="https://www.lofter.com/message/liufei885" rel="nofollow">私信</a>
          <a href="https://liufei885.lofter.com/view">归档</a>
        </div>
        <div class="f-fr sch">
          <a href="#" id="search">搜索</a>
          <form action="/search" method="get">
            <input type="text" name="q" style="display:none;" id="search_content">
          </form>
        </div>
      </div>
  </div>
</template>

<script>
 // import HeaderSearch from '../search'
  export default {
    name: "layout-header"
  }
</script>

<style>
  .title{
    width: 100%;
    text-align: center;
    overflow: hidden;
  }
  .title a {
    font: italic normal 60px/normal 'OFLSortsMillGoudyItalic', Georgia, sans;
    color: #555;
  }
  a {
    text-decoration: none;
    outline: 0;
  }

  .menu {
    margin: 40px -80px 0;
    padding: 0 80px;
    border: double 3px #ebebeb;
    border-left: none;
    border-right: none;
    background: #f5f5f5;
  }
  .menu:after, .info:after, .label:after, .g-ft:after {
    content: ".";
    display: block;
    visibility: hidden;
    height: 0;
    overflow: hidden;
    clear: both;
  }

  .f-fl {
    float: left;
  }
  .nav {
    width: 500px;
    height: 34px;
    overflow: hidden;
  }
  .nav a {
    margin: 0 10px 0 0;
  }
  .menu a {
    line-height: 34px;
    color: #bebebe;
  }
  .f-fr {
    float: right;
  }
  .sch {
    width: 140px;
    text-align: right;
  }
</style>
