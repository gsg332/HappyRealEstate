<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	$(document).ready(function(){
		pagination("${detailEtcReasonList[0].totalPage}", "${detailEtcReasonList[0].currentPage}");
		
		$("#selRowSize").change(function(){
			
			//console.log($("#selRowSize option:selected").val());
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchDetailEtcReasonList(1);
		});
	});
</script>
</head>
<body>
	<div class="tableSideOffset">
		<dl class="dl-horizontal dl-set">
			<dt style="padding-top:10px">${detailEtcReasonList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${detailEtcReasonList[0].currentPage}/${detailEtcReasonList[0].totalPage} 페이지 </dt>
			<dd style="position:absolute; right:0px; top:10px">
				<select name="selRowSize" id="selRowSize" class="form-control">
					<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
						<c:choose>
							<c:when test="${row eq detailEtcReasonList[0].rowSize}">
								<option value="${row}" selected>${row}건 보기</option>
							</c:when>
							<c:otherwise>
								<option value="${row}">${row}건 보기</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</dd>
		</dl>
		
		<table class="table table-hover table_b text-center">
			<thead>
				<tr>
					<th width="15%">번호</th>
					<th width="85%">내용</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${detailEtcReasonList}" varStatus="status">
					<tr>
						<td>${list.itemSerial}</td>
						<td>${list.resultMemo}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="12" style="background:#fff">
						<nav style="position:relative">
							<div id="page-selection" style="text-align: center;"></div>
						</nav>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
