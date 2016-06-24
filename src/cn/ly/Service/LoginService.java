package cn.ly.Service;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import cn.ly.bean.User;
import cn.ly.dao.LoginDao;

public class LoginService {

	public User login(User user) throws SQLException, PropertyVetoException {
		// TODO Auto-generated method stub
		LoginDao dao=new LoginDao();
		User exitUser=dao.login(user);
		return exitUser;
	}

}
