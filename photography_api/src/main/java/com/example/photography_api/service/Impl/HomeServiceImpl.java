package com.example.photography_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.photography_api.mapper.ClassificationMapper;
import com.example.photography_api.mapper.HomeMapper;
import com.example.photography_api.mapper.UserMapper;
import com.example.photography_api.mapper.WorkMapper;
import com.example.photography_api.model.Classification;
import com.example.photography_api.model.User;
import com.example.photography_api.model.VO.UserVO;
import com.example.photography_api.model.Work;
import com.example.photography_api.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private ClassificationMapper classificationMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WorkMapper workMapper;
    @Resource
    private HomeMapper homeMapper;

    @Override
    public List<Classification> getClassificationList() {
        List<Classification> classifications = classificationMapper.selectList(null);
        return classifications;
    }

    @Override
    public UserVO getUserAvatarAndContent() {
        UserVO userVO = new UserVO();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",1);
        User user = userMapper.selectOne(userQueryWrapper);
        userVO.setUserId(user.getUserId());
        userVO.setAvatarImg(user.getAvatarImg());
        userVO.setContent(user.getContent());
        return userVO;
    }

    @Override
    public List<Work> getWorkListByClassificationId(Long classificationId) {
        QueryWrapper<Work> workQueryWrapper = new QueryWrapper<>();
        workQueryWrapper.eq("classification_id",classificationId)
                .orderByDesc("create_time");
        List<Work> works = workMapper.selectList(workQueryWrapper);
        return works;
    }

    @Override
    public Work getWorkDetailByWorkId(Long workId) {
        Work workDetailByWorkId = homeMapper.getWorkDetailByWorkId(workId);
        return workDetailByWorkId;
    }
}
