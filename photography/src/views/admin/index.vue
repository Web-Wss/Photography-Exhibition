<script setup>
import Header from "@/views/admin/Header/index.vue";
import Aside from "@/views/admin/Aside/index.vue";
</script>

<template>
  <el-container>
    <el-header
      ><Header />
      <el-aside width="100%"><Aside /></el-aside>
    </el-header>

    <el-main>
      <el-card class="box-card">
        <router-view />
      </el-card>
    </el-main>
  </el-container>
</template>

<style scoped>
.el-header,
.el-footer {
  background-color: #ffffff;
  color: rgb(51, 51, 51);
  line-height: 60px;
}

/* .el-aside {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: calc(100vh - 60px);
} */

.el-main {
  /* background-color: #f2f2f2; */
  color: #333;
}

.box-card {
  overflow-y: scroll;
  padding: 30px 0 0;
  /* width: 99%; */
  height: calc(100vh - 100px);
  /* position: absolute; */
  /* z-index: -99; */
}
</style>
