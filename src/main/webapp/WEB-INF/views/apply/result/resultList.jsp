<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 리스트는 1년 단위로 보여준다.
		var yDate = new Date();
		var stDate = (yDate.getFullYear()-1)+'-'+getPad((yDate.getMonth()+1),2,0,'STR_PAD_LEFT​')+'-'+getPad(yDate.getDate(),2,0,'STR_PAD_LEFT​');
		var edDate = yDate.getFullYear()+'-'+getPad((yDate.getMonth()+1),2,0,'STR_PAD_LEFT​')+'-'+getPad(yDate.getDate(),2,0,'STR_PAD_LEFT​');
		$('#searchStDate').val(stDate);
		$('#searchEdDate').val(edDate);
		
		searchApplyList(1);	// 리스트 조회 호출
		
		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchApplyList(1);
			} 
		});	
		
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
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchApplyList(1);
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
	
		$('#searchPosition').change(function(){
			$("#searchDepart").empty();
			$("#searchTeam").empty();
			$.ajax({
				type : 'post',
				url : '/stat/apply/departList.json',
				dataType: 'json',
				data : {
					searchPosition : $(this).val()
				},	
				success : function(data) {
					if (data.departList.length > 0){
						$("#searchDepart").append("<option value=''>과 선택</option>");
						$("#searchTeam").append("<option value=''>계 선택</option>");
						for (var i = 0; i < data.departList.length; i++){
							$("#searchDepart").append("<option value='"+data.departList[i].depart+"'>"+data.departList[i].depart+"</option>");
						}
					}
				},
				error : function(request, status, error) {
					errorModal(request);
				},
			});   
			
			if($('#searchPosition').val() != '')
			{
				$("#searchDepart").removeAttr('disabled'); 
			} else {
				$("#searchDepart").attr("disabled",true);
				$("#searchTeam").attr("disabled",true);	
			}
		});	
		
		$('#searchDepart').change(function(){
			
			$("#searchTeam").empty();
			
			$.ajax({
				type : 'post',
				url : '/stat/apply/departList.json',
				dataType: 'json',
				data : {
					searchPosition : $("#searchPosition").val(),
					searchDepart : $(this).val()
				},	
				success : function(data) {
					if (data.departList.length > 0){
						$("#searchTeam").append("<option value=''>계 선택</option>");
						for (var i = 0; i < data.departList.length; i++){
							$("#searchTeam").append("<option value='"+data.departList[i].team+"'>"+data.departList[i].team+"</option>");
						}
					}
				},
				error : function(request, status, error) {
					errorModal(request);
				},
			});   
			
			if($('#searchDepart').val() != '')
			{
				$("#searchTeam").removeAttr('disabled'); 
			} else {
				$("#searchTeam").attr("disabled",true);	
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
	 *  활용결과 미등록 리스트 조회
	 */
	function searchApplyList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/apply/result/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#resultList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#resultList'}, 'listPage'+ pageNum);
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
		 	searchApplyList(num);
		}); 
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
				user['itemSerial'] = $(this).data("itemSerial");
				user['userid'] = $(this).data("userid");
				user['phoneNum'] = $(this).data("phoneNum");
				jsonUserArray.push(user);	
			}
		});
	
		$.ajax({
			type : 'post',
			url : '/sms/result/resultRegReqSend.json',
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
	
	function exportExcel(){
		var pageUrl = "/apply/result/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	/**
	 *  활용 결과 신청/조회 팝업
	 */
	function resultPop(flag, serial){
		if (flag == 'reg'){
			window.open("/apply/result/regPop.do?itemSerial="+serial, getTargetName("resultRegPop"),"left=200 top=100 width=520 height=450 menubar=no status=no scrollbars=no resizable=no");
		} else {
			window.open("/apply/result/viewPop.do?itemSerial="+serial, getTargetName("resultViewPop"),"left=200 top=100 width=500 height=430 menubar=no status=no scrollbars=no resizable=no");
		}
	}
	
	function clearSearchFrm(){
		moveMenu('/apply/result/list.do', '${P_MENU_ID}');
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
	        	<blockquote><p><strong>활용결과 미등록내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post">
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
					<input type="hidden" id="itemSerial" name="itemSerial" >
					<div class="box_gray2" style="margin:0px">
						<div style="line-height:30px; position:relative; width:550px; margin:0 auto">
							
							<label for="textfield" style="margin-left:8px;">기간&nbsp;&nbsp;</label>
							<div class="btn-group" role="group" data-toggle="buttons">
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="todayBtn" value="0">당일
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="weekBtn" value="7">1주
								</label>
								<label class="btn btn-default">
									<input type="radio" name="dateBtn" id="2monthBtn" value="14">2주
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
							<label for="textfield" style="margin-left: 12px;">범죄유형&nbsp;&nbsp;</label>
							<select id="searchCrime" name="searchCrime" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
									<option value="${list.codeKey}">${list.codeVal}</option>
								</c:forEach>
							</select> <br>
							
							<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="margin-left: 40px;width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
								<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
							</button>
							~
							<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
								<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
							</button>
							
							<label for="textfield" style="margin-left: 48px;">구분&nbsp;&nbsp;</label>
							<select id="searchType" name="searchType" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="list" items="${typeList}" varStatus="status">
									<option value="${list.codeKey}">${list.codeVal}</option>
								</c:forEach>
							</select>
							
							<div style="position:absolute; right:-58px; top:1px">
								<button class="btn btn-default" type="button" style="height:64px" onclick="javascript:searchApplyList(1)">
									<img src='<c:url value="/resources/images/ico_search.png" />' width="32" height="32" alt="" />
								</button>
 							<c:if test="${USER_LEVEL ge 8}">
								<br>
								<button type="button" class="btn btn-default" style="margin-top: 7px; width: 54px;" onclick="clearSearchFrm();">초기화</button>
							</c:if> 
							</div>
						<c:if test="${USER_LEVEL ge 8}">
							<label for="textfield">신청자&nbsp;</label>
							<select id="searchPosition" name="searchPosition" class="form-control" style="width:117px">
								<option value=''>소속 전체</option>
								<c:forEach var="list" items="${positionList}" varStatus="status">
									<option value="${list.position}">${list.position}</option>
								</c:forEach>
							</select>
							<select id="searchDepart" name="searchDepart" class="form-control" style="width:117px">
								<option value=''>과 전체</option>
							</select>
							<select id="searchTeam" name="searchTeam" class="form-control" style="width:117px">
								<option value=''>계 전체</option>
							</select>
							<input id="searchWord" name="searchWord" type="text" class="form-control" value="" style="width:135px" placeholder="아이디">
						</c:if>
						</div>
					</div>
				</form>
	        	<!--  
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">
					
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >

					<c:if test="${USER_LEVEL_NO ge 8}">
					<div class="box_gray" style="margin:0px">
						<div style="line-height:30px; position:relative; margin:0 auto">
	
							<label for="textfield">검색 종류&nbsp;&nbsp;</label>
							<select id="searchKind" name="searchKind" class="form-control" style="width:100px">
								<option value="">전체</option>
								<option value="id">아이디</option>
								<option value="name">이름</option>
								<option value="code">범죄유형</option>
							</select>
							
	            			<input id="searchWord" name="searchWord" type="text" class="form-control" readonly="readonly">
							
							<button class="btn btn-default" type="button" onclick="javascript:searchApplyList(1)">
								<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
							</button>
						</div>
					</div>			
					</c:if>
							
				</form>
				-->
				<!-- ajax list -->
				<div id="resultList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
