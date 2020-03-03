package com.example.core.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createTime;
    private Long updateTime;

    //创建时 初始化的数据
    public void initCreate(){
        this.setCreateTime(System.currentTimeMillis());
    }

    //更新时 更新的数据
    public void initUpdate(){
        this.setUpdateTime(System.currentTimeMillis());
    }

}
