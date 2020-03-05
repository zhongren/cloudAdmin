package com.example.core.orm;

import java.util.List;

public class MybatisHelper {
    public static boolean isList(Object object) {
        if (object instanceof List) {
            return true;
        }
        return false;
    }


}
