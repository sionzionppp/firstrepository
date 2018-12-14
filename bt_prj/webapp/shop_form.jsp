<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
<script>
$(document).ready(function(){
	
	//jQuery 지원 : 첨부파일 미리보기 ---------------------
	$("#files").on("change", myFilePreviewFunc);
	//$("#pname").change(function(){
	function myFilePreviewFunc(e) {
			$("#prev-img-div").empty();
			var files = e.target.files;  			//[object FileList]
			//FileList into an array 
			//var fileArr = Array.prototype.slice.call(files);			
			var fileArr = Array.from(files); //[object File],[object File],[object File]
				
		/* 	if(fileArr.length > 3) {  //files.length
					alert("이미지 첨부는 최대 3개만 가능합니다.");
					$("#files").val("");
					return false;
			} */
			
			var fileSize = 0;
			fileArr.forEach(function(f) {
					fileSize += f.size;
			});
			if(fileSize > 10*1024*1024) {
					alert("이미지 첨부는 최대 10MB만 가능합니다.");
					$("#files").val("");
					return false;
			}
			
			fileArr.forEach(function(f) {
					
				
					if(!f.type.match("image.*")) {
							alert("이미지 첨부만 가능합니다.");
							$("#files").val("");
							return false;
					} 
					var fname = f.name;
					var reader = new FileReader();
					var htmlStr = "";
					reader.onload = function(e) {
							htmlStr += "<td><img src='"+e.target.result+"' style='height:80px;width:80px;'><br> " + fname+" </td>";
							$("#prev-img-div").append(htmlStr);
							//alert(htmlStr)
					}
					//reader.onload = function(e) { 
					//	$("#prev-img").attr("src", e.target.result); 
					//} 
					reader.readAsDataURL(f); 
			}); //end of forEach
	}
	
		/* $("#files").change(function(){
				var plistStr = "";
				var input = document.getElementById("files");
				for (var i = 0; i < input.files.length; i++) {
						plistStr += input.files[i].name + "<br>";
				}
				$("#prev-img-div").html(plistStr);
		}); */
	
	
	  $("#saveButton").click(function(){
		  	console.log($("#sinfo").val());
		  	$("#sform").submit();
    });
});    
</script>

<style>
.pac-container{
  z-index: 1500 !important;
}
</style>
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
            <div>맛집 등록하기 --- <a href="/shop_form.jsp">[글쓰기]</a></div>
          </h1>

          <div class="section-body">
            
             <div class="row">
             
              <div class="col-12 col-md-6 col-lg-6">
              
              <!--  ------------------------------------------------- -->
              <!--  ------------------등록폼--------------- -->
              <!--  ------------------------------------------------- -->
              <form name="f" id="sform" method="post" 
              action="/shop_insert"
              class="needs-validation"
              enctype="multipart/form-data"
              >
                <div class="card">
                  <div class="card-header">
                    <h4>Shop Register Form</h4>
                  </div>
                  <div class="card-body">
	                  <div class="alert alert-primary">
	                      For maps, we use <a href="http://maps.google.com/" target="_blank">Google Map</a> created.
	                  </div>
                  
                    <div class="form-group">
                      <label>맛집 상호명</label>
                      <input type="text" name="sname" class="form-control" required>
                      <div class="invalid-feedback">
                        	상호명을 입력하세요.
                      </div>
                    </div>
                    <div class="form-group">
                      <label>사진업로드</label>
                      <input type="file" name="files[]" id="files" multiple class="form-control" required>
                      <div id="prev-img-div">
                      	<!-- 첨부파일 목록 보여질 영역 -->
                      </div>
                      <div class="invalid-feedback">
                        	대표 사진 한장을 등록해주세요.
                      </div>
                    </div>
                   
                    
                    <div class="form-group">
                      <label>※선택 : 맛집 지도위치 </label>
                      <a href="http://maps.google.com/" target="_blank">Google Map</a>                    
<!-- Maps JavaScript API -->                      
<!-- place API -->
<!-- geocode API xx -->
<div id="locationField">
  <input type="text" class="form-control" name="placename" id="placename" 
  placeholder="검색할 상호명을 입력하세요" >
</div>
<input type="text" class="form-control" readonly name="lat" id="lat" />
<input type="text" class="form-control" readonly name="lng" id="lng" />
                    </div>
                    
                    <div class="form-group">
                      <label>맛집 소개글</label>
                      <textarea id="sinfo" name="sinfo" class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button id="saveButton" class="btn btn-primary">Save Shop Info</button>
                  </div>
                </div>
              </form>
           
							<!--  ------------------------------------------------- -->
							<!--  ------------------등록폼--------------- -->
							<!--  ------------------------------------------------- -->
               </div>
               
               
               
               <div class="col-12 col-md-6 col-lg-6">
               			 <!--  -------  맵 지도   ----------- -->
	                   <div id="simple-map" style="height:450px;"></div>
	                   <!--  ------- 맵 지도    ----------- -->
	             </div>      
              
            </div>	<!-- end of rows -->
             
             
              
            
          </div>		<!-- end of section-body -->
          
        </section>
      </div>				
      <!-- end of main-content -->
      
    	<!-- 푸터 영역 -->
      <%@ include file="/include/footer.jsp" %>
      
    </div>
  </div>

  <!-- 스크립트 영역 -->
  <%@ include file="/include/script.jsp" %>
  
  <script src="/modules/summernote/summernote-lite.js"></script>
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyBmQeifzH8sT1hy-I5-G_vD5tOn9GsPbJA&libraries=places&callback=initAutocomplete" async defer></script>
  <script src="/modules/gmaps.js"></script>
  <script>
  //https://fatc.club/2017/06/05/1949
  //https://developers.google.com/maps/documentation/javascript/examples/geocoding-simple?hl=ko
  var placename = document.getElementById('placename');
  var autocompleteResult;
  function initAutocomplete() {
	    // type : address ,establishment, geocode
	    autocompleteResult = 
	    	new google.maps.places.Autocomplete(
	    			placename, 
	    			{types: ['establishment']}		
	    	);
    	
    	autocompleteResult.addListener(
    			'place_changed', 
    			function() {
	    				var choicePlace = autocompleteResult.getPlace();
	    				var latVal = choicePlace.geometry.location.lat();
	    				var lngVal = choicePlace.geometry.location.lng();
	    	      
	    				//-----------입력폼에 위도/경도 값 셋팅
	    				document.getElementById("lat").value = latVal;
	    	      document.getElementById("lng").value = lngVal;
	    	      
	    	      //$("#lat").val(latVal);
	    	      
	    	      //-----------우측 영역에 선택장소 지도 그리기 --------------
	    	      var locate = {lat:latVal, lng:lngVal};
	    	  	  //div 태그에 맵 그리기	
	    	      var resultMap = new google.maps.Map(
	    	         document.getElementById('simple-map'), 
	    	         {zoom: 18, center: locate}
	    	      );
	    	      //marker, center locate 표시하기
	    	    	var marker = new google.maps.Marker(
	    	    		  {position:locate , map:resultMap}
	    	    	);
	    	     
	    	      
    			}
    	);
  }
 
  </script>
  
  
</body>
</html>