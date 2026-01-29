package com.lab_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeRegistration 
{
	String driver ="oracle.jdbc.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="REHAN";
	String dbPwd ="HULK";
	
	Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	void getEmpData()
	{
		try
		{
			Connection con = connect();
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM EMPLOYEEINFO where EMP_ID=? AND EMP_NAME=?");
			
			String emp_id = IO.readln("Enter Employee ID:");
			String emp_name = IO.readln("Enter Employee Name:");
			pstm.setString(1, emp_id);
			pstm.setString(2, emp_name);
			ResultSet rs=pstm.executeQuery();
			
			if(rs.next())
			{
				IO.println("Login sucessfull");
				

				while(true)
				{
					IO.println("==========EmployeeDetails==========");
					IO.println("1. Show All Employees ");
					IO.println("2. Update MailId & Phno ");
					IO.println("3. Delete employee who salary between 50000 -70000 ");
					IO.println("4. Increase 10R salary ");
					IO.println("5. Exit");
					
					int choice = Integer.parseInt(IO.readln("Enter Your Choice :"));
					switch(choice)
					{
					case 1:
						IO.println("View All Employee Data");
					}
			}
			else
			{
				System.err.println("Employee Not Found!!!");
			}
			
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}