import { RouteRecordRaw } from "vue-router";

// 用户
const Home = () => import("@/views/Home/index.vue");
const List = () => import("@/views/Home/components/List/index.vue")
const Detail = () => import("@/views/Home/components/Detail/index.vue")

// 管理员
const Login = () => import("@/views/Login/index.vue");
const Admin = () => import("@/views/admin/index.vue");
const AClassfication = () => import("@/views/admin/AClassification/index.vue");
const AWork = () => import("@/views/admin/AWork/index.vue");
const ASystem = () => import("@/views/admin/ASystem/index.vue");

const routes: Array<RouteRecordRaw> = [
  {
    path: "/home",
    name: "Home",
    component:Home,
    meta: {
      title: "作品集",
      requireAuth: false,
    },
    children: [
      {
        path: "/",
        name: "List",
        component: List,
        meta: {
          title: "作品集",
      requireAuth: false,
          
        }
      },
      {
        path: "/detail",
        name: "Detail",
        component: Detail,
        meta: {
          title: "作品详情",
      requireAuth: false,
        }
      },
    ]
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      title: "登录",
      requireAuth: false,
    }
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
    meta: {
      title: "后台管理",
      requireAuth: true,
    },
    children:[
      {
        path: "aclassfication",
        name: "AClassfication",
        component: AClassfication,
        meta: {
          title: "分类管理",
      requireAuth: true,

        }
      },
      {
        path: "awork",
        name: "AWork",
        component: AWork,
        meta: {
          title: "作品管理",
      requireAuth: true,

        }
      },
      {
        path: "asystem",
        name: "ASystem",
        component: ASystem,
        meta: {
          title: "系统管理",
      requireAuth: true,

        }
      },
    ]
  }
 
]

export default routes;