package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcPro2 
{
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="REHAN";
	String dbPwd = "HULK";
	
	Scanner sc=new Scanner(System.in);
	String sqlQuery="select * from employee";
	String sqlQuery2="INSERT INTO EMPLOYEE VALUES(106,'SALMAN','KHAN',60000,'MUMBAI')";
	String sqlQuery3 ="DELETE FROM EMPLOYEE WHERE EID='106'";
	String sqlQuery4=" update employee set esal='38000'where eid='101'";
	
	Connection connect()
	{
		Connection con=null;
		
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl, dbUname, dbPwd);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void getEmpData()
	{
		System.out.println("---------Employee Details-------\n");
		try
		{
			Class.forName(driver);
			Connection con=connect();
			System.out.println("Database Connected Successfully\n ");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sqlQuery);
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5) );
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void insertEmpData()
	{
		System.out.println("Inserting the data in to employee table");
		try
		{
			Class.forName(driver);
		    Connection con=connect();
		    Statement stmt=con.createStatement();
		    int rowCount=stmt.executeUpdate(sqlQuery2);
		    if(rowCount==0) 
		    {
		    	System.out.println("Data Not inserted");
		    } 	
		    else 
		    {
		    	System.out.println(rowCount+" Data inserted");
		    	System.out.println("----------------------------");
		    	getEmpData();
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	void deleteEmpData()
	{
		System.out.println("Deteleting Data from employee table");
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate(sqlQuery3);
			if(rowCount==0)
			{
				System.out.println("There is No employee with the given Eid");
			}
			else
			{
				System.out.println("given EID removed from employee table");
				System.out.println("Do you want to view the table data ? (Y/N)");
				char choice = sc.nextLine().charAt(0);
				switch(choice)
				{
				case 'y','Y':
					getEmpData();
					break;
				case 'n','N':
					System.exit(0);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void updateEmpData()
	{
		System.out.println("Updating employee salary");
		try
		{
			Connection con =connect();
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate(sqlQuery4);
			if(rowCount==0)
			{
				System.out.println("given Employee Id is Not available");	
			}
			else
			{
				System.out.println("salary updated for given employee");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String  [] args)
	{
		 JdbcPro2 obj=new JdbcPro2();
		 //obj.getEmpData();
		 //obj.insertEmpData(); 
		 //obj.deleteEmpData();
		 obj.updateEmpData();
	}
}
