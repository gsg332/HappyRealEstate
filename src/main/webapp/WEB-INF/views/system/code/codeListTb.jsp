<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${searchGroupList[0].totalPage}", "${searchGroupList[0].currentPage}");
		
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchCodeList(1);
		});			
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${searchGroupList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${searchGroupList[0].currentPage}/${searchGroupList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq searchGroupList[0].rowSize}">
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
						<th style="width:10%">순번</th>
		                <th style="width:30%">값</th>
		                <th style="width:48%">설명</th>
		                <th colspan="2" style="width:12%">관리</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${searchGroupList}" varStatus="status">
					<tr>
		                <td style="text-align: center;">${list.rnum}</td>
		                <td>${list.codeVal}</td>
		                <td>${list.codeDesc}</td>
		                <td>
		                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${list.codeGroup}', '${list.codeKey}')">
		                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
		                	</button>
		                </td>
		                <td>	
		                	<button type="button" class="btn btn-default" onclick="deleteCodeInfo('${list.codeGroup}', '${list.codeKey}')">
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

			<form class="form-inline" id="insertCodeFrm" name="insertCodeFrm" method="post">
				<input type="hidden" name="codeFixed" id="codeFixed" value="N">
				<c:forEach var="list" items="${searchGroupList}" varStatus="status">
					<c:if test="${status.last}">	
					<input type="hidden" name="codeGroup" id="codeGroup" value="${list.codeGroup}">
					<input type="hidden" name="codeKey" id="codeKey" value="${list.codeKey +1}">					
					</c:if>
				</c:forEach>
				<table class="table table_b text-center" style="border-bottom:1px solid #e9e9e9">
		            <thead>
		              <tr>
		                <th style="width:30%">값</th>
		                <th style="width:50%">설명</th>
		                <th style="width:20%">추가</th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		                <td style="padding-bottom:5px !important"><input type="text" id="codeVal" name="codeVal" class="form-control" style="width:80%"></td>
		                <td><input type="text" id="codeDesc" name="codeDesc" class="form-control" style="width:90%"></td>
		                <td>
	       		           	<button type="button" class="btn btn-default" onclick="javascript:insertCodeInfo()">
		                		<img src="/resources/images/write.png" width="16" height="16" alt=""/>
		                	</button>	
		                </td>
		              </tr>
		            </tbody>
		        </table>
	        </form>