package com.test.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Board;
import com.test.repository.BoardRepository;

@Service
public class BoardService{

		@Autowired
		private BoardRepository boardRepository;

		public List<Board> getAllBoards() {
			return boardRepository.findAll();
		}

		public Optional<Board> getBoardById(Integer id) {
			return boardRepository.findById(id);
		}

		public Board createBoard(Board board) {
			return boardRepository.save(board);
		}

		public Board updateBoard(Integer id, Board updatedBoard) {
	        Optional<Board> optionalBoard = boardRepository.findById(id);
	        
	        if (optionalBoard.isPresent()) {
	            Board existingBoard = optionalBoard.get();
	            existingBoard.setBoardName(updatedBoard.getBoardName());
	            
	            return boardRepository.save(existingBoard);
	        } else {
	            throw new RuntimeException("Board not found with id: " + id);
	        }
	    }

		public void deleteBoard(Integer id) {
			boardRepository.deleteById(id);
		}
	

}
