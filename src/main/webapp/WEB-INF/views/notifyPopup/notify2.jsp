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
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
		self.close();
	}
</script>
</head>
<body>

<table border=0 style="width=566; height=577;" align=center>
<tr>
	<td width="100%" align=center>
	<img src="/resources/images/notice2.jpg">
	</td>
</tr>
<tr><td height=1 bgcolor="#efefef" ></td></tr>
<tr>
	<td align=right>
    <label><input type="checkbox" name="checkbox" onClick="setCookie('noti2','s',7);" style='font-size:9px;' align=absmiddle ><font style='font-size:12px; cursor:pointer;'>7일동안 이 창을 띄우지 않습니다.</font></label>
	</td>
</tr>
<tr><td height=1 bgcolor="#efefef" ></td></tr>
</table>
</body>
</html>