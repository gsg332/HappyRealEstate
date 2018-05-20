<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- Include Top -->
<script type="text/javascript">
	/*
		Top Menu Handling Script 
	*/
	$(document).ready(function(){
		$(".dropdown-toggle").mouseover(function(){
			if ($(this).prop("id") != "timeSlotBtn") {
				$(this).dropdown('toggle');
			}
		}); 
		/* $(".dropdown-toggle").mouseout(function(){
			$(this).dropdown('toggle');
		}); */
		
		$('.capacityFigureText').text('(' + displayShortCapacity('${USED_CAPACITY}') + ' / ' + displayShortCapacity('${TOTAL_CAPACITY}') + ' 사용중)');
	});
	
	function moveMenu(url, pMenuId){
		
		/*
		var nrc = parseInt("${NOT_RESULT_CNT}");
		 
		if (nrc > 0){
			alert("활용결과 미등록 내역이 존재합니다. \n결과를 등록해 주세요.");
			$("#parentMenuId").val("700");
			$("#menuFrm").attr("action", "/apply/result/list.do").submit();
		} else {
			$("#parentMenuId").val(pMenuId);
			$("#menuFrm").attr("action", url).submit();
		}
		 */
		$("#parentMenuId").val(pMenuId);
		$("#menuFrm").attr("action", url).submit();
		
	}
	
	function errorModal(request){
		var error = JSON.parse(request.responseText);
		$('#errorName').html(error.SimpleName);
		$('#errorMsg').html(error.message);
		$('#errorModal').modal('show');  
	}
	
	function displayShortCapacity(capacity) {
	  	var s = ['bytes', 'kB', 'MB', 'GB', 'TB', 'PB'];
	  	var e = Math.floor(Math.log(capacity) / Math.log(1024));
	  	return (capacity / Math.pow(1024, e)).toFixed(2) + " " + s[e];
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container position_r">
			<div class="fnb">
				<a class="top-title" href="<c:url value='j_spring_security_logout' />">
					<img src="/resources/images/ico_user_logout.png" align="middle" width="32" height="32" alt="로그아웃" title="로그아웃" style="margin-right: 4px;" />로그아웃
				</a>
				<c:if test="${USER_LEVEL lt 8 }">
					<c:forEach var="menuList" items="${FIRST_MENU_LIST}" varStatus="status">
						<c:if test="${menuList.menuId eq '900' }">
							<a class="top-title" href="<c:url value='${menuList.menuUrl}' />">
								<img src="/resources/images/ico_personal.png" align="middle" width="45" height="32" alt="" style="margin-right: 4px;"/>개인정보수정
							</a>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
			<div class="navbar-header">
				<!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> 
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
				</button> -->
				<a class="navbar-brand" href="${USER_HOME}">
					<img src="/resources/images/logo.png" width="193" height="30" alt=""/>
				</a> 
			</div>
			<div id="topMenu" class="collapse navbar-collapse">
				<ul class="nav navbar-nav"> 
					<c:if test="${fn:length(FIRST_MENU_LIST) > 0}">
						<c:forEach var="menuList" items="${FIRST_MENU_LIST}" varStatus="status">
							<c:if test="${menuList.menuId ne '900' }">
								<c:set var="subMenuYn" value="false" />
								<c:set var="doneLoop" value="false" />
								<c:forEach var="subMenuList" items="${SECOND_MENU_LIST}" varStatus="subStatus">
									<c:if test="${not doneLoop}">
									<c:if test="${subMenuList.pMenuId == menuList.menuId }">
										<c:set var="subMenuYn" value="true" />
										<c:set var="doneLoop" value="true" />
									</c:if>
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${subMenuYn}">
										<c:choose>
											<c:when test="${menuList.menuId eq P_MENU_ID}">
												<li class="dropdown active fist-menu">
											</c:when>
											<c:otherwise>
												<li class="dropdown fist-menu">
											</c:otherwise>
										</c:choose>
					          				<a href="javascript:moveMenu('${menuList.menuUrl}', '${menuList.menuId}')" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					          					<c:out value="${menuList.menuNm}" /> <span class="caret"></span>
					          				</a>
											<c:if test="${fn:length(SECOND_MENU_LIST) > 0}">
												<ul class="dropdown-menu">
												<c:forEach var="subMenuList" items="${SECOND_MENU_LIST}" varStatus="subStatus">
													<sec:authorize  access="hasRole('INCON_ADMIN')">
												        <c:set var="isInconAdmin" value="true"/>
												    </sec:authorize>
													<c:choose>
												    	<c:when test="${subMenuList.menuId != 507}">
												    		<c:if test="${subMenuList.pMenuId eq menuList.menuId}">
																<li class="second-menu"><a href="javascript:moveMenu('${subMenuList.menuUrl}', '${menuList.menuId}')">${subMenuList.menuNm}</a></li>
															</c:if>
														</c:when>
														<c:otherwise>
															<c:if test="${isInconAdmin}">
																<c:if test="${subMenuList.pMenuId eq menuList.menuId}">
																	<li class="second-menu"><a href="javascript:moveMenu('${subMenuList.menuUrl}', '${menuList.menuId}')">${subMenuList.menuNm}</a></li>
																</c:if>
															</c:if>
														</c:otherwise>	
												    </c:choose>
												</c:forEach>
												</ul>
											</c:if>
										</li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${menuList.menuId eq P_MENU_ID}">
										<li class="active fist-menu">
											</c:when>
											<c:otherwise>
										<li class="fist-menu">
											</c:otherwise>
										</c:choose>
											<a href="javascript:moveMenu('${menuList.menuUrl}', '${menuList.menuId}')"><c:out value="${menuList.menuNm}" /></a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<div class="capacityArea">
				<div class="capacityText">디스크 용량</div>
				<div class="capacityPerBox">
					<c:set var="capacityPercent" value="${(USED_CAPACITY / TOTAL_CAPACITY) * 100}"/>
					<span style="width:${capacityPercent}%;" class="capacityGage"></span>
				</div>
				<div class="capacityFigureText"></div>
			</div>
		<!--/.nav-collapse --> 
		</div>
	</nav>
	<form id="menuFrm" name="menuFrm" method="post">
		<input type="hidden" id="parentMenuId" name="parentMenuId" value="" />
		<input type="hidden" id="currentMenuId" name="currentMenuId" value="" />
	</form>