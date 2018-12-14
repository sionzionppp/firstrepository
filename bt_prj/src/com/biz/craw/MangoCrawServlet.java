package com.biz.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/mango")
public class MangoCrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//http://localhost:8088/mango
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		MangoCraw soup = new MangoCraw();
		String url = "https://www.mangoplate.com/top_lists/1282_izakaya";
		String selector="ul.list-restaurants.type-single-big.top_list_restaurant_list li";
		//int res = soup.ytnCrawling(url, selector);
		
		ArrayList<MangoVO> list = soup.mangoCrawling(url, selector, -1);
		System.out.println(list.size() + "건 크롤링");
		//request.setAttribute("KEY_LIST" , list);
		//("ytn_list.jsp").forward(req,res);
		
		PrintWriter out  = response.getWriter();
		for(MangoVO yvo : list) {
			out.println("<a href='"+yvo.getUrl()+"'>" + yvo.getTitle()+"</a><br>");
			out.println("<img width='100' height='100' src='"+yvo.getImgsrc()+"'><br>");
		}
	}

	//AJAX REST : post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int topn = 4;
		if(request.getParameter("topn") != null)
			topn = Integer.parseInt(request.getParameter("topn"));
		
		response.setContentType("application/json; charset=UTF-8");
		MangoCraw soup = new MangoCraw();
		String url = "https://www.mangoplate.com/top_lists/1282_izakaya";
		String selector="ul.list-restaurants.type-single-big.top_list_restaurant_list li";
		//int res = soup.ytnCrawling(url, selector);
		
		ArrayList<MangoVO> list = soup.mangoCrawling(url, selector, topn);
		System.out.println(list.size() + "건 크롤링");
		
		//JSON 결과 데이터 보내기
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		PrintWriter out  = response.getWriter();
		out.println(jsonStr);
	}

	
}
