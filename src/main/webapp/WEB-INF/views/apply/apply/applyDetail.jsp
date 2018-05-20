<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		var officialFileSize;
		var pledgeFileSize;
		
		searchPlayList(1);
		
		// 검색 시작 날짜 달력 세팅
		$("#veiDnLimitDate").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
			todayHighlight : true,
			startDate : "${applyInfo.videoStart}".substring(0, 10)
		});
		
		// 검색 종료 날짜 달력 세팅
		$("#veiLimitDatetime").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
			todayHighlight : true,
			startDate : "${applyInfo.videoStart}".substring(0, 10)
		});
		
		/* 공문 파일 첨부  */
		$("#officialFile").change(function(event){
			officialFileSize = $(this)[0].files[0].size;
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#officialFileNm").val(fileInfo);
		});
		
		/* 보안서약서 파일 첨부 */
		$("#pledgeFile").change(function(event){
			pledgeFileSize = $(this)[0].files[0].size;
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#pledgeFileNm").val(fileInfo);
		});
		
		showCrimeEtcTypeList();
		
		$("#reqReasonCode").change(function(){
			showCrimeEtcTypeList();
		});

		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("수정 확인");
			$this.find('.pop_title').text("관리자에게 변경승인을 요청하시겠습니까?");
			${btnType == 'yes'}
			$this.find('button.btn-primary').off().on('click',function(){
				modifyApplyInfo();
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
		$("#modifyFrm").validate({
			submitHandler : function(){
				$('#confirmModal').modal('show');
			},
			rules : {
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
				}
			},
			messages : {
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
				}
			}
		});
		
	});
	
	function showCrimeEtcTypeList(){
		if ($("#reqReasonCode").val() == '99999'){
			$("#reqReasoncodeReason").css("display", "");
		} else {
			$("#reqReasoncodeReason").css("display", "none");
		}
	}
	
	/**
	 *  신청 리스트 페이징 처리
	 */
	function pagination(totalPage, currentPage){
		$('#page-selection').bootpag({
		    total: totalPage,
		    page: currentPage,
		    maxVisible: 5,
		    leaps: true,
		    firstLastUse: true,
		    first: '←',
		    last: '→',
		    wrapClass: 'pagination',
		    activeClass: 'active',
		    disabledClass: 'disabled',
		    nextClass: 'next',
		    prevClass: 'prev',
		    lastClass: 'last',
		    firstClass: 'first'
		}).on("page", function(event, num){
		 	// apply list search ajax
		 	searchPlayList(num);
		}); 
	}
	
	function searchPlayList(pageNum){
		$("#currentPage").val(pageNum);
		var params = $("#modifyFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/apply/play/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#playList").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});   
	}
	
	function modifyApplyInfo(){
		
		// file 변경 여부에 따라 form의 enctype을 변경해야 함. 
		// because 서버에서 parameter mapping 시 넘어온 parameter와 맞지 않을 경우 400 bad request를 리턴함
		if ($("#officialFile").val() == '' && $("#pledgeFile").val() == ''){
			$("#modifyFrm").attr("enctype", "");
			$("#officialFile").remove();
			$("#pledgeFile").remove();
		} else {
			if($("#officialFile").val() != '' && $("#pledgeFile").val() != ''){
			}else{
				if ($("#officialFile").val() != ''){
					$("#pledgeFile").remove();
				} else {
					$("#officialFile").remove();
				}
			}
			$("#modifyFrm").attr("enctype", "multipart/form-data");
		}
/* 		
		if ($("#modLimit").val() != $("#veiLimitCount").val()){
			$("#veiLimitCount").val($("#veiLimitCount").val() - $("#modLimit").val());
		} else {
			$("#veiLimitCount").val('');
		}
*/

		$("#modifyFrm").ajaxSubmit({
	        type: 'post',
	        url: '/apply/apply/requestChange.json',
	        beforeSend:function(){
                $("#loadingContainer").show();
            },
	        dataType: 'json',
	        success: function(data) { // .... 서버와 통신 성공 시, 데이터 가공. 아래 참조
	        	$("#loadingContainer").hide();
	        	if (data.result == "SUCCESS"){
	        		if(parseInt('${USER_LEVEL}') <= 6){
	        			alert("영상 정보를 변경요청하셨습니다.");
	        		}else{
	        			alert("영상 정보를 수정하였습니다.");
	        		}
	        		moveMenu('/apply/apply/list.do', '200');
	        	} else {
	        		alert("영상 정보 수정을 실패하였습니다. \n 관리자에게 문의해 주세요.");
	        		//location.reload();
	        	}
	        },
	        complete:function(){
                $("#loadingContainer").hide();
            },
	        error: function(request, status, error) {
	        },
	    });
	}
	
	function registEvidenceInfo(){
		
		window.open("/apply/evidence/regPop.do?itemSerial="+$("#itemSerial").val(), getTargetName("evidenceRegPop"),
				"left=200 top=100 width=500 height=350 menubar=no status=no scrollbars=no resizable=no");
	}
	
	function exportExcel(){
		var pageUrl = "/apply/play/excel.do";
		modifyFrm.action = pageUrl;
		modifyFrm.submit();
	}
	
	function crimeAreaPop(){
		var lat = "${applyInfo.lat}", lng = "${applyInfo.lng}", videoId = "${applyInfo.videoId}";
		window.open("/map/crimeAreaPop.do?centerLat="+lat+"&centerLng="+lng+"&videoId="+videoId, getTargetName("crimeAreaPop"),
		"left=200 top=10 width=800 height=670 menubar=no status=no scrollbars=no resizable=no");
	}
	
	/*
	첨부파일 다운로드 처리
	*/
	function fileDown(itemNo){
		location.href = '/apply/apply/fileDown.do?itemNo='+itemNo;
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
					<div class="slnb_title1"></div>
				<c:forEach var="list" items="${SECOND_MENU_LIST}" varStatus="status">
					<c:if test="${list.pMenuId eq '200' || list.pMenuId eq '700'}">
					<c:set var="pMenuId" value="${list.pMenuId}" scope="session" />
						<c:choose>
							<c:when test="${list.menuUrl eq CURRENT_URL}">
								<a href="javascript:moveMenu('${list.menuUrl}','${list.pMenuId}')" class="list-group-item active">${list.menuNm }</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:moveMenu('${list.menuUrl}','${list.pMenuId}')" class="list-group-item">${list.menuNm }</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10">
				<blockquote><p><strong>영상 신청정보 상세보기</strong></p></blockquote>
		        <!-- Nav tabs -->
		        <div>
				<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px">
					<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">신청서</a></li>
					<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">재생기록</a></li>
				</ul>
					<div class="tab-content">
					<!--신청서 탭 시작-->
						<div role="tabpanel" class="tab-pane fade in active" id="home">
							<form id="modifyFrm" name="modifyFrm" method="post">
								
								<input type="hidden" id="itemSerial" name="itemSerial" value="${applyInfo.itemSerial}">
								<input type="hidden" id="officialFileNo" name="officialFileNo" value="${applyInfo.attachFileNo[0]}">
								<input type="hidden" id="pledgeFileNo" name="pledgeFileNo" value="${applyInfo.attachFileNo[1]}">
								<input type="hidden" id="officialFileTempNm" name="officialFileTempNm" value="${applyInfo.attachFileTempNm[0]}">
								<input type="hidden" id="pledgeFileTempNm" name="pledgeFileTempNm" value="${applyInfo.attachFileTempNm[1]}">
								<input type="hidden" id="currentPage" name="currentPage" value="1" >
								<input type="hidden" id="rowSize" name="rowSize" value="10" >
								<%-- <input type="hidden" id="modLimit" name="modLimit" value="${applyInfo.modLimit}" > --%>
								
							<table class="table">
								<tbody>
									<tr>
										<td class="th" style="width: 120px;">신청번호</td>
										<td>${applyInfo.itemSerial}</td>
									</tr>
									<tr>
										<td class="th">신청일</td>
										<td>${applyInfo.reqDate}</td>
									</tr>
									<tr>
										<td class="th">아이디</td>
										<td>${applyInfo.reqUserId}</td>
									</tr>
									<tr>
										<td class="th">소속</td>
										<td>${applyInfo.position} ${applyInfo.depart} ${applyInfo.team}</td>
									</tr>
									<tr>
										<td class="th">이름</td>
										<td>${applyInfo.reqUsername}</td>
									</tr>
									<tr>
										<td class="th">연락처</td>
										<td>${applyInfo.reqPhone}</td>
									</tr>
									<tr>
										<td class="th">제공근거</td>
										<td>
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
													<select name="reqReason" id="reqReason" class="form-control" style="width: 640px;" >
														<c:forEach var="list" items="${provideList}" varStatus="status">
															<c:choose>
																<c:when test="${applyInfo.reqReason eq list.codeVal}">
																	<option value="${list.codeVal}" selected="selected">${list.codeVal}</option>
																</c:when>
																<c:otherwise>
																	<option value="${list.codeVal}">${list.codeVal}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</c:when>
												<c:otherwise>
													${applyInfo.reqReason}
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="th">공문번호</td>
										<td>
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
													<input type="text" class="form-control" id="veiDocNo" name="veiDocNo" value="${applyInfo.veiDocNo}">
												</c:when>
												<c:otherwise>
													${applyInfo.veiDocNo}
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<c:if test="${pledgeFileRegType == '1' || officialFileRegType == '1'}">
										<tr>
											<td rowspan="2" class="th">
												첨부 파일
											</td>
											<c:if test="${officialFileRegType == '1'}">
												<td>
													<c:set var="officialFileIndex" value="${applyInfo.attachFileType[1] == '1001' ? 1 : 0}"/>
													<c:choose>
														<c:when test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
															<div class="input-group">
																<span class="input-group-btn">
																	<span class="btn btn-default btn-file">
																		공문 첨부 <input type="file" id="officialFile" name="officialFile" >
																	</span>
																</span>
																<input type="text" style="width: 334px;" class="form-control" id="officialFileNm" name="officialFileNm" value="${applyInfo.attachFileNm[officialFileIndex]}" readonly>
																<c:if test="${not empty applyInfo.attachFileNo[officialFileIndex]}">
																	<a href="javascript:fileDown('${applyInfo.attachFileNo[officialFileIndex]}')" style="display: inline-block; margin: 5px 0 0 5px;">
																		<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${applyInfo.attachFileNm[officialFileIndex]}" />
																	</a>
																</c:if>
															</div>
														</c:when>
														<c:otherwise>
															<a href="javascript:fileDown('${applyInfo.attachFileNo[officialFileIndex]}')" style="margin-right: 20px;">
																<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${applyInfo.attachFileNm[officialFileIndex]}" />
															</a>
															<c:out value="${applyInfo.attachFileNm[officialFileIndex]}" /> 
														</c:otherwise>
													</c:choose>
												</td>
											</c:if>
										</tr>
									</c:if>
									<tr>
										<c:if test="${pledgeFileRegType == '1'}">
											<td>
												<c:set var="pledgeFileIndex" value="${applyInfo.attachFileType[1] == '1002' ? 1 : 0}"/>
												<c:choose>
													<c:when test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
														<div class="input-group">
															<span class="input-group-btn">
																<span class="btn btn-default btn-file">
																	보안서약서 첨부 <input type="file" id="pledgeFile" name="pledgeFile">
																</span>
															</span>
															<input type="text" style="width: 300px;" class="form-control" id="pledgeFileNm" name="pledgeFileNm" value="${applyInfo.attachFileNm[pledgeFileIndex]}" readonly>
															<c:if test="${not empty applyInfo.attachFileNo[pledgeFileIndex]}">
																<a href="javascript:fileDown('${applyInfo.attachFileNo[pledgeFileIndex]}')" style="display: inline-block; margin: 5px 0 0 5px;">
																	<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${applyInfo.attachFileNm[pledgeFileIndex]}" />
																</a>
															</c:if>
														</div>
													</c:when>
													<c:otherwise>
														<a href="javascript:fileDown('${applyInfo.attachFileNo[pledgeFileIndex]}')" style="margin-right: 20px;">
															<img src='<c:url value="/resources/images/ico_file.png" />' width="20" height="20" title="${applyInfo.attachFileNm[pledgeFileIndex]}" />
														</a>
														<c:out value="${applyInfo.attachFileNm[pledgeFileIndex]}" />
													</c:otherwise>
												</c:choose>
											</td>
										</c:if>
									</tr>
									<tr>
										<td class="th">범죄유형</td>
										<td>
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
													<select id="reqReasonCode" name="reqReasonCode" class="form-control">
														<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
															<c:choose>
																<c:when test="${list.codeKey eq applyInfo.reqReasonCode}">
																	<option value="${list.codeKey}" selected="selected">${list.codeVal}</option>
																</c:when>
																<c:otherwise>
																	<option value="${list.codeKey}">${list.codeVal}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													<select id="reqReasoncodeReason" name="reqReasoncodeReason" class="form-control">
														<option value="">선택</option>
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
												</c:when>
												<c:otherwise>
													<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
														<c:if test="${list.codeKey eq applyInfo.reqReasonCode}">
														${list.codeVal}
														</c:if>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td rowspan="2" class="th">CCTV</td>
										<td style="display: inline-block; width: 700px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
											<table>
												<tr>
													<td class="panel-body">
														<c:forEach var="videolist" items="${applyInfo.videoId}" varStatus="listStatus">
															<c:forTokens var="token" items="${videolist}" delims="|" varStatus="vs">
																<c:if test="${vs.index == 0}">
																	<c:if test="${listStatus.index == 10}">
																		</td>
																		<td class="panel-body">
																	</c:if>
																	${fn:replace(token,'&&',',')}<br>
																</c:if>
															</c:forTokens> 
														</c:forEach>
														</td>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											${applyInfo.reqPosition}
											<button type="button" class="btn btn-default" style="margin-left: 30px;" onclick="crimeAreaPop()">사건장소 보기</button>
										</td>
									</tr>
									<tr>
										<td class="th">영상 시작일시</td>
										<td>${applyInfo.videoStart}</td>
									</tr>
									<tr>
										<td class="th">영상 종료일시</td>
										<td>${applyInfo.videoEnd}</td>
									</tr>
									<tr>
										<td class="th">승인 처리일시</td>
										<td>${applyInfo.approvingDate}&nbsp; &nbsp;/&nbsp; &nbsp;처리 소요 시간 : ${applyInfo.procTime}</td>
									</tr>
									<tr>
										<td class="th">승인 완료일시</td>
										<td>${applyInfo.approvalDate}</td>
									</tr>
									<tr>
										<td class="th">다운로드 만료일</td>
										<td>
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8}">
													<input type="text" class="form-control" id="veiDnLimitDate" name="veiDnLimitDate" value="${fn:substring(applyInfo.veiDnLimitDate,0,10)}">
													&nbsp; &nbsp;/&nbsp; &nbsp;다운로드 허용 횟수 : 
													<input type="text" class="form-control" id="veiDnLimitCount" name="veiDnLimitCount" value="${applyInfo.veiDnLimitCount}">
												</c:when>
												<c:otherwise>
													${applyInfo.veiDnLimitDate}&nbsp; &nbsp;/&nbsp; &nbsp;다운로드 허용 횟수 : ${applyInfo.veiDnLimitCount}
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="th">재생 만료일 </td>
										<td>
											<c:choose>
												<c:when test="${USER_LEVEL_NO ge 8}">
													<input type="text" class="form-control" id="veiLimitDatetime" name="veiLimitDatetime" value="${fn:substring(applyInfo.veiLimitDatetime,0,10)}">
													&nbsp; &nbsp;/&nbsp; &nbsp;재생 허용 횟수 : 
													<input type="text" class="form-control" id="veiLimitCount" name="veiLimitCount" value="${applyInfo.veiLimitCount}">
												</c:when>
												<c:otherwise>
													${applyInfo.veiLimitDatetime}&nbsp; &nbsp;/&nbsp; &nbsp;재생 허용 횟수 : ${applyInfo.veiLimitCount}
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<td class="th" style="border-bottom:1px solid #e9e9e9">파일크기</td>
										<td>${applyInfo.fileuploadVol} Bytes</td>
									</tr>
									<tr>
										<td colspan="2" style="background:#fff; text-align:right">
											<button type="button" class="btn btn-default" onclick="moveMenu('/apply/apply/list.do', '${pMenuId}')" >목록보기</button>
										<c:if test="${USER_LEVEL_NO lt 8}">
											<button type="button" class="btn btn-success" onclick="registEvidenceInfo()">증거자료 제출</button>
										</c:if>
										<c:if test="${USER_LEVEL_NO ge 8 || USER_LEVEL_NO le 6}">
											<button id="btnModify" type="submit" class="btn btn-primary">변경요청</button>
										</c:if>
										</td>
									</tr>
								</tbody>
							</table>
							</form>
						</div>
						
						<!--재생기록 탭내용시작-->
						<div role="tabpanel" class="tab-pane fade" id="profile">
							<div id="playList"></div>
						</div>
						<!--재생기록 탭내용 END-->
					</div>
				</div>
			<!--Nav tabs END--> 
			</div>
		</div>
	</div>
	
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
					<button type="button" class="btn btn-primary" style="width: 100px;" data-dismiss="modal">예</button>
					<button type="button" class="btn btn-danger" style="width: 100px;" data-dismiss="modal">아니오</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 연장신청 내역 Modal End -->
