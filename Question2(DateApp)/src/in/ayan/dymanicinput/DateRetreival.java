package in.ayan.dymanicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ayan.jdbcUtil.*;

public class DateRetreival {

	public static void main(String[] args) throws ParseException {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter name of employee to check record ::	");
		String name= sc.next();
						
		String sqlSelectQuery ="select * from employeedata where name=?";
		
		try {
			connection= JdbcUtil.getJdbcConnection();
									
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlSelectQuery);
			if(pstmt!= null)
			{
				pstmt.setString(1,name);
				resultSet=pstmt.executeQuery();		
							
			}
			if(resultSet != null)
			{
				if (resultSet.next()) 
				{
					//name
					String userName = resultSet.getString(1);
					
					//address
					String userAddress = resultSet.getString(2);
					
					//gender
					String userGender = resultSet.getString(3);
					
					//DOB
					java.sql.Date userDob = resultSet.getDate(4);
					SimpleDateFormat sdfDob = new SimpleDateFormat("dd-MM-yyyy");
					String dateOfBirth = sdfDob.format(userDob);

					//DOJ
					java.sql.Date userDoj = resultSet.getDate(5);
					SimpleDateFormat sdfDoj = new SimpleDateFormat("MM-dd-yyyy");
					String dateOfJoining = sdfDoj.format(userDoj);
					
					//DOM
					java.sql.Date dateOfMarriage = resultSet.getDate(6);
					
					
					System.out.println("Name is :: " + userName);
					System.out.println("Address is :: " + userAddress);
					System.out.println("Gender is :: " + userGender);
					System.out.println("DOB is :: " + dateOfBirth);
					System.out.println("DOJ is :: " + dateOfJoining);
					System.out.println("DOM is :: " + dateOfMarriage);
				}
				else
				{
					System.out.println("Record Not found");
				}
			}
				
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				JdbcUtil.closeConnection(null, pstmt, connection);
				sc.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
			
	}

}
