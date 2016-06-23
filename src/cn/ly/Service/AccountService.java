package cn.ly.Service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.security.auth.login.AccountException;

import cn.ly.dao.AccountDao;
import cn.ly.utils.JdbcUtil;

public class AccountService {


	public void transfer(String username, String userTo, String money) throws AccountException{
		// TODO Auto-generated method stub
		AccountDao accountDao=new AccountDao();
		Connection connection=null;
		try {
			connection=JdbcUtil.getConnection();
			connection.setAutoCommit(false);	
			accountDao.accountOut(username,money);
			accountDao.accountIn(userTo,money);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if(connection!=null){
					connection.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new AccountException();
		}finally{
			try {
				if(connection!=null){
					connection.commit();
				}
				JdbcUtil.releaseConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
