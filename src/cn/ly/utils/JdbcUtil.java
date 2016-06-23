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
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
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
	public static Connection getConnection() throws SQLException{
		connection=tl.get();
		if(connection==null){
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			tl.set(connection);
		}
		return connection;
	}
	@Deprecated
	/**
	 * 会发生sql注意，已废弃
	 * @return
	 */
	public static Statement getStateMent(){
		try {
			connection=getConnection(); 
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
	public static Statement getPrepareStateMent(String sql){
		try {
			connection=getConnection(); 
			st=connection.prepareStatement(sql);
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
	
	public static void releaseConnection(){
		try {
			if(connection!=null)connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void releaseStatement(){
		try {
			if(st!=null)st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
