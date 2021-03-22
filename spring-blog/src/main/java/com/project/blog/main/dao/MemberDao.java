package com.project.blog.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.blog.member.MemberVO;

@Repository
public class MemberDao implements MemberDaoImpl{

	@Autowired
	private SqlSession session;
	private String namespace = "com.project.blog.main.mapper.MainMapper";
	
	public int join(MemberVO member) {
		return session.insert(namespace + ".join", member);
	}
	
	public MemberVO login(String memId) {
		return session.selectOne(namespace + ".login", memId);
	}
	public int updateAuthKey(Map map) {
		return session.update(namespace + ".updateKey", map);
	}
	public String getSaltById(String memId) {
		return session.selectOne(namespace + ".salt", memId);
	}
}
