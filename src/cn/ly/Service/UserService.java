package cn.ly.Service;

import java.sql.SQLException;

import cn.ly.dao.UserDao;
import cnly.bean.User;

public class UserService {

	public User login(User user) throws SQLException {
		// TODO Auto-generated method stub
		UserDao dao=new UserDao();
		User exitUser=dao.login(user);
		return exitUser;
	}

}
