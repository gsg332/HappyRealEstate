<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tlds/custom.tld"%>
<script type="text/javascript">
	$(document).ready(function(){
		pagination("${changeReqList[0].totalPage}", "${changeReqList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchChangeReqList(1);
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
		
		$(".wordCut").dotdotdot({ 
	        wrapper:'div',
	        ellipsis: ' ··· ',
	        wrap  : 'word', 
	        after  : null,    
	        watch  : false, 
	        height  : 20,
	        tolerance: 0
	    });
	});

	function openUnApprovalModal(no, itemId, typeDepth1, requestValues){
		$("#no").val(no);
		$("#itemId").val(itemId);
		$("#typeDepth1").val(typeDepth1);
		$("#requestValues").val(requestValues);
		$("#unApprovalModal").modal('toggle');
	}
	
	function sendSmsAuthNo(type){
		if ($('.smsCheck:input[type="checkbox"]:checked').length == 0){
			alert("목록을 선택한 후 SMS 발송이 가능합니다.");
			return false;
		}
		
		var jsonUserArray = new Array(); //수정할 목록을 저장할 변수.
		
		$(".smsCheck").each(function(i, e){
			var user;
			if($(this).is(':checked')){
				user = new Object();
				user['reqUserid'] = $(this).data("reqUserid");
				user['phoneNum'] = $(this).data("phoneNum");
				user['unapprovalReason'] = $(this).data("unapprovalReason");
				jsonUserArray.push(user);	
			}
		});
	
		$.ajax({
			type : 'post',
			url : '/sms/result/sendUnapprovalReason.json',
			dataType : 'json',
			data : {'jsonUserArray':JSON.stringify(jsonUserArray)},
			success : function(data) {
				if(data.result == 'SUCCESS'){
					alert("SMS를 발송하였습니다.");
				}
			},	
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${changeReqList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${changeReqList[0].currentPage}/${changeReqList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq changeReqList[0].rowSize}">
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
				<thead >
					<tr >
						<c:if test="${SMS_USE eq 0}">
							<th width="1%"><input type="checkbox" id="allCheck"/></th>
						</c:if>
						<th width="7%">번호</th>
						<th width="5%">아이디</th>
						<th width="10%">IP</th>
						<th width="9%">변경요청일</th>
						<th width="9%">승인완료일</th>
						<th width="12%">변경메뉴</th>
						<th width="*">상세내용</th>
						<th width="10%">미승인 사유</th>
						<th width="8%">상태 </th>
						<th width="85px">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${changeReqList}" varStatus="status">
						<tr>
							<%-- <td>${(changeReqList[0].listCnt - status.index) - ( (changeReqList[0].currentPage - 1)  *  changeReqList[0].rowSize ) }</td> --%>
							<c:if test="${SMS_USE eq 0}">
								<td>
									<c:if test="${item.status == 1002}">
										<input type="checkbox" class="smsCheck" data-req-userid="${item.reqUserid}" data-phone-num="${item.phoneNum}" data-unapproval-reason="${item.unapprovalReason}"/>
									</c:if>
								</td>
							</c:if>
							<td class="wordCut" title="${item.no}">${item.no}</td>
							<td class="wordCut" title="${item.reqUserid}">${item.reqUserid}</td>
							<td class="wordCut" title="${item.reqIp}">${item.reqIp}</td>
							<td class="wordCut" title="${item.reqDate}">
								<fmt:parseDate value="${item.reqDate}" var="reqDate" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${reqDate}" pattern="yy-MM-dd"/>
							</td>
							<td class="wordCut" title="${item.approvalDate}">
								<fmt:parseDate value="${item.approvalDate}" var="approvalDate" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${approvalDate}" pattern="yy-MM-dd"/>
							</td>
							<td class="wordCut" title="${item.typeDepth1}">${item.typeDepth1}</td>
							<td title="${item.typeDepth2}">
								<custom:FirstStrCut value="${item.typeDepth2}"/>
							</td>
							<td class="wordCut" title="${item.unapprovalReason}">${item.unapprovalReason}</td>
							<td>
								<c:choose>
									<c:when test="${item.status == 1000}">
										대기
									</c:when>
									<c:when test="${item.status == 1001}">
										승인
									</c:when>
									<c:otherwise>
										미승인
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:if test="${item.status == 1000}">
									<a href="javascript:modifyChangeReqStatus('${item.typeDepth1}', 'approval', '${item.no}', '${item.itemId}')">
										승인
									</a>
									/
									<a href="javascript:openUnApprovalModal('${item.no}', '${item.itemId}', '${item.typeDepth1}', '${item.requestValues}')">
										미승인
									</a>
								</c:if>
							</td>					
						</tr>
					</c:forEach>
					<tr>
						<td colspan="11" style="background:#fff">
							<nav style="position:relative">
								<c:if test="${SMS_USE eq 0}">
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
