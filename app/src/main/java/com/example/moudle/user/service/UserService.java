package com.example.moudle.user.service;


import com.example.core.base.BaseService;
import com.example.moudle.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void init() {
        setBaseRepo(userRepo);
    }
}
