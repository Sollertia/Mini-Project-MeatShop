package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.dto.UserRequestDto;
import com.hanghae.miniprojectmeatshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup") // 회원가입.
    public void registerUser(@RequestBody UserRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @PostMapping("/user/login")
    public List<Map<String,String>> login(@RequestBody UserRequestDto requestDto) {
        List<Map<String,String>> ret = userService.login(requestDto);
        return ret;
    }

}
