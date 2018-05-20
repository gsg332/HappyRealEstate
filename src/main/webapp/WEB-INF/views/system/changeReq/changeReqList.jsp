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
		
		$("#veiUnApprovalReason").change(function(){
			if ($(this).val() == '기타'){
				$("#etcUnApprovalReason").removeAttr('disabled');
			} else {
				$("#etcUnApprovalReason").attr('disabled', true).val('');
			}
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);

			$this.find('.modal-title').text("승인 확인");
			$this.find('.pop_title').text("승인 하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				var officialFileTempNm;
				var pledgeFileTempNm;
				var requestValues = $('#requestValues').val().split('|'); 
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
					url : '/changeReq/modifyChangeReqStatus.json',
					dataType: 'json',
					data : {
						no : $this.data('no'), 
						itemId : $this.data('itemId'),
						officialFileTempNm : officialFileTempNm,
						pledgeFileTempNm : pledgeFileTempNm,
						typeDepth1 : $this.data('typeDepth1'),
						status : $this.data('type') == 'approval' ? 1001 : 1002 
					},	
					success : function(data) {
						if(data.result == 'SUCCESS'){
							alert($this.data('msg') + ' 처리 되었습니다.');	
						}else{
							alert($this.data('msg') + ' 처리를 실패하였습니다. \n 관리자에게 문의해 주세요.');
						}
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
			url : '/system/changeReq/list.json',
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
		 	// unApproval list search ajax
		 	searchChangeReqList(num);
		}); 
	}
	
	function exportExcel(){
		var pageUrl = "/system/changeReq/excel.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	function modifyChangeReqStatus(typeDepth1, type, no, itemId){
		if(type == 'approval'){
			$('#confirmModal').data('msg', '승인');
			$('#confirmModal').data('typeDepth1',typeDepth1);
			$('#confirmModal').data('type', type);
			$('#confirmModal').data('no', no);
			$('#confirmModal').data('itemId', itemId);
			$('#confirmModal').modal('show');
		}else{
			var msg = '미승인';
			no = $('#no').val();
			itemId = $('#itemId').val();
			var unapprovalReason = $("#veiUnApprovalReason").val() +  ($("#veiUnApprovalReason").val() == '기타' ? '(' + $('#etcUnApprovalReason').val() + ')' : '');
			
			var officialFileTempNm;
			var pledgeFileTempNm;
			var requestValues = $('#requestValues').val().split('|');
			
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
				url : '/changeReq/modifyChangeReqStatus.json',
				dataType: 'json',
				data : {
					no:no, 
					itemId:itemId,
					typeDepth1:typeDepth1,
					status: type == 'approval' ? 1001 : 1002, 
					unapprovalReason:unapprovalReason ,
					officialFileTempNm:officialFileTempNm,
					pledgeFileTempNm:pledgeFileTempNm
				},	
				success : function(data) {
					if(data.result == 'SUCCESS'){
						alert(msg + ' 처리 되었습니다.');	
					}else{
						alert(msg + ' 처리를 실패하였습니다. \n 관리자에게 문의해 주세요.');
					}
					searchChangeReqList(1);
				},
				error : function(request, status, error) {
					errorModal(request);
				},
			});
		}
	}
	
	function clearSearchFrm(){
		location.reload() ;
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
	        	<blockquote><p><strong>변경요청내역</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post" class="form-inline">		
					<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
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
							<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="margin-left: 40px;width:112px;">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
								<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
							</button>
							~
							<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:112px">
							<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
								<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
							</button>
							<label for="textfield" style="margin-left: 12px;">변경메뉴&nbsp;&nbsp;</label>
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
								<input id="searchUserid" name="searchUserid" type="text" class="form-control" placeholder="아이디" style="width:135px">
							</c:if>
						</div>
					</div>
				</form>
				<!-- ajax list -->
				<div id="changeReqList"></div>
				
				<!-- 미승인 사유 Modal Start -->
				<div class="modal fade" id="unApprovalModal" tabindex="1" role="dialog" aria-labelledby="unApprovalModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="unApprovalReason">미승인 사유 선택</h4>
							</div>
							<div class="modal-body">
								<div class="pop_title" style="margin-bottom:10px">미승인 사유를 선택해 주세요.</div>
								<form id="unApprovalFrm" name="unApprovalFrm" method="post">
									<input type="hidden" id="no" name="no">
									<input type="hidden" id="itemId" name="itemId">
									<input type="hidden" id="typeDepth1" name="typeDepth1">
									<input type="hidden" id="requestValues" name="requestValues">
								</form>
								<table class="table" style="width:850px; margin:0 auto; border-bottom:1px solid #ddd">
									<tbody>
										<tr>
											<td class="th" style="border-bottom:1px solid #ddd">미승인 사유</td>
											<td>
												<select class="form-control" id="veiUnApprovalReason" name="veiUnApprovalReason" style="width:650px">
												<c:forEach var="list" items="${unApprovalReasonList}" varStatus="status">
													<option value="${list.codeVal}">${list.codeVal}(${list.codeDesc})</option>
												</c:forEach>
												</select>
											</td>
											<td rowspan="2">
												<button type="button" class="btn btn-default" style="height:72px; width:72px;" onclick="modifyChangeReqStatus($('#typeDepth1').val(), 'unApproval')" data-dismiss="modal">등록</button>
											</td>
										</tr>
										<tr>
											<td class="th" style="border-bottom:1px solid #ddd">기타 사유</td>
											<td>
												<input type="text" class="form-control" style="width:650px" id="etcUnApprovalReason" name="etcUnApprovalReason" disabled="disabled">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- 반려 사유 Modal End -->
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
