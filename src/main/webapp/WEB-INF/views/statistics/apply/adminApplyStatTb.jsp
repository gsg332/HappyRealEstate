<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	$(document).ready(function(){
			
        //if('${allList[3]}' != '0')
        //{
        	var solutionRate = parseFloat('${allList[4] / allList[3] * 100}').toFixed(1);
        	var notSolutionRate = parseFloat('${allList[5] / allList[3] * 100}').toFixed(1);

        	var pieValue; 
        	//if('${allList[3]}' != '0' && '${allList[4]}' != '0' && '${allList[5]}' != '0'){
        		pieValue = [['해결활용',parseFloat(solutionRate)], ['미활용',parseFloat(notSolutionRate)]];	
        	//}else{
        		//pieValue = [['해결활용', 0], ['미활용', 0]];
        	//}

        	var contributeRate;
        	if('${allList[2]}' != '0' && '${allList[4]}' != '0'){
        		contributeRate = parseFloat('${allList[4] / allList[2] * 100}').toFixed(1);
        	}else{
        		contributeRate = 0;
        	}
        	
		    var plot = $.jqplot('adminPieChart', [pieValue], {
		    	title: '기여율 : ' + contributeRate + '%',
		        grid: {
		            drawBorder: false, 
		            drawGridlines: false,
		            background: '#ffffff',
		            shadow:false
		        },
		        axesDefaults: {
		             
		        },
		        seriesDefaults:{
		            renderer:$.jqplot.PieRenderer,
		            rendererOptions: {
		            	sliceMargin: 4,
		                showDataLabels: true
		            }
		        },
		        legend: {
		            show: true,
	 	            // rendererOptions: {
		            //    numberRows: 1
		            // }, 
		            location: 'e'
		        }
		    });
        //}
        //else
        //{
        	//$("#adminPieChart").append('데이터가 없습니다.');
        //}
	});	
