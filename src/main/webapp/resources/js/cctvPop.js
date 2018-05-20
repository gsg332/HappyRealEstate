function getDistance(P1_latitude, P1_longitude, P2_latitude, P2_longitude){  
    
	if ((P1_latitude == P2_latitude) && (P1_longitude == P2_longitude)){   
        return 0;  
    }
	
    var e10 = P1_latitude * Math.PI / 180;  
    var e11 = P1_longitude * Math.PI / 180;  
    var e12 = P2_latitude * Math.PI / 180;  
    var e13 = P2_longitude * Math.PI / 180;  
	
    /* 타원체 GRS80 */  
    var c16 = 6356752.314140910;  
    var c15 = 6378137.000000000;  
    var c17 = 0.0033528107;  
    var f15 = c17 + c17 * c17;  
    var f16 = f15 / 2;  
    var f17 = c17 * c17 / 2;  
    var f18 = c17 * c17 / 8;  
    var f19 = c17 * c17 / 16;  
    var c18 = e13 - e11; 
    var c20 = (1 - c17) * Math.tan(e10);  
    var c21 = Math.atan(c20);  
    var c22 = Math.sin(c21);  
    var c23 = Math.cos(c21);  
    var c24 = (1 - c17) * Math.tan(e12);  
    var c25 = Math.atan(c24);  
    var c26 = Math.sin(c25);  
    var c27 = Math.cos(c25);  
    var c29 = c18;  
    var c31 = (c27 * Math.sin(c29) * c27 * Math.sin(c29)) + (c23 * c26 - c22 * c27 * Math.cos(c29)) * (c23 * c26 - c22 * c27 * Math.cos(c29));  
    var c33 = (c22 * c26) + (c23 * c27 * Math.cos(c29));  
    var c35 = Math.sqrt(c31) / c33;  c36 = Math.atan(c35);  
    var c38 = 0;  


    if (c31 == 0){   
        c38 = 0;  
    } else {   
        c38 = c23 * c27 * Math.sin(c29) / Math.sqrt(c31);  
    }  

    var c40 = 0;  
    if ((Math.cos(Math.asin(c38)) * Math.cos(Math.asin(c38))) == 0){   
        c40 = 0;  
    } else {   
        c40 = c33 - 2 * c22 * c26 / (Math.cos(Math.asin(c38)) * Math.cos(Math.asin(c38)));  
    } 
	
    var c41 = Math.cos(Math.asin(c38)) * Math.cos(Math.asin(c38)) * (c15 * c15 - c16 * c16) / (c16 * c16);  
    var c43 = 1 + c41 / 16384 * (4096 + c41 * (-768 + c41 * (320 - 175 * c41)));  
    var c45 = c41 / 1024 * (256 + c41 * (-128 + c41 * (74 - 47 * c41)));  
    var c47 = c45 * Math.sqrt(c31) * (c40 + c45 / 4 * (c33 * (-1 + 2 * c40 * c40) - c45 / 6 * c40 * (-3 + 4 * c31) * (-3 + 4 * c40 * c40)));  
    var c50 = c17 / 16 * Math.cos(Math.asin(c38)) * Math.cos(Math.asin(c38)) * (4 + c17 * (4 - 3 * Math.cos(Math.asin(c38)) * Math.cos(Math.asin(c38))));  
    var c52 = c18 + (1 - c50) * c17 * c38 * (Math.acos(c33) + c50 * Math.sin(Math.acos(c33)) * (c40 + c50 * c33 * (-1 + 2 * c40 * c40)));  
    var c54 = c16 * c43 * (Math.atan(c35) - c47);  
    // return distance in meter  
	
    return c54; 
}

/*
 * 1. 지도의 중심을 결과값으로 받은 위치로 이동
 * 2. 마커 위치를 클릭한 위치로
 * 3. 반경 위치 변경
 * 4. 좌표로 행정동 주소 정보를 요청
 * */
function changeCrimeArea(latLng){
	// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	map.setCenter(latLng);
	// 마커 위치를 클릭한 위치로 옮깁니다
	crimeMarker.setPosition(latLng);
	// 반경 위치 변경
	circle.setPosition(latLng);
	// 좌표로 행정동 주소 정보를 요청합니다
	coord2addr(latLng);
}

