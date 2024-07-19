<script setup>
import { ref } from "vue";
import { ArrowDown } from "@element-plus/icons-vue";

const admin = ref({});
</script>

<template>
  <div id="headeradmin">
    <div class="left"></div>
    <div class="center">HC摄影后台管理</div>
    <div class="right">
      <div class="img">
        <!-- <img :src="staff.staffAvatar" alt="" /> -->
      </div>
      <!-- <div class="name">管理员</div> -->
      <!-- <el-dropdown trigger="click">
        <span class="el-dropdown-link" style="color: red">
          管理员1<i class="el-icon-caret-bottom el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item class="clearfix">
            <span>退出登录</span>
            <el-badge class="mark" />
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown> -->
    </div>
  </div>
</template>

<style scoped lang="scss">
#headeradmin {
  width: 100%;
  height: 60px;
  display: flex;
}

.left {
  width: 30%;
  text-align: left;
  padding: 10px 0 0 70px;
}
.left img {
  width: 50px;
  height: 50px;
}
.center {
  font-size: 20px;
  color: #951cff;
  width: 40%;
  text-align: center;
  font-weight: 600;
}
.right {
  width: 30%;
  display: flex;
  justify-content: flex-end;
}
.right img {
  padding: 10px;
  width: 40px;
  height: 40px;
  border-radius: 50px;
}
.right .name {
  font-size: 14px;
  color: #951cff;
  line-height: 60px;
  text-align: right;
}

.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
