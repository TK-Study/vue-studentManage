import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//引入导出Excel表格工具
import JsonExcel from "vue-json-excel";
// markdown
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';



//全局注册组件
Vue.component('downloadExcel', JsonExcel)


Vue.use(ElementUI);
Vue.use(mavonEditor);

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App),

}).$mount('#app')
