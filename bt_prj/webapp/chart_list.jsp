<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
	<script>
	$(document).ready(function(){
		  //$("#btn").click(function(){	
	    //});
	});    
	</script>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      <!-- 상단 검색창 -->
      <%@ include file="/include/top.jsp" %>
      
      <!-- 레프트 메뉴 영역 -->
      <%@ include file="/include/left.jsp" %>
      
      <!-- 컨텐츠 영영 -->
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>맛집 인기검색 순위 Chart.JS</div>
          </h1>

          <div class="section-body">
            <div class="card">
              <div class="card-body">
                <div class="alert alert-info mb-0">
                  We use 'Chart.JS' made by @chartjs. You can check the full documentation <a href="http://www.chartjs.org/">here</a>.
                </div>
              </div>
            </div>
            
            <div class="row">
		            	<!-- -------------------- Line Chart-------------------------- -->
		              <div class="col-12 col-md-6 col-lg-6">
		                <div class="card">
		                  <div class="card-header">
		                    <h4>Line Chart</h4>
		                  </div>
		                  <div class="card-body">
		                    <canvas id="myChart"></canvas>
		                  </div>
		                </div>
		              </div>
		              <!-- -------------------- Bar Chart-------------------------- -->
		              <div class="col-12 col-md-6 col-lg-6">
		                <div class="card">
		                  <div class="card-header">
		                    <h4>Bar Chart</h4>
		                  </div>
		                  <div class="card-body">
		                    <canvas id="myChart2"></canvas>
		                  </div>
		                </div>
		              </div>
            </div>
            
            
            <div class="row">
	            	<!-- --------------------Doughnut Chart-------------------------- -->
	              <div class="col-12 col-md-6 col-lg-6">
	                <div class="card">
	                  <div class="card-header">
	                    <h4>Doughnut Chart</h4>
	                  </div>
	                  <div class="card-body">
	                    <canvas id="myChart3"></canvas>
	                  </div>
	                </div>
	              </div>
	              <!-- --------------------Pie Chart-------------------------- -->
	              <div class="col-12 col-md-6 col-lg-6">
	                <div class="card">
	                  <div class="card-header">
	                    <h4>Pie Chart</h4>
	                  </div>
	                  <div class="card-body">
	                    <canvas id="myChart4"></canvas>
	                  </div>
	                </div>
	              </div>
            </div>
            
            
            
          </div>
        </section>
      </div>
      
      <!-- 푸터 영역 -->
      <%@ include file="/include/footer.jsp" %>
      
      
    </div>
  </div>

  <!-- 스크립트 영역 -->
  <%@ include file="/include/script.jsp" %>
  
  <script src="/modules/chart.min.js"></script>
  <script>
  	/* 
  	
  	var datas = [];
  	for() {
  		datas.push(get(i))
  	}	 */
  	
  	var dataArr = new Array(); //[460, 458, 330, 502, 430, 610, 488];
		<c:forEach var="v" items="${KEY_DATA}"> 
			dataArr.push("${v}");
		</c:forEach>
		
  	var arr = '${KEY_DATA}';
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        datasets: [{
          label: 'Statistics',
          data: dataArr,
          borderWidth: 2,
          backgroundColor: 'rgb(87,75,144)',
          borderColor: 'rgb(87,75,144)',
          borderWidth: 2.5,
          pointBackgroundColor: '#ffffff',
          pointRadius: 4
        }]
      },
      options: {
        legend: {
          display: false
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              stepSize: 150
            }
          }],
          xAxes: [{
            ticks: {
              display: false
            },
            gridLines: {
              display: false
            }
          }]
        },
      }
    });

    var ctx = document.getElementById("myChart2").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        datasets: [{
          label: 'Statistics',
          data: [460, 458, 330, 502, 430, 610, 488],
          borderWidth: 2,
          backgroundColor: 'rgb(87,75,144)',
          borderColor: 'rgb(87,75,144)',
          borderWidth: 2.5,
          pointBackgroundColor: '#ffffff',
          pointRadius: 4
        }]
      },
      options: {
        legend: {
          display: false
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              stepSize: 150
            }
          }],
          xAxes: [{
            ticks: {
              display: false
            },
            gridLines: {
              display: false
            }
          }]
        },
      }
    });

    var ctx = document.getElementById("myChart3").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        datasets: [{
          data: [
            80,
            50,
            40,
            30,
            20,
          ],
          backgroundColor: [
            '#574B90',
            '#28a745',
            '#ffc107',
            '#dc3545',
            '#343a40',
          ],
          label: 'Dataset 1'
        }],
        labels: [
          'Purple',
          'Green',
          'Yellow',
          'Red',
          'Black'
        ],
      },
      options: {
        responsive: true,
        legend: {
          position: 'bottom',
        },
      }
    });


   //서블릿에서 받아온 ArrayList를 script 배열로 변환 -----------
	var labelArr = new Array();
	var priceArr = new Array();
  var colorArr = new Array(); //[460, 458, 330, 502, 430, 610, 488];
	<c:forEach var="cvo" items="${KEY_DATA4}"> 
			labelArr.push("${cvo.label}");
			priceArr.push("${cvo.intVal}");
			colorArr.push("${cvo.color}");
	</c:forEach>
	//------------------------------------------------------
	
		var ctx = document.getElementById("myChart4").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'pie',
      data: {
        datasets: [{
          data: priceArr,  						//가격
          backgroundColor: colorArr, 	//색상
          label: 'Dataset 1'
        }],
        labels: labelArr,							//카테명
      },
      options: {
        responsive: true,
        legend: {
          position: 'bottom',
        },
      }
    });
  </script>
  
</body>
</html>