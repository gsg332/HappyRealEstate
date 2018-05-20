<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#loginBtn").click(function(){
			//console.log($("#userId").val());
			if ($("#userId").val() == ""){
				alert("ID를 입력해 주세요");
			} else if ($("#password").val() == ""){
				alert("비밀번호를 입력해 주세요");
			} else {
				$("#loginFrm").submit();
			}
		});
		 
		// 엔터 처리
		$("#loginModal").enter({ 
			exec: function(){ 
				$("#loginBtn").trigger("click");
			} 
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("IP허용 요청 확인");
			$this.find('.pop_title').text("해당 IP허용을 요청하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				var params = $("#allowIPFrm").serialize();
				$.ajax({
					type : 'post',
//					url : '/system/ip/insert.json',
					url : '/sms/allow/send.json',
					dataType: 'json',
					data : params,
					success : function(data) {
				
						if (data.result == "SUCCESS"){
							alert("해당 IP를 요청하였습니다.");
							$(location).attr('href',"/login.do");
							// searchIpList(1);
						} else {
							alert("IP 요청을 실패하였습니다. \n관리자에게 문의해 주세요.");
						}					
					
					},
					error : function(request, status, error) {
						errorModal(request);
					},
				});
			});
			$this.find('button.btn-danger').off().on('click',function(){
			});
		});
		
		$("#allowIPBtn").click(function(){
					
			if ($("#title").val().trim() == ''){
				alert("IP사용기관을 입력해 주세요.");
				return false;
			}
			if ($("#startIp").val().trim() == ''){
				alert("시작IP를 입력해 주세요.");
				return false;
			}
			
			if(verifyIPAddr($("#startIp").val().trim(), $("#endIp").val().trim()) != true){
				alert("유효하지 않은 IP 입니다.\n범위로 등록할 경우 IP 클래스를 같게 지정해 주세요.\nex) 211.222.233.1 ~ 211.222.233.255");
				return false;
			}
			
			$('#confirmModal').modal('show');
		});
	});

