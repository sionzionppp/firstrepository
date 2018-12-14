<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
  <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
<script>
$(document).ready(function(){
				//크롤링 목록에서 버튼 클릭 시 새창으로 상세보기
				$(document).on("click",".btn.btn-primary.btn-action.mr-1",function(){
						var openURL = $(this).attr("title");
						window.open(openURL, '_blank', 'height=570,width=520,scrollbars=yes,status=yes');
				});
						
				//viewAll이 클릭되어지면 전체 크롤링(-1) 해오기	
				$("#carwViewAll").click(function(){
						craw(-1);	
				});
		
				
				//-------------------- 페이지 로드 시 바로 실행 -------------------
				currentPage = getParam("currentPage");
				if(currentPage == "") currentPage = 1;
				console.log("-" + currentPage + "-");
				
				//페이지 로드 시 등록된 shop 정보  불러오기
				goPaging(currentPage);
				
				//페이지가 로드 시 크롤링 4개 해오기	
				craw(4);	
}); 



//-----------------------등록된 shop 정보 불러오기 -----------------------------
function goPaging(currentPage) {
	//var topn = -1;		// -1:전체 
	var topn = 4;				// 최근 4개

	if (navigator.geolocation) { 
	   	navigator.geolocation.getCurrentPosition(
	    		function(position) {
		    					mylat = position.coords.latitude;
	    		    		mylng = position.coords.longitude;
	    		    		
	    		    		var sendData = {"lat":mylat , "lng":mylng, "topn":topn, "currentPage":currentPage};
	    		    		listDefaultLatLng(sendData);
		    					
	    		} , 
				  function(error) {
		    			//alert('위치 정보를 받는 중 에러가 발생했습니다. 시청 위경도 기준으로 거리계산합니다. ');
					  	mylat = 37.335887; 
							mylng = 126.584063;
							sendData = {"lat":mylat , "lng":mylng, "topn":topn,  "currentPage":currentPage};		
		    			listDefaultLatLng(sendData);
				  } , 
				  {
				      enableHighAccuracy: false,
				      maximumAge: 0,
				      timeout: Infinity
				  }
	    );
  } else {
	  	alert('GPS를 지원하지 않습니다 . 시청 위경도 기준으로 거리계산합니다.');
	  	mylat = 37.335887; 
			mylng = 126.584063;
			sendData = {"lat":mylat , "lng":mylng, "topn":topn,  "currentPage":currentPage};		
			listDefaultLatLng(sendData);
  } 
}

//url 에서 parameter 추출
function getParam(sname) {
		var params = location.search.substr(location.search.indexOf("?") + 1);
		var sval = "";
		params = params.split("&");
		for (var i = 0; i < params.length; i++) {
		temp = params[i].split("=");
		if ([temp[0]] == sname) { sval = temp[1]; }
		}
		return sval;
}

//위경도 좌표에 해당하는 shop 목록 가져오기 
function listDefaultLatLng(sendData) {
		$.ajax({ 
			url:"/shop",   
			type:"post",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: "MYKEY="+JSON.stringify(sendData),
			resultType:"json",
			success:function(resMap){
					console.log(resMap);		//객체
					var htmlStr = "";
					var pagingStr = resMap.MAP_PAGING;
					var maplist 	= resMap.MAP_LIST;
		 			$.map(maplist, function(vv, idx){
			  		htmlStr += "<li class='media'>";
		 				htmlStr += "<img class='mr-3 rounded-circle' width='50' src='/cdir/" + vv.shopPicVO.pname + "' alt='"+vv.shopPicVO.pname+"'>";
		 				htmlStr += "<div class='media-body'>";
		 				htmlStr += "<div class='float-right'><small>" + vv.distance +  " km </small></div>";
		 				htmlStr += "<div class='media-title'><a href='/shop_detail?sseq="+vv.sseq+"'>" + vv.sname + "</a></div>";
		 				htmlStr += "<small>" + vv.sinfo + "</small>";
		 				htmlStr += "</div>";
		 				htmlStr += "</li>";
			  	});
		 			htmlStr += pagingStr;
		 			
		 			//div는 남겨두고 기존 내용만 지우기
				  $(".list-unstyled.list-unstyled-border").empty();
		 			$(".list-unstyled.list-unstyled-border").html(htmlStr);
			} //end of success
		}); 
}

