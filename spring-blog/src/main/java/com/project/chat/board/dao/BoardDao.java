package com.project.chat.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.chat.board.BoardVO;
import com.project.chat.board.SearchCriteria;

@Repository
public class BoardDao implements BoardDaoImpl {
	
	@Autowired
	private SqlSession session;
	
	private String namespace = "com.project.chat.board.mapper.BoardMapper";
	
	@Override
	public List<BoardVO> list(SearchCriteria scri) {
		return session.selectList(namespace + ".list", scri);
	}
	@Override
	public int write(BoardVO bo) {
		return session.insert(namespace + ".write", bo);
	}
	@Override
	public int reply(BoardVO bo) {
		return session.insert(namespace + ".reply", bo);
	}
	@Override
	public int replyUp(BoardVO bo) {
		return session.update(namespace + ".replyUp", bo);
	}
	@Override
	public BoardVO selectBoard(int seq) { 
		return session.selectOne(namespace + ".selectBoard", seq);
	}
	@Override
	public int updateBoard(BoardVO bo) {
		return session.update(namespace + ".updateBoard", bo);
	}
	@Override
	public int deleteBoard(int seq) {
		return session.delete(namespace + ".deleteBoard", seq);
	}
	@Override
	public int totalCount(SearchCriteria scri) {
		return session.selectOne(namespace + ".totalCount", scri);
	}
	@Override
	public int hitUp(int seq) {
		return session.update(namespace + ".hitUp", seq);
	}
	@Override
	public int boardHit(Map<String, Object> map) {
		return session.insert(namespace + ".boardHit", map);
	}
	@Override
	public int selectHitSeq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".selectHitSeq", map);
	}
	@Override
	public int hitUp2(int seq) {
		return session.update(namespace + ".hitUp2", seq);
	}
	@Override
	public int selectHitSeq2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".selectHitSeq2", map);
	}
	@Override
	public int fileWrite(BoardVO bo) {
		// TODO Auto-generated method stub
		return session.insert(namespace + ".fileWrite", bo);
	}
	@Override
	public void insertFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		session.insert(namespace + ".insertFile", map);
	}
	@Override
	public List<BoardVO> fileList(SearchCriteria scri) {
		return session.selectList(namespace + ".fileList", scri);
	}
	@Override
	public int totalFileCount(SearchCriteria scri) {
		return session.selectOne(namespace + ".totalFileCount", scri);
	}
	@Override
	public BoardVO selectBoardFile(int seq) {
		return session.selectOne(namespace + ".selectBoardFile", seq);
	}
	@Override
	public int reply2(BoardVO bo) {
		return session.insert(namespace + ".reply2", bo);
	}
	@Override
	public int replyUp2(BoardVO bo) {
		return session.update(namespace + ".replyUp2", bo);
	}
	@Override
	public BoardVO selectBoard2(int seq) {
		return session.selectOne(namespace + ".selectBoard2", seq);
	}
	@Override
	public int updateBoard2(BoardVO bo) {
		return session.update(namespace + ".updateBoard2", bo);
	}
	@Override
	public int deleteBoard2(int seq) {
		return session.delete(namespace + ".deleteBoard2", seq);
	}
	@Override
	public int deleteBoardFile(int seq) {
		return session.delete(namespace + ".deleteBoardFile", seq);
	}
	@Override
	public BoardVO selectFile(int seq) {
		return session.selectOne(namespace + ".selectFile", seq);
	}
	@Override
	public int deleteBoardComment(int boardGroup) {
		return session.delete(namespace + ".deleteBoardComment", boardGroup);
	}
	@Override
	public int deleteBoardFileAll(int boardGroup) {
		return session.delete(namespace + ".deleteBoardFileAll", boardGroup);
	}
}
