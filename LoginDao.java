package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {


	public static boolean isValidate(String username, String password) {
		boolean result= false;
		Connection connection= ConnectionPool.getConnectionObject();
		String sql= "select * from login_data where log_username=? and log_password=?";
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				result= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
