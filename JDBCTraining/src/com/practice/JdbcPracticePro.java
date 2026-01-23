package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcPracticePro 
{
	String driver="oracle.jdbc.OraclaDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="Rehan";
	String dbPwd = "Hulk";
	
	Connection connect()
	{
		Connection con = null;
		try
		{
			IO.println("Connecting");
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl, dbUname, dbPwd);
			IO.println("Connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	void Patient_Operation()
	{
		try
		{
			Connection con = connect();
			PreparedStatement pstm = con.prepareStatement("Insert into patient values (?,?,?,?)");
			PreparedStatement pstm2 = con.prepareStatement("select * from patient");
			PreparedStatement pstm3 = con.prepareStatement("select * from patient where pid=?");
			PreparedStatement pstm4 = con.prepareStatement("update patient set age=? where pid=?");
			PreparedStatement pstm5 = con.prepareStatement("delete from patient where pid =?");
			
			while(true)
			{
				IO.println("-------patient data---------");
				IO.println("1. Add patient details");
				IO.println("2. retrive patient details");
				IO.println("3. update patient details");
				IO.println("4. delete patient details");
				IO.println("5. exit");
				
				int choice = Integer.parseInt(IO.readln("Enter your choice:"));
				switch(choice)
				{
				case 1 :
				int pid = Integer.parseInt(IO.readln("Enter Patient ID : "));
				String pname = IO.readln("Enter Patient Name");
				int age = Integer.parseInt("Enter Patient Age :");
				long pcontact = Long.parseLong("Enter Patient contact number"); 
				
				pstm.setInt(1, pid);
				pstm.setString(2, pname);
				pstm.setInt(3, age);
				pstm.setLong(4, pcontact);
				int result = pstm.executeUpdate();
				if(result>0)
				{
					IO.println("Patient Inserted");
				}
				else
				{
					IO.println("Not inserted");
				}
				break;
				
				case 2:
					IO.println("\nView all patient data");
					ResultSet rs = pstm2.executeQuery();
					while(rs.next())
					{
						IO.println(); 
					}
					
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
				    System.exit(0);
				}
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void main()
	{
		JdbcPracticePro p5 = new JdbcPracticePro();
		p5.Patient_Operation();
	}
}
