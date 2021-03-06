package com.example.model;





import com.example.common.dto.ParamDto;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class BaseController {

/*
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResultDto> baseExceptionHandler(BaseException exception) {
        logger.error(exception.getCode() + ":" + exception.getMessage(), exception);
        return null;
    }
*/

    protected String getBaseUrl() {
        String baseUrl = getRequest().getScheme() + "://" + getRequest().getServerName()
                + ":" + getRequest().getServerPort()
                + getRequest().getServletPath();
        return baseUrl;
    }

    protected HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    protected Map<String, Object> getParamMap(String... except) {
        List<String> exceptList = new ArrayList<>();
        if (except != null && except.length > 0) {
            exceptList = Arrays.asList(except);
        }
        Map<String, String[]> paramMap = getRequest().getParameterMap();
        Map<String, Object> result = new HashMap<>();
        if (paramMap == null || paramMap.isEmpty()) {
            return result;
        }
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            if (!exceptList.isEmpty()) {
                if (exceptList.contains(entry.getKey())) {
                    continue;
                }
            }
            result.put(entry.getKey(), String.join(",", entry.getValue()));
        }
        return result;
    }

    protected Object getParam(String name) {
        Map<String, Object> paramMap = getParamMap();
        if (paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 过滤value为null和""的参数
     *
     * @param paramDto
     */
    private void filterParam(ParamDto paramDto) {
        if (paramDto == null || paramDto.getParam().isEmpty()) {
            return;
        } else {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry : paramDto.getParam().entrySet()) {
                if (entry.getValue() != null && !StringUtils.isEmpty(entry.getValue().toString())) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
            paramDto.getParam().clear();
            paramDto.getParam().putAll(map);
        }
    }


    /**
     * 将请求中的参数封装为ParamModel对象
     *
     * @return
     */
    protected ParamDto getParam() {
        ParamDto paramBean = new ParamDto();
        Integer pageNum = Integer.valueOf(getParam(ParamDto.PAGE_NUM) != null ? getParam(ParamDto.PAGE_NUM).toString() : "1");
        Integer pageSize = Integer.valueOf(getParam(ParamDto.PAGE_SIZE) != null ? getParam(ParamDto.PAGE_SIZE).toString() : "20");
        paramBean.setPageNum(pageNum);
        paramBean.setPageSize(pageSize);
        if (null == getParam(ParamDto.ORDER_TYPE) || null == getParam(ParamDto.ORDER_FIELD)) {
            paramBean.setOrderType(ParamDto.DESC);
            paramBean.setOrderField("id");
        } else {
            paramBean.setOrderType(getParam(ParamDto.ORDER_TYPE).toString());
            paramBean.setOrderField(getParam(ParamDto.ORDER_FIELD).toString());
        }

        Map<String, String> orderMap = paramBean.getOrderMap();
        orderMap.put(paramBean.getOrderField(), paramBean.getOrderType());
        paramBean.getParam().putAll(getParamMap(ParamDto.PAGE_NUM, ParamDto.PAGE_SIZE, ParamDto.ORDER_TYPE, ParamDto.ORDER_FIELD));
        filterParam(paramBean);
        return paramBean;
    }



}
