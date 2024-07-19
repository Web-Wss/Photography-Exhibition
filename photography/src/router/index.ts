// 导入router所需的方法
import { createRouter, createWebHistory } from 'vue-router'
import { useAdminStore } from '@/store/useAdminStore'

// 导入路由页面配置
import routes from "./route"

// 路由参数配置
const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  next()

  // 判断要去的路由有没有requireAuth
  if (to.matched.some((r) => r.meta.requireAuth)) {
    const store = useAdminStore()
    console.log("判断",store.getAdminInfo().token);
    
    if (store.getAdminInfo().token) {
      next(); //有token，进行request请求，后台还会验证token
    } else {
      router.push({
        path: "/login",
        // 将刚刚要去的路由path作为参数，方便直接跳转
        // query: { redirect: to.fullPath },
      });
    }
  } else {
    next(); //如果无需token，放行
  }
})

// 全局后置守卫
router.afterEach((to, from) => {
  // 设置页面标题
  document.title = to.meta.title as string
})

export default router