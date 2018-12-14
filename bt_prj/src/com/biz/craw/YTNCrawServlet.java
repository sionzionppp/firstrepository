package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class YTNCrawServlet
 */
@WebServlet("/ytn")
public class YTNCrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html; charset=UTF-8");
			YTNCraw soup = new YTNCraw();
			String url = "https://www.ytn.co.kr/photo/sports_list_9902.html";
			String selector="div#ytn_list_v2014 dl.photo_list";
			//int res = soup.ytnCrawling(url, selector);
			
			ArrayList<YTNVO> list = soup.ytnCrawling(url, selector);
			System.out.println(list.size() + "건 크롤링");
			//request.setAttribute("KEY_LIST" , list);
			//("ytn_list.jsp").forward(request,response);
			
			
			PrintWriter out = response.getWriter();
			for(YTNVO yvo : list) {
				out.println("<a href ='" + yvo.getUrl() + "'>" + yvo.getTitle() + "</a><br>");			
				out.println("<img width='100' height='100' src='"+ yvo.getImgsrc() +"'>");
			}
	}


}
