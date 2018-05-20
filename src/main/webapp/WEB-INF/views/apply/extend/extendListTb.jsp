<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${extendList[0].totalPage}", "${extendList[0].currentPage}");
		
		
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchExtendList(1);
		});		
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${extendList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${extendList[0].currentPage}/${extendList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq extendList[0].rowSize}">
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
					<c:if test="${USER_LEVEL_NO ge 8 }">
						<th>신청자ID</th>
					</c:if>
						<th>연장신청일</th>
						<th>처리완료일</th>
						<th width="320px;">연장사유</th>
						<th width="80px;">상태</th>
					<!-- <c:if test="${USER_LEVEL_NO ge 8 }">	
						<th>관리</th>
					</c:if> -->
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty extendList}">
					<tr>
						<td colspan="7"><spring:eval expression="@message['list.empty.message']" /></td>
					</tr>
				</c:when>	
				<c:otherwise>
					<c:forEach var="list" items="${extendList}" varStatus="status">							
						<tr>
							<td>${list.itemSerial}</td>
						<c:if test="${USER_LEVEL_NO ge 8 }">
							<td>${list.userId}</td>
						</c:if>						
							<td>
								${fn:substring(list.extDate,0,10)}
							</td>
							<td>
								${fn:substring(list.extAppDate,0,10)}
							</td>
							<td>
								<p style="width:320px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; margin: 0 0 0px;">${list.extReason}</p>
							</td>
						<c:choose>
							<c:when test="${list.extStatus == '0' }">
								<td class="list-group-item-info">${list.permitNm}</td>
							</c:when>
							<c:when test="${list.extStatus == '1'}">
								<td class="list-group-item-success">${list.permitNm}</td>
							</c:when>
							<c:when test="${list.extStatus == '2' }">
								<td class="list-group-item-danger">${list.permitNm}</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<!-- 상태 관리 주석 -->
						<!--<c:if test="${USER_LEVEL_NO ge 8 }">
							<c:choose>
								<c:when test="${list.extStatus == '0' }">
									<td>
										<a href="#" data-toggle="modal" data-target="#extendAppModal" data-whatever="${list.idx}" data-whatever2="${list.itemSerial}" >승인/반려</a> 
									</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>	
							</c:choose>
						</c:if>-->
						</tr>
					</c:forEach>
				</c:otherwise>
				</c:choose>					
					<tr>
						<td colspan="7" style="background:#fff">
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
