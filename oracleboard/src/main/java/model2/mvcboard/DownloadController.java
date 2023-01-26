package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileupload.FileUtil;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ű����� �ޱ�
		String ofile = request.getParameter("ofile");	// ���� ����
		String sfile = request.getParameter("sfile");	// �ű� ����
		String idx = request.getParameter("idx");	// �Խù� ��ȣ
		
		// ���� �ٿ�ε� ȣ��
		FileUtil.download(request, response, "/uploads", sfile, ofile);
		
		// �ش� �Խù� �ٿ�ε� �� ����
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.downCountPlus(idx);
		dao.close();
	}

}
