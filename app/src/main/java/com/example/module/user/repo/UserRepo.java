package com.example.module.user.repo;

import com.example.core.base.BaseRepo;
import com.example.core.orm.Condition;
import com.example.core.orm.ConditionMap;
import com.example.core.orm.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "tb_user")
public class UserRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap = new ConditionMap();
        conditionMap.put("username", new Condition("username", Op.LIKE));
        conditionMap.put("realName", new Condition("real_name", Op.LIKE));
        setConditionMap(conditionMap);
    }
}
