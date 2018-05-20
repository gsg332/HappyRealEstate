<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.dotdotdot.min.js'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchChangeReqList(1);	// 리스트 조회 호출
		
		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		
		// 검색 시작 날짜 달력 세팅
		$("#searchStDate").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : "2013-01-01",
			endDate : fromEndDate
			
		}).on('changeDate', function(selected){
			startDate = new Date(selected.date.valueOf());
			startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			$("#searchEdDate").datepicker('setStartDate', startDate);
		});
		
		// 검색 종료 날짜 달력 세팅
		$("#searchEdDate").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : toEndDate
		}).on('changeDate', function(selected){
			fromEndDate = new Date(selected.date.valueOf());
			fromEndDate.setDate(fromEndDate.getDate(new Date(selected.date.valueOf())));
			$("#searchStDate").datepicker('setEndDate', fromEndDate);
		});
		
		// 기간 선택 버튼 세팅
		$("input[name=dateBtn]").change(function(){
			
			var today = new Date();
			var endDate = getCalendarStr(today);
			
			if ($(this).val() != ''){
				today.setDate(today.getDate() - $(this).val());
				var startDate = getCalendarStr(today);
				
				$("#searchStDate").val(startDate);
				$("#searchEdDate").val(endDate);
				
				$("#searchStDate").datepicker('setDate', startDate);
				$("#searchEdDate").datepicker('setDate', endDate);
			} else {
				$("#searchStDate").val('');
				$("#searchEdDate").val('');
				
				$("#searchStDate").datepicker('setDate', '');
				$("#searchEdDate").datepicker('setDate', '');
			}
		});
	});
	
	/**
	 *  달력 버튼 클릭 처리
	 */
	function toggleCalendar(val){
		if (val == "st"){
			// $(".datepicker").datepicker('setStartDate', $("#searchStDate").val());
			if ($("#searchStDate").val() != ''){
				$("#searchStDate").datepicker("setDate", $("#searchStDate").val());
			}
			$("#searchStDate").focus();
		} else {
			if ($("#searchEdDate").val() != ''){
				$("#searchEdDate").datepicker("setDate", $("#searchEdDate").val());
			}
			$("#searchEdDate").focus();
		}
	}
	
	/**
	 *  사용이력 리스트 조회
	 */
	function searchChangeReqList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/changeReq/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#changeReqList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#changeReqList'}, 'listPage'+ pageNum);
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
		 	searchChangeReqList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/changeReq/excel.do";
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
	        	<blockquote><p><strong>변경내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post">
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					<input type="hidden" id="itemSerial" name="itemSerial" >
					<div class="box_gray2" style="margin:0px">
						<div style="line-height:30px; position:relative; width:534px; margin:0 auto">	
							<label for="textfield">기간&nbsp;&nbsp;</label>
							<div class="btn-group" role="group" data-toggle="buttons">
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="todayBtn" value="0">당일
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="weekBtn" value="7">1주
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="weekBtn" value="14">2주
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="monthBtn" value="30">1개월
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="2monthBtn" value="180">6개월
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="yearBtn" value="365">12개월
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="allBtn" value="">전체
								</label>
							</div>

							<label for="textfield" style="margin-left: 30px;">상태&nbsp;&nbsp;</label>
							<select id="searchStatus" name="searchStatus" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="item" items="${statusList}" varStatus="status">
									<c:choose>
										<c:when test="${item.codeKey eq pSearchStatus}">
											<option value="${item.codeKey}" selected="selected">${item.codeVal}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.codeKey}">${item.codeVal}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select> <br>
							
							<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="margin-left: 33px;width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
								<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
							</button>
							~
							<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
								<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
							</button>
							
							<label for="textfield" style="margin-left: 9px;">변경메뉴&nbsp;&nbsp;</label>
							<select id="searchTypeDepth1" name="searchTypeDepth1" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="item" items="${typeDepth1List}" varStatus="status">
									<c:choose>
										<c:when test="${item.codeVal eq pSearchTypeDepth1}">
											<option value="${item.codeVal}" selected="selected">${item.codeVal}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.codeVal}">${item.codeVal}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							
							<div style="position:absolute; right:-58px; top:1px">
								<button class="btn btn-default" type="button" style="height:64px" onclick="javascript:searchChangeReqList(1)">
									<img src='<c:url value="/resources/images/ico_search.png" />' width="32" height="32" alt="" />
								</button>
							</div>
						</div>
					</div>
				</form>
				
				<!-- ajax list -->
				<div id="changeReqList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
