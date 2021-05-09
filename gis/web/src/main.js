import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import NormailizeCss from 'normalize.css'
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'
import installElementPlus from './plugins/element'
import http from './utils/http'

const app = createApp(App)
    .use(store)
    .use(router)
    .use(ElementPlus)
    .use(NormailizeCss)
    .use(installElementPlus)
    .component('icon', Icon)
app.config.globalProperties.$http = http
app.mount('#app')
