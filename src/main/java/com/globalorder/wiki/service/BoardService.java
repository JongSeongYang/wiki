package com.globalorder.wiki.service;

import com.globalorder.wiki.domain.entity.BoardEntity;
import com.globalorder.wiki.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

   private final BoardRepository boardRepository;

    @Transactional
    public String write(BoardEntity boardEntity) {
        if(Objects.nonNull(boardRepository.findByTitle(boardEntity.getTitle())))
            return "중복된 제목입니다.";
        boardRepository.save(boardEntity);
        return "글 쓰기 완료.";
    }

    @Transactional
    public String deleteContent(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        boardRepository.delete(boardEntity);
        return "글 삭제 완료.";
    }

    @Transactional
    public List<BoardEntity> findContentList() {
        return boardRepository.findAll();
    }
}
