package com.example.photography_api.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {

    private Long userId;//用户id

    private String avatarImg;//用户头像

    private String content;//用户简介


}
