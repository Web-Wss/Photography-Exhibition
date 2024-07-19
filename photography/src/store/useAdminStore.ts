import { defineStore } from "pinia";
import { ref } from "vue";

export const useAdminStore = defineStore("admin", () => {
  // 数据
  const adminInfo = ref({});

  const getAdminInfo = () => {
    return adminInfo.value;
  };
 
  const setAdminInfo = (data: any) => {
    console.log('adminInfo存储完成',data);
    adminInfo.value = data;
  };

  return {
    adminInfo,
    getAdminInfo,
    setAdminInfo,
  }

},{
  persist: true
}
);