package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Service
public class BoardServicelmpl implements BoardService {

	@Autowired
	private BoardRepository boardrepo;


	@Override
	public List<Board> getBoardList(Board board){
		return (List<Board>) boardrepo.findAll();
	}

	@Override
	public void insertBoard(Board board) {
		boardrepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {
		return boardrepo.findById(board.getSeq()).get();
	}

	@Override
	public void updateBoard(Board board) {

	}

	@Override
	public void deleteBoard(Board board) {

	}
}
