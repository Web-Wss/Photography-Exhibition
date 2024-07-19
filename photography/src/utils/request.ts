import axios from 'axios'
import { useAdminStore } from '@/store/useAdminStore'

// 创建axios实例
const request = axios.create({
  baseURL: '/api', // api的base_url
  timeout: 5000 // 请求超时时间
})

// 请求拦截器
request.interceptors.request.use(config => {
  // 在发送请求之前做些什么
  const adminStore = useAdminStore()
  const token = adminStore.getAdminInfo().token
  console.log('token',token);
  
  if (token) {
    config.headers.Authorization = token
  }
  return config
}, (error) => {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 响应拦截器
request.interceptors.response.use(response => {

  // 对响应数据做点什么
  return response
}, (error) => {
  // 对响应错误做点什么
  return Promise.reject(error)
})

export default request;