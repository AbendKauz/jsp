package model2.mvcboard;

import java.sql.PreparedStatement;
import java.util.*;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	public MVCBoardDAO() {
		super();
	}
	
	// 검색 조건에 맞는 게시물의 개수 반환 -> 결과 int
	// select count(*) from mvcboard 를 이용한 전체 개수 가져오기
	public int selectCount(Map<String, Object>map) {
		int totalCount = 0;	// 초기화 세팅
		String query = "select count(*) from mvcboard";
		// 게시판에서 전체 개수 가져오기
		
		if(map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'" ;
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch(Exception e) {
			System.out.println("게시판 카운트 생성 에러");
			System.out.println(query);
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록 반환 (페이징 기능)
	// 컨트롤러에서 Map 컬렉션을 생성하고 목록에 출력할 게시물의 범위를 계산해
	// 매개변수 컬렉션(map)에 추가하려고 함
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
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board;
	}
	
	// 게시판 데이터를 받아 DB에 데이터 추가
	// mvcboard에 데이터 입력 시 방문횟수(visitcount)와 
	// 다운로드횟수(downcount)는 기본값이 0이기 때문에 입력 생략
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
			System.out.println("게시물 추가 중 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		String sql = "select * from mvcboard where idx=?";	// 게시물 번호에 해당하는 데이터를 모두 가져옴
		
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
			System.out.println("게시물 번호 데이터 가져오기 오류");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void updateVisitCount(String idx) {	// 게시물의 조회수 증가
		
		String sql = "update mvcboard set visitcount=visitcount+1 where idx=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 오류");
			e.printStackTrace();
		}
	}
	
	// 다운로드 횟수 증가
	public void downCountPlus(String idx) {
		
		String sql = "update mvcboard set downcount=downcount+1 where idx=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeQuery();
		} catch(Exception e) {
			System.out.println("다운로드수 증가 오류");
			e.printStackTrace();
		}
	}
	
	// 비밀번호 검증
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
			System.out.println("비밀번호 틀림");
			e.printStackTrace();
		}
		
		return isCorrect;
	}
	
	// 비밀번호가 검증된 게시물 삭제
	public int deletePost(String idx) {
		int result = 0;
		
		try {
			String sql = "delete from mvcboard where idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 삭제 오류");
			e.printStackTrace();
		}
		
		return result;	// 정상적으로 삭제되었으면 result의 결과로 1을 반환함
	}
	
}
