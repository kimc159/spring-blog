package com.project.blog.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.blog.board.BoardVO;
import com.project.blog.board.SearchCriteria;

@Repository
public class BoardDao implements BoardDaoImpl {
	
	@Autowired
	private SqlSession session;
	
	private String namespace = "com.project.blog.board.mapper.BoardMapper";
	
	public List<BoardVO> list(SearchCriteria scri) {
		return session.selectList(namespace + ".list", scri);
	}
	public int write(BoardVO bo) {
		return session.insert(namespace + ".write", bo);
	}
	public int reply(BoardVO bo) {
		return session.insert(namespace + ".reply", bo);
	}
	public int replyUp(BoardVO bo) {
		return session.update(namespace + ".replyUp", bo);
	}
	public BoardVO selectBoard(int seq) { 
		return session.selectOne(namespace + ".selectBoard", seq);
	}
	public int updateBoard(BoardVO bo) {
		return session.update(namespace + ".updateBoard", bo);
	}
	public int deleteBoard(int seq) {
		return session.delete(namespace + ".deleteBoard", seq);
	}
	public int totalCount(SearchCriteria scri) {
		return session.selectOne(namespace + ".totalCount", scri); 
	}
	public int hitUp(int seq) {
		return session.update(namespace + ".hitUp", seq);
	}
	public int boardHit(Map<String, Object> map) {
		return session.insert(namespace + ".boardHit", map);
	}
	public int selectHitSeq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".selectHitSeq", map);
	}
}
