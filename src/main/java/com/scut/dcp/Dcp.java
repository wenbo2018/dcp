package com.scut.dcp;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;


public class Dcp implements DataSource{

	private LinkedList<Connection> dataSources = new LinkedList<Connection>();
    public static final String url = "jdbc:mysql://115.28.101.73:3306/scut";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "wenbo2018";  
    public static final String password = "wenbo19910518"; 
	
    private static class SingletonFactory {  
        private static Dcp instance = new Dcp();  
    }
    
    public static Dcp getInstance() {
    	return SingletonFactory.instance;
    }
	private Dcp() {
		 //一次性创建10个连接  
        for(int i = 0; i < 10; i++) {  
               try {  
                   Class.forName(name);//指定连接类型  
                   Connection con = DriverManager.getConnection(url, user, password);//获取连接  
                   
                   dataSources.add(con);  
               } catch (Exception e) {  
                  e.printStackTrace();  
               }  
        }  
	}
	 //将连接放回连接池  
    public void releaseConnection(Connection conn) {  
           dataSources.add(conn);  
    }  
    @Override
	public Connection getConnection() throws SQLException {
		//取出连接池中一个连接  
        final Connection conn = dataSources.removeFirst(); // 删除第一个连接返回  
        return conn;
	}
    
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		
        return null;  
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
