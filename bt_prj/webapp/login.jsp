<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

 <!-- 헤더 css / jquery cdn -->
 <%@ include file="/include/header.jsp" %>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
$(document).ready(function(){
	  //$("#btn").click(function(){	
    //});
});    
</script>
</head>

<body>
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4">
            <div class="login-brand">
              Member Register
            </div>

            <div class="card card-primary">
              <div class="card-header"><h4>Login</h4></div>
              <div class="card-body">


			   <!---------------- 로그인 폼 ------------------>
                <form method="POST" action="/login" class="needs-validation">
                  <div class="form-group">
                    <label for="UserID">UserID</label>
                    <input id="userid" type="text" class="form-control" name="userid" tabindex="1" required autofocus>
                    <div class="invalid-feedback">
                      Please fill in your ID
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="userpw" class="d-block">Password
                      <div class="float-right">
                        <a href="forgot.jsp">
                          Forgot Password?
                        </a>
                      </div>
                    </label>
                    <input id="userpw" type="password" class="form-control" name="userpw" tabindex="2" required>
                    <div class="invalid-feedback">
                      Please fill in your password
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" name="remember" class="custom-control-input" tabindex="3" id="remember-me">
                      <label class="custom-control-label" for="remember-me">Remember Me</label>
                    </div>
                  </div>

                  <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" tabindex="4">
                      Login
                    </button>
                  </div>
                </form>
                
                
                
                
               
              </div>
            </div>
            <div class="mt-5 text-muted text-center">
              Don't have an account? <a href="register.jsp">Create One</a>
            </div>
            <div class="simple-footer">
              Copyright &copy; Stisla 2018
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  
 <!-- 스크립트 영역 -->
<%@ include file="/include/script.jsp" %>

</body>
</html>