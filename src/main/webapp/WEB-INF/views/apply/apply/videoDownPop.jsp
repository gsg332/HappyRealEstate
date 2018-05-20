<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript">
	function StopDownload()
	{
		document.all.down.StopTransfer();
		if(document.all.down.IsTransfer == true)
			alert("파일 전송을 멈추고 종료합니다.");
	}
	
	function checkActiveX()
	{
		if (document.all.down.GetNeedElevate() == 3) //관리자 권한이 필요한 경우
		{
			alert("Internet Explorer 가 관리자 권한으로 실행되어 있지 않습니다.\nInternet Exploerer 를 관리자 권한으로 실행합니다.");
			document.all.down.RunElevatedWeb(parent.document.URL + '&managerAuth=true');
            self.close();
		}
	}
	
	if('${param.managerAuth}' == 'true'){ //관리자권한으로 다시 다운로드 팝업창을 띄우면, 창사이즈가 일반 사이즈이기 때문에, 창을 닫고 window.open을 하여 창사이즈를 줄이도록 한다
		window.open("/apply/apply/videoPop.do?itemSerial=${param.itemSerial}", "videoDownPop","left=200 top=100 width=810 height=460 menubar=no status=no scrollbars=no resizable=no");
		window.open('about:blank','_top').close(); 
	}
</script>
</head>
	<body onload="checkActiveX()" onBeforeUnLoad="StopDownload()">
		<table width=806 height=398 border="0" cellpadding="0" cellspacing="0" marginwidth="0" >
			<tr>
				<td height=60 ><img src="/resources/images/downhead.png">
				</td>
			</tr>
			<tr>
				<td valign=top>
					<div align=center>
						<OBJECT
							name = "down"
							classid="CLSID:2753BD3A-194C-4501-AA37-EEDB27225AAE"
							codebase="/resources/download/VESDownloaderX.cab#version=1,0,0,1"
							width=806
							height=398
							hspace=0
							vspace=0 >
							<PARAM NAME='ManageCodeLength' VALUE="0"/>
							<PARAM NAME="UseSSL" VALUE="${activeXUseSSL}"/>
							<PARAM NAME='FileURL' VALUE="${pageContext.request.scheme}://${pageContext.request.serverName}/"/>
						</OBJECT>
						<script>
							document.all.down.SetItemSerial("${itemSerial}");
							/*파일 리스트 */
							<c:forEach var="list" items="${file_list}" varStatus="status">
								document.all.down.AddFile('${list}');
							</c:forEach>
						</script>
					</div>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript" for="down" event="OnDownloadEnd(msg)">
		if (msg) alert(msg);
		
		try{
			opener.searchApplyList(1);
		}catch(e){
			console.log(e);
		}finally{
			self.close();
		}
	</script>
</HTML>