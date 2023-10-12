package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.User;

public class DBOperations 
{
	
//	connect method
	static Connection con;
	public static void connect()
	{
		String url = "jdbc:mysql://localhost:3306/demo";
		String uname = "root";
		String upass = "mysqlbc";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, upass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	insert method
	public static boolean insert(User u)
	{
		boolean b = false;
		connect();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user value(?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstname());
			ps.setString(4, u.getLastname());
			ps.setString(5, u.getEmail());
			b = ps.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
		
	}
	
//	delete method
	public static boolean delete(int id)
	{
		boolean b = false;
		connect();
		
		try {
			PreparedStatement ps  = con.prepareStatement("delete from user where id = ?");
			ps.setInt(1, id);
			b = ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	show data method
	public static User[] showData()
	{
		User[] ur=null;
		int v = 0;
		try {
			PreparedStatement ps = con.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				v++;
			}
			
			ur = new User[v];
			rs = ps.executeQuery();
			while(rs.next())
			{
				User usr = new User();
				usr.setUsername(rs.getString(1));
				usr.setPassword(rs.getString(2));
				usr.setFirstname(rs.getString(3));
				usr.setLastname(rs.getString(4));
				usr.setEmail(rs.getString(5));
				ur[v++] = usr;
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ur;
	}
	
	//update method
	public static int update(User u)
	{
		int v = 0;
		connect();
		try
		{
			PreparedStatement ps = con.prepareStatement("update user set username=?, password=?, firstname=? lastname=? email=? where id = ?");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstname());
			ps.setString(4, u.getLastname());
			ps.setString(5, u.getEmail());
			v = ps.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return v;
	}
	
	public static void viewByUsername(String username)
	{
		
	}

}
