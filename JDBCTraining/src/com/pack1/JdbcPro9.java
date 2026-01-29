package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class JdbcPro9 {
	String driver = "oracle.jdbc.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String uName = "REHAN";
	String pwd = "HULK";
	String sqlQuery1="update trainseatavailability set AVAILABLE_SEATS=AVAILABLE_SEATS-1 WHERE TRAIN_ID=? "+ 
	                 "AND JOURNEY_DATE=? AND CLASS=? AND AVAILABLE_SEATS>0";
	String sqlQuery2  = "insert into bookingdetails values(?,?,?,?,?)";
	String sqlQuery3 = "select PAYMENT_STATUS from customerpayment where CUSTOMER_ID=?";
	String sqlQuery4 = "update bookingdetails set STATUS='CONFIRMED' where CUSTOMER_ID=?";
	
	 Connection connect() {
		  Connection con = null;
		  try {
			  Class.forName(driver);
			  con = DriverManager.getConnection(url, uName, pwd);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return con;
	  }
	 
	 void meth1() {
		 IO.println("Implementing Transaction Management");
		 Connection con = connect();
		 IO.println("Database connected successfully");
		 
		 try{
			 IO.println("Before disabling AutoCommit : "+con.getAutoCommit());
			 con.setAutoCommit(false);
			 IO.println("After disabling AutoCommit : "+con.getAutoCommit());
			 
			 PreparedStatement psm1 =  con.prepareStatement(sqlQuery1);
			 psm1.setString(1,"12345");
			 psm1.setString(2, "2024-10-10");
			 psm1.setString(3, "SLEEPER");
			 
			 int rowCount1 = psm1.executeUpdate();
			 if(rowCount1==0) {
				 throw new RuntimeException("Seats not available for booking!!");
			 }
			 else {
				 IO.println("Seat is locked for booking");
			 }
			 Savepoint sp = con.setSavepoint();
			 
			 PreparedStatement psm2 =  con.prepareStatement(sqlQuery2);
			 psm2.setString(1, "1001");
			 psm2.setString(2, "12345");
			 psm2.setString(3, "C123");
			 psm2.setInt(4,1);
			 psm2.setString(5, "Payment Pending");
			 
			 int rowCount2 = psm2.executeUpdate();
			 if(rowCount2==0) {
				 throw new RuntimeException("Booking record not created!!");
			 }
			 else {
				 IO.println("Booking record created & awaiting for payment confirmation");
			 }
			 
			 PreparedStatement psm3 =  con.prepareStatement(sqlQuery3);
			 psm3.setString(1, "C123");
			 
			 ResultSet rs = psm3.executeQuery();
			 String status="Failed";
			 if(rs.next()) {
				  status=rs.getString(1);
				 if(status.equals("Failed")) {
					 throw new RuntimeException("Transaction Failed");
				 }
				 else {
					 PreparedStatement psm4 =  con.prepareStatement(sqlQuery4);
					 psm4.setString(1, "C123");
					 
					 int rowCount3 = psm4.executeUpdate();
					 if(rowCount3==0) {
						 throw new RuntimeException("Transaction Failed at Payment portal");
					 }
					 else {
						 IO.println("Ticket booked successfully");
						 con.commit();
					 }
				 }
			 }
			 else {
				 IO.println("psm3 problem");
			 }
			 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
  void main() {
	  JdbcPro9 obj = new JdbcPro9();
	  obj.meth1();	 
  }
}