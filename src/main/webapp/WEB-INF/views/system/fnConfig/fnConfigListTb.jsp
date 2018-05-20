<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${fnConfigList[0].totalPage}", "${fnConfigList[0].currentPage}");
		
		// 건수 보기 변경 처리
		$("#selRowSize").change(function(){
			
			$("#rowSize").val($("#selRowSize option:selected").val());
			searchFnConfigList(1);
		});
		
	});
</script>
			<div style="margin: 20px 0 5px 10px; line-height: 15px; font-size: 11px;">
				<font style="font-weight: bold; font-size: 14px; margin-bottom:5px; display: inline-block">[주의사항]</font>
				<br/>
				- 기능설정은 개발 담당자를 위한 설정으로 목록 삭제 및 수정 시 오류가 발생할 수 있으니 주의.
			</div>
			
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${fnConfigList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${fnConfigList[0].currentPage}/${fnConfigList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq fnConfigList[0].rowSize}">
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
			<table class="table table_b text-center lh_40">
				<thead>
					<tr>
						<th width="10%">그룹</th>
		                <th width="30%">설명</th>
		                <th width="25%">값</th>
		                <th width="25%">Map Key</th>
		                <th colspan="2" width="*">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fnConfigItem" items="${fnConfigList}" varStatus="fnConfigStatus">
						<tr>
							<td colspan="6">
								<table class="text-center" style="width:100%;">
									<tbody>
										<tr style="background:#e6e6e6;">
											<td width="10%" style="font-weight:bold; font-size:15px;">
												FN-${fnConfigItem.itemIndex}
											</td>
							                <td width="30%">
							                	<div class="description" style="line-height: 30px;">
							                		${fnConfigItem.itemDescription}
							                	</div>
							                </td>
							                <td width="25%">
							                	<div class="value">
							                		${fnConfigItem.itemValue}
							                	</div>
							                </td>
							                <td width="25%">
							                	<div class="code">
							                		${fnConfigItem.itemCode}
							                	</div>
							                </td>
							                <td width="*">
							                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${fnConfigItem.itemIndex}')">
							                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
							                	</button>
							                </td>
							                <td width="*">
							                	<button type="button" class="btn btn-default" onclick="deleteFnConfigInfo('${fnConfigItem.itemIndex}')">
							                		<img src="/resources/images/del.png" width="16" height="16" alt=""/>
							                	</button>		
							                </td>
										</tr>
										<c:forEach var="subFnConfigItem" items="${fnConfigItem.subConfigList}" varStatus="subConfigStatus">
											<tr>
												<td style="text-align: center;">
													<span style="position:relative; left:30px; font-weight:bold;">
														추가 설정
													</span>
												</td>
								                <td>
								                	<div class="description" style="line-height: 30px;">
								                		${subFnConfigItem.itemDescription}
								                	</div>
								                </td>
								                <td>
								                	<div class="value">
								                		${subFnConfigItem.itemValue}
								                	</div>
								                </td>
								                <td>
								                	<div class="code">
								                		${subFnConfigItem.itemCode}
								                	</div>
								                </td>
								                <td>
								                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${subFnConfigItem.itemIndex}')">
								                		<img src="/resources/images/modify.png" width="16" height="16" alt=""/>
								                	</button>
								                </td>
								                <td>
								                	<button type="button" class="btn btn-default" onclick="deleteFnConfigInfo('${subFnConfigItem.itemIndex}')">
								                		<img src="/resources/images/del.png" width="16" height="16" alt=""/>
								                	</button>
								                </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
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
			
			<form class="form-inline" id="insertFnConfigFrm" name="insertFnConfigFrm" method="post">
				<table class="table table_b text-center" style="border-bottom:1px solid #e9e9e9">
		            <thead>
		              <tr>
		              	<th style="width:10%">그룹</th>
		              	<th style="width:30%">설명</th>
		                <th style="width:25%">값</th>
		                <th width="25%">Map Key</th>
		                <th style="width:10%">추가</th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		              	<td>
		              		<select name="pItemIndex" id="pItemIndex" class="form-control">
		              			<option value="">신규</option>
								<c:forEach var="fnConfigItem" items="${fnConfigList}" varStatus="status">
									<c:if test="${empty fnConfigItem.pItemIndex}">
										<option value="${fnConfigItem.itemIndex}">FN-${fnConfigItem.itemIndex}</option>
									</c:if>
								</c:forEach>
							</select>
		              	</td>
		              	<td><input type="text" id="itemDescription" name="itemDescription" class="form-control" style="width:90%"></td>
		                <td style="padding-bottom:5px !important"><input type="text" id="itemValue" name="itemValue" class="form-control" style="width:80%"></td>
		                <td style="padding-bottom:5px !important"><input type="text" id="itemCode" name="itemCode" class="form-control" style="width:80%"></td>
		                <td>
	       		           	<button type="button" class="btn btn-default" onclick="javascript:insertFnConfigInfo()">
		                		<img src="/resources/images/write.png" width="16" height="16" alt=""/>
		                	</button>	
		                </td>
		              </tr>
		            </tbody>
		        </table>
	        </form>
