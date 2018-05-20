<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		if ("${PASSWD_EXPIRED}" == "Y"){
			$(".navbar-nav").css("display", "none");
		}
		
		// Form Validate Check
		$("#modifyFrm").validate({
			submitHandler : function(){
				modifyUserInfo();
			},
			rules : {
				password : {
					minlength: 10,
					mix_eng_num : true
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
				}			
			},
			messages : {
				password : {
					minlength: $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
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
				}			
			}
		});
			
	    $.validator.addMethod("mix_eng_num", function (value, element) {
	        return this.optional(element) || /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(value);
	    });
	    
	    $('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			if(type == 'userModify'){
				$this.find('.modal-title').text("개인정보 수정 확인");
				$this.find('.pop_title').text("개인정보를 수정하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$("#modifyFrm").ajaxSubmit({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						success : function(data) {
							if (data.result == "SUCCESS"){
								if("${FN_CONFIG_LIST['USER_MODIFY_APPROVE_ADMIN']['Value']}" == "Y"){
									alert("관리자에게 개인정보수정을 요청하셨습니다.");									
								}else{
									alert("개인정보 변경이 승인되었습니다.");
								}
							} else {
								alert("개인정보 수정이 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
							$(location).attr('href',"/user/modify.do");
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'userLeave'){
				$this.find('.modal-title').text("회원 탈퇴 확인");
				$this.find('.pop_title').text("회원 탈퇴를 진행하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						data : {
							"userId" : $("#userId").val(),
							"permit" : 0,
							"expired" : "Y"
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("회원 탈퇴가 완료되었습니다.");
								$(location).attr('href',"j_spring_security_logout");
							} else {
								alert("회원 탈퇴를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}
	    });
	});		
	
	function modifyUserInfo(){
		$('#confirmModal').data('type', 'userModify');
		$('#confirmModal').modal('show');
	}
	
	function appLeaveUser(){
		$('#confirmModal').data('type', 'userLeave');
		$('#confirmModal').modal('show');
	}
</script>
<!-- Content Area -->
<div class="container">
	<div class="row" style="margin-top:81px;">
		<!--left menu start-->
		<div class="col-md-2">
			<div class="slnb list-group">
				<div class="slnb_title3"></div>
				<a href="<c:url value='/user/modify.do' />" class="list-group-item  active">개인정보수정</a>
				<a href="<c:url value='/changeReq/list.do' />" class="list-group-item">변경요청내역</a>
			</div>
		</div>
		<!--content start-->
		<div class="col-md-10">
			<blockquote><p><strong>개인정보수정</strong></p></blockquote>
			<form class="form-inline" id="modifyFrm" name="modifyFrm" method="post">
				<input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
				
				<table class="table">
					<tbody>
						<tr>
							<td class="th">아이디</td>
							<td>${userInfo.userId}</td>
						</tr>
						<tr>
							<td class="th">비밀번호</td>
							<td>
								<span>
									<input type="password" id="password" name="password" class="form-control" style="width: 200px;" value="">&nbsp;&nbsp;&nbsp;비밀번호 만료일 : ${userInfo.passwordLimitDate}
								</span>
							</td>
						</tr>
						<tr>
							<td class="th">소속 / 과 / 계</td>
							<td>
								<span>
									<input type="text" id="position" name="position" class="form-control" style="width: 200px;" value="${userInfo.position}">&nbsp;&nbsp;/&nbsp;
								</span>
								<span>
									<input type="text" id="depart" name="depart" class="form-control" style="width: 200px;" value="${userInfo.depart}">&nbsp;&nbsp;/&nbsp;
								</span>
								<span>
									<input type="text" id="team" name="team" class="form-control" style="width: 200px;" value="${userInfo.team}">
								</span>
							</td>
						</tr>
						<tr>
							<td class="th">이름</td>
							<td>
								<span>
									<input type="text" id="userName" name="userName" class="form-control" style="width: 200px;" value="${userInfo.userName}">
								</span>
							</td>
						</tr>
						<tr>
							<td class="th" style="border-bottom:#e9e9e9">연락처</td>
							<td>
								<span>
									<input type="text" id="phoneNum" name="phoneNum" class="form-control" style="width: 200px;" value="${userInfo.phoneNum}">
								</span>
							</td>
						</tr>
						<!--tr>
							<td class="th" style="border-bottom:#e9e9e9">수정사유</td>
							<td><input type="text" class="form-control w100"></td>
						</tr-->
						<tr>
							<td colspan="2" style="background:#fff; text-align:right">
								<button type="submit" class="btn btn-default">변경요청</button>
								<button type="button" class="btn btn-default" onclick="appLeaveUser()">탈퇴신청</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
