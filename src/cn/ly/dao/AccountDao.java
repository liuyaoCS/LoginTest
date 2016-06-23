package cn.ly.dao;

import java.sql.SQLException;

import javax.security.auth.login.AccountException;

import com.mysql.jdbc.PreparedStatement;

import cn.ly.utils.JdbcUtil;

public class AccountDao {

	public void accountOut(String username, String money) throws AccountException {
		// TODO Auto-generated method stub
		String sql="update userAccount set money=money-? where name=?";
		PreparedStatement ps=(PreparedStatement) JdbcUtil.getPrepareStateMent(sql);
		if(ps!=null){
			try {
				ps.setDouble(1, Double.parseDouble(money));
				ps.setString(2, username);
				int ret=ps.executeUpdate();
				if(ret==0){
					throw new AccountException("transfer error");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new AccountException("transfer error");
			} finally{
				JdbcUtil.releaseStatement();
			}
		}
		
		
		
	}

	public void accountIn(String userTo, String money) throws AccountException {
		// TODO Auto-generated method stub
		String sql="update userAccount set money=money+? where name=?";
		PreparedStatement ps=(PreparedStatement) JdbcUtil.getPrepareStateMent(sql);
		if(ps!=null){
			try {
				ps.setDouble(1, Double.parseDouble(money));
				ps.setString(2, userTo);
				int ret=ps.executeUpdate();
				if(ret==0){
					throw new AccountException("transfer error");
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new AccountException("transfer error");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUtil.releaseStatement();
			}
		}
		
		
	}

}
