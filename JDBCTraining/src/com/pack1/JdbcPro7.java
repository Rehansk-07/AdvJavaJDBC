package com.pack1;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcPro7 
{
	String driver = "oracle.jdbc.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="REHAN";
	String dbPwd = "HULK";
	
	void m1()
	{
		System.out.println("Implementing JdbcRowset");
		try
		{
			RowSetFactory rsf = RowSetProvider.newFactory();
			JdbcRowSet jrs =rsf.createJdbcRowSet();
			jrs.setUrl(dbUrl);
			jrs.setUsername(dbUname);
			jrs.setPassword(dbPwd);
			jrs.setCommand("SELECT * FROM EMPLOYEE");
			jrs.execute();
			
			while(jrs.next())
			{
				IO.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getInt(4)+" "+jrs.getString(5));
				
			}
			IO.println("--------------------------------------------------------");
			while(jrs.previous())
			{
				IO.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getInt(4)+" "+jrs.getString(5));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void m2()
	{
		IO.println("Implementing CacheRowSet");
		try
		{
			RowSetFactory rsf = RowSetProvider.newFactory();
			CachedRowSet crs = rsf.createCachedRowSet();
			crs.setUrl(dbUrl);
			crs.setUsername(dbUname);
			crs.setPassword(dbPwd);
			crs.setCommand("SELECT EID,EFNAME,ESAL FROM EMPLOYEE");
			crs.execute();
			
			while(crs.next())
			{
				String e_id = crs.getString(1);
				if(e_id.equals("104"));
				{
					crs.updateInt(3, 75000);
					crs.updateRow();
				}
			}
			crs.acceptChanges();
			while(crs.next())
			{
				if(crs.getString(1).equals("104"))
				{
					IO.println(crs.getString(1)+" "+crs.getString(2)+" "+crs.getInt(3));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void main(String[] args)
	{
		JdbcPro7 obj = new JdbcPro7();
		//obj.m1();
		obj.m2();
		
	}
}
