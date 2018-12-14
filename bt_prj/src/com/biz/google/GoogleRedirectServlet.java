package com.biz.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoogleSignInServlet
 */
@WebServlet("/google_redirect")
public class GoogleRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		https://accounts.google.com/o/oauth2/v2/auth //로그인 요청 여기다 해라
		
//         --------------- google auth request 요청 예 -----------------------------------------
//			https://accounts.google.com/o/oauth2/v2/auth?
//			scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly&
//			access_type=offline&
//			include_granted_scopes=true&
//			state=state_parameter_passthrough_value&
//			redirect_uri=http%3A%2F%2Foauth2.example.com%2Fcallback&
//			response_type=code&
//			client_id=client_id
			
			//String urlString = "http://....."; 한줄로 쭉 이어서 붙이는 방법
			
			String googleURL = "https://accounts.google.com/o/oauth2/v2/auth?";
			String client_id = "645381907717-hslugp80bvm9fc9ne6tvld1vd41iil8m.apps.googleusercontent.com";
			String access_type = "offline";
			String response_type = "code";
			
			//POST 전송일 경우
			String redirect_uri = "http://localhost:8083/google_redirect";
			String scope = "https://www.googleapis.com/auth/calendar.readonly";
			
			//GET 전송일 경우 인코딩
			//String redirect_uri = "http%3a%2f%2flocalhost%3a8083%2fgoogle_redirect";
			//String scope = "https%3a%2f%2fwww.googleapis.com%2fauth%2fcalendar.readonly";
			//byte[] postDataBytes = postData.toString().getBytes("UTF-8"); 
			
			
			Map<String,Object> map = new HashMap<>();
			map.put("scope", scope);
			map.put("access_type", access_type);
			map.put("include_granted_scopes", true);
			map.put("redirect_uri", redirect_uri);
			map.put("response_type", response_type);
			map.put("client_id", client_id);

			//map 데이터를 꺼내 k=v&k2=m& 문장 생성
	        StringBuffer buffer = new StringBuffer();
	        for (Map.Entry<String,Object> keyval : map.entrySet()) {
	            if (buffer.length() != 0) buffer.append('&');
	            buffer.append(URLEncoder.encode(keyval.getKey(), "UTF-8"));
	            buffer.append('=');
	            buffer.append(URLEncoder.encode(String.valueOf(keyval.getValue()), "UTF-8"));
	        }
			
	        //StringBuffer --> String
	        //String --> byte[]
			byte[] postDataBytes = buffer.toString().getBytes();	
				
			//URL url = new URL(urlString); GET 방식에서 쓰는 방법
			URL url = new URL(googleURL); 
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
		    connection.setDoOutput(true); 
		    connection.setInstanceFollowRedirects(false); 
		    connection.setRequestMethod("POST"); 
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		    connection.setRequestProperty("charset", "utf-8");
		    connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		    connection.connect();
		    //http body에 write == POST
		    connection.getOutputStream().write(postDataBytes); 
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		    String line = "";
		    if((line = in.readLine()) != null){
	            System.out.print(line); //동의서 화면<html> 출력
		    }
		    
		    //code<동의서>
		    //apikey
		    //securitykey
		    //client_id
		    //----> access_token
		    
		    
		    
		    
	}

}
