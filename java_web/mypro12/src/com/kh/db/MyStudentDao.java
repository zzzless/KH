package com.kh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MyStudentDao {
	private static MyStudentDao instance;
	private MyStudentDao() {}
	public static MyStudentDao getInstance() {
		if(instance==null) {
			instance = new MyStudentDao();
		}
		return instance;
	}
	// db 연결 변수
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "jsp02";
	private final String PASSWORD = "1234";
	// 연결메소드
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn: " + conn);
			return conn;
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	// 연결 닫기 메소드
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close();} catch(Exception e) {}
		if(pstmt != null) try {pstmt.close();} catch(Exception e) {}
		if(conn != null) try {conn.close();} catch(Exception e) {}
	}
	
	// insert 메소스
	public boolean insertStudent(MyStudentVo studentVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into tbl_student(st_num, st_name, st_major, st_year, st_score, st_etc)"
					+ "   values(?, ?, ?, ?, ?, ?)";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentVo.getSt_num());
			pstmt.setString(2, studentVo.getSt_name());
			pstmt.setString(3, studentVo.getSt_major());
			pstmt.setInt(4, studentVo.getSt_year());
			pstmt.setInt(5, studentVo.getSt_score());
			pstmt.setString(6, studentVo.getSt_etc());
			int count = pstmt.executeUpdate();
			if(count == 1) {
				return true;
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		return false;
	}
	
	// select
	public ArrayList<MyStudentVo> listStudent() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MyStudentVo> list = new ArrayList<MyStudentVo>(); 
		String sql = "select st_num, st_name, st_major, st_year"
				+ "   from tbl_student"
				+ "   order by st_num";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("st_num");
				String name = rs.getString("st_name");
				String major = rs.getString("st_major");
				int year = rs.getInt("st_year");
				MyStudentVo vo = new MyStudentVo(num, name, major, year, 0, null);
				list.add(vo);
			}
			closeAll(rs, pstmt, conn);
			return list;
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return null;
	}
	
	// 한 학생의 정보
	public MyStudentVo getondeStudent(int st_num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tbl_student where st_num="+st_num;
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 다음에 읽을 데이터가 있으면 커서를 다음 데이터로 옮기고 true를 리턴한다.
				String st_name = rs.getString("st_name");
				String st_major = rs.getString("st_major");
				int st_year = rs.getInt("st_year");
				int st_score = rs.getInt("st_score");
				String st_etc = rs.getString("st_etc");
				MyStudentVo vo = new MyStudentVo(st_num, st_name, st_major, st_year, st_score, st_etc);
				closeAll(rs, pstmt, conn);
				return vo;
			}
		} catch(Exception e) {
			//e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return null;
	}
	
	// 등록된 학번 체크
	public boolean checkStNum(int st_num) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from tbl_student "
				+ "where st_num = " + st_num;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("cnt");
				if(count == 0) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return false;
	}
}
