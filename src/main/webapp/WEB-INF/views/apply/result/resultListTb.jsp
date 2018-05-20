<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${resultList[0].totalPage}", "${resultList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchApplyList(1);
		});
		
		$("#allCheck").on("click", function(){
			$(".smsCheck").each(function(i, e){
				if($("#allCheck").is(':checked')){
					$(this).prop('checked',true);									
				}else{
					$(this).prop('checked',false);
				}				
			});
		});
	});
</script>
			<div style="margin: 20px 0 5px 10px; line-height: 15px; font-size: 11px;">
				<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
            		<c:if test="${list.itemName eq 'veiLimitDatetime' }">
            			<c:set var="veiLimitDatetime" value="${list.itemValue}"/>
            		</c:if>
				</c:forEach> 
				<font style="font-weight: bold; font-size: 14px; margin-bottom:5px; display: inline-block">[결과 미등록 데이터 추출기준]</font>
				<br/>
				- 일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;반 : 상태가 제공완료로 전환된 시점으로부터 ${veiLimitDatetime}일 이상 경과한 신청 건
				<br/>
				- 수 사 중  : 상태를 수사중으로 변경한 시점으로부터 ${veiLimitDatetime * 2}일 이상 경과한 신청 건
				<br/>
				- 연장신청 : 연장신청이 승인된 시점으로부터 ${veiLimitDatetime}일 이상 경과한 신청 건			
			</div>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${resultList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${resultList[0].currentPage}/${resultList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq resultList[0].rowSize}">
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
						<!-- <th><input type="checkbox"> </th> -->
						<c:if test="${USER_LEVEL_NO ge 8}">
							<c:if test="${SMS_USE eq 0 }">
								<th><input type="checkbox" id="allCheck"/></th>
							</c:if>
						</c:if>
						<th>신청번호</th>
						<c:if test="${USER_LEVEL ge 8}">
							<th>아이디</th>
							<th>이름</th>
						</c:if>
						<th>범죄유형</th>
						<th>CCTV관리번호</th>
						<th>신청일</th>
						<th>재생만료일</th>
						<th>경과일</th>
						<th>구분</th>
						<c:if test="${USER_LEVEL_NO lt 8}">
							<th>활용결과</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${resultList}" varStatus="status">
					<tr>
						<!-- <td><input type="checkbox"></td> -->
						<c:if test="${USER_LEVEL_NO ge 8}">
							<c:if test="${SMS_USE eq 0 }">
								<td>
									<fmt:parseNumber var="applyStatus" value="${list.applyStatusNm}"/>
									<c:if test="${applyStatus ne 4}">
										<input type="checkbox" class="smsCheck" data-item-serial="${list.itemSerial}" data-userid="${list.reqUserId}" data-phone-num="${list.reqPhone}" />
									</c:if>
								</td>
							</c:if>
						</c:if>
						<td>${list.itemSerial}</td>
						<c:if test="${USER_LEVEL ge 8}">
						<td>${list.reqUserId}</td>
						<td>${list.reqUsername}</td>
						</c:if>
						<!-- <td>${list.reqPhone}</td> -->
						<td>${list.codeVal}</td>
						
						<%-- <td style="display:inline-block; width:120px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">${list.videoId}</td> --%>						
 						<c:set var="videoIds" value="${fn:split(list.videoId, ',') }" scope="page"/>
						<c:choose>
							<c:when test="${fn:length(videoIds) > 1}">
							<c:forEach var="vlist" items="${videoIds}" varStatus="vstatus">
							<c:if test="${vstatus.first}">
								<td>${vlist} 외  ${fn:length(videoIds)-1}대</td>
							</c:if>
							</c:forEach>
							</c:when>
							<c:otherwise>
								<td>${list.videoId}</td>
							</c:otherwise>
						</c:choose>
						
						<td>${fn:substring(list.reqDate,0,10)}</td>
						<td>
							${fn:substring(list.veiLimitDatetime,0,10)}
						</td>
						<td>${list.pastDate}일</td>
						<td>${list.unregistedType}</td>
						<c:if test="${USER_LEVEL_NO lt 8}">
							<td><a href="javascript:resultPop('reg', '${list.itemSerial}')">결과등록</a></td>
						</c:if>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="10" style="background:#fff">
							<nav style="position:relative">
								<c:if test="${SMS_USE eq 0 && USER_LEVEL_NO ge 8}">
									<p style="position:absolute; left:0px; top:20px">
										<button type="button" class="btn btn-default" onclick="sendSmsAuthNo()">선택 SMS발송</button>
									</p>
								</c:if>
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
