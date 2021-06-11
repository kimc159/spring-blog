package com.project.chat.main.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.chat.login.LoginVO;
import com.project.chat.member.MemberVO;

@Repository
public class MemberDao implements MemberDaoImpl{

	@Autowired
	private SqlSession session;
	private String namespace = "com.project.chat.main.mapper.MainMapper";
	
	public int join(MemberVO member) {
		return session.insert(namespace + ".join", member);
	}
	public LoginVO login(String memId) {
		return session.selectOne(namespace + ".login", memId);
	}
	public int updateAuthKey(Map<String, String> map) {
		return session.update(namespace + ".updateAuthKey", map);
	}
	public String getSaltById(String memId) {
		return session.selectOne(namespace + ".salt", memId);
	}
	public String findId(String email) {
		return session.selectOne(namespace + ".findId", email);
	}
	public int findPassword(Map<String, Object> map) {
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
	public int joinConfirm(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.update(namespace + ".joinConfirm", map);
	}
	public int memberAuthStatus(LoginVO loginVO) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".memberAuthStatus", loginVO);
	}
}
