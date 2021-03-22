package com.project.blog.board.service;

import java.util.List;

import com.project.blog.board.BoardVO;
import com.project.blog.board.SearchCriteria;

public interface BoardServiceImpl {
	public List<BoardVO> list(SearchCriteria scri);
	public int write(BoardVO bo);
	public int reply(BoardVO bo);
	public int replyUp(BoardVO bo);
	public BoardVO selectBoard(int seq);
	public int updateBoard(BoardVO bo);
	public int deleteBoard(int seq);
	public int totalCount(SearchCriteria scri);
	public int countUp(int seq);
}
