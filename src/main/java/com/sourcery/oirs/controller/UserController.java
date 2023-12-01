package com.sourcery.oirs.controller;

import com.sourcery.oirs.model.User;
import com.sourcery.oirs.model.UserMainInfoResponseDTO;
import com.sourcery.oirs.model.UserUpdateDTO;
import com.sourcery.oirs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserMainInfoResponseDTO> getAllUsers() { return userService.getUserList(); }

    @PutMapping("/update")
    public User updateUser (@RequestBody UserUpdateDTO user){
        userService.updateUser(user);
        return userService.getUserById(user.getId());
    }
}
