<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript" src="/resources/js/map.js"></script>
<script type="text/javascript">
	try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
</script>
<script type="text/javascript">
	
	var map, geocoder, crimeMarker, circle, infowindow;
	var cctvMarkers = [], crimeMarkers = [], dongCenterMarkers = [];
	var isDisableCrimeTypeArea = false;
	var isDisableCctvTypeArea = false;
	var polygonObjList = new Object();

	$(document).ready(function(){
		
		disableCrimeTypeArea(true, true);
		disableCctvTypeArea(true, false);
		
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
	
		var container = document.getElementById('mapArea');
		var options = {
//			center: new daum.maps.LatLng(37.56338758202477, 127.03686447411565),
			center: new daum.maps.LatLng(<c:out value='${INIT_MAP_LAT}' />, <c:out value='${INIT_MAP_LNG}' />),
			level: 4
		};

		map = new daum.maps.Map(container, options);
		
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
		
		var startDate = new Date("2013-01-01");
		var fromEndDate = new Date();
		var toEndDate = new Date();
		
		// 검색 시작 날짜 달력 세팅
		$("#searchStDateView").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : "2013-01-01",
			endDate : fromEndDate
			
		}).on('changeDate', function(selected){
			//console.log("stDate change");
			startDate = new Date(selected.date.valueOf());
			startDate.setDate(startDate.getDate(new Date(selected.date.valueOf())));
			$("#searchEdDateView").datepicker('setStartDate', startDate);
			$("#searchStDate").val($("#searchStDateView").val().replace(/-/gi,''));
			
		});

		// 검색 종료 날짜 달력 세팅
		$("#searchEdDateView").datepicker({
			format : "yyyy-mm-dd",
			language : "ko",
			autoclose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : toEndDate
		}).on('changeDate', function(selected){
			//console.log("endDate change");
			fromEndDate = new Date(selected.date.valueOf());
			fromEndDate.setDate(fromEndDate.getDate(new Date(selected.date.valueOf())));
			$("#searchStDateView").datepicker('setEndDate', fromEndDate);
			$("#searchEdDate").val($("#searchEdDateView").val().replace(/-/gi,''));
		});
		
		
		
		// 범죄유형 전체 체크박스 처리
		$("#crimeTypeChkAll").click(function(){

			if ($(this).prop("checked")){
				$("input[name=crimeTypeChkBox]:checkbox").each(function(){
					$(this).prop("checked", true);
				});
				if ($("input[name=crimeTypeView]:checked").prop("id") == "crimeTypeViewOn"){
					hideMarker("crime");
					showMarker("crime");
				}
			} else {
				$("input[name=crimeTypeChkBox]:checkbox").each(function(){
					$(this).prop("checked", false);
				});
				hideMarker("crime");
			}
		});
		
		
		// cctv유형 전체 체크박스 처리
		$("#cctvTypeChkAll").click(function(){

			if ($(this).prop("checked")){
				$("input[name=cctvTypeChkBox]:checkbox").each(function(){
					$(this).prop("checked", true);
				});
				if ($("input[name=cctvTypeView]:checked").prop("id") == "cctvTypeViewOn"){
					hideMarker("cctv");
					showMarker("cctv");
				}
			} else {
				$("input[name=cctvTypeChkBox]:checkbox").each(function(){
					$(this).prop("checked", false);
				});
				hideMarker("cctv");
			}
		});
		
		$("input[name=crimeTypeView]").change(function(){
			if ($(this).prop("id") == "crimeTypeViewOn"){
				showMarker($(this).val());
				disableCrimeTypeArea(false, false);
			} else {
				hideMarker($(this).val());
				disableCrimeTypeArea(true, false);
			}
		});
		
		$(".crimeTypeMarkerArea > .btn-group").on('click',function(){
			if(isDisableCrimeTypeArea){
				return false;
			}
		});
		
		$("input[name=cctvTypeView]").change(function(){

			if ($(this).prop("id") == "cctvTypeViewOn"){
				showMarker($(this).val());
				disableCctvTypeArea(false, false);
			} else {
				hideMarker($(this).val());
				disableCctvTypeArea(true, false);
			}
		});
		
		$("input[name=crimeTypeChkBox]").click(function(){
			if ($("input[name=crimeTypeView]:checked").prop("id") == "crimeTypeViewOn"){
				hideMarker("crime");
				showMarker("crime");
			}
			
		});
		
		$("input[name=cctvTypeChkBox]").click(function(){
			if ($("input[name=cctvTypeView]:checked").prop("id") == "cctvTypeViewOn"){
				hideMarker("cctv");
				showMarker("cctv");
			}
			
		});
		
		// 지역별 위험 지수 통계
		searchAreaStatList();
		// 유형별 범죄현황 통계
		//searchTypeStatList('dong');
		// 목적별 cctv 설치현황
		//searchCctvInstStatList('dong');
		
		//searchNotUseCctvList();
		
		//getCrimeMarkerList();
		
		getCctvMarkerList();
		//동대표마커 목록 가져오기
		getDongCenterMarkerList();
		
		getLocationCoordinate();
		
		//일자 셋팅
		setDatePickerToday();
		
		$('.cctvTypeMarkerArea #cctvTypeViewOn').click();
	});
	
	function disableCrimeTypeArea(isCheckAreaDisable, isBtnDisable){
		var crimeTypeMarkerArea = $(".crimeTypeMarkerArea");
		if(isCheckAreaDisable){
			$('#crimeTypeDisableLayer').addClass('crimeTypeDisableLayer');
		}else{
			$('#crimeTypeDisableLayer').removeClass('crimeTypeDisableLayer');			
		}
		if(isBtnDisable){
			crimeTypeMarkerArea.find("label.btn").addClass('disabled');
		}else{
			crimeTypeMarkerArea.find("label.btn").removeClass('disabled');
		}
		var isOn = crimeTypeMarkerArea.find("label.btn").eq(0).hasClass('active');
		crimeTypeMarkerArea.find("input, select, button, textarea").prop("disabled",!isOn);
		if(isOn){
			$('#crimeTypeDisableLayer').removeClass('crimeTypeDisableLayer');
		}else{
			$('#crimeTypeDisableLayer').addClass('crimeTypeDisableLayer');
		}
		isDisableCrimeTypeArea = isBtnDisable;
	}
	
	function disableCctvTypeArea(isCheckAreaDisable, isBtnDisable){
		var cctvTypeMarkerArea = $(".cctvTypeMarkerArea");
		if(isCheckAreaDisable){
			$('#cctvTypeDisableLayer').addClass('cctvTypeDisableLayer');
		}else{
			$('#cctvTypeDisableLayer').removeClass('cctvTypeDisableLayer');			
		}
		if(isBtnDisable){
			cctvTypeMarkerArea.find("label.btn").addClass('disabled');
		}else{
			cctvTypeMarkerArea.find("label.btn").removeClass('disabled');
		}
		var isOn = cctvTypeMarkerArea.find("label.btn").eq(0).hasClass('active');
		cctvTypeMarkerArea.find("input, select, button, textarea").prop("disabled",!isOn);
		if(isOn){
			$('#cctvTypeDisableLayer').removeClass('cctvTypeDisableLayer');
		}else{
			$('#cctvTypeDisableLayer').addClass('cctvTypeDisableLayer');
		}
		isDisableCctvTypeArea = isBtnDisable;
	}
	
	/**
	 *  달력 버튼 클릭 처리
	 */
	function toggleCalendar(val){
		if (val == "st"){
			if ($("#searchStDateView").val() != ''){
				$("#searchStDateView").datepicker("setDate", $("#searchStDateView").val());
				$("#searchStDate").val($("#searchStDateView").val().replace(/-/gi,''));
			}
			$("#searchStDateView").focus();
		} else {
			if ($("#searchEdDateView").val() != ''){
				$("#searchEdDateView").datepicker("setDate", $("#searchEdDateView").val());
				$("#searchEdDate").val($("#searchEdDate").val().replace(/-/gi,''));
			}
			$("#searchEdDateView").focus();
		}
	}
	
	function searchAllMapStat(){
		// 날짜 체크
		if (date_check() == false) {
			return false;
		}
		//form 검색날자 셋팅
		$("#searchStDate").val($("#searchStDateView").val().replace(/-/gi,''));
		$("#searchEdDate").val($("#searchEdDateView").val().replace(/-/gi,''));
		
		// 지역별 통계
		searchAreaStatList();
		// 유형별 통계
		searchTypeStatList("dong");
		// 목적별 cctv 설치현황
		searchCctvInstStatList('dong');
		// 범죄유형 마커리스트
		//getCrimeMarkerList();
		// 범죄유형 마커 표기
		//crimeTypeView();
		//동대표마커 목록 가져오기(범죄 카운트 가져오기)
		getDongCenterMarkerList();
		
		detailTypeAddr3Stat($('#locationDong').val());
	}
	
	/**
	* 검색 후 범죄유형 마커 
	*/
	function crimeTypeView() {
		
		if ($("input[id=crimeTypeViewOn]").prop("checked")){
			showMarker("crime");
		} else {
			hideMarker("crime");
		}
	}
	
	/*
		지역별 통계 조회
	*/
	function searchAreaStatList(){
		
		var params = $("#searchFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/map/area/stat.json',
			dataType: 'html',
			data : params,
			success : function(data) {
				$("#areaStatDiv").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	/*
		유형별 통계 조회
	*/
	function searchTypeStatList(unit){
		
		var params = $("#searchFrm").serialize();
		var url;

		if (unit == "gu"){
			url = "/map/type/addr2stat.json";
		} else {
			url = "/map/type/addr3stat/list.json";
		}
		
		$.ajax({
			type : 'post',
			url : url,
			dataType: 'html',
			data : params,
			success : function(data) {
				$("#typeStatDiv").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function detailTypeAddr3Stat(dong){
		if(dong){
			$.ajax({
				type : 'post',
				url : '/map/type/addr3stat.json',
				dataType: 'html',
				data : {
					searchStDate : $('#searchStDate').val(),
					searchEdDate : $('#searchEdDate').val(),
					timeSlot00 : $('input[name="timeSlot00"]').is(':checked') ? $('input[name="timeSlot00"]').val() : null,
					timeSlot07 : $('input[name="timeSlot07"]').is(':checked') ? $('input[name="timeSlot07"]').val() : null,
					timeSlot18 : $('input[name="timeSlot18"]').is(':checked') ? $('input[name="timeSlot18"]') : null,
					timeSlot22 : $('input[name="timeSlot22"]').is(':checked') ? $('input[name="timeSlot22"]') : null,
					timeSlot03 : $('input[name="timeSlot03"]').is(':checked') ? $('input[name="timeSlot03"]') : null,
					locationDong : dong
				},
				success : function(data) {
					$("#typeStatDiv").html(data);
					
					displayTypeStatDiv();
					
					$('#locationDong').val(dong);
					hideMarker('crime');
					crimeMarkers = [];
					for (var i=0; i<crimeMarkerList.length; i++){
						crimeMarkers.push(addMarker(crimeMarkerList[i]));
					}
					crimeTypeView();
					disableCrimeTypeArea(true, false);
					$('.crimeTypeMarkerArea #crimeTypeViewOn').click();
					//$('.cctvTypeMarkerArea #cctvTypeViewOn').click();

					for(var i=0; i<dongCenterMarkers.length; i++){
						var addr3 = $(dongCenterMarkers[i]).data('addr3');
						if (addr3 == dong){
							var itemLongitude = $(dongCenterMarkers[i]).data('itemLongitude');
							var itemLatitude = $(dongCenterMarkers[i]).data('itemLatitude');
							map.setCenter(new daum.maps.LatLng(itemLatitude, itemLongitude));
							$('body,html').animate({scrollTop:0},500);
						}
					}
					//모든 경계선 Off
					for(var i=0; i<Object.keys(polygonObjList).length; i++){
						polygonObjList[Object.keys(polygonObjList)[i]].setMap(null);
					}
					polygonObjList[dong].setMap(map); //동 경계선 On
					polygonObjList[''].setMap(map); //구전체 경계선 On
				},
				error : function(request, status, error) {
					errorModal(request);
				}
			});
		}
	}
	
	function displayTypeStatDiv(){
		$('.nav.nav-tabs').children().eq(0).removeClass('active');
		$("#areaStatDiv").removeClass('in').removeClass('active');
		$('.nav.nav-tabs').children().eq(1).addClass('active');
		$("#typeStatDiv").addClass('in').addClass('active');
		$('.nav.nav-tabs').children().eq(2).removeClass('active');
		$("#cctvInstDiv").removeClass('in').removeClass('active');		
	}
	
	/*
		목적별 cctv 설치 현황 조회
	*/
	function searchCctvInstStatList(unit){
		var params = $("#searchFrm").serialize();
		var url;

		if (unit == "gu"){
			url = "/map/cctv/instStat/addr2stat.json";
		} else {
			url = "/map/cctv/instStat/addr3stat/list.json";
		}
		
		$.ajax({
			type : 'post',
			url : url,
			data : params,
			dataType: 'html',
			success : function(data) {
				$("#cctvInstDiv").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function detailCctvInstAddr3Stat(dong){
		$.ajax({
			type : 'post',
			url : '/map/cctv/instStat/addr3stat.json',
			dataType: 'html',
			data : {
				locationDong : dong
			},
			success : function(data) {
				$("#cctvInstDiv").html(data);
				$('#locationDong').val(dong);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	/*
		미활용 cctv 목록 조회
	*/
	function searchNotUseCctvList(){
		$.ajax({
			type : 'post',
			url : "/map/cctv/notUse/list.json",
			dataType: 'html',
			success : function(data) {
				$("#notUseCctvDiv").html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function getCrimeMarkerList(){
		var params = $("#searchFrm").serialize();
		$.ajax({
			type : 'post',
			url : '/map/marker/crime/list.json',
			dataType: 'json',
			async : false,
			data : params,
			success : function(data) {
				hideMarker("crime");
				crimeMarkers =null;
				crimeMarkers =[];
				for (var i = 0; i < data.crimeMarkerList.length; i++){
					crimeMarkers.push(addMarker(data.crimeMarkerList[i]));
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function getCctvMarkerList(){
		$.ajax({
			type : 'post',
			url : '/map/marker/cctv/list.json',
			dataType: 'json',
			async : false,
			success : function(data) {
				for (var i = 0; i < data.cctvMarkerList.length; i++){
					cctvMarkers.push(addMarker(data.cctvMarkerList[i]));
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function getDongCenterMarkerList(){
		var params = $("#searchFrm").serialize();
		$.ajax({
			type : 'post',
			url : '/map/marker/dongCenter/list.json',
			dataType: 'json',
			async : true,
			data : params,
			success : function(data) {
				hideMarker('dongCenter');
				dongCenterMarkers = [];
				for (var i = 0; i < data.dongCenterMarkerList.length; i++){
					dongCenterMarkers.push(addMarker(data.dongCenterMarkerList[i], 'dongCenter'));
				}
				showMarker('dongCenter');
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
	}
	
	function showMarker(type){
		if(type == "crime"){
			for (var i = 0; i < crimeMarkers.length; i++){
				$("input[name=crimeTypeChkBox]").each(function(){
					if ($(this).is(":checked")){
						var itemType = $(crimeMarkers[i]).data('itemType');
						if (itemType == $(this).val()){
							crimeMarkers[i].setMap(map);
						}
					}
				});
			}
		}else if(type == "cctv"){
			for (var i = 0; i < cctvMarkers.length; i++){
				$("input[name=cctvTypeChkBox]").each(function(){
					if ($(this).is(":checked")){
						var itemType = $(cctvMarkers[i]).data('itemType');
						if (itemType == $(this).val()){
							cctvMarkers[i].setMap(map);
						}
					}
				});
			}
		}else if(type == "dongCenter"){
			for (var i = 0; i < dongCenterMarkers.length; i++){
				dongCenterMarkers[i].setMap(map);
			}
		}
	}
	
	function hideMarker(type){
		if(type == "crime"){
			for (var i = 0; i < crimeMarkers.length; i++){
				crimeMarkers[i].setMap(null);
			}
		}else if(type == "cctv"){
			for (var i = 0; i < cctvMarkers.length; i++){
				cctvMarkers[i].setMap(null);
			}
		}else if(type == "dongCenter"){
			for (var i = 0; i < dongCenterMarkers.length; i++){
				dongCenterMarkers[i].setMap(null);
			}
		}
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
	
	/*
	*	시간대 설정 함수
	*/
	function selTimeSlot(Slot,obj) {
		
		var date,month,now,fDate,timeString,s,e;
		var timeStringArr = [];
		date = new Date();
		var tg=false;
		var cnt=0;
		var timeString = {
				"00" : "종일"
				,"07" : "주간"
				,"18" : "초저녁"
				,"22" : "심야"
				,"03" : "새벽"
			}
		
		if ($('#searchStDateView').val() == '') {
			$("input[id=timeSlot]").each(function(){
				if ($(this).val() == "00") {
					$(this).prop("checked", true);
				} else {
					$(this).prop("checked", false);
				}
			});
			alert("조회기간을 선택해 주세요");
			return;
		}
		
		if ($(obj).prop("checked")) {
			if ($(obj).val() == "00") {
				tg = true;
			}
		}
		$("input[id=timeSlot]").each(function(){
			if ($(this).prop("checked")) {
				cnt++;
			}
		});

		$("input[id=timeSlot]").each(function(){
			if (tg == false) {
				if ($(this).val() == "00") {
					$(this).prop("checked", false);
				}
			}else {
				if ($(this).val() != "00") {
					$(this).prop("checked", false);
				}
			}
			if (cnt == 0) {
				$("input[name=timeSlot00]").prop("checked", true);
			}
		});
		
		$('#timeSlot:checked').each(function(index){
			timeStringArr[index] = timeString[$(this).val()];
		})
		
		$('#timeString').text(timeStringArr.toString());
		
		timeStringArr=null;
	}
	
	/*
	*	날짜 체크 함수
	*/
	function date_check()
	{
		if ($("#searchStDateView").val() != '') {
			if ($("#searchEdDateView").val() == '') {
				alert('조회기간을 확인해주세요.');
				return false;
			}
		} else {
			alert('조회기간을 확인해주세요.');
			return false;
		}
		return true;
	}
	
	function date_lpod(value)
	{
		if (String(value).length < 2) {
			return res = '0'+value;
		}
		else{
			return value;
		}
	}
	
	/*
	신청 목록 엑셀 내보내기
	*/
	function exportExcel(pageUrl){
	
		//동단위 세부 정보가 아닐때 동 정보 초기화
		if (! pageUrl.match(/(type\/addr3statInfoExcel.do|\/cctv\/instStat\/addr3statExcel.do)$/)) {
			$('#locationDong').val('');
		}
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
	
	/*
		오늘일자로 피커를 세팅한다.	 
	*/
	function setDatePickerToday(){
		var todayDate = dateToYYYYMMDD(new Date()); //new Date('2013-09-16');
		$("#searchStDateView").val(todayDate);
		//$("#searchStDate").val(todayDate.replace(/-/gi,''));
		//$("#searchEdDateView").datepicker('setStartDate', todayDate);
		$("#searchEdDateView").val(todayDate);
		//$("#searchEdDate").val(todayDate.replace(/-/gi,''));
		//$("#searchStDateView").datepicker('setEndDate', todayDate);
	}
	
	/*
		Date Formatter
	*/
	function dateToYYYYMMDD(date){
	    function pad(num) {
	        num = num + '';
	        return num.length < 2 ? '0' + num : num;
	    }
	    return date.getFullYear() + '-' + pad(date.getMonth()+1) + '-' + pad(date.getDate());
	}
</script>
	<!-- Content Area -->
	<div class="container" style="width: 100%;">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-3 col-xs-3 box_map_left" style="width: 250px; margin-left: 2%;" >
				<div class="lt">
					<form id="searchFrm" name="searchFrm" method="post">
					<input type="hidden" id="locationDong" name="locationDong">
					<p>조회기간</p>
					<input type="text" id="searchStDateView" name="searchStDateView"
						class="form-control" style="width: 128px;" readonly="readonly">
					<input type="hidden" id="searchStDate" name="searchStDate"
						class="form-control" style="width: 128px;" readonly="readonly">
					<button class="btn btn-default" type="button" onclick="toggleCalendar('st')">
						<img src="/resources/images/ico_calendar.png" width="18" height="18" alt=""/>
					</button>
					<input type="text" id="searchEdDateView" name="searchEdDateView"
						class="form-control" style="width: 128px;" readonly="readonly">
					<input type="hidden" id="searchEdDate" name="searchEdDate"
						class="form-control" style="width: 128px;" readonly="readonly">
					<button class="btn btn-default" type="button" onclick="toggleCalendar('ed')">
						<img src="/resources/images/ico_calendar.png" width="18" height="18" alt=""/>
					</button>
					<br>
					<ol>
						<div class="btn-group">
							<button type="button" id="timeSlotBtn" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" >
								시간대 <span class="caret" style="position: static; top: 0px; right: 0px;"></span>
							</button>
							<span class="label label-primary active" id=timeString style="position: static; margin-left: 5px;font-size:10px;">종일</span>
						  <ul class="dropdown-menu">
						    <li class="dropdown-header">시간대 선택</li>
						    <li role="separator" class="divider"></li>
						    <li><label style="margin-left: 20px;"><input type="checkbox" checked="checked" id="timeSlot" name="timeSlot00" value="00" onclick="javascript:selTimeSlot('00',this)"> 종일(00 ~ 24)</label></li>
						    <li><label style="margin-left: 20px;"><input type="checkbox" id="timeSlot" name="timeSlot07" value="07" onclick="javascript:selTimeSlot('07',this)"> 주간 (07 ~ 18)</label></li>
						    <li><label style="margin-left: 20px;"><input type="checkbox" id="timeSlot" name="timeSlot18" value="18" onclick="javascript:selTimeSlot('18',this)"> 초저녁 (18 ~ 22)</label></li>
						    <li><label style="margin-left: 20px;"><input type="checkbox" id="timeSlot" name="timeSlot22" value="22" onclick="javascript:selTimeSlot('22',this)"> 심야 (22 ~ 03)</label></li>
						    <li><label style="margin-left: 20px;"><input type="checkbox" id="timeSlot" name="timeSlot03" value="03" onclick="javascript:selTimeSlot('03',this)"> 새벽 (03 ~ 07)</label></li>
						  </ul>
						</div>
					</ol>
					<span>
						<button class="btn btn-default" type="button" style="height:58px; padding:0 8px" onclick="searchAllMapStat()">
							<img src="/resources/images/ico_search.png" width="32" height="32" alt=""/>
						</button>
					</span> 
					</form>
				</div>
				<!--div class="lt">
					<p>위험지수
						<div class="btn-group" data-toggle="buttons" style="position:absolute; right:5px; top:5px">
							<label class="btn btn-default">
								<input type="radio" name="options" id="option1" autocomplete="off">ON 
							</label>
							<label class="btn btn-default active">
								<input type="radio" name="options" id="option2" autocomplete="off" checked>OFF 
							</label>
						</div>
					</p>
					지역별 위험지수 표출기준 (Heat map)
					<!--그래프-->
					<!-- <div style="height:60px; margin-top:5px; background:#e9e9e9"></div> -->
				</div-->
				<div class="lt crimeTypeMarkerArea">
					<div id="crimeTypeDisableLayer"></div>
					<p>범죄유형
						<div class="btn-group" data-toggle="buttons" style="position:absolute; right:5px; top:5px">
							<label class="btn btn-default">
								<input type="radio" name="crimeTypeView" id="crimeTypeViewOn" autocomplete="off" value="crime">ON 
							</label>
							<label class="btn btn-default active">
								<input type="radio" name="crimeTypeView" id="crimeTypeViewOff" autocomplete="off" value="crime">OFF 
							</label>
						</div>
					</p>
					<label class="checkbox-inline">
						<input type="checkbox" id="crimeTypeChkAll" name="crimeTypeChkAll" checked="checked">전체 
					</label>
					<br>
					<div style="border-bottom:3px solid #fff; margin-bottom:5px; padding:3px 0 3px 0"></div>
					<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
						<c:choose>
							<c:when test="${(status.count mod 2) eq 0 }">
								<label class="checkbox-inline">
									<input type="checkbox" id="crimeTypeChkBox" name="crimeTypeChkBox" value="${list.codeKey}" checked="checked">
									<img src="/resources/images/${fn:replace(list.codeDesc, 'location_', '')}" width="28" height="28" alt=""/> ${list.codeVal}
								</label>
								<br>
							</c:when>
							<c:otherwise>
								<label class="checkbox-inline" style="width: 109px;">
									<input type="checkbox" id="crimeTypeChkBox" name="crimeTypeChkBox" value="${list.codeKey}" checked="checked">
									<img src="/resources/images/${fn:replace(list.codeDesc, 'location_', '')}" width="28" height="28" alt=""/> ${list.codeVal}
								</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div class="lt cctvTypeMarkerArea">
					<div id="cctvTypeDisableLayer"></div>
					<p>CCTV 설치목적
						<div class="btn-group" data-toggle="buttons" style="position:absolute; right:5px; top:5px">
							<label class="btn btn-default">
								<input type="radio" name="cctvTypeView" id="cctvTypeViewOn" autocomplete="off" value="cctv">ON 
							</label>
							<label class="btn btn-default active">
								<input type="radio" name="cctvTypeView" id="cctvTypeViewOff" autocomplete="off" value="cctv" checked>OFF 
							</label>
						</div>
					</p>
					<label class="checkbox-inline">
						<input type="checkbox" id="cctvTypeChkAll" name="cctvTypeChkAll" checked="checked"> 전체 
					</label>
					<br>
					<div style="border-bottom:3px solid #fff; margin-bottom:5px; padding:3px 0 3px 0"></div>
					<c:forEach var="list" items="${cctvTypeList}" varStatus="status">
						<c:choose>
							<c:when test="${(status.count mod 2) eq 0 }">
								<label class="checkbox-inline">
									<input type="checkbox" id="cctvTypeChkBox" name="cctvTypeChkBox" value="${list.codeKey}" checked="checked">
									<!-- <img src="/resources/images/marker/${fn:replace(list.codeVal, 'location_', '')}" width="28" height="28" alt=""/> ${list.codeDesc}  -->
									<img src="/resources/images/marker/${list.codeVal}" width="28" height="31" alt=""/> ${list.codeDesc}
								</label>
								<br>
							</c:when>
							<c:otherwise>
								<label class="checkbox-inline" style="width: 109px;">
									<input type="checkbox" id="cctvTypeChkBox" name="cctvTypeChkBox" value="${list.codeKey}" checked="checked">
									 <!-- <img src="/resources/images/marker/${fn:replace(list.codeVal, 'location_', '')}" width="28" height="28" alt=""/> ${list.codeDesc}  -->
									<img src="/resources/images/marker/${list.codeVal}" width="28" height="31" alt=""/> ${list.codeDesc}
								</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<!-- 
				<div class="lt">
					<p>미활용 CCTV
					<div class="btn-group" data-toggle="buttons" style="position:absolute; right:5px; top:5px">
						<label class="btn btn-default active">
							<input type="radio" name="options" id="option1" autocomplete="off" checked>ON 
						</label>
						<label class="btn btn-default">
							<input type="radio" name="options" id="option2" autocomplete="off">OFF 
						</label>
					</div>
					</p>
					<ul class="list-inline" style="margin-bottom:0">
						<li><img src="/resources/images/ico_location_sample.png" width="26" height="54" alt=""/></li>
						<li style="vertical-align:middle"> 미활용 CCTV에 해당하는 경우<br>마커 상단에 미활용 건수 표출</li>
					</ul>
				</div>
				 -->
			</div>
			
			<!--content start-->
			<!-- Map Area -->
			<div class="col-md-9 col-xs-8" style="width: 70%;">
				<div class="box_map" id="mapArea"></div>
				<!-- Nav tabs -->
				<div style="border:1px solid #e9e9e9; border-top:none">
					<ul class="nav nav-tabs r_none" role="tablist" style="border-bottom:none; margin-bottom:1px">
						<li role="presentation" class="active">
							<a href="#areaStatDiv" aria-controls="areaStatDiv" role="tab" data-toggle="tab" style="border-left:none" onclick="searchAreaStatList();">지역별위험지수</a>
						</li>
						<li role="presentation">
							<a href="#typeStatDiv" aria-controls="typeStatDiv" role="tab" data-toggle="tab" onclick="searchTypeStatList('dong');">유형별범죄현황</a>
						</li>
						<li role="presentation">
							<a href="#cctvInstDiv" aria-controls="cctvInstDiv" role="tab" data-toggle="tab" onclick="searchCctvInstStatList('dong');">목적별CCTV설치현황</a>
						</li>
						<!-- 
						<li role="presentation">
							<a href="#notUseCctvDiv" aria-controls="notUseCctvDiv" role="tab" data-toggle="tab">미활용CCTV</a>
						</li>
						 -->
					</ul>
					<div class="tab-content position_r"> 
						<!--map1 탭 시작-->
						<div role="tabpanel" class="tab-pane fade in active" id="areaStatDiv"></div>
						<!--map2 탭내용시작-->
						<div role="tabpanel" class="tab-pane fade" id="typeStatDiv"></div>
						<!--map3 탭내용시작-->
						<div role="tabpanel" class="tab-pane fade" id="cctvInstDiv"></div>
						<!--map4 탭내용시작-->
						<!-- <div role="tabpanel" class="tab-pane fade" id="notUseCctvDiv"></div> -->
					</div>
				</div>
			</div>
			<!--Nav tabs END--> 
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
