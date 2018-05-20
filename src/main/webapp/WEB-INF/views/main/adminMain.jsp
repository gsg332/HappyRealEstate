<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<%@ include file="/WEB-INF/views/common/headerSub.jsp" %>
<!-- cctv운용현황 차트-->
<c:forEach var="list" items="${cctvMarkerTypeList}" varStatus="status">
	<c:set var="sumCctvTotCnt" value="${sumCctvTotCnt + list.cctvCount}" />
	<c:choose><c:when test="${status.last}">
			<c:set var="sumCctvCnt" value="${sumCctvCnt}[eval(${list.cctvCount}), ${status.count}],[eval(${sumCctvTotCnt}), ${status.count+1}]" scope="page"/>
			<c:set var="cctvTypeNm" value="${cctvTypeNm}'<span class=chartFont1>${list.cctvTypeNm}</span>','<span class=chartFont1>전체</span>'" scope="page"/>
		</c:when>
		<c:otherwise>
			<c:set var="sumCctvCnt" value="${sumCctvCnt}[eval(${list.cctvCount}), ${status.count}]," scope="page"/>
			<c:set var="cctvTypeNm" value="${cctvTypeNm}'<span class=chartFont1>${list.cctvTypeNm}</span>'," scope="page"/>
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- 미활용 차트 -->
<c:forEach var="list" items="${retUseResList}" varStatus="status">
	<c:choose>
		<c:when test="${list.useResDiff == 'Y'}">
			<c:set var="useResUseNm" value="${list.useResDiff}" scope="page"/>
			<c:set var="useResUseCnt" value="${list.useResDiffCnt}" scope="page"/>
		</c:when>
		<c:otherwise>
			<c:set var="useResNoUseNm" value="${list.useResDiff}" scope="page"/>
			<c:set var="useResNoUseCnt" value="${list.useResDiffCnt}" scope="page"/>
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- 프로그래스 차트 -->
<c:set var="InputRnd1" value="${fn:split(((allList[12] / allList[0]) * 100),'.')}" scope="page"/>
<c:set var="RetRnd1" value="${fn:split(((allList[12] / allList[2]) * 100),'.')}" scope="page"/>
<c:set var="ResNoUseCnt1" value="${fn:split(((useResNoUseCnt/(useResUseCnt+useResNoUseCnt)) * 100),'.')}" scope="page"/>
<!-- 주/야간 차트 -->
<c:set var="grfTimeH07_18" value="${fn:split(((resultTimeList[0].timeH07_18 / (resultTimeList[0].timeH07_18+resultTimeList[0].timeH18_07)) * 100),'.')}" scope="page"/>
<c:set var="grfTimeH18_07" value="${fn:split(((resultTimeList[0].timeH18_07 / (resultTimeList[0].timeH07_18+resultTimeList[0].timeH18_07)) * 100),'.')}" scope="page"/>

<script type="text/javascript">
	try {document.execCommand('BackgroundImageCache', false, true);} catch(e) {}
