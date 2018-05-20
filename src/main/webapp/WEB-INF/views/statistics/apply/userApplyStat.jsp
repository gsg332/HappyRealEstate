<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<%@ include file="/WEB-INF/views/common/headerSub.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		searchUserApplyStat();

		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		
		// 검색 시작 날짜 달력 세팅
		$("#searchStDate").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
			todayHighlight : true,
			startDate : "2013-01-01",
			endDate : fromEndDate
			
		}).on('changeDate', function(selected){
			//console.log("stDate change");
			startDate = new Date(selected.date.valueOf());
			startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			$("#searchEdDate").datepicker('setStartDate', startDate);
		});
		
		// 검색 종료 날짜 달력 세팅
		$("#searchEdDate").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : toEndDate
		}).on('changeDate', function(selected){
			//console.log("endDate change");
			fromEndDate = new Date(selected.date.valueOf());
			fromEndDate.setDate(fromEndDate.getDate(new Date(selected.date.valueOf())));
			$("#searchStDate").datepicker('setEndDate', fromEndDate);
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
	 *  신청 통계 조회
	 */
	function searchUserApplyStat(){

		var params = $("#statFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/stat/apply/userList.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#statArea").html(data);
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
					<div class="slnb_title2"></div>
					<a href="#" class="list-group-item  active">신청결과</a>
<%-- 					
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
 --%>				
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10" id="contentDiv">
	        	<blockquote><p><strong>신청통계</strong></p></blockquote>
	        	<form id="statFrm" name="statFrm" method="post">
					<!-- input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" -->
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; margin:0 auto">
				            <label for="textfield">기간&nbsp;&nbsp;</label>
				            
				            <div class="btn-group" role="group" data-toggle="buttons">
								<label class="btn btn-default">
									<input type="radio" name="dateFlag" id="yearly" value="yearly" checked>연간
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateFlag" id="monthly" value="monthly">월간
								</label>
							</div>
			            
				            
							<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="width:108px" placeholder="">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
								<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
							</button>
							~
							<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:107px" placeholder="">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
								<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
							</button>				            
				            
				            <label for="textfield">&nbsp;&nbsp;&nbsp;&nbsp;범죄유형&nbsp;&nbsp;</label>
							<select id="searchCrime" name="searchCrime" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
									<option value="${list.codeKey}">${list.codeVal}</option>
								</c:forEach>
							</select>
				            <button class="btn btn-default" type="button" onclick="javascript:searchUserApplyStat()">
				            	<img src="<c:url value='/resources/images/ico_search1.png' />" width="16" height="16" alt=""/>
				            </button>
				          </div>
					</div>
				</form>
				
				<!-- ajax list -->
				<div id="statArea"></div>
			
			</div>			
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
