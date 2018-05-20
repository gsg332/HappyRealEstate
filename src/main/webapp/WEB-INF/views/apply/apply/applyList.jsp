<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
	<c:if test="${list.itemName eq 'veiLimitDatetime' }">
		<c:set var="playLimitDatetime" value="${list.itemValue}" scope="page" />
	</c:if>
</c:forEach>
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
		
		$('#extendModal').on('show.bs.modal', function (event) {
			var button = $(event.relatedTarget); // Button that triggered the modal
			var recipient = button.data('whatever'); // Extract info from data-* attributes
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			/* 
			var modal = $(this);
			modal.find('.modal-title').text('New message to ' + recipient);
			modal.find('.modal-body input').val(recipient);
			*/
			$("#itemSerial").val(button.data('whatever'));
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var button = $(event.relatedTarget); // Button that triggered the modal
			var $this = $(this);
			$this.data('itemSerial', button.data('whatever'));
			$this.find('.modal-title').text("승인확인");
			$this.find('.pop_title').text("신청번호 : " + button.data('whatever') + " 승인하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				modifyAdmitStatus();
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
		});

		$('#extendAppModal').on('show.bs.modal', function (event) {
			var button = $(event.relatedTarget); // Button that triggered the modal
			$("#itemSerial").val(button.data('whatever'));
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
		
		$("#veiRejectReason").change(function(){
			
			if ($(this).val() == '기타'){
				$("#etcRejReason").removeAttr('disabled');
			} else {
				$("#etcRejReason").attr('disabled', true).val('');
			}
		});

		$(document).on("click", "a.videoDown", function(){
			$.fileDownload($(this).prop("href"), {
				successCallback : function(url){
					location.reload();
				},
				failCallback : function(responseHtml, url){

					alert("파일 다운로드 중 문제가 발생하였습니다.\n관리자에게 문의해 주세요.");
				}
			});
			
			
			return false;
		});
		
	});
	
	
	/**
	 *  신청 리스트 페이징 처리
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
		 	// apply list search ajax
		 	searchApplyList(num);
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
	 *  신청 리스트 조회
	 */
	function searchApplyList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		$.ajax({
			type : 'post',
			url : '/apply/apply/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#applyList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#applyList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/* 연장 신청 등록 */
	function registExtend(){
		
		$.ajax({
			type : 'post',
			url : '/apply/extend/regist.json',
			dataType: 'json',
			data : {
				"itemSerial" : $("#itemSerial").val(),
				"extReason" : $("#extReason").val()
			},	
			success : function(data) {
				if (data.result == "SUCCESS"){
					alert("연장 신청이 완료되었습니다.");
					searchApplyList(1);
				} else {
					alert("연장 신청이 실패하였습니다. \n 관리자에게 문의해 주세요.");
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	
	/* 연장 신청내역 상태 처리 */
	function modifyExtend(flag){
		$.ajax({
			type : 'post',
			url : '/apply/extend/modify.json',
			dataType: 'json',
			data : {
				//"idx" : $("#idx").val(),
				"itemSerial" : $("#itemSerial").val(),
				"extStatus" : flag
			},	
			success : function(data) {
				if (data.result == "SUCCESS"){
				//	alert("증거자료 제출 신청내역 상태 처리가 완료되었습니다.");
					searchApplyList(1);
				} else {
				//	alert("증거자료 제출 신청내역 상태 처리를 실패하였습니다.");
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});		
	}	
	/* 
		승인 / 반려 처리
	*/
	function modifyApplyStatus(){
		
		var msg = "";
		if(status == '2'){
			msg = "승인";
		} else {
			msg = "반려";
		}
		
		var reason;
		if ($("#veiRejectReason").val() == '기타'){
			reason = $("#etcRejReason").val()
		} else {
			reason = $("#veiRejectReason").val()
		}
		
		$.ajax({
			type : 'post',
			url : '/apply/apply/modify.json',
			dataType: 'json',
			data : {
				"itemSerial" : $("#rejItemSerial").val(),
				"userId" : $("#userId").val(),
				"phoneNum" : $("#phoneNum").val(),
				"veiStatus" : $("#veiStatus").val(),
				"veiRejectReason" : reason
			},	
			success : function(data) {

				if (data.result == "SUCCESS"){
					alert(msg + " 처리가 완료되었습니다.");
					//searchApplyList(1);
					moveMenu('/apply/apply/list.do', '${P_MENU_ID}');
				} else {
					alert(msg + " 처리가 실패하였습니다. \n 관리자에게 문의해 주세요.");
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	/*
		재신청 처리
	*/
	function registReapply(serial){
		location.href = '/apply/apply/regist.do?itemSerial='+serial;
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
	
	/**
	 *  영상다운로드  팝업
	 */
	function videoDownPop(serial){
		if(getDownloadLimitCount(serial) <= 0){
			alert('다운로드 가능 횟수가 초과되었습니다.');
			$(location).attr('href', '<c:url value="/apply/apply/list.do"/>');
			return;
		}
		window.open("/apply/apply/videoPop.do?itemSerial="+serial, getTargetName("videoDownPop"),"left=200 top=100 width=810 height=460 menubar=no status=no scrollbars=no resizable=no");
	}
	
	function getDownloadLimitCount(itemSerial){
		
		var dnLimitCnt;
		
		$.ajax({
			type : 'post',
			url : '/apply/apply/dlLimitCount.json',
			dataType: 'json',
			async : false,
			data : {itemSerial:itemSerial},	
			success : function(data) {
				dnLimitCnt = data.downloadLimitCount;
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});  
		
		return dnLimitCnt;
	}
	
	/*
 		신청 정보 상세 페이지 이동
	*/
	function moveApplyDetail(serial){
		$("#itemSerial").val(serial);
		var pageUrl = "/apply/apply/detail.do";
		searchFrm.action = pageUrl;
		searchFrm.method="post";
		searchFrm.submit();
	}
	
	/*
		신청 목록 엑셀 내보내기
	*/
	function exportExcel(){
		var pageUrl = "/apply/apply/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	/*
		첨부파일 다운로드 처리
	*/
	function fileDown(itemNo){
		location.href = '/apply/apply/fileDown.do?itemNo='+itemNo;
	}
	
	function openRejModal(serial, userId, phoneNum, status){
		
		$("#userId").val(userId);
		$("#phoneNum").val(phoneNum);
		$("#veiStatus").val(status);
		$("#rejItemSerial").val(serial);
		
		$("#rejectModal").modal('toggle');
	}
	
	function modifyAdmitStatus(type){
		$.ajax({
			type : 'post',
			url : '/apply/apply/modify.json',
			dataType: 'json',
			data : {
				"itemSerial" : $('#confirmModal').data('itemSerial'),
				"veiStatus" : '8'
			},	
			success : function(data) {
				if (data.result == "SUCCESS"){
					alert("승인 처리를 진행합니다.");
					//searchApplyList(1);
					moveMenu('/apply/apply/list.do', '${P_MENU_ID}');
				} else {
					alert("승인 처리가 실패하였습니다. \n관리자에게 문의해 주세요.");
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function clearSearchFrm(){
		moveMenu('/apply/apply/list.do', '${P_MENU_ID}');
	}
	/**
	* 상태 서치박스 중 처리후 상태값 따로 체크
	*/
	function selectStatus(val) {
		var splitVal=[];
		
		$('#searchApplyStatus').val(null);
		if (val.match(/^(apply)/)) {
			splitVal = val.split("_");
			$('#searchStatus').val("2");
			$('#searchApplyStatus').val(splitVal[1]);
		}else{
			$('#searchStatus').val(val);
		}
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
					<c:if test="${list.pMenuId eq P_MENU_ID}">
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
	        	<blockquote><p><strong>신청목록</strong></p></blockquote>
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
							<label for="textfield" style="margin-left: 32px;">상태&nbsp;&nbsp;</label>
							<!-- 처리후 상태값 변동사항  -->
							<input id="searchStatus" name="searchStatus" value="${pSearchStatus}"  type="hidden">
							<input id="searchApplyStatus" name="searchApplyStatus" value="${pSearchApplyStatus}" type="hidden">
							<select id="searchStatusValue" name="searchStatusValue" onchange="selectStatus(this.value)" class="form-control" style="width:100px">
								<option value="">전체</option>
							<c:forEach var="list" items="${statusList}" varStatus="status">
								<c:choose>
									<c:when test="${list.codeKey == pSearchStatus && pSearchApplyStatus == ''}">
										<option value="${list.codeKey}" selected="selected">${list.codeVal}</option>
									</c:when>
									<c:otherwise>
										<option value="${list.codeKey}">${list.codeVal}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:forEach var="list" items="${applyStatusList}" varStatus="status">
								<c:choose>
									<c:when test="${list.codeKey == pSearchApplyStatus}">
										<option value="apply_${list.codeKey}" selected="selected">${list.codeVal}</option>
									</c:when>
									<c:otherwise>
										<option value="apply_${list.codeKey}">${list.codeVal}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							</select> <br>
							
							<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="margin-left: 40px;width:112px;">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
								<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
							</button>
							~
							<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
								<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
							</button>
							
							<label for="textfield" style="margin-left: 12px;">범죄유형&nbsp;&nbsp;</label>
							<select id="searchCrime" name="searchCrime" class="form-control" style="width:100px">
								<option value="">전체</option>
								<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
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
					<div class="panel panel-default" style="margin-top:10px">
						<div class="panel-body text_red"><strong>주요현황</strong></div>
						<div class="panel-footer" style="background:#fff">
							<table class="table text-center" style="margin-bottom:0px">
								<thead>
									<tr>
										<th>전체</th>
										<th>당해년도</th>
										<th>대기</th>
										<th>처리중</th>
										<th>승인</th>
										<th>기타</th><!-- 서버이상일때 -->
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${totalCounts.totalCnt}</td>
										<td>${totalCounts.yearCnt}</td>
										<td>${totalCounts.waitingCnt}</td>
										<td>${totalCounts.procCnt}</td>
										<td>${totalCounts.allowCnt}</td>
										<td>${totalCounts.deniedCnt}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</form>
				
				<!-- ajax list -->
				<div id="applyList"></div>
				
				<!-- 연장 신청 Modal Start -->
				<div class="modal fade" id="extendModal" tabindex="1" role="dialog" aria-labelledby="extendModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="extendModalLabel">연장 신청</h4>
							</div>
							<div class="modal-body">
								<div class="pop_title" style="margin-bottom:10px">신청일로부터 ${playLimitDatetime}일 뒤로 기간이 연장됩니다.</div>
								<table class="table" style="width:850px; margin:0 auto; border-bottom:1px solid #ddd">
									<tbody>
										<tr>
											<td class="th" style="border-bottom:1px solid #ddd">연장 사유</td>
											<td><textarea class="form-control" rows="3" style="float:left; width:683px" id="extReason"></textarea>
											&nbsp;
											<button type="button" class="btn btn-default" style="height:61px" onclick="registExtend()" data-dismiss="modal">연장신청</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- 연장 신청 Modal End -->
				
				<!-- 반려 사유 Modal Start -->
				<div class="modal fade" id="rejectModal" tabindex="1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="rejectModalLabel">반려 사유 선택</h4>
							</div>
							<div class="modal-body">
								<div class="pop_title" style="margin-bottom:10px">반려 사유를 선택해 주세요.</div>
								<form id="rejectFrm" name="rejectFrm" method="post">
									<input type="hidden" id="userId" name="userId">
									<input type="hidden" id="phoneNum" name="phoneNum">
									<input type="hidden" id="veiStatus" name="veiStatus">
									<input type="hidden" id="rejItemSerial" name="rejItemSerial">
								</form>
								<table class="table" style="width:850px; margin:0 auto; border-bottom:1px solid #ddd">
									<tbody>
										<tr>
											<td class="th" style="border-bottom:1px solid #ddd">반려 사유</td>
											<td>
												<select class="form-control" id="veiRejectReason" name="veiRejectReason" style="width:650px">
												<c:forEach var="list" items="${rejReasonList}" varStatus="status">
													<option value="${list.codeVal}">${list.codeVal}(${list.codeDesc})</option>
												</c:forEach>
												</select>
											</td>
											<td rowspan="2">
												<button type="button" class="btn btn-default" style="height:72px; width:72px;" onclick="modifyApplyStatus()" data-dismiss="modal">등록</button>
											</td>
										</tr>
										<tr>
											<td class="th" style="border-bottom:1px solid #ddd">기타 사유</td>
											<td>
												<input type="text" class="form-control" style="width:650px" id="etcRejReason" name="etcRejReason" disabled="disabled">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- 반려 사유 Modal End -->
				
				<!-- 연장신청 승인/미승인 Modal Start -->
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
								<button type="button" class="btn btn-danger" style="width: 100px;" onclick="modifyExtend('2')" data-dismiss="modal">미승인</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 연장신청 내역 Modal End -->
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
