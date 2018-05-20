<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name=cctvInstUnit]").change(function(){
			searchCctvInstStatList($(this).val());
		});
	});
</script>

						<div class="btn-group mapbtn1" data-toggle="buttons">
							<label class="btn btn-default active">
								<input type="radio" name="cctvInstUnit" id="cctvInstUnitDong" autocomplete="off" value="dong" checked>동단위 
							</label>
							<label class="btn btn-default">
								<input type="radio" name="cctvInstUnit" id="cctvInstUnitGu" autocomplete="off" value="gu">구단위 
							</label>
							<button type="button" class="btn btn-default" style="background:#fff;width: 43px; height: 27px;" onclick="exportExcel('/map/cctv/instStat/addr3stat/listExcel.do')" alt="엑셀다운로드" title="엑셀다운로드">
									<img src="<c:url value='/resources/images/ico_excel.png' />" style="width: 23px; height: 16px;" />
							</button>
						</div>
						<div class="mapbtn"></div>
						<table class="table table_b" style="margin-bottom:0">
								<thead>
									<tr>
										<th width="8%">구분</th>
										<th width="8%">총계</th>
										<th width="8%">방범</th>
										<th width="8%">주정차</th>
										<th width="8%">추적/감지</th>
										<th width="8%">쓰레기무단투기</th>
										<th width="8%">어린이보호</th>
										<th width="8%">화재감시</th>
										<th width="8%">산불감시</th>
										<th width="8%">도로방범</th>
										<th width="8%">공원방범</th>
										<th width="6%">
											<c:choose>
												<c:when test="${FN_CONFIG_LIST['CCTV_USE_CHANGE']['Value'] == 'Y'}">
													<span>${FN_CONFIG_LIST['CCTV_USE_CHANGE']['SubConfigList']['CHANGED_USE']['Value']}</span>
												</c:when>
												<c:otherwise>
													다목적
												</c:otherwise>
											</c:choose>
										</th>
										<th width="6%">기타</th>
									</tr>
								</thead>
							</table>
							<div style="height:269px; overflow:auto">
								<table class="table table-hover text-center">
									<tbody>
									<c:forEach var="list" items="${cctvInstStatList}" varStatus="status">
										<tr>
											<td width="8%"><a href="javascript:detailCctvInstAddr3Stat('${list.locationDong}')">${list.locationDong}</a></td>
											<td width="8%">${list.cctvCnt}건</td>
											<td width="8%">${list.cctvType0}건</td>
											<td width="8%">${list.cctvType1}건</td>
											<td width="8%">${list.cctvType2}건</td>
											<td width="8%">${list.cctvType3}건</td>
											<td width="8%">${list.cctvType4}건</td>
											<td width="8%">${list.cctvType5}건</td>
											<td width="8%">${list.cctvType6}건</td>
											<td width="8%">${list.cctvType7}건</td>
											<td width="8%">${list.cctvType8}건</td>
											<td width="6%">${list.cctvType9}건</td>
											<td width="6%">${list.cctvType10}건</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

