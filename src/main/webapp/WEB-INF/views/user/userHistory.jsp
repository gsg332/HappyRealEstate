<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$(".datepicker").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
			todayHighlight : true
			
		});
		
		$(".datepicker").change(function(){
			
		});
		
		searchUserHistoryList();
	});
	
	function searchUserHistoryList(){
		var params = $("#searchFrm").serialize();
		$.ajax({
			type : 'post',
			url : '/user/history/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#userHistoryList").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			},
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
		    $("#currentPage").val(num); // or some ajax content loading...
		 	// list search ajax
		 	searchUserHistoryList();
		}); 
	}
	
</script>
<!-- Content Area -->
<div class="container">
	<div class="row" style="margin-top:81px;">
		<!--left menu start-->
		<div class="col-md-2">
			<div class="slnb list-group">
				<div class="slnb_title3"></div>
				<a href="<c:url value='/user/modify.do' />" class="list-group-item">개인정보수정</a>
				<%-- <a href="<c:url value='/user/history.do' />" class="list-group-item active">변경요청내역</a> --%>
			</div>
		</div>
		<!--content start-->
		<div class="col-md-10">
			<blockquote><p><strong>변경요청내역</strong></p></blockquote>
			<form class="form-inline" id="searchFrm" name="searchFrm" method="post">
				<div class="box_gray" style="margin:0px">
					<div style="line-height:30px; position:relative; width:490px; margin:0 auto">
						
						<label for="textfield">기간&nbsp;&nbsp;</label>
						<button type="button" class="btn btn-primary">당일</button>
						<button type="button" class="btn btn-default">1주</button>
						<button type="button" class="btn btn-default">2주</button>
						<button type="button" class="btn btn-default">1개월</button>
						<button type="button" class="btn btn-default">6개월</button>
						<button type="button" class="btn btn-default">12개월</button>
						
						<label for="textfield">&nbsp;&nbsp;&nbsp;&nbsp;상태&nbsp;&nbsp;</label>
						<select id="searchStatus" name="searchStatus" class="form-control" style="width:100px">
							<option value="">전체</option>
						<c:forEach var="list" items="${statusList}" varStatus="status">
							<option value="${list.codeKey}">${list.codeVal}</option>
						</c:forEach>
						</select> <br>
						
						<input type="text" id="searchStDate" name="searchStDate" class="form-control datepicker" style="width:108px" placeholder="2016-04-20">
						<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
							<img src='<c:url value="/resources/images/ico_calendar.png" />' width="18" height="18" alt=""/>
						</button>
						~
						<input type="text" id="searchEdDate" name="searchEdDate" class="form-control datepicker" style="width:107px" placeholder="2016-04-20">
						<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
							<img src="<c:url value="/resources/images/ico_calendar.png" />" width="18" height="18" alt=""/>
						</button>
						
						<label for="textfield">&nbsp;&nbsp;&nbsp;&nbsp;변경메뉴&nbsp;&nbsp;</label>
						<select id="searchCrime" name="searchCrime" class="form-control" style="width:100px">
							<option value="">전체</option>
							<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
								<option value="${list.codeKey}">${list.codeVal}</option>
							</c:forEach>
						</select>
						
						<div style="position:absolute; right:-58px; top:1px">
							<button class="btn btn-default" type="button" style="height:64px" onclick="javascript:searchApplyList()">
								<img src='<c:url value="/resources/images/ico_search.png" />' width="32" height="32" alt="" />
							</button>
						</div>
					</div>
				</div>
			</form>
			<div id="userHistoryList"></div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
