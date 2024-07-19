<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
const route = useRoute();

const menu_list = ref([
  {
    id: "1",
    title: "分类管理",
    index: "/admin/aclassfication",
  },
  {
    id: "2",
    title: "作品管理",
    index: "/admin/awork",
  },
  {
    id: "3",
    title: "系统管理",
    index: "/admin/asystem",
  },
]);

const onRoutes = () => {
  console.log(route.path);
  return route.path;
};
</script>

<template>
  <div id="asideadmin">
    <el-row class="tac">
      <el-col>
        <el-menu
          :default-active="onRoutes()"
          class="el-menu-vertical-demo"
          collapse-transition
          unique-opened
          router
          mode="horizontal"
        >
          <el-menu-item v-for="i in menu_list" :index="i.index" :key="i.id">
            <i class="el-icon-menu"></i>
            <span slot="title">{{ i.title }}</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.tac {
  width: 100%;
}
.el-col-20 {
  width: 100%;
}
</style>
