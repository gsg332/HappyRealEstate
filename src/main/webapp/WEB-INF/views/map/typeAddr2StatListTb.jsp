<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){

		$("input[name=crimeTypeUnit]").change(function(){
			searchTypeStatList($(this).val());
		});
		
	});
</script>
							<div class="btn-group mapbtn1" data-toggle="buttons">
								<label class="btn btn-default">
									<input type="radio" name="crimeTypeUnit" id="crimeTypeUnitDong" autocomplete="off" value="dong">동단위 
								</label>
								<label class="btn btn-default active">
									<input type="radio" name="crimeTypeUnit" id="crimeTypeUnitGu" autocomplete="off" value="gu" checked>구단위 
								</label>
								<button type="button" class="btn btn-default" style="background:#fff;width: 43px; height: 27px;" onclick="exportExcel('/map/type/addr2statExcel.do')" alt="엑셀다운로드" title="엑셀다운로드">
									<img src="<c:url value='/resources/images/ico_excel.png' />" style="width: 23px; height: 16px;" />
								</button>
							</div>
							<div class="mapbtn"></div>

							<table class="table table_b" style="margin-bottom:0">
								<thead>
									<tr>
										<th width="10%">구분</th>
										<th width="10%">범죄총계</th>
										<th width="10%">살인</th>
										<th width="10%">강도</th>
										<th width="10%">강간/추행</th>
										<th width="10%">절도</th>
										<th width="10%">폭행</th>
										<th width="10%">교통사고</th>
										<th width="10%">재물손괴</th>
										<th width="10%">기타</th>
									</tr>
								</thead>
							</table>
							<div style="height:269px; overflow:auto">
								<table class="table table-hover text-center">
									<tbody>
										<tr>
											<td width="10%">${typeStatInfo.locationGu}</td>
											<td width="10%">${typeStatInfo.crimeCnt}건</td>
											<td width="10%">${typeStatInfo.murderCnt}건</td>
											<td width="10%">${typeStatInfo.robberCnt}건</td>
											<td width="10%">${typeStatInfo.rapeCnt}건</td>
											<td width="10%">${typeStatInfo.theftCnt}건</td>
											<td width="10%">${typeStatInfo.violenceCnt}건</td>
											<td width="10%">${typeStatInfo.accidentCnt}건</td>
											<td width="10%">${typeStatInfo.destroyCnt}건</td>
											<td width="10%">${typeStatInfo.etcCnt}건</td>
										</tr>
										<tr>
											<td width="10%">해결수</td>
											<td width="10%">${typeStatInfo.resolutionCnt}건</td>
											<td width="10%">${typeStatInfo.murderResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.robberResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.rapeResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.theftResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.violenceResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.accidentResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.destroyResolutionCnt}건</td>
											<td width="10%">${typeStatInfo.etcResolutionCnt}건</td>
										</tr>
										<tr>
											<td width="10%">해결율</td>
											<td width="10%">${typeStatInfo.resolutionPct}%</td>
											<td width="10%">${typeStatInfo.murderResolutionPct}%</td>
											<td width="10%">${typeStatInfo.robberResolutionPct}%</td>
											<td width="10%">${typeStatInfo.rapeResolutionPct}%</td>
											<td width="10%">${typeStatInfo.theftResolutionPct}%</td>
											<td width="10%">${typeStatInfo.violenceResolutionPct}%</td>
											<td width="10%">${typeStatInfo.accidentResolutionPct}%</td>
											<td width="10%">${typeStatInfo.destroyResolutionPct}%</td>
											<td width="10%">${typeStatInfo.etcResolutionPct}%</td>
										</tr>
										<tr>
											<td width="10%">활용수</td>
											<td width="10%">${typeStatInfo.useCnt}건</td>
											<td width="10%">${typeStatInfo.murderUseCnt}건</td>
											<td width="10%">${typeStatInfo.robberUseCnt}건</td>
											<td width="10%">${typeStatInfo.rapeUseCnt}건</td>
											<td width="10%">${typeStatInfo.theftUseCnt}건</td>
											<td width="10%">${typeStatInfo.violenceUseCnt}건</td>
											<td width="10%">${typeStatInfo.accidentUseCnt}건</td>
											<td width="10%">${typeStatInfo.destroyUseCnt}건</td>
											<td width="10%">${typeStatInfo.etcUseCnt}건</td>
										</tr>
										<tr>
											<td width="10%">기여율</td>
											<td width="10%">${typeStatInfo.usePct}%</td>
											<td width="10%">${typeStatInfo.murderUsePct}%</td>
											<td width="10%">${typeStatInfo.robberUsePct}%</td>
											<td width="10%">${typeStatInfo.rapeUsePct}%</td>
											<td width="10%">${typeStatInfo.theftUsePct}%</td>
											<td width="10%">${typeStatInfo.violenceUsePct}%</td>
											<td width="10%">${typeStatInfo.accidentUsePct}%</td>
											<td width="10%">${typeStatInfo.destroyUsePct}%</td>
											<td width="10%">${typeStatInfo.etcUsePct}%</td>
										</tr>
									</tbody>
								</table>
							</div>
