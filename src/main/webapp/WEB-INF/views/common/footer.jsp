<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Include Footer -->
	<footer class="col-md-12" style="margin-top: 20px;">
	  <ul class="list-inline">
	    <li> 
	     <img src="/resources/images/<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status"><c:if test="${list.itemName eq 'LOGO_IMG' }">${list.itemValue}</c:if></c:forEach>" width="97" alt=""/>
	    </li>
	    <!-- <li>
	    	<a href="http://www.sd.go.kr" target="_blank" title="구청홈페이지"><font color="#4374D9">구청홈페이지</font></a> | 
	    	<a href="http://sd.go.kr/sd/main.do?op=mainSub&mCode=13J010000000" target="_blank" title="개인정보처리방침"><font color="#4374D9">개인정보처리방침</font></a> | 
	    	<a href="http://sd.go.kr/sd/main.do?op=mainSub&mCode=13J010030000" target="_blank" title="영상정보처리기기 관리방침"><font color="#4374D9">영상정보처리기기 관리방침</font></a>
	     </li>
	     <br> -->
	    <li>
	    	<c:forEach var="list" items="${CONFIG_LIST}" varStatus="status">
           		<c:if test="${list.itemName eq 'ADDRESS' }">
           			${list.itemValue}
           		</c:if>
			</c:forEach>	
		</ul>
	</footer>
	
	<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="smsModalLabel">접속 실패</h4>
				</div>
				<div class="modal-body">
					<div>
						<!-- <span style="font-size: 17px;">오류명 : </span><span id="errorName" style="color: #337ab7; font-size: 15px;"></span> -->
						<span style="font-size: 17px;">접속에 실패하였습니다. <br/>페이지에 다시 접속해 주세요.</span>
					</div>
					<br/>
					<!-- 
					<div>
						<span style="font-size: 17px;">내용 : </span><span id="errorMsg" style="color: #337ab7; font-size: 15px;"></span>
					</div>
					 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmModal" tabindex="1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="approveModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div class="pop_title" style="margin-bottom:10px"></div>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-primary" style="width: 100px;" data-dismiss="modal">확인</button>
					<button type="button" class="btn btn-danger" style="width: 100px;" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 연장신청 내역 Modal End -->
	
</body>
</html>