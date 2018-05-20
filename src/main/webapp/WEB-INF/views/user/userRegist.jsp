<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		var smsUse = false;
		if ($('#smsUse').val() == '0') {
			smsUse = true;
		}

		// Form Validate Check
		$("#registUserFrm").validate({
			submitHandler : function(){
				registUserApply();
			},
			rules : {
				userId : {
					required : true,
					minlength: 5
				},
				password : {
					required : true,
					minlength: 10,
					mix_eng_num : true
				},
				chk_password : {
					required : true,
					equalTo: "#password"
				},
				position : {
					required : true
				},
				depart : {
					required : true
				},
				team : {
					required : true
				},
				userName : {
					required : true
				},
				phoneNum : {
					required : true,
					number: true
				},	
				idChk : {
					required : true
				},
				smsAuthNo : {
					required : smsUse
				},
				pledgeFileNm : {
					required : '${pledgeFileRegType}' == '2' ? true : false
				}
			},
			messages : {
				userId : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("문자 또는 문자∙숫자를 조합해서 {0}자리 이상 입력해주세요.")
				},
				password : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
				},
				chk_password : {
					required : "필수 입력 항목 입니다.",
					equalTo: "비밀번호가 일치하지 않습니다."
				},
				position : {
					required : "필수 입력 항목 입니다."
				},
				depart : {
					required : "필수 입력 항목 입니다."
				},
				team : {
					required : "필수 입력 항목 입니다."
				},
				userName : {
					required : "필수 입력 항목 입니다."
				},
				phoneNum : {
					required : "필수 입력 항목 입니다.",
					number: "연락처는 숫자로 입력하셔야 합니다."
				},
				idChk : {
					required : "아이디 중복확인을 해주세요."
				},
				smsAuthNo : {
					required : "SMS 인증을 해주세요."
				},
				pledgeFileNm : {
					required : "필수 입력 항목 입니다."
				}
			}
		});
		
		/* 보안서약서 파일 첨부 */
		$("#pledgeFile").change(function(event){
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#pledgeFileNm").val(fileInfo);
		});
	});
	
	function registUserApply(){
	
		$("#registUserFrm").ajaxSubmit({
			type : 'post',
			url : '/user/regist.json',
			dataType: 'json',
			success : function(data) {
				
				if (data.result == "SUCCESS"){
					alert("회원가입이 신청되었습니다.");
				} else {
					alert("회원가입 신청을 실패하였습니다. \n관리자에게 문의해 주세요.");
				}					
				$(location).attr('href',"/login.do");
			
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	
	}
	
	function idDuplChk(){
		
		if ($("#userId").val() == ''){		
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : '/check/idDupli.json',
			dataType: 'json',
			data : {
				"userId" : $("#userId").val()
			},	
			success : function(data) {
				
				if (data.result == "used"){
					alert("이미 사용중인 아이디입니다.");
					$("#userId").val('').focus();
					$("#idChk").val('');
				} else {
					alert("사용가능한 아이디입니다.");
					$("#idChk").val($("#userId").val());
					$("#idChk").parent().data("title", "").removeClass("error").tooltip("destroy");
				}				
			
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function sendSmsAuthNo(type){
		if ($("#phoneNum").val() == ''){
			alert("전화번호를 입력한 후 인증받으시기 바랍니다.");
			$("#phoneNum").val('').focus();
			return false;
		}
		$.ajax({
			type : 'post',
			url : '/sms/auth/create.json',
			dataType : 'json',
			data : {
				userId : $("#userId").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(data) {
				//console.log(data.authNo);
				$("#smsAuthNo").val(data.authNo);
				if (type == 'new'){
					$("#smsModal").modal('toggle');
				} 
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function chkSmsAuthNo(){
		if ($("#smsAuthNo").val() == $("#userSmsAuthNo").val()){
			alert("인증이 성공하였습니다.");
			$(".sms-auth").text('인증완료').removeClass('text-danger').addClass('text-success');
			$("#smsModal").modal('hide');
		} else {
			$("#smsFailAlert").css("visibility", "visible");
		}
	}

</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container position_r">
			<!--div class="fnb">
				<a href="#" data-toggle="modal" data-target="#loginModal">
					<img src="/resources/images/ico_user_login.png" width="32" height="32" alt="로그인"/>
				</a>
				<a href="/user/regist.do">
					<img src="/resources/images/ico_group_add.png" width="45" height="32" alt="회원가입"/>
				</a>
			</div-->
			 <div class="navbar-header">
				<!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> 
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
				</button> -->
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
					<a href="/user/regist.do" class="list-group-item  active">회원가입</a>
					<c:choose>
					<c:when test="${SMS_USE eq 0}">
						<a href="/user/find.do" class="list-group-item">ID&sdot;PW 찾기</a>
					</c:when>
					</c:choose>
				</div>
		    </div>
			<div class="col-md-10">
				<blockquote><p><strong>회원가입</strong></p></blockquote>
				<form class="form-inline" id="registUserFrm" name="registUserFrm" method="post">
				<input type="hidden" id="level" name="level" value=0 > 
					<table class="table">
						<tbody>
							<tr>
								<td class="th">아이디</td>
								<td>
									<span>
										<input type="text" id="userId" name="userId" class="form-control" style="width: 150px;ime-mode: disabled;">
									</span>
									<span style="position: relative; left:35px;">
										<input type="text" id="idChk" name="idChk" class="form-control" style="width: 0px; position: absolute; left: 10px; visibility: hidden; top: -33px;">
									</span>
									<button type="button" id="btn_idchk" class="btn btn-default" onclick="idDuplChk()">중복확인</button>&nbsp;
									<span class="text-info">문자 또는 문자∙숫자를 조합해서 5자리 이상 입력해주세요</span>
								</td>
							</tr>
							<tr>
								<td class="th">비밀번호</td>
								<td>
									<span>
										<input type="password" id="password" name="password" class="form-control" style="width: 150px;"/>&nbsp;&nbsp;
									</span>
									<span class="text-info">영문자 및 숫자를 포함해서 10자리 이상 입력해주세요</span>
								</td>
							</tr>
							<tr>
								<td class="th">비밀번호 확인</td>
								<td>
									<span>
										<input type="password" id="chk_password" name="chk_password" class="form-control" style="width: 150px;">&nbsp;&nbsp;
									</span>
									<span class="text-info">비밀번호 확인을 위해 한번 더 동일하게 입력해주세요</span>
								</td>
							</tr>
							<tr>
								<td class="th">소속 / 과 / 계</td>
								<td>
									<span>
										<input type="text" id="position" name="position" class="form-control" style="width: 150px;">&nbsp;&nbsp;/&nbsp;&nbsp;
									</span>
									<span>
										<input type="text" id="depart" name="depart" class="form-control" style="width: 150px;">&nbsp;&nbsp;/&nbsp;&nbsp;
									</span>
									<span>
										<input type="text" id="team" name="team" class="form-control" style="width: 150px;">
									</span>	
								</td>
							</tr>
				            <tr>
				            	<td class="th">이름</td>
				            	<td>
				            		<span>
				            			<input type="text" id="userName" name="userName" class="form-control" style="width: 150px;">
				            		</span>
				            	</td>
				            </tr>
				            <tr>
					            <td class="th">연락처</td>
					            <td>
					            	<span>
					            		<input type="text" id="phoneNum" name="phoneNum" class="form-control" style="width: 150px;">
					            	</span>
					            	<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
					            		<c:if test="${list.itemName eq 'SMS_USE' }">
					            			<input type="hidden" id="smsUse" name="smsUse" value="${list.itemValue}">
					            			<c:if test="${list.itemValue eq '0' }">
					            				<input type="text" id="smsAuthNo" name="smsAuthNo" class="form-control" style="width:0px;visibility:hidden">
					            				<button type="button" class="btn btn-default" onclick="sendSmsAuthNo('new')">SMS 인증</button>
					            				<span class="text-info text-danger sms-auth" style="margin-left: 10px;">*SMS 인증 상태가 아닙니다.</span>
					            			</c:if>
					            		</c:if>
									</c:forEach>
					            </td>
				            </tr>
				            <c:if test="${pledgeFileRegType == '2'}">
								<tr>
									<td class="th">
										보안서약서
									</td>
									<td>
										<div class="input-group">
											<span class="input-group-btn">
												<span class="btn btn-default btn-file">
													보안서약서 첨부 <input type="file" id="pledgeFile" name="pledgeFile">
												</span>
											</span>
										</div>
										<input type="text" style="width: 500px;" class="form-control" id="pledgeFileNm" name="pledgeFileNm" readonly>
									</td>
								</tr>
							</c:if>
				            <tr>
				            	<td colspan="2" class="white text-center" style="padding:10px 0px">
				            		<button type="submit" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">회원가입</button>
				            	</td>
				            </tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- SMS 인증 Modal -->
	<div class="modal fade in" id="smsModal" tabindex="-1" role="dialog" aria-labelledby="smsModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="smsModalLabel">SMS 인증</h4>
	      </div>
	      <div class="modal-body">
	        <div style="width:570px;">
		    <div class="pop_title">SMS 인증</div>
		    <div class="box_gray">SMS로 전송된 인증번호 6자리를 입력해주세요.<br><br>
		      <input type="text" class="form-control" id="userSmsAuthNo" name="userSmsAuthNo">
		      <button type="submit" class="btn btn-default" onclick="chkSmsAuthNo()">확인</button>
		      <button type="submit" class="btn btn-default" onclick="sendSmsAuthNo('re')">재전송</button>
		    </div>
		    <div class="alert alert-info" id="smsFailAlert" role="alert" style="margin:10px; visibility: hidden;">인증이 실패하였습니다. 인증번호를 확인해주세요.</div>
		  </div>
	      </div>
	    </div>
	  </div>
	</div>	

</body>
</html>
