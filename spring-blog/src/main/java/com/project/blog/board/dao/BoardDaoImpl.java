package com.project.blog.board.dao;

import java.util.List;
import java.util.Map;

import com.project.blog.board.BoardVO;
import com.project.blog.board.SearchCriteria;

public interface BoardDaoImpl {
	public List<BoardVO> list(SearchCriteria scri);
	public int write(BoardVO bo);
	public int reply(BoardVO bo);
	public int replyUp(BoardVO bo);
	public BoardVO selectBoard(int seq);
	public int updateBoard(BoardVO bo);
	public int deleteBoard(int seq);
	public int hitUp(int seq);
	public int totalCount(SearchCriteria scri);
	public int boardHit(Map<String, Object> map);
	public int selectHitSeq(Map<String, Object> map);
}
