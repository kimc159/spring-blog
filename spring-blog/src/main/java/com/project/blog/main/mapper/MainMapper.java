package com.project.blog.main.mapper;

import java.util.Map;

import com.project.blog.member.MemberVO;

public interface MainMapper {
	public int join(MemberVO member);
	public MemberVO login(String memID);
	public int updateAuthKey(Map map);
}