function addr2coord(addr){
	geocoder.addr2coord(addr, function (status, result){
		if (status == daum.maps.services.Status.OK){
			var coords = new daum.maps.LatLng(result.addr[0].lat, result.addr[0].lng);
			changeCrimeArea(coords);
		} else {
			return false;
		}
	});
}

function coord2addr(latLng){
	// 좌표로 행정동 주소 정보를 요청합니다
	geocoder.coord2addr(latLng, function(status, result){
		if (status == daum.maps.services.Status.OK){
			$("#fullAddr").text(result[0].fullName);
			$(opener.document).find("#addr1").val(result[0].name1);
			$(opener.document).find("#addr2").val(result[0].name2);
			$(opener.document).find("#addr3").val(result[0].name3);
		} else {
			alert("좌표 > 주소 변환 실패!");
		}
	});
}


function cctvGroupAddOrDel(cctvMarkerList, addGroupCode){
	var cctvItemArea = $("#cctvChoice > tbody > tr");
	var isRemove = false;
	//addGroupCode = addGroupCode.indexOf("_", 3) == -1 ? addGroupCode : addGroupCode.substring(0, addGroupCode.indexOf("_", 3));

	for (var i=0; i<cctvItemArea.length; i++){
		if (addGroupCode != '' && cctvItemArea.eq(i).attr('id').indexOf(addGroupCode) > -1){
			cctvItemArea.eq(i).remove();
			isRemove = true;
		}
	}

	if (!isRemove){
		
		var cctvGroup = '';
		
		for (var i=0; i<cctvMarkerList.length; i++){
			
			cctvMarkerList[i].code1 = cctvMarkerList[i].code1.replace(/,/gi," ");
			
			if(i == 0){
				cctvGroup += '<tr id="tr_' + addGroupCode + '">';
				cctvGroup += 	'<td>';
				cctvGroup += 		'<table id="title_' + addGroupCode + '">';
				cctvGroup += 			'<tr>';
				cctvGroup += 				'<td>';
				cctvGroup += 					'<input id="groupChk_' + addGroupCode + '" type="checkbox" class="groupCode" style="margin: 0 5px 0 3px; position: relative; top: 2px;">';
				cctvGroup += 					'<label style="cursor:pointer;" for="groupChk_' + addGroupCode + '">' + addGroupCode + '</label><a href="#" data-target="#collapse_' + addGroupCode + '" data-toggle="collapse"><span class="arrow" style="font-size: 11px; margin-left: 2px; color:#909496;">▶</span></a>';
				cctvGroup += 				'</td>';
				cctvGroup += 			'</tr>';
				cctvGroup += 		'</table>';
				cctvGroup += 		'<div id="collapse_' + addGroupCode + '" class="collapse">';
				cctvGroup += 			'<table>';
			}
				
			cctvGroup += "<tr id='manageCode_"+cctvMarkerList[i].managecode+"' class='" + cctvMarkerList[i].code2 + "' code1='" + cctvMarkerList[i].code1 + "' onclick='mgrChoiceMarkerInfo(this)'>";
			cctvGroup += 	"<td style='padding-left: 25px;'>";
			cctvGroup += 		"<input id='itemChk_" + cctvMarkerList[i].managecode + "' type='checkbox' name='cctv[]' onclick=\"checkCctv('"+cctvMarkerList[i].code2+"');\" id='"+cctvMarkerList[i].managecode+"' style='margin-left: 3px; position: relative; top: 2px;'>&nbsp;&nbsp;&nbsp;";
			cctvGroup += 		"<span style='line-height:2px;'><label for='itemChk_" + cctvMarkerList[i].managecode + "' style='display:inherit; cursor:pointer;'>["+cctvMarkerList[i].code2+"] 관리코드 : "+cctvMarkerList[i].code1 + '</label>';
			if (cctvMarkerList[i].ptzYn == "Y"){
				cctvGroup += 	", 회전형 : <img src='/resources/images/arrow_around.gif' width='24' height='24' alt='' />";
			} else {
				if (isNaN(cctvMarkerList[i].directionDesc) && cctvMarkerList[i].directionDesc != "-1") {
					cctvGroup += ", "+cctvMarkerList[i].directionDesc+" : <img src='/resources/images/"+cctvMarkerList[i].directionImg+"' width='24' height='24' alt='' />";
				}
			}
			cctvGroup +=		"</span>";
			cctvGroup +=    	"<img class='cctvImg' src='/map/marker/getCctvImage.do?managecode=" + cctvMarkerList[i].managecode + "' onError='$(this).hide();' style='margin-left:3px; width:18px; height:18px; border-radius:2px; brder:1px solid gray;' alt='' />";
			cctvGroup += 	"</td>";
			cctvGroup += "</tr>";
			
			if(i == cctvMarkerList.length-1){
				cctvGroup += 			'</table>';
				cctvGroup += 		'</div>';
				cctvGroup += 	'</td>';
				cctvGroup += '</tr>';
			}
		}
		
		var $cctvGroup = $(cctvGroup);
		
		$("#cctvChoice > tbody:last").append($cctvGroup);
		
		$('.collapse').on({
			'hidden.bs.collapse' : function(e){
				var $target = $(e.target);
				$target.parent('td').find('.arrow').text('▼');
			},
			'show.bs.collapse' : function(e){
				var $target = $(e.target);
				$target.parent('td').find('.arrow').text('▶');
			}
		});
		
		$('#tr_' + addGroupCode + ' .collapse').collapse("show");
		
		$('#cctvChoice input[type=checkbox]').on('click', function(){
			var $checkbox = $(this);
			if($checkbox.hasClass('groupCode')){ //개소 코드 체크박스 선택
				var collapse = $checkbox.parents('table').next('div.collapse');
				var checkboxList = collapse.find('input[type=checkbox]');
				checkboxList.prop('checked', $checkbox.is(':checked'));
			}else{ //하위에 있는 CCTV별 코드 체크박스 선택
				var groupCodeChkbox = $checkbox.parents('div[id^="collapse_"]').prev('table').find('input.groupCode');
				var isAllChecked = true;
				$checkbox.parents('table').eq(0).find('[type=checkbox]').each(function(i,e){
					if(!$(this).is(':checked')){
						isAllChecked = false;
						return false;
					}
				});
				groupCodeChkbox.prop('checked', isAllChecked);
			}
		});
		
		$cctvGroup.find('tr[id^="manageCode_"] input[type=checkbox]').each(function(){
			var $this = $(this);
			var cntChecked = $('#cctvChoice tr[id^="manageCode_"] input[type=checkbox]:checked').length;
			$this.click();
		});
		
		var cctvImgLayout = $('.pop .mapArea .cctvImgLayout');
		var cctvImgInfo = cctvImgLayout.children().eq(0);
		var cctvImgArea = cctvImgLayout.children().eq(1);
		var cctvImg = cctvImgArea.children('img');
		$('.cctvImg').on({
			'mouseenter' : function(e){
				var $target = $(e.target);
				cctvImgLayout.css({'z-index':99999, 'text-indent': 0});
				cctvImgInfo.empty().append('<span>' + $target.prev().text() + '</span>').append($target.prev().children().eq(1).clone());
				cctvImg.attr('src', $target.attr('src'));
				cctvImg.css({'top': (cctvImgArea.height()-cctvImg.height())/2});
			},
			'mouseleave' : function(e){
				var cctvImgLayout = $('.pop .mapArea .cctvImgLayout');
				cctvImgLayout.css({'z-index':-1, 'text-indent': -999999999});
			}
		});
	}
}

