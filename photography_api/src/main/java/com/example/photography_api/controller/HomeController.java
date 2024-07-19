package com.example.photography_api.controller;

import com.example.photography_api.model.Classification;
import com.example.photography_api.model.Result;
import com.example.photography_api.model.VO.UserVO;
import com.example.photography_api.model.Work;
import com.example.photography_api.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private HomeService homeService;


//    获取分类列表
    @GetMapping("/getclassificationlist")
    public Result getClassificationList(){
        List<Classification> classificationList = homeService.getClassificationList();
        return Result.success("获取分类列表",classificationList);
    }


//    获取头像和简介
    @GetMapping("/getuserinfo")
    public Result getUserAvatarAndContent(){
        UserVO userAvatarAndContent = homeService.getUserAvatarAndContent();
        return Result.success("获取头像和简介",userAvatarAndContent);
    }


//    根据分类id获取分类下的作品
    @GetMapping("/getworklistbyclassificationid")
    public Result getWorkListByClassificationId(@RequestParam("classificationId") Long classificationId){
        List<Work> workListByClassificationId = homeService.getWorkListByClassificationId(classificationId);
        return Result.success("获取作品列表",workListByClassificationId);
    }


//    根据作品id获取详情信息
    @GetMapping("/getworkdetailbyworkid")
    public Result getWorkDetailByWorkId(@RequestParam("workId") Long workId){
        Work workDetailByWorkId = homeService.getWorkDetailByWorkId(workId);
        return Result.success("获取作品详情",workDetailByWorkId);
    }




}
