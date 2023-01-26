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
		MVCBoardDAO dao = new MVCBoardDAO();	// dao ��ü ����
		String idx = request.getParameter("idx");	// �Խù� ��ȣ ��������
		dao.updateVisitCount(idx);	// �Խù� ��ȸ�� ����
		MVCBoardDTO dto = dao.selectView(idx);	// �������� ���� ȣ��
		dao.close();	// db ���� �ݱ�
		
		// �Խù� ������ dto ��ü�� ���� �� view ����(�Խù� ��������)�� forward ��Ŵ
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/View.jsp").forward(request, response);
	}

}