//-----------------------망코싸이트 크롤링-----------------------------
function craw(topn) {
			//alert(topn)
			$.ajax({ 
				url:"/mango",   
				type:"post",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: "topn="+topn, //크롤링해올 갯수 ---------------
				resultType:"json",
				success:function(resObject){
						console.log(resObject);		//객체
						var mangoHtmlStr = "";
			 			$.map(resObject, function(vv, idx){
				 				mangoHtmlStr += "<tr>";
				 				mangoHtmlStr += "<td>"+vv.title
				 				mangoHtmlStr += "<div class='table-links'>";
				 				mangoHtmlStr += "<a target='_blank' href='"+vv.url+"'>"+vv.content.substring(0, 10)+"...</a>";
				 				mangoHtmlStr += "</div>";
				 				mangoHtmlStr += "</td>"; 
				 				mangoHtmlStr += "<td>";
				 				mangoHtmlStr += "<a target='_blank' href='"+vv.url+"'><img src='"+vv.imgsrc+"' alt='avatar' width='30' class='rounded-circle mr-1'>"+vv.addr+"</a>";
				 				mangoHtmlStr += "</td>";
				 				mangoHtmlStr += "<td>";
				 				mangoHtmlStr += "<a class='btn btn-primary btn-action mr-1' data-toggle='tooltip' title='"+vv.url+"'><i class='ion ion-edit'></i></a>";
								//mangoHtmlStr += "<a class='btn btn-danger btn-action' data-toggle='tooltip' title='Delete'><i class='ion ion-trash-b'></i></a>";
								mangoHtmlStr += "</td>";
								mangoHtmlStr += "</tr>";
			 	  	});
			 			
			 			$("#crawTbody").empty();
			 			$("#crawTbody").html(mangoHtmlStr);
				} //end of success
			}); 
}	

</script>

   		<!-- refrence web : https:developer.mozilla.org/ko/docs/WebAPI/Using_geolocation -->
		 
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
            <div>
            Dashboard
           이름:${sessionScope.SESS_NAME}<br>
           <%
           Cookie[] carr = request.getCookies();
           if(carr != null) {
	            for(int i=0; i<carr.length; i++) {
	        	   Cookie c = carr[i];
	        	   out.println(c.getName() + "," + c.getValue()+"<br>");
	            }
           }
           %>
            
            </div>
          </h1>
          <div class="row">
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-primary">
                  <i class="ion ion-person"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Total Admin</h4>
                  </div>
                  <div class="card-body">
                    10
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-danger">
                  <i class="ion ion-ios-paper-outline"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>News</h4>
                  </div>
                  <div class="card-body">
                    42
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-warning">
                  <i class="ion ion-paper-airplane"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Reports</h4>
                  </div>
                  <div class="card-body">
                    1,201
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-success">
                  <i class="ion ion-record"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Online Users</h4>
                  </div>
                  <div class="card-body">
                    47
                  </div>
                </div>
              </div>
            </div>                  
          </div>
          <div class="row">
            <div class="col-lg-8 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <div class="float-right">
                    <div class="btn-group">
                      <a href="#" class="btn active">Week</a>
                      <a href="#" class="btn">Month</a>
                      <a href="#" class="btn">Year</a>
                    </div>
                  </div>
                  <h4>Statistics</h4>
                </div>
                <div class="card-body">
                  <canvas id="myChart" height="158"></canvas>
                  <div class="statistic-details mt-sm-4">
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span> 7%</small>
                      <div class="detail-value">$243</div>
                      <div class="detail-name">Today's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-danger"><i class="ion-arrow-down-b"></i></span> 23%</small>
                      <div class="detail-value">$2,902</div>
                      <div class="detail-name">This Week's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span>9%</small>
                      <div class="detail-value">$12,821</div>
                      <div class="detail-name">This Month's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span> 19%</small>
                      <div class="detail-value">$92,142</div>
                      <div class="detail-name">This Year's Sales</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <h4>Recent Activities</h4>
                </div>
                <div class="card-body" >             
 <ul class="list-unstyled list-unstyled-border">
 
 <!-- ----------- jQuery에서 완성될 html 영역 --------------------------------------------------------------------------------------- -->
 
                    
 </ul>
                  <div class="text-center">
                    <a href="/shop_list.jsp" class="btn btn-primary btn-round">
                      View All
                    </a>
                  </div>
                  
                  
                  
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5 col-md-12 col-12 col-sm-12">
              <form method="post" class="needs-validation" novalidate="">
                <div class="card">
                  <div class="card-header">
                    <h4>Quick Draft</h4>
                  </div>
                  <div class="card-body">
                    <div class="form-group">
                      <label>Title</label>
                      <input type="text" name="title" class="form-control" required>
                      <div class="invalid-feedback">
                        Please fill in the title
                      </div>
                    </div>
                    <div class="form-group">
                      <label>Content</label>
                      <textarea class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button class="btn btn-primary">Save Draft</button>
                  </div>
                </div>
              </form>
            </div>
            
            
<!-- -------------맛집 크롤링  결과 ---------- -->
            <div class="col-lg-7 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <div class="float-right">
                    <a href="#" id="carwViewAll" class="btn btn-primary">View All</a>
                  </div>
                  <h4>맛집 추천 TOP</h4>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>상호명</th>
                          <th>주소</th>
                          <th>새창보기</th>
                        </tr>
                      </thead>
                      <tbody id="crawTbody"> 
                      
                                     
<!-- <script> AJAX 통신결과 뿌려질 영역 -->
                      
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
<!-- -------------맛집 크롤링  결과 ---------- -->
            
            
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
  <script src="/modules/summernote/summernote-lite.js"></script>
  <script>
  var ctx = document.getElementById("myChart").getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'line',
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
          gridLines: {
            display: false
          }
        }]
      },
    }
  });
  </script>
</body>
</html>