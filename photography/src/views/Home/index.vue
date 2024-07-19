<script setup>
import { getClassificationListApi, getUserInfoApi } from "@/apis/user";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();

// 导航列表
const navList = ref([
  {
    classificationId: 1,
    classificationName: "旗袍",
  },
  {
    classificationId: 2,
    classificationName: "汉服",
  },
  {
    classificationId: 3,
    classificationName: "街拍",
  },
  {
    classificationId: 4,
    classificationName: "小清新",
  },
  {
    classificationId: 5,
    classificationName: "私房",
  },
]);
// 导航选中
const navActive = ref(1);

// 获取导航列表
const getClassificationList = async () => {
  const res = await getClassificationListApi();
  // console.log(res);
  navList.value = res.data.data;
};

// 用户信息
const userInfo = ref({
  userId: 1,
  content: "简介",
  avatarImg:
    "https://www.yorkyang.com/static/upload/image/20231109/1699528211997896.jpg",
});
// 获取用户信息
const getUserInfo = async () => {
  const res = await getUserInfoApi();
  // console.log(res);
  userInfo.value = res.data.data;
};

// 修改导航信息
const editClassificationId = (id) => {
  // 判断导航是否在首页,不在则跳转至首页
  console.log(route.name);
  if (route.name != "List") {
    router.replace({
      path: "/",
    });
    navActive.value = id;
  } else {
    navActive.value = id;
  }
};

onMounted(() => {
  getClassificationList();
  getUserInfo();
});
</script>

<template>
  <div class="home">
    <!-- 导航 -->
    <div class="nav">
      <div
        class="item"
        v-for="item in navList"
        :key="item.classificationId"
        :class="{ active: navActive == item.classificationId }"
        @click="editClassificationId(item.classificationId)"
      >
        <span class="span">
          {{ item.classificationName }}
        </span>
      </div>
    </div>

    <!-- 头像 -->
    <div class="avatar">
      <img :src="userInfo.avatarImg" alt="" />
    </div>

    <!-- 介绍 -->
    <div class="info">
      {{ userInfo.content }}
    </div>

    <!-- 内容 -->
    <router-view :classificationId="navActive"></router-view>
  </div>
</template>

<style scoped lang="scss">
.home {
  width: 100%;
  padding: 2rem 0 0 0;

  // 导航
  .nav {
    display: flex;
    justify-content: center;
    .item {
      cursor: pointer;
      font-size: 1.1rem;
      .span {
        padding: 0 1rem;
        border-right: 1px solid rgb(0, 0, 0);
      }
    }
    .item:last-child {
      .span {
        border-right: none; /* 移除最后一个span的右边框 */
      }
    }
  }
  .active {
    font-weight: 600;
  }

  // 头像
  .avatar {
    margin: 2rem auto;
    width: 180px;
    height: 180px;

    img {
      width: 180px;
      height: 180px;
      object-fit: cover;
      border-radius: 100%;
    }
  }

  // 介绍
  .info {
    padding: 1rem;
    margin: 0 auto;
    width: 80%;
    font-size: 1.2rem;
    color: #777777;
  }
}
</style>
