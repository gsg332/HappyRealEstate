<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/directive.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<c:url value='/resources/css/apply_cctv_map.css'/>" />
<script type="text/javascript" src="/resources/js/cctvPop.js"></script>
<script type="text/javascript">
	
	var map, geocoder, crimeMarker, circle, markers, infowindow, videoListArr, videoList, listSize;
	var polygonObjList = new Object();
	
	$(document).ready(function(){

		<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
			<c:if test="${list.itemName eq 'INIT_MAP_LAT' }">
			<c:set var="INIT_MAP_LAT" value="${list.itemValue}" scope="session" />
			</c:if>
		</c:forEach>
		<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
			<c:if test="${list.itemName eq 'INIT_MAP_LNG' }">
			<c:set var="INIT_MAP_LNG" value="${list.itemValue}" scope="session" />
			</c:if>
		</c:forEach>
	
		var mContainer = document.getElementById('cctvMap');
		var mOptions = {
			center: new daum.maps.LatLng(<c:out value='${INIT_MAP_LAT}' />, <c:out value='${INIT_MAP_LNG}' />),
			level: 4
		};
		
		// 지도를 생성합니다
		map = new daum.maps.Map(mContainer, mOptions);
		
		map.setLevel(3); //축척 50m로 설정.
		
		// 주소-좌표 변환 객체를 생성합니다
		geocoder = new daum.maps.services.Geocoder();
		
		infowindow = new daum.maps.InfoWindow({zindex:1});
		
		$('#searchInput').on('keydown', function(e){
			if(e.keyCode==13){
				$("#btnCrimeArea").trigger('click');
				return false;
			}
		});
		
		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
		var mapTypeControl = new daum.maps.MapTypeControl()
		// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
		
		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
		var imageSrc = '/resources/images/ico_location.png', // 마커이미지의 주소입니다    
	    imageSize = new daum.maps.Size(48, 50), // 마커이미지의 크기입니다
	    imageOption = {offset: new daum.maps.Point(22, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	      
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
		    radius: 100, // 미터 단위의 원의 반지름입니다 
		    strokeWeight: 5, // 선의 두께입니다 
		    strokeColor: '#DD1144', // 선의 색깔입니다
		    strokeOpacity: 0.3, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'dashed', // 선의 스타일 입니다
		    fillColor: '#FF8AEF', // 채우기 색깔입니다
		    fillOpacity: 0.2  // 채우기 불투명도 입니다   
		}); 

		// 지도에 원을 표시합니다 
		circle.setMap(map); 
		
		// 좌표로 행정동 주소 정보를 요청합니다
		coord2addr(map.getCenter());
		
		// 지도에 클릭 이벤트를 등록합니다
		daum.maps.event.addListener(map, 'click', function(mouseEvent){
			
			changeCrimeArea(mouseEvent.latLng);
			
			$(opener.document).find("#lat").val(mouseEvent.latLng.getLat());
			$(opener.document).find("#lng").val(mouseEvent.latLng.getLng());
		});
		
		getLocationCoordinate();
		
		getMarkerList();
		
		$("#selRadius").change(function(){
			circle.setRadius($(this).val());
		});
		
		$("#btnCrimeArea").click(function(){
			var $searchType = $("input[name=searchType]").eq(0);
			if ($("#searchInput").val() != ''){
				if ($searchType.parent().hasClass('active')){
					searchCrimeArea();
				} else {
					addr2coord($("#searchInput").val());
				}
			}
		});
		
		$("#regBtn").click(function(){
			
			var choiceCctv = $("#cctvChoice > tbody > tr");
			var len = $(':checkbox[name="cctv[]"]:checked').length;
			var videoIds = "";
			
			if(len > 20){
				alert('20개를 초과하여 선택할 수 없습니다.');
				return;
			}
			
			if (len > 0){
				var uniqueCctvs = [];
				$(':checkbox[name="cctv[]"]').each(function(i,e){
					if($(this).is(':checked')){
						var videoId = $(this).parents('tr').eq(0).attr('class').replace(/,/gi,'&&') + '[' + $(this).parents('tr').eq(0).attr('code1') +']';
						videoId += "|"+$(this).prop("id").replace('itemChk_','');
						if($.inArray(videoId, uniqueCctvs) === -1){
							uniqueCctvs.push(videoId);
						} 
					}
				});
				videoIds = uniqueCctvs.join(',');
			}
			
			/*
			* 데이터 유효성 검사
			*/
			if (dataCheck(videoIds) == false) {
				return;
			}
			
			/* 실제 DB 에 들어가는 값 */
			$(opener.document).find("#videoId").val(videoIds);
			
			videoListArr = videoIds.split(',');
			videoList ='<table>';
			listSize = (videoListArr.length > 10) ? 10 : 5;
			for(var i=0,len=videoListArr.length;i < len;i++){
				if (i < listSize) {
					if(i == 0) videoList += '<td class="panel-body">';
					//videoList += "- "+videoListArr[i]+"<br>";
					videoList += "- "+videoListArr[i].replace(/&&/gi,',').split('|')[0]+"<br>";
				}else{
					if(i == listSize) videoList += '</td><td class="panel-body">';
					//videoList += "- "+videoListArr[i]+"<br>";
					videoList += "- "+videoListArr[i].replace(/&&/gi,',').split('|')[0]+"<br>";
				}

			}
			videoList +="<td></table>";
			
			$(opener.document).find("#fullAddr").text($("#fullAddr").text()+' '+$("#etcAddrTxt").text());
			$(opener.document).find("#addr4").val($("#etcAddrTxt").text());
			/*리스트만 그린다*/
			$(opener.document).find("#videoList").html(videoList);
			
			self.close();
		});
		
		$('#btnAllCheckToggle').on('click', function(){
			var checkboxList = $('#cctvChoice').find('input[type=checkbox]');
			var isChecked;
			
			checkboxList.each(function(i,e){
				var $checkbox =  $(this);
				isChecked = $checkbox.is(':checked');
				if(isChecked){
					checkboxList.prop('checked', false);
					return false;
				}
			});
			
			if(!isChecked){
				checkboxList.prop('checked', true);
			}
		});
		
		$('#btnSelDel').on('click', function(){
			$('#cctvChoice').find('input[type=checkbox]').each(function(i,e){
				var $this = $(this);
				if($this.is(':checked')){
					if($this.parents('table[id^=title_]').index() > -1){
						$this.parents('tr[id^=tr_]').eq(0).remove();
					}else{
						$this.parents('tr').eq(0).remove();
					}
				}
			});		
		});
	});
	
	/*
	* 데이터 유효성 검사
	*/
	function dataCheck(videoIds){
		var lat = $(opener.document).find("#lat").val() || '';
		var lng = $(opener.document).find("#lng").val() || '';

		if (lat =='' || lng == '') {
			alert("사건 장소를 선택하여 주십시요.");
			return false;
		}
		if (videoIds == '' || videoIds == undefined) {
			alert("선택된 CCTV가 없습니다.");
			return false;
		}
		return true;

	}
	/*
		구역 테두리 좌표 조회 및 그리기
	*/
	function getLocationCoordinate(){
		$.ajax({
			type : 'post',
			url : '/map/coordinate/list.json',
			dataType: 'json',
			success : function(data) {

				var polygonPath = new Array();
				
				for (var i = 0; i < data.coordsList.length; i++){
					polygonPath[i] = new daum.maps.LatLng(data.coordsList[i].locationLatitude, data.coordsList[i].locationLongitude);
					if((i > 0 && data.coordsList[i].locationsId != data.coordsList[i-1].locationsId) || i == data.coordsList.length-1){ //locationId값이 변경되었을 때, 마지막 목록일 때.
						// 지도에 표시할 다각형을 생성합니다
						var polygon;
						if(data.coordsList[i-1].locationDong == ''){ //구단위 경계선
							polygon = new daum.maps.Polygon({
							    path: polygonPath.slice(0,polygonPath.length-1), // 그려질 다각형의 좌표 배열입니다
							    strokeWeight: 5, // 선의 두께입니다
							    strokeColor: '#ED1F58', // 선의 색깔입니다
							    strokeOpacity: 0.5, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
							    strokeStyle: 'longdash', // 선의 스타일입니다
							    fillColor: '#000000', // 채우기 색깔입니다
							    fillOpacity: 0.0 // 채우기 불투명도 입니다
							});	
						}else{
							polygon = new daum.maps.Polygon({ //동단위 경계선
							    path: polygonPath.slice(0,polygonPath.length-1), // 그려질 다각형의 좌표 배열입니다
							    strokeWeight: 2, // 선의 두께입니다
							    strokeColor: '#0066ff', // 선의 색깔입니다
							    strokeOpacity: 0.5, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
							    strokeStyle: 'longdash', // 선의 스타일입니다
							    fillColor: '#A2FF99', // 채우기 색깔입니다
							    fillOpacity: 0.3 // 채우기 불투명도 입니다
							});
						}
						polygonObjList[data.coordsList[i-1].locationDong] = polygon; 
						polygon.setMap(map); // 지도에 다각형을 표시합니다
						//polygonObjList['옥수동'].setMap(null); 경계선을 제겨할 경우.
						polygonPath = new Array();
					}
				}
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
	
	function getCctvGroup(code, addr3){
		//code = code.indexOf("_") == -1 ? code : code.substring(0, code.lastIndexOf('_'));
		$.ajax({
			type : 'post',
			url : '/map/marker/cctv/list.json',
			dataType : 'json',
			data : {
				code1 : code,
				addr3 : addr3
			},
			success : function(data) {
				cctvGroupAddOrDel(data.cctvMarkerList, code);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function searchCrimeArea() {
		var $searchType = $("input[name=searchType]").eq(0);
		if($searchType.parent().hasClass('active')){
			$.ajax({
				type : 'post',
				url : '/map/marker/cctv/list.json',
				dataType : 'json',
				data : {
					code1 : $("#searchInput").val()
				},
				success : function(data) {
					if (data.cctvMarkerList.length > 0) {
						// 정상적으로 검색이 완료됐으면 검색 목록과 마커를 표출합니다
						displayPlaces(data.cctvMarkerList);
					}
				},
				error : function(request, status, error) {
					errorModal(request);
				}
			});
		}
	}
	
	function changeRadius(){
		$.ajax({
			type : 'post',
			url : '/map/marker/radius.json',
			dataType: 'json',
			data : {
				"itemLatitude" : crimeMarker.getPosition().getLat(), 
				"itemLongitude" : crimeMarker.getPosition().getLng(),
				"radius" : $('#selRadius').val()
			},
			success : function(data) {
				if (data.nearMarkerList.length > 0){
					choiceCnt = 0;
					var addedCctvGroup = [];
					for(var i = 0; i < data.nearMarkerList.length; i++){
						//var code = data.nearMarkerList[i].code1.indexOf("_", 3) == -1 ? data.nearMarkerList[i].code1 : data.nearMarkerList[i].code1.substring(0,data.nearMarkerList[i].code1.indexOf("_", 3));
						var code = data.nearMarkerList[i].code1;
						$("#cctvChoice > tbody > tr").each(function(i,e){
							var $this = $(this);
							if($this.attr('id').indexOf('tr_' + code) > -1){ //개수코드 목록이 존재하면.
								$this.remove();
							}
						});
					}
					
					for(var i = 0; i < data.nearMarkerList.length; i++){
						//var code = data.nearMarkerList[i].code1.indexOf("_", 3) == -1 ? data.nearMarkerList[i].code1 : data.nearMarkerList[i].code1.substring(0,data.nearMarkerList[i].code1.indexOf("_", 3));
						var code = data.nearMarkerList[i].code1;
						var addr3 = data.nearMarkerList[i].addr3;
						var isCodeTrExist = false;
						//돌면서 목록이 있는지 그리고 목록이 있더라도 자식 목록이 있는지 체크한다.
						$("#cctvChoice > tbody > tr").each(function(i,e){
							var $this = $(this);
							if($this.attr('id').indexOf('tr_' + code) > -1){
								isCodeTrExist = true;
								return false;
							}
						});
						
						if(!isCodeTrExist){
							var radius = getDistance(crimeMarker.getPosition().getLat(), crimeMarker.getPosition().getLng(), data.nearMarkerList[i].itemLatitude, data.nearMarkerList[i].itemLongitude);
							if (radius <= circle.getRadius()){
								var isGroupAdded = false;
								for(var y=0; y<addedCctvGroup.length; y++){
									if(addedCctvGroup[y] == code){
										isGroupAdded = true;
										break;
									}
								}
								if(!isGroupAdded){
									getCctvGroup(code, addr3);
									addedCctvGroup.push(code);
								}
							}							
						}					
					}
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function toggleEtcAddrInput(){
		var $etcAddrInputArea = $('#etcAddrInputArea');
		var $etcAddrTxtArea = $('#etcAddrTxtArea');
		var $etcAddrTxt = $('#etcAddrTxt');
		var $etcAddrInput = $('#etcAddrInput');
		
		if($etcAddrInputArea.hasClass('hide')){ //수정클릭
			$etcAddrInputArea.removeClass('hide');
			$etcAddrTxtArea.addClass('hide');
			
			$etcAddrInput.on('keydown', function(e){
				if(e.keyCode==13){
					toggleEtcAddrInput();
					return false;
				}
			});
		}else{ //완료클릭
			$etcAddrInputArea.addClass('hide');
			$etcAddrTxtArea.removeClass('hide');
			$etcAddrTxt.text($etcAddrInput.val());
		}
	}
</script>
</head>
<body>
	<form class="form-inline">
		<!--pop width 800px-->
		<div class="pop">
			<div class="pop_title"> CCTV 선택 </div>
			<div class="box_gray" style="padding: 10px;">
				<div>
					<div class="btn-group" data-toggle="buttons">
		                <label class="btn_radio btn btn-default active">
		                    <input type="radio" name="searchType" value="code1"/> 관리번호
		                </label> 
		                <label class="btn_radio btn btn-default">
		                    <input type="radio" name="searchType" value="address"/> 주소
		                </label> 
		            </div>
		            <input type="text" id="searchInput" name="searchInput" class="searchInput form-control">
		            <button type="button" id="btnCrimeArea" class="btnCrimeArea btn btn-default"></button>
		       	</div>
			</div>
			<!--지도영역 시작-->
			<div class="pop">
				<div class="mapArea">
					<div class="cctvImgLayout">
						<div class="cctvImgInfo"></div>
  						<div class="cctvImg"><img src="" alt=""/></div>
  					</div>
					<div class="addressArea">
						<div class="crimeAddressArea form-group">
						  <span id="fullAddr" class="fullAddr">범죄 주소 </span>
						  <span id="etcAddrInputArea" class="hide">
						  	<input type="text" id="etcAddrInput" class="etcAddrInput form-control" placeholder="상세 주소 입력">
						  	<button type="button" class="btn btn-primary" onclick="toggleEtcAddrInput();">완료</button>
						  </span>
						  <span id="etcAddrTxtArea">
						  	<span id="etcAddrTxt" class="etcAddrTxt"></span>
						  	<button type="button" id="etcAddrUpdate" class="btn btn-primary" onclick="toggleEtcAddrInput();">수정</button>
						  </span>
						  <!-- <input type="text" class="form-control" id="crimeAddress"> -->
						</div>
						<div class="selRadiusArea form-group">
							<label for="selRadius">반경</label>
							<select id="selRadius" name="selRadius" class="form-control" style="margin-right: 5px;">
								<c:forEach var="meter" begin="50" end="500" step="50" varStatus="status">
									<c:choose>
										<c:when test="${meter eq 100 }"><option value="${meter}" selected="selected">${meter}m</option></c:when>
										<c:otherwise><option value="${meter}">${meter}m</option></c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<button type="button" class="selRadiusCctv btn btn-primary" onclick="changeRadius()">선택</button>
						</div>
					</div>
					<div class="map_wrap">
						<div id="cctvMap" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
						<div id="menu_wrap" class="bg_white" style="visibility: hidden;">
					        <hr>
					        <ul id="placesList"></ul>
					        <div id="pagination"></div>
				        </div>
					</div>
				</div>
				<div class="cctv_selected">
					<div class="b_title">
						<!-- <span class="b_title1">반경 내 CCTV</span>  -->
						<span class="b_title2">선택 CCTV</span>
					</div>
					<div class="box_img">
						<table class="table table-striped" id="cctvChoice">
							<tbody></tbody>
						</table>
					</div>
					<button type="button" id="btnAllCheckToggle" class="btn btn-default btnAllCheckToggle">전체선택/해제</button>
					<button type="button" id="btnSelDel" class="btn btn-default btnSelDel">선택삭제</button>
					<button type="button" id="regBtn" class="btn btn-primary btnReg">선택등록</button>
				</div>
			</div>
			<div class="tvTitle">
				<ul class="list-inline text-center">
				<c:forEach var="list" items="${cctvTypeList}" varStatus="status">
					<li style="padding-left:2px;"><img src="/resources/images/marker/${list.codeVal}" width="28" height="31" alt=""/><br/>${list.codeDesc}</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>