package com.example.photography_api.mapper;

import com.example.photography_api.model.Work;

public interface HomeMapper{

    //    根据作品id获取详细信息
    Work getWorkDetailByWorkId(Long workId);

}
