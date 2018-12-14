package com.biz.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shop_detail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String sseqStr = request.getParameter("sseq");
    	int sseq = Integer.parseInt(sseqStr);
    	ShopDAO dao = new ShopDAO();
    	
    	//상세정보   <샵1:이미지n:댓글n>
    	ShopVO svo = dao.selectShopInfo(sseq);
    	
    	
//    	svo.setPvolist(dao.selectShopPic(sseq));
//        svo.setRvolist(dao.replySelect(sseq));
    	
//    	//이미지 목록
//    	ArrayList<ShopPicVO> pvolist = dao.selectShopPic(sseq);
//    	if(pvolist.size()<=0) {
//    		ShopPicVO pvo = new ShopPicVO();
//    		pvo.setPname("000.png");
//    		pvolist.add(pvo);
//    	}
//    	svo.setPvolist(pvolist);
    	
    	request.setAttribute("SVO", svo);
    	
    	
//    	//상세정보
//    	ShopVO svo = dao.selectShopInfo(sseq);
//    	//이미지 목록
//    	ArrayList<ShopPicVO> pvolist = dao.selectShopPic(sseq);
//    	
//    	request.setAttribute("SVO", svo);
//    	request.setAttribute("LIST_PIC", pvolist);
    	
    	request.getRequestDispatcher("shop_detail.jsp").forward(request, response);
    			
	}


}
