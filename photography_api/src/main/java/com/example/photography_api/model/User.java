package com.example.photography_api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class User {

    @TableId
    private Long userId;//用户id

    private String avatarImg;//头像

    private String phone;//手机号

    private String password;//密码

    private String content;//简介

}
