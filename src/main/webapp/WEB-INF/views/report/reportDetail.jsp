<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${applyList[0].totalPage}", "${applyList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchApplyList(1);
		});
	});
</script>
			<div style="margin: 30px 0 5px 10px; line-height: 15px; font-size: 11px;"> 
				<font style="font-weight: bold; font-size: 14px; margin-bottom:5px; display: inline-block">[재신청]</font>
				<br/>
				- 결과등록이 완료된 후에 재신청이 가능합니다.	
			</div>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${applyList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${applyList[0].currentPage}/${applyList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq applyList[0].rowSize}">
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
						<th>이름</th>
					</c:if>
						<th>범죄유형</th>
					<c:if test="${USER_LEVEL_NO lt 8 }">
						<th>CCTV 관리번호</th>
					</c:if>
						<th>첨부</th>
						<th>잔여재생</th>
						<th>신청일</th>
						<th>재생 만료일</th>
						<th>상태</th>
						<th>활용결과</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty applyList}">
						<tr>
							<td colspan="10"><spring:eval expression="@message['list.empty.message']" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="list" items="${applyList}" varStatus="status">
							<tr>
								<td><a href="javascript:moveApplyDetail('${list.itemSerial}')">${list.itemSerial}</a></td>
							<c:if test="${USER_LEVEL_NO ge 8 }">
								<td>${list.userId}</td>
								<td>${list.userName}</td>
							</c:if>
								<td>${list.codeVal}</td>
							<c:if test="${USER_LEVEL_NO lt 8 }">
								<td>
								<!-- <p style="width:100px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; margin: 0 0 0px;">${list.videoId}</p> -->
								<c:set var="videoIdLen" value="${fn:split(list.videoId,',')}" scope="page"/>
									<c:forEach var="videoList" items="${fn:split(list.videoId,',')}" varStatus="status" end="0">
										<c:forEach var="videoNameSpl" items="${fn:split(videoList,'|')}" varStatus="status" end="0">
											<c:set var="videoName" value="${videoNameSpl}" scope="page"/>
										</c:forEach>
									</c:forEach>
									<c:choose>
										<c:when test="${fn:length(videoIdLen) > 1}">
											${fn:substring(videoName,0,8)}.. 외 ${fn:length(videoIdLen)-1}개
										</c:when>
										<c:otherwise>
											${fn:substring(videoName,0,11)}
										</c:otherwise>
									</c:choose>
								</td>
							</c:if>
								<td>
								<c:forEach var="fileNm" items="${list.attachFileNm }" varStatus="fileStatus">
									<a href="javascript:fileDown('${list.attachFileNo[fileStatus.index]}')">
										<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${fileNm}" />
									</a>
								</c:forEach>
								</td>
								<td>${list.modLimit}</td>
								<td>
									${fn:substring(list.reqDate,0,10)}
								</td>
								<td>
									${fn:substring(list.veiLimitDatetime,0,10)}
								</td>
							<c:choose>
								<c:when test="${list.veiStatus == '0' }">
									<td class="list-group-item-danger">${list.statusNm}</td>
									<td>
									<c:if test="${USER_LEVEL_NO ge 8}">
									<c:forEach var="configList" items="${CONFIG_LIST}" varStatus="status">
					            		<c:if test="${configList.itemName eq 'VMS_LINK' }">
					            			<c:if test="${configList.itemValue eq '1' }">
					            				<a href="#" data-toggle="modal" data-target="#confirmModal" data-whatever="${list.itemSerial}">승인</a>
					            			</c:if>
					            			<c:if test="${configList.itemValue eq '0' }">
					            				<a href="#" data-toggle="modal" data-target="#confirmModal" data-whatever="${list.itemSerial}">승인</a>
					            			</c:if>
					            		</c:if>
									</c:forEach>
									</c:if>	
									</td>
									<td>
									<c:if test="${USER_LEVEL_NO ge 8}">
										<a href="javascript:openRejModal('${list.itemSerial}', '${list.userId}', '${list.phoneNum}', '3')">반려</a>
									</c:if>
									</td>
								</c:when>
								<c:when test="${list.veiStatus == '8'}">
									<td class="list-group-item-warning">${list.statusNm}</td>
									<td></td>
									<td>
									<c:if test="${USER_LEVEL_NO ge 8}">
										<a href="javascript:openRejModal('${list.itemSerial}', '${list.userId}', '${list.phoneNum}', '3')">반려</a>
									</c:if>
									</td>
								</c:when>
								<c:when test="${list.veiStatus == '1' || list.veiStatus == '9'}">
									<td class="list-group-item-warning">${list.statusNm}</td>
									<td></td>
									<td>
									<c:if test="${USER_LEVEL_NO ge 8}">
										<a href="javascript:openRejModal('${list.itemSerial}', '${list.userId}', '${list.phoneNum}', '3')">반려</a>
									</c:if>
									</td>
								</c:when>
								<c:when test="${list.veiStatus == '2' }">
									<c:choose>
										<c:when test="${list.veiApplyStatus == '1' }">
										<!-- 수사중 -->
											<td class="list-group-item-danger">${list.applyStatusNm}</td>
											<td>
												<c:choose>
													<c:when test="${list.itemResult == '' || list.itemResult eq null}"><a href="javascript:resultPop('reg', '${list.itemSerial}')">결과등록</a></c:when>
													<c:when test="${list.itemResult eq '2'}"><a href="javascript:resultPop('mod', '${list.itemSerial}')">결과등록</a></c:when>
													<c:otherwise><a href="javascript:resultPop('mod', '${list.itemSerial}')">결과보기</a></c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:if test="${USER_LEVEL_NO lt 8}">
													<c:if test="${list.extendFlag}"><a href="#" data-toggle="modal" data-target="#extendModal" data-whatever="${list.itemSerial}" >연장신청</a></c:if>
												</c:if>
											</td>
										</c:when>
										<c:when test="${list.veiApplyStatus == '2' }">
										<!-- 연장 신청 -->
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8 }">
													<td class="list-group-item-danger">${list.applyStatusNm}</td>
													<td> </td>
													<td>
														<a href="#" data-toggle="modal" data-target="#extendAppModal" data-whatever="${list.itemSerial}" >승인/미승인</a> 
													</td>
												</c:when>
												<c:otherwise>
													<td class="list-group-item-danger">${list.applyStatusNm}</td>
													<td> </td>
													<td> </td>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:when test="${list.veiApplyStatus == '3' || list.veiApplyStatus == '4' || list.veiApplyStatus == '5'}">
										<!-- 연장승인 완료, 결과등록 완료, 연장미승인 -->
											<td class="list-group-item-info">${list.applyStatusNm}</td>
											<td>
												<c:choose>
													<c:when test="${list.itemResult == '' || list.itemResult eq null}"><a href="javascript:resultPop('reg', '${list.itemSerial}')">결과등록</a></c:when>
													<c:otherwise><a href="javascript:resultPop('mod', '${list.itemSerial}')">결과보기</a></c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:if test="${list.veiApplyStatus == '4' && USER_LEVEL_NO lt 8}">
													<a href="javascript:registReapply('${list.itemSerial}')">재신청</a>
												</c:if>
											</td>
										</c:when>
										<c:otherwise>
										<!-- 기본 제공완료 상태 -->
											<td class="list-group-item-info">${list.statusNm}</td>
											<td>
												<c:choose>
													<c:when test="${list.itemResult == '' || list.itemResult eq null}"><a href="javascript:resultPop('reg', '${list.itemSerial}')">결과등록</a></c:when>
													<c:otherwise><a href="javascript:resultPop('mod', '${list.itemSerial}')">결과보기</a></c:otherwise>
												</c:choose>
											</td>
											<td>
											<c:if test="${USER_LEVEL_NO lt 8}">
												<c:choose>
													<c:when test="${list.extendFlag && list.veiDnLimitCount > 0}"><a class="down" href="javascript:videoDownPop('${list.itemSerial}')">다운로드</a></c:when>
													<%-- <c:when test="${list.extendFlag && list.veiDnLimitCount > 0}"><a class="videoDown" href="/apply/apply/videoDown.do?itemSerial=${list.itemSerial}">다운로드</a></c:when> --%>
													<c:when test="${list.extendFlag}"><a href="#" data-toggle="modal" data-target="#extendModal" data-whatever="${list.itemSerial}" >연장신청</a></c:when>
													<c:otherwise>
														<%-- <a href="javascript:registReapply('${list.itemSerial}')">재신청</a> --%>
													</c:otherwise>
												</c:choose>
											</c:if>
											</td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${list.veiStatus ge 100}">
									<td class="list-group-item-success">${list.statusNm}</td>
									<td></td>
									<td>
									<c:if test="${USER_LEVEL_NO ge 8}">
										<a href="javascript:openRejModal('${list.itemSerial}', '${list.userId}', '${list.phoneNum}', '3')">반려</a>
									</c:if>
									</td>
								</c:when>
								<c:when test="${list.veiStatus == '3' }">
									<td class="list-group-item-danger">${list.statusNm}</td>
									<td> </td>
									<td> </td>
								</c:when>
								<c:otherwise>
									<td> </td>
									<td> </td>
									<td> </td>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="12" style="background:#fff">
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
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
