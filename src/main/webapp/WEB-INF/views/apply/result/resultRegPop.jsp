<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
	<c:if test="${list.itemName eq 'veiLimitDatetime' }">
		<c:set var="veiLimitDatetime" value="${list.itemValue}"/>
	</c:if>
</c:forEach> 
<script type="text/javascript">
	var resultMemoCheck = false;
	
	$(document).ready(function(){
		
		if("${applyInfo.veiApplyStatus}" == '1'){
			$('.nav.nav-tabs li a').eq(0).click();
			//$('.nav.nav-tabs').addClass('hidden');
			$('.nav.nav-tabs li a').eq(1).hide();
		}
		$('.pop1').removeClass('hidden')
		
		$(".itemUseN").css("display", "none");
		$(".notUseReason").css("display", "none");
		
		$("input[name=itemUse]").change(function(){
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
			var type = $this.data('type');

			$this.find('.modal-title').text("등록 확인");
			
			if($('.nav.nav-tabs').find('li.active a').attr('href') == '#tab1'){
				$this.find('.pop_title').text("활용 결과를 등록 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					registResultInfo();
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});				
			}else{
				$this.find('.pop_title').text("수사중으로 등록 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					registInvestigating();
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}
		});
		
		// Form Validate Check
		$("#registFrm").validate({
			submitHandler : function(){
				$('#confirmModal').modal('show');
			},
			rules : {
				itemUse : {
					required : true
				},
				resultDisposal : {
					required : true
				},
				resultMemo : {
					required : function () {
						if ($("input[name=itemUse]:checked").val() == "1") {
							if ($('#resultCode').val() == "99999") {
								return true;
							}
							return false;
						} else {
							return false;
						}
					}
				}
			},
			messages : {
				itemUse : {
					required : "필수 입력 항목 입니다."
				},
				resultDisposal : {
					required : "필수 입력 항목 입니다."
				},
				resultMemo : {
					required : "필수 입력 항목 입니다."
				}
			}
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
	
	function registInvestigating(){
		$.ajax({
			type : 'post',
			url : '/apply/result/registInvestigating.json',
			dataType: 'json',
			data : $("#registFrm").serialize(),	
			success : function(data) {
				if (data.result == "SUCCESS"){
					alert("결과 등록기간이 " + (parseInt('${veiLimitDatetime}') * 2)  + "일 연장되었습니다.");
				} else {
					alert("등록이 실패하였습니다. \n 관리자에게 문의해 주세요.");
				}
				opener.searchApplyList(1);
				self.close();
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});
	}
	
	function registResultInfo(){
		$("#usedVideoStart").val($("#usedVideoStart").val()+" "+$("#videoStH").val()+":"+$("#videoStM").val()+":00");

		var url;
		if(parseInt('${applyInfo.veiApplyStatus}') == 1){
			url = '/apply/result/modify.json';
		}else{
			url = '/apply/result/regist.json';
		}
		
		$.ajax({
			type : 'post',
			url : url,
			dataType: 'json',
			data : $("#registFrm").serialize(),	
			success : function(data) {
				if (data.result == "SUCCESS"){
					alert("활용 결과 등록이 완료되었습니다.");
				} else {
					alert("활용 결과 등록이 실패하였습니다. \n 관리자에게 문의해 주세요.");
				}
				
				opener.searchApplyList(1);
				
				self.close();
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});
	}
	
	function crimeAreaPop(){
		var lat = "${applyInfo.lat}", lng = "${applyInfo.lng}", videoId = "${applyInfo.videoId}";
		window.open("/map/crimeAreaPop.do?centerLat="+lat+"&centerLng="+lng+"&videoId="+videoId, getTargetName("crimeAreaPop"),
		"left=200 top=10 width=800 height=670 menubar=no status=no scrollbars=no resizable=no");
	}
	
</script>
</head>
<body>
	<form class="form-inline" id="registFrm" name="registFrm" method="post">
		<input type="hidden" id="itemSerial" name="itemSerial" value="${applyInfo.itemSerial}">
		<!--pop1 width 500px-->
		<div class="pop1 hidden">
			<div class="pop_title" style="margin-bottom:10px">활용결과등록</div>
			
			<div style="margin:10px 0 0 10px;">
				<ul class="nav nav-tabs" style="margin-top: 25px;">
					<li class="active"><a data-toggle="tab" href="#tab1">활용결과등록</a></li>
				    <li><a data-toggle="tab" href="#tab2">수사중</a></li>
				</ul>
				  
				<div class="tab-content">
				    <div id="tab1" class="tab-pane fade in active">
				    	<input type="hidden" id="usedVideoEnd" name="usedVideoEnd" value="${applyInfo.videoEnd}">
				      	<table class="table" style="width:480px; margin:0 auto; margin-top:10px;">
							<tbody>
								<tr>
									<td class="pop_th" style="width: 130px;">사건 결과</td>
									<td colspan="2">
										<select id="itemResult" name="itemResult" class="form-control" >
										<c:forEach var="list" items="${itemResultCode}" varStatus="status">
											<option value="${list.codeKey}">${list.codeVal}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="pop_th">CCTV 활용 여부</td>
									<td colspan="2">
										<c:forEach var="list" items="${cctvCode}" varStatus="status">
										<label class="radio-inline">
											<input type="radio" name="itemUse" id="itemUse" value="${list.codeKey}"><span style="position: relative; top: 2px;">${list.codeVal}</span>
										</label>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td class="pop_th">파기 여부</td>
									<td colspan="2">
										<label class="radio-inline">
											<input type="radio" name="resultDisposal" id="resultDisposal" value="O"><span style="position: relative; top: 2px;">Y</span> 
										</label>
										<label class="radio-inline">
											<input type="radio" name="resultDisposal" id="resultDisposal" value="X"><span style="position: relative; top: 2px;">N</span> 
										</label>
									</td>
								</tr>
								<tr class="itemUseY">
									<td class="pop_th">활용된 CCTV</td>
									<td>
										<button type="button" class="btn btn-default" style="width:140px; height:40px" onclick="crimeAreaPop()">지도에서 확인</button>
									</td>
									<td>
										<div style="width:190px; height:80px; overflow:auto;">
										<c:if test="${!empty applyInfo.videoId }">
											<c:set var="videoIds" value="${fn:split(applyInfo.videoId, ',') }" />
											<c:forEach var="list" items="${videoIds}" varStatus="status">
												<label class="checkbox-inline" style="margin-left:10px;padding-left:0px;">&nbsp;&nbsp;
													<input type="checkbox" id="usedVideoId" name="usedVideoId" value="${list}"><span style="position: relative; top: 3px;">${list}</span> 
												</label>
											</c:forEach>
										</c:if>
										</div>
									</td>
					        	</tr>
					        	<tr class="itemUseY">
					        		<td class="pop_th">사건 발생시간</td>
					        		<td colspan="2">
						        		<input type="text" id="usedVideoStart" name="usedVideoStart" class="form-control" readonly="readonly" value="${fn:substring(applyInfo.videoStart, 0, 10)}">
						        		<button class="btn btn-default" type="button" onclick="toggleCalendar()">
						        			<img src="/resources/images/ico_calendar.png" width="18" height="18" alt=""/>
						        		</button>
						        		<select id="videoStH" name="videoStH" class="form-control">
						        			<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
									    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
									    		<c:choose>
									    			<c:when test="${fn:substring(applyInfo.videoStart, 11, 13) == no}">
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
									    			<c:when test="${fn:substring(applyInfo.videoStart, 14, 16) == no}">
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
										<select id="resultCode" name="resultCode" class="form-control" style="width: 300px;" >
										<c:forEach var="list" items="${resultCode}" varStatus="status">
											<option value="${list.codeKey}">${list.codeVal} (${list.codeDesc})</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr class="notUseReason">
									<td class="th">기타 사유</td>
									<td colspan="2">
										<input type="text" id="resultMemo" name="resultMemo" class="form-control" style="width: 300px;" >
									</td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="btn btn-default" style="margin:10px; width:480px; height:30px; font-weight:bold; font-size:14px">등록</button>
				    </div>
				    <div id="tab2" class="tab-pane fade">
		    			<!-- <input type="hidden" id="veiApplyStatus" name="veiApplyStatus" value="1"> -->
				    	<table class="table" style="width:480px; margin:0 auto; margin-top:10px;">
							<tbody>
								<tr>
									<td class="pop_th" style="width: 130px; line-height: 47px;">
										수사중으로 활용결과 등록이 불가한 경우,
										<br/>
										하단 버튼을 클릭하시면 결과 등록기간이 <font style="color: #ff0000;">${veiLimitDatetime * 2}일 연장</font>됩니다.
										<br/>
										단, 결과 등록기간 연장은 <font style="color: #ff0000;">건당 1회</font>로 제한되며,
										<br/>
										기한 내 수사완료 결과를 등록하지 않는 경우,
										<br/>
										영상정보 신청이 불가합니다.								
									</td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="btn btn-default" style="margin:13px 10px 10px 10px; width:480px; height:30px; font-weight:bold; font-size:14px">결과 등록기간 연장</button>
				    </div>
			  	</div>
			</div>
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