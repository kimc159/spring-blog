package com.project.chat.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.chat.ChatMessageVO;
import com.project.chat.FriendVO;
import com.project.chat.RoomVO;

@Repository
public class ChatDao implements ChatDaoImpl {

	@Autowired
	private SqlSession session;
	
	private String namespace = "com.project.chat.main.mapper.MainMapper";
	
	@Override
	public RoomVO selectRoom(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".selectRoom", roomVO);
	}
	@Override
	public int insertRoom(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return session.insert(namespace + ".insertRoom", roomVO);
	}
	@Override
	public List<ChatMessageVO> chatList(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".chatList", roomVO);
	}
	@Override
	public ChatMessageVO lastChatList(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".lastChatList", roomVO);
	}
	@Override
	public int insertMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.insert(namespace + ".insertMessage", map);
	}
	@Override
	public List<RoomVO> chatRoomList(String user_id) {
		return session.selectList(namespace + ".chatRoomList", user_id);
	}
	@Override
	public int userFriendAdd(Map<String, String> map) {
		return session.insert(namespace + ".userFriendAdd", map);
	}
	@Override
	public int userFriendCount(Map<String, String> map) {
		return session.selectOne(namespace + ".userFriendCount", map);
	}
	@Override
	public List<FriendVO> userFriendSelect(String user_id) { 
		return session.selectList(namespace + ".userFriendSelect", user_id);
	}
	
}
