package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.model.vo.Member;

// DAO(Data Access Obeject) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과 반환(JDBC)
//							  결과를 Controller로 다시 리턴
public class MemberDao {
	/*
	 * *JDBC용 객체
	 * -Connection : DB의 연결정보를 담고있는 객체
	 * -[Prepared]Statement : 연결된 DB에 SQL문을 전달해서 실행하고, 결과를 받아내는 객체
	 * -ResultSet : SELECT문 실행 후 조회된 결과물들이 담겨있는 객체 
	 * 
	 * *JDBC과정(순서 중요)
	 * 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
	 * 2) Connection 생성 : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결하면서 생성
	 * 3) Statement 생성 : Connection 객체를 이용해서 생성(sql문 실행 및 결과받는 객체)
	 * 4) sql문 전달하면서 실행 : Statement 객체를 이용해서 sql문 실행
	 * 5) 결과받기
	 *		> SELECT문 실행 => ResultSet객체 (조회된 데이터들이 담겨있음) => 6-1)
	 *		> DML문 => int(처리된 행 수) => 6-2)
	 *
	 * 6-1) ResultSet에 담겨있는 데이터들을 하나씩 하나씩 뽑아서 vo객체에 차근차근 옮겨닮기[+ ArrayList에 담아주기]
	 * 6-2) 트랜잭션 처리 (성공했다면 commit, 실패했다면 rollback 실행)
	 * 	
	 * 7) 다 사용한 JDBC용 객체들 반드시 자원 반납(close) => 생성된 역순으로
	 */
	
	/**
	 * 사용자가 입력한 정보들을 DB에 추가시켜주는 메서드
	 * @param m : 사용자가 입력한 값들이 담겨있는 meeber객체
	 * @return : int
	 */
	
	public int insertMember(Member m) {
		// insert문 => 처리된 행수(int) => 트랜잭션 처리
		
		// 필요한 변수들 먼저 세팅
		int result = 0; // 처리된 결과(처리된 행 수)를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결 정보를 담는 객체
		Statement stmt = null; // 완성된 sql문 전달해서 곧바로 실행 후 결과를 받는 객체
		
		// 실행할 sql문
		// INSERT INTO MEMBER
		// VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '홍길동', null, 23,
		// 		  'user01@iei.or.kr', '01022222222', '부산', '등산, 영화보기', '2021-08-02');
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,"
				+ "'" + m.getUserId() 	+ "',"
				+ "'" + m.getUserPwd() 	+ "',"
				+ "'" + m.getUserName() + "',"
				+ "'" + m.getGender() 	+ "',"
					  + m.getAge() 		+ ","
				+ "'" + m.getEmail() 	+ "',"
				+ "'" + m.getPhone() 	+ "',"
				+ "'" + m.getAddress() 	+ "',"
				+ "'" + m.getHobby() 	+ "', SYSDATE)";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 => DB연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) sql문 전달하면서 실행 후 결과받기(처리된 행 수)
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜잭션 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 JDBC 객체들 반환
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

}
