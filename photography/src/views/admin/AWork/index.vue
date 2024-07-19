<script setup>
import {
  getClassificationListApi,
  getWorkListApi,
  addWorkApi,
  editWorkApi,
  deleteWorkApi,
} from "@/apis/admin";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

// 分类列表
const classificationList = ref([
  { classificationId: 1, classificationName: "前端" },
  { classificationId: 2, classificationName: "后端" },
  { classificationId: 3, classificationName: "全栈" },
  { classificationId: 4, classificationName: "其他" },
]);
// 获取分类列表
const getClassificationList = async () => {
  const res = await getClassificationListApi();
  console.log(res);
  classificationList.value = res.data.data;
};

// 作品列表
const workList = ref([]);
// 获取作品列表
const getWorkList = async () => {
  const res = await getWorkListApi();
  console.log(res);
  workList.value = res.data.data;
};

// 新增作品
const dialogFormVisible = ref(false);
const formLabelWidth = "140px";
const form = ref({
  workName: "",
  classificationId: "",
});
const workImg = ref([]);
const workImgPath = ref([]);
const addWork = () => {
  form.value.workName = "";
  form.value.classificationId = "";
  workImg.value = [];
  workImgPath.value = [];
  dialogFormVisible.value = true;
};
const addWorkBtn = async () => {
  const formData = new FormData();
  if (workImg.value !== 0 && workImgPath.value !== 0) {
    formData.append("workImg", workImg.value[0].file);
    workImgPath.value.forEach((item) => {
      formData.append("workImgPath", item.file);
    });
  }
  formData.append("workName", form.value.workName);
  formData.append("classificationId", form.value.classificationId);
  const res = await addWorkApi(formData);
  console.log(res);
  getWorkList();
  dialogFormVisible.value = false;
  ElMessage({
    message: "新增作品完成！",
    type: "success",
  });
};

// 编辑作品
const dialogFormVisible1 = ref(false);
const form1 = ref({
  WorkId: "",
  classificationId: "",
  workName: "",
});
const editwork = (e) => {
  console.log(e);
  form1.value.WorkId = e.workId;
  form1.value.classificationId = e.classificationId;
  form1.value.workName = e.workName;
  dialogFormVisible1.value = true;
};
const editworkBtn = async () => {
  const formData = new FormData();
  formData.append("workId", form1.value.WorkId);
  formData.append("workName", form1.value.workName);
  formData.append("classificationId", form1.value.classificationId);
  const res = await editWorkApi(formData);
  getWorkList();
  ElMessage({
    message: "已完成作品编辑！",
    type: "success",
  });
  dialogFormVisible1.value = false;
};

// 删除作品信息
const deleteWork = async (e) => {
  ElMessageBox.confirm(`您确定要删除ID为${e.workId}的产品吗?`, "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      const res = deleteWorkApi(e.workId);
      ElMessage({
        message: `已删除ID为${e.workId}的作品`,
        type: "success",
      });
      getWorkList();
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "已取消",
      });
    });
};

onMounted(() => {
  getClassificationList();
  getWorkList();
});
</script>

<template>
  <div class="work">
    <!-- 新增作品按钮 -->
    <el-button type="primary" @click="addWork">新增作品</el-button>
    <!-- 数据 -->
    <el-table :data="workList" style="width: 100%">
      <el-table-column type="expand">
        <template #default="props">
          <p>图片列表</p>
          <img
            v-for="item in props.row.workImgList"
            :key="item.workImgId"
            :src="item.workImgPath"
            alt=""
            class="c-img"
          />
        </template>
      </el-table-column>
      <el-table-column prop="workId" label="ID" width="180" />
      <el-table-column
        prop="classification.classificationName"
        label="分类"
        width="180"
      />
      <el-table-column prop="workName" label="作品名称" width="180" />
      <el-table-column label="作品封面" width="380">
        <template #default="scope">
          <img
            :src="scope.row.workImg"
            alt=""
            style="width: 200px; object-fit: contain"
          />
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="180" />

      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="small" @click="editwork(scope.row)">
            编辑
          </el-button>
          <el-button size="small" type="danger" @click="deleteWork(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 新增作品 -->
  <el-dialog v-model="dialogFormVisible" title="新增作品" width="500">
    <el-form :model="form">
      <el-form-item label="作品名称" :label-width="formLabelWidth">
        <el-input v-model="form.workName" autocomplete="off" />
      </el-form-item>
      <!-- 选择分类 -->
      <el-form-item label="请选择作品分类" :label-width="formLabelWidth">
        <el-select
          v-model="form.classificationId"
          placeholder="请选择作品分类"
          style="width: 240px"
        >
          <el-option
            v-for="item in classificationList"
            :key="item.classificationId"
            :label="item.classificationName"
            :value="item.classificationId"
          />
        </el-select>
      </el-form-item>
      <!-- 作品头图 -->
      <el-form-item label="上传作品封面" :label-width="formLabelWidth">
        <van-uploader v-model="workImg" multiple :max-count="1" />
      </el-form-item>
      <!-- 作品图片列表 -->
      <el-form-item label="上传作品" :label-width="formLabelWidth">
        <van-uploader v-model="workImgPath" multiple :max-count="10" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addWorkBtn"> 新增 </el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 编辑作品 -->
  <el-dialog v-model="dialogFormVisible1" title="编辑作品" width="500">
    <el-form :model="form1">
      <el-form-item label="ID" :label-width="formLabelWidth">
        <el-input v-model="form1.WorkId" autocomplete="off" disabled />
      </el-form-item>
      <!-- 选择分类 -->
      <el-form-item label="请选择作品分类" :label-width="formLabelWidth">
        <el-select
          v-model="form1.classificationId"
          placeholder="请选择作品分类"
          style="width: 240px"
        >
          <el-option
            v-for="item in classificationList"
            :key="item.classificationId"
            :label="item.classificationName"
            :value="item.classificationId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="作品名称" :label-width="formLabelWidth">
        <el-input v-model="form1.workName" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
        <el-button type="primary" @click="editworkBtn"> 编辑 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped lang="scss">
.c-img {
  width: 100px;
  margin: 1rem;
}
</style>