function cctvAdd(cctvMarker){
	var cctvItemArea = $("#cctvChoice > tbody > tr");
	var isRemove = false;
	//var code = cctvMarker.code1.indexOf("_", 3) == -1 ? cctvMarker.code1 : cctvMarker.code1.substring(0, cctvMarker.code1.indexOf("_", 3));
	
	for (var i=0; i<cctvItemArea.length; i++){
		var $manageCode = cctvItemArea.eq(i).find('#manageCode_' + cctvMarker.managecode);
		if ($manageCode.index() > -1){
			$manageCode.remove();
			$('#tr_' + code + ' .collapse').collapse("show");
			var collapseItem = $('[id^=collapse_' + code + ']').find('tr');
			if(collapseItem.length == 0){
				cctvItemArea.eq(i).remove();
			}
			isRemove = true;
		}
	}

	if(!isRemove){
		cctvMarker.code1 = cctvMarker.code1.replace(/,/gi," ");
		var $trCode = $('#tr_' + code); 
		var $cctvGroup = $('tr[id="tr_' + code + '"]');
		
		if($trCode.index() == -1){ //해당 개소에 대한 목록이 선택 CCTV 목록에 존재하지 않는다면.
			var cctvGroup = '';
			cctvGroup += '<tr id="tr_' + code + '">';
			cctvGroup += 	'<td>';
			cctvGroup += 		'<table id="title_' + code + '">';
			cctvGroup += 			'<tr>';
			cctvGroup += 				'<td>';
			cctvGroup += 					'<input id="groupChk_' + code + '" type="checkbox" class="groupCode" style="margin: 0 5px 0 3px; position: relative; top: 2px;">';
			cctvGroup += 					'<label style="cursor:pointer;" for="groupChk_' + code + '">' + code + '</label><a href="#" data-target="#collapse_' + code + '" data-toggle="collapse"><span class="arrow" style="font-size: 11px; margin-left: 2px; color:#909496;">▶</span></a>';
			cctvGroup += 				'</td>';
			cctvGroup += 			'</tr>';
			cctvGroup += 		'</table>';
			cctvGroup += 		'<div id="collapse_' + code + '" class="collapse">';
			cctvGroup += 			'<table>';
			cctvGroup += 			'</table>';
			cctvGroup += 		'</div>';
			cctvGroup += 	'</td>';
			cctvGroup += '</tr>';
			
			$cctvGroup = $(cctvGroup);
			
			$("#cctvChoice > tbody:last").append($cctvGroup);
		}
		
		var cctvItem = '';
		cctvItem += "<tr id='manageCode_"+cctvMarker.managecode+"' class='" + cctvMarker.code2 + "' onclick='mgrChoiceMarkerInfo(this)'>";
		cctvItem += 	"<td style='padding-left: 25px;'>";
		cctvItem += 		"<input id='itemChk_" + cctvMarker.managecode + "' type='checkbox' name='cctv[]' onclick=\"checkCctv('"+cctvMarker.code2+"');\" id='"+cctvMarker.managecode+"' style='margin-left: 3px; position: relative; top: 2px;'>&nbsp;&nbsp;&nbsp;";
		cctvItem += 		"<span style='line-height:2px;'><label for='itemChk_" + cctvMarker.managecode + "' style='display:inherit; cursor:pointer;'>["+cctvMarker.code2+"] 관리코드 : "+cctvMarker.code1 + '</label>';
		if (cctvMarker.ptzYn == "Y"){
			cctvItem += 	", 회전형 : <img src='/resources/images/arrow_around.gif' width='24' height='24' alt='' />";
		} else {
			if (isNaN(cctvMarker.directionDesc) && cctvMarker.directionDesc != "-1") {
				cctvItem += ", "+cctvMarker.directionDesc+" : <img src='/resources/images/"+cctvMarker.directionImg+"' width='24' height='24' alt='' />";
			}
		}
		cctvItem += 	"</span>";
		cctvItem +=    	"<img class='cctvImg' src='/map/marker/getCctvImage.do?managecode=" + cctvMarker.managecode + "' onError='$(this).hide();' style='margin-left:3px; width:18px; height:18px; border-radius:2px; brder:1px solid gray;' alt='' />";
		cctvItem += 	"</td>";
		cctvItem += "</tr>";
		
		var $cctvItem = $(cctvItem);
		
		$('#collapse_' + code).children('table').append($cctvItem);
		
		$('.collapse').on({
			'hidden.bs.collapse' : function(e){
				var $target = $(e.target);
				$target.parent('td').find('.arrow').text('▼');
			},
			'show.bs.collapse' : function(e){
				var $target = $(e.target);
				$target.parent('td').find('.arrow').text('▶');
			}
		});
		
		$('#tr_' + code + ' .collapse').collapse("show");
		
		$('#cctvChoice input[type=checkbox]').on('click', function(){
			var $checkbox = $(this);
			if($checkbox.hasClass('groupCode')){ //개소 코드 체크박스 선택
				var collapse = $checkbox.parents('table').next('div.collapse');
				var checkboxList = collapse.find('input[type=checkbox]');
				checkboxList.prop('checked', $checkbox.is(':checked'));
			}else{ //하위에 있는 CCTV별 코드 체크박스 선택
				var groupCodeChkbox = $checkbox.parents('div[id^="collapse_"]').prev('table').find('input.groupCode');
				var isAllChecked = true;
				$checkbox.parents('table').eq(0).find('[type=checkbox]').each(function(i,e){
					if(!$(this).is(':checked')){
						isAllChecked = false;
						return false;
					}
				});
				groupCodeChkbox.prop('checked', isAllChecked);
			}
		});
		
		$cctvItem.find('input[type=checkbox]').each(function(){
			var $this = $(this);
			var cntChecked = $('#cctvChoice tr[id^="manageCode_"] input[type=checkbox]:checked').length;
			$this.click();
		});
		
		var cctvImgLayout = $('.pop .mapArea .cctvImgLayout');
		var cctvImgInfo = cctvImgLayout.children().eq(0);
		var cctvImgArea = cctvImgLayout.children().eq(1);
		var cctvImg = cctvImgArea.children('img');
		$('.cctvImg').on({
			'mouseenter' : function(e){
				var $target = $(e.target);
				cctvImgLayout.css({'z-index':99999, 'text-indent': 0});
				cctvImgInfo.empty().append('<span>' + $target.prev().text() + '</span>').append($target.prev().children().eq(1).clone());
				cctvImg.attr('src', $target.attr('src'));
				cctvImg.css({'top': (cctvImgArea.height()-cctvImg.height())/2});
			},
			'mouseleave' : function(e){
				var cctvImgLayout = $('.pop .mapArea .cctvImgLayout');
				cctvImgLayout.css({'z-index':-1, 'text-indent': -999999999});
			}
		});
	}
}

