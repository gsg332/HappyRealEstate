<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${joinList[0].totalPage}", "${joinList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#searchJoinFrm").children(":input[name=rowSize]").val($("#selRowSize option:selected").val());
			searchJoinList(1);
		});
		
	});
</script>

			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${joinList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${joinList[0].currentPage}/${joinList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq joinList[0].rowSize}">
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
						<th>번호</th>
		                <th>아이디</th>
		                <th>IP</th>
		                <th>메뉴</th>
		                <th>이력내역</th>
		                <th>상세내용</th>
		                <th>결과</th>
		                <th>이력일시</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${joinList}" varStatus="status">
					<tr>
						<td>${list.rnum}</td>
						<td>${list.userId}</td>
						<td>${list.accessIp}</td>
						<td>${list.menu}</td>
						<td>${list.action}</td>
						<td>${list.memo}</td>
						<td>${list.result}</td>
						<td>${list.actionTime}</td>						
					</tr>
				</c:forEach>				
					<tr>
						<td colspan="8" style="background:#fff">
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
