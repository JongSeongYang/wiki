package com.globalorder.wiki.controller;

import com.globalorder.wiki.domain.entity.BoardEntity;
import com.globalorder.wiki.dto.Board;
import com.globalorder.wiki.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final ModelMapper modelMapper;
    private final BoardService boardService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board.ResponseMessage> addContent(@RequestBody Board.Request board) {
        BoardEntity boardEntity = modelMapper.map(board, BoardEntity.class);
        String message = boardService.write(boardEntity);
        val response = Board.ResponseMessage.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<Board.ResponseMessage> deleteContent(@RequestParam("title") String title) {
        String message = boardService.deleteContent(title);
        val response = Board.ResponseMessage.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getList")
    public ResponseEntity<List<Board.Response>> getList() {
        List<BoardEntity> boardEntityList = boardService.findContentList();
        List<Board.Response> boardList = boardEntityList.stream()
                .map(boardEntity -> modelMapper.map(boardEntity, Board.Response.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(boardList);
    }
}
