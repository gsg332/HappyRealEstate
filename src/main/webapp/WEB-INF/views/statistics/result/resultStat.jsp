<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<%@ include file="/WEB-INF/views/common/headerSub.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
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
			fromEndDate = new Date(selected.date.valueOf());
			fromEndDate.setDate(fromEndDate.getDate(new Date(selected.date.valueOf())));
			$("#searchStDate").datepicker('setEndDate', fromEndDate);
		});
		
		searchResultStat();
		
		setDateEntireDate();
		
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
						$("#searchTeam").append("<option value=''>팀 선택</option>");
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
						$("#searchTeam").append("<option value=''>팀 선택</option>");
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
		
		$('.show_item').on('click',function(){
			var $showItem = $(this);
			var isChecked = $showItem.children().eq(0).is(':checked');
			if($showItem.find('#allCheck').index() > -1){
				$showItem.parent('ul').find('li').each(function(i){
					$(this).find('.show_item_check').prop('checked', isChecked);
				});	
			}else{
				if(!isChecked){
					$('#allCheck').prop('checked', false);
				}
			}
			showStatBox();
		});
	});
	
	function showStatBox(){
		$('ul.show_list').find('li').each(function(i){
			var $synthesizeStat = $('#synthesizeStat');
			var $positionStat = $('#positionStat');
			var $departStat = $('#departStat');
			var $userStat = $('#userStat');
			var $crimeStat = $('#crimeStat');
			var $apprTimeStat = $('#apprTimeStat');
			var $playStat = $('#playStat');
			var $volumeStat = $('#volumeStat');
			var $timeStat = $('#timeStat');
			if($(this).find('.show_item_check').is(':checked')){
				switch(i){
					case 0 :
						$synthesizeStat.show();	
						$positionStat.show();
						$departStat.show();
						$userStat.show();
						$crimeStat.show();
						$apprTimeStat.show();
						$playStat.show();
						$volumeStat.show();
						$timeStat.show();
						break;
					case 1 :
						$synthesizeStat.show();
						break;
					case 2 :
						$positionStat.show();
						break;
					case 3 :
						$departStat.show();
						break;
					case 4 :
						$userStat.show();
						break;
					case 5 :
						$crimeStat.show();
						break;
					case 6 :
						$apprTimeStat.show();
						break;
					case 7 :
						$playStat.show();
						break;
					case 8 :
						$volumeStat.show();
						break;
					case 9 :
						$timeStat.show();
						break;
				}
			}else{
				switch(i){
					case 0 :
						$synthesizeStat.hide();	
						$positionStat.hide();
						$departStat.hide();
						$userStat.hide();
						$crimeStat.hide();
						$apprTimeStat.hide();
						$playStat.hide();
						$volumeStat.hide();
						$timeStat.hide();
						break;
					case 1 :
						$synthesizeStat.hide();
						break;
					case 2 :
						$positionStat.hide();
						break;
					case 3 :
						$departStat.hide();
						break;
					case 4 :
						$userStat.hide();
						break;
					case 5 :
						$crimeStat.hide();
						break;
					case 6 :
						$apprTimeStat.hide();
						break;
					case 7 :
						$playStat.hide();
						break;
					case 8 :
						$volumeStat.hide();
						break;
					case 9 :
						$timeStat.hide();
						break;
				}
			}
		});
	}
	
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
	function searchResultStat(){

		var params = $("#statFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/stat/result/resultList.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#statArea").html(data);
				showStatBox();
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});   
	}
	
	/*
	전체일자로 피커를 세팅한다.	 
	*/
	function setDateEntireDate(){
		var startDate = dateToYYYYMMDD('${itemMinDate}');
		var endDate = dateToYYYYMMDD(new Date()); //new Date('2013-09-16');
		$("#searchStDate").val(startDate);
		$("#searchEdDate").datepicker('setStartDate', startDate);
		$("#searchEdDate").val(endDate);
		$("#searchStDate").datepicker('setEndDate', endDate);
	}
	
	/*
	Date Formatter
	*/
	function dateToYYYYMMDD(date){
		if(typeof date == 'string'){
			var dateArray = date.split(' ');
			if(dateArray[0]){
				return dateArray[0];	
			}
		}else{
			function pad(num) {
		        num = num + '';
		        return num.length < 2 ? '0' + num : num;
		    }
		    return date.getFullYear() + '-' + pad(date.getMonth()+1) + '-' + pad(date.getDate());
		}
	}