function cctvDel(trClass,trid){

	var $cctvChoiceTr = $("#cctvChoice > tbody > tr");
	var len = $("#cctvChoice > tbody > tr").length;
	var removeChk = false;

	if (len > 0){
		for (var i = 0; i < len; i++){
			if ($cctvChoiceTr.eq(i).children().eq(0).prop("class") == trClass
					&& $cctvChoiceTr.eq(i).children().eq(0).prop("id") == trid) {
				$cctvChoiceTr.eq(i).remove();
			}
		}
	}
}

function checkCctv(trClass,trid){
	//alert(trClass+"//"+trid);
}

var choiceCnt = 0;

function mgrChoiceMarkerInfo(choiceTr){
	
//	var $cctvChoiceTr = $("#cctvChoice > tbody > tr");
//	var len = $("#cctvChoice > tbody > tr").length;
//	var removeChk = false;
//
//	if (len > 0){
//		for (var i = 0; i < len; i++){
//			if ($cctvChoiceTr.eq(i).children().eq(0).prop("class") == $(choiceTr).children().eq(0).prop("class")
//					&& $cctvChoiceTr.eq(i).children().eq(0).prop("id") == $(choiceTr).children().eq(0).prop("id")) {
//				$cctvChoiceTr.eq(i).remove();
//				choiceCnt--;
//				removeChk = true;
//			}
//		}
//	} 
//	
//	if (!removeChk){
//		if (choiceCnt < 10){
//			$("#cctvChoice > tbody:last").append($(choiceTr).clone());
//			choiceCnt++;
//		} else {
//			alert("최대 10개까지 선택 가능합니다.");
//		}
//	}
}

