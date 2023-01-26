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
		// 업로드 디렉토리의 물리적인 경로를 확인함
		ServletContext application = getServletContext();
		
		// web.xml에서 매개변수로 설정한 첨부파일의 최대용량을 대입시킴
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		// 파일 업로드 실행
		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		
		if(mr == null) {	// 업로드하는 파일 용량이 설정값(1mb)보다 큰지 점검
			JSFunction.alertLocation(response, "파일 용량이 제한범위를 벗어났습니다.", "./write.do");
			return;
		}
		
		// 폼에서 입력한 값을 dto객체에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		
		String fileName = mr.getFilesystemName("ofile");
		
		// 기존 파일이 있는 경우 날짜/시간 데이터를 이용하여 파일명. 확장자를 만들어서 저장함
		if(fileName != null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName); // separator : 구분자 "/"
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			
		}
		
		// dao를 통해서 db에 게시물 내용 저장
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		// 입력이 성공했으면 게시판 리스트로 이동, 실패했으면 원상복귀
		if(result == 1) {
			response.sendRedirect("./list.do");
		} else {
			response.sendRedirect("./write.do");
		}
	}

}
