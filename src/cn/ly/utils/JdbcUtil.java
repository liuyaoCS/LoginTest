package cn.ly.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtil {
	private static String DRIVERCLASS="";
	private static String URL="";
	private static String USER="";
	private static String PASSWORD="";
	
	private static Connection connection=null;
	private static Statement st=null;
	static{
		DRIVERCLASS=ResourceBundle.getBundle("db").getString("driver");
		URL=ResourceBundle.getBundle("db").getString("url");
		USER=ResourceBundle.getBundle("db").getString("name");
		PASSWORD=ResourceBundle.getBundle("db").getString("password");
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Statement getStateMent(){
		try {
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			st=connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if(connection!=null)connection.close();
				if(st!=null)st.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return st;
	}
	
	public static void release(){
		try {
			if(connection!=null)connection.close();
			if(st!=null)st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
