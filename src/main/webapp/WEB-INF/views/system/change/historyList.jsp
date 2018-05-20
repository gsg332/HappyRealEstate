<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchActionHistoryList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchActionHistoryList(1);
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
	 *  사용이력 리스트 조회
	 */
	function searchActionHistoryList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/system/change/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#actionHistoryList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#actionHistoryList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/**
	 *  사용이력 리스트 페이징 처리
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
		 	searchActionHistoryList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/system/change/excel.do";
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
					<sec:authorize  access="hasRole('INCON_ADMIN')">
				        <c:set var="isInconAdmin" value="true"/>
				    </sec:authorize>
				    <c:choose>
				    	<c:when test="${list.menuId != 507}">
							<c:if test="${list.pMenuId == P_MENU_ID}">
								<c:choose>
									<c:when test="${list.menuUrl == CURRENT_URL}">
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${isInconAdmin}">
								<c:if test="${list.pMenuId == P_MENU_ID}">
									<c:choose>
										<c:when test="${list.menuUrl == CURRENT_URL}">
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:if>
						</c:otherwise>	
				    </c:choose>
				</c:forEach>
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10">
	        	<blockquote><p><strong>사용이력</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">
					
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; margin:0 auto">
	
							<label for="textfield">검색 종류&nbsp;&nbsp;</label>
							<select id="searchKind" name="searchKind" class="form-control" style="width:100px">
								<option value="">전체</option>
								<option value="id">아이디</option>
								<option value="menu">메뉴</option>
							</select>
							
	            			<input id="searchWord" name="searchWord" type="text" class="form-control" readonly="readonly">
							
							<button class="btn btn-default" type="button" onclick="javascript:searchActionHistoryList(1)">
								<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
							</button>
						</div>
					</div>
				</form>
				<!-- ajax list -->
				<div id="actionHistoryList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
