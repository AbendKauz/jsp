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
		
		if(command.equals("/BoardListAction.do")) {	// 기존에 등록된 글 목록 페이지 출력
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardWriteForm.do")) {	// 글 등록 페이지 출력
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./writeForm.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardWriteAction.do")) {	// 서버에 새로운 글을 등록함
			requestBoardWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}else if(command.equals("/BoardViewAction.do")) {	// 게시판 세부내용 출력
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardView.do");
			rd.forward(request, response);
		}else if(command.equals("/BoardView.do")) {	// 글 상세 페이지 연결
			RequestDispatcher rd = request.getRequestDispatcher("./view.jsp");
			rd.forward(request, response);
		}else if(command.equals("/BoardDeleteAction.do")) {	// 게시글 삭제
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			// 글 삭제 후 게시판 목록보기로 돌아감
			rd.forward(request, response);
		}else if(command.equals("/BoardUpdateAction.do")) {	// 게시글 수정
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardListAction.do");
			rd.forward(request, response);
		}
	}

	// 등록된 글 목록 가져오기
	private void requestBoardList(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		String items = request.getParameter("items");	// 검색 항목
		String text = request.getParameter("text");		// 검색 내용
		int total_record = dao.getListCount(items, text);	// 조건에 맞는 행 개수
		boardlist = dao.getBoardList(pageNum, limit, items, text);
		// 페이지 개수, 한 화면에 표시할 행 개수, 검색 항목, 검색 내용을 boardlist에 대입
		
		int total_page;	// 전체 페이지
		
		if(total_record % limit == 0) {
			// 전체 게시물 개수에서 표시될 개수를 나눈 나머지가 0과 같으면
			// ex) 한 페이지당 10개씩 게시물 표시, 전체 게시물이 100개
			total_page = total_record / limit;
			Math.floor(total_page);
		}else {
			// ex) 한 페이지당 10개씩 게시물 표시, 전체 게시물이 101개
			// 페이지 개수는 10개에서 1을 더한 11페이지가 되어야 함
			total_page = total_record / limit;
			Math.floor(total_page);
			total_page = total_page + 1;	// 다음페이지 추가
		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("boardlist", boardlist);
		
	}
	
	// 인증된 사용자명 가져오기
	public void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		BoardDAO dao = BoardDAO.getInstance();
		String name = dao.getLoginNameById(id);
		request.setAttribute("name", name);
	}
	
	// 새로운 글 등록하기
	public void requestBoardWrite(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setHit(0);	// 조회수 0 세팅
		board.setRegist_day(regist_day);	// 현재 날짜를 등록일자로 입력
		board.setIp(request.getRemoteAddr());	// ip주소는 현재 컴퓨터 ip주소 입력
		
		dao.insertBoard(board);
	}
	
	// 게시판 글 세부내용 보기
	public void requestBoardView(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num, pageNum);	// dao의 메소드를 이용해 상세내용 가져오기
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("board", board);
	}
	
	// 게시글 삭제
	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		dao.deleteBoard(num);
	}
	
	// 게시글 수정
	public void requestBoardUpdate(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		// 게시물 번호(num), 현재 페이지(pageNum)를 각각 대입
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO board = new BoardDTO();
		// DTO, DAO 객체를 각각 생성
		
		board.setNum(num);
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setHit(0);	// 조회수 0 세팅
		board.setRegist_day(regist_day);	// 현재 날짜를 등록일자로 입력
		board.setIp(request.getRemoteAddr());	// ip주소는 현재 컴퓨터 ip주소 입력
		
		dao.updateBoard(board);
	}
	
}
