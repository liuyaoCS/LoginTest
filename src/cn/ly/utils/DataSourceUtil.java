package cn.ly.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {
	
	private static ComboPooledDataSource cpds=new ComboPooledDataSource();
	public static Connection getConnection() throws SQLException{
		return cpds.getConnection();
	}
	public static ComboPooledDataSource getDataSource(){
		return cpds;
	}
	
}
