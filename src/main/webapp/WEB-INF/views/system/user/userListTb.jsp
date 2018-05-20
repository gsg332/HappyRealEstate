<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		pagination("${userList[0].totalPage}", "${userList[0].currentPage}");
		
		$("#selRowSize").change(function(){
			
			$("#searchUserFrm").children(":input[name=rowSize]").val($("#selRowSize option:selected").val());
			searchUserList(1);
		});			
		
		$("#userModifyForm").validate({
			submitHandler : function(){
				$('#confirmModal').modal('show');
			}
		});
		
		$("#insertUsersFrm").validate({
			submitHandler : function(){
				
				if ($("#idChk").val().trim() == ''){
					alert("ID 중복확인을 해주세요.");
					return false;
				}
				
				$('#confirmModal').modal('show');
			}
		});
			
	    $.validator.addMethod("mix_eng_num", function (value, element) {
	        return this.optional(element) || /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(value);
	    });
	});
</script>

			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">${userList[0].listCnt}건 조회&nbsp;&nbsp;|&nbsp;&nbsp;${userList[0].currentPage}/${userList[0].totalPage} 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="selRowSize" id="selRowSize" class="form-control">
						<c:forEach var="row" begin="10" end="30" step="10" varStatus="status">
							<c:choose>
								<c:when test="${row eq userList[0].rowSize}">
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
			<form id="userModifyForm" name="userModifyForm">
				<input type="hidden" id="modifyUserId" name="modifyUserId"/>
				<table class="table table-hover table_b text-center input_50">
					<thead>
						<tr>
							<th style="width:6%">번호</th>
			                <th style="width:7%">아이디</th>
			                <th style="width:9%">비밀번호</th>
			                <th style="width:8%">소속</th>
			                <th style="width:8%">과</th>
			                <th style="width:8%">계</th>
			                <th style="width:7%">이름</th>
			                <th style="width:11%">연락처</th>
			                <th style="width:8%">권한</th>
			                <th style="width:12%">비번만료일</th>
			                <th style="width:10%">SMS수신</th>
			                <c:if test="${pledgeFileRegType == '2'}">
			                	<th style="width:7%">첨부</th>
			                </c:if>
			                <th colspan="2" style="width:*">관리</th>
						</tr>
					</thead>
					<tbody>			
					<c:forEach var="list" items="${userList}" varStatus="status">
						<tr id="tr">
			                <td style="text-align: center;">${list.rnum}</td>
			                <td>${list.userId}</td>
			                <%-- <td>${list.password}</td> --%>
			                <td class="password"></td>
			                <td>
			                	<p class="position" style="width:70px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; margin: 0 0 0px;">${list.position}</p>
			                </td>
			                <td>
			                	<p class="depart" style="width:70px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; margin: 0 0 0px;">${list.depart}</p>
			                </td>		
			                <td class="team">${list.team}</td>	
			                <td class="userName">${list.userName}</td>
			                <td class="phoneNum">
			                	<c:set var="phoneNum" value="${fn:replace(list.phoneNum,'-','')}"/>
								<c:choose>
									<c:when test="${fn:length(phoneNum) == 10}">
										${fn:substring(phoneNum, 0, 3)}-${fn:substring(phoneNum, 3, 6)}-${fn:substring(phoneNum, 6, 10)}
									</c:when>
									<c:when test="${fn:length(phoneNum) == 11}">
										${fn:substring(phoneNum, 0, 3)}-${fn:substring(phoneNum, 3, 7)}-${fn:substring(phoneNum, 7, 11)}
									</c:when>
									<c:otherwise>
										${phoneNum}
									</c:otherwise>
								</c:choose>
			                </td>
			                <%-- <td>${list.level}</td> --%>
		               		<c:choose>
								<c:when test="${list.level eq 9}">
								<td class="level">관리자</td>
								</c:when>
								<c:when test="${list.level eq 8}">
								<td class="level">담당자</td>
								</c:when>	
								<c:when test="${list.level eq 7}">
								<td class="level">열람용</td>
								</c:when>													
								<c:otherwise>
								<td class="level">사용자</td>
								</c:otherwise>
							</c:choose>			                
			                <td>${list.passwordLimitDate}</td>
	     					<%-- <td>${list.smsReceive}</td> --%>
		               		<c:choose>
								<c:when test="${list.smsReceive eq 1}">
								<td class="smsReceive"><input type="checkbox" checked="checked" disabled /></td>
								</c:when>
								<c:otherwise>
								<td class="smsReceive"><input type="checkbox" disabled /></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${pledgeFileRegType == '2'}">
								<td>
									<c:forEach var="fileNm" items="${list.attachFileNm}" varStatus="fileStatus">
										<a href="<c:url value="/user/fileDown.do?itemNo=${list.attachFileNo[fileStatus.index]}"/>">
											<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${fileNm}" />
										</a>
									</c:forEach>
								</td>
							</c:if>	
							<c:choose>
								<c:when test="${list.permit eq 0}">													                	
					                <td>
										<a href="javascript:modifyUserStatus('${list.userId}', '${list.phoneNum}', '1', 'N')">승인</a>
					                </td>
					                <td>
										<a href="javascript:modifyUserStatus('${list.userId}', '${list.phoneNum}', '0', 'Y')">반려</a>
					                </td>			                
								</c:when>
								<c:otherwise>
					                <td class="modifyBtn">
					                	<button type="button" class="btn btn-default hidden" onclick="modifyUserInfo(this, '${list.userId}')">
					                		<img src="/resources/images/ico_OK.png" width="12" height="12" alt=""/>
					                	</button>
					                	<button type="button" class="btn btn-default" onclick="changeToModify(this, '${list.userId}', ${status.index})">
					                		<img src="/resources/images/modify.png" width="12" height="12" alt=""/>
					                	</button>
					                </td>
					                <td class="delBtn">	
					                	<button type="button" class="btn btn-default" onclick="deleteUserInfo('${list.userId}')">
					                		<img src="/resources/images/del.png" width="12" height="12" alt=""/>
					                	</button>			                	
					                </td>			                
								</c:otherwise>
							</c:choose>			                
			            </tr>
					</c:forEach>				
						<tr>
							<td colspan="14" style="background:#fff">
								<nav style="position:relative">
									<div id="page-selection" style="text-align: center;"></div>
								</nav>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			
			<form class="form-inline" id="insertUsersFrm" name="insertUsersFrm" method="post">
				<table class="table table_b text-center input_50" style="border-bottom:1px solid #e9e9e9">
		            <thead>
		              <tr>
		                <th>아이디</th>
		                <th>비밀번호</th>
		                <th>소속</th>
		                <th>과</th>
		                <th>계</th>
		                <th>이름</th>
		                <th>연락처</th>
		                <th>권한</th>
		                <th>추가</th>
		              </tr>
		            </thead>
		            <tbody>
		              <tr>
		                <td style="padding-bottom:5px !important">
		                	<span>
								<input type="text" id="userId" name="userId" class="form-control" style="width:50%">
							</span> 
							<button type="button" id="btn_idchk" class="btn btn-default" onclick="idDuplChk()" data-toggle="tooltip" data-placement="top" title="ID 중복체크">
								<img alt="" src="/resources/images/ico_check.png" width="18" height="18">
							</button>
							<input type="hidden" id="idChk" name="idChk" class="form-control">
						</td>
		                <td><span><input type="password" id="password" name="password" class="form-control" style="width:90%"></span></td>
		                <td><span><input type="text" id="position" name="position" class="form-control" style="width:90%"></span></td>
		                <td><span><input type="text" id="depart" name="depart" class="form-control" style="width:90%"></span></td>
		                <td><span><input type="text" id="team" name="team" class="form-control" style="width:90%"></span></td>
		                <td><span><input type="text" id="userName" name="userName" class="form-control" style="width:90%"></span></td>  
		                <td><span><input type="text" id="phoneNum" name="phoneNum" class="form-control" style="width:90%"></span></td>
		                <td>
		                <select id="level" name="level" class='form-control'>
		                	<option value="0">사용자</option>
		                	<option value="7">열람용</option>
		                	<option value="8">담당자</option>
		                	<option value="9">관리자</option>
		                </select>
		                </td>
		                <td style="width:50px">
	       		           	<button type="button" class="btn btn-default" onclick="insertUsersInfo();">
		                		<img src="/resources/images/write.png" width="16" height="16" alt=""/>
		                	</button>	
		                </td>
		              </tr>	             
		            </tbody>
		        </table>
	        </form>			