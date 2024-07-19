package com.example.photography_api.service;

import com.example.photography_api.model.Classification;
import com.example.photography_api.model.VO.UserVO;
import com.example.photography_api.model.Work;

import java.util.List;

public interface HomeService {


//    获取分类列表
    List<Classification> getClassificationList();

//    获取用户头像和简介
    UserVO getUserAvatarAndContent();

//    根据分类id获取分类下的作品
    List<Work> getWorkListByClassificationId(Long classificationId);

    //    根据作品id获取详细信息
    Work getWorkDetailByWorkId(Long workId);


}
