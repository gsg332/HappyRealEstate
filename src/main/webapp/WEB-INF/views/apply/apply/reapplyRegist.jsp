<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		
		// 요청 시작 날짜 달력 세팅
		$("#videoStCal").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoClose : true,
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
			autoClose : true,
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
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#officialFileNm").val(fileInfo);
		});
		
		/* 보안서약서 파일 첨부 */
		$("#pledgeFile").change(function(event){
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#pledgeFileNm").val(fileInfo);
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("신청 확인");
			$this.find('.pop_title').text("영상 정보를 신청 하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				registApply();
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
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
					required : true
				},
				pledgeFileNm : {
					required : true
				},
				videoStCal : {
					required : true
				},
				videoEdCal : {
					required : true
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
					required : "필수 입력 항목 입니다."
				},
				pledgeFileNm : {
					required : "필수 입력 항목 입니다."
				},
				videoStCal : {
					required : "필수 입력 항목 입니다."
				},
				videoEdCal : {
					required : "필수 입력 항목 입니다."
				}
			}
		});
		
	});
	
	/* CCTV 선택 팝업 */
	function cctvPop(){
		window.open("/apply/apply/cctvPop.do", getTargetName("cctvPop"),"left=200 top=100 width=800 height=800 menubar=no status=no scrollbars=no resizable=no");
	}
	
	/* 제공 근거 팝업 */
	function providePop(){
		window.open("/apply/apply/providePop.do", getTargetName("providePop"),"left=200 top=100 width=800 height=650 menubar=no status=no scrollbars=no resizable=no");
	}
	
	/* 제공근거 팝업에서 호출 */
	function setReqReason(value){
		$("#offerBasis").text(value);
		$("#reqReason").val(value);
	}
	
	/* 영상 신청 ajax */
	function registApply(){
		
		// 달려 및 시간 세팅
		$("#videoStart").val($("#videoStCal").val()+" "+$("#videoStH option:selected").val()+$("#videoStM option:selected").val()+"00");
		$("#videoEnd").val($("#videoEdCal").val()+" "+$("#videoEdH option:selected").val()+$("#videoEdM option:selected").val()+"59");
		
		$("#registFrm").ajaxSubmit({
	        type: 'post',
	        url: '/apply/apply/regist.json',
	        dataType: 'json',
	        success: function(data) { // .... 서버와 통신 성공 시, 데이터 가공. 아래 참조
	        	//console.log(data);
	        	if (data.result == "SUCCESS"){
	        		alert("신청 정보가 정상 등록되었습니다.")
	        		$(location).attr('href',"/apply/apply/list.do");
	        	} else {
	        		alert("신청 정보이 실패하였습니다. \n 관리자에게 문의해 주세요.")
	        	}
	        },
	        error: function(request, status, error) {
	        },
	    });
	}
	
