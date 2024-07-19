<script setup>
import { getWorkListByClassificationIdApi } from "@/apis/user";
import { onMounted, ref, defineProps, watchEffect } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();

const props = defineProps({
  classificationId: Number,
});

// 作品列表
const list = ref([]);

// 导航id
const navId = ref(1);

// 获取作品列表根据分类id
const getList = async () => {
  const res = await getWorkListByClassificationIdApi(navId.value);
  list.value = res.data.data;
};

watchEffect(() => {
  navId.value = props.classificationId;
  getList();
});
// 跳转到作品详情页
const goDetail = (item) => {
  console.log(item);
  router.push({
    path: "/detail",
    query: {
      workId: item,
    },
  });
};

onMounted(() => {
  getList();
});
</script>

<template>
  <div class="content">
    <div class="container">
      <div class="row">
        <div
          class="item col-6 col-md-4 col-lg-3"
          v-for="item in list"
          :key="item.workId"
        >
          <div class="img" @click="goDetail(item.workId)">
            <img :src="item.workImg" alt="" />
            <div class="span">
              <span> {{ item.workName }} </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
// 内容
.content {
  background-color: #f2f4f8;
  padding-top: 1rem;
  .item {
    padding: 1rem;
    .img {
      cursor: pointer;
      padding: 0.6rem;
      width: 100%;
      background-color: white;
      box-shadow: 4px 4px 13px 5px rgb(0 102 255 / 19%);
      img {
        width: 100%;
        height: 20rem;
        object-fit: cover;
      }
      .span {
        padding: 1rem 0;
        span {
          padding: 0 1rem;
          border-left: 3px solid red;
          line-height: 2rem;
          font-size: 1.1rem;
        }
      }
    }
  }
}
</style>
