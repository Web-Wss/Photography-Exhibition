<script setup>
import { ref, onMounted } from "vue";
import { getWorkDetailByIdApi } from "@/apis/user";
import { useRoute } from "vue-router";
const route = useRoute();
// 作品详情
const workInfo = ref({});
const workId = ref(1);

// 获取作品详情
const getWorkInfo = async () => {
  const res = await getWorkDetailByIdApi(workId.value);
  console.log(res);
  workInfo.value = res.data.data;
};

onMounted(() => {
  // console.log(route.query);
  workId.value = route.query.workId;
  getWorkInfo();
});
</script>

<template>
  <div class="detail">
    <div class="name">
      <span class="wz">{{ workInfo.workName }}</span>
    </div>
    <div class="container">
      <div class="item">
        <img
          v-for="item in workInfo.workImgList"
          :src="item.workImgPath"
          alt=""
        />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.detail {
  padding: 2rem;
  width: 100%;
  background-color: #f2f4f8;
}
.name {
  width: 100%;
  text-align: center;
  .wz {
    display: inline-block;
    font-size: 1.6rem;
    padding-left: 1rem;
    border-left: 4px solid red;
  }
}
.item {
  margin-top: 1rem;
  background-color: white;
  text-align: center;
}
img {
  width: 90%;
  object-fit: cover;
  margin-top: 1.2rem;
}
</style>
