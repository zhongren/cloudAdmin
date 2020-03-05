package com.example.core.util;



import com.example.core.dto.ParamDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;


/**
 * @authoguavar zhongren
 * @date 2017/11/8
 */
public class PageUtil {
    public static Page startPage(ParamDto pageParam) {
        if (pageParam == null) {
            return null;
        }
        Page page = null;
        if (pageParam.getPageNum() > 0) {
            page = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        }
        if (!StringUtils.isEmpty(pageParam.orderBy())) {
            PageHelper.orderBy(pageParam.orderBy());
        }

        return page;
    }
}
