package com.project.blog.main.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.blog.login.LoginVO;
import com.project.blog.member.MemberVO;

@Repository
public class MemberDao implements MemberDaoImpl{

	@Autowired
	private SqlSession session;
	private String namespace = "com.project.blog.main.mapper.MainMapper";
	
	public int join(MemberVO member) {
		return session.insert(namespace + ".join", member);
	}
	public LoginVO login(String memId) {
		return session.selectOne(namespace + ".login", memId);
	}
	public int updateAuthKey(Map map) {
		return session.update(namespace + ".updateKey", map);
	}
	public String getSaltById(String memId) {
		return session.selectOne(namespace + ".salt", memId);
	}
	public String findId(String email) {
		return session.selectOne(namespace + ".findId", email);
	}
	public int findPassword(Map map) {
		return session.selectOne(namespace + ".findPassword", map);
	}

	public int updatePassword(Map<String, Object> map) {
		return session.update(namespace + ".updatePassword", map);
	}
	public int idCheck(String id) {
		return session.selectOne(namespace + ".idCheck", id);
	}
	public int modify(MemberVO member) {
		// TODO Auto-generated method stub
		return session.update(namespace + ".modify", member);
	}
	public MemberVO selectMember(String memberId) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".selectMember", memberId);
	}
}
