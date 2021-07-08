package com.project.chat.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.chat.ChatMessageVO;
import com.project.chat.FriendVO;
import com.project.chat.RoomVO;
import com.project.chat.main.dao.ChatDao;
 
@Service
public class ChatService implements ChatServiceImpl {
	
	@Autowired
	private ChatDao chatDao;
	
	@Override
	public RoomVO selectRoom(RoomVO roomVO) {
		
		return chatDao.selectRoom(roomVO);
	}

	@Override
	public int insertRoom(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return chatDao.insertRoom(roomVO);
	}
	@Override
	public List<ChatMessageVO> chatList(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return chatDao.chatList(roomVO);
	}
	@Override
	public ChatMessageVO lastChatList(RoomVO roomVO) {
		// TODO Auto-generated method stub
		return chatDao.lastChatList(roomVO);
	}
	@Override
	public int insertMessage(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return chatDao.insertMessage(map);
	}
	@Override
	public List<RoomVO> chatRoomList(String user_id) {
		return chatDao.chatRoomList(user_id);
	}
	@Override
	public int userFriendAdd(Map<String, String> map) {
		return chatDao.userFriendAdd(map);
	}
	@Override
	public int userFriendCount(Map<String, String> map) {
		return chatDao.userFriendCount(map);
	}
	@Override
	public List<FriendVO> userFriendSelect(String user_id) {
		return chatDao.userFriendSelect(user_id);
	}
	@Override
	public int chatMessageDelete(int room_id) {
		// TODO Auto-generated method stub
		return chatDao.chatMessageDelete(room_id);
	}
	@Override
	public int roomDelete(int room_id) {
		// TODO Auto-generated method stub
		return chatDao.roomDelete(room_id);
	}
}
