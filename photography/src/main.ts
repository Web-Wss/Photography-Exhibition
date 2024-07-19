import { createApp } from 'vue'
import App from './App.vue'
import pinia from './store';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 1. 引入你需要的组件
import { Uploader,Image as VanImage  } from 'vant';
// 2. 引入组件样式
import 'vant/lib/index.css';

import router from '@/router/index'

const app = createApp(App)
app.use(pinia);
app.use(ElementPlus)
app.use(router)
app.use(Uploader);
app.use(VanImage);

app.mount('#app')
