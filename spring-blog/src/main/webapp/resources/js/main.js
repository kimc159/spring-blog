
// 아이디 찾기
function findId() {
	
	var email = $("#findEmail").val();
	
	$.ajax( 
        {
			url:"./login/findId",
            data : { 
            	email: email
            	},              /*html, text, json, xml, script*/
            type : 'post', 
            dataType : 'json',
            success : function(rs){
                console.log("success"); 
                console.log(rs); 
                if(rs.id == null) {
					alert("아이디가 없습니다.");
                } else {
	                alert("아이디는 " + rs.id + " 입니다.");
                }
            },
            error : function(xhr, status, error){
            	console.log(xhr);
            	console.log(status);
            	console.log(error);
            }
        }
    );
}

// 비밀번호 찾기
function findPassword() {

	var id = $("#findId").val();
	var email = $("#findEmail2").val();
	
	$.ajax( 
     {
		 url:"./login/findPassword",
         data : { 
          	id: id,
         	email: email
         	},              /*html, text, json, xml, script*/
         type : 'post', 
         dataType : 'json',
         success : function(rs){
             console.log("success"); 
             console.log(rs); 
             if(rs.result === "1") {
            	 alert("이메일로 임시 비밀번호가 발급되었습니다.");
             	location.href="/login";
             } else if(rs.result === null) {
            	 alert("회원이 없습니다.");
             } else {
            	 alert("데이터베이스 오류가 발생했습니다.");
             }
         },
         error : function(xhr, status, error){
         	console.log(xhr);
         	console.log(status);
         	console.log(error);
         }
     }
 );
}

// 로그인
function login() {
	var memId = $("#memId").val();
	var memPassword = $("#memPassword").val();
	var rememberId = $("#rememberId").is(":checked");
	
	$.ajax( 
        {
			url:"./login/loginOk",
            data : { 
            	memId: memId,
            	memPassword: memPassword,
            	rememberId: rememberId
            	},              /*html, text, json, xml, script*/
            type : 'post', 
            dataType : 'json',
            success : function(rs){
                if(rs.result === -2) {
                	alert("이메일 인증이 안료되지 않았습니다.");
                } else if(rs.result === 1) {
                	alert("로그인이 성공하엿습니다."); 
                	location.href="/main";
                } else if(rs.result === 0){
                	alert("비밀번호가 일치하지 않습니다.");
                } else {
                	alert("존재하지 않는 아이디 입니다.");
                }
            },
            error : function(xhr, status, error){
            	console.log(xhr);
            	console.log(status);
            	console.log(error);
            }
        }
    );
}
// 아이디 찾기 팝업 열기
function popFindId() {
	$(".popFindId").addClass("on");
}
// 비밀번호 찾기 팝업 열기
function popFindPassword() {
	$(".popFindPassword").addClass("on");
}
// 공통 레이어 팝업 닫기
function popClose(thisobj){
	var $this = $(thisobj);
	
	$this.closest(".popCon").removeClass("on"); 
	
}
