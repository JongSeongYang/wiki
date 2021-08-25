package com.globalorder.wiki.repository;

import com.globalorder.wiki.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByTitle(String title);
}