</script>
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
				
				<input type="hidden" name="position" id="position" value="${USER_POSITION}">
				<input type="hidden" name="depart" id="depart" value="${USER_DEPART}">
				<input type="hidden" name="team" id="team" value="${USER_TEAM}">
				<input type="hidden" name="videoStart" id="videoStart" value="">
				<input type="hidden" name="videoEnd" id="videoEnd" value="">
				<input type="hidden" name="veiLicenseCode" id="veiLicenseCode" value="90009">
				<input type="hidden" name="mediaCode" id="mediaCode" value="10001">
				
				<table class="table">
					<tbody>
						<tr>
							<td class="th">소속</td>
							<td>${USER_POSITION} ${USER_DEPART} ${USER_TEAM}</td>
						</tr>
						<tr>
							<td class="th">이름</td>
							<td>${USER_NAME}</td>
						</tr>
						<tr>
							<td class="th">연락처</td>
							<td>
								<input type="text" class="form-control" id="phoneNum" name="phoneNum" readonly="readonly" value="${USER_PHONE }">
								<button type="button" class="btn btn-default" data-toggle="modal" data-target="#smsModal">SMS인증</button>
								<span class="text-danger">&nbsp;&nbsp;</span><kbd>숫자만 입력해주세요</kbd></span>
							</td>
						</tr>
						<tr>
							<td class="th">제공근거</td>
							<td>
								<button type="button" class="btn btn-default" onclick="providePop()">제공 근거</button>
								<input type="text" id="reqReason" name="reqReason" readonly="readonly" class="form-control" style="display:inline-block; width:330px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;" />
							</td>
						</tr>
						<tr>
							<td class="th">공문번호</td>
							<td><input type="text" class="form-control" id="veiDocNo" name="veiDocNo"></td>
						</tr>
						<tr>
							<td rowspan="2" class="th">공문첨부</td>
							<td>
								<div class="input-group">
									<span class="input-group-btn">
										<span class="btn btn-default btn-file">
											공문 첨부 <input type="file" id="officialFile" name="officialFile" >
										</span>
									</span>
									<input type="text" style="width: 334px;" class="form-control" id="officialFileNm" name="officialFileNm" readonly>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="input-group">
									<span class="input-group-btn">
										<span class="btn btn-default btn-file">
											보안서약서 첨부 <input type="file" id="pledgeFile" name="pledgeFile">
										</span>
									</span>
									<input type="text" style="width: 300px;" class="form-control" id="pledgeFileNm" name="pledgeFileNm" readonly>
								</div>
							</td>
						</tr>
						<tr>
							<td class="th">범죄유형</td>
							<td>
								<select id="reqReasonCode" name="reqReasonCode" class="form-control">
								<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
									<option value="${list.codeKey}">${list.codeVal}</option>
								</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td rowspan="2" class="th">CCTV</td>
							<td><button type="button" class="btn btn-default" onclick="cctvPop()">CCTV선택</button></td>
						</tr>
						<tr>
							<td>서울특별시 성동구 성수2가 3동</td>
						</tr>
						<tr>
							<td rowspan="2" class="th">영상요청기간</td>
							<td>
								<label for="textfield">시작일시&nbsp;&nbsp;</label>
						    	<input class="form-control" id="videoStCal" name="videoStCal">
						    	<select name="select2" class="form-control" id="videoStH" name="videoStH">
						    	<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<option value="${no}">${no}</option>
						    	</c:forEach>
								</select> :
								<select class="form-control" id="videoStM" name="videoStM">
								<c:forEach var="minute" begin="0" step="1" end="59" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<option value="${no}">${no}</option>
						    	</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<label for="textfield">종료일시&nbsp;&nbsp;</label>
								<input type="text" class="form-control" id="videoEdCal" name="videoEdCal">
								<select class="form-control" id="videoEdH" name="videoEdH">
								<c:forEach var="hour" begin="0" step="1" end="23" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<option value="${no}">${no}</option>
						    	</c:forEach>
								</select> :
								<select class="form-control" id="videoEdM" name="videoEdM">
								<c:forEach var="minute" begin="0" step="1" end="59" varStatus="status">
						    		<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.index}" />
						    		<option value="${no}">${no}</option>
						    	</c:forEach>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="alert alert-danger" role="alert"><spring:eval expression="@message['security.caution.msg']" /></div>
				<button type="submit" class="btn btn-default" style="width:100%; height:50px; font-weight:bold; margin-bottom:20px; color:#336699">신청서 제출</button>
			</form>
			
		</div>
	</div>
</div>

<div class="modal fade" id="smsModal" tabindex="-1" role="dialog" aria-labelledby="smsModalLabel">
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
	      <input type="text" class="form-control">
	      <button type="submit" class="btn btn-default">확인</button>
	      <button type="submit" class="btn btn-default">재전송</button>
	    </div>
	    <div class="alert alert-info" role="alert" style="margin:10px">인증이 실패하였습니다. 인증번호를 확인해주세요.
		</div>
	  </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
