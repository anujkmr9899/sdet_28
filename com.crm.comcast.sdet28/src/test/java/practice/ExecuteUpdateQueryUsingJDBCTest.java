package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteUpdateQueryUsingJDBCTest {

	public static void main(String[] args) throws SQLException {
		// Register the driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		//to establish the database connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root","root");
		//issue statement
		
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("insert into student values(103, 'vikas','computer')");
		if(result==1) {
			System.out.println("database has been updated");
		}
		else System.out.println("database has not been updated");
		connection.close();
	}

}
