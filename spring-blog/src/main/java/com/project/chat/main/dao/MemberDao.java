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
	
	@Override
	public int join(MemberVO member) {
		return session.insert(namespace + ".join", member);
	}
	@Override
	public LoginVO login(String memId) {
		return session.selectOne(namespace + ".login", memId);
	}
	@Override
	public int updateAuthKey(Map<String, String> map) {
		return session.update(namespace + ".updateAuthKey", map);
	}
	@Override
	public String getSaltById(String memId) {
		return session.selectOne(namespace + ".salt", memId);
	}
	@Override
	public String findId(String email) {
		return session.selectOne(namespace + ".findId", email);
	}
	@Override
	public int findPassword(Map<String, Object> map) {
		return session.selectOne(namespace + ".findPassword", map);
	}
	@Override
	public int updatePassword(Map<String, Object> map) {
		return session.update(namespace + ".updatePassword", map);
	}
	@Override
	public int idCheck(String id) {
		return session.selectOne(namespace + ".idCheck", id);
	}
	@Override
	public int modify(MemberVO member) {
		return session.update(namespace + ".modify", member);
	}
	@Override
	public MemberVO selectMember(String memberId) {
		return session.selectOne(namespace + ".selectMember", memberId);
	}
	@Override
	public int joinConfirm(Map<String, Object> map) {
		return session.update(namespace + ".joinConfirm", map);
	}
	@Override
	public int memberAuthStatus(LoginVO loginVO) {
		return session.selectOne(namespace + ".memberAuthStatus", loginVO);
	}
}
