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

<script>
$(document).ready(function(){
	var mylat = 37.335887; 
	var mylng = 126.584063;
	var sendData = {"lat":mylat , "lng":mylng, "topn":-1};
		
		if (navigator.geolocation) { 
		   	navigator.geolocation.getCurrentPosition(
		    		function(position) {
		    						mylat = position.coords.latitude;
			    		    	mylng = position.coords.longitude;
			    		    	console.log(JSON.stringify({"lat":mylat , "lng":mylng}));
			    		    	
			    		    	$.ajax({ 
					    					url:"/shop",   
					    					type:"post",
					    					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					    					data: "MYKEY="+JSON.stringify(sendData),
					    					resultType:"json",
					    					success:function(resObject){
					    							console.log(resObject);		//객체
					    							var htmlStr = "";
					    				 			$.map(resObject, function(vv, idx){
					    					  		htmlStr += "<li class='media'>";
					    				 				htmlStr += "<img class='mr-3 rounded-circle' width='50' src='/cdir/" + vv.pname + "' alt='"+vv.pname+"'>";
					    				 				htmlStr += "<div class='media-body'>";
					    				 				htmlStr += "<div class='float-right'><small>" + vv.distance +  " km </small></div>";
					    				 				htmlStr += "<div class='media-title'><a href='/shop_detail?sseq="+vv.sseq+"'>" + vv.sname + "</a></div>";
					    				 				htmlStr += "<small>" + vv.sinfo + "</small>";
					    				 				htmlStr += "</div>";
					    				 				htmlStr += "</li>";
					    				 				htmlStr += "<hr>";
					    					  	});
					    				 			$(".list-unstyled.list-unstyled-border").html(htmlStr);
					    							
					    				 			//console.log(htmlStr);
				                    //$(".list-unstyled.list-unstyled-border").html(htmlStr);
					    					} //end of success
					    					
			    		    	}); 
			    					
		    		} , 
					  function(error) {
						  	alert('GPS를 지원하지 않습니다');
						  	return "";
					  } , 
					  {
					      enableHighAccuracy: false,
					      maximumAge: 0,
					      timeout: Infinity
					  }
		    );
	  } else {
	    	alert('GPS를 지원하지 않습니다');
	  } 
		
		
		
});    
</script>

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
            <div>맛집 전체목록 보기--- <a href="/shop_form.jsp">[글쓰기]</a></div>
          </h1>

          <div class="section-body">
            <div class="row">
             <div class="col-12">
              <div class="card">
               
                <div class="card-body" >             
 <ul class="list-unstyled list-unstyled-border">
 <!-- ----------- jQuery에서 완성될 html 영역 --------------------------------------------------------------------------------------- -->
 </ul>
                </div>
              </div>
            </div>
              
            </div> 	<!-- end of row -->
          </div>		<!-- end of section-body -->
          
        </section>
      </div>				<!-- end of main-content -->
      
    	<!-- 푸터 영역 -->
      <%@ include file="/include/footer.jsp" %>
      
    </div>
  </div>

  <!-- 스크립트 영역 -->
  <%@ include file="/include/script.jsp" %>
  
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyAK7EKtAH4NqVsXJCW4vkk2z61dnI9v1u8&amp;sensor=true"></script>
  
  <!-- &callback=initMap -->
  <script src="/modules/gmaps.js"></script>
  <script>
    // 단순히 지도만 나오는 형태
    /* 
    var simple_map = new GMaps({
      div: '#simple-map',
      lat: ${SVO.lat},
      lng: ${SVO.lng} 
    }); 
    */ 	
    
	 //지도 + 마커     map.js?...&callback=initMap
	//function initMap() {
   var locate = {lat: ${SVO.lat}, lng: ${SVO.lng}};
	 //div 태그에 맵 그리기	
   var map = new google.maps.Map(
       document.getElementById('simple-map'), 
       {zoom: 18, center: locate
   });
   //marker, center locate 표시하기
   var marker = new google.maps.Marker({
	   		position: locate, 
   	    map: map,
			  title:'${SVO.sname}'
	 });
  
  </script>
  
  
</body>
</html>