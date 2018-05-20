<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
</script>
						
						<div class="btn-group mapbtn1" data-toggle="buttons">
							<button type="button" class="btn btn-default" style="background:#fff" onclick="exportExcel('/map/area/excel.do')">
								<img src="<c:url value='/resources/images/ico_excel.png' />" alt=""/>&nbsp;&nbsp;엑셀다운로드
							</button>
						</div>
						<div class="mapbtn"></div>
							<table class="table table_b" style="margin-bottom:0">
								<thead>
									<tr>
										<th rowspan="2" width="10%">행정동</th>
										<th rowspan="2" width="10%">CCTV</th>
										<th rowspan="2" width="10%">총 결과등록<br>수</th>
										<th rowspan="2" width="10%">결과등록 사건해결<br>완료 수</th>
										<th colspan="4" width="12%">사건 발생시간</th>
										<th rowspan="2" width="12%">해결율</th>
									</tr>
									<tr>
										<th width="12%">주간<br>(07h ~ 18h)</th>
										<th width="12%">져녁<br>(18h ~ 22h)</th>
										<th width="12%">심야<br>(22h ~ 03h)</th>
										<th width="12%">새벽<br>(03h ~ 07h)</th>
									</tr>
								</thead>
							</table>
							<div style="height:203px; overflow:auto">
								<table class="table table-hover text-center">
									<tbody>
									<c:forEach var="list" items="${areaStatList}" varStatus="status">
										<tr>
											<td width="10%"><a href="javascript:detailTypeAddr3Stat('${list.locationDong}')">${list.locationDong}</a></td>
											<td width="10%">${list.cctvCnt}대</td>
											<td width="10%">${list.crimeCnt}건</td>
											<td width="10%">${list.resultTotal}건</td>
											<td width="12%">${list.noon}건</td>
											<td width="12%">${list.evening}건</td>
											<td width="12%">${list.night}건</td>
											<td width="12%">${list.dawn}건</td>
											<td width="8%">${list.rate}%</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
