package cn.ly.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//import com.mysql.jdbc.PreparedStatement;


import cn.ly.bean.User;
import cn.ly.utils.JdbcUtil;

public class LoginDao {

	public User login(User user) throws SQLException, PropertyVetoException {
		// TODO Auto-generated method stub
		User exitUser=null;
		//exitUser = normalStatement(user, exitUser);
		//exitUser = PrepareStatement(user, exitUser);
		exitUser = CpDataSourceAction(user, exitUser);
		
		return exitUser;
	}

	private User CpDataSourceAction(User user, User exitUser)
			throws PropertyVetoException, SQLException {
		ComboPooledDataSource cpds=new ComboPooledDataSource();
//		cpds.setDriverClass("com.mysql.jdbc.Driver");
//		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
//		cpds.setUser("root");
//		cpds.setPassword("985910");
		
		String sql="select * from userAccount where name=? and password=?";
		Connection connection= cpds.getConnection();
		PreparedStatement statement=(PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		
		ResultSet rSet=statement.executeQuery();
		if(rSet.next()){
			exitUser=new User(rSet.getString("name"),rSet.getString("password"));
		}
		return exitUser;
	}

	private User PrepareStatement(User user, User exitUser) throws SQLException {
		String sql="select * from userAccount where name=? and password=?";
		PreparedStatement statement= (PreparedStatement)JdbcUtil.getPrepareStateMent(sql);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		if(statement!=null){
			ResultSet rSet=statement.executeQuery();
			if(rSet.next()){
				exitUser=new User(rSet.getString("name"),rSet.getString("password"));
			}
		}
		JdbcUtil.releaseStatement();
		return exitUser;
	}

	private User normalStatement(User user, User exitUser) throws SQLException {
		Statement statement=JdbcUtil.getStateMent();
		if(statement!=null){
			String sql="select * from userAccount where name='"+user.getUsername()+"'and password='"+user.getPassword()+"'";
			ResultSet rSet=statement.executeQuery(sql);
			if(rSet.next()){
				exitUser=new User(rSet.getString("name"),rSet.getString("password"));
			}
		}
		JdbcUtil.releaseStatement();
		return exitUser;
	}

}
