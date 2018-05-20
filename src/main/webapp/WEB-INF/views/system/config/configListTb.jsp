<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${configList[0].totalPage}", "${configList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchConfigList(1);
		});
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${configList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${configList[0].currentPage}/${configList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq configList[0].rowSize}">
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
			<table class="table table-hover table_b lh_40">
				<thead>
					<tr>
						<th>순번</th>
		                <th>설명</th>
		                <th>값</th>
		                <th>관리</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${configList}" varStatus="status">
					<tr>
		                <td style="text-align: center;">${list.rnum }</td>
		                <%-- <td><input type="text" class="form-control" style="width:90%" value="${list.itemDescription}" readonly="readonly"></td>
		                <td><input type="text" class="form-control" style="width:90%" value="${list.itemValue}"></td>
		                <td>
		                	<button type="button" class="btn btn-default" onclick="modifyConfigInfo(this, '${list.itemIndex}')">
		                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
		                	</button>
		                </td> --%>
		                <td>
		                	<div class="description" style="line-height: 30px;">
		                		${list.itemDescription}
		                	</div>
		                	<c:choose>
		                		<c:when test="${list.itemName eq 'SMS_AUTH'}">
		                			<div style="height: 13px; line-height: 13px; color: #0088cc; font-size: 5px; margin-bottom: 5px;">
		                				{authNum} : 인증번호 삽입
		                			</div>			
		                		</c:when>
		                		<c:when test="${list.itemName eq 'SMS_RESULT_UNREGISTERED'}">
		                			<div style="height: 13px; line-height: 13px; color: #0088cc; font-size: 5px; margin-bottom: 5px;">
		                				{userId} : 사용자ID 삽입, {itemSerial} : 목록번호 삽입 
		                			</div>			
		                		</c:when>
		                		<c:when test="${list.itemName eq 'SMS_REQ_CHANGE'}">
		                			<div style="height: 13px; line-height: 13px; color: #0088cc; font-size: 5px; margin-bottom: 5px;">
		                				{userId} : 사용자ID 삽입, {reason} : 미승인사유 삽입
		                			</div>			
		                		</c:when>
		                		<c:otherwise>
		                		</c:otherwise>
		                	</c:choose>
		                </td>
		                <td><div class="value">${list.itemValue}</div></td>
		                <c:choose>
		                	<c:when test="${list.itemName == 'ExportMonitorPollingLastDate' }">
			                	<td style="text-align: center;">
			                	</td>
		                	</c:when>
		                	<c:when test="${list.itemName == 'OFFICIAL_FILE_REG_TYPE' || list.itemName == 'PLEDGE_FILE_REG_TYPE'}">
			                	<td style="text-align: center;">
			                		<c:if test="${empty list.itemValue}">
					                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${list.itemIndex}')">
					                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
					                	</button>
				                	</c:if>
				                </td>
		                	</c:when>
		                	<c:otherwise>
				                <td style="text-align: center;">
				                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${list.itemIndex}')">
				                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
				                	</button>
				                </td>
			                </c:otherwise>
			            </c:choose>
		            </tr>
				</c:forEach>
					<tr>
						<td colspan="12" style="background:#fff">
							<nav style="position:relative">
								<div id="page-selection" style="text-align: center;"></div>
							</nav>
						</td>
					</tr>
				</tbody>
			</table>
