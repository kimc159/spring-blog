package com.project.blog.main.dao;

import java.util.List;
import java.util.Map;

import com.project.blog.member.MemberVO;

public interface MemberDaoImpl {
	public int join(MemberVO member);
	public MemberVO login(String memId);
	public int updateAuthKey(Map map);
	public String getSaltById(String memId);
}
