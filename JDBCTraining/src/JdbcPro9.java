import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class JdbcPro9 
{
	String driver = "oracle.jdbc.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUname ="REHAN";
	String dbPwd = "HULK";
	
	String sqlQuery ="UPDATE TRAINSEATAVAILABILITTY SET AVAILABLE_SEATS=ABAILABLE_SEATS-1 WHERE TRAIN_ID=?_"
			+" and JOURNEY_DATE=? and CLASS=? and AVAILABLE_SEATS>0";
	
	String sqlQuery2="INSERT INTO BOOKINGDETAILS VALUES(?,?,?,?,?)";
	String sqlQuery3 ="SELECT PAYMENT_STATUS FROM CUSTOMERPAYMENT WHERE CUSTOMER_ID=?";
	String sqlQuery4 ="update bookingdetails set status=";
	
	
		
	
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
		try
		{
			Connection con=connect();
			System.out.println("Database connected sucessfully!!!!");
			System.out.println("Beforee disabling Autocommit===>"+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("After disabaling  autocommit====>"+con.getAutoCommit());
			
			PreparedStatement pstmt1 = con.prepareStatement(sqlQuery);
			pstmt1.setString(1, "12345");
			pstmt1.setString(2, "2024-10-10");
			pstmt1.setString(3, "SLEEPER");
			
			int rCount = pstmt1.executeUpdate();
			if(rCount==0)
			{
				throw new RuntimeException("Seat not available for booking");	
			}
			else
				System.out.println("Seat is locked for booking");
			Savepoint sp = con.setSavepoint();
			
		   PreparedStatement pstmt2 = con.prepareStatement(sqlQuery2);
		   pstmt2.setString(1, "B101");
		   pstmt2.setString(2, "12345");
		   pstmt2.setString(3, "C123");
		   pstmt2.setInt(4, 1);
		   
		   if(rCount==0)
			   throw new RuntimeException("Booking record not created");
		   else
			   System.out.println("Booking recor created\n waiting for payment confirmation!!!!");
		   
		   PreparedStatement pstm3 = con.prepareStatement(sqlQuery3);
		   pstm3.setString(rCount, dbPwd);
		   
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
