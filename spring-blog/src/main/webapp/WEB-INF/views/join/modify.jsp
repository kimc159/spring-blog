<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %> 
<div class="formArea">
	<h2>MEMBER JOIN</h2> 
	
	<form action="/join/modifyOk" method="post">
		<input type="hidden" id="memId" name="memId" title="ID" maxlength="20" value="${member.memId}">
		<div class="join_content">
			<div class="input_area">
		        <h3 class="join_title"><label for="memId">아이디</label></h3>
		            <span class="ps_box ">
						${member.memId}
		            </span>
		     </div>
			<div class="input_area">
		        <h3 class="join_title"><label for="memPassword1">패스워드</label></h3>
		            <span class="ps_box"> 
						<input type="password" id="memPassword1" name="memPassword" class="int" title="password" maxlength="20">
		            </span>
		        <div class="error_next_box" id="memPassword1Msg" style="" aria-live="assertive">필수 정보입니다.</div>
		     </div>
			 <div class="input_area">
		        <h3 class="join_title"><label for="memPassword2">패스워드 확인</label></h3>
		            <span class="ps_box ">
						<input type="password" id="memPassword2" name="memPassword2" class="int" title="password확인" maxlength="20">
		            </span>
		        <div class="error_next_box" id="password2Msg" style="" aria-live="assertive">필수 정보입니다.</div>
		     </div>
			<div class="input_area">
		        <h3 class="join_title"><label for="memName">이름</label></h3>
		            <span class="ps_box ">
						<input type="text" id="memName" name="memName" class="int" title="이름"maxlength="20" value="${member.memName }">
		            </span>
		        <div class="error_next_box" id="password2Msg" style="" aria-live="assertive">필수 정보입니다.</div>
		     </div>
		     <div class="input_area join_birthday">
			    <h3 class="join_title">
			        <label for="yy">생년월일</label>
			    </h3>
			    <div class="bir_wrap">
			        <div class="bir_yy"> 
			            <span class="ps_box">
			                <input type="text" id="yy" placeholder="년(4자)" aria-label="년(4자)" class="int" name="memBirthDay" value="${member.memBirthDay}">
			            </span>
			        </div>
			    </div>
			    <div class="error_next_box" id="birthdayMsg" style="" aria-live="assertive">태어난 년도 4자리를 정확하게 입력하세요.</span>
			</div>
			<div class="input_area join_sex">
                <h3 class="join_title"><label for="memGender">성별</label></h3>
                <div class="ps_box gender_code">
                    <select id="memGender" name="memGender" class="sel" aria-label="성별">
                        <option value="" >성별</option>
                                <option value="M" <c:if test="${member.memGender == 'M'}"> selected</c:if>>남자</option> 
                                <option value="F" <c:if test="${member.memGender == 'F'}"> selected</c:if>>여자</option> 
                    </select>
                </div>
            </div>
            <div class="input_area join_email">
			    <h3 class="join_title">
			        <label for="memEmail">본인 확인 이메일<span class="terms_choice">(선택)</span>
			        </label>
			    </h3>
			    <span class="ps_box int_email box_right_space">
			        <input type="text" id="memEmail" name="memEmail" placeholder="선택입력" aria-label="선택입력" class="int" maxlength="100" value="${member.memEmail}">
			    </span>
			</div> 
			<div class="input_area">
			    <h3 class="join_title">
			        <label for="memPhone">휴대전화</label>
			    </h3>
			    <span class="ps_box box_right_space">
			        <input type="tel" id="memPhone" name="memPhone" title="휴대전화" class="int" maxlength="40" value="${member.memPhone}">
			    </span>
			    <div class="error_next_box" id="phoneMsg" style="display:none" aria-live="assertive"></span>
			</div>
			<div class="btnArea">
                <button type="submit" id="btnJoin" class="btn_type btn_primary"><span>수정하기</span></button>
            </div>
     	</div>
	</form> 
</div>
</body>
</html>