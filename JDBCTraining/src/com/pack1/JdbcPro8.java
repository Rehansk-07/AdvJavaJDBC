package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;


public class JdbcPro8 
{
	String driver = "oracle.jdbc.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="REHAN";
	String dbPwd = "HULK";
	
	Connection connect()
	{
		Connection con = null;
		try
		{
			IO.println("Connecting to database");
			Class.forName(driver);
			con= DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			IO.println("Connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void m1()
	{
		IO.println("Implementing Callable Statment");
		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			CallableStatement cstmt = con.prepareCall("{Call InsertData(?,?,?,?,?)}");
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your Id:");
			String id=sc.nextLine();
			System.out.println("Enter your name:");
			String name = sc.nextLine();
			System.out.println("Enter your designation:");
			String desg = sc.nextLine();
			System.out.println("Enter your basic salary:");
			int bsal= Integer.parseInt(sc.nextLine());
			
			float tsal = bsal+(0.35f*bsal)+(0.15f*bsal);
			
			cstmt.setString(1, id);
			cstmt.setString(2, name);
			cstmt.setString(3, desg);
			cstmt.setInt(4, bsal);
			cstmt.setFloat(5, tsal);
			
			cstmt.execute();
			
			System.out.println("Data Inserted!!!!");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void m2()
	{
		IO.println("Implementing Callaable Statement===> Procedure2");
		try
		{
			Connection con = connect();
			CallableStatement cstmt = con.prepareCall("{Call RetriveEmpData(?,?,?,?,?)}");
			
			String e_id =IO.readln("Enter Id FOR RETRIEVING:");
			cstmt.setString(1, e_id);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.execute();
			
			IO.println("----------Employee Details----------");
			IO.println("Employee Id: " + e_id);
			IO.println("Employee Name: "+ cstmt.getString(2));
			IO.println("Employee Desg: "+ cstmt.getString(3));
			IO.println("Employee Basic Sal: "+ cstmt.getInt(4));
			IO.println("Employee Total Sal: "+ cstmt.getInt(5));	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void m3()
	{
		IO.println("Implementing Callabale Statement===> Functions");
		try
		{
			Connection con= connect();
			CallableStatement cstmt = con.prepareCall("{Call ?:=RetriveTsal(?)}");
			
			String e_id = IO.readln("Enter ID:");
			cstmt.setString(2, e_id);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.execute();
			
			IO.println("----------Employee Details----------");
			IO.println("Employee Id: "+ e_id);
			IO.println("Employee  Total Sal: "+cstmt.getInt(1));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		JdbcPro8 obj = new JdbcPro8();
		//obj.m1();
		//obj.m2();
		obj.m3();
	}
}
