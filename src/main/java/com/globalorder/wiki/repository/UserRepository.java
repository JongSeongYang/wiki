package com.globalorder.wiki.repository;

import com.globalorder.wiki.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(UserEntity userEntity) {
        em.persist(userEntity);
        return userEntity.getId();
    }

    public UserEntity findOne(Long id) {
        return em.find(UserEntity.class, id);
    }

    public List<UserEntity> findAll() {
        return em.createQuery("select u from UserEntity u", UserEntity.class)
                .getResultList();
    }

    public List<UserEntity> findByEmailPassword(String email, String password) {
        return em.createQuery("select u from UserEntity u where u.email =:email and u.password=:password", UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
    }

    public List<UserEntity> findByName(String name) {
        return em.createQuery("select u from UserEntity u where u.name =:name", UserEntity.class)
                .setParameter("name", name)
                .getResultList();
    }
}
