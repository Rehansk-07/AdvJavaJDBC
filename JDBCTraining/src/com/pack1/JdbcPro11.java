package com.pack1;

import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import oracle.net.aso.m;

public class JdbcPro11 
{
	String driver = "oracle.jdbc.OracleDriver";
	String url ="jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname = "REHAN";
	String dbupwd = "HULK";
	String query = "Select * from EMPLOYEE WHERE EID = ?";
	
	
	Connection connet() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbUname,dbupwd);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
		
		
	}
	void meth() {
		IO.println("Implementing meta data .");
		Connection con= connet();
		try {
			DatabaseMetaData dmtdt=con.getMetaData();
			IO.println("getDatabaseProductName : "+dmtdt.getDatabaseProductName());
			IO.println("getDatabaseProductVersion :"+dmtdt.getDatabaseProductVersion());
			IO.println("getDriverName :"+dmtdt.getDriverName());
			IO.println("supportsStoredProcedures :"+dmtdt.supportsStoredProcedures());
			
			IO.println("-----------------------------------------------------------------------");
			
			PreparedStatement prst = con.prepareStatement(query);
			prst.setString(1, "101");
			ResultSet rs = prst.executeQuery();
			ParameterMetaData pmtdt = prst.getParameterMetaData();
			IO.println("getParameterCount :"+pmtdt.getParameterCount());
			IO.println("getParameterType :"+pmtdt.getParameterType(1));
			IO.println("getParameterMode :"+pmtdt.getParameterMode(1));
			IO.println("isNullable :"+pmtdt.isNullable(1));
			
	        IO.println("=========================================");
	        ResultSetMetaData rsmtdt = rs.getMetaData();
	        IO.println("getColumnCount :"+rsmtdt.getColumnCount());
	        IO.println("getColumnName :"+rsmtdt.getColumnName(1));
	        IO.println("isAutoIncrement :"+rsmtdt.isAutoIncrement(1));
			
	        IO.println("--------------------------------------------------------------------------");
	        RowSetFactory rsf = RowSetProvider.newFactory();
	        CachedRowSet crs = rsf.createCachedRowSet();
	        crs.setUrl(url);
			crs.setUsername(dbUname);
			crs.setPassword(dbupwd);
			crs.setCommand("SELECT EID,EFNAME,ESAL FROM EMPLOYEE");
			crs.execute();
			RowSetMetaData rstmtdt2 = (RowSetMetaData) crs.getMetaData();
			IO.println("getColumnCount :"+rstmtdt2.getColumnCount());
			IO.println("getColumnName :"+rstmtdt2.getColumnName(1));
			IO.println("getColumnDisplaySize :"+rstmtdt2.getColumnDisplaySize(1));
			IO.println("getColumnCount :"+rstmtdt2.isAutoIncrement(1));
			IO.println("getColumnCount :"+rstmtdt2.getColumnType(1));
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
void main() {
	new JdbcPro11().meth();
}
	
	
	
}