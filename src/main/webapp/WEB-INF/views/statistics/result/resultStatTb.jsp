<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	$(document).ready(function(){
		
		resultApplyChart();
		resultPositionChart();
		resultDepartChart();
		resultUserChart();
		resultApprTimeChart();
		resultPlayChart();
		resultVolumeChart();
		resultTimeChart();
	});
	
	function resultApplyChart(){
		// 종합 현황 stacked bar chart
		var yearCnt = [];
		var totalCnt = [];	
		var waitingCnt = [];
		var procCnt = [];
		var allowCnt = [];
		
		//console.log("${resultApplyList[0].totalCnt}");
		if("${resultApplyList[0].totalCnt}" && "${resultApplyList[0].totalCnt}" != '0') 
		{
	 		<c:forEach var="list" items="${resultApplyList}" varStatus="status" begin="1">
				yearCnt.push("${list.yearCnt}");
				totalCnt.push(eval("${list.totalCnt}"));
				waitingCnt.push(eval("${list.waitingCnt}"));
				procCnt.push(eval("${list.procCnt}"));
				allowCnt.push(eval("${list.allowCnt}"));
			</c:forEach>	

			 $.jqplot('resultApplyChart', [waitingCnt, procCnt, allowCnt], {
		        stackSeries: true,
		        seriesDefaults:{
		            renderer:$.jqplot.BarRenderer,
		            rendererOptions: {
		            	barMargin: 25,
		            	fillToZero: true
		            },
		    		pointLabels: { show: true }
		        },
		        series:[
		                {label:'대기'},
		                {label:'처리중'},
		                {label:'승인'}
		            ],		        
	            grid: {
	                background: '#ffffff'
	                ,drawGridlines: true
	                ,drawBorder: false
	                ,shadow: true
	                ,borderColor: '#999999'
	                ,borderWidth: 1
	            },		            
		        axes:{
		            xaxis:{
		                renderer: $.jqplot.CategoryAxisRenderer,
		                ticks : yearCnt
		            },
		        	yaxis:{
		        		tickOptions: { formatString: '%d' }
		        	}
		        },
		        legend: {
		            show: true,
		            location: 'e',
		            placement: 'outsideGrid'
		          }              
		    });	 
		}		
	}
	
	function resultPositionChart(){
		// 소속별 신청현황 pie chart
		//var position = [];
		var totalCnt = [];	

		//console.log("${resultApplyList[0].totalCnt}");
		if("${resultPositionList[0].totalCnt}" != 0) 
		{
	 		<c:forEach var="list" items="${resultPositionList}" varStatus="status" begin="1">
				//position.push("'${list.position}'");
				totalCnt.push(["${list.position}", eval("${list.totalCnt}")]);
			</c:forEach>	

			 $.jqplot('resultPositionChart', [totalCnt], {
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
		            	sliceMargin: 3,
		                showDataLabels: true
		            }
		        }, 
     		    legend: {
		            show: false,
		            rendererOptions: {
		                numberRows: 5
		            },		            
		            location: 'e'
		        }  
		    });	 
		}		
	}
	
	function resultDepartChart(){
		// 부서별 신청현황 pie chart
		var totalCnt = [];	

		if("${resultDepartList[0].totalCnt}" != 0) 
		{
	 		<c:forEach var="list" items="${resultDepartList}" varStatus="status" begin="1">
				totalCnt.push(["${list.depart}", eval("${list.totalCnt}")]);
			</c:forEach>	

			 $.jqplot('resultDepartChart', [totalCnt], {
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
		            	sliceMargin: 3,
		                showDataLabels: true
		            }
		        },
     		    legend: {
		            show: false,
		            rendererOptions: {
		                numberRows: 5
		            },		            
		            location: 'e'
		        } 		        
		    });	 
		}		
	}

	function resultUserChart(){
		// 개인별 신청현황 pie chart
		var totalCnt = [];	

		if("${resultUserList[0].totalCnt}" != 0) 
		{
	 		<c:forEach var="list" items="${resultUserList}" varStatus="status" begin="1">
				totalCnt.push(["${list.userId}", eval("${list.totalCnt}")]);
			</c:forEach>	

			 $.jqplot('resultUserChart', [totalCnt], {
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
		            	sliceMargin: 3,
		                showDataLabels: true
		            }
		        },
     		    legend: {
		            show: false,
		            rendererOptions: {
		                numberRows: 5
		            },		            
		            location: 'e'
		        } 		        
		    });	 
		}		
	}
	
	function resultApprTimeChart(){
		// 승인 소요시간 bar chart
		var max = parseInt('${resultApprTimeList[0].maxVal}');
		var min = parseInt('${resultApprTimeList[0].minVal}');
		var avg = parseInt('${resultApprTimeList[0].avgVal}');
		if(max != 0){
	 	    var line = [max, min, avg];
		    var ticks = ['최대', '최소', '평균'];
	 	    
		    $('#resultApprTimeChart').jqplot([line], {
		        seriesDefaults:{
		            renderer:$.jqplot.BarRenderer,
		            rendererOptions: {
		            	varyBarColor: true,
		            	barMargin: 50,
		            	fillToZero: true
		            },
		    		pointLabels: { show: true, location:'s' }
		        },	        
	            grid: {
	                background: '#ffffff'
	                ,drawGridlines: true
	                ,drawBorder: false
	                ,shadow: true
	                ,borderColor: '#999999'
	                ,borderWidth: 1
	            },	         
		        axes:{
		            xaxis:{
		                renderer: $.jqplot.CategoryAxisRenderer,
		               	ticks : ticks
		            },
		        	yaxis:{
		        		min: 0,
		        		max: max,
		        		tickOptions: { formatString: '%d시간' }
		        	}
		        }            
		    });
        }
	}
	
	function resultPlayChart(){
		// 재생횟수 bar chart
		var max = parseInt('${resultPlayList[0].maxVal}');
		var min = parseInt('${resultPlayList[0].minVal}');
		var avg = parseInt('${resultPlayList[0].avgVal}');
		if(max != 0)
        {		
	 	    var line = [max, min, avg];
		    var ticks = ['최대', '최소', '평균'];
	 	    
		    $('#resultPlayChart').jqplot([line], {
		        seriesDefaults:{
		            renderer:$.jqplot.BarRenderer,
		            rendererOptions: {
		            	varyBarColor: true,
		            	barMargin: 50,
		            	fillToZero: true
		            },
		    		pointLabels: { show: true, location:'s' }
		        },	        
	            grid: {
	                background: '#ffffff'
	                ,drawGridlines: true
	                ,drawBorder: false
	                ,shadow: true
	                ,borderColor: '#999999'
	                ,borderWidth: 1
	            },	         
		        axes:{
		            xaxis:{
		                renderer: $.jqplot.CategoryAxisRenderer,
		               	ticks : ticks
		            },
		        	yaxis:{
		        		min: 0,
		        		max: max,
		        		tickOptions: { formatString: '%d' }
		        	}
		        }            
		    });
        }
	}
	
	function resultVolumeChart(){
		// 파일클기 bar chart
        var max = parseInt('${resultVolumeList[0].maxVal}');
		var min = parseInt('${resultVolumeList[0].minVal}');
		var avg = parseInt('${resultVolumeList[0].avgVal}');
		if(max != 0)
        {		
	 	    var line = [max, min, avg];
		    var ticks = ['최대', '최소', '평균'];
	 	    
		    $('#resultVolumeChart').jqplot([line], {
		        seriesDefaults:{
		            renderer:$.jqplot.BarRenderer,
		            rendererOptions: {
		            	varyBarColor: true,
		            	barMargin: 50,
		            	fillToZero: true
		            },
		    		pointLabels: { show: true, location:'s', labels:['${resultVolumeList[0].maxValTr}', '${resultVolumeList[0].minValTr}', '${resultVolumeList[0].avgValTr}']}
		        },	        
	            grid: {
	                background: '#ffffff'
	                ,drawGridlines: true
	                ,drawBorder: false
	                ,shadow: true
	                ,borderColor: '#999999'
	                ,borderWidth: 1
	            },	         
		        axes:{
		            xaxis:{
		                renderer: $.jqplot.CategoryAxisRenderer,
		               	ticks : ticks
		            },
		        	yaxis:{
		        		min: 0,
		        		max: '${resultVolumeList[0].maxVal}',
		        		tickOptions: { formatString: "%'d Byte" }
		        	}
		        }            
		    });
        }
	}
	
	function resultTimeChart(){
		// 범죄 발생시간 pie chart
		if("${resultTimeList[0].totalCnt}" != 0) 
		{	
			var totalCnt = [['00~03시',eval("${resultTimeList[0].time00_03}")], ['03~06시',eval("${resultTimeList[0].time03_06}")], ['06~09시',eval("${resultTimeList[0].time06_09}")], ['09~12시',eval("${resultTimeList[0].time09_12}")], 
			                ['12~15시',eval("${resultTimeList[0].time12_15}")], ['15~18시',eval("${resultTimeList[0].time15_18}")], ['18~21시',eval("${resultTimeList[0].time18_21}")], ['21~24시',eval("${resultTimeList[0].time21_24}")]];
			$.jqplot('resultTimeChart', [totalCnt], {
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
		            	sliceMargin: 3,
		                showDataLabels: true
		            }
		        }, 
     		        legend: {
		            show: true,
		            rendererOptions: {
                        numberRows : 4
		            },		            
		            location: 'e'
		        }  
		    });	 
		}		
	}	
