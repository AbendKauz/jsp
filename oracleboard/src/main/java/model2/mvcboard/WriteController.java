package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/WriteController")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/Write.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getServletContext().getRealPath("/uploads");
		// ���ε� ���丮�� �������� ��θ� Ȯ����
		ServletContext application = getServletContext();
		
		// web.xml���� �Ű������� ������ ÷�������� �ִ�뷮�� ���Խ�Ŵ
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		// ���� ���ε� ����
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		
		if(mr == null) {	// ���ε��ϴ� ���� �뷮�� ������(1mb)���� ū�� ����
			JSFunction.alertLocation(response, "���� �뷮�� ���ѹ����� ������ϴ�.", "./write.do");
			return;
		}
		
		// ������ �Է��� ���� dto��ü�� ����
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		String fileName = mr.getFilesystemName("ofile");
		
		// ���� ������ �ִ� ��� ��¥/�ð� �����͸� �̿��Ͽ� ���ϸ�. Ȯ���ڸ� ���� ������
		if(fileName != null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// ���ϸ� ����
			File oldFile = new File(saveDirectory + File.separator + fileName); // separator : ������ "/"
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			
		}
		
		// dao�� ���ؼ� db�� �Խù� ���� ����
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		// �Է��� ���������� �Խ��� ����Ʈ�� �̵�, ���������� ���󺹱�
		if(result == 1) {
			response.sendRedirect("./list.do");
		} else {
			response.sendRedirect("./write.do");
		}
	}

}
