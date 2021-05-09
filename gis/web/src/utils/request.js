import axios from 'axios'

const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL,
    timeout: 90 * 1000,
    withCredentials: true
})
service.interceptors.request.use(config => {
    config.data = JSON.stringify(config.data);
    config.headers = {
        // 'Content-Type': 'application/x-www-form-urlencoded' //配置请求头
        'Content-Type': 'application/json;charset=UTF-8' //配置请求头
    }
    return config
}, error => {
    Promise.reject(error)
})
//4.导入文件
export default service