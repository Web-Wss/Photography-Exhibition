package com.example.photography_api.controller;


import com.example.photography_api.model.Result;
import com.example.photography_api.model.Work;
import com.example.photography_api.service.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

//    管理员登录
    @PostMapping("/login")
    public Result login(@RequestParam("phone")String phone, @RequestParam("password")String password) {
        HashMap<String, Object> login = adminService.login(phone, password);
        if (login == null){
            return Result.fail("管理员登录失败",null);
        }
        return Result.success("管理员登录成功",login);
    }

//    获取分类列表
    @GetMapping("/getclassificationlist")
    public Result getClassificationList() {
        return Result.success("获取分类列表成功",adminService.getClassificationList());
    }

//    新增分类
    @PostMapping("/addclassification")
    public Result addClassification(@RequestParam("classificationName")String classificationName) {
        Integer integer = adminService.addClassification(classificationName);
        if (integer == 0){
            return Result.fail("新增分类失败",null);
        }
        return Result.success("新增分类成功",integer);
    }

//    编辑分类
    @PostMapping("/editclassification")
    public Result editClassification(@RequestParam("classificationId")Long classificationId,@RequestParam("classificationName")String classificationName) {
        Integer integer = adminService.editClassification(classificationId, classificationName);
        if (integer == 0){
            return Result.fail("编辑分类失败",null);
        }
        return Result.success("编辑分类成功",integer);
    }


//    删除分类
    @PostMapping("/deleteclassification")
    public Result deleteClassification(@RequestParam("classificationId")Long classificationId) {
        Integer integer = adminService.deleteClassification(classificationId);
        if (integer == 0){
            return Result.fail("删除分类失败",null);
        }
        return Result.success("删除分类成功",integer);
    }

//    获取作品列表
    @GetMapping("/getworklist")
    public Result getWorkList() {
        List<Work> workList = adminService.getWorkList();
        return Result.success("获取作品列表",workList);
    }

//    新增作品信息
    @PostMapping("/addwork")
    public Result addWork(@RequestParam("workName")String workName,
                          @RequestParam("classificationId")Long classificationId,
                          @RequestParam("workImg") MultipartFile workImg,
                          @RequestParam("workImgPath")MultipartFile[] workImgPath) throws IOException {
        Integer integer = adminService.addWork(workName, classificationId, workImg, workImgPath);
        if (integer == 0){
            return Result.fail("新增作品失败",null);
        }
       return Result.success("新增作品成功",integer);
    }


//    编辑作品信息
    @PostMapping("/editwork")
    public Result editWork(@RequestParam("workId")Long workId,
                           @RequestParam("workName")String workName,
                           @RequestParam("classificationId")Long classificationId){
        Integer integer = adminService.editWork(workId, workName, classificationId);
        if (integer == 0){
            return Result.fail("编辑作品失败",null);
        }
        return Result.success("编辑作品成功",integer);
    }

//    删除作品信息
    @PostMapping("/deletework")
    public Result deleteWork(@RequestParam("workId")Long workId) {
        Integer integer = adminService.deleteWork(workId);
        if (integer == 0){
            return Result.fail("删除作品失败",null);
        }
        return Result.success("删除作品成功",integer);
    }

//    获取用户信息
    @GetMapping("/getuserinfo")
    public Result getUserAvatarAndContent() {
        return Result.success("获取用户信息成功",adminService.getUserAvatarAndContent());
    }


//    编辑头像信息
    @PostMapping("/editavatar")
    public Result editAvatar(@RequestParam("avatarImg") MultipartFile avatarImg) throws IOException {
        Integer integer = adminService.editAvatar(avatarImg);
        if (integer == 0){
            return Result.fail("编辑头像失败",null);
        }
        return Result.success("编辑头像成功",integer);
    }

//    编辑用户信息
    @PostMapping("/edituserinfo")
    public Result editUserInfo(@RequestParam("phone")String phone,
                               @RequestParam("password")String password,
                               @RequestParam("content")String content) {
        Integer integer = adminService.editUserInfo(phone, password, content);
        if (integer == 0){
            return Result.fail("编辑用户信息失败",null);
        }
        return Result.success("编辑用户信息成功",integer);
    }


}
