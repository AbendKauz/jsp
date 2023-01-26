package model2.mvcboard;

import java.sql.PreparedStatement;
import java.util.*;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	public MVCBoardDAO() {
		super();
	}
	
	// �˻� ���ǿ� �´� �Խù��� ���� ��ȯ -> ��� int
	// select count(*) from mvcboard �� �̿��� ��ü ���� ��������
	public int selectCount(Map<String, Object>map) {
		int totalCount = 0;	// �ʱ�ȭ ����
		String query = "select count(*) from mvcboard";
		// �Խ��ǿ��� ��ü ���� ��������
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'" ;
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch(Exception e) {
			System.out.println("�Խ��� ī��Ʈ ���� ����");
			System.out.println(query);
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// �˻� ���ǿ� �´� �Խù� ��� ��ȯ (����¡ ���)
	// ��Ʈ�ѷ����� Map �÷����� �����ϰ� ��Ͽ� ����� �Խù��� ������ �����
	// �Ű����� �÷���(map)�� �߰��Ϸ��� ��
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("searchField", searchField);
	// map.put("searchWord", searchWord);
	public List<MVCBoardDTO> selectListPage(Map<String, Object>map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		String query = " " 
						+ "select * from ( "
						+ " select Tb.*, rownum rNum from ( "
						+ "  select * from mvcboard ";
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField")
					+ " like '%" + map.get("searchWord") + "%'" ;
		}
		
		query += "	  order by idx desc"
				+ "	 ) Tb "
				+ " ) "
				+ " where rNum between ? and ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		} catch(Exception e) {
			System.out.println("�Խù� ��ȸ �� ���� �߻�");
			e.printStackTrace();
		}
		
		return board;
	}
	
	// �Խ��� �����͸� �޾� DB�� ������ �߰�
	// mvcboard�� ������ �Է� �� �湮Ƚ��(visitcount)�� 
	// �ٿ�ε�Ƚ��(downcount)�� �⺻���� 0�̱� ������ �Է� ����
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String sql = "insert into mvcboard (idx, name, title, content, ofile, sfile, pass)"
					+ " values (seq_board_num.nextval,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("�Խù� �߰� �� ���� �߻�");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		String sql = "select * from mvcboard where idx=?";	// �Խù� ��ȣ�� �ش��ϴ� �����͸� ��� ������
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
			
		} catch(Exception e) {
			System.out.println("�Խù� ��ȣ ������ �������� ����");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void updateVisitCount(String idx) {	// �Խù��� ��ȸ�� ����
		
		String sql = "update mvcboard set visitcount=visitcount+1 where idx=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("�Խù� ��ȸ�� ���� ����");
			e.printStackTrace();
		}
	}
	
	// �ٿ�ε� Ƚ�� ����
	public void downCountPlus(String idx) {
		
		String sql = "update mvcboard set downcount=downcount+1 where idx=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("�ٿ�ε�� ���� ����");
			e.printStackTrace();
		}
	}
	
	// ��й�ȣ ����
	public boolean confirmPassword(String pass, String idx, String mode) {
		boolean isCorrect = true;
		
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0) {
				isCorrect = false;
			}
			
		} catch (Exception e) {
			isCorrect = false;
			System.out.println("��й�ȣ Ʋ��");
			e.printStackTrace();
		}
		
		return isCorrect;
	}
	
	// ��й�ȣ�� ������ �Խù� ����
	public int deletePost(String idx) {
		int result = 0;
		
		try {
			String sql = "delete from mvcboard where idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("�Խù� ���� ����");
			e.printStackTrace();
		}
		
		return result;	// ���������� �����Ǿ����� result�� ����� 1�� ��ȯ��
	}
	
}
