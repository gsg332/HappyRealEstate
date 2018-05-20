<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script type="text/javascript" src="/resources/js/cctvPop.js"></script>
<script type="text/javascript">
	
	var map, geocoder, crimeMarker, circle, markers, infowindow;
	
	$(document).ready(function(){
		
		var mContainer = document.getElementById('cctvMap');
		var mOptions = {
			center: new daum.maps.LatLng("${lat}", "${lng}"),
			level: 4
		};
		
		// 지도를 생성합니다
		map = new daum.maps.Map(mContainer, mOptions);
		
		
		// 주소-좌표 변환 객체를 생성합니다
		geocoder = new daum.maps.services.Geocoder();
		
		infowindow = new daum.maps.InfoWindow({zindex:1});
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new daum.maps.MapTypeControl()
		// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
		var imageSrc = '/resources/images/ico_location.png', // 마커이미지의 주소입니다    
	    imageSize = new daum.maps.Size(24, 34), // 마커이미지의 크기입니다
	    imageOption = {offset: new daum.maps.Point(12, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	      
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption);
		
		crimeMarker = new daum.maps.Marker({
			position : map.getCenter(),		// 지도 중심좌표에 마커를 생성합니다 
			title : "사건장소",
			image : markerImage
		});
		
		// 지도에 마커를 표시합니다
		crimeMarker.setMap(map);
		
		// 지도에 표시할 원을 생성합니다
		circle = new daum.maps.Circle({
		    center : map.getCenter(),  // 원의 중심좌표 입니다 
		    radius: 200, // 미터 단위의 원의 반지름입니다 
		    strokeWeight: 5, // 선의 두께입니다 
		    strokeColor: '#75B8FA', // 선의 색깔입니다
		    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'dashed', // 선의 스타일 입니다
		    fillColor: '#CFE7FF', // 채우기 색깔입니다
		    fillOpacity: 0.7  // 채우기 불투명도 입니다   
		}); 

		// 좌표로 행정동 주소 정보를 요청합니다
		coord2addr(map.getCenter());
		
		getLocationCoordinate();
		
		getMarkerList();
		
	});
	
	/*
		구역 테두리 좌표 조회 및 그리기
	*/
	function getLocationCoordinate(){
		$.ajax({
			type : 'post',
			url : '/map/coordinate/list.json',
			dataType: 'json',
			success : function(data) {
				
				var polygonPath = new Array(data.coordsList.length);
				
				for (var i = 0; i < data.coordsList.length; i++){
					polygonPath[i] = new daum.maps.LatLng(data.coordsList[i].locationLatitude, data.coordsList[i].locationLongitude);
				}
				
				// 지도에 표시할 다각형을 생성합니다
				var polygon = new daum.maps.Polygon({
				    path:polygonPath, // 그려질 다각형의 좌표 배열입니다
				    strokeWeight: 3, // 선의 두께입니다
				    strokeColor: '#39DE2A', // 선의 색깔입니다
				    strokeOpacity: 0.6, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
				    strokeStyle: 'longdash', // 선의 스타일입니다
				    fillColor: '#A2FF99', // 채우기 색깔입니다
				    fillOpacity: 0.2 // 채우기 불투명도 입니다
				});

				// 지도에 다각형을 표시합니다
				polygon.setMap(map);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function getMarkerList(){

		$.ajax({
			type : 'post',
			url : '/map/marker/cctv/list.json',
			data : {
				videoId : "${videoId}"
			},
			dataType: 'json',
			success : function(data) {

				for (var i = 0; i < data.cctvMarkerList.length; i++){
					addMarker(data.cctvMarkerList[i]);
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}

</script>
</head>
<body>
	<form class="form-inline">
		<!--pop width 800px-->
		<div class="pop">
			<div class="pop_title"> 사건 장소 </div>
			<div class="box_gray" style="padding: 10px;">
				<table style="margin-bottom:0">
					<tbody>
						<tr>
							<td><label>사건장소&nbsp;</label></td>
							<td><input type="text" id="fullAddr" class="form-control" style="width:600px; margin-left:10px" readonly="readonly"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--지도영역 시작-->
			<div class="tvTitle" style="margin-top: 10px">
				<ul class="list-inline text-center">
				<c:forEach var="list" items="${cctvTypeList}" varStatus="status">
					
					<li><img src="/resources/images/${fn:replace(list.codeVal, 'location_', '')}" width="28" height="28" alt=""/><br/>${list.codeDesc}</li>
				</c:forEach>
				</ul>
			</div>
			<!-- <div class="map" id="cctvMap"></div> -->
			<div class="map_wrap">
				<div id="cctvMap" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
				<div id="menu_wrap" class="bg_white" style="visibility: hidden;">
			        <hr>
			        <ul id="placesList"></ul>
			        <div id="pagination"></div>
		        </div>
			</div>
		</div>
	</form>
</body>
</html>