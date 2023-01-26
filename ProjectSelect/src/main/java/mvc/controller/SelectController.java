package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.SelectDAO;
import mvc.model.SelectDTO;


@WebServlet("/SelectController")
public class SelectController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    static final int LISTCOUNT = 5;
    
    public SelectController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		System.out.println(command);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if(command.equals("/select1.do")) {	// 1페이지
			readSelect(request);
			RequestDispatcher rd = request.getRequestDispatcher("./select1.jsp");
			rd.forward(request, response);
		}else if(command.equals("/select2.do")) {	// 2페이지
			readSelect(request);
			RequestDispatcher rd = request.getRequestDispatcher("./select2.jsp");
			rd.forward(request, response);
		}else if(command.equals("/select3.do")) {	// 3페이지
			readSelect(request);
			RequestDispatcher rd = request.getRequestDispatcher("./select3.jsp");
			rd.forward(request, response);
		}else if(command.equals("/select4.do")) {	// 4페이지
			readSelect(request);
			RequestDispatcher rd = request.getRequestDispatcher("./select4.jsp");
			rd.forward(request, response);
		}else if(command.equals("/select5.do")) {	// 5페이지
			readSelect(request);
			RequestDispatcher rd = request.getRequestDispatcher("./select5.jsp");
			rd.forward(request, response);
		}else if(command.equals("/category.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./category.jsp");
			rd.forward(request, response);
		}else if(command.equals("/result.do")) {
			addDB(request);
			RequestDispatcher rd = request.getRequestDispatcher("./result.jsp");
			rd.forward(request, response);
		}
	}

	private void readSelect(HttpServletRequest request) {	// 각 페이지 이미지 랜덤
		SelectDTO list = new SelectDTO();
		list.setImg1(String.valueOf((int)(Math.random() * 100)));
		System.out.println("1 : " + list.getImg1());
		list.setImg2(String.valueOf((int)(Math.random() * 100)));
		System.out.println("2 : " + list.getImg2());
		list.setImg3(String.valueOf((int)(Math.random() * 100)));
		System.out.println("3 : " + list.getImg3());
		list.setImg4(String.valueOf((int)(Math.random() * 100)));
		System.out.println("4 : " + list.getImg4());
		list.setImg5(String.valueOf((int)(Math.random() * 100)));
		System.out.println("5 : " + list.getImg5());
		list.setImg6(String.valueOf((int)(Math.random() * 100)));
		System.out.println("6 : " + list.getImg6());
		request.setAttribute("list", list);
	}
	
	private void addDB(HttpServletRequest request) {
		SelectDAO dao = SelectDAO.getInstance();
		SelectDTO dto = new SelectDTO();
		
		dto.setSelect1(request.getParameter("no1"));
		dto.setSelect2(request.getParameter("no2"));
		dto.setSelect3(request.getParameter("no3"));
		dto.setSelect4(request.getParameter("no4"));
		dto.setSelect5(request.getParameter("no5"));
		dto.setResultCate(request.getParameter("resultCate"));
		
		dao.addResult(dto);
		
	}

}
