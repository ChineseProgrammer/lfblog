import Vue from 'vue'
import index from './index.vue'
import router from '../../router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import './styles/element-variables.scss'
import 'font-awesome/css/font-awesome.min.css'
import '../../utils/filter_utils.js'
//import "./styles/templatemo-main.css"
//import $ from 'jquery'
//import 'bootstrap'
//import 'bootstrap/dist/css/bootstrap.min.css'
//import 'bootstrap/dist/js/bootstrap.min.js'

Vue.use(ElementUI)
Vue.config.productionTip = false
window.bus = new Vue();
new Vue({
  el: '#index',
  router,
  template: '<index/>',
  components: {index}
})
