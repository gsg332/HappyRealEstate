<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#addBtn').on('click',function(){

			var uReId
			var uRegionId;
			
			var type;
			if(!$('#realEstate').hasClass('hide') && $('#realEstate').val()){
				type = 'APT'; //아파트
				var reValue = $('#realEstate').val().split('|');
				uReId = reValue[0];
				uRegionId = reValue[1];
			}else if(!$('#ri').hasClass('hide') && $('#ri').val()){
				type = 'RI';
				uRegionId = $('#ri').val();
			}else if(!$('#emd').hasClass('hide') && $('#emd').val()){
				type = 'EMD';
				uRegionId = $('#emd').val();
			}else if(!$('#sgg').hasClass('hide') && $('#sgg').val()){
				type = 'SGG';
				uRegionId = $('#sgg').val();
			}else if(!$('#sido').hasClass('hide') && $('#sido').val()){
				type = 'SIDO';
				uRegionId = $('#sido').val();
			}
			
			$.ajax({
				type : 'post',
				url : '/interest/add.json',
				dataType: 'json',
				data : {uRegionId:uRegionId, uReId:uReId, type:type},
				success : function(data){
					
					if(data.result == '1'){
						getInterestList();
					}
					
					/* if(data.apartList.length > 0){
						$('#realEstate').removeClass('hide');
						$('#realEstate').children().remove();
						$("#realEstate").append("<option value=''>시/군/구</option>");
						
						for (var i=0; i<data.apartList.length; i++){
							$("#realEstate").append("<option value='"+data.apartList[i].uReId+"'>"+data.apartList[i].reName+"</option>");
						}
					}else{
						$('#realEstate').addClass('hide');					
					} */
				},
				error : function(request, status, error) {
					errorModal(request);
				},
			});
		});
		
		$('#sido,#sgg,#emd,#ri').change(function(){
			var reqType = $(this).attr('id');
			if($(this).val() == ''){ //빈값 선택
				switch (reqType) {
				case 'sido': 
					$('#sgg').addClass('hide');
					$('#emd').addClass('hide');
					$('#ri').addClass('hide');
					$('#realEstate').addClass('hide');
					break;
				case 'sgg':
					$('#emd').addClass('hide');
					$('#ri').addClass('hide');
					$('#realEstate').addClass('hide');
					break;
				case 'emd':
					$('#ri').addClass('hide');
					$('#realEstate').addClass('hide');
					break;
				case 'ri':
					$('#realEstate').addClass('hide');
					break;
				default:
					break;
				}	
			}else{
				getRegionList($(this).attr('id'));				
			}
		});
		
		getRegionList(); // 광역시/도  목록 가져오기.
		
		getInterestList();
		
	});
	
	function getRegionList(reqType){
		 
		var pURegionId = $('#'+reqType).val();
		
		$.ajax({
			type : 'post',
			url : '/region/list.json',
			dataType: 'json',
			data : {pURegionId:pURegionId},
			success : function(data){
				if(data.regionList.length > 0){
					for (var i=0; i<data.regionList.length; i++){
						switch(reqType){
						case 'sido' : // 시군구 목록
							if(i==0){
								$('#sgg').removeClass('hide');
								$('#sgg').children().remove();
								$("#sgg").append("<option value=''>시/군/구</option>");
								$('#emd').addClass('hide');
								$('#emd').children().remove();
								$('#ri').addClass('hide');
								$('#ri').children().remove();
								$('#realEstate').addClass('hide');
								$('#realEstate').children().remove();
							}
							$("#sgg").append("<option value='"+data.regionList[i].uRegionId+"'>"+data.regionList[i].sgg+"</option>");
							break;
						case 'sgg' : // 읍면동 목록 
							if(i==0){
								$('#emd').removeClass('hide');
								$('#emd').children().remove();
								$("#emd").append("<option value=''>광역시/도</option>");
								$('#ri').addClass('hide');
								$('#ri').children().remove();
								$('#realEstate').addClass('hide');
								$('#realEstate').children().remove();
							}
							$("#emd").append("<option value='"+data.regionList[i].uRegionId+"'>"+data.regionList[i].emd+"</option>");
							break;
						case 'emd' : // 리목록 또는 부동산 목록 요청
							if(i==0){
								$('#ri').removeClass('hide');
								$('#ri').children().remove();
								$("#ri").append("<option value=''>리</option>");
								$('#realEstate').addClass('hide');
								$('#realEstate').children().remove();
							}
							$("#ri").append("<option value='"+data.regionList[i].uRegionId+"'>"+data.regionList[i].ri+"</option>");
							break;
						//case 'ri' : // 부동산 목록 요청
						//	break;
						default : //reqType이 엇을 경우
							$("#sido").append("<option value='"+data.regionList[i].uRegionId+"'>"+data.regionList[i].sido+"</option>");
							break;
						}
					}
				}else{
					switch(reqType){
					case 'sido' : // 시군구 목록
						$('#sgg').addClass('hide');
						$('#emd').addClass('hide');
						$('#ri').addClass('hide');
						$('#realEstate').addClass('hide');
						break;
					case 'sgg' : // 읍면동 목록 
						$('#emd').addClass('hide');
						$('#ri').addClass('hide');
						$('#realEstate').addClass('hide');
						break;
					case 'emd' : // 리목록 또는 부동산 목록 요청
						$('#ri').addClass('hide');
						$('#realEstate').addClass('hide');
						break;
					case 'ri' : // 부동산 목록 요청
						$('#realEstate').addClass('hide');
						break;
					default : //reqType이 엇을 경우
						break;
					}
					getRealEstateList(pURegionId);
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});   
	}
	
	function getRealEstateList(pURegionId){
		$.ajax({
			type : 'post',
			url : '/realEstate/apartList.json',
			dataType: 'json',
			data : {pURegionId:pURegionId},
			success : function(data){
				if(data.apartList.length > 0){
					$('#realEstate').removeClass('hide');
					$('#realEstate').children().remove();
					$("#realEstate").append("<option value=''>부동산</option>");
					for (var i=0; i<data.apartList.length; i++){
						$("#realEstate").append("<option value='"+data.apartList[i].uReId+ "|" + data.apartList[i].pURegionId + "'>"+data.apartList[i].reName+"</option>");
					}
				}else{
					$('#realEstate').addClass('hide');					
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});
	}
	
	function showDelBtn(isShow, elem){
		
		console.log('숨김:', isShow);
		
		if(isShow){
			
			if(parseInt(elem.parent().css('margin-left')) >= parseInt(elem.parent().next().css('margin-left')) || elem.parent().next().index() == -1){
				elem.parent().find('span.delBtn').removeClass('hide');
			}
			/* 
			$('#interestListArea').children().each(function(i, e){
				if(parseInt($(this).css('margin-left')) >= parseInt($(this).next().css('margin-left'))){
					$(this).children().eq(1).removeClass('hide');
				}					
			});
			
			
			elem.parent().find('span.delBtn').removeClass('hide'); */	
		}else{
			elem.parent().find('span.delBtn').addClass('hide');	
		}
	}
	
	function deleteInterest(uInterestId, type, uRegionId, uReId){
		
		console.log('type :', type);
		console.log('uRegionId :', uRegionId);
		console.log('uReId :', uReId);
		
		$.ajax({
			type : 'post',
			url : '/interest/delete.json',
			dataType: 'json',
			data : {uInterestId:uInterestId, type:type, uRegionId:uRegionId, uReId:uReId},
			success : function(data){
				if(data.subInterestCnt > 0){
					alert('하위 목록을 먼저 삭제해야 삭제가능 합니다.');
				}else if(data.result == '1'){
					getInterestList();
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});
	}
	
	
	function getInterestList(){
		
		
		$.ajax({
			type : 'post',
			url : '/interest/list.json',
			dataType: 'json',
			cache: false,
			data : {},
			success : function(data){
				$('#interestListArea').children().remove(); //모든 목록 지우기.
				var tag;
				for(var i=0; i<data.interestList.length; i++){
					var item = data.interestList[i];
					var isSidoDuplicate = false;
					var isSggDuplicate = false;
					var isEmdDuplicate = false;
					var isRiDuplicate = false;
				
					if($('.sido').length > 0){
						$('.sido').each(function(index, elem){
							if($(this).text() == item.sido){
								isSidoDuplicate = true;
							}
						});	
					}
					if($('.sgg').length > 0){
						$('.sgg').each(function(index, elem){
							if($(this).text() == item.sgg){
								isSggDuplicate = true;
							}
						});	
					}
					if($('.emd').length > 0){
						$('.emd').each(function(index, elem){
							if($(this).text() == item.emd){
								isEmdDuplicate = true;
							}
						});	
					}if($('.ri').length > 0){
						$('.ri').each(function(index, elem){
							if($(this).text() == item.ri){
								isRiDuplicate = true;
							}
						});	
					}
					
					if(!isSidoDuplicate && item.sido){
						tag = $('<div style="margin-left:0px"></div>');
						tag.append($('<span style="cursor:pointer;' + (item.type=='SIDO'?'color:#ff00aa;font-weight: bold;':'') + '" class="sido">' + item.sido + '</span><span class="delBtn hide" onclick="deleteInterest(' + item.uInterestId + ',\'SIDO\',\'' + item.uRegionId + '\')">삭제</span>')
							.mouseenter(function(){
								console.log('왜 안 나와1');
								showDelBtn(true, $(this));
							}).mouseleave(function(){
								console.log('왜 안 나와1');
								showDelBtn(false, $(this));
							}).click(function(){
								getUrgentList($(this).data('sido'), null, null, null, null);
							}).data({sido:item.sido,sgg:item.sgg,emd:item.emd,ri:item.ri,reName:item.reName})
						);
						$('#interestListArea').append(tag);	
					}
					if(!isSggDuplicate && item.sgg){
						tag = $('<div style="margin-left:15px"></div>')
						tag.append($('<span style="cursor:pointer;' + (item.type=='SGG'?'color:#ff00aa;font-weight: bold;':'') + '" class="sgg">' + item.sgg + '</span><span class="delBtn hide" onclick="deleteInterest(' + item.uInterestId + ',\'SGG\',\'' + item.uRegionId + '\')">삭제</span>')
							.mouseenter(function(){
								console.log('왜 안 나와2');
								showDelBtn(true, $(this));
							}).mouseleave(function(){
								console.log('왜 안 나와2');
								showDelBtn(false, $(this));
							}).click(function(){
								getUrgentList($(this).data('sido'), $(this).data('sgg'), null, null, null);
							}).data({sido:item.sido,sgg:item.sgg,emd:item.emd,ri:item.ri,reName:item.reName})
						);
						$('#interestListArea').append(tag);	
					}
					if(!isEmdDuplicate && item.emd){
						tag = $('<div style="margin-left:30px"></div>')
						tag.append($('<span style="cursor:pointer;' + (item.type=='EMD'?'color:#ff00aa;font-weight: bold;':'') + '" class="emd">' + item.emd + '</span><span class="delBtn hide" onclick="deleteInterest(' + item.uInterestId + ',\'EMD\',\'' + item.uRegionId + '\')">삭제</span>')
							.mouseenter(function(){
								console.log('왜 안 나와3');
								showDelBtn(true, $(this));
							}).mouseleave(function(){
								console.log('왜 안 나와3');
								showDelBtn(false, $(this));
							}).click(function(){
								getUrgentList($(this).data('sido'), $(this).data('sgg'), $(this).data('emd'), null, null);
							}).data({sido:item.sido,sgg:item.sgg,emd:item.emd,ri:item.ri,reName:item.reName})
						);
						$('#interestListArea').append(tag);	
					}
					if(!isRiDuplicate && item.ri){
						tag = $('<div style="margin-left:45px"></div>')
						tag.append($('<span style="cursor:pointer;' + (item.type=='RI'?'color:#ff00aa;font-weight: bold;':'') + '"  class="ri">' + item.ri + '</span><span class="delBtn hide" onclick="deleteInterest(' + item.uInterestId + ',\'RI\',\'' + item.uRegionId + '\')">삭제</span>')
							.mouseenter(function(){
								console.log('왜 안 나와4');
								showDelBtn(true, $(this));
							}).mouseleave(function(){
								console.log('왜 안 나와4');
								showDelBtn(false, $(this));
							}).click(function(){
								getUrgentList($(this).data('sido'), $(this).data('sgg'), $(this).data('emd'), $(this).data('ri'), null);
							}).data({sido:item.sido,sgg:item.sgg,emd:item.emd,ri:item.ri,reName:item.reName})
						);
						$('#interestListArea').append(tag);	
					}
					if(item.reName && item.reName){
						if(item.ri){
							tag = $('<div style="margin-left:60px"></div>');
						}else{
							tag = $('<div style="margin-left:45px"></div>');
						}
						tag.append($('<span style="cursor:pointer;' + (item.type=='APT'?'color:#ff00aa;font-weight: bold;':'') + '" class="realRestate">' + item.reName + '</span><span class="delBtn hide" onClick="deleteInterest(' + item.uInterestId + ',\'APT\',\'\',\'' + item.uReId + '\')">삭제</span>')
							.mouseenter(function(){
								console.log('왜 안 나와5');
								showDelBtn(true, $(this));
							}).mouseleave(function(){
								console.log('왜 안 나와5');
								showDelBtn(false, $(this));
							}).click(function(){
								getUrgentList($(this).data('sido'), $(this).data('sgg'), $(this).data('emd'), $(this).data('ri'), $(this).data('reName'));
							}).data({sido:item.sido,sgg:item.sgg,emd:item.emd,ri:item.ri,reName:item.reName})
						);
						$('#interestListArea').append(tag);
					}
				}
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});
		
		
		
		
		
		
		
		
		//console.log(parseInt('${fn:length(interestList)}'));

		/*
		var item;
		

		
		<c:forEach var="item" items="${interestList}" varStatus="status">

		
			var isSidoDuplicate = false;
			var isSggDuplicate = false;
			var isEmdDuplicate = false;
			var isRiDuplicate = false;
		
			if($('.sido').length > 0){
				$('.sido').each(function(i, e){
					console.log($(this).text() + ', ' + '${item.sido}');
					console.log($(this).text() != '${item.sido}');
					if($(this).text() == '${item.sido}'){
						isSidoDuplicate = true;
					}
				});	
			}
			
			if($('.sgg').length > 0){
				$('.sgg').each(function(i, e){
					console.log($(this).text() + ', ' + '${item.sgg}');
					console.log($(this).text() != '${item.sgg}');
					if($(this).text() == '${item.sgg}'){
						isSggDuplicate = true;
					}
				});	
			}
			if($('.emd').length > 0){
				$('.emd').each(function(i, e){
					console.log($(this).text() + ', ' + '${item.emd}');
					console.log($(this).text() != '${item.emd}');
					if($(this).text() == '${item.emd}'){
						isEmdDuplicate = true;
					}
				});	
			}if($('.ri').length > 0){
				$('.ri').each(function(i, e){
					console.log($(this).text() + ', ' + '${item.ri}');
					console.log($(this).text() != '${item.ri}');
					if($(this).text() == '${item.ri}'){
						isRiDuplicate = true;
					}
				});	
			}
			
			if(!isSidoDuplicate){
				item = '<div class="sido" style="margin-left:0px">${item.sido}</div>';
				$('#interestListArea').append(item);	
			}
			
			if(!isSggDuplicate){
				item = '${item.sgg}' ? '<div class="sgg" style="margin-left:15px">${item.sgg}</div>' : '';
				$('#interestListArea').append(item);	
			}
			
			if(!isEmdDuplicate){
				item = '${item.emd}' ? '<div class="emd" style="margin-left:30px">${item.emd}</div>' : '';
				$('#interestListArea').append(item);	
			}
			
			if(!isRiDuplicate){
				item = '${item.ri}' ? '<div class="ri" style="margin-left:45px">${item.ri}</div>' : '';
				$('#interestListArea').append(item);	
			}
			
			
			if('${item.reName}'){
				
				if('${item.ri}'){
					item = '<div class="realRestate" style="margin-left:60px">${item.reName}</div>';
				}else{
					item = '<div class="realRestate" style="margin-left:45px">${item.reName}</div>';
				}
				$('#interestListArea').append(item);
			}
			
			
			
		
		</c:forEach>	 */	
		
		
			
		
			
		/* 
			console.log('${item.uRegionId}');
			item = $('<span/>',{}).text('${item.uRegionId}');
			$('#interestListArea').append(item);
			
			item = $('<span/>',{}).text('${item.uRegionId}');
			<div style="margin-left:0px">${item.sido}</div>
			
			
			$('#interestListArea').append('<br/>');
		 */	
			
	
		
		/* 
		switch('${item.type}'){
		case 'APT' :
			console.log('APT');
			
			 item = $('<div>${item.uRegionId}</div>\
					<div style="margin-left:0px">${item.sido}</div>'); 
			break;
		case 'RI' :
			console.log('RI');
			break;
		case 'EMD' :
			console.log('EMD');
			break;
		case 'SGG' :
			console.log('SGG');
			break;
		case 'SIDO' :
			console.log('SIDO');
			break;
		}
		 */
	}
	
	
	function getUrgentList(sido, sgg, emd, ri, reName){
		$.ajax({
			type : 'post',
			url : '/urgentSale/list.json',
			dataType: 'html',
			data : {sido:sido, sgg:sgg, emd:emd, ri:ri, reName:reName},
			success : function(data){
				$('#urgentListArea').html(data);
			},
			error : function(request, status, error) {
				errorModal(request);
			},
		});
	}
</script>
	<!-- Content Area -->
	<div class="container">
		<div style="margin-top:400px;">
			<!-- <input type="hidden" id="selectedId" name="selectedId"/> -->
			<label for="textfield">관심지역 선택&nbsp;</label>
			<br/>
			<select id="sido" name="sido" class="form-control" style="width:117px">
				<option value=''>광역시/도</option>
				<c:forEach var="item" items="${regionList}" varStatus="status">
					<option value="${item.uRegionId}">${item.sido}</option>	
				</c:forEach>
			</select>
			<select id="sgg" name="sgg" class="form-control hide" style="width:117px">
			</select>
			<select id="emd" name="emd" class="form-control hide" style="width:117px">
			</select>
			<select id="ri" name="ri" class="form-control hide" style="width:117px">
			</select>
			<select id="realEstate" name="realEstate" class="form-control hide" style="width:117px">
			</select>
			<button id="addBtn" class="btn" type="button">추가</button>
		</div>
		
		
		<div id="interestListArea" class="interestListArea" style="display:inline-block; width: 300px; float:left;">
			<%-- <c:forEach var="item" items="${interestList}" varStatus="status">
				<span>${item.uRegionId}|${item.uReId}</span>
				<c:choose>
					<c:when test="${item.type == 'APT'}">
						<div style="margin-left:0px">${item.sido}</div>
						<div style="margin-left:15px">${item.sgg}</div>
						<div style="margin-left:30px">${item.emd}</div>
						<c:choose>
							<c:when test="${not empty item.ri}">
								<div style="margin-left:45px">${item.ri}</div>
								<div style="margin-left:60px">${item.reName}</div>
							</c:when>
							<c:otherwise>
								<div style="margin-left:45px">${item.reName}</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${item.type == 'RI'}">
						<div style="margin-left:0px">${item.sido}</div>
						<div style="margin-left:15px">${item.sgg}</div>
						<div style="margin-left:30px">${item.emd}</div>
						<c:if test="${not empty item.ri}">
							<div style="margin-left:45px">${item.ri}</div>
						</c:if>
					</c:when>
					<c:when test="${item.type == 'EMD'}">
						<div style="margin-left:0px">${item.sido}</div>
						<div style="margin-left:15px">${item.sgg}</div>
						<div style="margin-left:30px">${item.emd}</div>
					</c:when>
					<c:when test="${item.type == 'SGG'}">
						<div style="margin-left:0px">${item.sido}</div>
						<div style="margin-left:15px">${item.sgg}</div>
					</c:when>
					<c:when test="${item.type == 'SIDO'}">
						<div style="margin-left:0px">${item.sido}</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<br/>
			</c:forEach> --%>
		</div>
		
		<div id="urgentListArea" class="urgentListArea" style="display:inline-block; width: 800px;">
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
