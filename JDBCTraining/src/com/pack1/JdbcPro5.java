
package com.pack1;

import java.sql.Connection;								
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcPro5 {
	String driver = "oracle.jdbc.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname = "REHAN";
	String dbPwd = "HULK";

	Scanner sc = new Scanner(System.in);
	
	Connection connect() {
		Connection con = null;
		try {
			IO.println("connecting");
			Class.forName(driver);
			con = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
			IO.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	void patient_operations() {
		try {
			Connection con = connect();
			PreparedStatement pstm = con.prepareStatement("insert into patient values(?,?,?,?)");
			  PreparedStatement pstm2 = con.prepareStatement("SELECT*FROM PATIENT");
			  PreparedStatement pstm3 = con.prepareStatement("select * from patient where pid=?");
			  PreparedStatement pstm4 = con.prepareStatement(" update patient set age=? where pid =?");
			  PreparedStatement pstm5 = con.prepareStatement(" delete from patient where pid=?");
			  
			while (true) {

				IO.println("-------patient data---------");
				IO.println("1. Add patient details");
				IO.println("2. View all patient deatils");
				IO.println("3. retrive patient details");
				IO.println("4. update patient details");
				IO.println("5. delete patient details");
				IO.println("6. exit");
				int choice = Integer.parseInt(IO.readln("enter your choice :"));
				switch (choice) {

				case 1:
					String pid = IO.readln("enter patient id :");
					String pname = IO.readln("enter patient name :");
					int age = Integer.parseInt(IO.readln("enter age :"));
					long pcontact = Long.parseLong(IO.readln("enter contact number :"));

					pstm.setString(1, pid);
					pstm.setString(2, pname);
					pstm.setInt(3, age);
					pstm.setLong(4, pcontact);
					int result = pstm.executeUpdate();
					if(result>0) {
						IO.println("patient inserted");						
					}
					else {
						IO.println("not inserted");
					}
					break;
				case 2:
					IO.println("\n view all patients data\n");
					ResultSet rs = pstm2.executeQuery();
					while(rs.next())
					{
						IO.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getLong(4));
					}
					IO.println();
					break;
				case 3:
					IO.println("\nRetrieve patient details\n");
					
					IO.println("Enter patient id");
					String p_id2 = sc.nextLine();
					pstm3.setString(1, p_id2);
					ResultSet rs2=pstm3.executeQuery();
					if(rs2.next())
					{
						IO.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getLong(4));
					}
					else
						IO.println("Patient record with patient id : "+p_id2+"is not found");
					break;
				case 4:
					IO.println("\nUpdate patient data\n");
					IO.println("Enter patient Id :");
					String p_id3 = sc.nextLine();
					IO.println("Enter patient age");
					int p_age2=Integer.parseInt(sc.nextLine());
					pstm4.setInt(1, p_age2);
					pstm4.setString(2, p_id3);
					int rowCount=pstm4.executeUpdate();
					if(rowCount>0)
					{
						IO.println("Patient Record : " +p_id3+"updated");
					}
					else
						IO.println("Patient record with patient id : "+p_id3+"is not found");
					break;				
				case 5:
					IO.println("\nDelete patient data\n");
					IO.println("Enter patient Id :");
					String p_id4 =sc.nextLine();
					pstm5.setString(1, p_id4);
					int rowCount2 =pstm5.executeUpdate();
					if(rowCount2>0)
					{
						IO.println("Patient Record : " +p_id4+"deleted");
					}
					else
						IO.println("Patient record with patient id : "+p_id4+"is not found");
					break;
				case 6:
					IO.println();
					System.exit(0);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void main() {
		JdbcPro5 j5=new JdbcPro5();
		j5.patient_operations();
	}
}