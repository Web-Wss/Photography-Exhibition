package com.example.photography_api.service;

import com.example.photography_api.model.Classification;
import com.example.photography_api.model.User;
import com.example.photography_api.model.Work;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface AdminService {



//    管理员登录
    HashMap<String,Object> login(String phone, String password);

//    获取分类列表
    List<Classification> getClassificationList();

//    新增分类信息
    Integer addClassification(String classificationName);

//    编辑分类信息
    Integer editClassification(Long classificationId, String classificationName);

//    删除分类
    Integer deleteClassification(Long classificationId);

//    获取作品列表
    List<Work> getWorkList();

//    新增作品信息
    Integer addWork(String workName, Long classificationId, MultipartFile workImg, MultipartFile[] workImgPath) throws IOException;

//    编辑作品信息
    Integer editWork(Long workId,String workName,Long classificationId);

//    删除作品信息
    Integer deleteWork(Long workId);

//    编辑头像信息
    Integer editAvatar(MultipartFile avatarImg) throws IOException;


    List<User> getUserAvatarAndContent();

    Integer editUserInfo(String phone, String password, String content);

}
