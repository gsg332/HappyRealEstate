<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		searchDetailEtcReasonList(1);
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
		 	searchDetailEtcReasonList(num);
		}); 
	}
	
	/**
	 *  신청 리스트 조회
	 */
	function searchDetailEtcReasonList(pageNum){
		$("#currentPage").val(pageNum);
		var params = $("#searchFrm").serialize();
		$.ajax({
			type : 'post',
			url : '/stat/apply/detailEtcReasonListTb.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#detailEtcReasonList").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});   
	}
</script>
</head>
<body>
	<div class="pop_title" style="margin-bottom:10px"> 미활용사유 기타</div>
	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" id="currentPage" name="currentPage" value="1" >
		<input type="hidden" id="rowSize" name="rowSize" value="10" >
		<input type="hidden" id="applyYear" name="applyYear" value="${applyYear}" >
		<!-- ajax list -->
		<div id="detailEtcReasonList"></div>
	</form>
</body>
</html>
