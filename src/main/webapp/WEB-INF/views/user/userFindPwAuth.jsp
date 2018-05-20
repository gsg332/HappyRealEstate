<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	$('#pwSmsAuthChk').val("false");
	
});
	
</script>
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
						<td class="th">아이디</td>
						<td>
							<span>${userId}</span>
							<input type="hidden" id="pwUserId" name="pwUserId" value="${userId}" class="form-control">
						</td>
					</tr>
					<tr>
						<td class="th">이름</td>
						<td>
							<input type="text" id="pwUserNm" name="pwUserNm" class="form-control">
						</td>
					</tr>
					<tr>
						<td rowspan="2" class="th1">휴대폰 번호</td>
						<td>
							<input type="hidden"  id="pwSmsAuthNo" name="pwSmsAuthNo" class="form-control" >
							<input type="text"  id="pwPhoneNum" name="pwPhoneNum" class="form-control">
							<button type="button" class="btn btn-default" onclick="sendSmsAuthNo('pw')">인증번호 전송</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" class="form-control" id="pwUserSmsAuthNo" name="pwUserSmsAuthNo">
							<input type="hidden" class="form-control" id="pwSmsAuthChk" name="pwSmsAuthChk" value="false">
							<button type="button" class="btn btn-default" onclick="chkPwSmsAuthNo()">확인</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<button type="button" onclick="moveFindPwAuth()" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; color:#336699; font-size:14px">다음</button>
</body>
</html>