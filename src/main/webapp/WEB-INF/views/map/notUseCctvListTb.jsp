<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
	});
</script>

						<div class="mapbtn"></div>
						<table class="table table_b" style="margin-bottom:0">
								<thead>
									<tr>
										<th width="10%">순위</th>
										<th width="30%">관리번호</th>
										<th width="50%">설치주소</th>
										<th width="10%">미활용건수</th>
									</tr>
								</thead>
							</table>
							<div style="height:269px; overflow:auto">
								<table class="table table-hover text-center">
									<tbody>
									<c:forEach var="list" items="${notUseCctvList}" varStatus="status">
										<tr>
											<td width="10%">${list.rank}</td>
											<td width="30%">${list.code1}</td>
											<td width="50%">${list.address}</td>
											<td width="10%">${list.notUseCnt}건</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

