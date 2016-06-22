package cn.ly.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.ly.utils.JdbcUtil;
import cnly.bean.User;

public class UserDao {

	public User login(User user) throws SQLException {
		// TODO Auto-generated method stub
		User exitUser=null;
		Statement statement=JdbcUtil.getStateMent();
		if(statement!=null){
			String sql="select * from user where name='"+user.getUsername()+"'and password='"+user.getPassword()+"'";
			ResultSet rSet=statement.executeQuery(sql);
			if(rSet.next()){
				exitUser=new User(rSet.getString("name"),rSet.getString("password"));
			}
		}
		JdbcUtil.release();
		return exitUser;
	}

}
