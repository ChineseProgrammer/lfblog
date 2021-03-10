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
       <template v-for="item in posts">
         <post :post="item"></post>
       </template>
    </div>

    <div class="g-ft">
      <foot></foot>
    </div>
  </div>
</template>

<script>
  import {postRequest} from '@/utils/api'
  import {getRequest} from '@/utils/api'
   import Post from '@/components/Post'
   import Foot from '@/components/Foot'
export default {
 
  name: 'Home',
  created(){
    getRequest('/api/article/all',{}).then(res=>{

    }).catch(err=>{

    });
  },
  data () {
    return {
      posts: [
      {title:'云上班体验',summary:'一大早起来，刷朋友圈才知道今天凌晨四点，科比离开了。消息铺满一地，可惜没有人告诉我这是谣言。从上学期间就知道科比，那个给无数人带来正能量影响很多人的传奇人物，他曾经说那些你早起的日子，那些你奋斗的日子，那些你打球到晚',editTime:'2020-03-04 23:00:01',author:'LF',commentsCount:'23',viewsCount:'45'},
      {title:'凌晨四点的洛杉矶',summary:'一大早起来，刷朋友圈才知道今天凌晨四点，科比离开了。消息铺满一地，可惜没有人告诉我这是谣言。从上学期间就知道科比，那个给无数人带来正能量影响很多人的传奇人物，他曾经说那些你早起的日子，那些你奋斗的日子，那些你打球到晚',editTime:'2020-03-04 23:00:01',author:'LF',commentsCount:'23',viewsCount:'45'},
      {title:'背景图的天',summary:'一大早起来，刷朋友圈才知道今天凌晨四点，科比离开了。消息铺满一地，可惜没有人告诉我这是谣言。从上学期间就知道科比，那个给无数人带来正能量影响很多人的传奇人物，他曾经说那些你早起的日子，那些你奋斗的日子，那些你打球到晚',editTime:'2020-03-04 23:00:01',author:'LF',commentsCount:'23',viewsCount:'45'},
      {title:'没有辩护',summary:'一大早起来，刷朋友圈才知道今天凌晨四点，科比离开了。消息铺满一地，可惜没有人告诉我这是谣言。从上学期间就知道科比，那个给无数人带来正能量影响很多人的传奇人物，他曾经说那些你早起的日子，那些你奋斗的日子，那些你打球到晚',editTime:'2020-03-04 23:00:01',author:'LF',commentsCount:'23',viewsCount:'45'}
      ]
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
