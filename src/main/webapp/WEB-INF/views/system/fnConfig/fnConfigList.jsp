<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchFnConfigList(1);	// 리스트 조회 호출
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			
			if(type == 'fnConfigAdd'){
				$this.find('.modal-title').text("기능설정 추가 확인");
				$this.find('.pop_title').text("해당 기능설정을 추가 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/fnConfig/insert.json',
						dataType: 'json',
						data : $this.data('params'),
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 기능설정을 추가 하였습니다.");
								searchFnConfigList(1);
							} else {
								alert("기능설정 추가를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'fnConfigDel'){
				$this.find('.modal-title').text("기능설정 삭제 확인");
				$this.find('.pop_title').text("해당 기능설정을 삭제 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/fnConfig/delete.json',
						dataType: 'json',
						data : {
							"itemIndex" : $this.data('itemIndex')
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 기능설정을 삭제 하였습니다.");
								searchFnConfigList(1);
							} else {
								alert("기능설정 삭제를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'fnConfigModify'){
				$this.find('.modal-title').text("기능설정 수정 확인");
				$this.find('.pop_title').text("해당 기능설정을 수정하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/fnConfig/modify.json',
						dataType: 'json',
						data : {
							"itemIndex" : $this.data('itemIndex'),
							"itemDescription" : $this.data('itemDescription'),
							"itemValue" : $this.data('itemValue'),
							"itemCode" : $this.data('itemCode'),
						},	
						success : function(data) {
							//console.log(data.result);
							if (data.result == "SUCCESS"){
								alert("기능설정 수정이 완료되었습니다.");
								searchFnConfigList(1);
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
	 *  설정 코드 리스트 조회
	 */
	function searchFnConfigList(pageNum){
		$("#currentPage").val(pageNum);
		
		var params = $("#searchFrm").serialize()
		
		$.ajax({
			type : 'post',
			url : '/system/fnConfig/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#fnConfigList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#fnConfigList'}, 'listPage'+ pageNum);
			},
			error : function(request, status, error) {
				errorModal(request);
			}
		});   
	}
	
	/**
	 *  설정 코드 리스트 페이징 처리
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
		 	searchFnConfigList(num);
		}); 
	}
	
	function modifyFnConfigInfo(description, value, code, itemIndex){
		var description = description.children('input').val(); 
		var value = value.children('input').val();
		var code = code.children('input').val();
		
		$('#confirmModal').data('itemIndex', itemIndex);
		$('#confirmModal').data('itemDescription', description);
		$('#confirmModal').data('itemValue', value);
		$('#confirmModal').data('itemCode', code);
		$('#confirmModal').data('type', 'fnConfigModify');
		$('#confirmModal').modal('show');
	}
	
	function changeToModify(obj, itemIndex){
		var $description = $(obj).parent('td').parent('tr').find('.description');
		var $value = $(obj).parent('td').parent('tr').find('.value');
		var $code = $(obj).parent('td').parent('tr').find('.code');
		var $btn = $(obj).parent('td').parent('tr').find('.btn').eq(0);
		var description = $.trim($description.text()); 
		var value = $.trim($value.text());
		var code = $.trim($code.text());
		$description.html("<input type='text' class='form-control' style='width:90%' value='"+description+"'>");
		$value.html("<input type='text' class='form-control' style='width:90%' value='"+value+"'>");
		$code.html("<input type='text' class='form-control' style='width:90%' value='"+code+"'>");
		
		$btn.children("img").attr("src", "/resources/images/ico_OK.png")
		$btn.attr('onclick','').on('click',function(){
			modifyFnConfigInfo($description, $value, $code, itemIndex);
		});
	}
	
	function deleteFnConfigInfo(itemIndex){
		$('#confirmModal').data('type', 'fnConfigDel');
		$('#confirmModal').data('itemIndex', itemIndex);
		$('#confirmModal').modal('show');
	}
	
	function insertFnConfigInfo(){
		
		var params = $("#insertFnConfigFrm").serialize();
		
		if ($("#itemDescription").val().trim() == ''){
			alert("셜명을 입력해 주세요.");
			$("#itemDescription").focus();
			return false;
		}
		
		if ($("#itemValue").val().trim() == ''){
			alert("값을 입력해 주세요.");
			$("#itemValue").focus();
			return false;
		}
		
		if ($("#itemCode").val().trim() == ''){
			alert("Map Key를 입력해 주세요.");
			$("#itemCode").focus();
			return false;
		}
		
		$('#confirmModal').data('params', params);
		$('#confirmModal').data('type', 'fnConfigAdd');
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
	        	<blockquote><p><strong>기능설정</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post">
	        		<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
	        	<div id="fnConfigList"></div>
	        	</form>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
