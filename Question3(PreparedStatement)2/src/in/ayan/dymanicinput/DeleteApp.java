package in.ayan.dymanicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ayan.jdbcUtil.JdbcUtil;

public class DeleteApp {

	public static void main(String[] args) throws SQLException {

		
		Connection connection = null;
		PreparedStatement pstmt = null;

		Scanner scanner = new Scanner(System.in);


		System.out.print("Enter the ID of student to be deleted:: ");
		int sid = scanner.nextInt();

		String sqlDeleteQuery = "delete from student where sid = ? ";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			
			if (pstmt != null) {
				
				pstmt.setInt(1, sid);
												
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of records DELETED is :: " + rowAffected);
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
