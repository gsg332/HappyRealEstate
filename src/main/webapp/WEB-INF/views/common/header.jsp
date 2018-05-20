<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Include Header -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<title><c:forEach var="list" items="${CONFIG_LIST}" varStatus="status"><c:if test="${list.itemName eq 'TITLE' }">${list.itemValue}</c:if></c:forEach></title>
	
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.11.3.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.form.js'/>"></script>
	
	<!-- jquery validate -->
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.validate.min.js'/>"></script>
	
	<!-- jquery file download -->
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.fileDownload.js'/>"></script>
	
	<!-- jquery bootpag -->
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.bootpag.min.js'/>"></script>
	
	<!-- bootstrap -->
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/resources/js/bootstrap/bootstrap.min.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/common.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/cctv_map.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/loading.css'/>" />
	
	<!-- bootstrap-datepicker -->
	<script type="text/javascript" src="<c:url value='/resources/js/datepicker/bootstrap-datepicker.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/datepicker/bootstrap-datepicker.ko.min.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/resources/js/datepicker/bootstrap-datepicker.min.css'/>" />
	
	<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
		<c:if test="${list.itemName eq 'DAUM_MAP_PUBLIC_KEY' }">
		<c:set var="MAP_KEY" value="${list.itemValue}" scope="session" />
		</c:if>
		<c:if test="${list.itemName eq 'SMS_USE' }">
		<c:set var="SMS_USE" value="${list.itemValue}" scope="session" />
		</c:if>
		<c:if test="${list.itemName eq 'ManageCall' }">
		<c:set var="TEL_manageCall" value="${list.itemValue}" scope="session" />
		</c:if>
		<c:if test="${list.itemName eq 'TechCall' }">
		<c:set var="TEL_TechCall" value="${list.itemValue}" scope="session" />
		</c:if>
	</c:forEach>
	
	<!-- Daum Map API -->
	<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=<c:out value='${MAP_KEY}' />&libraries=services,clusterer"></script>
	
	<!-- Custom Common Script -->
	<script type="text/javascript" src="<c:url value='/resources/js/common.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/map.js'/>"></script>
	<!-- console 크로스브라우징 -->
	<script type="text/javascript">
		var console = window.console || {log:function() {}}
		
		$(window).on('popstate', function(event) {
		  	var data = event.originalEvent.state;
		  	if(data){
		  		$(data.area).html(data.listPage);			  		
		  	}else{
		  		window.history.back();
		  	}
		});
	</script>
	