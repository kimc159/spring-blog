<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
 
	<div class="chatbox">
	  <div class="top-bar">
	    <div class="avatar"><p>V</p></div>
	    <div class="name">Voldemort</div>
	    <div class="icons">
	      <i class="fas fa-phone"></i>
	      <i class="fas fa-video"></i>
	    </div>
	    <div class="menu">
	      <div class="dots"></div>
	    </div>
	  </div>
	  <div class="middle">
	    <div class="voldemort">
	      <div class="incoming" id="toUser">
	        <div class="bubble"><span>Hey, Father's Day is coming up..</span></div>
	        <div class="bubble"><span>What are you getting.. Oh, oops sorry dude.</span></div>
	      </div>
	      <div class="outgoing" id="fromUser">
	        <div class="bubble lower"><span>Nah, it's cool.</span></div>
	        <div class="bubble"><span>Well you should get your Dad a cologne. Here smell it. Oh wait! ...</span></div>
	      </div>
	      <div class="typing">
	        <div class="bubble">
	          <div class="ellipsis one"></div> 
	          <div class="ellipsis two"></div>
	          <div class="ellipsis three"></div>
	        </div>
	      </div>
	    </div>
	  </div>
	  <div class="bottom-bar">
	    <div class="chat">
	      <input type="text" id="message" />
	      <button type="button" id="sendBtn">전송</button>
	    </div>
	  </div>
	</div>
</body>
</html>