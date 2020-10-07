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
					return 1; // �α��� ����
					
				} else {
					return 0; // ��й�ȣ ����ģ
				}
			}	
			return -1; // ���̵� ����
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // �����ͺ��̽� ����
	}
}
