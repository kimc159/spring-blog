package com.project.chat.main.dao;

import java.util.List;
import java.util.Map;

import com.project.chat.ChatMessageVO;
import com.project.chat.FriendVO;
import com.project.chat.RoomVO;

public interface ChatDaoImpl {
	RoomVO selectRoom(RoomVO roomVO);
	int insertRoom(RoomVO roomVO);
	List<ChatMessageVO> chatList(RoomVO roomVO);
	int insertMessage(Map<String, Object> map);
	List<RoomVO> chatRoomList(String user_id);
	ChatMessageVO lastChatList(RoomVO roomVO);
	int userFriendAdd(Map<String, String> map);
	int userFriendCount(Map<String, String> map);
	List<FriendVO> userFriendSelect(String user_id);
	int chatMessageDelete(int room_id);
	int roomDelete(int room_id);
}
