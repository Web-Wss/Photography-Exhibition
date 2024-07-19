<script setup>
import { editAvatarApi, editUserInfoApi, getUserInfoApi } from "@/apis/admin";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

const tableData = ref([
  {
    userId: 1,
    avatarImg:
      "https://www.yorkyang.com/static/upload/image/20231109/1699528211997896.jpg",
    phone: "18888888888",
    password: "123456",
    content:
      "我是鲍瑞瑞！我与摄影的际遇可以追溯到22年，我的大四时光。这两年，我走过很多城市，去记录那里的风景、人文，去记录人们的情感。在摄影领域，我的成长并不算快，因为要兼顾学业与其他爱好，加上本人略微内向的性格，摄影经历一度停滞很久。摄影之外，在此必须提及的是我的专业特长——前端开发，虽然目前（24年1月）我还未毕业，但日常的学习和积累，以及在中国教育在线半年的实习经验，独立开发一个网页对我来说并不是件难事。也必须感谢 Benjamin 的 photographe",
  },
]);
// 获取信息
const getTableInfo = async () => {
  const res = await getUserInfoApi();
  tableData.value = res.data.data;
};
const dialogFormVisible = ref(false);
// 编辑头像信息
const avatar = ref([]);
const formLabelWidth = "100";
const editAvatar = () => {
  dialogFormVisible.value = true;
};
const editAvatarBtn = async () => {
  const formData = new FormData();
  formData.append("avatarImg", avatar.value[0].file);
  const res = await editAvatarApi(formData);
  getTableInfo();
  ElMessage({
    message: "头像修改成功！",
    type: "success",
  });
  dialogFormVisible.value = false;
};

// 编辑用户信息
const dialogFormVisible1 = ref(false);
const form1 = ref({
  phone: "",
  password: "",
  content: "",
});
const editUserInfo = (e) => {
  form1.value.phone = e.phone;
  form1.value.password = e.password;
  form1.value.content = e.content;
  dialogFormVisible1.value = true;
};
const editUserInfoBtn = async () => {
  const formData = new FormData();
  formData.append("phone", form1.value.phone);
  formData.append("password", form1.value.password);
  formData.append("content", form1.value.content);
  const res = await editUserInfoApi(formData);
  getTableInfo();
  ElMessage({
    message: "信息修改成功！",
    type: "success",
  });
  dialogFormVisible1.value = false;
};

onMounted(() => {
  getTableInfo();
});
</script>

<template>
  <div class="system">
    <el-button type="success" @click="editAvatar">编辑头像</el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="userId" label="ID" width="180" />
      <el-table-column label="头像" width="180">
        <template #default="scope">
          <img :src="scope.row.avatarImg" alt="" width="100" />
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="180" />
      <el-table-column prop="password" label="密码" width="180" />
      <el-table-column prop="content" label="简介" />
      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="small" @click="editUserInfo(scope.row)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 编辑头像信息 -->
  <el-dialog v-model="dialogFormVisible" title="编辑头像" width="500">
    <el-form>
      <el-form-item label="新头像" :label-width="formLabelWidth">
        <van-uploader v-model="avatar" multiple :max-count="1" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editAvatarBtn"> 编辑 </el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 编辑用户信息 -->
  <el-dialog v-model="dialogFormVisible1" title="编辑信息" width="500">
    <el-form>
      <el-form-item label="手机号" :label-width="formLabelWidth">
        <el-input v-model="form1.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form1.password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="简介" :label-width="formLabelWidth">
        <el-input
          v-model="form1.content"
          autocomplete="off"
          type="textarea"
          rows="6"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
        <el-button type="primary" @click="editUserInfoBtn"> 编辑 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped></style>
