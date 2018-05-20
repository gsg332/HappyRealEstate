<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
	<c:if test="${list.itemName eq 'veiLimitCount' }">
		<c:set var="playLimitCount" value="${list.itemValue}" scope="page" />
	</c:if>
	<c:if test="${list.itemName eq 'veiLimitDatetime' }">
		<c:set var="playLimitDatetime" value="${list.itemValue}" scope="page" />
	</c:if>
</c:forEach>
<script type="text/javascript">

	if('${notExistResultCnt}' > 0){
		alert('영상정보 활용결과 미등록이 ${notExistResultCnt}건 있습니다. 영상정보신청은 활용결과 등록 후 이용할 수 있습니다.');
		location.href = '<c:url value="/apply/result/list.do"/>'; 
	}

	$(document).ready(function(){
		
		var officialFileSize;
		var pledgeFileSize;
		
		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		var reApplyTrue = $("#reApplyTrue").val() || false;
		if (reApplyTrue == "true") {
			reApplyTrue = true;
		}
		
		// 요청 시작 날짜 달력 세팅
		$("#videoStCal").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : "2013-01-01",
			endDate : fromEndDate
			
		}).on('changeDate', function(selected){
			startDate = new Date(selected.date.valueOf());
			startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			$("#videoEdCal").datepicker('setStartDate', startDate);
		});
		
		// 요청 종료 날짜 달력 세팅
		$("#videoEdCal").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : toEndDate
		}).on('changeDate', function(selected){
			fromEndDate = new Date(selected.date.valueOf());
			fromEndDate.setDate(fromEndDate.getDate(new Date(selected.date.valueOf())));
			$("#videoStCal").datepicker('setEndDate', fromEndDate);
		});
		
		/* 공문 파일 첨부  */
		$("#officialFile").change(function(event){
			officialFileSize = $(this)[0].files[0].size;
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#officialFileNm").val(fileInfo);
		});
		
		/* 보안서약서 파일 첨부 */
		$("#pledgeFile").change(function(event){
			pledgeFileSize = $(this)[0].files[0].size;
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#pledgeFileNm").val(fileInfo);
		});

		if ("${applyInfo}" == ""){
			$("#reqReasoncodeReason").css("display", "none");
		} else {
			if ("${applyInfo.reqReasonCode}" == "99999") {
				$("#reqReasoncodeReason").css("display", "line");
			} else {
				$("#reqReasoncodeReason").css("display", "none");
			}
		}
		
		$("#reqReasonCode").change(function(){
			if ($(this).val() == '99999'){
				$("#reqReasoncodeReason").css("display", "");
			} else {
				$("#reqReasoncodeReason").css("display", "none");
			}
		})
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			$this.find('.modal-title').text("신청 확인");
			$this.find('.pop_title').text("영상 정보를 신청 하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				registApply();
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
		});

		$.validator.addMethod("officialFileSizeCheck", function(value, element, regexpr) {
			return officialFileSize == 0 ? false : true;				
		});
		$.validator.addMethod("pledgeFileSizeCheck", function(value, element, regexpr) {
			return pledgeFileSize == 0 ? false : true;				
		});
		
		// Form Validate Check
		$("#registFrm").validate({
			submitHandler : function(){
				$('#confirmModal').modal('show');
			},
			rules : {
				reqReason : {
					required : true
				},
				veiDocNo : {
					required : true
				},
				officialFileNm : {
					required : '${officialFileRegType}' == '1' ? true : false, 
					officialFileSizeCheck : true
				},
				pledgeFileNm : {
					required : '${pledgeFileRegType}' == '1' ? true : false,
					pledgeFileSizeCheck : true
				},
				videoStCal : {
					required : true
				},
				videoEdCal : {
					required : true
				},
				smsAuthNo : {
					required : true
				},
				reapplyReason : {
					required : reApplyTrue
				}
			},
			messages : {
				reqReason : {
					required : "필수 입력 항목 입니다."
				},
				veiDocNo : {
					required : "필수 입력 항목 입니다."
				},
				officialFileNm : {
					required : "필수 입력 항목 입니다.",
					officialFileSizeCheck : "빈파일을 첨부하셨습니다."
				},
				pledgeFileNm : {
					required : "필수 입력 항목 입니다.",
					pledgeFileSizeCheck : "빈파일을 첨부하셨습니다."
				},
				videoStCal : {
					required : "필수 입력 항목 입니다."
				},
				videoEdCal : {
					required : "필수 입력 항목 입니다."
				},
				smsAuthNo : {
					required : "필수 입력 항목 입니다."
				},
				reapplyReason : {
					required : "필수 입력 항목 입니다."
				}
			}
		});
		
	});
	
	/* CCTV 선택 팝업 */
	function cctvPop(){
		window.open("/map/cctvPop.do", getTargetName("cctvPop"),"left=200, top=10, width=1120, height=968, menubar=no, status=no, scrollbars=yes, resizable=no");
	}
	
	/* 제공 근거 팝업 */
	function providePop(){
		window.open("/apply/apply/providePop.do", getTargetName("providePop"),"left=200, top=100, width=800, height=650, menubar=no, status=no, scrollbars=no, resizable=no");
	}
	
	/* 영상 신청 ajax */
	function registApply(){
		
		//CCTV 선택 체크
		if ($('#videoId').val() == '') {
			alert('CCTV를 선택해 주세요.');
			return;
		}
		
		// 영상 시간 셋팅 확인 체크
		var fomatVideoStCal = $("#videoStCal").val().replace(/-/gi,'');
		var fomatVideoEdCal = $("#videoEdCal").val().replace(/-/gi,'');
		var videoStCal = fomatVideoStCal+""+$("#videoStH option:selected").val()+$("#videoStM option:selected").val();
		var videoEdCal = fomatVideoEdCal+""+$("#videoEdH option:selected").val()+$("#videoEdM option:selected").val();
		if (Number(videoStCal) >= Number(videoEdCal)) {
			alert('영상요청기간을 확인해 주세요.');
			return;
		}
		
		//만약 데이터가 없을시에..
		if ($('#veiLimitCount').val() == '') {
			$('#veiLimitCount').val('100');
		}
		if ($('#veiLimitDatetime').val() =='') {
			$('#veiLimitDatetime').val('14');
		}
		
		// 중복제출 방지
		if ($('#submitCheck').val() == "false") {
			$('#submitCheck').val('true');
		} else {
			alert("잠시만 기다려 주세요.");
			return;
		}
		
		// 달려 및 시간 세팅
		$("#videoStart").val($("#videoStCal").val()+" "+$("#videoStH option:selected").val()+":"+$("#videoStM option:selected").val()+":00");
		$("#videoEnd").val($("#videoEdCal").val()+" "+$("#videoEdH option:selected").val()+":"+$("#videoEdM option:selected").val()+":59");
		$("#registFrm").ajaxSubmit({
	        type: 'post',
	        url: '/apply/apply/regist.json',
	        beforeSend:function(){
                $("#loadingContainer").show();
            },
	        dataType: 'json',
	        success: function(data) { // .... 서버와 통신 성공 시, 데이터 가공. 아래 참조
	        	$("#loadingContainer").hide();
	        	if (data.result == "SUCCESS"){
	        		alert("신청서가 제출되었습니다.\n담당자의 승인 후에 영상을 다운로드할 수 있습니다.")
	        		$(location).attr('href',"/apply/apply/list.do");
	        	} else {
	        		alert("신청 정보이 실패하였습니다. \n 관리자에게 문의해 주세요.")
	        	}
	        },
	        complete:function(){
                $("#loadingContainer").hide();
            },
	        error: function(request, status, error) {
	        	errorModal(request);
	        },
	    });
	}
	
	function sendSmsAuthNo(type){
		if ($("#phoneNum").val() == ''){
			alert("전화번호를 입력한 후 인증받으시기 바랍니다.");
			$("#phoneNum").val('').focus();
			return false;
		}
		$.ajax({
			type : 'post',
			url : '/sms/auth/create.json',
			dataType : 'json',
			data : {
				userId : $("#userId").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(data) {
				//console.log(data.authNo);
				$("#smsAuthNo").val(data.authNo);
				if (type == 'new'){
					$("#smsModal").modal('toggle');
				} 
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function chkSmsAuthNo(){
		if ($("#smsAuthNo").val() == $("#userSmsAuthNo").val()){
			alert("인증이 성공하였습니다.");
			$(".sms-auth").text('인증완료').removeClass('text-danger').addClass('text-success');
			$("#smsModal").modal('hide');
		} else {
			$("#smsFailAlert").css("visibility", "visible");
		}
	}
	
</script>

<!-- Ajax Loading -->
<div id="loadingContainer">
    <img class="loadingImage" src="/resources/images/ajax_loader.gif">
</div>
    
<!-- Content Area -->
<div class="container">
	<div class="row" style="margin-top:81px;"> 
		<!--left menu start-->
		<div class="col-md-2">
			<div class="slnb list-group">
				<div class="slnb_title"></div>
				<a href="#" class="list-group-item  active">영상정보신청</a>
			</div>
		</div>
		<!--content start-->
		<div class="col-md-10">
			<blockquote><p><strong>영상정보신청</strong></p></blockquote>
			<div class="panel panel-default">
				<div class="panel-body text_red"><strong>신청서 반려사유</strong></div>
				<div class="panel-footer"> <spring:eval expression="@message['reject.reason.1']" /><br>
		  								   <spring:eval expression="@message['reject.reason.2']" /><br>
		  								   <spring:eval expression="@message['reject.reason.3']" /> 
		  		</div>
			</div>
			<form class="form-inline" id="registFrm" name="registFrm" method="post" enctype="multipart/form-data">
				
				<input type="hidden" name="userId" id="userId" value="${USER_ID}">
				<input type="hidden" name="position" id="position" value="${USER_POSITION}">
				<input type="hidden" name="depart" id="depart" value="${USER_DEPART}">
				<input type="hidden" name="team" id="team" value="${USER_TEAM}">
				<input type="hidden" name="videoStart" id="videoStart" value="">
				<input type="hidden" name="videoEnd" id="videoEnd" value="">
				<input type="hidden" name="veiLicenseCode" id="veiLicenseCode" value="90009">
				<input type="hidden" name="mediaCode" id="mediaCode" value="10001">
				<input type="hidden" name="addr1" id="addr1" value="${applyInfo.addr1}">
				<input type="hidden" name="addr2" id="addr2" value="${applyInfo.addr2}">
				<input type="hidden" name="addr3" id="addr3" value="${applyInfo.addr3}">
				<input type="hidden" name="addr4" id="addr4" value="${applyInfo.addr4}">
				<input type="hidden" name="lat" id="lat" value="${applyInfo.lat}">
				<input type="hidden" name="lng" id="lng" value="${applyInfo.lng}">
				<input type="hidden" name="veiLimitCount" id="veiLimitCount" value="${playLimitCount}">
				<input type="hidden" name="veiLimitDatetime" id="veiLimitDatetime" value="${playLimitDatetime}">
				<c:if test="${applyInfo.itemSerial != null && applyInfo.itemSerial != ''}">
				<input type="hidden" name="orgItemSerial" id="orgItemSerial" value="${applyInfo.itemSerial}">
				</c:if>
				
				<table class="table">
					<tbody>
						<tr>
							<td class="pop_th" style="width: 150px;">소속</td>
							<td>${USER_POSITION} ${USER_DEPART} ${USER_TEAM}</td>
						</tr>
						<tr>
							<td class="pop_th">이름</td>
							<td>${USER_NAME}</td>
						</tr>
						<tr>
							<td class="pop_th">연락처</td>
							<td>
								<input type="text" class="form-control" id="phoneNum" name="phoneNum" readonly="readonly" value="${USER_PHONE}">
								<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
				            		<c:if test="${list.itemName eq 'SMS_USE' }">
				            			<c:if test="${list.itemValue eq '0' }">
				            				<button type="button" class="btn btn-default" onclick="sendSmsAuthNo('new')">SMS 인증</button>
											<span class="text-danger">&nbsp;&nbsp;</span><kbd>숫자만 입력해주세요</kbd></span>
							            	<span class="text-info text-danger sms-auth">*SMS 인증 상태가 아닙니다.</span>
							            	<input type="text" id="smsAuthNo" name="smsAuthNo" class="form-control" style="width:0px;visibility:hidden">
				            			</c:if>
				            		</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td class="pop_th">제공근거</td>
							<td>
								<button type="button" class="btn btn-default" onclick="providePop()">제공 근거</button>
								<c:choose>
									<c:when test="${applyInfo ne null}">
										<input type="text" id="reqReason" name="reqReason"
											readonly="readonly" class="form-control" value="${applyInfo.reqReason}"
											style="display: inline-block; width: 530px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" />
									</c:when>
									<c:otherwise>
										<input type="text" id="reqReason" name="reqReason"
											readonly="readonly" class="form-control"
											style="display: inline-block; width: 530px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="pop_th">공문번호</td>
							<td>
								<c:choose>
									<c:when test="${applyInfo ne null}">
										<input type="text" class="form-control" id="veiDocNo" name="veiDocNo" value="${applyInfo.veiDocNo}">
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" id="veiDocNo" name="veiDocNo">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<c:if test="${officialFileRegType == '1' || pledgeFileRegType == '1'}">
							<tr>
								<td rowspan="2" class="pop_th">
									첨부 파일
								</td>
								<c:if test="${officialFileRegType == '1'}">
									<td>
										<div class="input-group">
											<span class="input-group-btn">
												<span class="btn btn-default btn-file">
													공문 첨부 <input type="file" id="officialFile" name="officialFile" >
												</span>
											</span>
										</div>
										<input type="text" style="width: 534px;"  class="form-control" id="officialFileNm" name="officialFileNm" readonly>
									</td>
								</c:if>
							</tr>
							<tr>
								<c:if test="${pledgeFileRegType == '1'}">
									<td>
										<div class="input-group">
											<span class="input-group-btn">
												<span class="btn btn-default btn-file">
													보안서약서 첨부 <input type="file" id="pledgeFile" name="pledgeFile">
												</span>
											</span>
										</div>
										<input type="text" style="width: 500px;" class="form-control" id="pledgeFileNm" name="pledgeFileNm" readonly>
									</td>
								</c:if>
							</tr>
						</c:if>
						<tr>
							<td class="pop_th">범죄유형</td>
							<td>
								<select id="reqReasonCode" name="reqReasonCode" class="form-control">
								<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
									<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${applyInfo.reqReasonCode eq list.codeKey }">
													<option value="${list.codeKey}" selected="selected">${list.codeVal}</option>
												</c:when>
												<c:otherwise>
													<option value="${list.codeKey}">${list.codeVal}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<option value="${list.codeKey}">${list.codeVal}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>
								<select id="reqReasoncodeReason" name="reqReasoncodeReason" class="form-control">
								<c:forEach var="list" items="${crimeEtcTypeList}" varStatus="status">
									<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${applyInfo.reqReasoncodeReason eq list.codeKey }">
													<option value="${list.codeKey}" selected="selected">${list.codeVal}</option>
												</c:when>
												<c:otherwise>
													<option value="${list.codeKey}">${list.codeVal}</option>
												</c:otherwise>
											</c:choose>
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
							<td rowspan="2" class="pop_th">CCTV 및<br>사건장소</td>
							<td>
								<table>
									<tr>
										<td>
											<button type="button" class="btn btn-default" onclick="cctvPop()">CCTV선택</button>
										</td>
										<c:choose>
											<c:when test="${applyInfo ne null}">
											<td>
												<span id="videoList">
													<table>
														<tr>
															<td class="panel-body">
																<c:forEach var="videolist" items="${applyInfo.videoId}" varStatus="listStatus">
																	<c:if test="${listStatus.index == 10}">
																		</td>
																		<td class="panel-body">
																	</c:if>
																	-&nbsp;${videolist}<br>
																</c:forEach>
																</td>
															</td>
														</tr>
													</table>
												</span>
												<input type="hidden" id="videoId" name="videoId"
													readonly="readonly" class="form-control" value="${applyInfo.videoId}"
													style="display: inline-block; width: 526px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" />
											</td>
											</c:when>
											<c:otherwise>
											<td>
												<span id="videoList"></span>
												<input type="hidden" id="videoId" name="videoId"
													readonly="readonly" class="form-control"
													style="display: inline-block; width: 526px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" />
											</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td id="fullAddr">${applyInfo.reqPosition}</td>
						</tr>
						<tr>
							<td rowspan="2" class="pop_th">영상요청기간</td>
							<td>
								<label for="textfield">시작일시&nbsp;&nbsp;</label>
								<input class="form-control" id="videoStCal" name="videoStCal" value=${fn:substring(applyInfo.videoStart, 0, 10)}>
								<select name="select2" class="form-control" id="videoStH" name="videoStH">
								<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
									<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
									<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${fn:substring(applyInfo.videoStart, 11, 13) == no}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${no eq '00'}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
						    	</c:forEach>
								</select> :
								<select class="form-control" id="videoStM" name="videoStM">
								<c:forEach var="minute" begin="0" step="1" end="59" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${fn:substring(applyInfo.videoStart, 14, 16) == no}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${no eq '00'}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
						    	</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<label for="textfield">종료일시&nbsp;&nbsp;</label>
								<input type="text" class="form-control" id="videoEdCal" name="videoEdCal" value=${fn:substring(applyInfo.videoEnd, 0, 10)}>
								<select class="form-control" id="videoEdH" name="videoEdH">
								<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${fn:substring(applyInfo.videoEnd, 11, 13) == no}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${no eq '00'}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
						    	</c:forEach>
								</select> :
								<select class="form-control" id="videoEdM" name="videoEdM">
								<c:forEach var="minute" begin="0" step="1" end="59" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<c:choose>
										<c:when test="${applyInfo ne null}">
											<c:choose>
												<c:when test="${fn:substring(applyInfo.videoEnd, 14, 16) == no}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${no eq '00'}">
													<option value="${no}" selected="selected">${no}</option>
												</c:when>
												<c:otherwise>
													<option value="${no}">${no}</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
						    	</c:forEach>
								</select>
							</td>
						</tr>
						<c:if test="${applyInfo ne null}">
						<tr>
							<td class="th">재신청 사유</td>
							<td>
								<input type="hidden" name="reApplyTrue" id="reApplyTrue" value="true">
								<input type="text" class="form-control" style="width: 400px;" id="reapplyReason" name="reapplyReason">
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<div class="alert alert-danger" role="alert"><spring:eval expression="@message['security.caution.msg']" /></div>
				<button type="submit" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; margin-bottom:20px; color:#336699">신청서 제출</button>
			</form>
			<!-- 제출버튼 확인 -->
			<input type="hidden" id="submitCheck" name="submitCheck" value="false">
		</div>
	</div>
</div>
<!-- SMS 인증 Modal -->
<div class="modal fade in" id="smsModal" tabindex="-1" role="dialog" aria-labelledby="smsModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="smsModalLabel">SMS 인증</h4>
      </div>
      <div class="modal-body">
        <div style="width:570px;">
	    <div class="pop_title">SMS 인증</div>
	    <div class="box_gray">SMS로 전송된 인증번호 6자리를 입력해주세요.<br><br>
	      <input type="text" class="form-control" id="userSmsAuthNo" name="userSmsAuthNo">
	      <button type="submit" class="btn btn-default" onclick="chkSmsAuthNo()">확인</button>
	      <button type="submit" class="btn btn-default" onclick="sendSmsAuthNo('re')">재전송</button>
	    </div>
	    <div class="alert alert-info" id="smsFailAlert" role="alert" style="margin:10px; visibility: hidden;">인증이 실패하였습니다. 인증번호를 확인해주세요.</div>
	  </div>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
