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
		// DAO ��ü ����
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// �� ���Ͽ� ������ �Ű����� ����� �� ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		// ���޹��� �Ű����� �� �˻� �ܾ �ִٸ� map�� �ش� �� ����
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.selectCount(map);	// �Խù��� ���� ����
		
		// ����¡ ���� ������� �����ͼ� �������� �Խù� ���� ��ϴ� ������ ���� ����
		// �� �� ���� �������� Ȯ���� �� ��Ͽ� ����� �Խù� ���� ����ؼ� collection map�� �߰���
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		// ���� ������ Ȯ��
		int pageNum = 1;	// �ʱⰪ 1�� ����
		String pageTemp = request.getParameter("pageNum");
		
		if(pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		// ��Ͽ� ����� �Խù� ���� Ȯ��
		int start = (pageNum - 1) * pageSize + 1;	// ù �Խù� ��ȣ
		int end = pageNum * pageSize;	// ������ �Խù� ��ȣ
		map.put("start", start);
		map.put("end", end);
		
		// �Խù� ��� �ޱ�
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		dao.close();	// DB ���� �ݱ�
		
		// �信 ������ �Ű����� �߰�
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
