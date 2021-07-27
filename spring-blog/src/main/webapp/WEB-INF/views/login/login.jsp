<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<body>
	<c:if test="${not empty cookie.rememberID}">
		<c:set var="checked" value="checked" />
	</c:if>
	<div id="loginWrap">
		<!-- login -->
		<div class="login">
			<h2 class="title">로그인</h2>
			<form name="loginForm" id="loginForm" method="post">
				<!-- input -->
				<div class="login_input">
					<div class="input_area">
						<label for="id"><input type="text" name="memId" id="memId"
							value="${cookie.rememberID.value}" onkeyup="noSpaceForm(this)"
							onchange="noSpaceForm(this)" placeholder="아이디" /></label>
					</div>
					<div class="input_area">
						<label for="pw"><input type="password" name="memPassword"
							id="memPassword" value="" onkeyup="noSpaceForm(this)"
							onchange="noSpaceForm(this)" placeholder="비밀번호" /></label>
					</div>
					<div class="btn_area">
						<button type="button" id="loginBtn" class="btn_common btn_primary"
							onClick="login()">로그인</button>
						<a href="/join" class="btn_common btn_gray">회원가입</a>
					</div>
				</div>
				<div class="login_btn_area">
					<div class="save_id">
					<label class="check_container typeInspect" for="rememberId">아이디저장
	                     <input type="checkbox" name="rememberId" id="rememberId"
                            ${checked}>
	                     <span class="checkmark"></span>
	                 </label>
					</div>
					<div class="find_area">
						<button type="button" class="" onClick="popFindId()">아이디찾기</button>
						/
						<button type="button" class="" onClick="popFindPassword()">비밀번호찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="pop" class="popFindId popCon">
		<div class="pop_in">
			<h2 class="title">아이디 찾기</h2>
			<div class="input_wrap">
				<label for="findEmail">Email</label> <input type="text"
					id="findEmail">
			</div>
			<div class="btn_area">
				<button class="btn_common type_full" onClick="findId()">find</button>
				<button class="btn_common btn_gray" onClick="popClose(this)">cancel</button>
			</div>
		</div>
	</div>
	<div id="pop" class="popFindPassword popCon">
		<div class="pop_in">
			<h2 class="title">비밀번호 찾기</h2>
			<div class="input_wrap">
				<label for="findId">ID</label> <input type="text" id="findId">
			</div>
			<div class="input_wrap">
				<label for="findEmail">Email</label> <input type="text"
					id="findEmail2">
			</div>
			<div class="btn_area">
				<button class="btn_common type_full" onClick="findPassword()">find</button>
				<button class="btn_common btn_gray" onClick="popClose(this)">cancel</button>
			</div>
		</div>
	</div>
</body>
</html>