package com.globalorder.wiki.service;

import com.globalorder.wiki.domain.entity.UserEntity;
import com.globalorder.wiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String join(UserEntity userEntity) {
        List<UserEntity> findUserEntityName = userRepository.findByName(userEntity.getName());
        if (!findUserEntityName.isEmpty()) {
            return "이미 존재하는 회원입니다.";
        }
        userRepository.save(userEntity);
        return "회원 가입 완료.";
    }

    @Transactional
    public String logIN(String email, String password) {
        List<UserEntity> findUserEntity = userRepository.findByEmailPassword(email, password);
        if (!findUserEntity.isEmpty()) {
            return "로그인 성공";
        }
        return "로그인 실패";
    }

    public List<UserEntity> findUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUser(Long userId) {
        return userRepository.findOne(userId);
    }
}
