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
			$("#fullAddr").val(result[0].fullName);
			$(opener.document).find("#addr1").val(result[0].name1);
			$(opener.document).find("#addr2").val(result[0].name2);
			$(opener.document).find("#addr3").val(result[0].name3);
		} else {
			alert("좌표 > 주소 변환 실패!");
		}
	});
}

var choiceCnt = 0;

function mgrChoiceMarkerInfo(title){
	
	var len = $(".table > tbody > tr").length;
	var removeChk = false;
	
	if (len > 0){
		for (var i = 0; i < len; i++){
			if ($(".table > tbody > tr").eq(i).children().eq(1).prop("class") == title){
				$(".table > tbody > tr").eq(i).remove();
				choiceCnt--;
				removeChk = true;
			}
		}
	} 
	
	if (!removeChk){
		if (choiceCnt < 10){
			var tr = "<tr><td><input type='checkbox'></td><td class='"+title+"'>["+title+"] 동서남북</td></tr>";
			$(".table > tbody:last").append(tr);
			choiceCnt++;
		} else {
			alert("최대 10개까지 선택 가능합니다.");
		}
	}
}

function displayPlaces(markerList) {
	var listEl = document.getElementById('placesList'), menuEl = document
			.getElementById('menu_wrap'), fragment = document
			.createDocumentFragment(), bounds = new daum.maps.LatLngBounds(), listStr = '';

	// 검색 결과 목록에 추가된 항목들을 제거합니다
	//removeAllChildNods(listEl);
	$(menuEl).prop("style", "visibility:visible");
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
		(function(marker, title, code2) {

			itemEl.onmouseover = function() {
				displayInfowindow(marker, code2, code1);
			};
			itemEl.onmouseout = function() {
				infowindow.close();
			};
			// 리스트에 click 이벤트를 등록합니다
			$(itemEl).click(function(){
				mgrChoiceMarkerInfo(marker.getTitle());
			});
		})(marker, markerList[i].code1, markerList[i].code2);

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
	itemStr += '</span>';
	itemStr += '<div class="info">' + '<h5>' + markerObj.code1 + '</h5>';
	itemStr += '<span>' + markerObj.address + '</span>';
	itemStr += '</div>';

	$(el).html(itemStr);
	$(el).addClass('item');

	return el;
}

function addMarker(Obj, type) {
	var imageSrc = '/resources/images/marker/' + Obj.imageSrc, // 마커이미지의 주소입니다    
	//imageSize = new daum.maps.Size(16, 21), // 마커이미지의 크기입니다
	imageSize = new daum.maps.Size(19, 24), // 마커이미지의 크기입니다
	imageOption = {
		offset : new daum.maps.Point(8, 21)
	// 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	};

	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
	var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption);

	var marker = new daum.maps.Marker(
			{
				position : new daum.maps.LatLng(Obj.itemLatitude, Obj.itemLongitude), // 마커를 표시할 위치
				title : null, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
				clickable : true,
				image : markerImage
			});

	// 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    (function(marker, markerInfo) {
    	if(type == 'dongCenter'){
    		// 마커에 click 이벤트를 등록합니다
    	    daum.maps.event.addListener(marker, 'click', function(){
    	    	// mgrChoiceMarkerInfo(title);
    	    	detailTypeAddr3Stat(markerInfo.addr3);
    		});
    	    
    	    //대표마커 텍스트 생성
    	    var content = $('<div/>').css({'width':'105px', 'height':'auto', 'z-index':'9999', 'text-align':'center', 'position':'absolute','background-color':'#ffffff','color':'#000000','top':'-28px','left':'-43px','padding':'3px','border-radius':'8px','border':'1px solid #000000','font-weight':'bold'});
    		content.html(markerInfo.addr3 + '(' + markerInfo.applyCnt + ')');
    		$(marker).data('addr3', markerInfo.addr3);
    		$(marker).data('itemLongitude', markerInfo.itemLongitude);
    		$(marker).data('itemLatitude', markerInfo.itemLatitude);
    		$(marker.a.children[2]).append(content);
    	}else{
    		// 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
    		daum.maps.event.addListener(marker, 'mouseover', function() {
	            // infowindow.open(map, marker);
	            displayInfowindow(marker, markerInfo.code2, markerInfo.code1);
	        });
	        // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
	        daum.maps.event.addListener(marker, 'mouseout', function() {
	            infowindow.close();
	        });
	        
	        $(marker).data('itemType', Obj.itemType);
    	}
    })(marker, Obj);
    
	return marker;
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title, code1) {
	var content = '<div style="padding:5px;z-index:1; width:250px; text-align: center;">' + title  + '<br>' + '[' + code1 +']</div>';

	infowindow.setContent(content);
	infowindow.open(map, marker);
}