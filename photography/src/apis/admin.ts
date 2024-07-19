import request from "@/utils/request";
import { AxiosPromise } from "axios";

// 管理员登录
export function adminLoginApi(phone: string, password: string): AxiosPromise {
  return request({
    url: "/admin/login",
    method: "post",
    params: {
      phone,
      password,
    },
  });
}

// 获取分类列表信息
export function getClassificationListApi(): AxiosPromise {
  return request({
    url: "/admin/getclassificationlist",
    method: "get",
  });
}

// 新增分类
export function addClassificationApi(classificationName: string): AxiosPromise {
  return request({
    url: "/admin/addclassification",
    method: "post",
    params: {
      classificationName,
    },
  });
}

// 编辑分类
export function editClassificationApi(classificationId: number, classificationName: string): AxiosPromise {
  return request({
    url: "/admin/editclassification",
    method: "post",
    params: {
      classificationId,
      classificationName,
    },
  });
}

// 删除分类信息
export function deleteClassificationApi(classificationId: number): AxiosPromise {
  return request({
    url: "/admin/deleteclassification",
    method: "post",
    params: {
      classificationId,
    },
  });
}

// 获取作品列表
export function getWorkListApi(): AxiosPromise {
  return request({
    url: "/admin/getworklist",
    method: "get",
  });
}

// 新增作品信息
export function addWorkApi(formData:Object): AxiosPromise {
  return request({
    url: "/admin/addwork",
    method: "post",
    data: formData
  });
}

// 编辑作品信息
export function editWorkApi(formData:Object): AxiosPromise {
  return request({
    url: "/admin/editwork",
    method: "post",
    data: formData
  });
}

// 删除作品信息
export function deleteWorkApi(workId: number): AxiosPromise {
  return request({
    url: "/admin/deletework",
    method: "post",
    params: {
      workId,
    },
  });
}

// 获取用户信息
export function getUserInfoApi(): AxiosPromise {
  return request({
    url: "/admin/getuserinfo",
    method: "get",
  });
}

// 修改头像信息
export function editAvatarApi(formData: Object): AxiosPromise {
  return request({
    url: "/admin/editavatar",
    method: "post",
    data: formData

  });
}

// 编辑用户信息
export function editUserInfoApi(formData: Object): AxiosPromise {
  return request({
    url: "/admin/edituserinfo",
    method: "post",
    data: formData

  });
}