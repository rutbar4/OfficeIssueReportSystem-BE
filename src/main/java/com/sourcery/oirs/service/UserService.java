package com.sourcery.oirs.service;


import com.sourcery.oirs.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    public User getUser() {
        return User.buildMockData();
    }
}
