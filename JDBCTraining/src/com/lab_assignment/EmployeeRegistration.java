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
	String sqlQuery1 = "INSERT INTO EMPLOYEEINFO VALUES(?,?,?,?,?,?,?)";
	String sqlQuery2 ="SELECT * FROM EMPLOYEEINFO";
	String sqlQuery3 = "update EMPLOYEEINFO set EMP_MAIL=?,EMP_PHNO=? where EMP_ID=?";
	String sqlQuery4 = "DELETE FROM EMPLOYEEINFO WHERE EMP_SAL BETWEEN 50000 AND 70000 ";
	
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
	
	public void Registration()
	{
		IO.println("Employee Registration:");
		Connection con = connect();
		try
		{
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			
			String emp_id = IO.readln("Enter Employee ID:");
			String name = IO.readln("Enter Employee Full Name:");
			int salary = Integer.parseInt(IO.readln("Enter Employee Salary"));
			String fname = IO.readln("Enter Employee First Name");
			String lname = IO.readln("Enter Employee Last Name");
			String mail = IO.readln("Enter Employee Email ID:");
			long phone = Long.parseLong(IO.readln("Enter Employee Phone Number"));
			
			pstmt1.setString(1, emp_id);
			pstmt1.setString(2, name);
			pstmt1.setInt(3, salary);
			pstmt1.setString(4, fname);
			pstmt1.setString(5, lname);
			pstmt1.setString(6, mail);
			pstmt1.setLong(7, phone);
			int rowCount1 = pstmt1.executeUpdate();
			if(rowCount1<=0)
			{
				System.err.println("Data Not Inserted!!!!");
			}
			else
			{
				IO.println("Employee Registration Successful");
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void login()
	{
		Connection con = connect();
		try
		{
			PreparedStatement pstmt2 = con.prepareStatement(sqlQuery2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			IO.println("Employee Login:");
			
			String emp_id = IO.readln("Enter Employee Id");
			String name = IO.readln("Enter Employee Name:");
		    ResultSet rs=pstmt2.executeQuery();
		    
		    while(rs.next())
		    {
		    	if(rs.getString(1).equalsIgnoreCase(emp_id) && rs.getString(2).equalsIgnoreCase(name))
		    	{
		    		IO.println("Login Successfull");
		    	}
		    	
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void Employee_Operations()
	{
		try
		{
			Connection con= connect();
			PreparedStatement pstmt2 = con.prepareStatement(sqlQuery2);
			
			while(true)
			{
				IO.println("=========Employee Details=========");
				IO.println("1. Show All Employee");
				IO.println("2. Update MailID & Phno");
				IO.println("3. Delete Employee who's salary between 50000-70000");
				IO.println("4. Employee 10% Increament of each Enmployee Salary");
				int choice = Integer.parseInt(IO.readln("Enter Your Choice:"));
				
				switch(choice)
				{
				case 1:
					IO.print("View All Employee Data ");
					ResultSet rs = pstmt2.executeQuery();
					while(rs.next())
					{
						IO.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getLong(2));
					}
					break;
				case 2:
					IO.println("\n Update Employee Mail And Phno");
					String mail = IO.readln("Enter New Mail ID :");
					long phone = Long.parseLong(IO.readln("Enter New Phno:"));
					
					String emp_id2 = IO.readln("Enter Employee Id which you want to update");
					
					
					PreparedStatement pstmt3 = con.prepareStatement(sqlQuery3);
					
					pstmt3.setString(1, mail);
					pstmt3.setLong(2, phone);
					pstmt3.setString(3, emp_id2);
					
					int rowCount2 =pstmt3.executeUpdate();
					if(rowCount2<=0)
					{
						System.err.println("Data with ID "+emp_id2+" not found");
					}
					else
					{
						IO.println("Data Updated ");
					}
					break;
				case 3:
					IO.println("\n Delete Employee Data \n");
					IO.println("Enter Employee Id:");
					String emp_id = IO.readln();
					
				    PreparedStatement pstmt4 = con.prepareStatement(sqlQuery4);
				    
				    pstmt4.setString(1, emp_id);
				    
				    int rowCount3 =pstmt4.executeUpdate();
					if(rowCount3>0)
					{
						IO.println("Patient Record : " +emp_id+"deleted");
					}
					else
						IO.println("Patient record with patient id : "+emp_id+"is not found");
					break;
					
							
					
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args)
	{
		EmployeeRegistration er = new EmployeeRegistration();
		
			er.Registration();
			er.login();
			er.Employee_Operations();
		
		
	}
}