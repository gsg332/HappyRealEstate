<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${playList[0].totalPage}", "${playList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchPlayList(1);
		});
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${playList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${playList[0].currentPage}/${playList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq playList[0].rowSize}">
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
						<th>재생일시</th>
						<th colspan="7">재생PC IP주소</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty playList}">
						<tr>
							<td colspan="3"><spring:eval expression="@message['list.empty.message']" /></td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach var="list" items="${playList}" varStatus="status">
						<tr>
							<td>${list.rnum}</td>
							<td>${list.playTime }</td>
							<td colspan="7">${list.ipaddr }</td>
						</tr>
					</c:forEach>
					</c:otherwise>
				</c:choose>
					<tr>
						<td colspan="9" style="background:#fff">
							<nav style="position:relative">
								<div id="page-selection" style="text-align: center;"></div>
								<p style="position:absolute; right:0px; top:20px">
									<button type="button" class="btn btn-default" onclick="moveMenu('/apply/apply/list.do', '${pMenuId}')"" style="height:32px">목록보기</button>
									<c:if test="${not empty playList}">
										<button type="button" class="btn btn-default" style="background:#fff" onclick="javascript:exportExcel()">
											<img src="<c:url value='/resources/images/ico_excel.png' />" width="22" height="22" alt=""/>&nbsp;&nbsp;엑셀다운로드
										</button>
									</c:if>
								</p>
							</nav>
						</td>
					</tr>
				</tbody>
			</table>
