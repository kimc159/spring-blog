package com.project.chat.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.chat.board.BoardVO;
import com.project.chat.board.SearchCriteria;

interface BoardServiceImpl {
	List<BoardVO> list(SearchCriteria scri);
	int write(BoardVO bo);
	int reply(BoardVO bo);
	int replyUp(BoardVO bo);
	BoardVO selectBoard(int seq);
	int updateBoard(BoardVO bo);
	int deleteBoard(int seq);
	int totalCount(SearchCriteria scri);
	int hitUp(int seq);
	int boardHit(Map<String, Object> map);
	int selectHitSeq(Map<String, Object> map);
	void fileWrite(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception;
	List<BoardVO> fileList(SearchCriteria scri);
	int totalFileCount(SearchCriteria scri);
	BoardVO selectBoardFile(int seq);
	int hitUp2(int seq);
	int selectHitSeq2(Map<String, Object> map);
	int reply2(BoardVO bo);
	int replyUp2(BoardVO bo);
	BoardVO selectBoard2(int seq);
	void updateBoard2(BoardVO bo, MultipartHttpServletRequest mpRequest) throws Exception;
	int deleteBoardComment(int boardGroup);
	int deleteBoardFileAll(int seq);
	void deleteBoardFile(int seq);
}
