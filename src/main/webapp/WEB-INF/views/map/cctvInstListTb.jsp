<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
						<div class="mapbtn"></div>
						<table class="table table_b" style="margin-bottom:0">
							<thead>
								<tr>
									<th width="8%">행정동</th>
									<th width="7%">총계</th>
									<th width="7%">방범</th>
									<th width="7%">주정차</th>
									<th width="7%">추적<br>
									/감지</th>
									<th width="10%">쓰레기<br>
									무단투기</th>
									<th width="10%">어린이<br>
									보호구역</th>
									<th width="7%">화재<br>
									감시</th>
									<th width="7%">산불<br>
									감시</th>
									<th width="7%">도로<br>
									방범</th>
									<th width="7%">공원<br>
									방범</th>
									<th width="7%">
										<c:choose>
											<c:when test="${FN_CONFIG_LIST['CCTV_USE_CHANGE']['Value'] == 'Y'}">
												<span>${FN_CONFIG_LIST['CCTV_USE_CHANGE']['SubConfigList']['CHANGED_USE']['Value']}</span>
											</c:when>
											<c:otherwise>
												다목적
											</c:otherwise>
										</c:choose>	
									</th>
									<th width="9%">기타</th>
								</tr>
							</thead>
						</table>
						<div style="height:252px; overflow:auto">
							<table class="table table-hover text-center">
								<tbody>
								<c:forEach var="list" items="${cctvInstList}" varStatus="status">
									<tr>
										<td width="8%">${list.locationDong}</td>
										<td width="7%">${list.cctvCnt}대</td>
										<td width="7%">${list.cctvType0}대</td>
										<td width="8%">${list.cctvType1}대</td>
										<td width="7%">${list.cctvType2}대</td>
										<td width="10%">${list.cctvType3}대</td>
										<td width="10%">${list.cctvType4}대</td>
										<td width="7%">${list.cctvType5}대</td>
										<td width="7%">${list.cctvType6}대</td>
										<td width="7%">${list.cctvType7}대</td>
										<td width="7%">${list.cctvType8}대</td>
										<td width="9%">${list.cctvType9}대</td>
										<td width="6%">${list.cctvType10}대</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
