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

const app = createApp(App)
    .use(store)
    .use(router)
    .use(ElementPlus)
    .use(NormailizeCss)
    .component('icon', Icon)

app.mount('#app')
