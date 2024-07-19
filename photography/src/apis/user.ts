import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 获取分类列表
export function getClassificationListApi(): AxiosPromise {
  return request({
    url: "/home/getclassificationlist",
    method: "get",
  });
}

// 获取头像和简介
export function getUserInfoApi(): AxiosPromise {
  return request({
    url: "/home/getuserinfo",
    method: "get",
  });
}

// 根据分类id获取分类下的作品
export function getWorkListByClassificationIdApi(classificationId: number): AxiosPromise {
  return request({
    url: "/home/getworklistbyclassificationid",
    method: "get",
    params: {
      classificationId
    }
  });
}

// 获取作品详情根据作品id
export function getWorkDetailByIdApi(workId: number): AxiosPromise {
  return request({
    url: "/home/getworkdetailbyworkid",
    method: "get",
    params: {
      workId
    }
  });
}
