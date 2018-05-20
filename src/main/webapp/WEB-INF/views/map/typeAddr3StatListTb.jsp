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
								<label class="btn btn-default active">
									<input type="radio" name="crimeTypeUnit" id="crimeTypeUnitDong" autocomplete="off" value="dong" checked>동단위 
								</label>
								<label class="btn btn-default">
									<input type="radio" name="crimeTypeUnit" id="crimeTypeUnitGu" autocomplete="off" value="gu">구단위 
								</label>
								<button type="button" class="btn btn-default" style="background:#fff;width: 43px; height: 27px;" onclick="exportExcel('/map/type/addr3stat/Excel.do')" alt="엑셀다운로드" title="엑셀다운로드">
									<img src="<c:url value='/resources/images/ico_excel.png' />" style="width: 23px; height: 16px;" />
								</button>
							</div>
							<div class="mapbtn"></div>

							<table class="table table_b" style="margin-bottom:0">
								<thead>
									<tr>
										<th width="10%">행정동</th>
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
									<c:forEach var="list" items="${typeStatList}" varStatus="status">
										<tr>
											<td width="10%"><a href="javascript:detailTypeAddr3Stat('${list.locationDong}')">${list.locationDong}</a></td>
											<td width="10%">${list.crimeCnt}건</td>
											<td width="10%">${list.murderCnt}건</td>
											<td width="10%">${list.robberCnt}건</td>
											<td width="10%">${list.rapeCnt}건</td>
											<td width="10%">${list.theftCnt}건</td>
											<td width="10%">${list.violenceCnt}건</td>
											<td width="10%">${list.accidentCnt}건</td>
											<td width="10%">${list.destroyCnt}건</td>
											<td width="10%">${list.etcCnt}건</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