</script>
<script type="text/javascript">
	jQuery(function($) {
		$('.pie_progress').asPieProgress({
			namespace: 'pie_progress'
		});
		$('.pie_progress').asPieProgress('start');
	});

	$(document).ready(function(){
	
		adminApplyChart();
		adminReasonChart();
		adminAllChart();
		adminAllChart2();
		adminCctvChart();
		adminCrimeChart();
		adminTimeChart();
	});

	function adminTimeChart(){
		// 범죄 발생시간 bar chart
			var totalCnt = [eval("${resultTimeList[0].time00_03}"), eval("${resultTimeList[0].time03_06}"), eval("${resultTimeList[0].time06_09}"), eval("${resultTimeList[0].time09_12}"), 
			                eval("${resultTimeList[0].time12_15}"), eval("${resultTimeList[0].time15_18}"), eval("${resultTimeList[0].time18_21}"), eval("${resultTimeList[0].time21_24}")];
			var ticks = ['<span class=chartFont2>00~03</span>',
			             '<span class=chartFont2>03~06</span>',
			             '<span class=chartFont2>06~09</span>',
			             '<span class=chartFont2>09~12</span>',
			             '<span class=chartFont2>12~15</span>',
			             '<span class=chartFont2>15~18</span>',
			             '<span class=chartFont2>18~21</span>',
			             '<span class=chartFont2>21~24</span>'];
			
			$.jqplot('adminTimeChart', [totalCnt], {
		        seriesDefaults:{
		            renderer:$.jqplot.BarRenderer,
		            rendererOptions: {
		            	varyBarColor: false,
		            	sliceMargin: 3,
		                showDataLabels: true,
		                fillToZero: true
		            },
	    			pointLabels: { show: true }
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
		        		max: Math.max('${resultTimeList[0].time00_03 +5}', '${resultTimeList[0].time03_06 +5}', '${resultTimeList[0].time06_09 +5}', '${resultTimeList[0].time09_12 +5}', 
		        					  '${resultTimeList[0].time12_15 +5}', '${resultTimeList[0].time15_18 +5}', '${resultTimeList[0].time18_21 +5}', '${resultTimeList[0].time21_24 +5}'),
		        		tickOptions: { formatString: '%d' }
		        	}
		        }    
		    });
			
			var yCnt = ['<span class=chartFont2>주간/야간</span>'];
			var grfTimeH07_18 = ${grfTimeH07_18[0]};
			var grfTimeH18_07 = ${grfTimeH18_07[0]};
			if (grfTimeH07_18 == 'NaN') grfTimeH07_18 = 0;
			if (grfTimeH18_07 == 'NaN') grfTimeH18_07 = 0;
			
	        $.jqplot('adminHalftTimeChart', [[[grfTimeH07_18,1,'주간 '+grfTimeH07_18+' %']],
	                                         [[grfTimeH18_07,1,'야간 '+grfTimeH18_07+' %']]], {
	            stackSeries: true,
	            captureRightClick: true,
	            
	            seriesDefaults:{
	                renderer:$.jqplot.BarRenderer,
	                shadowAngle: 135,
	                rendererOptions: {
	                    barDirection: 'horizontal',
	                    highlightMouseDown: true
	                },
	                pointLabels: {show: true, formatString: '%s'}
	            },
	            grid: {
			          drawBorder: false, 
			          drawGridlines: false,
			          background: '#fff',
			          shadow:false
			      },
	            axes: {
	                yaxis: {
	                    renderer: $.jqplot.CategoryAxisRenderer,
	                    tickOptions:{
	        				showMark:false,showGridline:true,showLabel:false,show:false
		        		},
	                },
	                xaxis:{
	                	max : Math.max('100'),
	        			tickOptions:{
	        				showMark:false,showGridline:true,showLabel:false,show:false
		        		},
		        		showTickMarks:false,
	        		}
	            }
	        });
	        // 차트 안 문구 크게
	        var type1,type2;
			type1 =$('#adminHalftTimeChart .jqplot-point-label.jqplot-series-0.jqplot-point-0').text();
			type2 =$('#adminHalftTimeChart .jqplot-point-label.jqplot-series-1.jqplot-point-0').text();
			if (type1 != '') {
				$('#adminHalftTimeChart .jqplot-point-label.jqplot-series-0.jqplot-point-0').html("<span class=chartFont1 style='font-weight: bold'> "+type1+"</span>");
				$('#adminHalftTimeChart .jqplot-point-label.jqplot-series-1.jqplot-point-0').html("<span class=chartFont1 style='font-weight: bold'> "+type2+"</span>");
			}
			$('#adminHalftTimeChart .jqplot-yaxis-tick').css('display','none');
	}	
	
	function adminApplyChart(){
		// 업무처리현황 bar chart
		//처리건수
	    var line1 = [${BusinessCounts.busiApplyProcCnt}, ${BusinessCounts.busiExtendApplyCnt}, ${BusinessCounts.busiChangeApplyCnt}];
		// 신청건수
	    var line2 = [${BusinessCounts.busiApplyReqCnt}, ${BusinessCounts.busiExtendReqCnt}, ${BusinessCounts.busiChangeReqCnt}];
		  $('#adminApplyChart').jqplot('adminApply', [line1, line2], {
		      stackSeries: true, 
		      seriesDefaults: {
		          renderer: $.jqplot.BarRenderer,
		          rendererOptions:{barMargin: 25},
		          pointLabels:{show:false, stackedValue: false}
		      },
		      grid: {
		          drawBorder: false, 
		          drawGridlines: false,
		          background: '#fff',
		          shadow:false,
		      },
		      axes: {
		          xaxis:{
		        			tickOptions:{
		        				showMark:false,showGridline:true,showLabel:false,show:false
			        		},
			        		showTickMarks:false,
		            		renderer:$.jqplot.CategoryAxisRenderer,
	            		},
		          yaxis:{
		        			tickOptions:{
		        				showMark:false,showGridline:true,showLabel:false,show:true
		        			},
		        			showTickMarks:false
	            		}
		      }
		  });
		  // 차트 크기 재조걸
		  $('.jqplot-axis.jqplot-xaxis').css('display','none');
		  $('#adminApplyChart').css('height','95px');
	}


	function adminReasonChart(){
		// 미활용 현황 donut chart
		if (isNaN('${ResNoUseCnt1[0]}') == false ) {
			setTimeout(function(){
				$('#adminReasonChart0').css('display','none');
				$('#adminReasonChart1').css('display','');
			},650);
		}
	}
	
	function adminAllChart(){
		// 종합현황 파이차트
		if (isNaN('${InputRnd1[0]}') == false ) {
			setTimeout(function(){
				$('#adminAllInputRnd0').css('display','none');
				$('#adminAllInputRnd1').css('display','');
				
			},650);
		}
		if (isNaN('${InputRnd1[0]}') == false ) {
			setTimeout(function(){
				$('#adminAllRetRnd0').css('display','none');
				$('#adminAllRetRnd1').css('display','');
			},650);
		}
	}
	
	function adminAllChart2(){
		// 종합 현황 horizontal bar chart		

		var yCnt = ['<span class=chartFont1>해결활용<br>/미활용</span>', '<span class=chartFont1>결과등록<br>/미등록</span>', '<span class=chartFont1>전체신청</span>'];
	    var totalCnt = [
	                    [[eval("${allList[12]}"), 1], [eval("${allList[2]}"), 2], [eval("${allList[0]}"), 3]], 
	                    [[eval("${allList[13]}"), 1], [eval("${allList[1]}"), 2]]
	                   ];

		$.jqplot('adminAllChart2', totalCnt, {
			  stackSeries: true,
			  captureRightClick: true,
		      seriesDefaults:{
		          renderer:$.jqplot.BarRenderer,
		          shadowAngle: 135,
		          rendererOptions: {
                      barDirection: 'horizontal',
                      barMargin: 30,
                      highlightMouseDown: true 
	              },
	              pointLabels: {show: true, formatString: '%d건'}
		      },
		      grid: {
		          drawBorder: false, 
		          drawGridlines: false,
		          background: '#fff',
		          shadow:false,
		      },			      
	          axes: {
                 yaxis: {
                     renderer: $.jqplot.CategoryAxisRenderer,
                     ticks : yCnt,
                     tickOptions: {
                         showGridline: false,
                         markSize: 0
                     }
                 },
                 xaxis:{
	        			tickOptions:{
	        				showMark:false,showGridline:true,showLabel:false,show:false
		        		},
		        		showTickMarks:false,
	        		}
             }
		  });

	}	

	
	function adminCctvChart(){
		// CCTV 운영현황 horizontal bar chart		

		var yCnt = [${cctvTypeNm}];
		var totalCnt = [[${sumCctvCnt}]];
		
	    $.jqplot('adminCctvChart', totalCnt, {
		      seriesDefaults:{
		          renderer:$.jqplot.BarRenderer,
		          shadowAngle: 135,
		          rendererOptions: {
                      barDirection: 'horizontal',
                      varyBarColor: false,
                      barMargin:10,
                      highlightMouseDown: true 
	              },
	              pointLabels: {show: true, formatString: '%d대', location: 'e', edgeTolerance: -15 }
		      },
		      grid: {
		          drawBorder: false, 
		          drawGridlines: false,
		          background: '#fff',
		          shadow:false,
		      },
	          axes: {
                 yaxis: {
                     renderer: $.jqplot.CategoryAxisRenderer,
                     ticks : yCnt,
                     tickOptions: {
                         showGridline: false,
                         markSize: 0
                   },
                 },
                 xaxis:{
	        			tickOptions:{
	        				showMark:false,showGridline:true,showLabel:false,show:false
		        		},
		        		showTickMarks:false,
	        	}
              }
		  });

	}

	function adminCrimeChart(){
		// 범죄 현황 donut chart		

		var totalCnt = [['<span id="chartCrimeLeg" class="chartFont1">살인</span>', eval("${typeStatInfo.murderCnt}")],
	                    ['<span class="chartFont1">강도</span> ', eval("${typeStatInfo.robberCnt}")],
	                    ['<span class="chartFont1">강간/추행 </span>', eval("${typeStatInfo.rapeCnt}")],
	                    ['<span class="chartFont1">절도</span>', eval("${typeStatInfo.theftCnt}")],
	                    ['<span class="chartFont1">폭행</span>', eval("${typeStatInfo.violenceCnt}")],
	                    ['<span class="chartFont1">교통사고</span>', eval("${typeStatInfo.accidentCnt}")],
	                    ['<span class="chartFont1">재물손괴</span>', eval("${typeStatInfo.destroyCnt}")],
	                    ['<span class="chartFont1">기타</span>', eval("${typeStatInfo.etcCnt}")]];
	   	
		$.jqplot('adminCrimeChart', [totalCnt], {
			  //title: '기여율 : <fmt:formatNumber value="${allList[4] / allList[2] * 100}" pattern=".0" />%',
		      grid: {
		          drawBorder: false, 
		          drawGridlines: false,
		          background: '#fff',
		          shadow:false
		      },
		      axesDefaults: {
		           
		      },
		      seriesDefaults:{
		          renderer:$.jqplot.DonutRenderer,
		          rendererOptions: {
		          	  sliceMargin: 3,
		              startAngle: -90,
		              showDataLabels: true,
		              dataLabels: 'value',
		              totalLabel: false
		          },
		      },
			  legend: {
			      show: true,
			      location: 'e'
			  }
		});
		// 범례 테두리 삭제
		$('#chartCrimeLeg').parent().parent().parent().parent().css('border','0px #ccc');
	}
	function moveApplyList(page,status,applyStatus){
		$("#searchStatus").val(status);
		$("#searchApplyStatus").val(applyStatus);
		var pageUrl = "/apply/apply/list.do";
		if (page == "change") {
			$("#searchTypeDepth1").val('영상정보신청');
			pageUrl = "/system/changeReq/list.do";
		}
		
		searchFrm.action = pageUrl;
		searchFrm.submit();
	}
