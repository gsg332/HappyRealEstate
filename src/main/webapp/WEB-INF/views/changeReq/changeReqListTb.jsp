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
		
		$(".wordCut").dotdotdot({ 
	        wrapper:'div',
	        ellipsis: ' ··· ',
	        wrap  : 'word', 
	        after  : null,    
	        watch  : false, 
	        height  : 20,
	        tolerance: 0      
	    });
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("취소 확인");
			$this.find('.pop_title').text("정말로 취소 하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				var officialFileTempNm;
				var pledgeFileTempNm;
				var requestValues = $('#confirmModal').data('requestValues').split('|'); 
				for(var i=0; i<requestValues.length; i++){
					if(requestValues[i].indexOf('{') != -1 && requestValues[i].indexOf('}') != -1){
						var json = eval("("+requestValues[i]+")");				
						if(json.officialFile){
							officialFileTempNm = json.officialFile.tempFilename;
						}else if(json.pledgeFile){
							pledgeFileTempNm = json.pledgeFile.tempFilename;
						}
					}
				}
				$.ajax({
					type : 'post',
					url : '/changeReq/cancel.json',
					dataType: 'json',
					data : {no:$this.data('no'), itemId:$this.data('itemId'), officialFileTempNm:officialFileTempNm, pledgeFileTempNm:pledgeFileTempNm},	
					success : function(data) {
						//console.log(data.result);
						alert('취소되었습니다.');
						searchChangeReqList(1);
					},
					error : function(request, status, error) {
						errorModal(request);
					},
				});
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
		});
	});

	function cancel(no, itemId, requestValues){
		$('#confirmModal').data('no',no);
		$('#confirmModal').data('itemId',itemId);
		$('#confirmModal').data('requestValues',requestValues);
		$('#confirmModal').modal('show');
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
				<thead>
					<tr>
						<th width="7%">번호</th>
						<th width="14%">변경요청일</th>
						<th width="14%">승인완료일</th>
						<th width="15%">변경메뉴</th>
						<th width="*">상세내용</th>
						<th width="15%">미승인 사유</th>
						<th width="8%">상태 </th>
						<th width="85px">관리</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${changeReqList}" varStatus="status">
					<tr>
						<%-- <td>${(changeReqList[0].listCnt - status.index) - ( (changeReqList[0].currentPage - 1)  *  changeReqList[0].rowSize ) }</td> --%>
						<td class="wordCut" title="${item.no}">${item.no}</td>
						<td class="wordCut" title="${item.reqDate}">
							<fmt:parseDate value="${item.reqDate}" var="reqDate" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${reqDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td class="wordCut" title="${item.approvalDate}">
							<fmt:parseDate value="${item.approvalDate}" var="approvalDate" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${approvalDate}" pattern="yyyy-MM-dd"/>
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
								<a id="cancel" href="javascript:cancel('${item.no}', '${item.itemId}', '${item.requestValues}')">
									취소
								</a>
							</c:if>
						</td>						
					</tr>
				</c:forEach>
					<tr>
						<td colspan="8" style="background:#fff">
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
				</tbody>
			</table>
