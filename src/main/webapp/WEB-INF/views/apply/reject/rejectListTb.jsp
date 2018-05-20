<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${rejectList[0].totalPage}", "${rejectList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchRejectList(1);
		});
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${rejectList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${rejectList[0].currentPage}/${rejectList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq rejectList[0].rowSize}">
									<option value="${row}" selected>${row}건 보기</option>
								</c:when>
								<c:otherwise>
									<option value="${row}">${row}건 보기</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<table class="table table-hover table_b text-center">
				<thead>
					<tr>
						<th>신청번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>신청일시</th>
						<th>반려일시</th>
						<th width="300px;">반려사유</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty rejectList}">
					<tr>
						<td colspan="6"><spring:eval expression="@message['list.empty.message']" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${rejectList}" varStatus="status">
						<tr>
							<td>${list.itemSerial}</td>
							<td>${list.userId}</td>
							<td>${list.reqUsername}</td>
							<td>
								${fn:substring(list.reqDate,0,10)}
							</td>
							<td>
								${fn:substring(list.rejectDate,0,10)}
							</td>
							<td>
								<p style="width:300px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; margin: 0 0 0px;">${list.veiRejectReason}</p>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
				</c:choose>
					<tr>
						<td colspan="6" style="background:#fff">
							<nav style="position:relative">
								<div id="page-selection" style="text-align: center;"></div>
								<p style="position:absolute; right:0px; top:20px">
									<button type="button" class="btn btn-default" style="background:#fff" onclick="exportExcel()">
										<img src="<c:url value='/resources/images/ico_excel.png' />" width="22" height="22" alt=""/>&nbsp;&nbsp;엑셀다운로드
									</button>
								</p>
							</nav>
						</td>
					</tr>
				 
				</tbody>
			</table>
