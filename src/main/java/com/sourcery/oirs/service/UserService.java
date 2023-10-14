package com.sourcery.oirs.service;


import com.sourcery.oirs.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser() {
        return User.buildMockData();
    }
}
