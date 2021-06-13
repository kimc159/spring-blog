package com.project.chat.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.chat.ChatMessageVO;
import com.project.chat.RoomVO;
import com.project.chat.login.LoginVO;
import com.project.chat.main.service.ChatService;
import com.project.chat.main.service.MemberService;
import com.project.chat.member.MemberVO;

@Controller
public class ChatController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ChatService chatService;

	@RequestMapping(value = "/chat/chatUserFind", method = RequestMethod.GET)
	public String chatUserFind() {

		return "chat/chatUserFind";
	}

	@ResponseBody
	@RequestMapping(value = "/chat/chatUserFind", method = RequestMethod.POST)
	public Map<String, Object> chatUserFindP(@RequestParam("memID") String memID, HttpSession session) {
		MemberVO searchUser = memberService.selectMember(memID);
		LoginVO loginUser = (LoginVO) session.getAttribute("login");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("searchUser", searchUser);
		map.put("loginUser", loginUser);

		return map;
	}

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chat(RoomVO roomVO, Model model) {
		
		RoomVO room = chatService.selectRoom(roomVO);
		List<ChatMessageVO> chatList = null;
		
		if (room != null) {
			roomVO.setRoom_id(room.getRoom_id());
			chatList = chatService.chatList(roomVO);
		} else {
			chatService.insertRoom(roomVO);
			room = chatService.selectRoom(roomVO); 
			chatList = chatService.chatList(roomVO);
		}

		model.addAttribute("chatList", chatList);
		model.addAttribute("room", roomVO); 
		
		return "chat/chat";
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/chat/chatRoomList") 
	public String chatRoomList(HttpSession session, Model model) { 
		String user_id = String.valueOf(session.getAttribute("user_id")); 
		
		List<RoomVO> chatRoomList = chatService.chatRoomList(user_id);
		List<ChatMessageVO> lastChatMessage = null;
		
		Iterator<RoomVO> it = chatRoomList.iterator();
		
		while(it.hasNext()) { 
			RoomVO room = it.next();
			lastChatMessage.add(chatService.lastChatList(room)); 
		}
		
		
		model.addAttribute("chatRoomList", chatRoomList);
		model.addAttribute("lastChatMessage", lastChatMessage);
		
		return "/chat/chatRoomList";
	}

}
