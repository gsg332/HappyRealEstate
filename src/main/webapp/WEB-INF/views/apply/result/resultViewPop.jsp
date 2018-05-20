<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		if ("${resultInfo.itemUse}" == "0"){
			$(".itemUseN").css("display", "none");
			$(".notUseReason").css("display", "none");
		} else {
			$(".itemUseY").css("display", "none");
			if ("${resultInfo.resultCode}" != "99999"){
				$(".notUseReason").css("display", "none");
			}
		}
		
		$("input[name=itemUse]").change(function(){
			//console.log($(this).val());
			if ($(this).val() != "0"){
				$(".itemUseY").css("display", "none");
				$(".itemUseN").css("display", "");
				//$(".spanTd").prop("colspan", "1");
				if ($("#resultCode option:selected").val() == "99999"){
					$(".notUseReason").css("display", "");
				} else {
					$(".notUseReason").css("display", "none");
				}
			} else {
				$(".itemUseN").css("display", "none");
				$(".itemUseY").css("display", "");
				$(".notUseReason").css("display", "none");
				//$(".spanTd").prop("colspan", "2");
			}
		});
		
		$("#resultCode").change(function(){
			//console.log($(this).val());
			if ($(this).val() == "99999"){
				$(".notUseReason").css("display", "");
			} else {
				$(".notUseReason").css("display", "none");
			}
		});
		
		// 달력 세팅
		$("#usedVideoStart").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			orientation : "top left",
			startDate : "${fn:substring(applyInfo.videoStart, 0, 10)}",
			endDate : "${fn:substring(applyInfo.videoEnd, 0, 10)}"
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("수정 확인");
			$this.find('.pop_title').text("이대로 수정 하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				if ($(":input:radio[id='itemUse']:checked").val() == '0' && $("#usedVideoStart").val() == '') {
					alert("사건 발생시간을 확인해 주세요.");
					return;
				}
				
				if ($("input[name=itemUse]:checked").val() == "1") {
					if ($('#resultCode').val() == "99999") {
						if ($('#resultMemo').val() == '') {
							alert('기타 사유를 입력해 주세요');
							return;
						}
					}
				}

				$("#usedVideoStart").val($("#usedVideoStart").val()+" "+$("#videoStH").val()+":"+$("#videoStM").val()+":00");
				
				$.ajax({
					type : 'post',
					url : '/apply/result/modify.json',
					dataType: 'json',
					data : $("#modifyFrm").serialize(),	
					success : function(data) {
						
						if (data.result == "SUCCESS"){
							alert("활용 결과 수정이 완료되었습니다.");
						} else {
							alert("활용 결과 수정이 실패하였습니다. \n 관리자에게 문의해 주세요.");
						}
						opener.searchApplyList(1);
						self.close();
					},
					error : function(request, status, error) {
						errorModal(request);
					},
				});
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
		});
	});
	
	/**
	 *  달력 버튼 클릭 처리
	 */
	function toggleCalendar(){
		if ($("#usedVideoStart").val() != ''){
			$("#usedVideoStart").datepicker("setDate", $("#usedVideoStart").val());
		}
		$("#usedVideoStart").focus();
	}
	
	function modifyResultInfo(){
		$('#confirmModal').modal('show');
	}
	
	function crimeAreaPop(){
		var lat = "${applyInfo.lat}", lng = "${applyInfo.lng}", videoId = "${applyInfo.videoId}";
		window.open("/map/crimeAreaPop.do?centerLat="+lat+"&centerLng="+lng+"&videoId="+videoId, getTargetName("crimeAreaPop"),
		"left=200 top=10 width=800 height=670 menubar=no status=no scrollbars=no resizable=no");
	}
	
