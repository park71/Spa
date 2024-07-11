package com.example.controller;

import com.example.dto.BoardDTO;
import com.example.entity.BoardEntity;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {


    @Autowired
    private BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<Map<String, String>> boardWritePro(@RequestBody BoardDTO boardDTO, @RequestParam(value="file") MultipartFile file) throws Exception{
        boardService.write(boardDTO.toEntity(), file);
        Map<String, String> response = new HashMap<>();
        response.put("message", "글 작성이 완료되었습니다.");
        response.put("searchUrl", "/api/board/list");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> boardList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {

        Page<BoardEntity> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        Map<String, Object> response = new HashMap<>();
        response.put("list", list.getContent());
        response.put("nowPage", nowPage);
        response.put("startPage", startPage);
        response.put("endPage", endPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BoardEntity> boardView(@PathVariable("id") Integer id) {
        BoardEntity board = boardService.boardView(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<BoardEntity> boardModify(@PathVariable("id") Integer id, @RequestBody BoardDTO boardDTO, @RequestParam("file") MultipartFile file) throws Exception{
        BoardEntity board = boardService.boardView(id);
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setFileName(boardDTO.getFileName());
        boardService.write(board, file);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BoardEntity> boardUpdate(@PathVariable("id") Integer id, @RequestBody BoardDTO boardDTO, @RequestParam("file") MultipartFile file) throws Exception{
        BoardEntity boardTemp = boardService.boardView(id);
        boardTemp.setTitle(boardDTO.getTitle());
        boardTemp.setContent(boardDTO.getContent());
        boardTemp.setFileName(boardDTO.getFileName());
        boardService.write(boardTemp, file);

        return new ResponseEntity<>(boardTemp, HttpStatus.OK);
    }
}