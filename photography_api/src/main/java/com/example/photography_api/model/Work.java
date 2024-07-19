package com.example.photography_api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("work")
public class Work {

    @TableId(value = "work_id", type = IdType.AUTO)
    private Long workId;//作品id

    private Long classificationId;//分类id

    private String workName;//作品名称

    private String workImg;//作品图片

    private String createTime;//创建时间

    @TableField(exist = false)
    private List<WorkImg> workImgList;//作品图片列表
    @TableField(exist = false)
    private Classification classification;

}