</script>	
		<!-- 종합 현황 DIV Start -->
		<div id="synthesizeStat" class="panel panel-default" style="margin-top:10px">
			<div class="panel-body text_red">
				<strong>종합현황</strong>&nbsp;&nbsp;
				<c:choose>
					<c:when test="${stDate eq ''}"><span class="tooltip-inner">전체</span></c:when>
					<c:otherwise><span class="tooltip-inner">${stDate} ~ ${edDate}</span></c:otherwise>
				</c:choose>
			</div>
			<div class="panel-footer" style="background:url../../resources/images/th.gif; height:250px">
				<div id="adminPieChart" style="height:240px"></div>
			</div>			
			<table class="table table-striped table_b text-center">
	            <thead>
	              <tr>
	                <th colspan="4">구분</th>
	                <th>건수</th>
	                <th>전체비율</th>
	                <th>입력대비 기여율</th>
	                <th>결과대비 기여율</th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	                <td>전체신청</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>${allList[0]}</td>
	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td>0%</td></c:when>
	                	<c:otherwise><td><fmt:formatNumber value="${allList[0] / allList[0] * 100}" pattern="" />%</td></c:otherwise>
	                </c:choose>
	                <td></td>
	                <td></td>
	              </tr>
	              <tr>
	                <td class="gray1">&nbsp;</td>
	                <td>미입력</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>${allList[1]}</td>
   	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td>0%</td></c:when>
	                	<c:otherwise><td><fmt:formatNumber value="${allList[1] / allList[0] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>
	                <td></td>
	                <td></td>
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td class="bg-success">입력</td>
	                <td class="bg-success">&nbsp;</td>
	                <td class="bg-success">&nbsp;</td>
	                <td class="bg-success">${allList[2]}</td>
   	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td class="bg-success">0%</td></c:when>
	                	<c:otherwise><td class="bg-success"><fmt:formatNumber value="${allList[2] / allList[0] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>	 
   	                <c:choose>
	                	<c:when test="${allList[2] eq 0}"><td class="bg-success">0%</td></c:when>
	                	<c:otherwise><td class="bg-success"><fmt:formatNumber value="${allList[2] / allList[2] * 100}" pattern="" />%</td></c:otherwise>
	                </c:choose>	                               
	                <td class="bg-success"></td>
	              </tr>
	              <tr>
	                <td class="gray1">&nbsp;</td>
	                <td class="bg-success">&nbsp;</td>
	                <td class="bg-warning">해결사건</td>
	                <td class="bg-warning">&nbsp;</td>
	                <td class="bg-warning">${allList[3]}</td>
   	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td class="bg-warning">0%</td></c:when>
	                	<c:otherwise><td class="bg-warning"><fmt:formatNumber value="${allList[3] / allList[0] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>	  
   	                <c:choose>
	                	<c:when test="${allList[2] eq 0}"><td class="bg-warning">0%</td></c:when>
	                	<c:otherwise><td class="bg-warning"><fmt:formatNumber value="${allList[3] / allList[2] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>	
   	                <c:choose>
	                	<c:when test="${allList[3] eq 0}"><td class="bg-warning">0%</td></c:when>
	                	<c:otherwise><td class="bg-warning"><fmt:formatNumber value="${allList[3] / allList[3] * 100}" pattern="" />%</td></c:otherwise>
	                </c:choose>		                	                               
	              </tr>
	              <tr>
	                <td>&nbsp;</td>
	                <td class="bg-success">&nbsp;</td>
	                <td class="bg-warning">&nbsp;</td>
	                <td class="bg-info">활용</td>
	                <td class="bg-info">${allList[4]}</td>
   	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td class="bg-info">0%</td></c:when>
	                	<c:otherwise><td class="bg-info"><fmt:formatNumber value="${allList[4] / allList[0] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>	 	
   	                <c:choose>
	                	<c:when test="${allList[2] eq 0}"><td class="bg-info">0%</td></c:when>
	                	<c:otherwise><td class="bg-info"><fmt:formatNumber value="${allList[4] / allList[2] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>
   	                <c:choose>
	                	<c:when test="${allList[3] eq 0}"><td class="bg-info">0%</td></c:when>
	                	<c:otherwise><td class="bg-info"><fmt:formatNumber value="${allList[4] / allList[3] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>		                		                                
	              </tr>
	              <tr>
	                <td class="gray1">&nbsp;</td>
	                <td class="bg-success">&nbsp;</td>
	                <td class="bg-warning">&nbsp;</td>
	                <td>미활용</td>
	                <td>${allList[5]}</td>
   	                <c:choose>
	                	<c:when test="${allList[0] eq 0}"><td>0%</td></c:when>
	                	<c:otherwise><td><fmt:formatNumber value="${allList[5] / allList[0] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>	
   	                <c:choose>
	                	<c:when test="${allList[2] eq 0}"><td>0%</td></c:when>
	                	<c:otherwise><td><fmt:formatNumber value="${allList[5] / allList[2] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>
   	                <c:choose>
	                	<c:when test="${allList[3] eq 0}"><td>0%</td></c:when>
	                	<c:otherwise><td><fmt:formatNumber value="${allList[5] / allList[3] * 100}" pattern=".0" />%</td></c:otherwise>
	                </c:choose>		                		                	                
	              </tr>
	            </tbody>
			</table>
		</div>
		<!-- 종합 현황 DIV End -->
		<!-- 기여율 DIV Start -->
		<div id="contributionRatioStat" class="panel panel-default" style="margin-top:10px">
			<div class="panel-body text_red"><strong>기여율(해결사건)</strong></div>
			<div class="panel-body">
				<table class="table table-striped table_b text-center">
					<thead>
						<tr>
							<th>구분</th>
							<th>전체신청</th>
							<th>해결사건</th>
							<th>CCTV활용</th>
							<th>CCTV미활용</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${solveList}" varStatus="status">
						<tr>
							<td>${list.yearCnt}</td>
							<td>${list.totalCnt}</td>
							<td>${list.solveCnt}</td>
							<td>${list.useSolveCnt}</td>
							<td>${list.notUseSolveCnt}</td>
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
		<!-- 기여율 DIV End -->
		<!-- 미활용 사유 DIV Start -->
		<div id="notUseReasonStat" class="panel panel-default" style="margin-top:10px">
			<div class="panel-body text_red"><strong>미활용 사유</strong></div>
			<div class="panel-body">
				<table class="table table-striped table_b text-center">
					<thead>
						<tr>
							<th width='30%'>구분</th>
							<th>전체</th>
							<c:forEach var="list" items="${yearList}" varStatus="status">
							<th>${list}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${reasonList}" varStatus="status">
						<tr>
							<td>${list.codeVal}</td>
							<td>
								<c:choose>
									<c:when test="${list.codeVal == '기타'}">
										<a href="#" onclick="searchDetailEtcReasonList('')"><u>${list.totalCnt}</u></a>
									</c:when>
									<c:otherwise>
										${list.totalCnt}
									</c:otherwise>
								</c:choose>
							</td>
							
							<c:forEach var="yList" items="${yearList}" varStatus="yStatus">
								<c:set var="yearName" value="year_${yStatus.index}" />
								<td>
									<c:choose>
										<c:when test="${list.codeVal == '기타'}">
											<a href="#" onclick="searchDetailEtcReasonList('${yList}')"><u>${list[yearName]}</u></a>
										</c:when>
										<c:otherwise>
											${list[yearName]}
										</c:otherwise>
									</c:choose>
								</td>
							</c:forEach>										
						</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
		<!-- 미활용 사유 DIV End -->


