<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		//console.log('${CURRENT_URL}');
		searchEvidenceList(1);
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchEvidenceList(1);
			} 
		});	
		
		$('#evidenceModal').on('show.bs.modal', function (event) {			
			var button = $(event.relatedTarget);
			var recipient = button.data('whatever');

			$("#eviItemNo").val(button.data('whatever'));
		});
		
	});
	
	function searchEvidenceList(pageNum){
		
		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/apply/evidence/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#evidenceList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#evidenceList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}

	function pagination(totalPage, currentPage){
		$('#page-selection').bootpag({
		    total: totalPage,
		    page: currentPage,
		    maxVisible: 5,
		    leaps: true,
		    firstLastUse: true,
		    first: '←',
		    last: '→',
		    wrapClass: 'pagination',
		    activeClass: 'active',
		    disabledClass: 'disabled',
		    nextClass: 'next',
		    prevClass: 'prev',
		    lastClass: 'last',
		    firstClass: 'first'
		}).on("page", function(event, num){
		    // $("#currentPage").val(num); // or some ajax content loading...
		 	// apply list search ajax
		 	searchEvidenceList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/apply/evidence/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	/* 증거자료 제출 신청내역 상태 처리 */
	function modifyEvidence(flag){
		$.ajax({
			type : 'post',
			url : '/apply/evidence/modify.json',
			dataType: 'json',
			data : {
				"eviItemNo" : $("#eviItemNo").val(),
				"permit" : flag
			},	
			success : function(data) {
				if (data.result == "SUCCESS"){
				//	alert("증거자료 제출 신청내역 상태 처리가 완료되었습니다.");
					searchEvidenceList(1);
				} else {
				//	alert("증거자료 제출 신청내역 상태 처리를 실패하였습니다.");
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});		
	}
	
</script>
	<!-- Content Area -->
	<div class="container">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-2">
				<div class="slnb list-group">
					<div class="slnb_title1"></div>
				<c:forEach var="list" items="${SECOND_MENU_LIST}" varStatus="status">
					<c:if test="${list.pMenuId == P_MENU_ID}">
						<c:choose>
							<c:when test="${list.menuUrl == CURRENT_URL}">
								<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm }</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm }</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10">
	        	<blockquote><p><strong>증거자료 제출내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">
					
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					<input type="hidden" id="eviItemNo" name="eviItemNo" >
					
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; width:490px; margin:0 auto">
	
							<label for="textfield">상태&nbsp;&nbsp;</label>
							<select id="searchPermit" name="searchPermit" class="form-control" style="width:100px">
								<option value="">전체</option>
							<c:forEach var="list" items="${permitList}" varStatus="status">
								<option value="${list.codeKey}">${list.codeVal}</option>
							</c:forEach>
							</select>
							
							<label for="textfield">&nbsp;&nbsp;&nbsp;신청자ID&nbsp;&nbsp;</label>
	            			<input id="searchWord" name="searchWord" type="text" class="form-control">
							
							<button class="btn btn-default" type="button" onclick="javascript:searchEvidenceList(1)">
								<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
							</button>
						</div>
					</div>
				</form>
				<!-- ajax list -->
				<div id="evidenceList"></div>
			</div>
		</div>
	</div>
	
	<!-- 증거자료 제출내역 Modal Start -->
	<div class="modal fade" id="evidenceModal" tabindex="1" role="dialog" aria-labelledby="evidenceModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="evidenceModalLabel">증거자료 제출내역</h4>
				</div>
				<div class="modal-body">
					<div class="pop_title" style="margin-bottom:10px">증거자료 제출 신청내역에 대한 승인 혹은 반려를 선택합니다.</div>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-primary" style="width: 100px;" onclick="modifyEvidence('1')" data-dismiss="modal">승인</button>
					<button type="button" class="btn btn-danger" style="width: 100px;" onclick="modifyEvidence('2')" data-dismiss="modal">반려</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 증거자료 제출내역 Modal End -->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
