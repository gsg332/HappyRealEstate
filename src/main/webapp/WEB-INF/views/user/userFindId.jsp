<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<body>
<script type="text/javascript">
	function menu(url){
		$("#menuFrm").attr("action", url).submit();
	}
	function navTab(){
		$("#findTab a:last").tab('show');
	}
</script>
	<c:choose>
		<c:when test="${result eq 'success'}">
			<div class="panel panel-default">
				<div class="panel-body">
					<strong>아이디 찾기</strong>
					<br><br>
					 고객님의 정보와 일치하는 아이디 입니다
				</div>
				<div class="panel-footer" style="background:url(/resources/images/bg_sub_title1.gif)">
					<table class="table" style="margin-bottom:0">
						<tbody>
							<tr>
								<td class="th" width="16%" >아이디</td>
								<td width="84%" height="51">${userId}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<button type="button" onclick="menu('/user/find.do')" class="btn btn-default" style="width:49%; height:40px; font-weight:bold; color:#336699; font-size:14px">확인</button>
			<button type="button" onclick="navTab()" class="btn btn-primary" style="width:49%; float:right; height:40px; font-weight:bold; font-size:14px">비밀번호 찾기</button>
		</c:when>
		<c:otherwise>
			<div class="panel panel-default">
				<div class="panel-body">
					<strong>아이디 찾기</strong>
					<br><br>
					 고객님의 정보와 일치하는 아이디가 없습니다.
				</div>
			</div>
			<button type="button" onclick="menu('/user/find.do')" class="btn btn-default" style="width:100%;height:40px; font-weight:bold; font-size:14px">확인</button>
		</c:otherwise>
	</c:choose>
</body>
</html>