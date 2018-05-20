<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<!--pop width 800px-->
	<div align="center" style="text-align: center;width:100%;">
		<div class="pop_title" style="margin-top:40px;margin-bottom:10px">접속 실패</div>
		<table class="table table-hover table_b" style="width:780px; margin:0 auto; border-bottom:1px dashed #ccc">
			<thead>
				<tr>
					<th colspan="2" class="text-info">
						 <%-- 오류명 : ${SimpleName} 
						 <br/>
						 내용 : ${message} --%>
						 접속에 실패하였습니다. <br/>페이지에 다시 접속해 주세요.
					</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>