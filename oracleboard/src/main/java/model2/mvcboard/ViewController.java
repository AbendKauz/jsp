package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MVCBoardDAO dao = new MVCBoardDAO();	// dao 객체 생성
		String idx = request.getParameter("idx");	// 게시물 번호 가져오기
		dao.updateVisitCount(idx);	// 게시물 조회수 증가
		MVCBoardDTO dto = dao.selectView(idx);	// 세부정보 보기 호출
		dao.close();	// db 연결 닫기
		
		// 게시물 내용을 dto 객체에 저장 후 view 파일(게시물 세부정보)로 forward 시킴
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/View.jsp").forward(request, response);
	}

}
