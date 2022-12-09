package in.ayan.dymanicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ayan.jdbcUtil.JdbcUtil;

public class UpdateApp {

	public static void main(String[] args) throws SQLException {

		
		Connection connection = null;
		PreparedStatement pstmt = null;

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the age to be update: ");
		int sage = scanner.nextInt();

		System.out.print("Enter the name:: ");
		String sname = scanner.next();

		String sqlUpdateQuery = "update student set sage = ? where sname = ? ";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			
			if (pstmt != null) {
				
				pstmt.setInt(1, sage);
				pstmt.setString(2, sname);
												
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of records UPDATED is :: " + rowAffected);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection(null, pstmt, connection);

			if (scanner != null)
				scanner.close();
		}

	}

}
