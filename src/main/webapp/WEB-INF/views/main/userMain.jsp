<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tlds/custom.tld"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<%@ include file="/WEB-INF/views/common/headerSub.jsp" %>
<!-- cctv운용현황 차트-->
<c:forEach var="list" items="${cctvMarkerTypeList}" varStatus="status">
	<c:set var="sumCctvTotCnt" value="${sumCctvTotCnt + list.cctvCount}" scope="page"/>
	<c:choose><c:when test="${status.last}">
			<c:set var="sumCctvCnt" value="${sumCctvCnt}[eval(${list.cctvCount}), ${status.count}],[eval(${sumCctvTotCnt}), ${status.count+1}]" scope="page"/>
			<c:set var="cctvTypeNm" value="${cctvTypeNm}'${list.cctvTypeNm}','전체'" scope="page"/>
		</c:when>
		<c:otherwise>
			<c:set var="sumCctvCnt" value="${sumCctvCnt}[eval(${list.cctvCount}), ${status.count}]," scope="page"/>
			<c:set var="cctvTypeNm" value="${cctvTypeNm}'${list.cctvTypeNm}'," scope="page"/>
		</c:otherwise>
	</c:choose>
</c:forEach>
<script type="text/javascript">
// 나의신청현황 리스트 차트
var myApplyList= [
		<c:forEach var="applyDate" items="${applyDateCounts}" varStatus="sstatus">
		<c:choose><c:when test="${sstatus.last}">
			['<font weight=bold color=#3399cc size=2px>${applyDate.applyDate}</font>', '${applyDate.totalCnt}']
		</c:when>
		<c:otherwise>
			['<font weight=bold size=2px>${applyDate.applyDate}</font>', '${applyDate.totalCnt}'],
		</c:otherwise></c:choose></c:forEach>
	];


	$(document).ready(function(){

		//주의 팝업
	    notiPupup();
		
		if ("${PASSWD_EXPIRED}" == "Y"){
			$(".navbar-nav").css("display", "none");
			$("#alertModal").modal('toggle');
		}

		// cctvBarChart
		adminCctvChart();

	    $('#userMainChart').jqplot([myApplyList], {
	        // title:'신청현황',
	        // Provide a custom seriesColors array to override the default colors.
	        // seriesColors:['#00749F', '#73C774', '#C7754C'],
	        seriesDefaults:{
	            renderer:$.jqplot.BarRenderer,
	            rendererOptions: {
	                // Set varyBarColor to tru to use the custom colors on the bars.
	                //varyBarColor: true,
	                barWidth: 25
	            },
	    		pointLabels: { show: true }
	        },
            grid: {
                background: '#ffffff'
                ,drawGridlines: true
                ,drawBorder: false
                ,shadow: true
                ,borderColor: '#999999'
                ,borderWidth: 1
            },	         
	        axes:{
	            xaxis:{
	                renderer: $.jqplot.CategoryAxisRenderer,
	                // tickOptions: { fontSize: 10}
	            },
	        	yaxis:{
	        		min: 0,
	        		max: Math.max('${applyDateCounts[0].totalCnt+5}', '${applyDateCounts[1].totalCnt+5}', '${applyDateCounts[2].totalCnt+5}'),
	        		tickOptions: { formatString: '%d' }
	        	}
	        }
	    });
	});
	

	function adminCctvChart(){
		// CCTV 운영현황 horizontal bar chart

		//if("${sumCctvCnt}" != 0) 
		//{	
			var yCnt = [${cctvTypeNm}];
			var totalCnt = [[${sumCctvCnt}]];

		    $.jqplot('adminCctvChart', totalCnt, {
			      seriesDefaults:{
			          renderer:$.jqplot.BarRenderer,
			          shadowAngle: 135,
			          rendererOptions: {
	                      barDirection: 'horizontal',
	                      varyBarColor: false,
	                      barMargin: 5,
	                      highlightMouseDown: true 
		              },
		              pointLabels: {show: true, formatString: '%d대', location: 'e', edgeTolerance: -15 }
			      },
			      grid: {
			          drawBorder: false, 
			          drawGridlines: false,
			          background: '#fff',
			          shadow:false,
			      },
		          axes: {
		        	xaxis:{
		        			tickOptions:{
		        				showMark:false,showGridline:true,showLabel:false,show:false
			        		},
			        		showTickMarks:false,
		        	},
	                 yaxis: {
	                     renderer: $.jqplot.CategoryAxisRenderer,
	                     ticks : yCnt,
	                     tickOptions: {
	                         showGridline: false,
	                         markSize: 0
	                     }
	                 },
	              }
			  });	 
		//}		
	}
	
	function mainTableTabs(id) {
		$('#tableResultNoReg').css('display','none');
		$('#tablePlaylimit').css('display','none');
		$('#tableWaitChangeReq').css('display','none');
		if (id != '') {
			$('#'+id).css('display','block');
		}
	}
	
	function moveUserModify(){
		$(location).attr('href',"/user/modify.do");
	}
	
	function moveApplyDetail(serial){
		$("#itemSerial").val(serial);
		var pageUrl = "/apply/apply/detail.do";
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}

	function notiPupup(){

		// 노티 1에 대한 핸들링
		var windowW = 550 + 90;
		var windowH = 696 + 100;
		var aleft = (screen.width - windowW) / 2;
		var atop =  (screen.height - windowH) / 2;
	
		if(getCookie('noti') != 's')
		{
			//var POPWin=open("/notifyPopup/notify.do", "_notify", "top="+atop+", left="+aleft+", height="+windowH+", width="+windowW + ",menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=yes");
			//POPWin.focus();
		}
	
		// 노티 2에 대한 핸들링
		windowW = 566+30;
		windowH = 577+60;
		aleft = (screen.width - windowW) / 2;
		atop =  (screen.height - windowH) / 2;
		
		if(getCookie('noti2') != 's')
		{
			//var POPWin2=open("/notifyPopup/notify2.do", "_notify2", "top="+atop+", left="+aleft+", height="+windowH+", width="+windowW + ",menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=yes");
			//POPWin2.focus();
		}
		
		// BK 노티에 대한 핸들링
		windowW = 711+30;
		windowH = 400+30;
		aleft = (screen.width - windowW) / 2;
		atop =  (screen.height - windowH) / 2;

		//var POPWin3=open("/notifyPopup/bkViewnotify.do", "_BVnotify3", "top="+atop+", left="+aleft+", height="+windowH+", width="+windowW + ",menubar=no,toolbar=no,directories=no,location=no,status=no,scrollbars=yes");
		//POPWin2.focus();
	}