</script>
		<!-- 종합 현황 DIV Start -->
		<div id="synthesizeStat" class="panel panel-default" style="margin-top:10px; clear:both;">
			<div class="panel-body text_red">
				<strong>종합현황</strong>&nbsp;&nbsp;
				<c:choose>
					<c:when test="${stDate eq ''}"><span class="tooltip-inner">전체</span></c:when>
					<c:otherwise><span class="tooltip-inner">${stDate} ~ ${edDate}</span></c:otherwise>
				</c:choose>
			</div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:210px">
				<div id="resultApplyChart" style="height:200px"></div>
			</div>
			<table class="table table-striped table_b text-center">
	            <thead>
	              <tr>
	                <th>구분</th>
	                <th>전체</th>
	                <th>대기</th>
	                <th>처리중</th>
	                <th>승인</th>
	              </tr>
	            </thead>
	            <tbody>
				<c:forEach var="list" items="${resultApplyList}" varStatus="status">
					<tr>
						<td>${list.yearCnt}</td>
						<td>${list.totalCnt}</td>
						<td>${list.waitingCnt}</td>
						<td>${list.procCnt}</td>
						<td>${list.allowCnt}</td>
					</tr>
				</c:forEach>	
	            </tbody>
			</table>
		</div>
		<!-- 소속별 신청현황 DIV -->
		<div id="positionStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>소속별 신청현황</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:220px">
				<div id="resultPositionChart" style="height:210px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th width="30%">구분</th>
						<th>전체</th>
						<c:forEach var="list" items="${yearList}" varStatus="status">
						<th>${list}</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${resultPositionList}" varStatus="status">
					<tr>
						<td>${list.position}</td>
						<td>${list.totalCnt}</td>
						
						<c:forEach var="yList" items="${yearList}" varStatus="yStatus">
						<c:set var="yearName" value="year_${yStatus.index}" />
						<td>${list[yearName]}</td>
						</c:forEach>										
					</tr>
					</c:forEach>				
				</tbody>
			</table>
		</div>
		<!-- 부서별 신청현황 DIV -->
		<div id="departStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>부서별 신청현황</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:220px">
				<div id="resultDepartChart" style="height:210px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th width="30%">구분</th>
						<th>전체</th>
						<c:forEach var="list" items="${yearList}" varStatus="status">
						<th>${list}</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${resultDepartList}" varStatus="status">
					<tr>
						<td>${list.depart}</td>
						<td>${list.totalCnt}</td>
						
						<c:forEach var="yList" items="${yearList}" varStatus="yStatus">
						<c:set var="yearName" value="year_${yStatus.index}" />
						<td>${list[yearName]}</td>
						</c:forEach>										
					</tr>
					</c:forEach>				
				</tbody>
			</table>
		</div>
		<!-- 개인별 신청현황 DIV -->
		<div id="userStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>개인별 신청현황</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:220px">
				<div id="resultUserChart" style="height:210px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th width="30%">구분</th>
						<th>전체</th>
						<c:forEach var="list" items="${yearList}" varStatus="status">
						<th>${list}</th>
						</c:forEach>						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${resultUserList}" varStatus="status">
					<tr>
						<td>${list.userId}</td>
						<td>${list.totalCnt}</td>
						
						<c:forEach var="yList" items="${yearList}" varStatus="yStatus">
						<c:set var="yearName" value="year_${yStatus.index}" />
						<td>${list[yearName]}</td>
						</c:forEach>										
					</tr>
					</c:forEach>				
				</tbody>
			</table>
		</div>
		<!-- 범죄유형별 신청현황 DIV -->
		<div id="crimeStat" class="panel panel-default" style="clear:both; margin-top:10px;">
			<div class="panel-body text_red">
				<strong>범죄유형별 신청현황</strong>&nbsp;<span style="font-size:12px; color:#666; position:relative; top:0; float:right;">(상세건수 : 해결/미해결/수사중/미응답)</span>
			</div>
			<div class="panel-body">
				<table class="table table-striped table_b text-center" style="margin:0">
					<thead>
						<tr>
							<th>구분</th>
							<th>&nbsp;</th>
							<th>범죄총계</th>
							<th>살인</th>
							<th>강도</th>
							<th>강간/추행</th>
							<th>절도</th>
							<th>폭행</th>
							<th>교통사고</th>
							<th>재물손괴</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${resultCrimeList}" varStatus="status">
						<tr>
							<td rowspan="3" style="border-right:1px solid #e9e9e9">${list.yearCnt}</td>
							<td>신청순위</td>
							<td></td>
							<td>${list.murderRank}</td>
							<td>${list.robberRank}</td>
							<td>${list.rapeRank}</td>
							<td>${list.theftRank}</td>
							<td>${list.violenceRank}</td>
							<td>${list.accidentRank}</td>
							<td>${list.destroyRank}</td>
							<td>${list.etcRank}</td>
						</tr>
						<tr>
							<td>신청건수</td>
							<td>${list.totalCnt}</td>
							<td>${list.murderCnt}</td>
							<td>${list.robberCnt}</td>
							<td>${list.rapeCnt}</td>
							<td>${list.theftCnt}</td>
							<td>${list.violenceCnt}</td>
							<td>${list.accidentCnt}</td>
							<td>${list.destroyCnt}</td>	
							<td>${list.etcCnt}</td>														
						</tr>
						<tr>
							<td>상세건수</td>
							<td>${list.totalSolveCnt}/${list.totalNotSolveCnt}/${list.totalIngCnt}/${list.totalNotAnswerCnt}</td>
							<td>${list.murderSolveCnt}/${list.murderNotSolveCnt}/${list.murderIngCnt}/${list.murderNotAnswerCnt}</td>
							<td>${list.robberSolveCnt}/${list.robberNotSolveCnt}/${list.robberIngCnt}/${list.robberNotAnswerCnt}</td>
							<td>${list.rapeSolveCnt}/${list.rapeNotSolveCnt}/${list.rapeIngCnt}/${list.rapeNotAnswerCnt}</td>
							<td>${list.theftSolveCnt}/${list.theftNotSolveCnt}/${list.theftIngCnt}/${list.theftNotAnswerCnt}</td>
							<td>${list.violenceSolveCnt}/${list.violenceNotSolveCnt}/${list.violenceIngCnt}/${list.violenceNotAnswerCnt}</td>
							<td>${list.accidentSolveCnt}/${list.accidentNotSolveCnt}/${list.accidentIngCnt}/${list.accidentNotAnswerCnt}</td>
							<td>${list.destroySolveCnt}/${list.destroyNotSolveCnt}/${list.destroyIngCnt}/${list.destroyNotAnswerCnt}</td>	
							<td>${list.etcSolveCnt}/${list.etcNotSolveCnt}/${list.etcIngCnt}/${list.etcNotAnswerCnt}</td>														
						</tr>
					</c:forEach>										
					</tbody>
				</table>
			</div>
		</div>
		<!-- 승인 소요시간 DIV -->
		<div id="apprTimeStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red">
				<strong>승인 소요시간</strong>&nbsp;<span style="font-size:12px; color:#666">(단위 / 시간:분:초)</span>
			</div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:210px">
				<div id="resultApprTimeChart" style="height:200px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th>구분</th>					
						<th>최대</th>
						<th>최소</th>
						<th>평균</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${resultApprTimeList}" varStatus="status">
					<tr>
						<td>${list.yearCnt}</td>
						<td>${list.maxVal}</td>
						<td>${list.minVal}</td>
						<td>${list.avgVal}</td>
					</tr>
				</c:forEach>				
				</tbody>
			</table>
		</div>
		<!-- 평균 재생횟수 DIV -->
		<div id="playStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>평균 재생횟수</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:210px">
				<div id="resultPlayChart" style="height:200px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th>구분</th>
						<th>최대</th>
						<th>최소</th>
						<th>평균</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${resultPlayList}" varStatus="status">
					<tr>
						<td>${list.yearCnt}</td>
						<td>${list.maxVal}</td>
						<td>${list.minVal}</td>
						<td>${list.avgVal}</td>
					</tr>
				</c:forEach>					
				</tbody>
			</table>
		</div>
		<!-- 파일 크기 DIV -->
		<div id="volumeStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>파일 크기</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:210px">
				<div id="resultVolumeChart" style="height:200px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th>구분</th>
						<th>최대</th>
						<th>최소</th>
						<th>평균</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${resultVolumeList}" varStatus="status">
					<tr>
						<td>${list.yearCnt}</td>
						<td><fmt:formatNumber value="${list.maxVal}" groupingUsed="true" /> Byte &nbsp;(${list.maxValTr})</td> 
						<td><fmt:formatNumber value="${list.minVal}" groupingUsed="true" /> Byte &nbsp;(${list.minValTr})</td>	
						<td><fmt:formatNumber value="${list.avgVal}" groupingUsed="true" /> Byte &nbsp;(${list.avgValTr})</td>				
					</tr>
				</c:forEach>				
				</tbody>
			</table>
		</div>
		<div id="timeStat" class="panel panel-default" style=" clear:both; margin-top:10px">
			<div class="panel-body text_red"><strong>범죄 발생시간</strong></div>
			<div class="panel-footer" style="background:url(../../resources/images/th.gif); height:220px">
				<div id="resultTimeChart" style="height:210px"></div>
			</div>
			<table class="table table-striped table_b text-center">
				<thead>
					<tr>
						<th>구분</th>
						<th>총계</th>
						<th>00~03시</th>
						<th>03~06시</th>
						<th>06~09시</th>
						<th>09~12시</th>
						<th>12~15시</th>
						<th>15~18시</th>
						<th>18~21시</th>
						<th>21~24시</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${resultTimeList}" varStatus="status">
					<tr>
						<td>${list.yearCnt}</td>
						<td>${list.totalCnt}</td>
						<td>${list.time00_03}</td>
						<td>${list.time03_06}</td>
						<td>${list.time06_09}</td>
						<td>${list.time09_12}</td>	
						<td>${list.time12_15}</td>
						<td>${list.time15_18}</td>
						<td>${list.time18_21}</td>
						<td>${list.time21_24}</td>									
					</tr>
				</c:forEach>					
				</tbody>
			</table>
		</div>