function displayPlaces(markerList) {
	var listEl = document.getElementById('placesList'), menuEl = document
			.getElementById('menu_wrap'), fragment = document
			.createDocumentFragment(), bounds = new daum.maps.LatLngBounds(), listStr = '';

	// 검색 결과 목록에 추가된 항목들을 제거합니다
	//removeAllChildNods(listEl);
	$(menuEl).css('visibility','visible');
	$(listEl).empty();

	for (var i = 0; i < markerList.length; i++) {

		// 마커를 생성하고 지도에 표시합니다
		var placePosition = new daum.maps.LatLng(markerList[i].itemLatitude, markerList[i].itemLongitude), 
			marker = addMarker(markerList[i]),
			// 검색 결과 항목 Element를 생성합니다
			itemEl = getListItem(i, markerList[i]); // 검색 결과 항목 Element를 생성합니다

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다
		bounds.extend(placePosition);

		// 마커와 검색결과 항목에 mouseover 했을때
		// 해당 장소에 인포윈도우에 장소명을 표시합니다
		// mouseout 했을 때는 인포윈도우를 닫습니다
		(function(marker, title, code2, addr3) {
			daum.maps.event.addListener(marker, 'mouseover', function() {
				displayInfowindow(marker, title, code2);
			});

			daum.maps.event.addListener(marker, 'mouseout', function() {
				infowindow.close();
			});

			itemEl.onmouseover = function() {
				displayInfowindow(marker, title, code2);
			};

			itemEl.onmouseout = function() {
				infowindow.close();
			};
			
			// 마커에 click 이벤트를 등록합니다
			/*
			daum.maps.event.addListener(marker, 'click', function(){
				mgrChoiceMarkerGroup(markerInfo);
			});
			*/
			// 리스트에 click 이벤트를 등록합니다
			$(itemEl).click(function(){
				//title = title.indexOf("_", 3) == -1 ? title : title.substring(0,title.indexOf("_", 3));
				getCctvGroup(title, addr3)
				//cctvAdd(markerInfo);
			});
		})(marker, markerList[i].code1, markerList[i].code2, markerList[i].addr3);

		$(fragment).append(itemEl);
	}

	// 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	$(listEl).append(fragment);
	$(menuEl).scrollTop(0);

	// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	map.setBounds(bounds);
}

