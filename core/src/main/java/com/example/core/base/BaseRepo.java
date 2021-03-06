package com.example.core.base;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.core.orm.*;

import com.example.core.util.BeanUtil;
import com.example.core.orm.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.Table;
import java.util.*;


public class BaseRepo {
    private static String FIND = "com.example.core.orm.BaseMapper.find";
    private static String CREATE = "com.example.core.orm.BaseMapper.create";
    private static String UPDATE = "com.example.core.orm.BaseMapper.update";
    private static String DELETE = "com.example.core.orm.BaseMapper.delete";
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    private ConditionMap conditionMap;

    public ConditionMap getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(ConditionMap conditionMap) {
        this.conditionMap = conditionMap;
    }

    /**
     * 设置查询条件
     */
    @PostConstruct
    public void init() {

    }

    public SearchCriteria createSearchCriteria(Map<String, Object> param, String... fields) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        if (null != fields && fields.length > 0) {
            List<String> fieldList = Arrays.asList(fields);
            searchCriteria.setColumnList(fieldList);
        }
        if (CollectionUtil.isNotEmpty(param) && CollectionUtil.isNotEmpty(conditionMap)) {
            List<Condition> conditionList = new ArrayList<>();
            for (Map.Entry<String, Condition> entry : getConditionMap().entrySet()) {
                if (param.containsKey(entry.getKey())) {
                    Condition condition = entry.getValue();
                    condition.setValue(param.get(entry.getKey()));
                    conditionList.add(condition);
                }
            }
            searchCriteria.setConditionList(conditionList);
        }
        return searchCriteria;
    }

    /**
     * field会自动驼峰转下划线
     *
     * @param field
     * @param value
     * @param fields
     * @return
     */
    private SearchCriteria createSearchCriteria(String field, Object value, String... fields) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        //添加查询字段
        if (null != fields && fields.length > 0) {
            List<String> fieldList = Arrays.asList(fields);
            searchCriteria.setColumnList(fieldList);
        }
        //添加查询条件
        if (!StringUtils.isEmpty(value)) {
            field = StrUtil.toUnderlineCase(field);
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition(field, value, Op.EQ);
            conditionList.add(condition);
            searchCriteria.setConditionList(conditionList);
        }

        return searchCriteria;
    }

    private CreateCriteria createCreateCriteria(Map<String, Object> param) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        CreateCriteria createCriteria = new CreateCriteria(tableName);
        List<Condition> conditionList = new ArrayList<>();
        for (Map.Entry<String, Object> map : param.entrySet()) {
            Condition condition = new Condition(map.getKey(), map.getValue());
            conditionList.add(condition);
        }
        createCriteria.setConditionList(conditionList);
        return createCriteria;
    }

    private UpdateCriteria createUpdateCriteria(Map<String, Object> param, Condition... conditions) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        UpdateCriteria updateCriteria = new UpdateCriteria(tableName);
        List<UpdateValue> updateValueList = new ArrayList<>();
        for (Map.Entry<String, Object> map : param.entrySet()) {
            UpdateValue updateValue = new UpdateValue(map.getKey(), map.getValue());
            updateValueList.add(updateValue);
        }
        updateCriteria.setUpdateValueList(updateValueList);
        if (conditions != null && conditions.length > 0) {
            updateCriteria.setConditionList(Arrays.asList(conditions));
        }
        return updateCriteria;
    }


    /**
     * 删除
     *
     * @param conditions
     * @return
     */

    private DeleteCriteria createDeleteCriteria(Condition... conditions) {
        Table table = AnnotationUtils.findAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        DeleteCriteria deleteCriteria = new DeleteCriteria(tableName);
        if (conditions != null && conditions.length > 0) {
            deleteCriteria.setConditionList(Arrays.asList(conditions));
        }
        return deleteCriteria;
    }

    public List<Map<String, Object>> findMapList(Map<String, Object> param, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(param, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        toCamelCase(data);
        return data;
    }


    public List<Map<String, Object>> findMapList(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field, value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        toCamelCase(data);
        return data;
    }

    public Map<String, Object> findMap(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field, value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        if (CollectionUtil.isEmpty(data)) {
            return null;
        }
        toCamelCase(data);
        return data.get(0);
    }

    public <T> T find(String field, Object value, Class<T> tClass, String... columns) {
        Map<String, Object> map = findMap(field, value, columns);
        return BeanUtil.mapToBean(map, tClass, true);
    }

    public <T> T find(Map<String, Object> param, Class<T> tClass, String... columns) {
        List<T> data = findList(param, tClass);
        if (data == null || data.isEmpty()) {
            return null;
        }
        return data.get(0);
    }

    public <T> List<T> findList(String field, Object value, Class<T> tClass, String... columns) {
        List<Map<String, Object>> list = findMapList(field, value, columns);
        return BeanUtil.convertMap2List(list, tClass);
    }


    public <T> List<T> findList(Map<String, Object> param, Class<T> tClass, String... columns) {
        List<Map<String, Object>> list = findMapList(param, columns);
        return BeanUtil.convertMap2List(list, tClass);
    }

    private int createMap(Map<String, Object> param) {
        CreateCriteria createCriteria = createCreateCriteria(param);
        return sqlSessionTemplate.insert(CREATE, createCriteria);
    }

    private int updateMap(Map<String, Object> param, Condition condition) {
        UpdateCriteria updateCriteria = createUpdateCriteria(param, condition);
        return sqlSessionTemplate.update(UPDATE, updateCriteria);
    }

    public <T> int create(T bean) {
        Map<String, Object> map = BeanUtil.convertBean2Map(bean);
        return createMap(map);
    }


    public <T> int update(String field, Object value, T bean) {
        Map<String, Object> map = BeanUtil.convertBean2Map(bean);
        Condition condition = new Condition(field, value, Op.EQ);
        return updateMap(map, condition);
    }

    public int deleteList(String field, Object value) {
        Condition condition = new Condition(field, value, Op.IN);
        DeleteCriteria deleteCriteria = createDeleteCriteria(condition);
        return sqlSessionTemplate.delete(DELETE, deleteCriteria);
    }

    public int delete(String field, Object value) {
        Condition condition = new Condition(field, value, Op.EQ);
        DeleteCriteria deleteCriteria = createDeleteCriteria(condition);
        return sqlSessionTemplate.delete(DELETE, deleteCriteria);
    }

    private List<Map<String, Object>> toCamelCase(List<Map<String, Object>> data) {
        for (Map<String, Object> map : data) {
            Map<String, Object> tmpMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                tmpMap.put(StrUtil.toCamelCase(entry.getKey()), entry.getValue());
            }
            map.clear();
            map.putAll(tmpMap);
        }
        return data;
    }

}
