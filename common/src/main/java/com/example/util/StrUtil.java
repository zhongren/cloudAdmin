package com.example.util;

import com.google.common.base.CaseFormat;
import org.springframework.util.StringUtils;

public class StrUtil extends StringUtils {
    public static String Camel2Underscore(String str) {
        str = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
        return str;
    }

    public static String Underscore2Camel(String str) {
        str = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
        return str;
    }
}
