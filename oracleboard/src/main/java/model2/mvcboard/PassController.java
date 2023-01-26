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
		
		// mode �Ű����� ���� ������ ���� Pass.jsp�� ������
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pass.jsp���� �Ѿ�� idx, mode, pass �� ����
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		// ��й�ȣ ����
		MVCBoardDAO dao = new MVCBoardDAO();
		boolean confirmed = dao.confirmPassword(pass, idx, mode);
		dao.close();
		
		if(confirmed) {	// ��ġ�ϴ� �����Ͱ� ���� ���
			dao = new MVCBoardDAO();
			MVCBoardDTO dto = dao.selectView(idx);
			int result = dao.deletePost(idx);
			dao.close();
			if(result == 1) {	// ������ �������� ���
				String saveFileName = dto.getSfile();
				FileUtil.deleteFile(request, "/uploads", saveFileName);
			}
			JSFunction.alertLocation(response, "���� �Ϸ�", "./list.do");
		} else {
			JSFunction.alertBack(response, "��й�ȣ ����ġ");	// ���� ���� �� ������������
		}
		
	}
	
	

}