</script>
</head>
<body>
	<form class="form-inline" id="modifyFrm" name="modifyFrm" method="post">
		<input type="hidden" id="usedVideoEnd" name="usedVideoEnd" value="${applyInfo.videoEnd}">
		<input type="hidden" id="itemSerial" name="itemSerial" value="${applyInfo.itemSerial}">
	<!--pop1 width 500px-->
	<div class="pop1">
		<div class="pop_title" style="margin-bottom:10px"> 활용결과 보기</div>
		<table class="table" style="width:480px; margin:0 auto">
			<tbody>
				<tr>
					<td class="pop_th" style="width: 130px;">사건 결과</td>
					<td colspan="2">
						<select id="itemResult" name="itemResult" class="form-control" >
						<c:forEach var="list" items="${itemResultCode}" varStatus="status">
							<c:choose>
								<c:when test="${list.codeKey eq resultInfo.itemResult}">
									<option value="${list.codeKey}" selected="selected">${list.codeVal}</option>
								</c:when>
								<c:otherwise>
									<option value="${list.codeKey}">${list.codeVal}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pop_th">CCTV 활용 여부</td>
					<td colspan="2">
						<c:forEach var="list" items="${cctvCode}" varStatus="status">
						<label class="radio-inline">
							<c:choose>
								<c:when test="${list.codeKey eq resultInfo.itemUse}">
									<input type="radio" name="itemUse" id="itemUse" value="${list.codeKey}" checked="checked"><span style="position: relative; top: 2px;">${list.codeVal}</span>
								</c:when>
								<c:otherwise>
									<input type="radio" name="itemUse" id="itemUse" value="${list.codeKey}"><span style="position: relative; top: 2px;">${list.codeVal}</span>
								</c:otherwise>
							</c:choose>
						</label>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="pop_th">파기 여부</td>
					<td colspan="2">
						<label class="radio-inline">
							<input type="radio" name="resultDisposal" id="resultDisposal" value="O"
								<c:if test="${resultInfo.resultDisposal eq 'O' }" >checked="checked" </c:if> /><span style="position: relative; top: 2px;">Y</span>
						</label>
						<label class="radio-inline">
							<input type="radio" name="resultDisposal" id="resultDisposal" value="X"
								<c:if test="${resultInfo.resultDisposal eq 'X' }" >checked="checked" </c:if> /><span style="position: relative; top: 2px;">N</span> 
						</label>
					</td>
				</tr>
				<tr class="itemUseY">
					<td class="pop_th">활용된 CCTV</td>
					<td>
						<button type="button" class="btn btn-default" style="width:140px; height:40px" onclick="crimeAreaPop()">지도에서 확인</button>
					</td>
					<td>
						<div style="width:190px; height:80px; overflow-y:auto;">
						<c:if test="${!empty applyInfo.videoId }">
							<c:set var="videoIds" value="${fn:split(applyInfo.videoId, ',') }" />
							<c:set var="usedVideoIds" value="${fn:split(resultInfo.usedVideoId, ',') }" />
							<c:forEach var="list" items="${videoIds}" varStatus="status">
								<label class="checkbox-inline" style="margin-left:10px;padding-left:0px;">&nbsp;&nbsp;
									<c:set var="chkUsed" value="false" />
									<c:forEach var="usedList" items="${usedVideoIds}" varStatus="usedStatus">
										 <c:if test="${usedList eq list}">
										 	<c:set var="chkUsed" value="true" />
										 </c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${chkUsed}">
											<input type="checkbox" id="usedVideoId" name="usedVideoId" value="${list}" checked="checked" /><span style="position: relative; top: 3px;">${list}</span>
										</c:when>
										<c:otherwise>
											<input type="checkbox" id="usedVideoId" name="usedVideoId" value="${list}" /><span style="position: relative; top: 2px;">${list}</span>
										</c:otherwise>
									</c:choose>
								</label>
							</c:forEach>
						</c:if>
						</div>
					</td>
	        	</tr>
	        	<tr class="itemUseY">
	        		<td class="pop_th">사건 발생시간</td>
	        		<td colspan="2">
		        		<input type="text" id="usedVideoStart" name="usedVideoStart" class="form-control" readonly="readonly" value="${fn:substring(resultInfo.usedVideoStart, 0, 10)}">
		        		<button class="btn btn-default" type="button" onclick="toggleCalendar()">
		        			<img src="/resources/images/ico_calendar.png" width="18" height="18" alt=""/>
		        		</button>
		        		<select id="videoStH" name="videoStH" class="form-control">
		        			<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
					    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
					    		<c:choose>
					    			<c:when test="${fn:substring(resultInfo.usedVideoStart, 11, 13) == no}">
					    				<option value="${no}" selected="selected">${no}</option>
					    			</c:when>
					    			<c:otherwise>
					    				<option value="${no}">${no}</option>
					    			</c:otherwise>
					    		</c:choose>
					    	</c:forEach>
		        		</select>
		        		:
						<select id="videoStM" name="videoStM" class="form-control">
							<c:forEach var="minute" begin="0" step="5" end="59" varStatus="status">
					    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
					    		<c:choose>
					    			<c:when test="${fn:substring(resultInfo.usedVideoStart, 14, 16) == no}">
					    				<option value="${no}" selected="selected">${no}</option>
					    			</c:when>
					    			<c:otherwise>
					    				<option value="${no}">${no}</option>
					    			</c:otherwise>
					    		</c:choose>
					    	</c:forEach>
					    </select>
				    </td>
				</tr>
				<tr class="itemUseN" >
					<td class="th">미사용 사유</td>
					<td colspan="2">
						<select id="resultCode" name="resultCode" class="form-control" style="width: 300px;">
						<c:forEach var="list" items="${resultCode}" varStatus="status">
							<c:choose>
								<c:when test="${list.codeKey eq resultInfo.resultCode}">
									<option value="${list.codeKey}" selected="selected">${list.codeVal} (${list.codeDesc})</option>
								</c:when>
								<c:otherwise>
									<option value="${list.codeKey}">${list.codeVal} (${list.codeDesc})</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr class="notUseReason">
					<td class="th">기타 사유</td>
					<td colspan="2">
						<input type="text" id="resultMemo" name="resultMemo" class="form-control" style="width: 300px;" value="${resultInfo.resultMemo}" >
					</td>
				</tr>
				<tr>
					<td class="pop_th">활용결과 입력일</td>
					<td>
						<p style="vertical-align: middle; margin-top: 6px;">${resultInfo.resultTime}</p>
					</td>
	        	</tr>
			</tbody>
		</table>
		<button type="button" class="btn btn-default" style="margin:10px; width:480px; height:30px; font-weight:bold; font-size:14px" onclick="modifyResultInfo()">수정</button>
	</div>
	</form>
	
	<!-- 연장신청 승인/미승인 Modal Start -->
	<div class="modal fade" id="confirmModal" tabindex="1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="approveModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div class="pop_title" style="margin-bottom:10px"></div>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-primary" style="width: 100px;" data-dismiss="modal">확인</button>
					<button type="button" class="btn btn-danger" style="width: 100px;" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 연장신청 내역 Modal End -->
</body>
</html>