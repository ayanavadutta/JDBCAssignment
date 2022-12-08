package in.ayan.dymanicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import in.ayan.jdbcUtil.*;

public class DateInsert {

	public static void main(String[] args) throws ParseException {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		
		//Name
		System.out.print("Enter name :: ");
		String name= sc.next();
		
		//Address
		System.out.print("Enter address :: ");
		String address= sc.next();
		
		//Gender
		System.out.print("Enter gender :: ");
		String gender= sc.next();
		
		//DOB		
		System.out.print("Enter the Date Of Birth(dd-mm-yyyy) :: ");
		String sdob = sc.next();
		
		SimpleDateFormat sdfdob = new SimpleDateFormat("dd-MM-yyyy");
		Date uDateOfBirth = sdfdob.parse(sdob);
		
		long birthDate = uDateOfBirth.getTime();
		java.sql.Date birthDateFinal = new java.sql.Date(birthDate);
		
		
		//DOJ
		System.out.print("Enter the Date Of Joining(mm-dd-yyyy) :: ");
		String sdoj = sc.next();
		
		SimpleDateFormat sdfdoj = new SimpleDateFormat("MM-dd-yyyy");
		Date uDateOfJoining = sdfdoj.parse(sdoj);
		
		long joinDate = uDateOfJoining.getTime();
		java.sql.Date joinDateFinal = new java.sql.Date(joinDate);
		
		//DOM
		System.out.print("Enter the Date Of Marriage(yyyy-mm-dd) :: ");
		String sdom = sc.next();
		
		java.sql.Date marriageDateFinal = java.sql.Date.valueOf(sdom);
		
		
		//Query		
		String sqlInsertQuery ="insert into employeedata(name,address,gender,dob,doj,dom) values(?,?,?,?,?,?)";
		
		try {
			
			connection= JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlInsertQuery);
			if(pstmt!= null)
			{
				pstmt.setString(1,name);
				pstmt.setString(2,address);
				pstmt.setString(3,gender);
				pstmt.setDate(4,birthDateFinal);
				pstmt.setDate(5,joinDateFinal);
				pstmt.setDate(6,marriageDateFinal);
				
				int rowsAffected = pstmt.executeUpdate();
				System.out.println("No of records inserted :: "+rowsAffected);
				
			}
				
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				JdbcUtil.closeConnection(null, pstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
