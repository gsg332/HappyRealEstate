<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
	html, body,h1,h2,h4,button
	{
	  font:'돋움',dotum,Helvetica,sans-serif;
	  font-size:9pt;
	  border:0;
	  margin: 0;
	  padding: 0;
	}
</style>
<script type="text/javascript">
	function setCookie( name, value, expiredays ) 
	{
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	self.close();
	}
</script>
</head>
<body>

<table align=center border=0 style="width:711; height:400;">
<tr>
	<td width="100%" align=center>

	<img src="/resources/images/ad_main_white.jpg" usemap="#Map">
	<map name="Map" id="Map" style="z-index:10;">
		<area  shape="rect" coords="125,242,378,284" href="/resources/download/VES_BackupViewer.exe" alt="영상플레이어" title="영상플레이어" />
	</map>

	</td>
</tr>
<tr><td height=1 bgcolor="#efefef" ></td></tr>
<!-- <tr>
	<td align=right>
    <input type="checkbox" name="checkbox" onClick="setCookie('noti','s',7);" style='font-size:9px;'  align=absmiddle ><font style='font-size:12px;'>7일동안 이 창을 띄우지 않습니다.</font>
	</td>
</tr>
 -->
<tr><td height=1 bgcolor="#efefef" ></td></tr>
</table>
</body>
</html>