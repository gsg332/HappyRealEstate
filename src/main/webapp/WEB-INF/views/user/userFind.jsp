<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	var htmlid,htmlpw;
	$(document).ready(function(){
		idhtml = $("#id").html();
		pwhtml = $("#pw").html();
		$('#smsAuthChk').val("false");
	});
	
	//html 초기화
	function htmlid(){
		$('#id').html(idhtml);
	}
	function htmlpw(){
		$('#pw').html(pwhtml);
	}
	
	function moveFindId(){
		//id
		if ($("#userNm").val() == ''){
			alert("이름을 입력해  주세요.");
			$("#userNm").val('').focus();
			return;
		}
		
		if ($('#smsAuthChk').val() != "success"){
			alert("휴대폰 인증을 확인해 주세요.");
			return;
		}

		$.ajax({
			type : 'post',
			url : '/sms/auth/findIdUser.json',
			dataType : 'html',
			data : {
				userName : $("#userNm").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(data) {
				$("#id").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function moveFindPw(){
		if ($("#userId").val() == ''){
			alert("아이디를 입력해  주세요.");
			$("#userId").val('').focus();
			return;
		}
		
		$.ajax({
			type : 'post',
			url : '/sms/auth/findPwUser.json',
			dataType : 'html',
			data : {
				userId : $("#userId").val()
			},
			success : function(data) {
				$("#pw").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function moveFindPwAuth(){
		//pw
		if ($("#pwUserNm").val() == ''){
			alert("이름을 입력해  주세요.");
			$("#pwUserNm").val('').focus();
			return;
		}
		
		if ($('#pwSmsAuthChk').val() != "success"){
			alert("휴대폰 인증을 확인해 주세요.");
			return;
		}
	
		$.ajax({
			type : 'post',
			url : '/sms/auth/findPwAuthUser.json',
			dataType : 'html',
			data : {
				userId : $("#pwUserId").val(),
				userName : $("#pwUserNm").val(),
				phoneNum : $("#pwPhoneNum").val()
			},
			success : function(data) {
				$("#pw").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function moveFindPwReplace(){
		
		if ($('#password').val() != $('#passwordSub').val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#passwordSub").val('').focus();
			return false;
		}
		
		$("#modifyPwFrm").ajaxSubmit({
			type : 'post',
			url : '/sms/auth/findPwReplaceUser.json',
			dataType : 'json',
			success : function(data) {
				if (data.result == "SUCCESS") {
					alert('수정완료 되었습니다.\n관리자 승인 후 로그인 할 수 있습니다.');
					$("#menuFrm").attr("action", "/login.do").submit();
				} else {
					alert('문제 발생');
					//console.log(data);
				}
				
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function sendSmsAuthNo(gb){
		
		var phoneNum,smsAuthNo;
		
		if (gb == 'id') {
			phoneNum = $("#phoneNum");
			smsAuthNo = $("#smsAuthNo");
		} else {
			phoneNum =  $("#pwPhoneNum");
			smsAuthNo = $("#pwSmsAuthNo");
		}
		if (phoneNum.val() == ''){
			alert("전화번호를 입력한 후 인증받으시기 바랍니다.");
			phoneNum.val('').focus();
			return;
		}
		
		$.ajax({
			type : 'post',
			url : '/sms/auth/create.json',
			dataType : 'json',
			data : {
				userId : "userFind",
				phoneNum : phoneNum.val()
			},
			success : function(data) {
				console.log("bg"+gb+"///"+data.authNo);
				smsAuthNo.val(data.authNo);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function chkSmsAuthNo(){
		if ($("#smsAuthNo").val() == $("#userSmsAuthNo").val()){
			alert("인증이 성공하였습니다.");
			$('#smsAuthChk').val("success");
		} else {
			alert('인증이 실패하였습니다. 인증번호를 확인해주세요.');
			$('#smsAuthChk').val("false");
		}
	}
	
	function chkPwSmsAuthNo(){
		if ($("#pwSmsAuthNo").val() == $("#pwUserSmsAuthNo").val()){
			alert("인증이 성공하였습니다.");
			$('#pwSmsAuthChk').val("success");
		} else {
			alert('인증이 실패하였습니다. 인증번호를 확인해주세요.');
			$('#pwSmsAuthChk').val("false");
		}
	}

</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container position_r">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> 
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
				</button>
				<a class="navbar-brand" href="/login.do"><img src="/resources/images/logo.png" width="193" height="30" alt=""/></a> 
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-2">
				<div class="slnb list-group">
					<div class="slnb_title4"></div>
					<a href="/user/regist.do" class="list-group-item">회원가입</a>
					<a href="/user/find.do" class="list-group-item active">ID&sdot;PW 찾기</a>
				</div>
		    </div>
			<div class="col-md-10">
				<blockquote><p><strong>아이디&sdot;비밀번호 찾기</strong></p></blockquote>
				<form class="form-inline">
					<!-- Nav tabs -->
					<div>
						<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px" id="findTab">
							<li role="presentation" class="active" onclick="htmlid()"><a href="#id" aria-controls="id" role="tab" data-toggle="tab">아이디 찾기</a></li>
							<li role="presentation" onclick="htmlpw()"><a href="#pw" aria-controls="pw" role="tab" data-toggle="tab">비밀번호 찾기</a></li>
						</ul>
						<div class="tab-content">
							<!--아이디 탭 시작-->
							<div role="tabpanel" class="tab-pane fade in active" id="id">
								<div class="panel panel-default">
									<div class="panel-body"> 
										<strong>회원정보에 등록한 휴대전화로 인증</strong>
										<br><br>
										<span class="text-info">회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가 같아야 인증번호를 받을 수 있습니다</span> 
									</div>
									<div class="panel-footer" style="background:url(/resources/images/bg_sub_title1.gif)">
										<table class="table" style="margin-bottom:0">
											<tbody>
												<tr>
													<td class="th">이름</td>
													<td><input type="text" id="userNm" name="userNm" class="form-control"></td>
												</tr>
												<tr>
													<td rowspan="2" class="th1">휴대폰 번호</td>
													<td>
														<input type="hidden" id="smsAuthNo" name="smsAuthNo" class="form-control" >
														<input type="text" id="phoneNum" name="phoneNum" class="form-control">
														<button type="button" class="btn btn-default" onclick="sendSmsAuthNo('id')">인증번호 전송</button>
													</td>
												</tr>
												<tr>
													<td>
														<input type="text" class="form-control" id="userSmsAuthNo" name="userSmsAuthNo">
														<input type="hidden" class="form-control" id="smsAuthChk" name="smsAuthChk" value="false">
														<button type="button" class="btn btn-default" onclick="chkSmsAuthNo()">확인</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<button type="button" onclick="moveFindId()" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">다음</button>
							</div>
							<!--비밀번호 탭내용시작-->
							<div role="tabpanel" class="tab-pane fade" id="pw">
								<div class="panel panel-default">
									<div class="panel-body"> 비밀번호를 찾고자 하는 아이디를 입력해 주세요</div>
									<div class="panel-footer" style="background:url(/resources/images/bg_sub_title1.gif)">
										<table class="table" style="margin-bottom:0">
											<tbody>
												<tr>
													<td class="th">아이디</td>
													<td><input type="text" id="userId" name="userId" class="form-control" style="ime-mode: disabled;"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<button type="button" onclick="moveFindPw()" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">다음</button>
							</div>
							<!--비밀번호 탭내용 END-->
						</div>
					</div>
					<!--Nav tabs END-->
				</form>
			</div>
		</div>
	</div>
<form id="menuFrm" name="menuFrm" method="post">
</form>
</body>
</html>
