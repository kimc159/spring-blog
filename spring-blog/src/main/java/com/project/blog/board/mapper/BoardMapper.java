package com.project.blog.board.mapper;

import java.util.List;

import com.project.blog.board.BoardVO;

public interface BoardMapper {
	public List<BoardVO> list();
	public int write(BoardVO bo);
	public List<BoardVO> selectBoard(int seq);
	public int updateBoard(BoardVO bo);
	public int deleteBoard(int seq);
}