</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container position_r">
			<div class="fnb">
				<a class="top-title" href="#" data-toggle="modal" data-target="#loginModal">
					<img src="/resources/images/ico_user_login.png" align="middle" class="top-title" width="32" height="32" alt="로그인" title="로그인" style="margin-right: 4px;"/>로그인
				</a>
				<a class="top-title" href="/user/regist.do">
					<img src="/resources/images/ico_group_add.png" align="middle" class="top-title" width="45" height="32" alt="회원가입" title="회원가입" style="margin-right: 4px;"/>회원가입
				</a>
			</div>
			<div class="navbar-header">
			<!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> 
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
				</button> -->
				<a class="navbar-brand" href="/login.do"><img src="/resources/images/logo.png" width="193" height="30" alt=""/></a> 
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row" style="margin-top:51px;">
			<!-- Alert Message Area Start -->
			<c:if test="${!empty param.logout}">
			<div class="alert alert-success fade in" role="alert" style="margin-top:20px; text-align: center;" id="asdf" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h3><spring:eval expression="@message['logout.message']" /></h3>
			</div>
			</c:if>
			<c:if test="${!empty param.fail}">
			<div class="alert alert-warning fade in" role="alert" style="margin-top:20px;" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4><spring:eval expression="@message['login.badCredentials.message']" /></h4>
			</div>
			</c:if>
			<c:if test="${!empty param.denied}">
			<div class="alert alert-danger fade in" role="alert" style="margin-top:20px;" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4><spring:eval expression="@message['login.denied.message']" />
				<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
	           		<c:if test="${list.itemName eq 'SMS_USE' }">
	           			<c:if test="${list.itemValue eq '0' }">				
						<a href="#" class="alert-link" style="margin-left: 30px; color: blue;" data-toggle="modal" data-target="#allowIPModal">IP 허용 요청</a>
	           			</c:if>
	           		</c:if>
				</c:forEach>	
				</h4>			
			</div>
			</c:if>
			<c:if test="${!empty param.duplicate}">
			<div class="alert alert-info fade in" role="alert" style="margin-top:20px;" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4><spring:eval expression="@message['login.duplicate.message']" /></h4>
			</div>
			</c:if>
			<c:if test="${!empty param.timeover}">
			<div class="alert alert-info fade in" role="alert" style="margin-top:20px;" >
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4><spring:eval expression="@message['login.timeover.message']" /></h4>
			</div>
			</c:if>
			<!-- Alert Message Area End -->
			
			<div class="col-md-3 main_left"></div>
			<div id="mimg" class="carousel slide col-md-8 ad_main" data-ride="carousel"> 
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#mimg" data-slide-to="0" class="active"></li>
					<li data-target="#mimg" data-slide-to="1"></li>
					<li data-target="#mimg" data-slide-to="2"></li>
				</ol>
			
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active"> <img src="/resources/images/ad_main_white.jpg" width="711" height="400" alt="" usemap="#Map"/>
						<map name="Map" id="Map" style="z-index:10;">
							<area  shape="rect" coords="125,242,378,284" href="/resources/download/VES_BackupViewer.exe" alt="영상플레이어" title="영상플레이어" />
						</map>
					</div>
					<div class="item"> <img src="/resources/images/ad_main1_white.jpg" width="711" height="400" alt=""/></div>
					<div class="item"> <img src="/resources/images/ad_main2_white.jpg" width="711" height="400" alt=""/></div>
				</div>
			
				<!-- Controls --> 
				<a class="left carousel-control" href="#mimg" role="button" data-slide="prev"> 
					<!--<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>--> 
					<span class="sr-only">Previous</span> 
				</a> 
				<a class="right carousel-control" href="#mimg" role="button" data-slide="next"> 
					<!--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>--> 
					<span class="sr-only">Next</span>
				</a> 
			</div>
			<div class="col-md-2 service">
				<div class="service_title">
					<%-- <h4>서비스센터</h4><spring:eval expression="@message['home.service']" /> --%>
					<span><h4>서비스센터</h4>AM 09:00~18:00<br>주말, 공휴일은 쉽니다</span>
				</div>
				<div class="number">
					<span>
						<h3>${TEL_manageCall}</h3>운영
						<h3>${TEL_TechCall}</h3>기술
					</span>
				</div>
			</div>
			<div class="col-md-2 down">
				<div class="menual">
					<a href="/resources/download/UserManual.pdf" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('manual','','/resources/images/ico_menual_hover.png',1)">
						<img src="/resources/images/ico_menual.png" alt="" id="manual">
					</a>
					<%-- <span><h4>사용자매뉴얼</h4><spring:eval expression="@message['home.manual']" /></span> --%>
					<span><h4>사용자매뉴얼</h4>쉽고 편리한 설치 및 사용을<br>지원하는 매뉴얼입니다</span>
				</div>
				<div class="player">
					<a href="/resources/download/VES_BackupViewer.exe" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('player','','/resources/images/ico_player_hover.png',1)">
						<img src="/resources/images/ico_player.png" alt="" id="player">
					</a>
					<span><h4>영상플레이어</h4>최적화된 기능을 제공하는<br>전용 플레이어 입니다</span>
				</div>
			</div>
			<div id="simg" class="carousel slide col-md-2 ad_small" data-ride="carousel"> 
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#simg" data-slide-to="0" class="active"></li>
					<li data-target="#simg" data-slide-to="1"></li>
					<li data-target="#simg" data-slide-to="2"></li>
				</ol>
		
				<!-- Wrapper for slides -->
				<div class="carousel-inner simg" role="listbox">
					<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
						<c:if test="${list.itemName eq 'MAIN_BANNER_IMG' }">
							<c:set var="mainBannerImg" value="${list.itemValue}"/>
						</c:if>
					</c:forEach>
					<div class="item active"> <img src="/resources/images/${mainBannerImg}" width="230" height="295" alt=""/> </div>
					<div class="item"> <img src="/resources/images/${mainBannerImg}" width="230" height="295" alt=""/></div>
					<div class="item"> <img src="/resources/images/${mainBannerImg}" width="230" height="295" alt=""/></div>
				</div>
		
				<!-- Controls --> 
				<a class="left carousel-control" href="#simg" role="button" data-slide="prev"> 
					<!--<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>--> 
					<span class="sr-only">Previous</span> 
				</a> 
				<a class="right carousel-control" href="#simg" role="button" data-slide="next"> 
					<!--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>--> 
					<span class="sr-only">Next</span> 
				</a> 
			</div>
		</div>
	</div>
	<!-- Login Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="loginModalLabel">Login</h4>
				</div>
				<div class="modal-body">
					<div class="login1">
						<form id="loginFrm" name="loginFrm" method="post" action="<c:url value='j_spring_security_check' />">
							<h3>일반 로그인</h3>
							<input type="text" class="form-control w100" id="userId" name="userId" placeholder="아이디를 입력하세요">
							<input type="password" class="form-control w100" id="password" name="password" placeholder="비밀번호를 입력하세요">
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<div class="login1">
						<button type="button" id="loginBtn" name="loginBtn" class="btn btn-primary">로그인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Allow IP Request Modal -->
	<div class="modal fade" id="allowIPModal" tabindex="-1" role="dialog" aria-labelledby="allowIPModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="allowIPModalLabel">IP 허용 요청</h4>
				</div>
				<div class="modal-body">
					<form id="allowIPFrm" name="allowIPFrm" method="post">
					<div>
						<div>
							IP 사용 기관 : <input type="text" class="form-control" id=title name="title" style="background: #f7f7f7; height: 40px; margin-top: 10px;">
						</div>
						<div>
							허용 IP 범위 : <input type="text" class="form-control" id="startIp" name="startIp" style="background: #f7f7f7; height: 40px; margin-top: 10px;">
						 ~ <input type="text" class="form-control" id="endIp" name="endIp" style="background: #f7f7f7; height: 40px; margin-top: 10px;">
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="allowIPBtn" name="allowIPBtn" class="btn btn-primary">IP 허용 요청</button>
				</div>
			</div>
		</div>
	</div>
	 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
