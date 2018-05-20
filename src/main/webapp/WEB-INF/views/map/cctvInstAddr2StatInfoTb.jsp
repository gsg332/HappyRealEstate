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
							<label class="btn btn-default">
								<input type="radio" name="cctvInstUnit" id="cctvInstUnitDong" autocomplete="off" value="dong">동단위 
							</label>
							<label class="btn btn-default active">
								<input type="radio" name="cctvInstUnit" id="cctvInstUnitGu" autocomplete="off" value="gu" checked>구단위 
							</label>
							<button type="button" class="btn btn-default" style="background:#fff;width: 43px; height: 27px;" onclick="exportExcel('/map/cctv/instStat/addr2statExcel.do')" alt="엑셀다운로드" title="엑셀다운로드">
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
										<tr>
											<td width="8%">${cctvInstStatInfo.locationGu}</td>
											<td width="8%">${cctvInstStatInfo.cctvCnt}건</td>
											<td width="8%">${cctvInstStatInfo.preventionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkingCnt}건</td>
											<td width="8%">${cctvInstStatInfo.trackCnt}건</td>
											<td width="8%">${cctvInstStatInfo.garbageCnt}건</td>
											<td width="8%">${cctvInstStatInfo.multiCnt}건</td>
											<td width="8%">${cctvInstStatInfo.fireCnt}건</td>
											<td width="8%">${cctvInstStatInfo.mFireCnt}건</td>
											<td width="8%">${cctvInstStatInfo.streetCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkCnt}건</td>
											<td width="6%">${cctvInstStatInfo.childCnt}건</td>
											<td width="6%">${cctvInstStatInfo.etcCnt}건</td>
										</tr>
										<tr>
											<td width="8%">해결수</td>
											<td width="8%">${cctvInstStatInfo.resolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.preventionResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkingResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.trackResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.garbageResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.multiResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.fireResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.mFireResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.streetResolutionCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkResolutionCnt}건</td>
											<td width="6%">${cctvInstStatInfo.childResolutionCnt}건</td>
											<td width="6%">${cctvInstStatInfo.etcResolutionCnt}건</td>
										</tr>
										<tr>
											<td width="8%">해결율</td>
											<td width="8%">${cctvInstStatInfo.resolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.preventionResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.parkingResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.trackResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.garbageResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.multiResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.fireResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.mFireResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.streetResolutionPct}%</td>
											<td width="8%">${cctvInstStatInfo.parkResolutionPct}%</td>
											<td width="6%">${cctvInstStatInfo.childResolutionPct}%</td>
											<td width="6%">${cctvInstStatInfo.etcResolutionPct}%</td>
										</tr>
										<tr>
											<td width="8%">활용수</td>
											<td width="8%">${cctvInstStatInfo.useCnt}건</td>
											<td width="8%">${cctvInstStatInfo.preventionUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkingUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.trackUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.garbageUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.multiUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.fireUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.mFireUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.streetUseCnt}건</td>
											<td width="8%">${cctvInstStatInfo.parkUseCnt}건</td>
											<td width="6%">${cctvInstStatInfo.childUseCnt}건</td>
											<td width="6%">${cctvInstStatInfo.etcUseCnt}건</td>
										</tr>
										<tr>
											<td width="8%">기여율</td>
											<td width="8%">${cctvInstStatInfo.usePct}%</td>
											<td width="8%">${cctvInstStatInfo.preventionUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.parkingUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.trackUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.garbageUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.multiUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.fireUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.mFireUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.streetUsePct}%</td>
											<td width="8%">${cctvInstStatInfo.parkUsePct}%</td>
											<td width="6%">${cctvInstStatInfo.childUsePct}%</td>
											<td width="6%">${cctvInstStatInfo.etcUsePct}%</td>
										</tr>
									</tbody>
								</table>
							</div>

