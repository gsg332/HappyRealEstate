/**
 *  Image Swap Function
 */
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

/**
 * 엔터실행
 */
$.fn.enter = function( opts ) {
	
	this.defaults = {
		exec: null
	};
	
	opts = $.extend( {}, this.defaults, opts );

	this.keypress( function( e ) {
		if( e.keyCode == 13 ) {
			e.preventDefault();
			opts.exec();
		}
	});
}

/**
 * input 예 : /common/smsCertPop.incon output 예 : smsCertPop
 */
function getTargetName(url){
	var winname = "";
	var temparr1 = [];
	var temparr2 = [];
	temparr1 = url.split("/");
	temparr2 = temparr1[temparr1.length - 1].split(".");
	if(temparr2[0].length > 0){
		winname = temparr2[0];
	}else{
		winname = "new";
	}
	return winname;	
}

/**
 * 자릿수만큼 문자열 채우기
 * getPad(변수, 총자릿수, 채울문자, 채울 방향); // STR_PAD_LEFT, STR_PAD_RIGHT, STR_PAD_BOTH
*/

function getPad(input, length, string, type) {
	if (input.length >= length) return input;	
	
	var string = string || '0', 
		input = input + '',
		type = type || 'STR_PAD_LEFT​';
		inputLength = input.length;
		pad = Array(length - inputLength + 1).join(string);
	switch (type) {
		case 'STR_PAD_LEFT​': 
			result = pad + input;
			break;
		case 'STR_PAD_RIGHT': 
			result = input + pad;
			break;
		case 'STR_PAD_BOTH': 
			var i = parseInt((length - inputLength) / 2);
			result = pad.substring(0,i) + input + pad.substring(i, length - i + 1);			
			break;
	}
 return result;

}

/**
 * 달력 표시 (yyyy-mm-dd)
 * getCalendarStr(날짜형 변수)
 */
function getCalendarStr(date){
	date = date.getFullYear() + "-"
			+ getPad((date.getMonth() + 1).toString(), 2, 0) + "-"
			+ getPad((date.getDate()).toString(), 2, 0);
	return date;
}

/**
 * 날짜 포멧
 * 
 */
function getFormatDateSMC(){
	var regDate = '';
	var yyyy,mm,dd,si,bun,cho;
	var currentY,currentM,currentD;
	var date = new Date();

	yyyy = date.getFullYear().toString();
	mm = (date.getMonth() + 1).toString();
	dd = date.getDate().toString();
	hh = date.getHours().toString(); 
	tmm = date.getMinutes().toString();
	ss =  date.getSeconds().toString();

	var result = yyyy
	+ '-' 
	+ (mm[1]?mm:"0"+mm[0])
	+ '-' 
	+ (dd[1]?dd:"0"+dd[0])
	+ ' ' 
	+ (hh[1]?hh:"0"+hh[0])
	+ ':' 
	+ (tmm[1]?tmm:"0"+tmm[0])
	+ ':' 
	+ (ss[1]?ss:"0"+ss[0]);

	return result;
}



/**
 * IP 검증
 * verifyIPAddr(문자형 ip 변수,문자형 ip 변수)
 */
function verifyIPAddr(startIP, endIP) {
	
	if(!checkIP(startIP))
	{
		return false;
	}
	
	if(endIP != "")
	{
		if(!checkIP(endIP))
		{
			return false;
		}
	}
	
	var arrIP1 = startIP.split('.');
	var arrIP2 = endIP.split('.');

	if(endIP != "")
		if( (arrIP1[0] != arrIP2[0]) || (arrIP1[1] != arrIP2[1]) || (arrIP1[2] != arrIP2[2]) )
		{
			return false;
		}

	if(endIP != "")
		if( parseInt(arrIP1[3]) > parseInt(arrIP2[3]) )
		{
			// alert(arrIP1[3] + " / " + arrIP2[3]);
			return false;
		}
	
	return true;
}

function checkIP(strIP) {
	
	var blsRet = false;
	if(strIP.search(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/) >= 0)
	{
		var arrIP = strIP.split('.');
		if(arrIP.length == 4)
		{
			if(arrIP[0] < 256 && arrIP[1] < 256 && arrIP[2] < 256 && arrIP[3] < 256)
			return true;
		}
	}
	return blsRet;
}	

// 
$(function(){
	
	MM_preloadImages('/resources/images/ico_menual_hover.png','/resources/images/ico_player_hover.png');
	
	// jquery validate massage tooltip 
	jQuery.validator.setDefaults({
        showErrors : function(errorMap, errorList){
            
        	$.each(this.validElements(), function (index, element) {
                var $element = $(element);
                $element.parent().data("title", "")
                    .removeClass("error")
                    .tooltip("destroy");
            });

            $.each(errorList, function (index, error) {
                var $element = $(error.element);
                $element.parent().tooltip("destroy")
                    .data("title", error.message)
                    .addClass("error")
                    .tooltip();
            });
        }
    });
	
	jQuery.validator.addMethod("mix_eng_num", function (value, element) {
        return this.optional(element) || /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(value);
    });
	
});

function getCookie(name)
{ 
	var nameOfCookie = name + "="; 
	var x = 0; 
	while (x <= document.cookie.length) 
	{ 
		var y = (x+nameOfCookie.length); 
		if (document.cookie.substring(x, y) == nameOfCookie) 
		{ 
			if((endOfCookie=document.cookie.indexOf(";",y)) == -1) 
				endOfCookie = document.cookie.length; 
			return unescape(document.cookie.substring(y,endOfCookie));
		} 
		x = document.cookie.indexOf( " ", x ) + 1; 
		
		if ( x == 0 ) 
			break; 
	} 
	return "";
}