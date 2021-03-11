<template>
  <div class="g-doc">
    <div class="g-hd">
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
    <div class="g-mn">
      <div style="text-align: left" v-html="article.htmlContent">
      </div>
    </div>

      <div id="SOHUCS" :sid="this.$route.params.id" ></div>
      <a href="#SOHUCS" id="changyan_count_unit"></a>
      <a href="#SOHUCS" id="changyan_parti_unit"></a>
      <foot></foot>

  </div>
</template>
<script type="text/javascript">
  window.changyan.api.config({
    appid: 'cyvlEg0NX',
    conf: 'prod_98e62b4b1c6ad6f436507f8c50cd5473'
  });
</script>
<script>
  import {getRequest} from '@/utils/api'
  import {getUrlKey} from '@/utils/utils'
  import Post from '@/components/Post'
  import Foot from '@/components/Foot'
  export default {

    name: 'Article',
    created(){
      var params  = getUrlKey("id ",window.location.href)
      getRequest('/api/home/article/'+params.id).then(res=>{
        this.article= res.data;
      }).catch(err=>{

      });
    },
    data () {
      return {
        article: {}
      }
    },
    components: {
      Post,
      Foot
    }
  }
</script>

<style>

  .g-doc {
    width: 650px;
    margin: 0 auto;
    padding: 40px 80px 20px;
    background: #fff;
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    box-shadow: 1px 0 20px #ddd;
  }
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
