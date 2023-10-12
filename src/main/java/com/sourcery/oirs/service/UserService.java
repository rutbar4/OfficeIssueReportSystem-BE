package com.sourcery.oirs.service;

import com.sourcery.oirs.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser() {
        return new User("John Doe",
                "IT",
                "Manager",
                "123 Main Street",
                "New York",
                "NY",
                "10001",
                "USA");
    }
}