</script>

<form name='searchFrm' method="post">
 <input type="hidden" id="itemSerial" name="itemSerial">
</form>
<!-- Content Area -->
<div class="container">
	<div class="row" style="margin-top:70px;">
	 <!-- 로그인시 변경DIV -->
    <div class="col-md-3 main_left_login">
     <div class="left2" style="height:150px">
        <ul class="list-inline">
          <li class="title_blue">나의 신청현황</li><li class="title_black">${TODAY}</li>
        </ul>
        <table class="table table-bordered" style="margin-top:8px;">
          <thead>
            <tr>
              <th class="title_blue1" style="color: white;">대기</th>
              <th class="title_blue1" style="color: white;">처리중</th>
              <th class="title_blue1" style="color: white;">완료</th>
              <th class="title_blue1" style="color: white;">전체</th>
            </tr>
          </thead>
          <tbody>
            <tr>
            <c:choose>
            	<c:when test="${applyCounts.totalCnt == '00'}">
            	  <td style="font-weight: bold;color:#3399cc;">00</td>
	              <td style="font-weight: bold;color:#3399cc;">00</td>
	              <td style="font-weight: bold;color:#3399cc;">00</td>
	              <td style="font-weight: bold;color:#3399cc;">00</td>
            	</c:when>
            	<c:otherwise>
            	 <td style="font-weight: bold;color:#3399cc;">${applyCounts.waitingCnt}</td>
	              <td style="font-weight: bold;color:#3399cc;">${applyCounts.procCnt}</td>
	              <td style="font-weight: bold;color:#3399cc;">${applyCounts.allowCnt}</td>
	              <td style="font-weight: bold;color:#3399cc;">${applyCounts.totalCnt}</td>
            	</c:otherwise>
            </c:choose>
              
            </tr>
          </tbody>
        </table>
      	<div id="userMainChart" style="height:100px"></div>   
      </div>
      <div class="left2" style="height:230px">
		<ul class="nav nav-tabs nav-justified" >
			<li class="active"><a href="#" onclick="javascript:mainTableTabs('tableResultNoReg')" data-toggle="tab">
				<c:choose>
					<c:when test="${resultCounts.notResultCnt == null || resultCounts.notResultCnt == ''}">
					<u>00</u>
					</c:when>
					<c:otherwise>
						<u>${resultCounts.notResultCnt}</u>
					</c:otherwise>
				</c:choose>
				<br><strong>결과미등록</strong></a>
			</li>
			<li>
				<a href="#" onclick="javascript:mainTableTabs('tablePlaylimit')" data-toggle="tab">
					<u>${limitDateCounts.limitDateTotalCnt}</u>
					<br>
					<strong>만료예정영상</strong>
				</a>
			</li>
			<%-- 
			<li>
				<a href="#" onclick="javascript:mainTableTabs('tableWaitChangeReq')" data-toggle="tab">
					<u><c:choose><c:when test="${empty waitChangeReqList}">00</c:when><c:when test="${waitChangeReqList[0].listCnt < 10}">0${waitChangeReqList[0].listCnt}</c:when><c:otherwise>${waitChangeReqList[0].listCnt}</c:otherwise></c:choose></u>
					<br><strong>변경요청</strong>
				</a>
			</li>
			 --%>
		</ul>
        <!-- 결과미등록 -->
        <div id="tableResultNoReg" class="table-responsive" style="height:160px;">
        <table class="table table_b" style="margin-top:8px;">
          <thead>
            <tr>
              <th>신청번호</th>
              <th>관리번호</th>
              <th>경과일</th>
            </tr>
          </thead>
          <tbody>
		  <c:forEach var="list" items="${resultList}" varStatus="status">
			<c:set var="videoIdLen" value="${fn:split(list.videoId,',')}" scope="page"/>
				<c:forEach var="videoList" items="${fn:split(list.videoId,',')}" varStatus="status" end="0">
					<c:forEach var="videoNameSpl" items="${fn:split(videoList,'|')}" varStatus="status" end="0">
						<c:set var="videoName" value="${videoNameSpl}" scope="page"/>
					</c:forEach>
				</c:forEach>
				<tr>
					<td><a href="/apply/result/list.do">${list.itemSerial}</a></td>
					<c:choose>
						<c:when test="${fn:length(videoIdLen) > 1}">
							<td>${fn:substring(videoName,0,6)}.. 외 ${fn:length(videoIdLen)-1}개</td>
						</c:when>
						<c:otherwise>
							<td>${fn:substring(videoName,0,11)}</td>
						</c:otherwise>
					</c:choose>
					<td>${list.pastDate}일</td>
				</tr>
			</c:forEach>
		</tbody>
        </table>
        </div>
        <!-- 만료예정영상 -->
        <div id="tablePlaylimit" class="table-responsive" style="height:160px;display:none;">
	        <table class="table table_b" style="margin-top:8px;">
	          <thead>
	            <tr>
	              <th>신청번호</th>
	              <th>관리번호</th>
	              <th>재생만료일</th>
	            </tr>
	          </thead>
	          <tbody>
			  <c:forEach var="limitList" items="${limitDateList}" varStatus="status">
				<c:set var="limitVideoIdLen" value="${fn:split(limitList.videoId,',')}" scope="page"/>
				<c:forEach var="videoList2" items="${fn:split(limitList.videoId,',')}" varStatus="status" end="0">
					<c:forEach var="limetVideoNameSpl" items="${fn:split(videoList2,'|')}" varStatus="status" end="0">
						<c:set var="limetVideoName" value="${limetVideoNameSpl}" scope="page"/>
					</c:forEach>
				</c:forEach>
				<tr>
					<td><a href="javascript:moveApplyDetail('${limitList.itemSerial}')">${limitList.itemSerial}</a></td>
					<c:choose>
						<c:when test="${fn:length(limitVideoIdLen) > 1}">
							<td>${fn:substring(limetVideoName,0,6)}.. 외 ${fn:length(limitVideoIdLen)-1}개</td>
						</c:when>
						<c:otherwise>
							<td>${fn:substring(limetVideoName,0,11)}</td>
						</c:otherwise>
					</c:choose>
					<td>${limitList.veiLimitDatetime}</td>
				</tr>
			  </c:forEach>
	          </tbody>
	        </table>
      	</div>
      	<!-- 변경요청 -->
      	<%-- 
		<div id="tableWaitChangeReq" class="table-responsive" style="height:160px;display:none;">
		  	<table class="table table_b" style="margin-top:8px;">
		    	<thead>
		        	<tr>
		            	<th>신청번호</th>
		            	<th>변경메뉴</th>
		            	<th>상세내용</th>
		          	</tr>
		        </thead>
		   		<tbody>
			  	<c:forEach var="list" items="${waitChangeReqList}" varStatus="status">
					<tr>
						<td><a href="/changeReq/list.do?searchStatus=1000&searchTypeDepth1=영상정보신청">${list.itemId}</a></td>
						<td>${list.typeDepth1}</td>
						<td><custom:FirstStrCut value="${list.typeDepth2}"/></td>
					</tr>
				</c:forEach>
				</tbody>
		  	</table>
		</div>
		 --%>
      </div>
      <div class="left2" style="height:230px">
	       <ul class="list-inline">
	          <li class="title_blue">CCTV&nbsp;&nbsp;운영현황</li>
	        </ul>
			<div id="adminCctvChart" style="height:200px; width:250px; margin-top:-5px; margin-left:-5px;"></div>
	   </div>
    </div>
    <!-- 로그인시 변경DIV END -->
		<div id="mimg" class="carousel slide col-md-8 ad_main" data-ride="carousel"> 
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#mimg" data-slide-to="0" class="active"></li>
				<li data-target="#mimg" data-slide-to="1"></li>
				<li data-target="#mimg" data-slide-to="2"></li>
			</ol>
		
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="/resources/images/ad_main_white.jpg" width="711" height="400" alt="" usemap="#Map"/>
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
				<h4>서비스센터</h4>AM 09:00~18:00<br>주말,공휴일은 쉽니다
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
<!-- Alert Modal -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호 사용기간 만료</h4>
      </div>
      <div class="modal-body">
        <p>비밀번호 사용 기간이 만료되었습니다. 비밀번호를 변경하여 주십시오.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="moveUserModify()">비밀번호 변경</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
