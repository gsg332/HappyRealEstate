<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${ipList[0].totalPage}", "${ipList[0].currentPage}");
		
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchIpList(1);
		});			
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${ipList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${ipList[0].currentPage}/${ipList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq ipList[0].rowSize}">
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
			<table class="table table-hover table_b text-center lh_40">
				<thead>
					<tr>
						<th>번호</th>
		                <th>IP 사용기관</th>
		                <th>시작 IP</th>
		                <th></th>
		                <th>종료 IP</th>
		                <th>작성자</th>
		                <th>등록일시</th>
		                <th>수정일시</th>
		                <th colspan="2" >관리</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${ipList}" varStatus="status">
					<tr>
		                <td style="text-align: center;">${list.rnum}</td>
		                <td>${list.title}</td>
		                <td>${list.startIp}</td>
		                <td>~</td>
		                <td>${list.endIp}</td>
		                <td>${list.writeUser}</td>
		                <td>${list.regDate}</td>
		                <td>${list.modifyDate}</td>
		                <td>
		                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${list.no}')">
		                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
		                	</button>
		                </td>
		                <td>	
		                	<button type="button" class="btn btn-default" onclick="deleteIPInfo('${list.no}')">
		                		<img src="/resources/images/del.png" width="16" height="16" alt=""/>
		                	</button>			                	
		                </td>
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
			
			<form class="form-inline" id="insertIpFrm" name="insertIpFrm" method="post">
				<table class="table table_b text-center" style="border-bottom:1px solid #e9e9e9">
		            <thead>
		              <tr>
		                <th>IP 사용기관</th>
		                <th>허용 IP범위</th>
		                <th>추가</th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		                <td style="padding-bottom:5px !important"><input type="text" id="title" name="title" class="form-control" style="width:80%"></td>
		                <td><input type="text" id="startIp" name="startIp" class="form-control" style="width:40%">&nbsp;&nbsp; ~ &nbsp;&nbsp;
		                	<input type="text" id="endIp" name="endIp" class="form-control" style="width:40%"></td>
		                <td>
	       		           	<button type="button" class="btn btn-default" onclick="javascript:insertIpInfo()">
		                		<img src="/resources/images/write.png" width="16" height="16" alt=""/>
		                	</button>	
		                </td>
		              </tr>
	             
		            </tbody>
		        </table>
	        </form>