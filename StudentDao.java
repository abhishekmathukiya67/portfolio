package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Student;


public class StudentDao {
	public static int insertData(Student s) {
		// TODO Auto-generated method stub
		Connection connection= ConnectionPool.getConnectionObject();
		String sql= "insert into student_management values(NEXTVAL('std_id_seq'),?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getEmail());
			ps.setLong(4, s.getMobile());
			Date d= s.getDob();
			java.sql.Date d1= new java.sql.Date(d.getTime()); 	
			ps.setDate(5, d1);
			ps.setString(6, s.getGender());
			ps.setString(7, s.getQualification());
			int i= ps.executeUpdate();
			ConnectionPool.receiveConnectionObject(connection);
			return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<Student> read() {
		
		Connection connection= ConnectionPool.getConnectionObject();
		String sql="select * from student_management";
		List<Student> students= new ArrayList<Student>();
		
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Student s= new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				s.setEmail(rs.getString(4));
				s.setMobile(rs.getLong(5));
				s.setDob(rs.getDate(6));
				s.setGender(rs.getString(7));
				s.setQualification(rs.getString(8));
				students.add(s);
			}
			ConnectionPool.receiveConnectionObject(connection);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return students;
		
	}

	public static void delete(int id) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionPool.getConnectionObject();
		String sql= "delete from student_management where stu_id =?";
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("data deleted successfully");
			}else {
				System.out.println("failed to delete");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Student getStudentById(int id) {
		Connection connection= ConnectionPool.getConnectionObject();
		Student s= new Student();
		String sql= "select * from student_management where stu_id=?";
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setAge(rs.getInt(3));
				s.setEmail(rs.getString(4));
				s.setMobile(rs.getLong(5));
				s.setDob(rs.getDate(6));
				s.setGender(rs.getString(7));
				s.setQualification(rs.getString(8));
				
			}
			ConnectionPool.receiveConnectionObject(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
		
	}

	public static void update(Student s) {
		// TODO Auto-generated method stub
		
		Connection connection= ConnectionPool.getConnectionObject();
		String sql= "update student_management set stu_name=?, stu_age=?,stu_email=?,stu_mobile=?,stu_dob=?,stu_gender=?,stu_qualification=? where stu_id=?";
		String sql1= "update student_management set stu_name=?, stu_age=?,stu_email=?,stu_mobile=?,stu_dob=?,stu_qualification=? where stu_id=?";
		if(s.getGender()== null) {
			try {
				PreparedStatement ps= connection.prepareStatement(sql1);
				ps.setString(1, s.getName());
				ps.setInt(2, s.getAge());
				ps.setString(3, s.getEmail());
				ps.setLong(4, s.getMobile());
				Date d= s.getDob();
				java.sql.Date d1= new java.sql.Date(d.getTime()); 	
				ps.setDate(5, d1);
				ps.setString(6, s.getQualification());
				ps.setInt(7, s.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				PreparedStatement ps= connection.prepareStatement(sql);
				ps.setString(1, s.getName());
				ps.setInt(2, s.getAge());
				ps.setString(3, s.getEmail());
				ps.setLong(4, s.getMobile());
				Date d= s.getDob();
				java.sql.Date d1= new java.sql.Date(d.getTime()); 	
				ps.setDate(5, d1);
				ps.setString(6, s.getGender());
				ps.setString(7, s.getQualification());
				ps.setInt(8, s.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Data Updated Successfully");
		ConnectionPool.receiveConnectionObject(connection);
	}



}
