<script setup>
import {
  addClassificationApi,
  deleteClassificationApi,
  editClassificationApi,
} from "@/apis/admin";
import { getClassificationListApi } from "@/apis/user";
import { onMounted, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

// 分类列表
const classificationList = ref([]);

// 获取分类列表
const getClassificationList = async () => {
  const res = await getClassificationListApi();
  classificationList.value = res.data.data;
};

// 新增分类
const dialogFormVisible = ref(false);
const formLabelWidth = "140px";
const form = ref({ name: "", id: "" });
const addClassification = () => {
  dialogFormVisible.value = true;
};
const addClassificationBtn = async () => {
  const res = await addClassificationApi(form.value.name);
  if (res.data.code === 200) {
    dialogFormVisible.value = false;
    form.value.name = "";
    getClassificationList();
    ElMessage({
      message: "已完成新增分类！",
      type: "success",
    });
  }
};

// 编辑分类
const dialogFormVisible1 = ref(false);
const editClassification = (item) => {
  dialogFormVisible1.value = true;
  console.log(item);
  form.value.id = item.classificationId;
  form.value.name = item.classificationName;
};
const editClassificationBtn = async () => {
  const res = await editClassificationApi(form.value.id, form.value.name);
  if (res.data.code === 200) {
    dialogFormVisible1.value = false;
    form.value.id = "";
    form.value.name = "";
    getClassificationList();
    ElMessage({
      message: "已完成分类信息修改！",
      type: "success",
    });
  }
};

// 删除分类
const deleteClassification = (item) => {
  ElMessageBox.confirm(
    `您确定要删除分类ID为${item.classificationId}的分类吗?`,
    "Warning",
    {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    }
  )
    .then(() => {
      const res = deleteClassificationApi(item.classificationId);
      getClassificationList();
      ElMessage({
        message: `已删除ID为${item.classificationId}的分类`,
        type: "success",
      });
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
});
</script>

<template>
  <div class="classification">
    <!-- 新增分类按钮 -->
    <el-button type="primary" @click="addClassification">新增分类</el-button>

    <!-- 数据 -->
    <el-table :data="classificationList" style="width: 100%">
      <el-table-column prop="classificationId" label="ID" width="180" />
      <el-table-column prop="classificationName" label="分类名称" width="180" />
      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="small" @click="editClassification(scope.row)">
            编辑
          </el-button>
          <el-button
            size="small"
            type="danger"
            @click="deleteClassification(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 新增分类 -->
  <el-dialog v-model="dialogFormVisible" title="新增分类" width="500">
    <el-form :model="form">
      <el-form-item label="分类名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addClassificationBtn">
          新增
        </el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 编辑分类 -->
  <el-dialog v-model="dialogFormVisible1" title="编辑分类" width="500">
    <el-form :model="form">
      <el-form-item label="ID" :label-width="formLabelWidth">
        <el-input v-model="form.id" autocomplete="off" disabled />
      </el-form-item>
      <el-form-item label="分类名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
        <el-button type="primary" @click="editClassificationBtn">
          编辑
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped></style>