</script>
<form name="searchFrm" method="post">
	<input type="hidden" name="searchStatus" id="searchStatus">
	<input type="hidden" name="searchTypeDepth1" id="searchTypeDepth1">
	<input type="hidden" name="searchApplyStatus" id="searchApplyStatus">
</form>
<!-- Content Area -->
<div class="container">
	<div class="row" style="margin-top:60px;">
		<div class="col-md-3 col-xs-3 box_01">
			<p><img src="/resources/images/bg_box1_p.jpg" width="10" height="290" alt=""/></p>
			<span>업무&nbsp;&nbsp;처리현황</span>
			<table class="table table-bordered table1 p10">
				<thead>
					<tr>
						<th>전체</th>
						<th>대기</th>
						<th>처리중</th>
						<th>승인</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${applyCounts.totalCnt == '00'}">
								<td>00</td>
				                <td>00</td>
				                <td>00</td>
				                <td>00</td>
							</c:when>
							<c:otherwise>
								<td>${applyCounts.totalCnt}</td>
				                <td>${applyCounts.waitingCnt}</td>
				                <td>${applyCounts.procCnt}</td>
				                <td>${applyCounts.allowCnt}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
			<div  align=center style="margin-top:5px;">
			<table>
				<tbody>
					<tr class="jqplot-table-legend">
						<td class="jqplot-table-legend jqplot-table-legend-swatch" style="text-align: center; padding-top: 0px;">
							<div class="jqplot-table-legend-swatch-outline"><div class="jqplot-table-legend-swatch" style="border-color: #eaa228; background-color: #eaa228;"></div>
							</div>
						</td>
						<td class="jqplot-table-legend jqplot-table-legend-label" style="padding-top: 0px;">신청건수
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;
						</td>
						<td class="jqplot-table-legend jqplot-table-legend-swatch" style="text-align: center;">
							<div class="jqplot-table-legend-swatch-outline"><div class="jqplot-table-legend-swatch" style="border-color: #4bb2c5; background-color: #4bb2c5;"></div>
							</div>
						</td>
						<td class="jqplot-table-legend jqplot-table-legend-label">처리건수
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<!--업무처리현황 다이어그램시작 -->
			<div id="adminApplyChart" style="height:110px"></div>
			<table class="table table1">
				<thead>
					<tr>
						<th>영상정보신청</th>
						<th>연장신청</th>
						<th>변경요청</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="font-weight: bold"><a href='javascript:moveApplyList("apply","0","")'><u>${BusinessCounts.busiApplyReqCnt}</u></a></td>
		                <td style="font-weight: bold"><a href='javascript:moveApplyList("apply","2","2")'><u>${BusinessCounts.busiExtendReqCnt}</u></a></td>
		                <td style="font-weight: bold"><a href='javascript:moveApplyList("change","1000","")'><u>${BusinessCounts.busiChangeReqCnt}</u></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-md-9 col-xs-9">
			<article class="box_02">
				<section class="box_02_1 col-md-6 col-xs-6">
					<p>종합&nbsp;&nbsp;현황</p>
					<!--종합현황 다이어그램-->
					<div class="diagram2">
						<div>
						</div>
						<table style="height:140px;width:300px;">
						<tbody>
						<tr>
							<td style="width:1px;">
							<div id="adminAllInputRnd" style="width:150px;height:140px; margin-left:40px;margin-top:-10px;">
								<div class="pie_progress" id="adminAllInputRnd0" style="display:;position:absolute;z-index:8;" role="progressbar" data-goal="0" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
									<div class="pie_progress__content" >
										<label class="pie_progress_title1">입력대비</label>
										<br><label class="pie_progress_title2">기여율</label>
										<br><label class="pie_progress_title2">00%</label>
									</div>
								</div>
								<div class="pie_progress" id="adminAllInputRnd1" style="display:none;position:absolute;z-index:7;" role="progressbar" data-goal="${InputRnd1[0]}" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
									<div class="pie_progress__content" >
										<label class="pie_progress_title1">입력대비</label>
										<br><label class="pie_progress_title2">기여율</label>
										<br>
										<c:choose>
										<c:when test="${InputRnd1[0] eq 'NaN'} ">
											<label class="pie_progress_title2">0%</label>
										</c:when>
										<c:otherwise>
											<label class="pie_progress_title2">${InputRnd1[0]}%</label>
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							</td>
							<td>
							<div id="adminAllRetRnd" style="height:140px; margin-top:-10px;">
							<div class="pie_progress" id="adminAllRetRnd0" style="display:;position:absolute;z-index:8;" role="progressbar" data-goal="0" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
									<div class="pie_progress__content" >
										<label class="pie_progress_title1">결과대비</label>
										<br><label class="pie_progress_title2">기여율</label>
										<br><label class="pie_progress_title2">00%</label>
									</div>
								</div>
								<div class="pie_progress" id="adminAllRetRnd1" style="display:none;position:absolute;z-index:7;" role="progressbar"  data-goal="${RetRnd1[0]}" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
									<div class="pie_progress__content" >
										<label class="pie_progress_title1">결과대비</label>
										<br><label class="pie_progress_title2">기여율</label>
										<br>
										<c:choose>
										<c:when test="${RetRnd1[0] eq 'NaN'}">
											<label class="pie_progress_title2">0%</label>
										</c:when>
										<c:otherwise>
											<label class="pie_progress_title2">${RetRnd1[0]}%</label>
										</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							</td>
							</tbody>
						</table>
						<div id="adminAllChart2" style="height:150px; margin-top:-24px;"></div>
					</div>
				</section>
				<section class="box_02_1 col-md-6 col-xs-6">
					<p class="bg_st" style="left:20px">CCTV&nbsp;&nbsp;운영현황</p>
				    <div id="adminCctvChart" style="height:270px; width:350px; margin-top:-5px; margin-left:-20px;"></div>
				</section>
			</article>
		</div>
		<div class="col-md-3 col-xs-3 box_03" style="margin-top:20px">
			<p><img src="/resources/images/bg_box3_p.jpg" width="10" height="330" alt=""/></p>
			<span class="sp">CCTV&nbsp;&nbsp;미활용현황</span>
			<!--미활용현황 다이어그램-->
			<div  style="background:#fff;height:140px; margin-bottom:-0px">
				<br>
				<div class="pie_progress" id="adminReasonChart0" style="position:absolute;z-index:8;background:#fff;margin-left:65px;margin-top:5px;" role="progressbar" data-goal="0" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
					<div class="pie_progress__content" >
						<label class="pie_progress_title2">미활용</label>
						<br><label class="pie_progress_pers">00%</label>
					</div>
				</div>
				<div class="pie_progress" id="adminReasonChart1" style="display:none;position:absolute;z-index:7;background:#fff;margin-left:65px;margin-top:5px;" role="progressbar" data-goal="${ResNoUseCnt1[0]}" data-barcolor="#ff9933" data-barsize="17"  aria-valuemax="100">
					<div class="pie_progress__content" >
						<label class="pie_progress_title2">미활용</label>
						<br><label class="pie_progress_pers">${ResNoUseCnt1[0]}%</label>
					</div>
				</div>
			</div>
			
			<div style="height:165px;margin-top:0px">
			<table class="table table-striped table3">
				<thead>
					<tr>
						<th>순위</th>
						<th>미활용사유</th>
						<th>건수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${reasonList}" varStatus="status" begin="1">
					<tr>
						<td>${status.count}</td>
						<td>${list.codeVal}</td>
						<td>${list.totalCnt}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		<div class="col-md-9 col-xs-9" style="margin-top:20px">
			<article class="box_04">
				<section class="box_04_1 col-md-6 col-xs-6">
					<p>유형별&nbsp;&nbsp;범죄현황</p>
					<!--유형별 다이어그램-->
					<div class="diagram3" id="adminCrimeChart" style="margin-left:-20px; width:310px;"></div>
				</section>
				<section class="box_04_1 col-md-6 col-xs-6">
					<p class="bg_st1" style="left:20px">시간대별&nbsp;&nbsp;범죄현황</p>
					<!--시간대별 다이어그램-->
					<div class="diagram4" id="adminTimeChart" style="height:140px"></div>
					<div class="diagram4" id="adminHalftTimeChart" style="height:70px;width:100%;"></div>
				</section>
			</article>
			<article class="box_05">
				<ul>
					<li class="col-md-5 col-xs-5">
						<img src="/resources/images/clock.png" width="34" height="38" alt=""/>
						<div>
							<ul>
								<li class="time">${resultApprTimeList[0].avgVal}</li>
								<li>승인소요시간(평균/시간:분:초)</li>
							</ul>
						</div>
					</li>
					<li class="col-md-4 col-xs-4">
						<img src="/resources/images/film.png" width="34" height="38" alt=""/>
						<div>
							<ul>
								<li class="time">${resultPlayList[0].avgVal}</li>
								<li>재생횟수(평균/회)</li>
							</ul>
						</div>
					</li>
					<li class="col-md-3 col-xs-3" style="padding-left:0">
						<img src="/resources/images/save.png" width="34" height="38" alt=""/>
						<div>
							<ul>
								<li class="time"><fmt:formatNumber value="${resultVolumeList[0].avgVal}" groupingUsed="true" /></li>
								<li>파일크기(평균/Bytes)</li>
							</ul>
						</div>
					</li>
				</ul>
			</article>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
