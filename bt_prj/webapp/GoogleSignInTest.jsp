<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE> <!-- HTML5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-client_id" content="645381907717-hslugp80bvm9fc9ne6tvld1vd41iil8m.apps.googleusercontent.com">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}
</script>
<script>
$(document).ready(function(){
    //$("#checkBtn").click(function(){
		 	    
	//});
    
  
    
});
</script>
</head>
<body>

<div id="googleButton" class="g-signin2" data-onsuccess="onSignIn"></div>

<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>


</html>