package com.biz.chart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chart")
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int[] arr = {14,51,84,52,14,86,35};
		
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(44);
		arr.add(34);
		arr.add(98);
		arr.add(89);
		arr.add(63);
		arr.add(26);
		arr.add(79);
		request.setAttribute("KEY_DATA", arr);
		
		ArrayList<ChartVO> clist = new ArrayList<ChartVO>();
		String[] colors = {"#574B90","#28a745","#ffc107","#dc3545","#343a40","#574B90","#28a745","#ffc107","#dc3545","#343a40"};
		for(int i=1; i<=5; i++) {
			ChartVO vo = new ChartVO();
			vo.setLabel("가전"+i);
			vo.setIntVal(45400*i);
			vo.setColor(colors[i]);
			clist.add(vo);
		}
		request.setAttribute("KEY_DATA4", clist);
		
		request.getRequestDispatcher("chart_list.jsp").forward(request, response);  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
