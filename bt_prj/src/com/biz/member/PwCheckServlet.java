package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/pwcheck")
public class PwCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//비밀번호 체크 -------------------------------
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getMethod();
		System.out.println("PwCheckServlet 전송방식get/post:" + method);
		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("SESS_ID") == null) {
			response.sendRedirect("login.jsp");
		} else {
			String sess_id = session.getAttribute("SESS_ID").toString();
			String userPw = request.getParameter("userpw");
			
			MemberVO mvo = new MemberVO();
			mvo.setUserId(sess_id);
			mvo.setUserPw(userPw);
			
			MemberDAO mdao = new MemberDAO();
			mvo = mdao.select(mvo);		//로그인과 상동(id/pw)
			if(mvo.getUserGubun() == null || mvo.getUserGubun().equals(""))  {
				response.sendRedirect("pwcheck.jsp");
			} else {
				//수정()/삭제(del)
				String mode = request.getParameter("mode");
				//회원탈퇴 처리
				if(mode.equals("del")) {
					response.sendRedirect("/del");
				//회원정보 수정 처리
				} else {
					response.sendRedirect("/edit");					
				}
			}
		}
	}

}
