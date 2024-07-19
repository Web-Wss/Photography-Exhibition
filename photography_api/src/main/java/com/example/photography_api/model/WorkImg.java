package com.example.photography_api.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("work_img")
public class WorkImg {

    @TableId(value = "work_img_id",type = IdType.AUTO)
    private Long workImgId;//作品图片id

    private Long workId;//作品id

    private String workImgPath;//作品图片地址

}
