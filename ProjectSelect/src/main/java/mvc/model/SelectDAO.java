package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import mvc.database.DBConnection;

public class SelectDAO {

	private static SelectDAO instance;
	
	private SelectDAO() {
		
	}

	public static SelectDAO getInstance() {
		if (instance == null)
			instance = new SelectDAO();
		return instance;
	}

	public void addResult(SelectDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into test_pre (img1,img2,img3,img4,img5,result) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getSelect1());
			pstmt.setString(2, dto.getSelect2());
			pstmt.setString(3, dto.getSelect3());
			pstmt.setString(4, dto.getSelect4());
			pstmt.setString(5, dto.getSelect5());
			pstmt.setString(6, dto.getResultCate());

			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("DB 추가 오류 : " + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
	}	
	
}
