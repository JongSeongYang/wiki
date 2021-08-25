package com.globalorder.wiki.service;

import com.globalorder.wiki.domain.entity.UserEntity;
import com.globalorder.wiki.dto.User;
import com.globalorder.wiki.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserEntityServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    public void join() throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Kim");
        userEntity.setEmail("abc@def.com");
        userEntity.setPassword("1234");

        String message = userService.join(userEntity);

        assertEquals(message, "회원 가입 완료.");
    }

//    /*
//    중복 회원 검사
//     */
//    @Test
//    public void duplicateException() throws Exception {
//        UserEntity userEntity1 = new UserEntity();
//        userEntity1.setName("Yang");
//        userEntity1.setEmail("abc@def.com");
//        userEntity1.setPassword("1234");
//
//        UserEntity userEntity2 = new UserEntity();
//        userEntity2.setName("Yang");
//        userEntity2.setEmail("abc@def.com");
//        userEntity2.setPassword("1234");
//
//        userService.join(userEntity1);
//
//        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.join(userEntity2));
//        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
//    }

    /*
    mapper test
     */
    @Test
    public void mappingTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Kim");
        userEntity.setEmail("abc@def.com");
        userEntity.setPassword("1234");

        User user = modelMapper.map(userEntity, User.class);

        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getPassword(), userEntity.getPassword());
    }
}