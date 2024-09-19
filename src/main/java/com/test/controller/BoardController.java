package com.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.model.Board;
import com.test.service.BoardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/all")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Integer id) {
        Optional<Board> board = boardService.getBoardById(id);
        
        if (board.isPresent()) {
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board savedBoard = boardService.createBoard(board);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        if (!boardService.getBoardById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        board.setId(id);
        Board updatedBoard = boardService.updateBoard(id, board);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer id) {
        if (!boardService.getBoardById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

