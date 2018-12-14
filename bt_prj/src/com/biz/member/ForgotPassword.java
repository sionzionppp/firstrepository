package com.biz.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.biz.common.SendMailGoogle;

@WebServlet("/forgot")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String subject = "<BT_PRJ>변경된 비밀번호 알림";
		
		
		//랜덤 비밀번호 생성 -------------------------
		// equal to 0.0 and less than 1.0.
		// 0.0 <= rnum < 1.0  (0.0~0.9) 
		//!@_ _ _ _ 
		
		int rnum1 = (int)(Math.random() *10);
		int rnum2 = (int)(Math.random() *10);
		int rnum3 = (int)(Math.random() *10);
		int rnum4 = (int)(Math.random() *10);
		
		String newPw = "!@"+rnum1+rnum2+rnum3+rnum4;
		System.out.println(newPw);
		
		//DB pw변경 -------------------------
		//update member
		//set pw=newPw
		//where user_id='' and email="email"
		
		MemberVO vo = new MemberVO();
		vo.setUserEmail(email);
		vo.setUserPw(newPw);
    	
		MemberDAO dao = new MemberDAO();
    	int res = dao.changePassword(vo);
		if(res > 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<html>");
			buffer.append("<head>");
			buffer.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			buffer.append("<title>Insert title here</title>");
			buffer.append("</head>");
			buffer.append("<body>");
			buffer.append("<table width='200'>");
			buffer.append("<tr><td><img width='400' height='100' src='http://cdnweb01.wikitree.co.kr/webdata/editor/201707/07/img_20170707111552_dd184538.jpg'></tr>");
			buffer.append("<tr><td>변경된 비밀번호 : "+ newPw + "</td></tr>");
			buffer.append("</table>");
			buffer.append("</body>");
			buffer.append("</html>");
			
			SendMailGoogle g = new SendMailGoogle();
			g.sendMail(email , subject, buffer.toString());
		} else {
			response.sendRedirect("404.jsp");
		}
	}
}
