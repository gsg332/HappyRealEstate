<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchRejectList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchRejectList(1);
			} 
		});
		
		// 검색 종류 전체 일 경우 input readonly
		$("#searchKind").change(function(){

			if ($("#searchKind option:selected").val() != ""){
				$("#searchWord").prop("readonly", false);
			} else {
				$("#searchWord").val("");
				$("#searchWord").prop("readonly", true);
			}
		});
	});
	
	/**
	 *  반려 리스트 조회
	 */
	function searchRejectList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/apply/reject/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#rejectList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#rejectList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/**
	 *  반려 리스트 페이징 처리
	 */
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
		 	// reject list search ajax
		 	searchRejectList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/apply/reject/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
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
	        	<blockquote><p><strong>반려내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">
					
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; margin:0 auto">
	
							<label for="textfield">검색 종류&nbsp;&nbsp;</label>
							<select id="searchKind" name="searchKind" class="form-control" style="width:100px">
								<option value="">전체</option>
								<option value="name">이름</option>
								<option value="reason">반려사유</option>
							</select>
							
	            			<input id="searchWord" name="searchWord" type="text" class="form-control" readonly="readonly">
							
							<button class="btn btn-default" type="button" onclick="javascript:searchRejectList(1)">
								<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
							</button>
						</div>
					</div>
				</form>
				<!-- ajax list -->
				<div id="rejectList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
