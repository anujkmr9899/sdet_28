package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteUpdateQueryUsingtry_catchTest {

	public static void main(String[] args) throws SQLException {
		
	Connection connection = null;
	try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//establish the database connection
		connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentinfo","root", "root");
		// issue the create statement
		Statement statement = connection.createStatement();
		//execute the query
		
		ResultSet result = statement.executeQuery("select * from student;");
		while(result.next()) {
			System.out.println(result.getString(0 )+"\t"+result.getString(2)+"\t"+result.getString(3));
		}
	}
		catch(Exception e) {
			
		}
	finally {
		//close the database connection
				connection.close();
	}
		
		
	}
}
