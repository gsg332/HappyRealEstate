<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">

	var levelNm = [['0','사용자'],['7','열람용'],['8','담당자'],['9','관리자']];
	var tabFlag = "pinfo";
	
	$(document).ready(function(){
		
		searchUserList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchJoinFrm").children(".box_gray").children("div").children().eq(2).enter({ 
			exec: function(){ 
				searchJoinList(1);
			} 
		});
		// 엔터 검색 처리
		$("#searchUserFrm").children(".box_gray").children("div").children().eq(2).enter({ 
			exec: function(){ 
				searchUserList(1);
			} 
		});
		
		// 검색 종류 전체 일 경우 input readonly
		$("#searchUserFrm").children(".box_gray").children("div").children().eq(1).change(function(){
			if ($(this).children("option:selected").val() != ""){
				$("#searchUserFrm").children(".box_gray").children("div").children().eq(2).prop("readonly", false);
			} else {
				$("#searchUserFrm").children(".box_gray").children("div").children().eq(2).val("");
				$("#searchUserFrm").children(".box_gray").children("div").children().eq(2).prop("readonly", true);
			}
		});
		
		// 검색 종류 전체 일 경우 input readonly
		$("#searchJoinFrm").children(".box_gray").children("div").children().eq(1).change(function(){
			if ($(this).children("option:selected").val() != ""){
				$("#searchJoinFrm").children(".box_gray").children("div").children().eq(2).prop("readonly", false);
			} else {
				$("#searchJoinFrm").children(".box_gray").children("div").children().eq(2).val("");
				$("#searchJoinFrm").children(".box_gray").children("div").children().eq(2).prop("readonly", true);
			}
		});
		
		
		$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
			if ($(e.target).text() == '개인정보'){				
				$("#joinList").empty();
				tabFlag = "pinfo";
				searchUserList(1);	// 리스트 조회 호출
			} else {
				$("#userList").empty();
				tabFlag = "history";
				searchJoinList(1);	// 리스트 조회 호출
			}
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			if(type == 'userAdd'){
				$this.find('.modal-title').text("회원 추가 확인");
				$this.find('.pop_title').text("사용자를 추가 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					var params = $("#insertUsersFrm").serialize();
					if ($("#userId").val().trim() == ''){
						alert("사용자 ID를 입력해 주세요.");
						return false;
					}		
					if ($("#password").val().trim() == ''){
						alert("비밀번호를 입력해 주세요.");
						return false;
					}
					if ($("#position").val().trim() == '' || $("#depart").val().trim() == '' || $("#team").val().trim() == ''){
						alert("소속/과/계 를 입력해 주세요.");
						return false;
					}
					if ($("#userName").val().trim() == ''){
						alert("사용자 이름을 입력해 주세요.");
						return false;
					}
					if ($("#phoneNum").val().trim() == ''){
						alert("전화번호를 입력해 주세요.");
						return false;
					}		
					$.ajax({
						type : 'post',
						url : '/user/regist.json',
						dataType: 'json',
						data : params,
						success : function(data) {
					
							if (data.result == "SUCCESS"){
								alert("사용자를 추가 하였습니다.");
								searchUserList(1);
							} else {
								alert("사용자 추가를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});			
			}else if(type == 'userDel'){
				$this.find('.modal-title').text("회원 삭제 확인");
				$this.find('.pop_title').text("회원정보를 삭제하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						data : {
							userId : $this.data('userId'),
							permit : 0,
							expired : "Y"
						},
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("회원정보 삭제가 완료되었습니다.");
								searchUserList(1);
							} else {
								alert("회원정보 삭제를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});	
			}else if(type == 'userApprove'){
				$this.find('.modal-title').text("회원가입 승인 확인");
				$this.find('.pop_title').text("회원가입을 승인하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						data : {
							"userId" : $this.data('userId'),
							"phoneNum" : $this.data('phoneNum'),
							"permit" : $this.data('permit'),
							"expired" : $this.data('expired') 
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("회원가입 승인이 완료되었습니다.");
								searchUserList(1);
							} else {
								alert("회원가입 승인을 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'userReturn'){
				$this.find('.modal-title').text("회원가입 반려 확인");
				$this.find('.pop_title').text("회원가입을 반려하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						data : {
							"userId" : $this.data('userId'),
							"phoneNum" : $this.data('phoneNum'),
							"permit" : $this.data('permit'),
							"expired" : $this.data('expired')
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("회원가입 반려가 완료되었습니다.");
								searchUserList(1);
							} else {
								alert("회원가입 반려를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'userModify'){

				$this.find('.modal-title').text("사용자정보 수정 확인");
				$this.find('.pop_title').text($this.data('userId')+" 사용자정보를 수정하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/user/modify.json',
						dataType: 'json',
						data : {
							"userId" : $this.data('userId'),
							"password" : $this.data('password'),
							"position" : $this.data('position'),
							"depart" : $this.data('depart'),
							"team" : $this.data('team'),
							"userName" : $this.data('userName'),
							"phoneNum" : $this.data('phoneNum'),		
							"level" : $this.data('level'),
							"smsReceive" : $this.data('smsChkVal')					
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert($this.data('userId')+ " 사용자정보 수정이 완료되었습니다.");
								searchUserList(1);
							} else {
								alert("수정이 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					}); 
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}
		});
	});
	
	/**
	 *  개인정보 리스트 조회
	 */
	function searchUserList(pageNum){

		$("#searchUserFrm").children(":input[name=currentPage]").val(pageNum);
		var params = $("#searchUserFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/system/user/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#userList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#userList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/**
	 *  개인정보 리스트 조회
	 */
	function searchJoinList(pageNum){

		$("#searchJoinFrm").children(":input[name=currentPage]").val(pageNum);
		var params = $("#searchJoinFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/system/user/join.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#joinList").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});   
	}	
	
	/**
	 *  개인정보 리스트 페이징 처리
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
		 	// reject list search ajax
		 	//console.log(tabFlag+" , "+num);
			if (tabFlag == "pinfo"){				
				searchUserList(num);
			} else {
				searchJoinList(num);
			}
		}); 
	}
	
	function modifyUserInfo(obj, userId){
		
		$('#userModifyForm').find('input[name^=userName]').each(function (i, e) {
			var $this = $(this);
			var $tr = $this.parents('#tr');	
			var $password = $tr.find('[name^=password]');
			var $position = $tr.find('[name^=position]');
			var $depart = $tr.find('[name^=depart]');
			var $team = $tr.find('[name^=team]');
			var $userName = $tr.find('[name^=userName]');
			var $phoneNum = $tr.find('[name^=phoneNum]');
			
			var isValidCheckField = $this.attr('id').indexOf(userId) > -1;
			
			//$position.rules('remove');
			
			$password.rules("add", {
				minlength: function(){
					return isValidCheckField ? 10 : 0; 
				},
				mix_eng_num : isValidCheckField
		        ,
		        messages : {
					minlength : $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
					mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
		        }
		    });
			$position.rules("add", {
		        required: isValidCheckField,
		        messages : {
		        	required : "필수 입력 항목 입니다."
		        }
		    });
			$depart.rules("add", {
		        required: isValidCheckField,
		        messages : {
		        	required : "필수 입력 항목 입니다."
		        }
		    });
			$team.rules("add", {
		        required: isValidCheckField,
		        messages : {
		        	required : "필수 입력 항목 입니다."
		        }
		    });
			$userName.rules("add", {
		        required: isValidCheckField,
		        messages : {
		        	required : "필수 입력 항목 입니다."
		        }
		    });
			$phoneNum.rules("add", {
		        required : isValidCheckField,
		        number :isValidCheckField,
		        messages : {
		        	required : "필수 입력 항목 입니다.",
					number: "연락처는 숫자로 입력하셔야 합니다."
		        }
		    });
		});
		
		var $password = $(obj).parents('tr').find('.password');
		var $position = $(obj).parents('tr').find('.position');
		var $depart = $(obj).parents('tr').find('.depart');	
		var $team = $(obj).parents('tr').find('.team');
		var $userName = $(obj).parents('tr').find('.userName');
		var $phoneNum = $(obj).parents('tr').find('.phoneNum');	
		var $level = $(obj).parents('tr').find('.level');
		var $smsReceive = $(obj).parents('tr').find('.smsReceive');

		var smsChkVal;
 		if ($smsReceive.children().is(":checked")){
			smsChkVal = "1";
		} else {
			smsChkVal = "0";
		}
	 
 		$('#confirmModal').data('userId',userId);
 		$('#confirmModal').data('password',$password.find('input').val());
 		$('#confirmModal').data('position',$position.find('input').val());
 		$('#confirmModal').data('depart',$depart.find('input').val());
 		$('#confirmModal').data('team',$team.find('input').val());
 		$('#confirmModal').data('userName',$userName.find('input').val());
 		$('#confirmModal').data('phoneNum',$phoneNum.find('input').val());
 		$('#confirmModal').data('level',$level.find('select').val());
 		$('#confirmModal').data('smsChkVal',smsChkVal);
 		$('#confirmModal').data('type','userModify');
 		
 		$('#userModifyForm').submit();
	}
	
	function changeToModify(obj, userId, index){
		var $password = $(obj).parents('tr').find('.password');		
		var $position = $(obj).parents('tr').find('.position');
		var $depart = $(obj).parents('tr').find('.depart');	
		var $team = $(obj).parents('tr').find('.team');
		var $userName = $(obj).parents('tr').find('.userName');
		var $phoneNum = $(obj).parents('tr').find('.phoneNum');	
		var $level = $(obj).parents('tr').find('.level');
		var $smsReceive = $(obj).parents('tr').find('.smsReceive');		
		var $modifyBtn = $(obj).parents('tr').find('.modifyBtn');
		var $delBtn = $(obj).parents('tr').find('.delBtn');
		
		$password.html("<span><input type='password' name='password[" + index + "]' id='password_" + userId + "' class='form-control' style='width:90%'></span>");		
		$position.html("<span><input type='text' name='position[" + index + "]' id='position_" + userId + "' class='form-control' style='width:90%' value='"+$.trim($position.text())+"'></span>");
		$depart.html("<span><input type='text' name='depart[" + index + "]' id='depart_" + userId + "' class='form-control' style='width:90%' value='"+$.trim($depart.text())+"'></span>");	
		$team.html("<span><input type='text' name='team[" + index + "]' id='team_" + userId + "'  class='form-control' style='width:90%' value='"+$.trim($team.text())+"'></span>");
		$userName.html("<span><input type='text' name='userName[" + index + "]' id='userName_" + userId + "' class='form-control' style='width:90%' value='"+$.trim($userName.text())+"'></span>");	
		$phoneNum.html("<span><input type='text' name='phoneNum[" + index + "]' id='phoneNum_" + userId + "' class='form-control' style='width:90%' value='"+$.trim($phoneNum.text()).replace(/-/gi,'')+"'></span>");
		var addInput = "<span><select class='form-control' style='width:120%'>";
		for(var i =0; i < levelNm.length; i++){

			if ($level.text() == levelNm[i][1]){
				addInput += "<option value='"+levelNm[i][0]+"' selected>"+levelNm[i][1]+"</option>";
			} else {
				addInput += "<option value='"+levelNm[i][0]+"'>"+levelNm[i][1]+"</option>";
			}
		}
		addInput += "</select>";
		$level.html(addInput);	
		
		if ($smsReceive.children("input").is(":checked")){
			addInput = "<input type='checkbox' style='width:20%' checked>";
		} else {
			addInput = "<input type='checkbox' style='width:20%'>";
		}
		$smsReceive.html(addInput);
		$modifyBtn.children('button').eq(0).removeClass('hidden');
		$modifyBtn.children('button').eq(1).addClass('hidden');
	}
	
	function insertUsersInfo(){
		var $form = $('#insertUsersFrm');
		var $userId = $form.find('[name^=userId]');
		var $password = $form.find('[name^=password]');
		var $position = $form.find('[name^=position]');
		var $depart = $form.find('[name^=depart]');
		var $team = $form.find('[name^=team]');
		var $userName = $form.find('[name^=userName]');
		var $phoneNum = $form.find('[name^=phoneNum]');
		
		$userId.rules("add", {
			required : true,
			minlength: 5 
			,
	        messages : {
	        	required : "필수 입력 항목 입니다.",
				minlength: $.validator.format("문자 또는 문자∙숫자를 조합해서 {0}자리 이상 입력해주세요.")
	        }
	    });		
		$password.rules("add", {
			required : true,
			minlength: 10,
			mix_eng_num : true
	        ,
	        messages : {
	        	required : "필수 입력 항목 입니다.",
				minlength : $.validator.format("영문자 및 숫자를 포함해서 {0}자리 이상 입력해주세요."),
				mix_eng_num : "영문자 및 숫자를 포함해서 입력해주세요."
	        }
	    });
		$position.rules("add", {
	        required: true,
	        messages : {
	        	required : "필수 입력 항목 입니다."
	        }
	    });
		$depart.rules("add", {
	        required: true,
	        messages : {
	        	required : "필수 입력 항목 입니다."
	        }
	    });
		$team.rules("add", {
	        required: true,
	        messages : {
	        	required : "필수 입력 항목 입니다."
	        }
	    });
		$userName.rules("add", {
	        required: true,
	        messages : {
	        	required : "필수 입력 항목 입니다."
	        }
	    });
		$phoneNum.rules("add", {
	        required : true,
	        number :true,
	        messages : {
	        	required : "필수 입력 항목 입니다.",
				number: "연락처는 숫자로 입력하셔야 합니다."
	        }
	    });
		
		$('#confirmModal').data('type', 'userAdd');

		$('#insertUsersFrm').submit();
	}
	
	function idDuplChk(){

		if ($("#userId").val() == ''){		
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : '/check/idDupli.json',
			dataType: 'json',
			data : {
				"userId" : $("#userId").val()
			},	
			success : function(data) {
				
				if (data.result == "used"){
					alert("이미 사용중인 아이디입니다.");
					$("#userId").val('').focus();
					$("#idChk").val('');
				} else {
					alert("사용가능한 아이디입니다.");
					$("#idChk").val($("#userId").val());
				}				
			
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function deleteUserInfo(userId){
		$('#confirmModal').data('type', 'userDel');
		$('#confirmModal').data('userId', userId);
		$('#confirmModal').modal('show');
	}
	
	function modifyUserStatus(userId, phoneNum, permit, expired){
		$('#confirmModal').data('userId', userId);
		$('#confirmModal').data('phoneNum', phoneNum);
		$('#confirmModal').data('permit', permit);
		$('#confirmModal').data('expired', expired);
		if(permit == 1) {
			$('#confirmModal').data('type', 'userApprove');
		}
		else {
			$('#confirmModal').data('type', 'userReturn');
		}
		$('#confirmModal').modal('show');
	}	
	
	function exportExcel(){
		var pageUrl = "/system/user/joinExcel.do";
		searchJoinFrm.action = pageUrl;
		searchJoinFrm.submit();
	}
	
</script>
	<!-- Content Area -->
	<div class="container">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-2">
				<div class="slnb list-group">
					<div class="slnb_title1"></div>
				<c:forEach var="list" items="${SECOND_MENU_LIST}" varStatus="status">
					<sec:authorize  access="hasRole('INCON_ADMIN')">
				        <c:set var="isInconAdmin" value="true"/>
				    </sec:authorize>
				    <c:choose>
				    	<c:when test="${list.menuId != 507}">
							<c:if test="${list.pMenuId == P_MENU_ID}">
								<c:choose>
									<c:when test="${list.menuUrl == CURRENT_URL}">
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${isInconAdmin}">
								<c:if test="${list.pMenuId == P_MENU_ID}">
									<c:choose>
										<c:when test="${list.menuUrl == CURRENT_URL}">
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:if>
						</c:otherwise>	
				    </c:choose>
				</c:forEach>
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10">
	        	<blockquote><p><strong>개인정보관리</strong></p></blockquote>
	        	<div>
	        		<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px">
	        			<li role="presentation" class="active"><a href="#pinfo" aria-controls="pinfo" role="tab" data-toggle="tab">개인정보</a></li>
        				<li role="presentation"><a href="#join" aria-controls="join" role="tab" data-toggle="tab">가입이력</a></li>
	        		</ul>
	        		<div class="tab-content">
	        			<!-- 사용자 정보 탭 -->
	        			<div role="tabpanel" class="tab-pane fade in active" id="pinfo">
	        				<form id="searchUserFrm" name="searchUserFrm" method="post" class="form-inline">
				
								<input type="hidden" id="currentPage" name="currentPage" value="1" >
								<input type="hidden" id="rowSize" name="rowSize" value="10" >
								
								<div class="box_gray" style="margin:0px">
									<div style="line-height:30px; position:relative; margin:0 auto">
				
										<label for="textfield">검색 종류&nbsp;&nbsp;</label>
										<select id="searchKind" name="searchKind" class="form-control" style="width:100px">
											<option value="">전체</option>
											<option value="name">이름</option>
											<option value="id">아이디</option>
											<option value="position">소속</option>
											<option value="depart">과</option>
											<option value="team">계</option>
										</select>
										
				            			<input id="searchWord" name="searchWord" type="text" class="form-control" readonly="readonly">
										
										<button class="btn btn-default" type="button" onclick="javascript:searchUserList(1)">
											<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
										</button>
									</div>
								</div>
							</form>
							<div id="userList"></div>
	        			</div>
	        			<!-- 사용자 정보 탭 -->
	        			<div role="tabpanel" class="tab-pane fade" id="join">
							<form id="searchJoinFrm" name="searchJoinFrm" method="post" class="form-inline">
								<input type="hidden" id="currentPage" name="currentPage" value="1" >
								<input type="hidden" id="rowSize" name="rowSize" value="10" >
								        				
								<div class="box_gray" style="margin:0px">
									<div style="line-height:30px; position:relative; margin:0 auto">
					
										<label for="textfield">검색 종류&nbsp;&nbsp;</label>
										<select id="searchKind" name="searchKind" class="form-control" style="width:100px">
											<option value="">전체</option>
											<option value="id">아이디</option>
											<option value="menu">메뉴</option>
											<option value="ip">IP</option>
											<option value="history">이력내역</option>
											<option value="detailed">상세내용</option>
											<option value="result">결과</option>
										</select>
					           			<input id="searchWord" name="searchWord" type="text" class="form-control" readonly="readonly">
										
										<button class="btn btn-default" type="button" onclick="javascript:searchJoinList(1)">
											<img src='<c:url value="/resources/images/ico_search1.png" />' width="16" height="16" alt="" />
										</button>
									</div>
								</div>
					    	</form>
        					<div id="joinList"></div>
	        			</div>
	        		</div>
	        	</div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
