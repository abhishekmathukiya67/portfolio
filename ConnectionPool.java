package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool {

	private static String driver="org.postgresql.Driver";
	private static String url="jdbc:postgresql://localhost:5432/m3_servlet?user=postgres&password=root";
	private static int pool_size=5;
	private static List <Connection> connections= new ArrayList<>();
	
	static {
		try {
			Class.forName(driver);
			for(int i=0;i<pool_size;i++) {
				connections.add(createConnectionObject());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection createConnectionObject() {
		// TODO Auto-generated method stub
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnectionObject() {
		if(!connections.isEmpty()) {
			return connections.remove(0);
		}
		else {
			return ConnectionPool.createConnectionObject();
		}
		
	}
	public static void receiveConnectionObject(Connection connection) {
		if(connections.size()<pool_size) {
			connections.add(connection);
		}
		else {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	


}
