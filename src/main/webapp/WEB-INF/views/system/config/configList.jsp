<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		searchConfigList(1);	// 리스트 조회 호출
		
		$('#confirmModal').on('show.bs.modal', function (event) {
			var $this = $(this);
			$this.find('.modal-title').text("값수정 확인");
			$this.find('.pop_title').text($this.data("description")+" 값을 수정하시겠습니까?");
			$this.find('button.btn-primary').off().on('click',function(){
				$.ajax({
					type : 'post',
					url : '/system/config/modify.json',
					dataType: 'json',
					data : {
						"itemIndex" : $this.data('idx'),
						"itemDescription" : $this.data('description'),
						"itemValue" : $this.data('value')
					},	
					success : function(data) {
						//console.log(data.result);
						if (data.result == "SUCCESS"){
							alert($this.data('description') + "값 수정이 완료되었습니다.");
							searchConfigList(1);
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
		});
	});
	
	/**
	 *  설정 코드 리스트 조회
	 */
	function searchConfigList(pageNum){

		$("#currentPage").val(pageNum);
		
		var params = $("#searchFrm").serialize()
		
		$.ajax({
			type : 'post',
			url : '/system/config/list.json',
			dataType: 'html',
			data : params,	
			success : function(data) {
				$("#configList").html(data);
				history.pushState({listPage: data, pageNum: pageNum, area:'#configList'}, 'listPage'+ pageNum);
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
		 	// reject list search ajax
		 	searchConfigList(num);
		}); 
	}
	
	function modifyConfigInfo(description, value, idx){
		var description = description.children('input').val() 
		var value = value.children('input').val()
		
		$('#confirmModal').data('idx', idx);
		$('#confirmModal').data('description', description);
		$('#confirmModal').data('value', value);
		$('#confirmModal').modal('show');
	}
	
	function changeToModify(obj, idx){
		var $description = $(obj).parents('tr').find('.description');
		var $value = $(obj).parents('tr').find('.value');
		var $btn = $(obj).parents('tr').find('.btn');
		var description = $.trim($description.text()); 
		var value = $.trim($value.text());
		$description.html("<input type='text' class='form-control' style='width:90%' value='"+description+"'>");
		$value.html("<input type='text' class='form-control' style='width:90%' value='"+value+"'>");
		
		$btn.children("img").attr("src", "/resources/images/ico_OK.png")
		$btn.attr('onclick','').on('click',function(){
			modifyConfigInfo($description, $value, idx);
		});
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
	        	<blockquote><p><strong>환경설정</strong></p></blockquote>
	        	<form id="searchFrm" name="searchFrm" method="post">
	        		<input type="hidden" id="currentPage" name="currentPage" value="1" >
					<input type="hidden" id="rowSize" name="rowSize" value="10" >
	        	<div id="configList"></div>
	        	</form>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
