package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mode", request.getParameter("mode"));
		request.getRequestDispatcher("/Pass.jsp").forward(request, response);
		
		// mode 매개변수 값을 저장한 다음 Pass.jsp로 포워드
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pass.jsp에서 넘어온 idx, mode, pass 값 저장
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		// 비밀번호 검증
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx, mode);
		dao.close();
		
		if(confirmed) {	// 일치하는 데이터가 있을 경우
			dao = new MVCBoardDAO();
			MVCBoardDTO dto = dao.selectView(idx);
			int result = dao.deletePost(idx);
			dao.close();
			if(result == 1) {	// 삭제에 성공했을 경우
				String saveFileName = dto.getSfile();
				FileUtil.deleteFile(request, "/uploads", saveFileName);
			}
			JSFunction.alertLocation(response, "삭제 완료", "./list.do");
		} else {
			JSFunction.alertBack(response, "비밀번호 불일치");	// 삭제 실패 시 이전페이지로
		}
		
	}
	
	

}
