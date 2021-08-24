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

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Transactional
    public Long join(UserEntity userEntity) {
        checkDuplicateUser(userEntity);
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    private void checkDuplicateUser(UserEntity userEntity) {
        List<UserEntity> findUserEntityName = userRepository.findByName(userEntity.getName());
        if (!findUserEntityName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<UserEntity> findUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUser(Long userId) {
        return userRepository.findOne(userId);
    }
}
