<template>
	<div class="blogs">
		<figure>
			<a href="https://www.yezismile.com/detail/493.html" title="云上班体验" target="_blank">
				<img src="https://static.yezismile.com/data/photo/day_20200207/202002071654548727_s.jpeg" original="https://static.yezismile.com/data/photo/day_20200207/202002071654548727_s.jpeg" style="width: 167.5px; height: 137px; display: block;" alt="云上班体验">
			</a>
		</figure>
		<ul>
			<h3><a href="https://www.yezismile.com/detail/493.html" title="云上班体验" target="_blank">{{post.title}}</a></h3>
			<p>{{post.summary}}...<a href="https://www.yezismile.com/detail/493.html" target="_blank" style="color: #759b08;padding-left:5px;">[详情]</a>
			</p>
			<p class="autor">
				<span class="lm f_l"><a href="/memory/index?tag=3" style="color: #759b08">{{post.author}}</a></span>
				<span class="dtime f_l">{{post.editTime}}</span>
				<input class="zan_cookie" type="hidden" value="2">
				<input class="zan_newsid" type="hidden" value="493">
				<span class="label_bottom f_r" style="padding-left: 0;">
					<a href="javascript:void(0)" onclick="return false;" class="yz_zan" style="">{{post.viewsCount}}</a>
				</span>
				<span class="viewnum f_r">{{post.viewsCount}}k</span>
				<span class="pingl f_r" style="margin-right: 0;">
					<a href="https://www.yezismile.com/detail/493.html#SOHUCS">
						<span id="sourceId::493" class="cy_cmt_count">{{post.commentsCount}}</span>
					</a>
				</span>
			</p>
		</ul>
	</div>
</template>
<script>
	export default {
		name: "post",
		props: {
			post: {
				type: Object
			}
		}
	}
</script>
<style type="">
* {
    margin: 0;
    padding: 0;
}
ul {
    list-style: none;
}
/**文章列表**/
.blogs {
	padding: 30px 0;
	position: relative;
	border-bottom: #BFAB86 1px solid;
	1px solid: ;
	overflow: hidden;
}
.blogs figure {
	float: left;
	width: 25%;
	overflow: hidden;
	cursor: pointer;
}
.blogs ul {
	float: right;
	line-height: 22px;
	width:72%;
	color: #777;
}
.blogs h3 {
	font-size: 16px;
	font-weight: bold;
	transition: all .5s;
	margin-bottom: 10px;
}
p {
	display: block;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
}
.autor {
	overflow: hidden;
	clear: both;
	margin: 10px 0;
	display: inline-block;
	color: #999;
	width: 100%;
}
.blogs h3 a {
	color: #474645;
}
.autor span {
	margin: 0 10px 0 0;
	padding-left: 20px;
}
.lm {
	background: url('../assets/mynewsbg.png') no-repeat center;
	background-position: 0px 4px;
}
.f_l {
	float: left;
}
.dtime {
	background: url(../assets/mynewsbg.png) no-repeat center;
	background-position: 0px -45px;
}
.f_r {
	float: right;
}
.label_bottom .yz_zan {
	padding-left: 20px;
	color: #999999;
	font-size: 12px;
	background: url(../assets/list_icon.png) no-repeat -47px -297px;
}
.viewnum {
  padding-left: 20px;
	color: #999999;
	font-size: 12px;
	background: url(../assets/mynewsbg.png) no-repeat center;
	background-position: 0px -70px;
}
.pingl {
	padding-left: 20px;
	color: #999999;
	font-size: 12px;
	background: url(../assets/mynewsbg.png) no-repeat center;
	background-position: 0px -20px;
}
.pingl.f_r {
	padding: 0;
}
.cy_cmt_count{
   color: #999999;
   font-size: 12px;
}
</style>