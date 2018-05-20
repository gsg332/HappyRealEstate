<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchIpList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchIpList(1);
			} 
		});
		
		// 검색 종류 전체 일 경우 input readonly
		$("#searchKind").change(function(){

			if ($("#searchKind option:selected").val() != ""){
				$("#searchWord").prop("readonly", false);
			} else {
				$("#searchWord").val("");
				$("#searchWord").prop("readonly", true);
			}
		});
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			if(type == 'ipAdd'){
				$this.find('.modal-title').text("IP추가 확인 ");
				$this.find('.pop_title').text("해당 IP를 추가 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/ip/insert.json',
						dataType: 'json',
						data : $this.data('params'),
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 IP를 추가 하였습니다.");
								searchIpList(1);
							} else {
								alert("IP 추가를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'ipDel'){
				$this.find('.modal-title').text("IP삭제 확인");
				$this.find('.pop_title').text("해당 IP를 삭제 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/ip/delete.json',
						dataType: 'json',
						data : {
							"no" : $this.data('no')
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 IP를 삭제 하였습니다.");
								searchIpList(1);
							} else {
								alert("IP 삭제를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});	
			}else if(type == 'ipModify'){
				$this.find('.modal-title').text("IP수정 확인");
				$this.find('.pop_title').text($this.data('title')+" IP를 수정하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/ip/modify.json',
						dataType: 'json',
						data : {
							"no" : $this.data('no'),
							"title" : $this.data('title'),
							"startIp" : $this.data('startIp'),
							"endIp" : $this.data('endIp')
						},	
						success : function(data) {
							//console.log(data.result);
							if (data.result == "SUCCESS"){
								alert($this.data('title') + " IP 수정이 완료되었습니다.");
								searchIpList(1);
							} else {
								alert("수정이 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					}); 
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});				
			}
		});
	});
	
	/**
	 *  반려 리스트 조회
	 */
	function searchIpList(pageNum){
		
		$("#currentPage").val(pageNum); 
		var params = $("#searchIpFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/system/ip/list.json',
			dataType: 'html',
			data : params, 			
			success : function(data) {
				$("#ipList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#ipList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/**
	 *  반려 리스트 페이징 처리
	 */
	function pagination(totalPage, currentPage){
		$('#page-selection').bootpag({
		    total: totalPage,
		    page: currentPage,
		    maxVisible: 5,
		    leaps: true,
		    firstLastUse: true,
		    first: '←',
		    last: '→',
		    wrapClass: 'pagination',
		    activeClass: 'active',
		    disabledClass: 'disabled',
		    nextClass: 'next',
		    prevClass: 'prev',
		    lastClass: 'last',
		    firstClass: 'first'
		}).on("page", function(event, num){
		 	// reject list search ajax
		 	searchIpList(num);
		}); 
	}

	function modifyIpInfo(obj, no){

		var title = $(obj).parent().parent().children("td")[1];
		var startIp = $(obj).parent().parent().children("td")[2];
		var endIp = $(obj).parent().parent().children("td")[4];	

		if ($(title).children("input").val().trim() == ''){
			alert("IP사용기관을 입력해 주세요.");
			return false;
		}
		if ($(startIp).children("input").val().trim() == ''){
			alert("시작IP를 입력해 주세요.");
			return false;
		}
		
		if(verifyIPAddr($(startIp).children("input").val().trim(), $(endIp).children("input").val().trim()) != true){
			alert("유효하지 않은 IP 입니다.\n범위로 등록할 경우 IP 클래스를 같게 지정해 주세요.\nex) 211.222.233.1 ~ 211.222.233.255");
			return false;
		}
		
		$('#confirmModal').data('no', no);
		$('#confirmModal').data('title', $(title).children("input").val().trim());
		$('#confirmModal').data('startIp', $(startIp).children("input").val().trim());
		$('#confirmModal').data('endIp', $(endIp).children("input").val().trim());
		$('#confirmModal').data('type', 'ipModify');
		$('#confirmModal').modal('show');
	}
	
	function changeToModify(obj, no){
		
		var title = $(obj).parent().parent().children("td")[1];
		var startIp = $(obj).parent().parent().children("td")[2];
		var endIp = $(obj).parent().parent().children("td")[4];	
		var btn = $(obj).parent().parent().children("td")[8];
		
		var addInput = "<input type='text' class='form-control' style='width:90%' value='"+$(title).text()+"'>";
		$(title).html(addInput);
		
		addInput = "<input type='text' class='form-control' style='width:90%' value='"+$(startIp).text()+"'>";
		$(startIp).html(addInput);
		addInput = "<input type='text' class='form-control' style='width:90%' value='"+$(endIp).text()+"'>";
		$(endIp).html(addInput);		
		
		$(btn).children("button").children("img").prop("src", "/resources/images/ico_OK.png")
		$(btn).children("button").prop("onclick", "").click(function(){
			modifyIpInfo(obj, no);
		});
	}

 	function deleteIPInfo(no){
 		$('#confirmModal').data('no', no);
 		$('#confirmModal').data('type', 'ipDel');
		$('#confirmModal').modal('show');
	}	 
	
	function insertIpInfo(){
		
		var params = $("#insertIpFrm").serialize();
		
		if ($("#title").val().trim() == ''){
			alert("IP사용기관을 입력해 주세요.");
			return false;
		}
		if ($("#startIp").val().trim() == ''){
			alert("시작IP를 입력해 주세요.");
			return false;
		}
		
		if(verifyIPAddr($("#startIp").val().trim(), $("#endIp").val().trim()) != true){
			alert("유효하지 않은 IP 입니다.\n범위로 등록할 경우 IP 클래스를 같게 지정해 주세요.\nex) 211.222.233.1 ~ 211.222.233.255");
			return false;
		}
		
		$('#confirmModal').data('params', params);
		$('#confirmModal').data('type', 'ipAdd');
		$('#confirmModal').modal('show');
	}
		
</script>
	<!-- Content Area -->
	<div class="container">
		<div class="row" style="margin-top:81px;">
			<!--left menu start-->
			<div class="col-md-2">
				<div class="slnb list-group">
					<div class="slnb_title1"></div>
				<c:forEach var="list" items="${SECOND_MENU_LIST}" varStatus="status">
					<sec:authorize  access="hasRole('INCON_ADMIN')">
				        <c:set var="isInconAdmin" value="true"/>
				    </sec:authorize>
				    <c:choose>
				    	<c:when test="${list.menuId != 507}">
							<c:if test="${list.pMenuId == P_MENU_ID}">
								<c:choose>
									<c:when test="${list.menuUrl == CURRENT_URL}">
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${isInconAdmin}">
								<c:if test="${list.pMenuId == P_MENU_ID}">
									<c:choose>
										<c:when test="${list.menuUrl == CURRENT_URL}">
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item active">${list.menuNm}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:moveMenu('${list.menuUrl}','${P_MENU_ID}')" class="list-group-item">${list.menuNm}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:if>
						</c:otherwise>	
				    </c:choose>
				</c:forEach>
				</div>
			</div>
			<!--content start-->
			<div class="col-md-10">
	        	<blockquote><p><strong>허용IP관리</strong></p></blockquote>
 	        	<form id="searchIpFrm" name="searchIpFrm" method="post" class="form-inline">					
				<input type="hidden" id="currentPage" name="currentPage" value="1" >
				<input type="hidden" id="rowSize" name="rowSize" value="10" >	        	
	        	</form>	         	
	        	<div id="ipList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
