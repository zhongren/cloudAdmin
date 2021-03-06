package com.example.core.base;



import com.example.common.dto.ParamDto;
import com.example.core.dto.PageInfoDto;

import com.example.core.util.BeanUtil;
import com.example.core.util.PageUtil;

import com.github.pagehelper.Page;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */

public abstract class BaseService {


    private BaseRepo baseRepo;

    public BaseRepo getBaseRepo() {
        return baseRepo;
    }

    public void setBaseRepo(BaseRepo baseRepo) {
        this.baseRepo = baseRepo;
    }

    @PostConstruct
    public abstract void init();

    public  <T> PageInfoDto<T> page(ParamDto paramDto, Class<T> clazz) {
        Page page = PageUtil.startPage(paramDto);
        List<Map<String, Object>> data = baseRepo.findMapList(paramDto.getParam());
        List<T> list = BeanUtil.convertMap2List(data, clazz);
        PageInfoDto<T> pageInfoBean = new PageInfoDto<>();
        pageInfoBean.setData(list);
        pageInfoBean.setPageNum(page.getPageNum());
        pageInfoBean.setPageSize(page.getPageSize());
        pageInfoBean.setTotal(page.getTotal());
        pageInfoBean.setPages(page.getPages());
        return pageInfoBean;
    }

    public <T> List<T> findList(Map<String, Object> map, Class<T> tClass, String... columns) {
        return baseRepo.findList(map, tClass, columns);
    }

    public <T> List<T> findList(Class<T> tClass, String... columns) {
        return baseRepo.findList(null, tClass, columns);
    }

    public <T> List<T> findList(String field, Object value, Class<T> tClass, String... columns) {
        return baseRepo.findList(field, value, tClass, columns);
    }

    public <T> T find(String field, Object value, Class<T> tClass, String... columns) {
        return baseRepo.find(field, value, tClass, columns);

    }

    public <T> T find(Map<String, Object> param, Class<T> tClass, String... columns) {
        return baseRepo.find(param, tClass, columns);

    }

    public <T> int create(T bean) {
        return baseRepo.create(bean);
    }

    public <T> int update(String field, Object value, T bean) {
        return baseRepo.update(field, value, bean);
    }

    public int delete(String field, Object value) {
        return baseRepo.delete(field, value);
    }

    public int delete(String field, List value) {
        return baseRepo.deleteList(field, value);
    }
}
