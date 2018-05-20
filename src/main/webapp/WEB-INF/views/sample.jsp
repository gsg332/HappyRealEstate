<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$.jqplot('chartdiv1', [[[1, 2],[3,5.12],[5,13.1],[7,33.6],[9,85.9],[11,219.9]]], {
		title:'Exponential Line',
		axes:{yaxis:{min:-10, max:240}},
		series:[{color:'#5FAB78'}]
	});
	$.jqplot('chartdiv2', [[['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],
	                        ['Out of home', 16],['Commuting', 7], ['Orientation', 9]]], {
			seriesDefaults : {
				// make this a donut chart.
   	      	renderer:$.jqplot.DonutRenderer,
   	      	rendererOptions:{
      	        // Donut's can be cut into slices like pies.
      	        sliceMargin: 3,
      	        // Pies and donuts can start at any arbitrary angle.
      	        startAngle: -90,
      	        showDataLabels: true,
      	        // By default, data labels show the percentage of the donut/pie.
      	        // You can show the data 'value' or data 'label' instead.
      	        dataLabels: 'value',
      	        // "totalLabel=true" uses the centre of the donut for the total amount
      	        totalLabel: true
				}
			}
	});
	var s1 = [[2013, 114000], [2014, 133000], [2015, 161000], [2016, 173000]];
	var s2 = [[2013, 13200], [2014, 12600], [2015, 13100]];
	$.jqplot('chartdiv3', [s1, s2], {
		animate: true,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
        cursor: {
            show: true,
            zoom: true,
            looseZoom: true,
            showTooltip: false
        },
        series:[{
        	pointLabels: {
        		show: true
        	},
        	renderer: $.jqplot.BarRenderer,
        	showHighlight: false,
        	yaxis: 'y2axis',
        	rendererOptions: {
        		// Speed up the animation a little bit.
        		// This is a number of milliseconds.
        		// Default for bar series is 3000.
        		animation: {
        			speed: 2500
        		},
        		barWidth: 40,
        		barPadding: -15,
        		barMargin: 0,
        		highlightMouseOver: false
        	}
        },
        {
        	rendererOptions: {
        		// speed up the animation a little bit.
        		// This is a number of milliseconds.
        		// Default for a line series is 2500.
        		animation: {
        			speed: 2000
        		}
        	}
        }],
        axesDefaults: {
        	pad: 0
        },
        axes: {
        	// These options will set up the x axis like a category axis.
            xaxis: {
                tickInterval: 1,
                drawMajorGridlines: false,
                drawMinorGridlines: true,
                drawMajorTickMarks: false,
                rendererOptions: {
                	tickInset: 0.5,
                	minorTicks: 1
            	}
            },
            yaxis: {
                tickOptions: {
                    formatString: "$%'d"
                },
                rendererOptions: {
                    forceTickAt0: true
                }
            },
            y2axis: {
                tickOptions: {
                    formatString: "$%'d"
                },
                rendererOptions: {
                    // align the ticks on the y2 axis with the y axis.
                    alignTicks: true,
                    forceTickAt0: true
                }
            }
        },
        highlighter: {
            show: true, 
            showLabel: true, 
            tooltipAxes: 'y',
            sizeAdjust: 7.5 , tooltipLocation : 'ne'
        }
	});
	$.jqplot('chartdiv4', [[[2,1],[6,2],[7,3]], [[7,1],[5,2],[3,3]]], {
		stackSeries: true,
        captureRightClick: true,
        seriesDefaults:{
            renderer:$.jqplot.BarRenderer,
            shadowAngle: 135,
            rendererOptions: {
            	barWidth: 40,
                barDirection: 'horizontal',
                highlightMouseDown: true   
            },
            pointLabels: {show: true, formatString: '%d'}
        },
        legend: {
            show: false,
            location: 'e',
            placement: 'outside'
        },
        axes: {
            yaxis: {
                renderer: $.jqplot.CategoryAxisRenderer
            }
        }
	});
	
	$(".input-daterange").datepicker({
		
	});
	
	$("#selRowSize").change(function(){
		$("#rowSize").val($("#selRowSize").val());
	});
	
	searchApplyList();
	
});

function searchApplyList(){
	var params = $("#searchFrm").serialize();
	console.log(params);
	$.ajax({
		type : 'post',
		url : '/apply/list.json',
		dataType: 'html',
		data : params,	
		success : function(data) {
			$("#applyList").html(data);
		},
		error : function(request, status, error) {
			errorModal(request);
		},
	});   
}

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
	    $("#currentPage").val(num); // or some ajax content loading...
	 	// apply list search ajax
	 	searchApplyList();
	}); 
}

function fileDown(){
	console.log("asdf");
}	
</script>
	<!-- Content Area -->
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="col-xs-6 col-md-3">
				<div class="thumbnail" id="chartdiv1"></div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="thumbnail" id="chartdiv2"></div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="thumbnail" id="chartdiv3"></div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="thumbnail" id="chartdiv4"></div>
			</div>
		</div>
	</div>
	<div class="page-header">
		<h3>신청목록</h3>
		<ol class="breadcrumb">
		  <li><a href="#">Home</a></li>
		  <li><a href="#">Library</a></li>
		  <li class="active">Data</li>
		</ol>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<form id="searchFrm" name="searchFrm" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value="1" >
				<input type="hidden" id="rowSize" name="rowSize" value="10" >
				<div class="form-inline">
					<div class="form-group">
						<label for="searchTerm">기간</label>
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">당일
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">1주
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">2주
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">1개월
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">6개월
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">1년
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="searchTerm" id="searchTerm" autocomplete="off">전체
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="searchStatus">상태</label>
						<select class="form-control" id="searchStatus" name="searchStastus">
							<option value="">전체</option>
							<c:forEach var="list" items="${statusList}" varStatus="status">
								<option value="${list.codeKey}">${list.codeVal}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label class="sr-only" for="searchApply"></label>
						<input type="button" id="searchApply" name="searchApply" onclick="searchApplyList();" value="검색">
					</div>
				</div>
				<div class="form-inline">
					<div class="input-daterange input-group" id="datepicker">
						<input type="text" class="input-sm form-control" name="searchStDate" />
						<span class="input-group-addon">to</span>
						<input type="text" class="input-sm form-control" name="searchEdDate" />
					</div>
					<div class="form-group">
						<label for="searchCrime">범죄유형</label>
						<select class="form-control" id="searchCrime" name="searchCrime">
							<option value="">전체</option>
							<c:forEach var="list" items="${crimeTypeList}" varStatus="status">
								<option value="${list.codeKey}">${list.codeVal}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-inline">
					<label for="deptDepth">신청자</label>
					<select class="form-control" id="searchDept1" name="searchDept1">
						<option>소속</option>
					</select>
					<select class="form-control" id="searchDept2" name="searchDept2">
						<option>과</option>
					</select>
					<select class="form-control" id="searchDept3" name="searchDept3">
						<option>계</option>
					</select>
					<input type="text">
				</div>
			</form>
		</div>
		<div class="panel panel-defaul">
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>전체</th>
							<th>당해년도</th>
							<th>대기</th>
							<th>처리중</th>
							<th>승인</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${totalCounts.totalCnt}</td>
							<td>${totalCounts.yearCnt}</td>
							<td>${totalCounts.waitingCnt}</td>
							<td>${totalCounts.procCnt}</td>
							<td>${totalCounts.allowCnt}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-defaul" id="applyList"></div>
	</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>