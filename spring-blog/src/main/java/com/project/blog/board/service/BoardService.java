package com.project.blog.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.board.BoardVO;
import com.project.blog.board.SearchCriteria;
import com.project.blog.board.dao.BoardDao;

@Service
public class BoardService implements BoardServiceImpl {
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public List<BoardVO> list(SearchCriteria scri) {
		return dao.list(scri);
	}
	
	@Override
	public int write(BoardVO bo) {
		// TODO Auto-generated method stub
		return dao.write(bo); 
	}
	
	@Override
	public int reply(BoardVO bo) {
		return dao.reply(bo);
	}
	
	@Override
	public int replyUp(BoardVO bo) {
		return dao.replyUp(bo);
	}
	
	@Override
	public BoardVO selectBoard(int seq) {
		return dao.selectBoard(seq);
	}
	@Override
	public int updateBoard(BoardVO bo) {
		return dao.updateBoard(bo);
	}
	@Override
	public int deleteBoard(int seq) {
		return dao.deleteBoard(seq);
	}
	
	@Override
	public int totalCount(SearchCriteria scri) {
		return dao.totalCount(scri);
	}
	@Override
	public int hitUp(int seq) {
		return dao.hitUp(seq);
	}
	@Override
	public int boardHit(Map<String, Object> map) {
		return dao.boardHit(map);
	}

	public int selectHitSeq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectHitSeq(map);
	}
}
