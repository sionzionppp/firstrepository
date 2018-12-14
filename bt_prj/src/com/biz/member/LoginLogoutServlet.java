package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/login")
public class LoginLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//로그아웃 ----------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout");
		HttpSession session = request.getSession();
		session.invalidate(); //세션 지워짐
		session.setMaxInactiveInterval(0); //최대 세션이 활성화 되는 시간 0초
		response.sendRedirect("index.jsp");
	}
	
	//로그인 ------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO mvo = new MemberVO();
		mvo.setUserId(userid);
		mvo.setUserPw(userpw);
		
		mvo = dao.select(mvo);
		
		if(mvo.getUserGubun()==null || mvo.getUserGubun().equals("")) {
			response.sendRedirect("404.jsp");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("SESS_ID", mvo.getUserId());
			session.setAttribute("SESS_NAME", mvo.getUserName());
			session.setAttribute("SESS_GUBUN", mvo.getUserGubun());
			
			

			
//			Cookie cname = new Cookie("COOKIE_NAME",mvo.getUserName());
//			Cookie cgubun = new Cookie("COOKIE_GUBUN",mvo.getUserGubun());
//			
//			cname.setDomain("fetric.com");
//			cgubun.setDomain("fetric.com");
//			
//			cname.setMaxAge(60*30);
//			cgubun.setMaxAge(60*30);
//			
//			response.addCookie(cname);
//			response.addCookie(cgubun);
			
			
			if(mvo.getUserGubun().equals("u")) {
				//response.sendRedirect("index.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
				
			}else if(mvo.getUserGubun().equals("a")) {
				response.sendRedirect("admin/index.jsp");
				//request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	
	}

}