function getListItem(idx, markerObj) {

	var el = document.createElement('li'),

	/*itemStr = '<span class="markerbg marker_' + (idx + 1) + '"></span>';*/
	itemStr = '<span class="markerbg">';
	itemStr += '<img src="/resources/images/'+markerObj.imageSrc+'" />';
	if (markerObj.ptzYn == "Y"){
		itemStr += '<img src="/resources/images/arrow_around.gif" width="24" height="24" />';
	} else if (markerObj.directionDesc != '' ){
		if (isNaN(markerObj.directionDesc) && markerObj.directionDesc != "-1") {
			itemStr += '<img src="/resources/images/'+markerObj.directionImg+'" width="24" height="24" />';
		}
	}
	itemStr += '</span>';
	itemStr += '<div class="info">' + '<h5>' + markerObj.code2 + '</h5>';
	itemStr += '<div>관리코드 : ' + markerObj.code1 + '</div>';
	itemStr += '<span>' + markerObj.address + '</span>';
	itemStr += '</div>';

	$(el).html(itemStr);
	$(el).addClass('item');

	return el;
}

function addMarker(Obj) {
	var imageSrc = '/resources/images/marker/' + Obj.imageSrc, // 마커이미지의 주소입니다    
	//imageSize = new daum.maps.Size(16, 21), // 마커이미지의 크기입니다
	imageSize = new daum.maps.Size(19, 24), // 마커이미지의 크기입니다
	imageOption = {
		offset : new daum.maps.Point(8, 21)
	// 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	};

	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
	var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize,
			imageOption);

	var marker = new daum.maps.Marker({
				map : map, // 마커를 표시할 지도
				position : new daum.maps.LatLng(Obj.itemLatitude,
						Obj.itemLongitude), // 마커를 표시할 위치
				title : null, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				clickable : true,
				image : markerImage
			// 마커 이미지 
			});
	
	// 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    (function(marker, title, code2, addr3) {
        // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
        daum.maps.event.addListener(marker, 'mouseover', function() {
            displayInfowindow(marker, title, code2);
        });

        // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
        daum.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });
        
     	// 마커에 click 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'click', function(){
	    	//title = title.indexOf("_", 3) == -1 ? title : title.substring(0,title.indexOf("_", 3));
	    	getCctvGroup(title, addr3);
		});
    })(marker, Obj.code1, Obj.code2, Obj.addr3);

	return marker;
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title, code2, addr3) {
	var content = '<div style="padding:5px; z-index:1; width:250px; text-align: center;">' + code2 + '<br>' + '[' + title + ']</div>';

	infowindow.setContent(content);
	infowindow.open(map, marker);
}