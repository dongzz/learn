import ElementPlus from 'element-plus'
import '@/assets/css/element-variables.scss'
import locale from 'element-plus/lib/locale/lang/zh-cn'

export default (app) => {
  app.use(ElementPlus, { locale })
}
