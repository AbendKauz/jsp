package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DAO 객체 생성
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// 뷰 파일에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		// 전달받은 매개변수 중 검색 단어가 있다면 map에 해당 값 저장
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map);	// 게시물의 개수 저장
		
		// 페이징 설정 상수값을 가져와서 페이지당 게시물 수와 블록당 페이지 수를 구함
		// 이 후 현재 페이지를 확인한 후 목록에 출력할 게시물 범위 계산해서 collection map에 추가함
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		// 현재 페이지 확인
		int pageNum = 1;	// 초기값 1로 설정
		String pageTemp = request.getParameter("pageNum");
		
		if(pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		// 목록에 출력할 게시물 범위 확인
		int start = (pageNum - 1) * pageSize + 1;	// 첫 게시물 번호
		int end = pageNum * pageSize;	// 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		
		// 게시물 목록 받기
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		dao.close();	// DB 연결 닫기
		
		// 뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "./list.do");
		
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		request.setAttribute("boardLists", boardLists);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/List.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
