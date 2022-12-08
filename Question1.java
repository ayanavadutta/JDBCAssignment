package in.ineuron.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question1 {

	public static void main(String[] args) throws SQLException 
	{

		Connection connection =null;
		Statement statement =null;
		ResultSet resultSet = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/enterprisejava","root","root123");
			
			if (connection!= null)
			{
				statement = connection.createStatement();
				
				if(statement != null) 
				{
					
				boolean exit = false;
				do {
				    
							System.out.println("Enter 1: To Create 2:To Read 3:To Update 4:To Delete 5:To Exit");
							System.out.print("Enter your choice here");
						    int n=sc.nextInt();
						    switch(n)
						    {
							    case 1:
							    	
									System.out.print("Enter the Student Name");
									String sname1= sc.next();
									System.out.print("Enter the Student Age");
									int sage1= sc.nextInt();
									System.out.print("Enter the Student Address");
									String saddr1= sc.next();
									
									String sqlInsertQuery =String.format("insert into student(sname,sage,saddr) values('%s',%d,'%s')",sname1,sage1,saddr1); 
									int noOfRows1 = statement.executeUpdate(sqlInsertQuery);
									System.out.println("No of records created is ::"+ noOfRows1);
									
								
								break;
								
							    case 2:
							    	
							    	String sqlSelectQuery = "select * from student";
									resultSet = statement.executeQuery(sqlSelectQuery);
									
									if(resultSet != null) {
										
										while(resultSet.next())
										{
											int sid2 = resultSet.getInt("sid");
											String sname2= resultSet.getString("sname");
											int sage2 = resultSet.getInt("sage");
											String saddr2 = resultSet.getString("saddr");
											
											System.out.println(sid2 +"\t"+ sname2 +"\t"+sage2+"\t"+ saddr2);	
										}
									}
							    	
							    break;
							    
							    case 3:
							    	
							    	System.out.println("Enter the Student id");
									int sid3= sc.nextInt();
									System.out.println("Enter the sage");
									int sage3= sc.nextInt();
							    	String sqlUpdateQuery = String.format("update student set sage='%d' where sid='%d'",sage3,sid3);
									int noOfRows3 = statement.executeUpdate(sqlUpdateQuery);
									System.out.println("No of rows updated is ::"+ noOfRows3);
									
									break;
									
							    case 4:
							    	
							    	System.out.println("Enter the Student id");
									int sid4= sc.nextInt();
							    	String sqlDeleteQuery = String.format("delete from student where sid = '%d'",sid4);
									int noOfRows4 = statement.executeUpdate(sqlDeleteQuery);
									System.out.println("No of records deleted is ::"+ noOfRows4);

								break;
							    case 5:
							        exit =true;
							    break;
							        
							    default:
							        System.out.println("Thank you visit again");
						    }
				
					}while(!exit);
			
				}
			}	    
				
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//6. closing the resources
			
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}	
			if(sc!= null) {
				sc.close();
			}
		}
	

	}

}
