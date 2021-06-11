package com.project.chat.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.chat.ChatMessageVO;
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
	public int insertMessage(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return chatDao.insertMessage(map);
	}
	@Override
	public List<RoomVO> roomList(String user_id) {
		return chatDao.roomList(user_id);
	}
}
