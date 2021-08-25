package com.globalorder.wiki.controller;

import com.globalorder.wiki.domain.entity.UserEntity;
import com.globalorder.wiki.dto.User;
import com.globalorder.wiki.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    /**
     * 회원가입
     * @param user
     * @return
     */

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        String message = userService.join(userEntity);
        return ResponseEntity.ok(message);
    }

    @GetMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        String message = userService.logIN(email, password);
        return ResponseEntity.ok(message);
    }

}
