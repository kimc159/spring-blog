
// 아이디 찾기
function findId() {
	var findEmail = $("#findEmail").val();
	
	$.ajax({ 
		url: "./findId",
		data: {
			email : findEmail
		},
		type:"post",
		dayaType:"json",
		success : function(rs) {
			var result = rs.result;
			if(result !== null) { 
				alert("아이디는 : " + result + " 입니다.");
			} else {
				alert("아이디가 없습니다.");
			}
		},
		error : function(error) {
			console.log(error);
		}
	})
}

// 비밀번호 찾기
function findPassword() {
	var findId = $("#findId").val();
	var findEmail2 = $("#findEmail2").val();
	
	$.ajax({
		url: "./findPassword",
		data: {
			id : findId,
			email : findEmail2
		},
		type:"post",
		dayaType:"json",
		success : function(rs) {
			console.log(rs);
			var result = rs.result;
			if(result !== null && result !== "-1") {
				alert("임시 비밀번호 : " + result + " 입니다.");
			} else {
				alert("아이디와 이메일을 정확히 입력해주세요.");
			}
		},
		error : function(error) {
			console.log(error);
		}
	})
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
                	alert("이메일 인증이 완료되지 않았습니다.");
                } else if(rs.result === 1) {
                	alert("로그인이 성공하엿습니다."); 
                	location.href="/board/list";
                } else if(rs.result === 0){
                	alert("비밀번호가 일치하지 않습니다.");
                } else if(rs.result === -3){
                	alert("아이디 또는 패스워드를 입력해주세요.");
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
// 아이디 중복 체크
function idCheck() {
	var idText = $("#memId").val();
	
	if($("#memId").val() === "") {
		alert("아이디를 입력해주세요.");
		return false;
	}
	$.ajax( 
        {
			url:"./idCheck",
            data : {id:idText},              /*html, text, json, xml, script*/
            type : 'post', 
            dataType : 'json',
            success : function(rs){ 
				
                if(rs.result === 1) {
                	alert("이미 존재하는 아이디입니다.");
                } else {
                	alert("사용할수 있는 아이디입니다.");
                }
            },
            error : function(xhr, status, error){
            }
        }
    );
}

function removeFile(thisobj) {
	var $this = $(thisobj);
	
	$this.parent(".uploaded_file").css("display", "none").closest(".boardInputArea").find(".select_file").css("display", "block"); 
}
