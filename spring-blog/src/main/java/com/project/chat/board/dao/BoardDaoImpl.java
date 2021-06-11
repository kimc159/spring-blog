package com.project.chat.board.dao;

import java.util.List;
import java.util.Map;

import com.project.chat.board.BoardVO;
import com.project.chat.board.SearchCriteria;

interface BoardDaoImpl {
	List<BoardVO> list(SearchCriteria scri);
	int write(BoardVO bo);
	int reply(BoardVO bo);
	int replyUp(BoardVO bo);
	BoardVO selectBoard(int seq);
	int updateBoard(BoardVO bo);
	int deleteBoard(int seq);
	int hitUp(int seq);
	int totalCount(SearchCriteria scri);
	int boardHit(Map<String, Object> map);
	int selectHitSeq(Map<String, Object> map);
	void insertFile(Map<String, Object> map);
	int fileWrite(BoardVO boardVO);
	List<BoardVO> fileList(SearchCriteria scri);
	int totalFileCount(SearchCriteria scri);
	BoardVO selectBoardFile(int seq);
	int hitUp2(int seq);
	int selectHitSeq2(Map<String, Object> map);
	int reply2(BoardVO bo);
	int replyUp2(BoardVO bo);
	BoardVO selectBoard2(int seq);
	int updateBoard2(BoardVO bo);
	int deleteBoard2(int seq);
	int deleteBoardFile(int seq);
	BoardVO selectFile(int seq);
	int deleteBoardComment(int boardGroup);
	int deleteBoardFileAll(int seq);
}
