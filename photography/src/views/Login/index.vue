<script setup>
import { adminLoginApi } from "@/apis/admin";
import { useAdminStore } from "@/store/useAdminStore";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const form = ref({ phone: "", password: "" });
const adminStore = useAdminStore();
const router = useRouter();
// 登录按钮
const loginBtn = async () => {
  console.log(form.value);
  const res = await adminLoginApi(form.value.phone, form.value.password);
  console.log(res);
  if (res.data.code == 200) {
    adminStore.setAdminInfo(res.data.data);
    ElMessage({
      message: "登陆成功！",
      type: "success",
    });
    router.replace("/admin/aclassfication");
  } else {
    ElMessage({
      message: "登陆失败！",
      type: "error",
    });
  }
};
</script>

<template>
  <div class="login">
    <div class="form">
      <div class="title">HC摄影网站后台登陆</div>
      <el-form class="form-box" label-width="80px" :model="form">
        <el-form-item label="手机号">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div class="loginbtn">
        <el-button type="primary" @click="loginBtn">后台登陆</el-button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.login {
  width: 100%;
  height: 100vh;
  background: url("https://img.zcool.cn/community/0129f15a322568a80120ba3802ecc0.jpg@3000w_1l_0o_100sh.jpg")
    no-repeat;
  background-size: cover;
  background-position: center;
  position: relative;
  .form {
    position: absolute;
    border-radius: 20px;
    right: 20%;
    top: 26%;
    width: 420px;
    height: 350px;
    background-color: rgb(255 255 255 / 48%);
    .title {
      color: #01a39b;
      font-weight: 600;
      font-size: 1.6rem;
      width: 100%;
      text-align: center;
      margin-top: 3rem;
    }
    .form-box {
      margin-top: 2rem;
      width: 90%;
    }
    .loginbtn {
      width: 100%;
      text-align: center;
    }
  }
}
</style>
