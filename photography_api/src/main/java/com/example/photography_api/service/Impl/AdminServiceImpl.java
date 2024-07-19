package com.example.photography_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.photography_api.mapper.*;
import com.example.photography_api.model.Classification;
import com.example.photography_api.model.User;
import com.example.photography_api.model.Work;
import com.example.photography_api.model.WorkImg;
import com.example.photography_api.service.AdminService;
import com.example.photography_api.utils.JWTUtils;
import com.example.photography_api.utils.QiniuUtilsHC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private ClassificationMapper classificationMapper;
    @Resource
    private WorkMapper workMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private WorkImgMapper workImgMapper;

    @Override
    public HashMap<String, Object> login(String phone, String password) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone)
                .eq("password",password);
        List<User> users = userMapper.selectList(userQueryWrapper);
//        判断是否有查询出信息
        if (users.size()>0){
            String token = JWTUtils.generateToken(users.get(0).getPhone());
            map.put("token",token);
            map.put("admin",users.get(0));
            return map;
        }
        return null;
    }

    @Override
    public List<Classification> getClassificationList() {
        List<Classification> classifications = classificationMapper.selectList(null);
        return classifications;
    }

    @Override
    public Integer addClassification(String classificationName) {
        Classification classification = new Classification();
        classification.setClassificationName(classificationName);
        int insert = classificationMapper.insert(classification);
        return insert;
    }

    @Override
    public Integer editClassification(Long classificationId, String classificationName) {
        QueryWrapper<Classification> classificationQueryWrapper = new QueryWrapper<>();
        classificationQueryWrapper.eq("classification_id",classificationId);
        Classification classification = new Classification();
        classification.setClassificationName(classificationName);
        int update = classificationMapper.update(classification, classificationQueryWrapper);
        return update;
    }

    @Override
    public Integer deleteClassification(Long classificationId) {
        QueryWrapper<Classification> classificationQueryWrapper = new QueryWrapper<>();
        classificationQueryWrapper.eq("classification_id",classificationId);
        int delete = classificationMapper.delete(classificationQueryWrapper);
        return delete;
    }

    @Override
    public List<Work> getWorkList() {
        List<Work> workList = adminMapper.getWorkList();
        return workList;
    }

    @Override
    public Integer addWork(String workName, Long classificationId, MultipartFile workImg, MultipartFile[] workImgPath) throws IOException {
        Integer i = null;
        Work work = new Work();
        work.setWorkName(workName);
        work.setClassificationId(classificationId);
        if (workImg!=null){
            String originalFilename = workImg.getOriginalFilename();//获取图片原始文件名
            int index1 = originalFilename.lastIndexOf(".");
            String extention = originalFilename.substring(index1);//获得图片后缀名
            String fileName = UUID.randomUUID().toString() + extention; //进行拼接
            String hc = QiniuUtilsHC.uploadQiniu(workImg.getBytes(), fileName, "HC/");
            work.setWorkImg("http://" + hc);
        }
//        新增
        workMapper.insert(work);
//        获取新增的workId
        Long workId = work.getWorkId();
            for (MultipartFile multipartFile : workImgPath) {
                WorkImg workImg1 = new WorkImg();
                String originalFilename = multipartFile.getOriginalFilename();//获取图片原始文件名
                int index1 = originalFilename.lastIndexOf(".");
                String extention = originalFilename.substring(index1);//获得图片后缀名
                String fileName = UUID.randomUUID().toString() + extention; //进行拼接
                String hc = QiniuUtilsHC.uploadQiniu(multipartFile.getBytes(),fileName,"HC/");
                workImg1.setWorkImgPath("http://" + hc);
                workImg1.setWorkId(workId);
                i = workImgMapper.insert(workImg1);
            }
        if (i!=null){
            return i;
        }
        return null;
    }

    @Override
    public Integer editWork(Long workId, String workName, Long classificationId) {
        Work work = new Work();
        QueryWrapper<Work> workQueryWrapper = new QueryWrapper<>();
        workQueryWrapper.eq("work_id",workId);
        work.setWorkName(workName);
        work.setClassificationId(classificationId);
        int update = workMapper.update(work, workQueryWrapper);
        return update;
    }

    @Override
    public Integer deleteWork(Long workId) {
        Integer i = null;
//        根据id获取作品头图
        Work work = workMapper.selectById(workId);
        String workImg = work.getWorkImg();
        int index = workImg.lastIndexOf("/");
        String imgPath = workImg.substring(index + 1);
        QiniuUtilsHC.deleteFileFromQiniu(imgPath,"HC/");
//        删除数据库数据
        i = workMapper.deleteById(workId);
//        获取作品图片列表
        List<WorkImg> workImgList = workImgMapper.selectList(new QueryWrapper<WorkImg>().eq("work_id", workId));
//        遍历删除作品图片
        for (WorkImg workImg1 : workImgList) {
            String workImgPath = workImg1.getWorkImgPath();
            int index1 = workImgPath.lastIndexOf("/");
            String imgPath1 = workImgPath.substring(index1 + 1);
            QiniuUtilsHC.deleteFileFromQiniu(imgPath1,"HC/");
        }
        i = workImgMapper.delete(new QueryWrapper<WorkImg>().eq("work_id",workId));
        return i;
    }

    @Override
    public Integer editAvatar(MultipartFile avatarImg) throws IOException {
        User user = new User();
        if (avatarImg!=null){
            String originalFilename = avatarImg.getOriginalFilename();//获取图片原始文件名
            int index1 = originalFilename.lastIndexOf(".");
            String extention = originalFilename.substring(index1);//获得图片后缀名
            String fileName = UUID.randomUUID().toString() + extention; //进行拼接
            String hc = QiniuUtilsHC.uploadQiniu(avatarImg.getBytes(),fileName,"HC/");
            user.setAvatarImg("http://" + hc);
        }
//        获取原头像信息并删除
        User user1 = userMapper.selectById(1);
        if (user1!=null){
            String avatarImg1 = user1.getAvatarImg();
            int index = avatarImg1.lastIndexOf("/");
            String imgPath = avatarImg1.substring(index + 1);
            QiniuUtilsHC.deleteFileFromQiniu(imgPath,"HC/");
        }
//        修改数据库信息
        int user_id = userMapper.update(user, new QueryWrapper<User>().eq("user_id", 1));
        return user_id;
    }

    @Override
    public List<User> getUserAvatarAndContent() {
        return userMapper.selectList(null);
    }

    @Override
    public Integer editUserInfo(String phone, String password, String content) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",1);
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setContent(content);
        int update = userMapper.update(user, userQueryWrapper);
        return update;
    }
}
