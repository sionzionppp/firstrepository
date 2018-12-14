package com.biz.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.common.MyFileRenamePolicy;
import com.biz.shop.ShopDAO;
import com.biz.shop.ShopPicVO;
import com.biz.shop.ShopVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String saveDirectory = "C:/uploads/profile";
		int maxPostSize = 1024 * 1024 * 10; //10M
		String encoding = "UTF-8";



//		//--------------------------------------------
//		// * c:/uploads 폴더에 파일카피 input/output Stream
//		// * 중복파일 rename
//		//--------------------------------------------
		FileRenamePolicy policy = new MyFileRenamePolicy();
		MultipartRequest mrequest = new MultipartRequest(request,saveDirectory,maxPostSize,encoding,policy);
		
		//-----------------------------------------------		
		// * shop_pic : cos.jar 를 이용한 단일 파일 업로드 처리 
		//-----------------------------------------------
		String origPname = mrequest.getOriginalFileName("pname");
		//3.File 객체를 이용한 파일명 
		File pfile = mrequest.getFile("pname");
		String filePname  = pfile.getName(); 	
				
		//중복 시 리네임된 파일명
		String sysPname = mrequest.getFilesystemName("pname");
		
		//파일크기
		long attachFileSize = pfile.length();		
		
		String attachFileContentType = mrequest.getContentType("pname");
		
		//파일 확장자 처리
		String attachFileExt = "jpg";
		if(origPname.lastIndexOf(".") != -1) {
			attachFileExt = origPname.substring(origPname.lastIndexOf(".")+1);
		}
		if(!attachFileExt.toUpperCase().equals("JPG") &&
				!attachFileExt.toUpperCase().equals("PNG") &&
				!attachFileExt.toUpperCase().equals("GIF")
		) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미지 첨부만 가능')</script>");
			//response.sendRedirect("shop_form.jsp");
		}
		
//		
//		//--------------------------------------------
//		// * users 관련 파라미터
//		//--------------------------------------------
		
		String userId = mrequest.getParameter("user_id");
		String userName = mrequest.getParameter("user_name");
		String userPw = mrequest.getParameter("user_pw");
		String userEmail = mrequest.getParameter("user_email");
		
		
		//--------------------------------------------
		// 2. DB저장작업
		//    USERS 테이블 정보 입력 : 1번 입력
		//--------------------------------------------
		MemberVO mvo = new MemberVO();
		mvo.setUserId(userId);
		mvo.setUserName(userName);
		mvo.setUserPw(userPw);
		mvo.setUserEmail(userEmail);
		mvo.setPpath(saveDirectory);
		mvo.setPname(origPname);
		mvo.setSysname(sysPname);
		mvo.setJoinPath("home");
		
		MemberDAO dao = new MemberDAO();
		int res = dao.insert(mvo);
		if(res > 0) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("404.jsp");
		}
	}

}
