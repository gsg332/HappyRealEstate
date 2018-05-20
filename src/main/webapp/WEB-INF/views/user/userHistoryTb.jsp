<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		
	});
</script>
			<dl class="dl-horizontal" style="position:relative; height:40px">
				<dt style="padding-top:10px">00건 조회&nbsp;&nbsp;|&nbsp;&nbsp;0/0 페이지 </dt>
				<dd style="position:absolute; right:0px; top:10px">
					<select name="select2" class="form-control">
						<option>10건 보기</option>
					</select>
				</dd>
			</dl>
			<table class="table table-hover table_b text-center">
				<thead>
					<tr>
						<th>번호</th>
						<th>변경요청일</th>
						<th>승인일</th>
						<th>미승인 사유</th>
						<th>변경메뉴</th>
						<th>상세내용</th>
						<th>상태</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
					<tr>
		            	<td>50.360</td>
		            	<td>2016-05-30</td>
		            	<td>2016-05-30</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td class="alert-info">대기</td>
		            	<td>취소</td>
		            </tr>
					<tr>
		            	<td>50.360</td>
		            	<td>2016-05-30</td>
		            	<td>2016-05-30</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td class="alert-danger">미승인</td>
		            	<td>취소</td>
		            </tr>
					<tr>
		            	<td>50.360</td>
		            	<td>2016-05-30</td>
		            	<td>2016-05-30</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td>&nbsp;</td>
		            	<td class="alert-success">승인</td>
		            	<td>&nbsp;</td>
		            </tr>
		            
					<tr>
						<td colspan="5" style="background:#fff">
							<nav style="position:relative">
								<div id="page-selection" style="text-align: center;"></div>
								<p style="position:absolute; right:0px; top:20px">
									<button type="button" class="btn btn-default" style="background:#fff">
										<img src="<c:url value='/resources/images/ico_excel.png' />" width="22" height="22" alt=""/>&nbsp;&nbsp;엑셀다운로드
									</button>
								</p>
							</nav>
						</td>
					</tr>
				 
				</tbody>
			</table>
