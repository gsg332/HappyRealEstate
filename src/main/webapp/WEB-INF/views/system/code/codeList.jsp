<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchCodeList(1);	// 리스트 조회 호출
		
		// 엔터 검색 처리
		$("#searchWord").enter({ 
			exec: function(){ 
				searchCodeList(1);
			} 
		});		
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			var type = $this.data('type');
			
			if(type == 'codeAdd'){
				$this.find('.modal-title').text("코드추가 확인");
				$this.find('.pop_title').text("해당 코드를 추가 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/code/insert.json',
						dataType: 'json',
						data : $this.data('params'),
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 코드를 추가 하였습니다.");
								searchCodeList(1);
							} else {
								alert("코드 추가를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'codeDel'){
				$this.find('.modal-title').text("코드삭제 확인");
				$this.find('.pop_title').text("해당 코드를 삭제 하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
					$.ajax({
						type : 'post',
						url : '/system/code/delete.json',
						dataType: 'json',
						data : {
							"codeGroup" : $this.data('codeGroup'),
							"codeKey" : $this.data('codeKey')
						},	
						success : function(data) {
							if (data.result == "SUCCESS"){
								alert("해당 코드를 삭제 하였습니다.");
								searchCodeList(1);
							} else {
								alert("코드 삭제를 실패하였습니다. \n 관리자에게 문의해 주세요.");
							}					
						},
						error : function(request, status, error) {
							errorModal(request);
						},
					});
				});
				$this.find('button.btn-danger').off().on('click',function(){
				});
			}else if(type == 'codeModify'){
				$this.find('.modal-title').text("코드수정 확인");
				$this.find('.pop_title').text("값을 수정하시겠습니까?");
				$this.find('button.btn-primary').off().on('click',function(){
						$.ajax({
							type : 'post',
							url : '/system/code/modify.json',
							dataType: 'json',
							data : {
								"codeGroup" : $this.data('codeGroup'),
								"codeKey" : $this.data('codeKey'),
								"codeVal" : $this.data('value'),
								"codeDesc" : $this.data('description')
							},	
							success : function(data) {
								//console.log(data.result);
								if (data.result == "SUCCESS"){
									alert($this.data('value') + " 값 수정이 완료되었습니다.");
									searchCodeList(1);
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
	function searchCodeList(pageNum){

		$("#currentPage").val(pageNum);
		var params = $("#searchCodeFrm").serialize();
		
		$.ajax({
			type : 'post',
			url : '/system/code/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#codeList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#codeList'}, 'listPage'+ pageNum);
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
		 	searchCodeList(num);
		}); 
	}

	function modifyCodeInfo(obj, codeGroup, codeKey){
		
		var value = $(obj).parent().parent().children("td")[1];
		var description = $(obj).parent().parent().children("td")[2];
		
		if ($(value).children("input").val().trim() == ''){
			alert("코드 값을 입력해 주세요.");
			return false;
		}
		
		$('#confirmModal').data('type', 'codeModify');
		$('#confirmModal').data('codeGroup', codeGroup);
		$('#confirmModal').data('codeKey', codeKey);
		$('#confirmModal').data('description', $(description).children("input").val());
		$('#confirmModal').data('value', $(value).children("input").val());
		$('#confirmModal').modal('show');
	}
	
	function changeToModify(obj, codeGroup, codeKey){
		
		var value = $(obj).parent().parent().children("td")[1];
		var description = $(obj).parent().parent().children("td")[2];
		var btn = $(obj).parent().parent().children("td")[3];
		
		var addInput = "<input type='text' class='form-control' style='width:90%' value='"+$(value).text()+"'>";
		$(value).html(addInput);
		
		addInput = "<input type='text' class='form-control' style='width:90%' value='"+$(description).text()+"'>";
		$(description).html(addInput);
		
		$(btn).children("button").children("img").prop("src", "/resources/images/ico_OK.png")
		$(btn).children("button").prop("onclick", "").click(function(){
			modifyCodeInfo(obj, codeGroup, codeKey);
		});
	}
	
	function deleteCodeInfo(codeGroup, codeKey){
		$('#confirmModal').data('type', 'codeDel');
		$('#confirmModal').data('codeGroup', codeGroup);
		$('#confirmModal').data('codeKey', codeKey);
		$('#confirmModal').modal('show');
	}	
	
	function insertCodeInfo(){
		
		var params = $("#insertCodeFrm").serialize();
		
		if ($("#codeVal").val().trim() == ''){
			alert("코드 값을 입력해 주세요.");
			return false;
		}		
		
		$('#confirmModal').data('params', params);
		$('#confirmModal').data('type', 'codeAdd');
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
	        	<blockquote><p><strong>코드관리</strong></p></blockquote>
	        	<form id="searchCodeFrm" name="searchCodeFrm" method="post" class="form-inline">
					
				<input type="hidden" id="currentPage" name="currentPage" value="1" >
				<input type="hidden" id="rowSize" name="rowSize" value="10" >	        	
	        	
	        	<div class="box_gray" style="margin:0px">
	        		<div style="line-height:30px; position:relative; margin:0 auto">
	        			<label for="textfield">코드종류&nbsp;&nbsp;</label>
						<select id="codeGroup" name="codeGroup" class="form-control" style="width:150px">
							<c:forEach var="list" items="${codeGroupList}" varStatus="status">
								<option value="${list.codeGroup}">${list.codeDesc}</option>
							</c:forEach>
						</select>	        			
	        			<button class="btn btn-default" type="button" onclick="javascript:searchCodeList(1)">
	        				<img src="/resources/images/ico_search1.png" width="16" height="16" alt=""/>
	        			</button>
	        		</div>
	        	</div>
	        	</form>
	        	<div id="codeList"></div>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
