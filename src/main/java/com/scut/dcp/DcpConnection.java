package com.scut.dcp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DcpConnection {
   
	public static final String url = null;
	
    public static final String name = null;  
    
    public static final String user =null; 
    
    public static final String password =null; 
	
    public static final int DUFAULT_MAX=100;
    
    public static final int  DUFAULT_MIN=2;
    
    public static final int  DUFAULT_COUNT=10;
    
    public DcpConnection() {
		// TODO Auto-generated constructor stub
	}
    
    public DcpConnection(String url,String driverName,String username,String password) {
        for(int i = 0; i < 10; i++) {  
               try {  
                   Class.forName(this.name);//指定连接类型  
                   Connection con = DriverManager.getConnection(this.url, this.user, this.password);//获取连接  
               } catch (Exception e) {  
                  e.printStackTrace();  
               }  
        }  
    }
    
    
}
