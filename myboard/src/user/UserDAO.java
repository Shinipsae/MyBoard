package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs; 

	public UserDAO() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "tester";
			String pw = "1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String sql = "SELECT \"userPassword\" FROM USER1 WHERE \"userID\" = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
					
				} else {
					return 0; // 비밀번호 불일친
				}
			}	
			return -1; // 아이디가 없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
}
