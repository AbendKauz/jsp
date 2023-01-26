package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import mvc.model.BoardDTO;
import mvc.model.BoardDAO;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    static final int LISTCOUNT = 5;
    
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if(command.equals("/BoardListAction.do")) {	// ������ ��ϵ� �� ��� ������ ���
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardWriteForm.do")) {	// �� ��� ������ ���
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./writeForm.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardWriteAction.do")) {	// ������ ���ο� ���� �����
			requestBoardWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}else if(command.equals("/BoardViewAction.do")) {	// �Խ��� ���γ��� ���
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardView.do");
			rd.forward(request, response);
		}else if(command.equals("/BoardView.do")) {	// �� �� ������ ����
			RequestDispatcher rd = request.getRequestDispatcher("./view.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardDeleteAction.do")) {	// �Խñ� ����
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			// �� ���� �� �Խ��� ��Ϻ���� ���ư�
			rd.forward(request, response);
		}else if(command.equals("/BoardUpdateAction.do")) {	// �Խñ� ����
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
	}

	// ��ϵ� �� ��� ��������
	private void requestBoardList(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		String items = request.getParameter("items");	// �˻� �׸�
		String text = request.getParameter("text");		// �˻� ����
		int total_record = dao.getListCount(items, text);	// ���ǿ� �´� �� ����
		boardlist = dao.getBoardList(pageNum, limit, items, text);
		// ������ ����, �� ȭ�鿡 ǥ���� �� ����, �˻� �׸�, �˻� ������ boardlist�� ����
		
		int total_page;	// ��ü ������
		
		if(total_record % limit == 0) {
			// ��ü �Խù� �������� ǥ�õ� ������ ���� �������� 0�� ������
			// ex) �� �������� 10���� �Խù� ǥ��, ��ü �Խù��� 100��
			total_page = total_record / limit;
			Math.floor(total_page);
		}else {
			// ex) �� �������� 10���� �Խù� ǥ��, ��ü �Խù��� 101��
			// ������ ������ 10������ 1�� ���� 11�������� �Ǿ�� ��
			total_page = total_record / limit;
			Math.floor(total_page);
			total_page = total_page + 1;	// ���������� �߰�
		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("boardlist", boardlist);
		
	}
	
	// ������ ����ڸ� ��������
	public void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		BoardDAO dao = BoardDAO.getInstance();
		String name = dao.getLoginNameById(id);
		request.setAttribute("name", name);
	}
	
	// ���ο� �� ����ϱ�
	public void requestBoardWrite(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setHit(0);	// ��ȸ�� 0 ����
		board.setRegist_day(regist_day);	// ���� ��¥�� ������ڷ� �Է�
		board.setIp(request.getRemoteAddr());	// ip�ּҴ� ���� ��ǻ�� ip�ּ� �Է�
		
		dao.insertBoard(board);
	}
	
	// �Խ��� �� ���γ��� ����
	public void requestBoardView(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num, pageNum);	// dao�� �޼ҵ带 �̿��� �󼼳��� ��������
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("board", board);
	}
	
	// �Խñ� ����
	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		dao.deleteBoard(num);
	}
	
	// �Խñ� ����
	public void requestBoardUpdate(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		// �Խù� ��ȣ(num), ���� ������(pageNum)�� ���� ����
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		// DTO, DAO ��ü�� ���� ����
		
		board.setNum(num);
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setHit(0);	// ��ȸ�� 0 ����
		board.setRegist_day(regist_day);	// ���� ��¥�� ������ڷ� �Է�
		board.setIp(request.getRemoteAddr());	// ip�ּҴ� ���� ��ǻ�� ip�ּ� �Է�
		
		dao.updateBoard(board);
	}
	
}
