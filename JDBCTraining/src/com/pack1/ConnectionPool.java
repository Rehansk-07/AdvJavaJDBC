package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool 
{
	String driver,dbUrl,dbUname,dbPwd;
	Vector<Connection> v = new Vector<Connection>();
	
	public ConnectionPool(String driver, String dbUrl, String dbUname, String dbPwd) 
	{
		super();
		this.driver = driver;
		this.dbUrl = dbUrl;
		this.dbUname = dbUname;
		this.dbPwd = dbPwd;
	}
	public void con_Initialization()
	{
		IO.println("Connection Pool is Emplty");
		IO.println("There are "+v.size()+"Connection Objects");
		while(v.size()<5)
		{
			try
			{
				Class.forName(driver);
				Connection con = DriverManager.getConnection(dbUrl,dbUname,dbPwd);
				v.addElement(con);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		for(Object o:v)
			System.out.println(o);
		IO.println("There are " +v.size()+"Connectionobjects");
	}
	public Connection con_acquisition()
	{
		Connection con=v.get(0);
		v.remove(0);
		return con;
	}
		public void con_return(Connection con) 
		{
			IO.println("adding the connection object to the connection pool");
			v.addElement(con);
			IO.println("there are "+v.size()+"connections object");
			for(Object o:v) 
			{
				IO.println(o);
	}
  }
}
