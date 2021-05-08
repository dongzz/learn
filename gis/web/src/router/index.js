import { createRouter, createWebHashHistory } from 'vue-router'
import HistoryMap from '@/components/HistoryMap'
import SpecialMap from '@/components/SpecialMap'
import HelperDocument from '@/components/HelperDocument'

const routes = [
  {
    path: '/',
    name: 'historymap',
    component: HistoryMap
  },{
    path: '/specialmap',
    name: 'specialmap',
    component: SpecialMap
  },{
    path:'/helperdocument',
    name:'helperdocument',
    component:HelperDocument
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
