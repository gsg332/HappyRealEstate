<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Include Directive -->
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.happyJ.realestate.web.common.Config"%>
<jsp:useBean id="date" class="java.util.Date"/>

<!-- 현재 URL에서 메뉴(서비스) 저장 변수 -->
<c:set var="CURRENT_URL" value="${requestScope['javax.servlet.forward.servlet_path']}" />

<!-- Config Variable -->
<c:set var="CONFIG_LIST" value="<%= Config.configList %>" />
<c:set var="FN_CONFIG_LIST" value="<%= Config.fnConfigList %>" />

<!-- Session Variable -->
<c:set var="USER_ID" value="${sessionScope.USER_ID}" />
<c:set var="USER_LEVEL" value="${sessionScope.USER_LEVEL}" />
<c:set var="USED_CAPACITY" value="${sessionScope.USED_CAPACITY}" />
<c:set var="TOTAL_CAPACITY" value="${sessionScope.TOTAL_CAPACITY}" />

<fmt:parseNumber var="USER_LEVEL_NO" value="${USER_LEVEL}" type="number" scope="application" />

<fmt:parseDate var="USER_PASSWD_LIMIT" value="${sessionScope.USER_PASSWD_LIMIT}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="limitDate" value="${USER_PASSWD_LIMIT}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="TODAY" value="${date}" pattern="yyyy-MM-dd"/>

<c:set var="PASSWD_EXPIRED" value="N" />
<c:if test="${TODAY > limitDate}">
	<c:set var="PASSWD_EXPIRED" value="Y" />
</c:if>