</script>
	<!-- Content Area -->
	<div class="container">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-2">
				<div class="slnb list-group">
					<div class="slnb_title2"></div>
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
	        	<blockquote><p><strong>결과현황</strong></p></blockquote>
	        	<form id="statFrm" name="statFrm" method="post">
					<div class="box_gray" style="min-width:600px; padding:10px; display: inline-block; float: left; margin: 0 0 10px 0; text-align:left;">
						<div style="line-height:30px; position:relative; margin:0px auto 0px 15px; display: inline-block; float: left;">
							<div>
					            <label for="textfield" style="margin-left:22px;">기간&nbsp;&nbsp;</label>
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
							</div>				            
				            
				            <div>
					            <label for="textfield">범죄유형&nbsp;&nbsp;</label>
								<select id="searchCrime" name="searchCrime" class="form-control" style="width:410px;">
									<option value="">전체</option>
									<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
										<option value="${list.codeKey}">${list.codeVal}</option>
									</c:forEach>
								</select>
							</div>
							
							<div>
								<div>
									<label for="textfield" style="margin-left:12px;">신청자&nbsp;&nbsp;</label>
									<select id="searchPosition" name="searchPosition" class="form-control" style="width:201px; ">
										<option value=''>소속 전체</option>
										<c:forEach var="list" items="${positionList}" varStatus="status">
											<option value="${list.position}">${list.position}</option>
										</c:forEach>								
									</select>&nbsp;
									<select id="searchDepart" name="searchDepart" class="form-control" style="width:201px;">
										<option value=''>과 전체</option>
									</select>&nbsp;
								</div>
								<div style="margin-left:55px;">
									<select id="searchTeam" name="searchTeam" class="form-control" style="width:201px;">
										<option value=''>계 전체</option>
									</select>&nbsp;
									<input type="text" id="searchWord" name="searchWord" class="form-control" style="width:201px;" placeholder="아이디">								
								</div>
							</div>
				    	</div>
				    	<div style="display: inline-block; position: relative; margin: 10px 0 0 19px;">
							<button class="btn btn-default" type="button" style="height:90px; width:70px;" onclick="javascript:searchResultStat()">
								<img src="/resources/images/ico_search.png" width="32" height="32" alt=""/>
							</button>
						</div>	
					</div>
					<div class="box_gray show_box">
						<ul class="show_list">
							<li class="show_item">
								<input id="allCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="allCheck" class="show_item_txt">전체항목</label>
							</li>
							<li class="show_item">
								<input id="synthesizeCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="synthesizeCheck"" class="show_item_txt">종합현황</label>
							</li>
							<li class="show_item ">
								<input id="positionCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="positionCheck" class="show_item_txt">소속별 신청현황</label>
							</li>
							<li class="show_item">
								<input id="departCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="departCheck" class="show_item_txt">부서별 신청현황</label>
							</li>
							<li class="show_item">
								<input id="userCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="userCheck" class="show_item_txt">개인별 신청현황</label>
							</li>
							<li class="show_item">
								<input id="crimeCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="crimeCheck" class="show_item_txt">범죄유형별 신청현황</label>
							</li>
							<li class="show_item">
								<input id="apprTimeCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="apprTimeCheck" class="show_item_txt">승인 소요시간</label>
							</li>
							<li class="show_item">
								<input id="playCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="playCheck" class="show_item_txt">평균 재생횟수</label>
							</li>
							<li class="show_item">
								<input id="volumeCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="volumeCheck" class="show_item_txt">파일 크기</label>
							</li>
							<li class="show_item">
								<input id="timeCheck" class="show_item_check" type="checkbox" checked/> 
								<label for="timeCheck" class="show_item_txt">범죄 발생시간</label>
							</li>
						</ul>
					</div>	        	
				</form>
				
				<!-- ajax list -->
				<div id="statArea"></div>
			
			</div>
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
