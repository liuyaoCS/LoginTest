package cn.ly.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import cn.ly.bean.User;
import cn.ly.utils.JdbcUtil;

public class LoginDao {

	public User login(User user) throws SQLException {
		// TODO Auto-generated method stub
		User exitUser=null;
		//exitUser = normalStatement(user, exitUser);
		exitUser = PrepareStatement(user, exitUser);
		
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
