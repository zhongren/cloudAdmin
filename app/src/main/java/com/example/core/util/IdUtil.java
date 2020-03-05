package com.example.core.util;

import cn.hutool.core.lang.Snowflake;

public class IdUtil {
    static {
        Snowflake idWorker = new Snowflake(0, 0);
    }

    public static long nextId(){
        Snowflake idWorker= cn.hutool.core.util.IdUtil.getSnowflake(0,0);
        return idWorker.nextId();
    }
}
