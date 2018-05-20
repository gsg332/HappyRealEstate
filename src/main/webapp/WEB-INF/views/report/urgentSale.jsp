<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>

<div>
	<c:forEach var="item" items="${urgentSaleList}" varStatus="status">
		<div>
			기사번호 : ${item.articleNum} | 
			설명 : ${item.articleDescription} | 
			링크 : ${item.linkUrl} | 
			부동산유형 : ${item.reType} | 
			시도 : ${item.sido} | 
			시군구 : ${item.sgg} | 
			읍면동 : ${item.emd} | 
			리 : ${item.ri} | 
			부동산명 : ${item.reName} | 
			층 : ${item.floor} | 
			동 : ${item.dong} | 
			급매가 : ${item.urgentPrice} | 
			전세가 : ${item.junsePrice} | 
			시세평균 : ${item.marketPrice} | 
			최저가 : ${item.lowestPrice} | 
			최고가 : ${item.highestPrice} | 
			평균가 : ${item.averagePrice} | 
			공급면적 : ${item.supplyExtent} | 
			전용면적 : ${item.exclusiveExtent} | 
			중개소 : ${item.agentName} | 
			중개소연락처 : ${item.agentPhone} | 
			등록일자 : ${item.regDate}
		</div>
		<br/>
	</c:forEach>

</div>