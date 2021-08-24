package com.globalorder.wiki.repository;

import com.globalorder.wiki.domain.entity.BoardEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(BoardEntity boardEntity) {
        em.persist(boardEntity);
    }

    public BoardEntity findOne(Long id) {
        return em.find(BoardEntity.class, id);
    }

    public List<BoardEntity> findAll() {
        return em.createQuery("select b from BoardEntity b", BoardEntity.class)
                .getResultList();
    }
}
