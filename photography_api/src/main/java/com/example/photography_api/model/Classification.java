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
@TableName("classification")
public class Classification {

    @TableId(value = "classification_id",type = IdType.AUTO)
    private Long classificationId;//分类id

    private String classificationName;//分类名称

}
