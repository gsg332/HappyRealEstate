<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<body>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// Form Validate Check
		$("#modifyPwFrm").validate({
			submitHandler : function(){
				moveFindPwReplace();
			},
			rules : {
				password : {
					required : true,
					minlength: 10,
					mix_eng_num : true
				},
				passwordSub : {
					required : true,
					minlength: 10,
					mix_eng_num : true
				}
			},
			messages : {
				password : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
				},
				passwordSub : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
				}
			}
		});
		$.validator.addMethod("mix_eng_num", function (value, element) {
			return this.optional(element) || /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(value);
		});
	});
	
	function menuPw(url){
		$("#menuFrm").attr("action", url).submit();
	}
</script>
	<c:choose>
		<c:when test="${result eq 'success'}">
			<form class="form-inline" id="modifyPwFrm" name="modifyPwFrm" method="post">
			<div class="panel panel-default">
				<div class="panel-body">
					<strong>비밀번호 재설정</strong>
					<br><br>
					<span class="text-info">새로 사용할 비밀번호를 입력해 주세요</span> 
				</div>

				<div class="panel-footer" style="background:url(/resources/images/bg_sub_title1.gif)">
					<table class="table" style="margin-bottom:0">
						<tbody>
							<tr>
								<td class="th">아이디</td>
								<td>
									${userId}
									<input type="hidden" id="userId" name="userId" value="${userId}" class="form-control">
								</td>
								
							</tr>
							<tr>
								<td rowspan="2" class="th1">비밀번호</td>
								<td>
									<input type="password" id="password" name="password" class="form-control">
									&nbsp;&nbsp;<span class="text-info">영문자 및 숫자를 포함해서 10자리 이상 입력해주세요</span>
								</td>
							</tr>
							<tr>
								<td>
									<input type="password" id="passwordSub" name="passwordSub"  class="form-control">
									&nbsp;&nbsp;<span class="text-info">비밀번호 확인을 위해 한번 더 동일하게 입력해주세요</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
			</div>
			<button type="submit" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">확인</button>
			</form>
		</c:when>
		<c:otherwise>
			<div class="panel panel-default">
				<div class="panel-body">
					<strong>비밀번호 재설정</strong>
					<br><br>
					 고객님의 정보와 일치하는 아이디가 없습니다.
				</div>
			</div>
			<a href="javascript:menuPw('/user/find.do')">
				<button type="button" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">확인</button>
			</a>
		</c:otherwise>
	</c:choose>
</body>
</html>