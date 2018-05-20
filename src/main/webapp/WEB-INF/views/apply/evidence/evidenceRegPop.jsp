<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
	<c:if test="${list.itemName eq 'LOCATION_GU' }">
		<c:set var="LOCATION_GU" value="${list.itemValue}" scope="page" />
	</c:if>
</c:forEach>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	var LOCATION_GU = "${LOCATION_GU}";
	//아이디 고정발급 사이트 리스트
	var idCheckReturnList=
	{
		"성동구":true,
	};
	
	$(document).ready(function(){
		
		// Form Validate Check
		$("#registFrm").validate({
			submitHandler : function(){
				registEvidenceInfo();
			},
			rules : {
				eviUserid : {
					required : true,
					minlength: 5
				},
				eviUserpass : {
					required : true,
					minlength: 10,
					mix_eng_num : true
				},
				eviUserpassChk : {
					required : true,
					equalTo: "#eviUserpass"
				},
				officialFileNm : {
					required : true
				}
			},
			messages : {
				eviUserid : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("문자 또는 문자∙숫자를 조합해서 {0}자리 이상 입력해주세요.")
				},
				eviUserpass : {
					required : "필수 입력 항목 입니다.",
					minlength: $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
				},
				eviUserpassChk : {
					required : "필수 입력 항목 입니다.",
					equalTo: "비밀번호가 일치하지 않습니다."
				},
				officialFileNm : {
					required : "필수 입력 항목 입니다."
				}
			}
		});
		
		/* 공문 파일 첨부  */
		$("#officialFile").change(function(event){
			//console.log($(this)[0].files[0]);
			var fileInfo = $(this)[0].files[0].name +" ["+$(this)[0].files[0].size+" bytes]";
			$("#officialFileNm").val(fileInfo);
		});
		
	});
	
	var idChk = false;
	
	function idDuplChk(){

		if ($("#eviUserid").val() == ''){
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : '/check/idDupli.json',
			dataType: 'json',
			data : {
				"userId" : $("#eviUserid").val()
			},	
			success : function(data) {
				
				if (data.result == "used"){
					alert("이미 사용중인 아이디입니다.");
					$("#eviUserid").val('').focus();
				} else {
					alert("사용가능한 아이디입니다.");
					idChk = true;
				}				
			
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function registEvidenceInfo(){
		//아이디 고정발급 아이디 추가 해서 사용
		if ( idCheckReturnList[LOCATION_GU] == true) {
			idChk = true;
		}
		if (idChk){
			$("#registFrm").ajaxSubmit({
		        type: 'post',
		        url: '/apply/evidence/regist.json',
		        dataType: 'json',
		        beforeSend:function(){
	                $("#loadingContainer").show();
	            },
		        success: function(data) { // .... 서버와 통신 성공 시, 데이터 가공. 아래 참조
		        	if (data.result == "SUCCESS"){
		        		alert("증거자료 제출이 정상 등록되었습니다.");
		        		$(opener.document).find("#parentMenuId").val("700");
		        		$(opener.location).attr('href',"/apply/evidence/list.do");
		        		self.close();
		        	} else {
		        		alert("증거자료 제출이 실패하였습니다. \n 관리자에게 문의해 주세요.");
		        		self.close();
		        	}
		        },
		        complete:function(){
	                $("#loadingContainer").hide();
	            },
		        error: function(request, status, error) {
		        },
		    });
		} else {
			alert("ID 중복체크를 해주세요.");
			return false;
		}
	}
	
</script>
</head>
<body>
<!-- Ajax Loading -->
<div id="loadingContainer">
    <img class="loadingImage" src="/resources/images/ajax_loader.gif">
</div>
	<form class="form-inline" id="registFrm" name="registFrm" method="post">
		<input type="hidden" id="itemSerial" name="itemSerial" value="${itemSerial}" />
		
		<!--pop width 480px-->
		<div class="pop1">
			<div class="pop_title" style="margin-bottom:10px"> 영상정보 증거자료 제출</div>
			<table class="table" style="width:480px; margin:0 auto;" >
				<tbody>
					<tr>
						<td class="th" style="width: 140px;">발급 아이디</td>
						<td>
							<c:choose>
								<c:when test="${FN_CONFIG_LIST['EVIDENCE_STATIC_ID']['Value'] == 'Y'}">
									<span>${FN_CONFIG_LIST['EVIDENCE_STATIC_ID']['SubConfigList']['STATIC_ID']['Value']}</span>
									<input type="hidden" id="eviUserid" name="eviUserid" value="${FN_CONFIG_LIST['EVIDENCE_STATIC_ID']['SubConfigList']['STATIC_ID']['Value']}" />
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="eviUserid" name="eviUserid" />&nbsp;
									<button type="button" id="btn_idchk" class="btn btn-default" onclick="idDuplChk()">중복확인</button>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="th">발급 비밀번호</td>
						<td>
							<input type="password" class="form-control" id="eviUserpass" name="eviUserpass" />
						</td>
					</tr>
					<tr>
						<td class="th">발급 비밀번호 확인</td>
						<td>
							<input type="password" class="form-control" id="eviUserpassChk" name="eviUserpassChk" />
						</td>
					</tr>
					<tr>
						<td class="th">관련공문 첨부</td>
						<td>
							<div class="input-group">
								<span class="input-group-btn">
									<span class="btn btn-default btn-file">
										공문 첨부 <input type="file" id="officialFile" name="officialFile" >
									</span>
								</span>
								<input type="text" style="width: 250px;" class="form-control" id="officialFileNm" name="officialFileNm" readonly>
							</div>
						</td>
					</tr>
					<tr>
						<td class="th">재생 만료일</td>
						<td>
							<select name="eviLimitDate" id="eviLimitDate" class="form-control">
							<c:forEach var="list" items="${periodList}" varStatus="status">
								<option value="${list.codeVal}">${list.codeDesc}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="btn btn-default" style="margin:10px; width:480px; height:30px; font-weight:bold; font-size:14px">증거자료제출</button>
		</div>
	</form>
</body>
</html>