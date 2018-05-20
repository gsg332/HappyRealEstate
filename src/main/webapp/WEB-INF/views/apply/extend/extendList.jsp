<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		console.log('${CURRENT_URL}');
		searchExtendList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchExtendList(1);
			} 
		});		
		
		$('#extendAppModal').on('show.bs.modal', function (event) {			
			var button = $(event.relatedTarget);

			$("#idx").val(button.data('whatever'));
			$("#itemSerial").val(button.data('whatever2'));
		});		
	});
	
	
	function searchExtendList(pageNum){
		
		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/apply/extend/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#extendList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#extendList'}, 'listPage'+ pageNum);
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
		 	searchExtendList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/apply/extend/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	/* 연장 신청내역 상태 처리 */
	function modifyExtend(flag){				
		$.ajax({
			type : 'post',
			url : '/apply/extend/modify.json',
			dataType: 'json',
			data : {
				"idx" : $("#idx").val(),
				"itemSerial" : $("#itemSerial").val(),
				"extStatus" : flag
			},	
			success : function(data) {
				console.log(data.result);
				if (data.result == "SUCCESS"){
				//	alert("증거자료 제출 신청내역 상태 처리가 완료되었습니다.");
					searchExtendList(1);
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
	        	<blockquote><p><strong>연장신청내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">
					
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					<input type="hidden" id="idx" name="idx" >
					<input type="hidden" id="itemSerial" name="itemSerial" >
					
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; width:490px; margin:0 auto">
	
							<label for="textfield">상태&nbsp;&nbsp;</label>
							<select id="searchPermit" name="searchPermit" class="form-control" style="width:100px">
								<option value="">전체</option>
							<c:forEach var="list" items="${permitList}" varStatus="status">
								<option value="${list.codeKey}">${list.codeVal}</option>
							</c:forEach>
							</select>
							
							<label for="textfield">&nbsp;&nbsp;&nbsp;연장사유&nbsp;&nbsp;</label>
	            			<input id="searchWord" name="searchWord" type="text" class="form-control">
							
							<button class="btn btn-default" type="button" onclick="javascript:searchExtendList(1)">
								<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
							</button>
						</div>
					</div>
				</form>
				<!-- ajax list -->
				<div id="extendList"></div>
			</div>
		</div>
	</div>
	
	<!-- 연장신청 내역 Modal Start -->
	<div class="modal fade" id="extendAppModal" tabindex="1" role="dialog" aria-labelledby="extendAppModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="extendAppModalLabel">연장신청 내역</h4>
				</div>
				<div class="modal-body">
					<div class="pop_title" style="margin-bottom:10px">연장 신청내역에 대한 승인 혹은 반려를 선택합니다.</div>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-primary" style="width: 100px;" onclick="modifyExtend('1')" data-dismiss="modal">승인</button>
					<button type="button" class="btn btn-danger" style="width: 100px;" onclick="modifyExtend('2')" data-dismiss="modal">반려</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 연장신청 내역 Modal End -->
		
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
