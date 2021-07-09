package com.project.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.project.chat.main.service.ChatService;

@RequestMapping("/socket") 
public class SocketHandler extends TextWebSocketHandler{
    
	@Autowired
	private ChatService chatService;
	
	//세션 리스트
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class); 
    
    //클라이언트가 연결 되었을 때 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        logger.info("{} 연결됨", session.getId()); 
    }

    //클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("{}로 부터 {} 받음", session.getAttributes().get("user_id"), message.getPayload());
        
        String currentUser = (String) session.getAttributes().get("user_id"); 
        String userMessage = message.getPayload();
        
        JSONParser parser = new JSONParser(); 
        Object obj = parser.parse(userMessage);
        JSONObject jsonObj = (JSONObject) obj;
        
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String time= format1.format(cal.getTime()); 
        Boolean createRoom = false; 
        
        
        Map<String, Object>map = new HashMap<String, Object>(); 
        
        map.put("room_id",  Integer.parseInt(String.valueOf(jsonObj.get("room_id"))));
        map.put("message", String.valueOf(jsonObj.get("message")));
        map.put("to_id", String.valueOf(jsonObj.get("to_id")));  
        map.put("from_id", String.valueOf(jsonObj.get("from_id"))); 
        map.put("time", time);
        
        
        RoomVO room = new RoomVO(); 
        room.setFrom_id(String.valueOf(jsonObj.get("from_id")));
        room.setTo_id(String.valueOf(jsonObj.get("to_id")));
        
        RoomVO selectRoom = chatService.selectRoom(room);
        
        if(selectRoom == null) {// 채팅방 없을 경우
        	chatService.insertRoom(room); // 채팅방 생성
        	createRoom = true;
        	
        	selectRoom = chatService.selectRoom(room); // 새로 생성한 채팅방 조회
            
            if(selectRoom != null) { 
            	map.put("room_id", selectRoom.getRoom_id()); // room_id 설정
                chatService.insertMessage(map); // 채팅 메시지 저장
            }
            
        } else {// 채팅방 있을 경우
        	map.put("room_id", selectRoom.getRoom_id()); // room_id 설정
            chatService.insertMessage(map); 
        }
        
        JSONObject jsonObject = new JSONObject(map);
        
        //모든 유저에게 메세지 출력
        for(WebSocketSession sess : sessionList){  
        
        	sess.sendMessage(new TextMessage(session.getAttributes().get("user_id")+"&"+jsonObject+"&"+time + "&" + createRoom));
        }   
        
    }

    //클라이언트 연결을 끊었을 때 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        logger.info("{} 연결 끊김.", session.getId());
    }
}
