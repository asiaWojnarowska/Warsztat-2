package pl.coderslab.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitApp {

	
	private static final String createGroupsTable = "CREATE TABLE user_group (id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY(id))";
	
	private static String createUsersTable = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, username VARCHAR(255), email VARCHAR(255) UNIQUE, password VARCHAR(245), user_group_id INT, PRIMARY KEY(id), FOREIGN KEY(user_group_id) REFERENCES user_group(id) ON DELETE SET NULL)";
	
	private static String createExerciseTable = "CREATE TABLE exercise (id INT AUTO_INCREMENT, title VARCHAR(255), description TEXT, PRIMARY KEY (id))";
	
	private static String createSolutionTable = "CREATE TABLE solution (id INT AUTO_INCREMENT, created DATETIME, updated DATETIME, description TEXT, exercise_id INT, users_id BIGINT, PRIMARY KEY(id), FOREIGN KEY (exercise_id) REFERENCES exercise(id) , FOREIGN KEY(users_id) REFERENCES users(id) ON DELETE SET NULL)";
	
	
	public static void main(String[] args) {
		
	
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztaty?useSSL=false&characterEncoding=utf8","root", "coderslab")) {
		

		Statement stat = conn.createStatement();
		stat.executeUpdate(createGroupsTable);	
		stat.executeUpdate(createUsersTable);
		stat.executeUpdate(createExerciseTable);
		stat.executeUpdate(createSolutionTable);
			
			
			
			
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		
		
		
		
	}

}
