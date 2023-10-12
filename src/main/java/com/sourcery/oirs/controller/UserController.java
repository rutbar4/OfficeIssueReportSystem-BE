package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.User;
import com.sourcery.oirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getUser() {
        return userService.getUser();
    }
}
