<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
	});
	
	function checkOffer(){
		if ($("input[name='offer']").is(":checked")){
			$(opener.document).find("#reqReason").val($("input[name='offer']:checked").val());
			self.close();
		} else {
			$("#offerAlert").show();
		}
	}
</script>
</head>
<body>
	<form class="form-inline">
	<!--pop width 800px-->
	<div class="pop">
		<div class="pop_title" style="margin-bottom:10px">제공근거<small>_제공근거를 선택하는 화면입니다.</small></div>
		<table class="table table-hover table_b" style="width:780px; margin:0 auto; border-bottom:1px dashed #ccc">
			<thead>
				<tr>
					<th colspan="2" class="text-info"><spring:eval expression="@message['offer.basis']" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${provideList}" varStatus="status">
					<c:choose>
						<c:when test="${(status.index mod 2) eq 0}">
							<tr>
								<td width="50" class="text-center bg-info">
									<input type="radio" id="offer" name="offer" value="${list.codeVal}">
								</td>
								<td width="730">${list.codeVal}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td width="50" class="text-center bg-success">
									<input type="radio" id="offer" name="offer" value="${list.codeVal}">
								</td>
								<td width="730">${list.codeVal}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
		<div class="alert alert-danger text-center collapse" role="alert" style="margin: 10px;" id="offerAlert">
			<a class="close" onclick="$('#offerAlert').hide()">x</a>
			<strong>체크된 값이 없습니다!</strong> 항목을 선택해 주세요.
		</div>
		<button type="button" class="btn btn-default" style="margin:10px; width:780px; height:30px; font-weight:bold; font-size:14px" onclick="checkOffer()">완료</button>
	</div>
	</form>
</body>
</html>