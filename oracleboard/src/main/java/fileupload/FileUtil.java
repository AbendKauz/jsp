package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import utils.JSFunction;

// web.xml���� �ִ� �뷮�� 1mb�� �⺻ ������
public class FileUtil {
	
	public static MultipartRequest uploadFile(HttpServletRequest request, String saveDirectory, int maxPostSize) {
		try {
			return new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8");
		} catch(Exception e) {
			System.out.println("���� ���ε� ȣ�� ����");
			e.printStackTrace();
			return null;
		}
	}
	
	// �ش� ������ ã�� �ٿ�ε�
	public static void download(HttpServletRequest request, HttpServletResponse response, String directory, String sfileName, String ofileName) {
		
		String sDirectory = request.getServletContext().getRealPath(directory);	// ������ ����� ���� ��ġ�� ������
		
		try {
			// �ش� ������ ã�� �Է� ��Ʈ�� ������
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			// �ѱ� ���� ó��
			// �ѱ� ������ ���� ��츦 ����ϴ� ��������
			// ������ ��� ������ user-agent���� ������ �о�鿩 ������ ������ �ľ���
			// ms ���ͳ� �ͽ��÷η� �� ���ϰ� �������� �� �����Ͽ� �ѱ� ���� ó������
			String client = request.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1) {
				ofileName = new String(ofileName.getBytes("utf-8"), "ISO-8859-1");
			}else {
				ofileName = new String(ofileName.getBytes("ksc5601"), "ISO-8859-1");
			}
			
			// ���� �ٿ�ε�� ���� ��� ����
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
			response.setHeader("Content-Length", "" + file.length());
			
			// ��� ��Ʈ�� ����
			OutputStream oStream = response.getOutputStream();
			
			// ��� ��Ʈ���� ���� ���� ���
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			
			while((readBuffer = iStream.read(b)) > 0) {
				oStream.write(b, 0, readBuffer);
			}
			
			// ����� ��Ʈ�� �ݱ�
			iStream.close();
			oStream.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("���� ã�� ����");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("���� �ٿ�ε� ����");
			e.printStackTrace();
		}
		
	}
	
	// ������ ��ġ�� ���� ����
	public static void deleteFile(HttpServletRequest request, String directory, String filename) {
		
		String sDirectory = request.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {	// ������ �����ϸ� ���� ����
			file.delete();
		}
		
	}
	
}
